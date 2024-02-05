import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Ordonnancement {
    public static final int NIVEAU_PRIORITE_MAX = 5;
    private HashMap<Integer, LinkedList<Tache>> tachesParPriorite;

    public Ordonnancement() {
        tachesParPriorite = new HashMap<Integer, LinkedList<Tache>>();
        for (int i = 1; i < 6; i++) {
            tachesParPriorite.put(i, new LinkedList<Tache>());
        }
    }

    public void ajouterTache(String descriptif, int priorite) {
        if (priorite > 5 || priorite < 1)
            throw new IllegalArgumentException("La priorité n/' est pas compris entre 1 et 5");

        if (descriptif == "" || descriptif == null)
            throw new IllegalArgumentException("Il faut un descriptif");

        Tache newTache = new Tache(descriptif, priorite);
        tachesParPriorite.get(priorite).add(newTache);
    }

    //renvoie la tache prioritaire
    //renvoie null si plus de tache presente
    public Tache attribuerTache() {
        for(int i = 5; i> 0; i--){
            if(!tachesParPriorite.get(i).isEmpty())
                return tachesParPriorite.get(i).removeFirst();
        }
        return null;
    }
}
