package com.android.stcp;

import android.app.Application;
import android.content.Context;

public class StcpApp extends Application {

	private static Application app = null;

	public void onCreate() {
		super.onCreate();
		setAppContext(this);
	}

	public static Application getApp() {
		return app;
	}

	private static void setAppContext(final Application leApp) {
		StcpApp.app = leApp;
	}

	public static Context getAppContext() {
		return app.getApplicationContext();
	}

}