package com.android.stcp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.android.stcp.R;
import com.android.stcp.managers.LinhasManager;
import com.android.stcp.map.Routing;
import com.android.stcp.map.RoutingListener;
import com.android.stcp.modelobjects.Linha;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class LinhasMapFragment extends AbstractMapFragment implements
		RoutingListener {

	protected static final String TAG = LinhasMapFragment.class.getSimpleName();

	private String linha_id = "";
	private Linha linha = null;
	public List<LatLng> stops = new ArrayList<LatLng>();

	@Override
	public void onMapReady() {
		super.onMapReady();

		Bundle bundle = this.getArguments();
		if (bundle != null) {
			linha_id = bundle.getString("linha_id", "");
		}

		if (linha_id != null) {
			linha = LinhasManager.getInstance().getLinhaById(linha_id);
		} else {
			Log.e(TAG, "Unable to get Linha");
		}

		if (linha != null) {
			stops = linha.getParagensLocations();
		}

		if (stops != null) {
			for (int i = 0; i < stops.size() - 1; i++) {
				Routing routing = new Routing(Routing.TravelMode.DRIVING);
				routing.registerListener(this);
				routing.execute(stops.get(i), stops.get(i + 1));
			}
			insertMarkers(stops);

			for (int i = 0; i < stops.size(); i++) {
				latLngBoundsBuilder.include(stops.get(i));
			}

		}

		googleMap.setMyLocationEnabled(true);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		googleMap
				.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {

					@Override
					public void onMyLocationChange(Location currentLocation) {
						googleMap.animateCamera(CameraUpdateFactory
								.newLatLngBounds(latLngBoundsBuilder.build(),
										200));

					}
				});

	}

	public void insertMarkers(List<LatLng> stops) {
		MarkerOptions options = new MarkerOptions();

		for (int i = 0; i < stops.size(); i++) {
			options = new MarkerOptions();
			options.position(stops.get(i));
			options.icon(BitmapDescriptorFactory
					.fromResource(R.drawable.marker_paragem));
			googleMap.addMarker(options);
		}
	}

	@Override
	public void onRoutingFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRoutingStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRoutingSuccess(PolylineOptions mPolyOptions) {
		PolylineOptions polyoptions = new PolylineOptions();
		polyoptions.color(Color.BLUE);
		polyoptions.width(5);
		polyoptions.addAll(mPolyOptions.getPoints());
		googleMap.addPolyline(polyoptions);
	}
}
