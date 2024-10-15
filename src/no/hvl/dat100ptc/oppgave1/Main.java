package no.hvl.dat100ptc.oppgave1;

// opprettter et GPSPoint-objekt med tid 1, breddegrad 2.0, lengdegrad 3.0 og høyde 5.0.

// Bruk getTime-metoden for å skrive tidspunkt ut på skjermen og setTime-metoden for å endre tidspunkt til 2. Bruk toString-metoden for å skrive informasjon om objektet ut på skjermen.

public class Main {

	public static void main(String[] args) {
		
		GPSPoint gpsPoint1 = new GPSPoint(1, 2.0, 3.0, 5.0);
		System.out.println(gpsPoint1.getTime());
		gpsPoint1.setTime(5);
		System.out.println(gpsPoint1.toString());
	}

}
