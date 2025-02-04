package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		// TODO
		// TODO - START
		hr = Integer.parseInt(timestr.substring(11,13));
		min = Integer.parseInt(timestr.substring(14,16));
		sec = Integer.parseInt(timestr.substring(17,19));
		
		secs = sec + (min + (hr * 60)) * 60;
		
		return secs;
		//TODO SLUTT
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		// TODO
		// TODO - START
		gpspoint = new GPSPoint(toSeconds(timeStr),Double.parseDouble(latitudeStr),Double.parseDouble(longitudeStr),Double.parseDouble(elevationStr));
		
		return gpspoint;
		// TODO SLUTT
		
	}
	
}
