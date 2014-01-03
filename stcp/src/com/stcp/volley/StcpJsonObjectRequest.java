package com.stcp.volley;

import java.io.UnsupportedEncodingException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class StcpJsonObjectRequest extends StcpJsonRequest<JsonObject> {

	public StcpJsonObjectRequest(int method, String url, JsonObject jsonRequest,
			Listener<JsonObject> listener, ErrorListener errorListener) {
		super(method, url, (jsonRequest == null) ? null : jsonRequest
				.toString(), listener, errorListener);
	}

	@Override
	protected Response<JsonObject> parseNetworkResponse(NetworkResponse response) {
		try {
			/*
			 * String jsonString = new String(response.data,
			 * HttpHeaderParser.parseCharset(response.headers));
			 */
			String jsonString = new String(response.data, "utf-8");

			JsonObject jsonObject = new JsonParser().parse(jsonString)
					.getAsJsonObject();

			return Response.success(jsonObject,
					HttpHeaderParser.parseCacheHeaders(response));

		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonParseException je) {
			return Response.error(new ParseError(je));
		}
	}

}
