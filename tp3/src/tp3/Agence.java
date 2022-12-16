package tp3;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class Agence {
	private List<Voiture> voitures;
	public Agence()
	{
		voitures= new ArrayList<>();
	}
	public Iterator<Voiture> selectionne (Critere c) {
		ArrayList<Voiture> tmp=new ArrayList<>();
		for (Voiture voiture : voitures) {
			if(c.estSatisfaitPar(voiture)) {
				tmp.add(voiture);
			}
		}
		return tmp.iterator();
	}
	public void afficheSelection(Critere c) {
		Iterator<Voiture> elements=selectionne(c);
		while(elements.hasNext())
		{
			System.out.println(elements.next());
		}
		
	}
	public void ajouterVoiture(Voiture v) {
		voitures.add(v);
	}
	
}
