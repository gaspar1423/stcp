package com.android.stcp.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.stcp.R;
import com.android.stcp.StcpApp;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;
import com.stcp.volley.RequestManager;
import com.stcp.volley.StcpJsonObjectRequest;

public class LoginActivity extends ActionBarActivity {

	private static final String TAG = LoginActivity.class.getSimpleName();

	private EditText userEditText;
	private EditText passEditText;
	private View loginFormView;
	private View loginStatusView;
	private TextView loginStatusMessageView;
	private String username;
	private String password;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		getActionBar().hide();

		userEditText = (EditText) findViewById(R.id.user);
		passEditText = (EditText) findViewById(R.id.pass);

		passEditText
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_ACTION_GO) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		loginFormView = findViewById(R.id.login_form);
		loginStatusView = findViewById(R.id.login_status);
		loginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		Button loginBtn = (Button) findViewById(R.id.btnLogin);
		loginBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});

		Button experimentarBtn = (Button) findViewById(R.id.btnExperimentar);
		experimentarBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(LoginActivity.this, DrawerActivity.class);
				LoginActivity.this.startActivity(i);
				finish();
			}
		});
	}

	public void attemptLogin() {
		userEditText.setError(null);
		passEditText.setError(null);

		boolean cancel = false;
		View focusView = null;

		username = userEditText.getText().toString();
		password = passEditText.getText().toString();

		if (password.equals("")) {
			passEditText.setError("Parametro vazio");
			focusView = passEditText;
			cancel = true;
		}

		if (username.equals("")) {
			userEditText.setError("Parametro vazio");
			focusView = userEditText;
			cancel = true;
		}

		if (cancel) {
			focusView.requestFocus();
		} else {
			if (password.equals("1234") && username.equals("ufp")) {
				loginStatusMessageView
						.setText(R.string.login_progress_signing_in);
				showProgress(true);
				doRequestLogin(username, password, false);
			} else {
				loginStatusMessageView
						.setText(R.string.login_progress_signing_in);
				showProgress(true);
				doRequestLogin(username, password, true);
			}
		}
	}

	private void fakeLogin() {
		showProgress(false);
		Intent i = new Intent(LoginActivity.this, DrawerActivity.class);
		LoginActivity.this.startActivity(i);
		finish();
	}

	private void doRequestLogin(final String username, final String password,
			boolean fakeError) {

		String path = "";
		if (fakeError) {
			path = getString(R.string.error_login_path);
		} else {
			path = getString(R.string.login_path);
		}

		StcpJsonObjectRequest request = RequestManager.createJsonObjectRequest(
				Method.POST, path, null, new Listener<JsonObject>() {

					@Override
					public void onResponse(JsonObject response) {
						Log.i(TAG, response.toString());
						try {
							int code = response.get("code").getAsInt();
							if (code == 0) {
								fakeLogin();
							} else {
								String descricao = response.get("descricao")
										.getAsString();
								Toast t = Toast.makeText(
										StcpApp.getAppContext(), descricao,
										Toast.LENGTH_LONG);
								t.show();
								showProgress(false);
							}
						} catch (Exception e) {
							Log.e(TAG, "Exception", e);
						}

					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast t = Toast.makeText(StcpApp.getAppContext(),
								R.string.error_no_connection, Toast.LENGTH_LONG);
						t.show();
						showProgress(false);
						Log.e(TAG, "volley error Request:"
								+ error.getClass().getName(), null);
					}

				});

		request.addParam("username", username);
		request.addParam("password", password);

		RequestManager.addToRequestQueue(request, TAG);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			loginStatusView.setVisibility(View.VISIBLE);
			loginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							loginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			loginFormView.setVisibility(View.VISIBLE);
			loginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							loginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			loginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
