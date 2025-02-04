package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 600;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		// TODO
		// TODO - START
		double x;
		double fx;
		double y;
		double fy;
		
		for(int i = 0; i < gpspoints.length; i++) {
			x = MARGIN + ((gpspoints[i].getLongitude() - minlon) * xstep);
			y = ybase - ((gpspoints[i].getLatitude() - minlat) * ystep);
			if(i > 0) {
				fx = MARGIN + ((gpspoints[i - 1].getLongitude() - minlon) * xstep);
				fy = ybase - ((gpspoints[i - 1].getLatitude() - minlat) * ystep);
				drawLine((int) fx, (int) fy, (int) x, (int) y);
			}
			fillCircle((int) x, (int) y, 2);
		}
		// TODO SLUTT
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO
		// TODO - START
		drawString(String.format("%-23s %s %-15s %s", "Total time", ":", GPSUtils.formatTime(gpscomputer.totalTime()), ""), TEXTDISTANCE, TEXTDISTANCE);
		drawString(String.format("%-20s %-3s %-15.2f %s", "Total distance", ":", gpscomputer.totalDistance() / 1000, "km"), TEXTDISTANCE, TEXTDISTANCE * 2);
		drawString(String.format("%-20s %-4s %-14.2f %s", "Total elevation", ":", gpscomputer.totalElevation(), "m"), TEXTDISTANCE, TEXTDISTANCE * 3);
		drawString(String.format("%-20s %-4s %-15.2f %s", "Max speed", ":", gpscomputer.maxSpeed() * 3.6, "km/t"), TEXTDISTANCE, TEXTDISTANCE * 4);
		drawString(String.format("%-17s %-4s %-15.2f %s", "Average speed", ":", gpscomputer.averageSpeed() * 3.6, "km/t"), TEXTDISTANCE, TEXTDISTANCE * 5);
		drawString(String.format("%-23s %-4s %-14.2f %s", "Energy", ":", gpscomputer.totalKcal(80.0), "kcal"), TEXTDISTANCE, TEXTDISTANCE * 6);
		// TODO SLUTT
		
	}

	public void replayRoute(int ybase) {

		// TODO 
		// TODO - START
		double x = MARGIN + ((gpspoints[0].getLongitude() - minlon) * xstep);
		double y = ybase - ((gpspoints[0].getLatitude() - minlat) * ystep);
		setSpeed(5);
		setColor(0, 0, 255);
		int tracker = fillCircle((int) x, (int) y, 5);
		for(int i = 1; i < gpspoints.length; i++) {
			x = MARGIN + ((gpspoints[i].getLongitude() - minlon) * xstep);
			y = ybase - ((gpspoints[i].getLatitude() - minlat) * ystep);
			moveCircle(tracker, (int) x, (int) y);
		}
		// TODO SLUTT
		
	}

}
