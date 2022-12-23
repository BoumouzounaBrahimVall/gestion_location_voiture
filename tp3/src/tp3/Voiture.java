package tp3;

public class Voiture {
	private String matricule;
	private String marque;
	private String modele;
	private int anneeProd;
	private int prix;
	public Voiture(String marque, String modele, int anneeProd, int prix,String matricule) {
		this.marque = marque;
		this.modele = modele;
		this.anneeProd = anneeProd;
		this.prix = prix;
		this.matricule=matricule;
	}
	
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
		if(obj==this) return true;
		if(!(obj instanceof Voiture)) return false;
		
		return ((this.marque!=null)&&(this.marque.equals(((Voiture) obj).getMarque()))&&
				(this.anneeProd==((Voiture)obj).getAnneeProd())&&
				(this.prix==((Voiture)obj).getPrix())&&
				(this.modele!=null)&&(this.modele.equals(((Voiture)obj).getModele())));
	}
	@Override
	public String toString() {
		return " Marque : "+marque+" Modele : "+modele+" Annee de Prod : "+anneeProd +" Prix : "+prix;
	}
	
}
