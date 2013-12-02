package com.android.stcp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.stcp.R;
import com.android.stcp.fragments.CustomMapFragment.OnMapReadyListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public abstract class AbstractMapFragment extends Fragment implements
		OnMapReadyListener {

	public static final String TAG = AbstractMapFragment.class.getSimpleName();
	protected LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();
	protected LatLng coord = null;
	protected CustomMapFragment mapFragment = null;
	protected GoogleMap googleMap = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_map, container, false);
		return view;
	}

	@Override
	public void onDetach() {
		removeMap();
		super.onDetach();
	}

	@Override
	public void onDestroy() {
		removeMap();
		super.onDestroy();
	}

	private void removeMap() {
		try {
			CustomMapFragment fragment = ((CustomMapFragment) getFragmentManager()
					.findFragmentByTag("TAG_MAP"));
			if (fragment != null) {
				FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();

				ft.remove(fragment);
				ft.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public CustomMapFragment getMapFragment() {
		return mapFragment;
	}

	public GoogleMap getGoogleMap() {
		return googleMap;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		removeMap();

		mapFragment = CustomMapFragment.newInstance();

		getChildFragmentManager().beginTransaction()
				.replace(R.id.framemap, mapFragment, "TAG_MAP").commit();

	}

	public void onDestroyView() {
		removeMap();
		super.onDestroyView();
	}

	@Override
	public void onMapReady() {

		try {
			if (mapFragment != null) {
				googleMap = mapFragment.getMap();
			} else {
				mapFragment = CustomMapFragment.newInstance();
				googleMap = mapFragment.getMap();
			}

			googleMap.setMyLocationEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setZoomControlsEnabled(false);

		} catch (Exception ex) {
			ex.printStackTrace();

			Toast.makeText(getActivity(), "Nao foi possivel carregar o mapa!",
					Toast.LENGTH_LONG).show();

		}

	}

}
