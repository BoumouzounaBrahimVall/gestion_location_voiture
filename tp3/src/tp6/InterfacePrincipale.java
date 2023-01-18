package tp6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import tp3.*;

public class InterfacePrincipale extends JFrame implements ActionListener{
public ControlerVoitures CV;
public InterfaceLocation interfaceLocation;
public JTabbedPane tablePane;
public Agence agence;
public interfaceAccueil ia;
private 	String ch;
public InterfacePrincipale()
{
	
	tablePane=new JTabbedPane();
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	agence =new Agence();
	recupererAgence();
	CV=new ControlerVoitures(agence);
	interfaceLocation=new InterfaceLocation(agence, CV);
	CV.interfaceVoitures.agence.affichVoituresLouees();
	ia=new interfaceAccueil(this);
	tablePane.add("Accueil",ia);
	tablePane.add("Gestion des Voitures",CV.interfaceVoitures);
	tablePane.add("Gestion des Locations",interfaceLocation);
	CV.colorerVoitureLouees();

	this.add(tablePane);
	this.setSize(1000, 700);
	this.setVisible(true);
	this.setResizable(false);
	
	JMenuBar tlb=new JMenuBar();
	JButton save=new JButton("save");
	save.addActionListener((ActionListener) this);
	tlb.add(save);
	this.setJMenuBar(tlb);
	}


public void recupererAgence() {

	try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("voitures.txt"));
		String chaine;
		//TODO:: should be dynamic
		try {
			String bigString = null;
			try {
				bigString = (String) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String[] mesString=bigString.split("\n");
			String[] ch;
			for (int i = 0; i < mesString.length; i++) { 
				ch = mesString[i].split(" ");
				this.agence.ajouterVoiture(
						new Voiture(ch[1], ch[2], Integer.parseInt(ch[3]), Integer.parseInt(ch[4]), ch[0]));
			}
		} finally {
			ois.close();
		}
	} catch (IOException e) {

		e.printStackTrace();
	}
	
	
	try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("locations.txt"));
		String chaine;
		//TODO:: should be dynamic
		try {
			String bigString = null;
			try {
				bigString = (String) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			String[] mesString=bigString.split("\n");
			System.err.println("errrrr " +bigString );
			String[] ch;
			for (int i = 0; i < mesString.length; i++) { 
					ch = mesString[i].split(" ");
					this.agence.loueVoiture(new Client(ch[1], ch[2], ch[3], ch[4]),
							this.agence.getVoiture(ch[0]));}
		} finally {
			ois.close();
		}
	} catch (IOException e) {

		e.printStackTrace();
	}
	
	

}
public void sauvegarder()
{
	try {

		String chaine="";
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("voitures.txt"));
		try {
			for(Voiture v: agence.voitures) {
					chaine+= v.getMatricule()+" "+v.getMarque()+" "+v.getModele()+" "
							+v.getAnneeProd()+" "+v.getPrix()+"\n";			
			}oos.writeObject(chaine);
		} finally {
			oos.close();
		}
	} catch (IOException e) {

		e.printStackTrace();
	}
	
	try {

		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("locations.txt"));
		try {
			
			agence.locations.forEach((k,v)->{
				ch+=v.getMatricule()+" "+k.getCIN()+" "+k.getNom()
				+" "+ k.getPrenom()+" "+k.getCivilite()+"\n";
				
			});
			oos.writeObject(ch);
		} finally {
			oos.close();
		}
	} catch (IOException e) {

		e.printStackTrace();
	}
	
	
}

@Override
public void actionPerformed(ActionEvent e) {
	JButton b=(JButton) e.getSource();
	if(b.getText().equals("save"))
	{
		sauvegarder();
	}
}
	

	
public static void main(String[] args) {
	InterfacePrincipale inte =new InterfacePrincipale();
}
	
	
}