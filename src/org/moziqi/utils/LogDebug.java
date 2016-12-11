package org.moziqi.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

/**
 * http://blog.csdn.net/wangyuexing_blog/article/details/39005239
 * 
 * @author moziqi
 * 
 */
public class LogDebug {

	// 是否输出log信息
	public volatile static boolean DEBUG = true;

	public static final String CACHE_DIR_NAME = "A_mo";

	public static String customTagPrefix = "mo";

	private LogDebug() {
	}

	private static String generateTag() {
		StackTraceElement caller = new Throwable().getStackTrace()[2];
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
		tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
		return tag;
	}

	public static void d(String content) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.d(tag, content);
	}

	public static void d(String content, Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.d(tag, content, tr);
	}

	@SuppressLint("NewApi")
	public static void e(Context context, final String content) {
		if (!DEBUG)
			return;
		final String tag = generateTag();
		Log.e(tag, content + "");
		if (Build.VERSION.SDK_INT >= 23) {
			boolean checkStoragePermissions = PermissionUtils.checkStoragePermissions(context);
			if (checkStoragePermissions) {
				ScheduledUtils.getInstance(context).doRunnable(new Runnable() {

					@Override
					public void run() {
						write(time() + tag + " --> " + content + "\n");
					}
				});
			}
		} else {
			ScheduledUtils.getInstance(context).doRunnable(new Runnable() {

				@Override
				public void run() {
					write(time() + tag + " --> " + content + "\n");
				}
			});
		}
	}

	public static void e_nc(final String content) {
		final String tag = generateTag();
		Log.e(tag, content + "");
		new Thread() {
			public void run() {
				write(time() + tag + " --> " + content + "\n");
			};
		}.start();
	}

	public static void e(String content, final Throwable tr) {
		if (!DEBUG)
			return;
		final String tag = generateTag();
		Log.e(tag, content + "", tr);
		new Thread() {
			public void run() {
				write(time() + tag + " [CRASH] --> " + getStackTraceString(tr) + "\n");
			};
		}.start();
	}

	public static void i(String content) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.i(tag, content);
	}

	public static void i(String content, Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.i(tag, content, tr);
	}

	public static void v(String content) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.v(tag, content);
	}

	public static void v(String content, Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.v(tag, content, tr);
	}

	public static void w(String content) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.w(tag, content);
	}

	public static void w(String content, Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.w(tag, content, tr);
	}

	public static void w(Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.w(tag, tr);
	}

	@SuppressLint("NewApi")
	public static void wtf(String content) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.wtf(tag, content);
	}

	@SuppressLint("NewApi")
	public static void wtf(String content, Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.wtf(tag, content, tr);
	}

	@SuppressLint("NewApi")
	public static void wtf(Throwable tr) {
		if (!DEBUG)
			return;
		String tag = generateTag();
		Log.wtf(tag, tr);
	}

	/**
	 * 保存到日志文件
	 * 
	 * @param content
	 */
	private static synchronized void write(String content) {
		synchronized (LogDebug.class) {
			try {
				FileWriter writer = new FileWriter(getFile(), true);
				writer.write(content);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取日志文件路径
	 * 
	 * @return
	 */
	private static String getFile() {
		File sdDir = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			sdDir = Environment.getExternalStorageDirectory();
		File cacheDir = new File(sdDir + File.separator + CACHE_DIR_NAME);
		if (!cacheDir.exists())
			cacheDir.mkdir();
		File filePath = new File(cacheDir + File.separator + date() + ".txt");
		return filePath.toString();
	}

	/**
	 * 标识每条日志产生的时间
	 * 
	 * @return
	 */
	private static String time() {
		return "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())) + "] ";
	}

	/**
	 * 以年月日作为日志文件名称
	 * 
	 * @return
	 */
	private static String date() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 获取捕捉到的异常的字符串
	 * 
	 * @param tr
	 * @return
	 */
	private static String getStackTraceString(Throwable tr) {
		if (tr == null) {
			return "";
		}
		Throwable t = tr;
		while (t != null) {
			if (t instanceof UnknownHostException) {
				return "";
			}
			t = t.getCause();
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tr.printStackTrace(pw);
		return sw.toString();
	}
}