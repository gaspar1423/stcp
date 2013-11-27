package com.android.stcp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.android.stcp.MapActivity;
import com.android.stcp.R;

public class SplashActivity extends Activity {

	private static final String TAG = SplashActivity.class.getSimpleName();
	private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

	private View splashLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		// Full screen Mode
		getActionBar().hide();

		splashLayout = findViewById(R.id.splashLayout);

		TextView versionText = (TextView) findViewById(R.id.versionText);
		String versionName = null;
		try {
			versionName = getPackageManager().getPackageInfo(getPackageName(),
					0).versionName;
		} catch (NameNotFoundException e) {
		}
		versionText.setText("v " + versionName);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		splashLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(SplashActivity.this, MapActivity.class);
				SplashActivity.this.startActivity(i);
				finish();
			}

		}, getResources().getInteger(R.integer.splashscreen_delay_time_ms));
	}

}
