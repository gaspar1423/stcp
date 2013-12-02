package com.android.stcp.fragments;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.LatLng;

public class MapaFragment extends AbstractMapFragment {

	private Location myLocation;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onMapReady() {
		super.onMapReady();

		googleMap.setMyLocationEnabled(true);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		googleMap
				.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {

					@Override
					public void onMyLocationChange(Location currentLocation) {

						if (currentLocation != null) {
							myLocation = currentLocation;
						}

						LatLng latLng = new LatLng(myLocation.getLatitude(),
								myLocation.getLongitude());

						CameraUpdate cameraUpdate = CameraUpdateFactory
								.newLatLngZoom(latLng, 15);

						googleMap.animateCamera(cameraUpdate);
					}
				});
	}
}
