package org.moziqi.scheduled;

import org.moziqi.utils.LogDebug;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmService extends Service {

	private Run run3;

	@Override
	public void onCreate() {
		super.onCreate();
		run3 = new Run(getApplicationContext(), "3.alarm");
		LogDebug.e(getApplicationContext(), "AlarmService.onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		run3.run();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		LogDebug.e(getApplicationContext(), "AlarmService.onDestroy");
		super.onDestroy();
	}
}
