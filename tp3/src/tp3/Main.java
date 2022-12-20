package tp3;

public class Main {
	public static void main(String[] args) {
		Agence agence =new Agence();
		agence.ajouterVoiture(new Voiture("BMW", "A3", 2018, 67));
		agence.ajouterVoiture(new Voiture("BMW", "A7", 2018, 110));
		agence.ajouterVoiture(new Voiture("BMW", "A5", 2018, 256));
		agence.ajouterVoiture(new Voiture("Audi", "Q7", 2016, 86));
		agence.ajouterVoiture(new Voiture("Mazerati", "M23A", 2017, 100));
		agence.ajouterVoiture(new Voiture("Mercides", "190D", 1996, 45));
		
		agence.ajouterVoiture(new Voiture("Renaut", "21", 2009, 56));
		agence.ajouterVoiture(new Voiture("Renaut", "2C34", 2009, 146));
		agence.ajouterVoiture(new Voiture("Renaut", "2008", 2021, 170));
		
		InterCritere crit=new InterCritere();
		crit.addCritere(new CritereMarque("Renaut"));
		crit.addCritere(new CriterePrix(120));
		crit.addCritere(new CritereAnnee(2009));
		
		//agence.afficheSelection(crit);
		
		agence.loueVoiture(
				new Client("A321", "vall", "brahim", "Mr")
				,new Voiture("BMW", "A3", 2018, 67) );
		
		agence.loueVoiture(
				new Client("X873", "Hamza", "El", "Mr")
				,new Voiture("Renaut", "2008", 2021, 170) );
		
		agence.loueVoiture(
				new Client("X873", "Hamza", "El", "Mr")
				,new Voiture("Renaut", "2008", 2021, 170) );
		
		/*Iterator<Voiture> it=agence.lesVoituresLouees();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}*/
		
		agence.afficherLocation();
	}	
	
}
