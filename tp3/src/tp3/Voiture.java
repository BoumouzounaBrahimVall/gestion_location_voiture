package tp3;
/*
 
 Les voitures sont définies par une marque, un nom de modèle,
  une année de production et un prix de location à la journée. 
  Pour simplifier, les deux premiers paramètres seront des objets
  de la classe String et les deux derniers seront des int. Deux 
  voitures sont considérées égales si tous leurs attributs sont égaux.
 */
public class Voiture {
	private String marque;
	private String modele;
	private int anneeProd;
	private int prix;
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getAnneeProd() {
		return anneeProd;
	}
	public void setAnneeProd(int anneeProd) {
		this.anneeProd = anneeProd;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!obj.getClass().equals(this.getClass())) return false;
		
		return ((this.marque!=null)&&(this.marque.equals(((Voiture) obj).getMarque()))&&
				(this.anneeProd==((Voiture)obj).getAnneeProd())&&
				(this.prix==((Voiture)obj).getPrix())&&
				(this.modele!=null)&&(this.modele.equals(((Voiture)obj).getModele())));
	}
	@Override
	public String toString() {
		return "Marque : "+marque+"Modele : "+modele+"Annee de Prod : "+anneeProd +"Prix : "+prix;
	}
	
	
}
