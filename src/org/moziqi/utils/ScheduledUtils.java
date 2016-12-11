package org.moziqi.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

public class ScheduledUtils {

	private Context mContext;
	private final static int SUM = Runtime.getRuntime().availableProcessors() ^ 2;
	private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;
	private ExecutorService mExecutorService;
	private Handler mHandler;

	private static ScheduledUtils instance;

	public static ScheduledUtils getInstance(Context context) {
		ScheduledUtils temp = instance;
		if (temp == null) {
			synchronized (ScheduledUtils.class) {
				temp = instance;
				if (temp == null) {
					temp = new ScheduledUtils(context.getApplicationContext());
					instance = temp;
				}
			}
		}
		return temp;
	}

	private ScheduledUtils(Context context) {
		super();
		this.mContext = context;
		mExecutorService = Executors.newFixedThreadPool(SUM);
		mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(SUM);
		mHandler = new Handler(context.getMainLooper());
	}

	public void doSchedule(Runnable runnable, long delay) {
		mScheduledThreadPoolExecutor.scheduleWithFixedDelay(runnable, 1, delay, TimeUnit.MILLISECONDS);
	}

	public void doRunnable(Runnable runnable) {
		mExecutorService.submit(runnable);
	}

	public void doUIRunnable(Runnable runnable) {
		mHandler.post(runnable);
	}

	public void startAlarmBroadcast(Class<?> clazz, String action, int code, long delay) {
		Intent intent = new Intent(mContext, clazz);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, code, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager manager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		manager.cancel(pendingIntent);
		manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), delay, pendingIntent);
	}

	public void stopAlarmBroadcast(Class<?> clazz, String action, int code) {
		AlarmManager manager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(mContext, clazz);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, code, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		// 取消
		manager.cancel(pendingIntent);
	}

	public void onDestroy() {
		mHandler = null;
		mExecutorService.shutdown();
		mExecutorService = null;
		mScheduledThreadPoolExecutor.shutdown();
		mScheduledThreadPoolExecutor = null;
		instance = null;
	}
}
