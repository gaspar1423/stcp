package com.stcp.volley;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;

import com.android.stcp.R;
import com.android.stcp.StcpApp;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

public class RequestManager {

	private static final String TAG = RequestManager.class.getSimpleName();

	private static final int MAX_IMAGE_CACHE_ENTIRES = 100;

	private static String mServerUrl;
	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;

	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(
				MAX_IMAGE_CACHE_ENTIRES));
		mServerUrl = StcpApp.getAppContext().getString(R.string.server_url);
	}

	/**
	 * Gets the volley RequestQueue.
	 * 
	 * @return RequestQueue
	 */
	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}

	/**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means that no memory caching is used. This is useful
	 * for images that you know that will be show only once.
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader() {
		if (mImageLoader != null) {
			return mImageLoader;
		} else {
			throw new IllegalStateException("ImageLoader not initialized");
		}
	}

	/**
	 * Gets the server url.
	 * 
	 * @return ServerUrl
	 */
	public static String getServerUrl() {
		return mServerUrl;
	}

	/**
	 * Gets the server url with path.
	 * 
	 * @param Path
	 *            to append
	 * @return ServerUrl/Path
	 */
	public static String getUrlForPath(String path) {
		StringBuilder url = new StringBuilder();
		// url.append(mServerUrl);
		url.append(path);

		return url.toString();
	}

	public static RetryPolicy getRetryPolicy() {
		return new StcpRetryPolicy();
	}

	/**
	 * Creates a GET request with final url and default headers.
	 * 
	 * @param path
	 *            The path to build the final url
	 * @param listener
	 *            The OK response listener that receives a JSONObject
	 * @param errorListener
	 *            The error listener that receives the VolleyError
	 * @return Returns a StcpJsonObjectRequest
	 */
	public static StcpJsonObjectRequest createJsonObjectRequest(int method,
			String path, FragmentActivity activity,
			Listener<JsonObject> listener, ErrorListener errorListener) {

		String url = getUrlForPath(path);

		StcpJsonObjectRequest request = new StcpJsonObjectRequest(method, url,
				null, listener, errorListener);

		request.setRetryPolicy(getRetryPolicy());

		Log.w(TAG, "request url:" + request.getUrl());

		return request;
	}

	/**
	 * Cancels all pending requests by the specified TAG, it is important to
	 * specify a TAG so that the pending/ongoing requests can be cancelled.
	 * 
	 * @param tag
	 */

	public static void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	/**
	 * Adds the specified request to the global queue, if tag is specified then
	 * it is used else Default TAG is used.
	 * 
	 * @param req
	 * @param tag
	 */
	public static <T> void addToRequestQueue(Request<T> request, String tag) {
		// Set the default tag if tag is empty
		request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(request);
	}

	/**
	 * Adds the specified request to the global queue using the Default TAG.
	 * 
	 * @param <T>
	 * 
	 * @param req
	 * @param tag
	 */
	public static <T> void addToRequestQueue(Request<T> request) {
		request.setTag(TAG);
		getRequestQueue().add(request);
	}

}
