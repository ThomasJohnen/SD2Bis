import java.util.Comparator;

public class EtudiantComparator implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        return Double.compare(o2.getMoyenne(), o1.getMoyenne());
    }
}
