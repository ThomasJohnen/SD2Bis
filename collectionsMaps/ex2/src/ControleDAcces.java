import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ControleDAcces {

	HashSet<Employe> estPresent;
	HashMap<Badge, Employe> badgeEmployeHashMap;
	public ControleDAcces(){
		estPresent = new HashSet<Employe>();
		badgeEmployeHashMap = new HashMap<Badge, Employe>();
	}
	
	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e){
		badgeEmployeHashMap.put(b,e);
	}
	
	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b){
		estPresent.add(badgeEmployeHashMap.get(b));
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b){
		estPresent.remove(badgeEmployeHashMap.get(b));

	}
	
	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e){
	return estPresent.contains(e);
	}

}
