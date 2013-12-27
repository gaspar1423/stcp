package com.android.stcp.modelobjects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Paragem {

	private int paragem_id;
	private String nome;
	private String zona;
	private boolean status;
	private float latitude;
	private float longitude;
	private List<Linha> listaLinhas;

	public Paragem(int paragem_id, String nome, String zona, boolean status,
			float latitude, float longitude, List<Linha> listaLinhas) {
		super();
		this.paragem_id = paragem_id;
		this.nome = nome;
		this.zona = zona;
		this.status = status;
		this.latitude = latitude;
		this.longitude = longitude;
		this.listaLinhas = listaLinhas;
	}

	public static Paragem fromJSON(final JsonObject json) {
		int paragem_id = 0;
		String nome = "";
		String zona = "";
		boolean status = false;
		float latitude = 0;
		float longitude = 0;

		if (json.get("paragem_id") != null
				&& json.get("paragem_id") != JsonNull.INSTANCE) {
			paragem_id = json.get("paragem_id").getAsInt();
		}

		if (json.get("nome") != null && json.get("nome") != JsonNull.INSTANCE) {
			nome = json.get("nome").getAsString();
		}

		if (json.get("zona") != null && json.get("zona") != JsonNull.INSTANCE) {
			zona = json.get("zona").getAsString();
		}

		if (json.get("status") != null
				&& json.get("status") != JsonNull.INSTANCE) {
			status = json.get("status").getAsBoolean();
		}

		if (json.get("latitude") != null
				&& json.get("latitude") != JsonNull.INSTANCE) {
			latitude = json.get("latitude").getAsFloat();
		}

		if (json.get("longitude") != null
				&& json.get("longitude") != JsonNull.INSTANCE) {
			longitude = json.get("longitude").getAsFloat();
		}

		List<Linha> lista = new ArrayList<Linha>();
		JsonArray jarray = json.getAsJsonArray("linhas");
		if (jarray != null) {
			for (int i = 0; i < jarray.size(); i++) {
				Linha linha = Linha.fromJSON(jarray.get(i).getAsJsonObject());

				lista.add(linha);
			}
		}

		return new Paragem(paragem_id, nome, zona, status, latitude, longitude,
				lista);
	}

	public int getParagem_id() {
		return paragem_id;
	}

	public void setParagem_id(int paragem_id) {
		this.paragem_id = paragem_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public List<Linha> getListaLinhas() {
		return listaLinhas;
	}

	public void setListaLinhas(List<Linha> listaLinhas) {
		this.listaLinhas = listaLinhas;
	}

}
