package org.moziqi.scheduled;

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent startService = new Intent(this, ScheduledService.class);
		this.startService(startService);
	}

	public static String readCacheConfig(Context mContext, String fileName) {
		String libs = "";
		try {
			String mfileName = null;
			mfileName = mContext.getFilesDir().toString() + File.separator + fileName;
			Log.e("TAG", mfileName);
			File myFile = new File(mfileName);
			if (!myFile.exists()) {
				return libs;
			}
			FileInputStream in = new FileInputStream(myFile);
			int length = in.available();
			byte[] buf = new byte[length];
			in.read(buf);
			libs = new String(buf, "utf-8");
			in.close();
			return libs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return libs;
	}

	public static void getLauncherActivity(Context context) {
		Intent mainIntent = new Intent(Intent.ACTION_MAIN);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		PackageManager packageManager = context.getPackageManager();
		for (ResolveInfo info : packageManager.queryIntentActivities(mainIntent, 0)) {
			Log.e("TAG", info.activityInfo.packageName);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
