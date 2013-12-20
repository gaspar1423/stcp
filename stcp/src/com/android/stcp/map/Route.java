package com.android.stcp.map;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

public class Route {
	private String name;
	private final List<LatLng> points;
	private List<Segment> segments;
	private String copyright;
	private String warning;
	private String country;
	private int length;
	private String polyline;

	private static final class SingletonHolder {
		public static final Route instance = new Route();
	}

	public static Route getInstance() {
		return SingletonHolder.instance;
	}

	public Route() {
		points = new ArrayList<LatLng>();
		segments = new ArrayList<Segment>();
	}

	public List<LatLng> getListPoints() {
		return points;
	}

	public void addPoint(final LatLng p) {
		points.add(p);
	}

	public void clearPoints() {
		points.clear();
	}

	public void addPoints(final List<LatLng> points) {
		this.points.addAll(points);
	}

	public List<LatLng> getPoints() {
		return points;
	}

	public void addSegment(final Segment s) {
		segments.add(s);
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getWarning() {
		return warning;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

	public String getPolyline() {
		return polyline;
	}

}