package tp3;

public class Main {
	public static void main(String[] args) {
		Agence agence =new Agence();
		agence.ajouterVoiture(new Voiture("BMW", "A3", 2018, 67));
		agence.ajouterVoiture(new Voiture("BMW", "A7", 2018, 350));
		agence.ajouterVoiture(new Voiture("Audi", "Q7", 2016, 86));
		agence.ajouterVoiture(new Voiture("Mazerati", "M23A", 2017, 100));
		agence.ajouterVoiture(new Voiture("Mercides", "190", 1996, 45));
		agence.afficheSelection(new CriterePrix(100));

	}	
	
}
