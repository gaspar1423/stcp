package com.stcp.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public abstract class StcpJsonRequest<T> extends StcpRequest<T> {

	private static final String PROTOCOL_CHARSET = "utf-8";

	private static final String PROTOCOL_CONTENT_TYPE = String.format(
			"application/json; charset=%s", PROTOCOL_CHARSET);

	public StcpJsonRequest(int method, String url, String payload,
			Listener<T> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
		setPayload(payload);
	}

	private void setPayload(String payload) {

		if (payload != null) {
			try {
				byte[] payloadBytes = payload.getBytes(PROTOCOL_CHARSET);
				super.setCustomBody(payloadBytes, PROTOCOL_CONTENT_TYPE);
			} catch (UnsupportedEncodingException uee) {
				VolleyLog
						.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
								payload, PROTOCOL_CHARSET);
			}
		}
	}

	@Override
	abstract protected Response<T> parseNetworkResponse(NetworkResponse response);
}
