package com.android.stcp.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.stcp.R;
import com.android.stcp.modelobjects.Linha;

public class LinhasAdapter extends BaseAdapter {

	private Context context;
	List<Linha> linhas;

	public LinhasAdapter(Context context, List<Linha> linhas) {
		super();
		this.context = context;
		this.linhas = linhas;
	}

	@Override
	public int getCount() {
		return this.linhas.size();
	}

	@Override
	public Linha getItem(int position) {
		return this.linhas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Linha field = this.getItem(position);

		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.linha_field, null);
		}

		ImageView icon = (ImageView) convertView.findViewById(R.id.field_icon);
		TextView num_linha = (TextView) convertView
				.findViewById(R.id.num_linha);
		TextView linha_nome = (TextView) convertView
				.findViewById(R.id.linha_nome);

		icon.setImageResource(R.drawable.box_green);
		num_linha.setText(field.getNr_linha());
		linha_nome.setText(field.getNome());

		return convertView;
	}

}
