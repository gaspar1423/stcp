package com.stcp.volley;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public abstract class StcpRequest<T> extends Request<T> {

	private Map<String, String> mHeaders = new HashMap<String, String>();
	private Map<String, String> mParams = new HashMap<String, String>();
	private byte[] mCustomBody = null;
	private String mCustomBodyContentType = null;
	private Priority priority;

	private final Listener<T> mListener;

	public StcpRequest(int method, String url, Listener<T> listener,
			ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener = listener;
		this.priority = Priority.NORMAL;
	}

	/*
	 * Priority
	 */
	@Override
	public Priority getPriority() {
		return this.priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/*
	 * Headers
	 */
	public void addHeader(String key, String value) {
		this.mHeaders.put(key, value);
	}

	public void addHeaders(Map<String, String> headers) {
		this.mHeaders.putAll(headers);
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return this.mHeaders;
	}

	/*
	 * Post Params
	 */
	public void addParam(String key, String value) {
		this.mParams.put(key, value);
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return this.mParams;
	}

	/*
	 * Custom Post Body
	 */
	public void setCustomBody(byte[] customBody, String contentType) {
		this.mCustomBody = customBody;
		this.mCustomBodyContentType = contentType;
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		return this.mCustomBody != null ? this.mCustomBody : super.getBody();
	}

	@Override
	public String getBodyContentType() {
		return this.mCustomBodyContentType != null ? this.mCustomBodyContentType
				: super.getBodyContentType();
	}

	@Override
	protected void deliverResponse(T response) {
		if (this.mListener != null) {
			this.mListener.onResponse(response);
		}
	}

	/**
	 * Subclasses must implement this to parse the raw network response and
	 * return an appropriate response type. This method will be called from a
	 * worker thread. The response will not be delivered if you return null.
	 * 
	 * @param response
	 *            Response from the network
	 * @return The parsed response, or null in the case of an error
	 */
	@Override
	abstract protected Response<T> parseNetworkResponse(NetworkResponse response);
}
