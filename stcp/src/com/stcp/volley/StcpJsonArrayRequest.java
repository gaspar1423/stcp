package com.stcp.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class StcpJsonArrayRequest extends StcpJsonRequest<JsonArray> {

	public StcpJsonArrayRequest(String url, Listener<JsonArray> listener,
			ErrorListener errorListener) {
		super(Method.GET, url, null, listener, errorListener);
	}

	@Override
	protected Response<JsonArray> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(new JsonParser().parse(jsonString)
					.getAsJsonArray(), HttpHeaderParser
					.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonParseException je) {
			return Response.error(new ParseError(je));
		}
	}

}
