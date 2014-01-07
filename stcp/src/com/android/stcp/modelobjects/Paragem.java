package com.android.stcp.modelobjects;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

public class Paragem {

	private String paragem_id;
	private String nome;
	private boolean activa;
	private String zona;
	private float latitude;
	private float longitude;

	// private List<int> listaLinhas;

	public Paragem(String paragem_id, String nome, boolean activa, String zona,
			float latitude, float longitude) {
		super();
		this.paragem_id = paragem_id;
		this.nome = nome;
		this.activa = activa;
		this.zona = zona;
		this.latitude = latitude;
		this.longitude = longitude;
		// this.listaLinhas = listaLinhas;
	}

	public static Paragem fromJSON(final JsonObject json) {
		String paragem_id = "";
		String nome = "";
		boolean activa = false;
		String zona = "";
		float latitude = 0;
		float longitude = 0;

		if (json.get("paragem_id") != null
				&& json.get("paragem_id") != JsonNull.INSTANCE) {
			paragem_id = json.get("paragem_id").getAsString();
		}

		if (json.get("nome") != null && json.get("nome") != JsonNull.INSTANCE) {
			nome = json.get("nome").getAsString();
		}

		if (json.get("activa") != null
				&& json.get("activa") != JsonNull.INSTANCE) {
			activa = json.get("activa").getAsBoolean();
		}

		if (json.get("zona") != null && json.get("zona") != JsonNull.INSTANCE) {
			zona = json.get("zona").getAsString();
		}

		if (json.get("latitude") != null
				&& json.get("latitude") != JsonNull.INSTANCE) {
			latitude = json.get("latitude").getAsFloat();
		}

		if (json.get("longitude") != null
				&& json.get("longitude") != JsonNull.INSTANCE) {
			longitude = json.get("longitude").getAsFloat();
		}

		// List<int> lista = new ArrayList<int>();
		// JsonArray jarray = json.getAsJsonArray("linhas");
		// if (jarray != null) {
		// for (int i = 0; i < jarray.size(); i++) {
		// int linha = jarray.get(i).getAsJsonObject();
		//
		// lista.add(linha);
		// }
		// }

		return new Paragem(paragem_id, nome, activa, zona, latitude, longitude);
	}

	public String getParagem_id() {
		return paragem_id;
	}

	public void setParagem_id(String paragem_id) {
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

	public boolean isActiva() {
		return activa;
	}

	public void set_activa(boolean activa) {
		this.activa = activa;
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

	public LatLng getLatLngPoint() {
		return new LatLng(latitude, longitude);
	}

	public Location getLocationObject() {
		Location location = new Location("");
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		return location;
	}
	//
	// public List<int> getListaLinhas() {
	// return listaLinhas;
	// }
	//
	// public void setListaLinhas(List<int> listaLinhas) {
	// this.listaLinhas = listaLinhas;
	// }

}
