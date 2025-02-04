package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.text.DecimalFormat;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO 
		// TODO - START
		min = da[0];
		
		for(double i : da) {
			if(i < min) {
				min = i;
			}
		}
		
		return min;
		//TODO SLUTT
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		// TODO
		// TODO - START
		double[] tabell = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			tabell[i] = gpspoints[i].getLatitude();
		}
		
		return tabell;
		// TODO SLUTT
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO
		// TODO - START
		double[] tabell = new double[gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			tabell[i] = gpspoints[i].getLongitude();
		}
		
		return tabell;
		// TODO SLUTT

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO
		// TODO - START
		latitude1 = toRadians(gpspoint1.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude2 = toRadians(gpspoint2.getLongitude()); 
		
		double a = compute_a(latitude1,latitude2,latitude2 - latitude1,longitude2 - longitude1);
		double c = compute_c(a);
		
		d = R * c;
		
		return d;
		//TODO SLUTT
		
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		
		// TODO
		// TODO - START
		double a = Math.pow(Math.sin(deltaphi / 2),2) + Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltadelta / 2),2);
		
		return a;
		//TODO SLUTT

	}

	private static double compute_c(double a) {
		
		// TODO
		// TODO - START
		double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		
		return c;
		// TODO SLUTT

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		// TODO
		// TODO - START
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = distance(gpspoint1,gpspoint2) / secs;
		
		return speed;
		// TODO SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		// TODO
		// TODO - START
		int h = secs / 3600;
		int m = (secs % 3600) / 60;
		int s = secs % 60;
		
		timestr = String.format("  %02d:%02d:%02d",h,m,s);
		
		return timestr;
		// TODO SLUTT
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		// TODO
		// TODO - START
		double temp = d * 100;
		temp = Math.round(temp);
		str = Double.toString(temp / 100);
		
		for(int i = str.length(); i < 10; i++) {
			str = " " + str;
		}
		
		return str;
		// TODO SLUTT
		
	}
}
