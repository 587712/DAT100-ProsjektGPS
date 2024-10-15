package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

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
		int x;
		int y;
		int nextX;
		int nextY;

		setColor(0, 255, 0);
		for (int i = 1; i < gpspoints.length - 1; i++) {
			x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
			y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep); 

			nextX = MARGIN + (int) ((gpspoints[i + 1].getLongitude() - minlon) * xstep);
			nextY = ybase - (int) ((gpspoints[i + 1].getLatitude() - minlat) * ystep);  

			fillCircle(x, y, 4);
			drawLine(x, y, nextX, nextY);
		}

		x = MARGIN + (int) ((gpspoints[gpspoints.length-1].getLongitude() - minlon) * xstep);
		y = ybase - (int) ((gpspoints[gpspoints.length-1].getLatitude() - minlat) * ystep); 
		setColor(0, 255, 0);
		fillCircle(x, y, 5);

	}

	public void write(String text, int line)  {
		setColor(0,0,0);
		setFont("Courier",12);
		int TEXTDISTANCE = 20;
		drawString(text, TEXTDISTANCE, TEXTDISTANCE * line);
		pause(50);
	}


	public void showStatistics() {
		write(String.format("Total time      : %s", GPSUtils.formatTime(gpscomputer.totalTime())), 1);
		write(String.format("Total distance  :   %.2f km", gpscomputer.totalDistance() / 1000.0), 2);
		write(String.format("Total elevation :   %.2f m", gpscomputer.totalElevation()), 3);
		write(String.format("Max speed       :   %.2f km/h", gpscomputer.maxSpeed()), 4);
		write(String.format("Average speed   :   %.2f km/h", gpscomputer.averageSpeed()), 5);
		write(String.format("Energy          :   %.2f kcal", gpscomputer.totalKcal(80.0)), 6);
	}

	public void replayRoute(int ybase) {
		int x;
		int y;
		setSpeed(7); 
		x = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
		y = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep); 
		setColor(0, 0, 255); 
		int running = fillCircle(x, y, 6); 
	
		for (int i = 0; i < gpspoints.length; i++) {
			x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
			y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep); 
			moveCircle(running, x, y);
		}
	}

}

