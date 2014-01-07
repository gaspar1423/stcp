package com.android.stcp.managers;

import java.util.ArrayList;
import java.util.List;

import com.android.stcp.modelobjects.Paragem;

public class ParagensManager {
	public List<Paragem> listaParagens;

	private static final class SingletonHolder {
		public static final ParagensManager instance = new ParagensManager();
	}

	public static ParagensManager getInstance() {
		return SingletonHolder.instance;
	}

	private ParagensManager() {
		this.listaParagens = new ArrayList<Paragem>();
	}

	public List<Paragem> getListaParagens() {
		return listaParagens;
	}

	public void setListaLinhas(List<Paragem> listaParagens) {
		this.listaParagens = listaParagens;
	}

	public Paragem getParagem(int position) {
		return this.listaParagens.get(position);
	}

	public Paragem getParagemById(String paragemId) {

		Paragem paragem = null;

		for (Paragem l : listaParagens) {
			if (l.getParagem_id().equals(paragemId)) {
				paragem = l;
				break;
			}
		}

		return paragem;
	}
}
