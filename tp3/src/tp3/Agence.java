package tp3;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Agence {
	private List<Voiture> voitures;
	private Map<Client, Voiture> locations ;
	public Agence()
	{
		voitures= new ArrayList<>();
		locations=new TreeMap<>();
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
	
	public void loueVoiture(Client client, Voiture v) {
		
		try {
			if(voitures.contains(v))  
			{
				try {
					if(!estLoue(v)) {
						
						try {
							if(!estLoueur(client))
							{
								locations.put(client, v); 
							}else throw new Exception();
						}catch (Exception e) {
							System.out.println("client loueur ");
						}
						
					}else throw new Exception();
				}catch (Exception e) {
					System.out.println("la voiture est deja loue ");
				}
				
			}else throw new Exception();
		}catch(Exception ignored)
		{
			System.out.println("la voiture n'est pas dans l'agence ");
		}
	}
	
	public boolean estLoueur(Client client) {
			return locations.containsKey(client);
	}
	public boolean estLoue(Voiture v) {
		return locations.containsValue(v);
	}
	public void rendVoiture(Client client) {
		if(estLoueur(client)) locations.remove(client);	
	}
	public Iterator<Voiture> lesVoituresLouees() {
		List<Voiture> tmp=new ArrayList<>();
		for (Map.Entry<Client,Voiture> entry : locations.entrySet()) {
			tmp.add(entry.getValue());
		}
		return tmp.iterator();
	}
	public void afficherLocation()
	{
		locations.forEach(
				(k,v)->System.out.println("---------------------\nClient: \n\t"+
											k+"\nVoiture loue: \n\t"+v));
	}
}
