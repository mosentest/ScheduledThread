package org.moziqi.scheduled;

import org.moziqi.utils.ScheduledUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class ScheduledReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if ("org.moziqi.sheduled.action".equals(action)) {
			// 接受到定时器，启动service
			Intent startService = new Intent(context, AlarmService.class);
			context.startService(startService);
			// 因为setWindow只执行一次，所以要重新定义闹钟实现循环。
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				ScheduledUtils.getInstance(context.getApplicationContext()).startAlarmBroadcast(ScheduledReceiver.class, "org.moziqi.sheduled.action", ScheduledService.SCHEDULE_CODE, ScheduledService.SHEDULED_TIME);
			}
		}
	}

}
