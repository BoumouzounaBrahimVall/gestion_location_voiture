package tp3;

public class CriterePrix implements Critere {

	private static int prixst;
	public CriterePrix(int pr) {
		prixst=pr;
	}
	@Override
	public boolean estSatisfaitPar(Voiture v) {	
		return v.getPrix()<prixst;
	}

}
