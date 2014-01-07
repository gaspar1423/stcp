package com.android.stcp.modelobjects;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Linha {

	private String nome;
	private String nr_linha;
	private String sentido;
	private boolean activa;
	private List<Paragem> listaParagens;

	public Linha(String nome, String nr_linha, String sentido, boolean activa,
			List<Paragem> listaParagens) {
		super();
		this.nome = nome;
		this.nr_linha = nr_linha;
		this.sentido = sentido;
		this.activa = activa;
		this.listaParagens = listaParagens;
	}

	public static Linha fromJSON(final JsonObject json) {
		String nome = "";
		String nr_linha = "";
		String sentido = "";
		boolean activa = false;

		if (json.get("nome") != null && json.get("nome") != JsonNull.INSTANCE) {
			nome = json.get("nome").getAsString();
		}

		if (json.get("nr_linha") != null
				&& json.get("nr_linha") != JsonNull.INSTANCE) {
			nr_linha = json.get("nr_linha").getAsString();
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

		return new Linha(nome, nr_linha, sentido, activa, lista);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNr_linha() {
		return nr_linha;
	}

	public void setNr_linha(String nr_linha) {
		this.nr_linha = nr_linha;
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

	public List<LatLng> getParagensLocations() {
		List<LatLng> result = new ArrayList<LatLng>();

		for (Paragem p : listaParagens) {
			result.add(p.getLatLngPoint());
		}
		return result;
	}
}
