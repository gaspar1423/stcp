package com.android.stcp.modelobjects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Linha {

	private String nr_linha;
	private String nr_zona;
	private String sentido;
	private boolean activa;
	private List<Paragem> listaParagens;

	public Linha(String nr_linha, String nr_zona, String sentido,
			boolean activa, List<Paragem> listaParagens) {
		super();
		this.nr_linha = nr_linha;
		this.nr_zona = nr_zona;
		this.sentido = sentido;
		this.activa = activa;
		this.listaParagens = listaParagens;
	}

	public static Linha fromJSON(final JsonObject json) {
		String nr_linha = "";
		String nr_zona = "";
		String sentido = "";
		boolean activa = false;

		if (json.get("nr_linha") != null
				&& json.get("nr_linha") != JsonNull.INSTANCE) {
			nr_linha = json.get("nr_linha").getAsString();
		}

		if (json.get("nr_zona") != null
				&& json.get("nr_zona") != JsonNull.INSTANCE) {
			nr_zona = json.get("nr_zona").getAsString();
		}

		if (json.get("sentido") != null
				&& json.get("sentido") != JsonNull.INSTANCE) {
			sentido = json.get("sentido").getAsString();
		}

		if (json.get("activa") != null
				&& json.get("activa") != JsonNull.INSTANCE) {
			activa = json.get("activa").getAsBoolean();
		}

		List<Paragem> lista = new ArrayList<Paragem>();
		JsonArray jarray = json.getAsJsonArray("paragens");
		if (jarray != null) {
			for (int i = 0; i < jarray.size(); i++) {
				Paragem paragem = Paragem.fromJSON(jarray.get(i)
						.getAsJsonObject());

				lista.add(paragem);
			}
		}

		return new Linha(nr_linha, nr_zona, sentido, activa, lista);
	}

	public String getNr_linha() {
		return nr_linha;
	}

	public void setNr_linha(String nr_linha) {
		this.nr_linha = nr_linha;
	}

	public String getNr_zona() {
		return nr_zona;
	}

	public void setNr_sentido(String nr_zona) {
		this.nr_zona = nr_zona;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public List<Paragem> getListaParagens() {
		return listaParagens;
	}

	public void setListaParagens(List<Paragem> listaParagens) {
		this.listaParagens = listaParagens;
	}
}
