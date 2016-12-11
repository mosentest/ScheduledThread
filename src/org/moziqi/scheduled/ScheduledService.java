package org.moziqi.scheduled;

import org.moziqi.utils.LogDebug;
import org.moziqi.utils.ScheduledUtils;
import org.moziqi.utils.WakeLockUtils;
import android.annotation.SuppressLint;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class ScheduledService extends Service {

	private final static int SCHEDULE_CODE = 1;

	private final static long SHEDULED_TIME = 1 * 60 * 60 * 1000L; // 一小时
	// private final static long SHEDULED_TIME = 30 * 1000L; // 一小时

	private Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			switch (msg.what) {
			case SCHEDULE_CODE:
				if (run1 == null) {
					run1 = new Run(getApplicationContext(), "1.handler");
				}
				run1.run();
				handler.removeMessages(SCHEDULE_CODE);
				handler.sendEmptyMessageDelayed(SCHEDULE_CODE, SHEDULED_TIME);
				break;

			default:
				break;
			}
			super.dispatchMessage(msg);
		}
	};

	private Run run1;

	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		super.onCreate();
		LogDebug.e(getApplicationContext(), "ScheduledService.onCreate");
		WakeLockUtils.acquireWakeLock(getApplicationContext());
		// 启动定时器
		// 1.handler实现定时器
		handler.removeMessages(SCHEDULE_CODE);
		handler.sendEmptyMessage(SCHEDULE_CODE);
		// 2.ScheduledThreadPoolExecutor
		ScheduledUtils.getInstance(getApplicationContext()).doSchedule(new Run(getApplicationContext(), "2.ScheduledThreadPoolExecutor"), SHEDULED_TIME);
		// 3.alarm
		ScheduledUtils.getInstance(getApplicationContext()).startAlarmBroadcast(ScheduledReceiver.class, "org.moziqi.sheduled.action", SCHEDULE_CODE, SHEDULED_TIME);
		// 4. jobService
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
			ComponentName componentName = new ComponentName(this, SheduledJobService.class);
			JobInfo.Builder builder = new JobInfo.Builder(SCHEDULE_CODE, componentName);
			builder.setPeriodic(SHEDULED_TIME);
			scheduler.schedule(builder.build());
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@SuppressLint("NewApi")
	@Override
	public void onDestroy() {
		LogDebug.e(getApplicationContext(), "ScheduledService.onDestroy");
		handler.removeMessages(SCHEDULE_CODE);
		handler = null;
		ScheduledUtils.getInstance(getApplicationContext()).stopAlarmBroadcast(ScheduledReceiver.class, "org.moziqi.sheduled.action", SCHEDULE_CODE);
		ScheduledUtils.getInstance(getApplicationContext()).onDestroy();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
			scheduler.cancelAll();
		}
		WakeLockUtils.releaseWakeLock();
		super.onDestroy();
	}
}
