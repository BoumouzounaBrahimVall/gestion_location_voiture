package tp6;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp3.Voiture;


public class AffichageVoiture  extends JPanel{
	private JTable table;
	private static  String[] titres= new String[] {"MATRICULE","MARQUE","MODELE","PRIX","ANNEE"};
	private static final long serialVersionUID = -7029700824422688255L;
	public AffichageVoiture(List<Voiture> voitures) {
		
		table=new JTable(new DefaultTableModel(titres,0));
	//	this.setLayout(new GridLayout(1,1,10,10));
		this.add(new JScrollPane(table));
		
		//this.setSize(500,500);
		//this.setVisible(true);
		for(Voiture v : voitures) {
			((DefaultTableModel )table.getModel() ).addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;

		}
			
	}
	public JTable getTable() {
		return table;
	}
	
	public void setTable(JTable tab) {
		this.table = tab;
	}
}
