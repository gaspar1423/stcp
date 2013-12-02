package com.android.stcp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

public class CustomMapFragment extends SupportMapFragment {

	public CustomMapFragment() {
		super();
	}

	public static CustomMapFragment newInstance() {
		CustomMapFragment fragment = new CustomMapFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater li, ViewGroup v, Bundle bundle) {
		View view = super.onCreateView(li, v, bundle);
		Fragment fragment = getParentFragment();
		if (fragment != null && fragment instanceof OnMapReadyListener) {
			((OnMapReadyListener) fragment).onMapReady();
		}
		return view;
	}

	/**
	 * Listener interface to tell when the map is ready
	 */
	public static interface OnMapReadyListener {

		void onMapReady();
	}
}
