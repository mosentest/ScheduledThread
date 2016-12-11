package org.moziqi.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * http://blog.csdn.net/gyl31016/article/details/51792209
 * http://www.cnblogs.com/mengdd/p/4892856.html
 * 
 * @author moziqi
 *
 */
public class PermissionUtils {

	public final static String[] ContactsPermissions = new String[] { Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_CONTACTS };

	/**
	 * 通讯录权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkContactsPermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestContactsPermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, ContactsPermissions, requestCode);
	}

	@SuppressLint("InlinedApi")
	public final static String[] PhonePermissions = new String[] { Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.USE_SIP,
			Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.ADD_VOICEMAIL };

	/**
	 * 通话权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkPhonePermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.USE_SIP) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.ADD_VOICEMAIL) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestPhonePermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, PhonePermissions, requestCode);
	}

	public final static String[] CalendarPermissions = new String[] { Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR };

	/**
	 * 日历权限
	 * 
	 * @param context
	 * @param requestCode
	 * @return
	 */
	public static boolean checkCalendarPermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestCalendarPermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, CalendarPermissions, requestCode);
	}

	public final static String[] CameraPermissions = new String[] { Manifest.permission.CAMERA };

	/**
	 * 相机权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkCameraPermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestCameraPermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, CameraPermissions, requestCode);
	}

	@SuppressLint("InlinedApi")
	public final static String[] BodySensorsPermissions = new String[] { Manifest.permission.BODY_SENSORS };

	/**
	 * 身体传感器权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkBodySensorsPermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestBodySensorsPermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, BodySensorsPermissions, requestCode);
	}

	public final static String[] LocationPermissions = new String[] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION };

	/**
	 * 定位权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkLocationPermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestLocationPermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, LocationPermissions, requestCode);
	}

	public final static String[] MicrophonePermissions = new String[] { Manifest.permission.RECORD_AUDIO };

	/**
	 * 录音权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkMicrophonePermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestMicrophonePermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, MicrophonePermissions, requestCode);
	}

	public final static String[] SmsPermissions = new String[] { Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.RECEIVE_MMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS };

	/**
	 * 短信权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkSmsPermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_WAP_PUSH) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_MMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestSmsPermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, SmsPermissions, requestCode);
	}

	@SuppressLint("InlinedApi")
	public final static String[] StoragePermissions = new String[] { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };

	/**
	 * 存储权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkStoragePermissions(Context context) {
		if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
				|| ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {// 没有权限
			return false;
		} else {
			return true;
		}
	}

	public static void requestStoragePermissions(Activity context, int requestCode) {
		ActivityCompat.requestPermissions(context, StoragePermissions, requestCode);
	}

	/**
	 * http://www.cnblogs.com/fangyucun/p/4027750.html
	 * 
	 * @param context
	 */
	@SuppressLint("NewApi")
	public static void getAppDetailSettingIntent(Context context) {
		Intent localIntent = new Intent();
		localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (Build.VERSION.SDK_INT >= 9) {
			localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
		} else if (Build.VERSION.SDK_INT <= 8) {
			localIntent.setAction(Intent.ACTION_VIEW);
			localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
			localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
		}
		if (isIntentAvailable(context, localIntent)) {
			context.startActivity(localIntent);
		}
	}

	/**
	 * 判断是否有可以接受的Activity
	 * 
	 * @param context
	 * @param action
	 * @return
	 */
	public static boolean isIntentAvailable(Context context, Intent intent) {
		if (intent == null)
			return false;
		return context.getPackageManager().queryIntentActivities(intent, PackageManager.GET_ACTIVITIES).size() > 0;
	}

}
