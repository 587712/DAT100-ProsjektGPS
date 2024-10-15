package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {
		double distance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}

		return distance;
	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 1; i < gpspoints.length; i++) {
			double diff = gpspoints[i].getElevation() - gpspoints[i-1].getElevation();
			elevation += Math.max(diff, 0);
		}

		return elevation;
	}

	public int totalTime() {
		
		int time = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			time += gpspoints[i+1].getTime() - gpspoints[i].getTime();
		}

		return time;
		// Kronologisk tid
		//return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
	}
		

	public double[] speeds() {
		double[] speeds = new double[gpspoints.length-1];

		for (int i = 1; i < gpspoints.length; i++) {
			speeds[i-1] = GPSUtils.speed(gpspoints[i], gpspoints[i-1]);
		}
		return speeds;
	}
	
	public double maxSpeed() {
		return GPSUtils.findMax(speeds()) * 3.6;
	}

	public double averageSpeed() {

		double totalTime = totalTime();
		double totalDistance = totalDistance();
		return (totalDistance / totalTime) * 3.6;
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;
		double met = 0;		
		double speedmph = speed * MS;
		double time = secs / 3600.0;

		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else if (speedmph < 20) {
			met = 12.0;
		} else met = 16.0;

		kcal = met * weight * time;

		return kcal;
	}
	

	public double totalKcal(double weight) {

		double totalKcal = 0;
	
		for (int i = 1; i < gpspoints.length; i++) {
			int secs = gpspoints[i].getTime() - gpspoints[i-1].getTime();
			double speed = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			totalKcal += kcal(weight, secs, speed);
		}
	
		return totalKcal;
	}
	
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.printf(
			"==============================================%n" +
			"Total time      : %.2f hours%n" +
			"Total distance  : %.2f km%n" +
			"Total elevation : %.2f m%n" +
			"Max speed       : %.2f km/h%n" +
			"Average speed   : %.2f km/h%n" +
			"Energy burned   : %.2f kcal%n" +
			"==============================================%n", 
			totalTime() / 3600.0, 
			totalDistance() / 1000.0, 
			totalElevation(), 
			maxSpeed(), 
			averageSpeed(), 
			totalKcal(WEIGHT)
		);
	}
}
