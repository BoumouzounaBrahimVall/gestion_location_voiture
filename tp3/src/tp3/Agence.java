package tp3;

import java.util.ArrayList;
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

	private boolean containsV(final String matricule) {
		return voitures.stream().anyMatch(o -> matricule.equals(o.getMatricule()));
	}

	public void ajouterVoiture(Voiture v) {
		try {
			if (containsV(v.getMatricule())) {
				throw new Exception();
			} else {
				voitures.add(v);
				System.out.println("voiture ajoutee avec succes");
			}
		} catch (Exception e) {
			System.out.println("le matricule : (" + v.getMatricule() + ") correspond a une voiture deja ajoutee");
		}
	}

	public void supprimerVoiture(String v) {
		try {
			if (!voitures.removeIf(vt -> vt.getMatricule().equals(v)))
				throw new Exception();
			else
				System.out.println("suppression avec succes");
		} catch (Exception e) {
			System.out.println("echec de suppression matricule ne correspond a aucune voiture");
		}
	}

	public Voiture getVoiture(String mat) {
		for (Voiture v : voitures)
			if (v.getMatricule().equals(mat))
				return v;
		return null;
	}
	
	public Client getClient(String cin) {
		ArrayList<Client> clients = new ArrayList<>(locations.keySet());
		for (Client cli: clients)
			if(cli.getCIN().equals(cin)) return (Client) cli;
		return null;
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

	public void affichClientsLoueur() {
		Iterator<Client> iter_clients = this.lesClientsLoueur();
		while (iter_clients.hasNext())
			System.out.println(iter_clients.next());
	}

	public void affichVoituresLouees() {
		Iterator<Voiture> iter_voitures = this.lesVoituresLouees();
		while (iter_voitures.hasNext())
			System.out.println(iter_voitures.next());
	}

	public void afficherLocation() {
		locations.forEach(
				(k, v) -> System.out.println("---------------------\nClient: \n\t" + k + "\nVoiture loue: \n\t" + v));
	}

	public void menu() {
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
			Scanner scan = new Scanner(System.in);
			key = scan.next().charAt(0);
			switch (key) {
			case '1' -> {
				ajouterVoiture(Voiture.Saisir_Voiture());
			}
			case '2' -> {
				System.out.println("entrer le matricule de la voiture a supprimer de l'agence ");
				supprimerVoiture(scan.nextLine());
			}
			case '3' -> {
				System.out.println("entrer le matricule de la voityure a modufie : ");
				Voiture v =getVoiture(scan.nextLine());
				if(v!=null) v.modifierVoiture();
				else System.out.println("matricule incorrect !!");
			}
			case '4' -> {

			}
			case '5' -> {
				
				Voiture v;
				do {
					System.out.println("entrer le matricule de la voiture a louee: ");
					v=getVoiture(scan.nextLine());
					if(v==null) System.out.println("Matricule incorrect");
				}while(v==null);
				loueVoiture(Client.Saisir_Client(),v);
			}
			case '6' -> {

			}
			case '7' -> {

			}
			case '8' -> {

			}
			case '9' -> {

			}
			case '0' -> {

			}
			default -> {
				System.out.println("choix incorrect!!!!");
			}
			}

		} while (true);
	}
}
