import java.util.*;

public class ChoixOptions {
	
	// associe le nom d'une option avec son objet Option correspondant
	private Map<String, Option> options;
	private Map<Etudiant, Deque<String>> preferencesEtudiants;
	private ArrayList<Etudiant> etudiants;
	
	//constructeur prenant un entier et une suite de string en param�tres
	//ces string repr�sentent les noms des diff�rentes options possibles
	public ChoixOptions(int nbEtudiantsParOption, String... nomsOption) {
		this.preferencesEtudiants = new HashMap<Etudiant, Deque<String>>();
		this.options = new HashMap<String, Option>();
		if (nomsOption.length < 3)
			throw new IllegalArgumentException();
		for (int i = 0; i < nomsOption.length; i++) {
			String nomOption = nomsOption[i];
			options.put(nomOption, new Option(nomOption, nbEtudiantsParOption));
		}
		this.etudiants = new ArrayList<Etudiant>();
	}



	// cette m�thode encode les pr�f�rences des �tudiants
	// il ne faut pas v�rifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2,String choix3) {
		Deque<String> preferences = new ArrayDeque<>();
		preferences.add(choix1);
		preferences.add(choix2);
		preferences.add(choix3);
		preferencesEtudiants.put(etu, preferences);
		etudiants.add(etu);
	}

	// cette m�thode est appel�e apr�s que les �tudiants aient donn� leurs pr�f�rences
	// cette m�thode attribue les options aux �tudiants en favorisant les �tudiants 
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines 
	// options. Pour les �tudiants faibles, si les deux premi�res options sont pleines, 
	// il faut recourir au troisi�me choix.
	// Cette m�thode doit faire appel � la m�thode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
		Collections.sort(etudiants); // pour utiliser compareTo
		// Collections.sort(etudiants, new EtudiantComparator());
		System.out.println(etudiants);
		for (Etudiant etu : etudiants) {
			String premierChoix = preferencesEtudiants.get(etu).removeFirst();
			String deuxiemeChoix = preferencesEtudiants.get(etu).removeFirst();
			String troisiemeChoix = preferencesEtudiants.get(etu).removeFirst();
			if(!options.get(premierChoix).inscrireEtudiant(etu))
				if(!options.get(deuxiemeChoix).inscrireEtudiant(etu))
					options.get(troisiemeChoix).inscrireEtudiant(etu);
		}
	}
	
	public String toString(){
		String s="";
		for (Option o:options.values()){
			s=s+o+"\n"+"-----------------"+"\n";
		}
		return s;
	}
}
