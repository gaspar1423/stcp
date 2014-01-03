package com.android.stcp.fragments;

import java.util.List;

import com.android.stcp.R;
import com.android.stcp.map.Route;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LinhasFragment extends AbstractMapFragment {

	public List<LatLng> stops = Route.getInstance().getListPoints();

	@Override
	public void onMapReady() {
		super.onMapReady();

		insertMarkers(stops);

		for (int i = 0; i < stops.size(); i++) {
			latLngBoundsBuilder.include(stops.get(i));
		}

		googleMap.setMyLocationEnabled(true);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);
	}

	public void insertMarkers(List<LatLng> stops) {
		MarkerOptions options = new MarkerOptions();

		for (int i = 1; i < stops.size() - 1; i++) {
			options = new MarkerOptions();
			options.position(stops.get(i));
			options.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.marker_paragem));
			googleMap.addMarker(options);
		}
	}

	private void getLinhas() {
		String path = getString(R.string.linhas_path);

	}
}
