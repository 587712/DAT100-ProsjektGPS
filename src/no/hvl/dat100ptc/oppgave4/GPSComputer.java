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

		// TODO
		// TODO - START
		for(int i = 1; i < gpspoints.length; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i-1]);
		}
		
		return distance;
		//TODO SLUTT

	}

	public double totalElevation() {
		
		double elevation = 0;
		
		// TODO
		// TODO - START
		for(int i = 1; i < gpspoints.length; i++) {
			if(gpspoints[i].getElevation() > gpspoints[i - 1].getElevation()) {
				elevation += gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
			}
		}
		
		return elevation;
		// TODO SLUTT
		
	}

	public int totalTime() {

		// TODO
		// TODO - START
		int tid;
		
		tid = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		
		return tid;
		// TODO SLUTT
		
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		// TODO
		// TODO - START
		for(int i = 1; i < gpspoints.length; i++) {
			speeds[i - 1] = GPSUtils.speed(gpspoints[i - 1], gpspoints[i]);
		}
		
		return speeds;
		// TODO SLUTT
		
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO 
		// TODO - START
		double[] speeds = speeds();
		maxspeed = speeds[0];
		
		for(int i = 1; i < speeds.length; i++) {
			if(speeds[i] > maxspeed) {
				maxspeed = speeds[i];
			}
		}
		
		return maxspeed;
		// TODO SLUTT
	
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// TODO - START
		average = totalDistance()/totalTime();
		
		return average;
		// TODO SLUTT
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		// TODO 
		// TODO - START
		if(speedmph > 20.0) {
			met = 16.0;
		}
		else if(speedmph >= 16.0) {
			met = 12.0;
		}
		else if(speedmph >= 14.0) {
			met = 10.0;
		}
		else if(speedmph >= 12.0) {
			met = 8.0;
		}
		else if(speedmph >= 10.0) {
			met = 6.0;
		}
		else {
			met = 4.0;
		}
		kcal = met * weight * secs / 3600;
		
		return kcal;
		// TODO SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO 
		// TODO - START
		for(int i = 1; i < gpspoints.length; i++) {
			totalkcal += kcal(weight,gpspoints[i].getTime() - gpspoints[i - 1].getTime(),GPSUtils.speed(gpspoints[i - 1],gpspoints[i]));
		}
		
		return totalkcal;
		//TODO SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		// TODO 
		// TODO - START
		String stats = "";
		stats += "==============================================\n";
		stats += String.format("%-15s %-3s %-10s %s\n", "Total time", ":", GPSUtils.formatTime(totalTime()), "");
		stats += String.format("%-15s %-5s %-10.2f %s\n", "Total distance", ":", totalDistance() / 1000, "km");
		stats += String.format("%-15s %-5s %-10.2f %s\n", "Total elevation", ":", totalElevation(), "m");
		stats += String.format("%-15s %-5s %-10.2f %s\n", "Max speed", ":", maxSpeed() * 3.6, "km/t");
		stats += String.format("%-15s %-5s %-10.2f %s\n", "Average speed", ":", averageSpeed() * 3.6, "km/t");
		stats += String.format("%-15s %-5s %-10.2f %s\n", "Energy", ":", totalKcal(WEIGHT), "kcal");
		stats += "==============================================";
		System.out.println(stats);
		// TODO SLUTT
		
	}
	
	// TODO
	// TODO - START
	/*
	public double[] climbs() {
		
		double[] climb = new double[gpspoints.length - 1];
		
		for(int i = 1; i < gpspoints.length; i++) {
			
			climb[i - 1] = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
			System.out.println(climb[i - 1]);
			
		}
		
		return climb;
		
	}
	// TODO SLUTT
	 */

}
