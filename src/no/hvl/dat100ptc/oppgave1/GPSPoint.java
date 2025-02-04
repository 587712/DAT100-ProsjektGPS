package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	// TODO - objektvariable
	// TODO - START
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	// TODO SLUTT
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktur
		// TODO - START
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		// TODO SLUTT
		
	}

	// TODO - get/set metoder
	public int getTime() {
		
		// TODO - START
		return time;
		// TODO SLUTT
		
	}

	public void setTime(int time) {
				
		// TODO - START
		this.time = time;
		// TODO SLUTT
		
	}

	public double getLatitude() {
		
		// TODO - START
		return latitude;
		// TODO SLUTT
		
	}

	public void setLatitude(double latitude) {
		
		// TODO - START
		this.latitude = latitude;
		// TODO SLUTT
		
	}

	public double getLongitude() {
		
		// TODO - START
		return longitude;
		// TODO SLUTT
		
	}

	public void setLongitude(double longitude) {
		
		// TODO - START
		this.longitude = longitude;
		// TODO SLUTT
		
	}

	public double getElevation() {
		
		// TODO - START
		return elevation;
		// TODO SLUTT
		
	}

	public void setElevation(double elevation) {
		
		// TODO - START
		this.elevation = elevation;
		// TODO SLUTT
		
	}
	
	public String toString() {
		
		String str;

		// TODO
		// TODO - START
		str = time + " (" + latitude + "," + longitude + ") " + elevation + "\n";
		return str;
		// TODO SLUTT
		
	}
}
