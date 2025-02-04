package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		
		// TODO
		// TODO - START
		gpspoints = new GPSPoint[antall];
		antall = 0;
		// TODO SLUTT
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		
		boolean inserted = false;
		
		// TODO 
		// TODO - START
		if(antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		else {
			inserted = false;
		}
		return inserted;
		// TODO SLUTT
	
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO
		// TODO - START
		GPSPoint point = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		gpspoint = new GPSPoint(point.getTime(),point.getLatitude(),point.getLongitude(),point.getElevation());
		
		return insertGPS(gpspoint);
		//TODO SLUTT
		
	}

	public void print() {
		
		// TODO 
		// TODO - START
		System.out.println("====== GPS Data - START");
		for(int i = 0; i < gpspoints.length; i++) {
			System.out.println(gpspoints[i].toString());
		}
		System.out.println("====== GPS Data - SLUTT");
		// TODO SLUTT
		
	}
}
