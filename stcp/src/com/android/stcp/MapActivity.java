package com.android.stcp;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends Activity {

	private GoogleMap googleMap;
	private Location myLocation;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		googleMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

}
