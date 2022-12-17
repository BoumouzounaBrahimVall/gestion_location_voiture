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
		if(obj==this) return true;
		if(!(obj instanceof Client)) return false;
		final Client other = (Client) obj;
		
		if ((this.CIN == null) ? (other.CIN != null) : !this.CIN.equals(other.CIN)) {
            return false;
        }
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if ((this.prenom == null) ? (other.prenom != null) : !this.prenom.equals(other.prenom)) {
            return false;
        }
        if ((this.civilite == null) ? (other.civilite != null) : !this.civilite.equals(other.civilite)) {
            return false;
        }
        return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result
                + ((CIN == null) ? 0 : CIN.hashCode());
        return result;
	}

	@Override
	public String toString() {
		
		return "CIN: "+CIN+" Nom et prenom: "+civilite+" "+nom+" "+prenom;
	}
	
}
