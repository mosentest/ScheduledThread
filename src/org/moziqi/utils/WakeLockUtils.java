package org.moziqi.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class WakeLockUtils {

	private static WakeLock wakeLock = null;

	// 获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
	public static void acquireWakeLock(Context context) {
		context = context.getApplicationContext();
		if (null == wakeLock) {
			PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
			wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "HEMSService");
			if (null != wakeLock) {
				wakeLock.acquire();
			}
		}
	}

	// 释放设备电源锁
	public static void releaseWakeLock() {
		if (null != wakeLock) {
			wakeLock.release();
			wakeLock = null;
		}
	}
}
