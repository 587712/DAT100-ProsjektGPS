package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	public static void main(String[] args) {

		// TODO
		// TODO - START
		GPSPoint gps1 = new GPSPoint(3,4.0,4.0,15);
		GPSPoint gps2 = new GPSPoint(1,3.4,5.5,2);
		GPSData samling1 = new GPSData(2);
		samling1.insertGPS(gps1);
		samling1.insertGPS(gps2);
		samling1.print();
		// TODO SLUTT
		
	}
}
