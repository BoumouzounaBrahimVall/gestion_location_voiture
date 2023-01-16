package tp6;
//43, 100, 122 strong   113, 195, 227

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp3.Agence;
import tp3.Voiture;

public class InterfaceAgence extends JFrame implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	public Agence agence;
	private JPanel panelAjout;
	private JPanel panelBtns;
	private JPanel panelTab;
	private JTextField[] inputs; 
	private JButton[] boutons;
	private JLabel[] labels;
	private JScrollPane scrollpane;
	private JTable table;
	private Filtrage filtre;
	private JScrollPane scroller,scroller2;
	String[] colums= {"CIN","NOM","PRENOM","Matricule","Marque","Modele","Annee","Prix"};
	public InterfaceAgence()
	{
		agence=new Agence();
	
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container content = this.getContentPane();
		 content.setLayout(new BorderLayout());
		panelAjout=new JPanel(new GridLayout(5,2,10,10));
		panelAjout.setBackground(new Color(113, 195, 227));
		inputs=new JTextField[8];
		for(int i=0;i<5;i++)
			inputs[i]=new JTextField(20);
		labels=new JLabel[10];
		labels[0]=new JLabel("Matricule");
		labels[1]=new JLabel("CIN");
		labels[2]=new JLabel("NOM");
		labels[3]=new JLabel("PRENOM");
		labels[4]=new JLabel("Prix");
		for(int i=0;i<5;)
		{
			panelAjout.add(labels[i]);
			panelAjout.add(inputs[i]);
			i++;
		}
			
		
		panelBtns=new JPanel(new  BorderLayout(10,30));
		
		
		boutons=new JButton[4];
		boutons[0]=new JButton("Ajouter");
		boutons[0].addActionListener(this);
		boutons[1]=new JButton("Supprimer");
		boutons[1].addActionListener(this);
		boutons[2]=new JButton("Modifier");
		boutons[2].addActionListener(this);
		panelBtns.add(boutons[0],BorderLayout.NORTH);
		boutons[0].setBackground(new Color(43, 100, 122));
		boutons[0].setForeground(Color.white);
		panelBtns.add(boutons[1],BorderLayout.CENTER);
		boutons[1].setBackground(new Color(43, 100, 122));
		boutons[1].setForeground(Color.white);
		panelBtns.add(boutons[2],BorderLayout.SOUTH);
		boutons[2].setBackground(new Color(43, 100, 122));
		boutons[2].setForeground(Color.white);
		panelBtns.setBackground(new Color(113, 195, 227));
		
		panelTab=new JPanel();
		scrollpane=new JScrollPane();
		panelTab.add(scrollpane);
		DefaultTableModel model = new DefaultTableModel(colums, 0);
		table=new JTable(model);
		table.addMouseListener(this);
		scrollpane.setViewportView(table);

		
		filtre=new Filtrage(agence);
		//------ main Front works
        JSplitPane panSplit=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panSplit.setDividerLocation(500);
        panSplit.setDividerSize(10);
		JPanel panelCrud=new JPanel();
		JPanel panelFiltEtCrud=new JPanel();
		panelFiltEtCrud.setLayout(new BorderLayout());

		panelCrud.add(panelAjout);
		panelCrud.add(panelBtns);
		panelCrud.setBackground(new Color(113, 195, 227));

		 JSplitPane panSplit2=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	        panSplit.setDividerLocation(500);
	        panSplit.setDividerSize(10);
		
	   Label titreFiltre=new Label("\tFiltrer pour selection une voiture",1);
	   titreFiltre.setFont(new Font("", Font.BOLD, 17 ));
	   titreFiltre.setForeground(Color.WHITE);
		panelFiltEtCrud.add(titreFiltre,BorderLayout.NORTH);
		panelFiltEtCrud.add(filtre,BorderLayout.CENTER);
		panelFiltEtCrud.setBackground(new Color(113, 195, 227));
		scroller=new JScrollPane(panelFiltEtCrud,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panSplit2.add(panelCrud);
		panSplit2.add(scroller);
		panSplit.add(panSplit2);

		
		scroller2=new JScrollPane(panelTab,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panSplit.add(scroller2);
		content.add(panSplit);
		
		
		
		this.setSize(1000,500);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocation(150, 100);

		
	}
	
	public static void main(String[] args) {
		new InterfaceAgence();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel modele=(DefaultTableModel) table.getModel();
		String buttonLable=((JButton)e.getSource( )).getText();
		switch (buttonLable) {
		case "Ajouter": {
			boolean ajout=true;
			for(int i=0;i<5;i++)
			{
				if(inputs[i].getText().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Remplisser tous les champs!", "Champ vide", JOptionPane.ERROR_MESSAGE);
					ajout=false;
					break;
				}
				
			}
			
			if(ajout)
			{
				Voiture v=new Voiture(inputs[1].getText(),inputs[2].getText(),Integer.parseInt(inputs[3].getText()),
						Integer.parseInt(inputs[4].getText()),inputs[0].getText()	);
				if(agence.containsV(v.getMatricule()))
				{
					JOptionPane.showMessageDialog(this, "Cette voiture existe!", "Voiture existe", JOptionPane.ERROR_MESSAGE);
					 inputs[0].requestFocus();//cursor
	
				}
				else {
				agence.ajouterVoiture(v);
				modele.addRow(new Object[] {v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()}) ;
				agence.afficherVoiture();
				viderInputs();
				 
				}
			}
		}
		case "Supprimer": {
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				agence.supprimerVoiture(o);
				modele.removeRow(ligne);
				viderInputs();
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);

		}
		case "Modifier": {
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				for(int i=0;i<5;i++)
				modele.setValueAt(inputs[i].getText(), ligne, i);
				agence.getVoiture(o).setMatricule(inputs[0].getText());
				agence.getVoiture(inputs[0].getText()).setMarque(inputs[1].getText());
				agence.getVoiture(inputs[0].getText()).setModele(inputs[2].getText());
				agence.getVoiture(inputs[0].getText()).setAnneeProd(Integer.parseInt(inputs[3].getText()));
				agence.getVoiture(inputs[0].getText()).setPrix(Integer.parseInt(inputs[4].getText()));
				
				
				viderInputs();
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une voiture!", "no selection", JOptionPane.ERROR_MESSAGE);

		}}
		
		
	}

	private void viderInputs() {
		for(int i=0;i<5;i++) inputs[i].setText("");
		inputs[0].requestFocusInWindow();//cursor
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
		int ligne=table.getSelectedRow();
		if(ligne!=-1)
		{
			String o= (String) table.getValueAt(ligne,0);
			inputs[0].setText(agence.getVoiture(o).getMatricule());
			inputs[1].setText(agence.getVoiture(o).getMarque());
			inputs[2].setText(agence.getVoiture(o).getModele());
			inputs[3].setText(Integer.toString(agence.getVoiture(o).getAnneeProd()));
			inputs[4].setText(Integer.toString(agence.getVoiture(o).getPrix()));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	
	
}