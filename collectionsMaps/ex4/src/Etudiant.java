public class Etudiant implements Comparable<Etudiant> {
	private final String nom;
	private int moyenne;
	public Etudiant(String nom, int moyenne) {
		super();
		this.nom = nom;
		this.moyenne = moyenne;
	}
	public String getNom() {
		return nom;
	}
	public int getMoyenne() {
		return moyenne;
	}


	//  remove the comment and remove "implements Comparable<Etudiant>" from the class signature
	@Override
	public int compareTo(Etudiant o) {
		return Double.compare(o.moyenne, this.moyenne);
	}


	@Override
	public String toString() {
		return "Etudiant{" +
				"nom='" + nom + '\'' +
				", moyenne=" + moyenne +
				'}';
	}
}
