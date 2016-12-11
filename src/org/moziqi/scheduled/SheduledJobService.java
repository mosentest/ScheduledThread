package org.moziqi.scheduled;

import org.moziqi.utils.LogDebug;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

@SuppressLint("NewApi")
public class SheduledJobService extends JobService {

	private Run run4;

	@Override
	public void onCreate() {
		super.onCreate();
		LogDebug.e(getApplicationContext(), "SheduledJobService.onCreate");
		run4 = new Run(getApplicationContext(), "4. jobService");
	}

	@Override
	public void onDestroy() {
		LogDebug.e(getApplicationContext(), "SheduledJobService.onDestroy");
		super.onDestroy();
	}

	@Override
	public boolean onStartJob(JobParameters params) {
		run4.run();
		jobFinished(params, false);// 任务执行完后记得调用jobFinsih通知系统释放相关资源
		return false;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		return false;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_NOT_STICKY;
	}

}
