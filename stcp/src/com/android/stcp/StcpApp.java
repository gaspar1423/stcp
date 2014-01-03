package com.android.stcp;

import android.app.Application;
import android.content.Context;

import com.stcp.volley.RequestManager;

public class StcpApp extends Application {

	private static Application app = null;

	public void onCreate() {
		super.onCreate();
		setAppContext(this);

		RequestManager.init(this);
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