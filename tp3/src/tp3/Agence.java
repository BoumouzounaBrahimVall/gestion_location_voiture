package tp3;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Agence {
	private List<Voiture> voitures;
	private Map<Client, Voiture> locations;
	
	public Agence() {
		voitures = new ArrayList<>();
		locations = new TreeMap<>();
	}

	public Iterator<Voiture> selectionne(Critere c) {
		ArrayList<Voiture> tmp = new ArrayList<>();
		for (Voiture voiture : voitures) {
			if (c.estSatisfaitPar(voiture)) {
				tmp.add(voiture);
			}
		}
		return tmp.iterator();
	}

	public void afficheSelection(Critere c) {
		Iterator<Voiture> elements = selectionne(c);
		while (elements.hasNext()) {
			System.out.println(elements.next());
		}

	}

	public void ajouterVoiture(Voiture v) {
		voitures.add(v);
	}

	public void loueVoiture(Client client, Voiture v) {

		try {
			if (voitures.contains(v)) {
				try {
					if (!estLoue(v)) {

						try {
							if (!estLoueur(client)) {
								locations.put(client, v);
							} else
								throw new Exception();
						} catch (Exception e) {
							System.out.println("client loueur ");
						}

					} else
						throw new Exception();
				} catch (Exception e) {
					System.out.println("la voiture est deja loue ");
				}

			} else
				throw new Exception();
		} catch (Exception ignored) {
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
		if (estLoueur(client))
			locations.remove(client);
	}

	public Iterator<Voiture> lesVoituresLouees() {
		List<Voiture> voituresLouees = new ArrayList<Voiture>();
		voituresLouees.addAll(this.locations.values());
		return voituresLouees.iterator();
	}

	public Iterator<Client> lesClientsLoueur() {
		List<Client> clientsLoueur = new ArrayList<Client>();
		clientsLoueur.addAll(this.locations.keySet());
		return clientsLoueur.iterator();
	}

	public void affichClientsLoueur()
	{
		Iterator<Client> iter_clients = this.lesClientsLoueur();
		while (iter_clients.hasNext())
			System.out.println(iter_clients.next());
	}

	public void affichVoituresLouees()
	{
		Iterator<Voiture> iter_voitures = this.lesVoituresLouees();
		while (iter_voitures.hasNext())
			System.out.println(iter_voitures.next());
	}

	 public Integer convertInt(String chaine)
	    {
		    try {
		    	return Integer.parseInt(chaine);
		    }
			    catch(Exception e) {
			    //e.printStackTrace();
			    System.out.println("Probleme de convertion de : "+chaine);
			    return 0;
		    }
	    }
	public void afficherLocation() {
		locations.forEach(
				(k, v) -> System.out.println("---------------------\nClient: \n\t" + k + "\nVoiture loue: \n\t" + v));
	}
	
	public Voiture Saisir_Voiture()
	{
		Scanner clavier = new Scanner(System.in);
		String marque, nom,matricule;
		int prix, annee;
		System.out.println("Donner Le matricule du voiture ");
		matricule = clavier.nextLine();
		System.out.println("Donner La marque du voiture ");
		marque = clavier.nextLine();
		System.out.println("Donner Le nom du modele du voiture ");
		nom = clavier.nextLine();
		System.out.println("Donner le prix du voiture");
		prix =convertInt(clavier.nextLine());
		System.out.println("Donner l'annee de production du voiture");
		annee = convertInt(clavier.nextLine());
		return new Voiture(marque, nom, annee, prix,matricule);
	}

	public Client Saisir_Client()
	{
		Scanner clavier = new Scanner(System.in);
		String prenom, nom, cin, civilite;
		System.out.println("Donner Le nom du client ");
		nom = clavier.nextLine();
		System.out.println("Donner Le prenom du client ");
		prenom = clavier.nextLine();
		System.out.println("Donner le cin du client");
		cin = clavier.nextLine();
		System.out.println("Donner la civilite du client");
		civilite = clavier.nextLine();
		return new Client(nom, prenom, cin, civilite);
	}
	public void menu()
	{
		char key;
		do {
			System.out.println("1-ajouter une voiture");
			System.out.println("2-supprimer une voiture");
			System.out.println("3-modifier une voiture");
			System.out.println("4-afficher les voitures");
			
			System.out.println("5-ajouter une location");
			System.out.println("6-confirmer le rend d'une voiture (fin location)");
			System.out.println("7-afficher les voitures louees");
			System.out.println("8-afficher les clients");
			System.out.println("9-afficher les locations");
			
			System.out.println("0-quiter le programme");
			Scanner scan=new Scanner(System.in);
			key=scan.next().charAt(0);
			switch (key) {
			case '1'->{
				ajouterVoiture(Saisir_Voiture());
			}
			case '2'->{
				
			}
			case '3'->{
				
			}
			case '4'->{
				
			}
			case '5'->{
				
			}
			case '6'->{
				
			}
			case '7'->{
				
			}
			case '8'->{
				
			}
			case '9'->{
				
			}
			case '0'->{
				
			}
			default->{
				System.out.println("choix incorrect!!!!");
			}
			}
			
		}while(true);
	}
}












