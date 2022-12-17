package tp3;
/*
 Un client ne peut louer qu'un véhicule à la fois. 
 On supposera que les clients sont identifiés par un nom,
  un prénom, un CIN et une civilité (M., Mlle, Mme)
 */
public class Client {
	private String CIN;
	private String nom;
	private String prenom;
	private String civilite;
	
	public Client(String cIN, String nom, String prenom, String civilite) {
		CIN = cIN;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
	}
	
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!obj.getClass().equals(this.getClass())) return false;
		
		return ((this.CIN!=null)&&(this.CIN.equals(((Client) obj).getCIN()))&&
				(this.nom!=null)&&(this.nom.equals(((Client) obj).getNom()))&&
				(this.prenom!=null)&&(this.prenom.equals(((Client) obj).getPrenom()))&&
				(this.civilite!=null)&&(this.civilite.equals(((Client) obj).getCivilite()))
				);
	}
	@Override
	public String toString() {
		
		return "CIN: "+CIN+" Nom et prenom: "+civilite+" "+nom+" "+prenom;
	}
	
}
