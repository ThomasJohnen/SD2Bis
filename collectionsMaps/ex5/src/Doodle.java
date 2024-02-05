import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Doodle {
	private PlageHoraire[] plages;
	private HashMap<String, ArrayList<Boolean>> disponibilites;

	public Doodle(PlageHoraire... plages) {
		this.plages = plages;
		disponibilites = new HashMap<String, ArrayList<Boolean>>();

	}

	// ajoute les disponibilit�s d'un participant sous forme d'un tableau de booleen.
	// les indices du tableau correspondent aux indices du tableau plages
	// true � l'indice i veut dire que le participant est disponible pour la plage � l'indice i du tableau plages
	// false � l'indice i veut dire que le participant n'est pas disponible pour la plage � l'indice i du tableau plages
	public void ajouterDisponibilites(String participant,
			boolean[] disponibilites) {
		if (disponibilites.length != plages.length)
			throw new IllegalArgumentException();
		ArrayList<Boolean> dispo = new ArrayList<Boolean>();
		for (boolean b : disponibilites)
			dispo.add(b);
		this.disponibilites.put(participant, dispo);

		// il faut maintenant ajouter les participants pr�vus pour chaque plage
		for (int i = 0; i < disponibilites.length; i++) {
			if (disponibilites[i]) {
				plages[i].setNbParticipantPresent(plages[i].getNbParticipantPresent() + 1);
			}
		}

	}
	
	// Hypoth�se: la PlageHoraire plage en param�tre fait bien partie du tableau plages
	// renvoie vrai si le participant est disponible pour cette plage horaire
	// renvoie faux si le participant n'est pas disponible ou s'il n'a pas rempli le
	// sondage doodle
	public boolean estDisponible(String participant, PlageHoraire plage) {
		ArrayList<Boolean> dispos = disponibilites.get(participant);
		int position = IntStream.range(0, plages.length).filter(i -> plages[i] == plage).findFirst().orElse(-1);
		if (position == -1)
			throw new IllegalArgumentException();
		return dispos.get(position);
    }

	// renvoie une des plages horaires qui a le maximum de participants pr�vus
	// cette m�thode est appel�e apr�s que les participants aient rempli leurs disponibilit�s
	public PlageHoraire trouverPlageQuiConvientLeMieux() {
		return Arrays.stream(plages).max((p1, p2) -> Integer.compare(p1.getNbParticipantPresent(), p2.getNbParticipantPresent())).get();
	}

}
