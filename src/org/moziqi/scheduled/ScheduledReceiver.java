package org.moziqi.scheduled;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScheduledReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if ("org.moziqi.sheduled.action".equals(action)) {
			// 接受到定时器，启动service
			Intent startService = new Intent(context, AlarmService.class);
			context.startService(startService);
		}
	}

}
