package tp6;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import tp3.*;

public class InterfacePrincipale extends JFrame{
private ControlerVoitures CV;
private InterfaceLocation interfaceLocation;
private JTabbedPane tablePane;
private Agence agence;
public InterfacePrincipale()
{
	
	tablePane=new JTabbedPane();
	agence =new Agence();
	recupererAgence();
	CV=new ControlerVoitures(agence);
	interfaceLocation=new InterfaceLocation(agence);
	CV.interfaceVoitures.agence.loueVoiture(new Client("a","a","a","a"), new Voiture("AUDI","RS3",2020,20000,"a1"));
	CV.interfaceVoitures.agence.affichVoituresLouees();
	tablePane.add("Gestion des Voitures",CV.interfaceVoitures);
	tablePane.add("Gestion des Locations",interfaceLocation);
	CV.colorerVoitureLouees();

	this.add(tablePane);
	this.setSize(1000, 700);
	this.setVisible(true);
	this.setResizable(false);
}

public void recupererAgence() {

	try {

		// On cree un flux

		DataInputStream dis = new DataInputStream(new FileInputStream("C:\\Users\\S USER\\git\\gestion_location_voiture\\tp3\\src\\voitures.txt"));

		String chaine;

		try {
			String matricule;
			String[] ch;
			for (int i = 0; i < 9; i++) { // 9 voiture  pour hamza
				matricule = dis.readLine();
				ch = matricule.split(" ");
				this.agence.ajouterVoiture(
						new Voiture(ch[1], ch[2], Integer.parseInt(ch[3]), Integer.parseInt(ch[4]), ch[0]));
			}
		} finally {
			dis.close();
		}
	} catch (IOException e) {

		e.printStackTrace();
	}
	
	try {

		//remplire les  locations
		DataInputStream dis = new DataInputStream(new FileInputStream("C:\\Users\\S USER\\git\\gestion_location_voiture\\tp3\\src\\locations.txt"));
		String chaine;
		try {
			String matricule;
			String[] ch;
			for (int i = 0; i < 3; i++) { // 6 voiture  pour hamza
				matricule = dis.readLine();
				ch = matricule.split(" ");
				this.agence.loueVoiture(new Client(ch[1], ch[2], ch[3], ch[4]),
						this.agence.getVoiture(ch[0]));
			}
		} finally {
			dis.close();
		}
	} catch (IOException e) {

		e.printStackTrace();
	}

}



	
public static void main(String[] args) {
	InterfacePrincipale inte =new InterfacePrincipale();
}
	
	
	
}