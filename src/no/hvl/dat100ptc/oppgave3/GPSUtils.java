package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import static java.lang.String.format;
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

		double min = da[0];
		for (double d : da) {
			min = min(d, min);
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		int len = gpspoints.length;
		double[] latitudes = new double[len];

		for (int i = 0; i < len; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		int len = gpspoints.length;
		double[] longitudes = new double[len];

		for (int i = 0; i < len; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
	}

	private static final int R = 6371000; // jordens radius i meter

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
	
		double lat1 = toRadians(gpspoint1.getLatitude());
		double lat2 = toRadians(gpspoint2.getLatitude());
		double long1 = toRadians(gpspoint1.getLongitude());
		double long2 = toRadians(gpspoint2.getLongitude());
	
		double dLat = lat2 - lat1;
		double dLong = long2 - long1;
	
		double a = compute_a(lat1, lat2, dLat, dLong);
		double c = compute_c(a);
	
		double distance = R * c;
	
		return distance;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		return pow(sin(deltaphi / 2), 2) + cos(phi1) * cos(phi2) * pow(sin(deltadelta / 2), 2);
	}
	
	private static double compute_c(double a) {
		return 2 * atan2(sqrt(a), sqrt(1 - a));
	}
	

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double distance = distance(gpspoint1, gpspoint2);

		int t1 = gpspoint1.getTime();
		int t2 = gpspoint2.getTime();

		double avgSpeed = distance / abs(t1 - t2);

		return avgSpeed;
	}
	private static int STRINGWIDTH = 10;

	public static String formatTime(int secs) {

		int hrs = secs / 3600;
		secs %= 3600;

		int min = secs / 60;
		secs %= 60;

		String timeFormatted = format("%02d:%02d:%02d", hrs, min, secs);
		return format("%" + STRINGWIDTH + "s" , timeFormatted);
	}
	
	public static String formatDouble(double d) {
		return format("%" + STRINGWIDTH + ".2f", d) ;
	}
}
