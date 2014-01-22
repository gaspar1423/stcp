package com.android.stcp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.stcp.R;
import com.android.stcp.StcpApp;
import com.android.stcp.adapters.LinhasAdapter;
import com.android.stcp.managers.LinhasManager;
import com.android.stcp.modelobjects.Linha;
import com.stcp.volley.RequestManager;

public class LinhasFragment extends Fragment implements OnItemClickListener {

	protected static final String TAG = LinhasFragment.class.getSimpleName();

	private ListView listView;
	private View spinner;
	private View noData;
	public List<Linha> listLinhas = new ArrayList<Linha>();
	private LinhasAdapter mAdapter;
	private String linha_id = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.linhas_listview, container, false);

		return view;
	}

	protected ListView getListView() {
		return listView;
	}

	protected void waitData() {
		noData.setVisibility(View.GONE);
		spinner.setVisibility(View.VISIBLE);
		listView.setVisibility(View.GONE);
	}

	protected void showData(boolean haveData) {

		if (haveData) {
			noData.setVisibility(View.GONE);
			spinner.setVisibility(View.GONE);
			listView.setVisibility(View.VISIBLE);
		} else {
			noData.setVisibility(View.VISIBLE);
			spinner.setVisibility(View.GONE);
			listView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		listView = (ListView) getView().findViewById(R.id.list);

		listView.setOnItemClickListener(this);
		spinner = getView().findViewById(R.id.spinnerContent);
		noData = getView().findViewById(R.id.nodataContent);

		getListView().setBackgroundResource(android.R.color.white);

		listLinhas = LinhasManager.getInstance().getListaLinhas();

		if (listLinhas != null && listLinhas.size() > 0) {
			povoateList(listLinhas);
		}

	}

	@Override
	public void onResume() {
		super.onResume();

		listLinhas = LinhasManager.getInstance().getListaLinhas();

		if (listLinhas != null && listLinhas.size() > 0) {
			povoateList(listLinhas);
		} else {
			LinhasManager.getInstance().doRequestLinhas(getActivity());
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		RequestManager.cancelPendingRequests(TAG);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View v,
			final int position, long id) {
		linha_id = mAdapter.getItem(position).getNr_linha();

		Bundle bundle = new Bundle();
		bundle.putString("linha_id", linha_id);

		Fragment fragment = null;
		FragmentManager fragmentManager = getFragmentManager();
		fragment = new LinhasMapFragment();
		fragment.setArguments(bundle);
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

	}

	protected void setListAdapter(BaseAdapter mAdapter) {
		if (listView != null) {
			listView.setAdapter(mAdapter);
		}
	}

	protected void povoateList(List<Linha> lista) {
		if (lista != null && lista.size() > 0) {
			LinhasManager.getInstance().setListaLinhas(lista);
			mAdapter = new LinhasAdapter(StcpApp.getAppContext(), lista);
			setListAdapter(mAdapter);
			showData(true);
		} else {
			showData(false);
		}
	}
}
