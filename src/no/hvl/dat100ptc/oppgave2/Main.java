package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class Main {

	
	public static void main(String[] args) {
		GPSPoint gpsPoint1 = new GPSPoint(4, 10.5, 12.5, 5);
		GPSPoint gpsPoint2 = new GPSPoint(5, 10.7, 11.5, 6);

		GPSData gpsData = new GPSData(2);

		gpsData.insertGPS(gpsPoint1);
		gpsData.insertGPS(gpsPoint2);

		gpsData.print();
	}
}
