import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {

	HashMap<Integer, Deque<Integer>> pointures;
	HashMap<Integer, LocalTime> casiersOccupes;
	int[] casiers;

	
	public LocationPatins(int[] casiers) {
		pointures = new HashMap<Integer, Deque<Integer>>();
		casiersOccupes = new HashMap<Integer, LocalTime>();
		this.casiers = casiers;
		for(int i= 0; i< casiers.length; i++){
			if(casiers[i]<33 || casiers[i] > 48)
				throw new IllegalArgumentException("La pointure n'est pas envisageable");
			if (pointures.containsKey(casiers[i]))
				pointures.get(casiers[i]).add(i);
			else {
				pointures.put(casiers[i], new ArrayDeque<Integer>());
				pointures.get(casiers[i]).add(i);
			}
		}
	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();
		int casierAttribuer = pointures.get(pointure).removeFirst();
		if(casierAttribuer == -1) return casierAttribuer;
		casiersOccupes.put(casierAttribuer, l);
		return casierAttribuer;

	}

	public double libererCasier(int numeroCasier) {
		LocalTime l = casiersOccupes.remove(numeroCasier);
		double prix = prix(LocalTime.now(), l);
		pointures.get(casiers[numeroCasier]).add(numeroCasier);

		return prix;
	}

}
