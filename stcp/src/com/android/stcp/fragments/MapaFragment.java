package com.android.stcp.fragments;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.android.stcp.map.Route;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends AbstractMapFragment {

	private Location myLocation;
	private boolean isFirstTimeZoom = true;

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

						if (isFirstTimeZoom) {
							LatLng latLng = new LatLng(
									myLocation.getLatitude(), myLocation
											.getLongitude());
							Route.getInstance().addPoint(latLng);

							CameraUpdate cameraUpdate = CameraUpdateFactory
									.newLatLngZoom(latLng, 15);

							googleMap.animateCamera(cameraUpdate);
							isFirstTimeZoom = false;
						}
					}
				});

		googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {

			@Override
			public void onMapLongClick(LatLng point) {
				final Geocoder geocoder = new Geocoder(getActivity(), Locale
						.getDefault());
				String locationAddress = "";

				try {
					List<Address> myList = geocoder.getFromLocation(
							point.latitude, point.longitude, 1);
					locationAddress = myList.get(0).getAddressLine(0)
							.toString();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				googleMap.addMarker(new MarkerOptions()
						.position(point)
						.title(locationAddress)
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
				Route.getInstance().addPoint(point);

			}
		});
	}
}
