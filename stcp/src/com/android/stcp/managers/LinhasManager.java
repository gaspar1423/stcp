package com.android.stcp.managers;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.stcp.R;
import com.android.stcp.StcpApp;
import com.android.stcp.modelobjects.Linha;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stcp.volley.RequestManager;
import com.stcp.volley.StcpJsonObjectRequest;

public class LinhasManager {

	public static final String TAG = LinhasManager.class.getSimpleName();

	public List<Linha> listaLinhas;

	private static final class SingletonHolder {
		public static final LinhasManager instance = new LinhasManager();
	}

	public static LinhasManager getInstance() {
		return SingletonHolder.instance;
	}

	private LinhasManager() {
		this.listaLinhas = new ArrayList<Linha>();
	}

	public List<Linha> getListaLinhas() {
		return listaLinhas;
	}

	public void addLinha(Linha linha) {
		this.listaLinhas.add(linha);
	}

	public void clearLinhas() {
		this.listaLinhas.clear();
	}

	public void setListaLinhas(List<Linha> listaLinhas) {
		this.listaLinhas = listaLinhas;
	}

	public Linha getLinha(int position) {
		return this.listaLinhas.get(position);
	}

	public Linha getLinhaById(String linhaId) {

		Linha linha = null;

		for (Linha l : listaLinhas) {
			if (l.getNr_linha().equals(linhaId)) {
				linha = l;
				break;
			}
		}

		return linha;
	}

	public void doRequestLinhas(FragmentActivity activity) {

		String path = StcpApp.getAppContext().getString(R.string.linhas_path);

		StcpJsonObjectRequest request = RequestManager.createJsonObjectRequest(
				Method.POST, path, activity, new Listener<JsonObject>() {

					@Override
					public void onResponse(JsonObject response) {
						parseJsonResponse(response);
						Log.i(TAG, "[LinhasManager] onResponse. JsonObject: "
								+ response);
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e(TAG, "[LinhasManager] volley error Request:"
								+ error.getClass().getName(), null);
					}

				});

		RequestManager.addToRequestQueue(request);

	}

	public void parseJsonResponse(JsonObject response) {

		this.listaLinhas.clear();

		if (response != null && response.has("linhas")) {
			Log.i(TAG, "[LinhasManager] as reponse");
			try {
				JsonArray jArray = response.get("linhas").getAsJsonArray();

				if (jArray != null) {
					Log.i(TAG, "[LinhasManager] as linhas to add");
					for (int i = 0; i < jArray.size(); i++) {
						JsonObject linhaJsonObject = jArray.get(i)
								.getAsJsonObject();

						Linha linha = Linha.fromJSON(linhaJsonObject);
						listaLinhas.add(linha);
						Log.i(TAG, "[LinhasManager] linha added");
					}
					this.setListaLinhas(listaLinhas);
				}
			} catch (Exception e) {
				Log.e("Parse delivery exception" + e, null);
			}
		}
	}
}
