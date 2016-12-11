package org.moziqi.scheduled;

import org.moziqi.utils.LogDebug;

import android.content.Context;

public class Run implements Runnable {
	private Context context;
	private String name;

	public Run(Context context, String name) {
		super();
		this.context = context;
		this.name = name;
	}

	@Override
	public void run() {
		LogDebug.e(context, name);
	}

}