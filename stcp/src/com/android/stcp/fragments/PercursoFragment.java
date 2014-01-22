package com.android.stcp.fragments;

import java.util.List;

import android.graphics.Color;
import android.location.Location;

import com.android.stcp.R;
import com.android.stcp.map.Route;
import com.android.stcp.map.Routing;
import com.android.stcp.map.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class PercursoFragment extends AbstractMapFragment implements
		RoutingListener {

	private Location myLocation;
	public List<LatLng> stops = Route.getInstance().getListPoints();

	private boolean isFirstTimeZoom = true;

	@Override
	public void onMapReady() {
		super.onMapReady();
		String[] titleList;

		titleList = getResources().getStringArray(R.array.drawer_list_menu);
		getActivity().setTitle(titleList[1]);

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

						if (currentLocation != null) {
							myLocation = currentLocation;
						}

						if (isFirstTimeZoom) {
							LatLng latLng = new LatLng(
									myLocation.getLatitude(), myLocation
											.getLongitude());

							latLngBoundsBuilder.include(latLng);
							LatLngBounds llb = latLngBoundsBuilder.build();

							CameraUpdate cameraUpdate = CameraUpdateFactory
									.newLatLngBounds(llb, 100);

							googleMap.animateCamera(cameraUpdate);
							isFirstTimeZoom = false;
						}
					}
				});
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

}
