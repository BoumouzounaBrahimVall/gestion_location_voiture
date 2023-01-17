package tp6;
//43, 100, 122 strong   113, 195, 227

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import tp3.Agence;
import tp3.Client;
import tp3.Voiture;

public class InterfaceLocation extends JPanel implements ActionListener,MouseListener{

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
	JRadioButton Homme,Femme;
	ButtonGroup groupeHF;
	String[] colums= {"CIN","NOM","PRENOM","Matricule","Marque","Modele","Annee","Prix"};
	public InterfaceLocation(Agence agence)
	{
		this.agence=agence;
	
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Container content = this.getContentPane();
		 this.setLayout(new BorderLayout());
		panelAjout=new JPanel(new GridLayout(5,2,10,10));
		panelAjout.setBackground(new Color(113, 195, 227));
		inputs=new JTextField[8];
		for(int i=0;i<5;i++)
			inputs[i]=new JTextField(20);
		labels=new JLabel[10];
		labels[4]=new JLabel("CIVILITE");
		labels[0]=new JLabel("CIN");
		labels[1]=new JLabel("NOM");
		labels[2]=new JLabel("PRENOM");
		labels[3]=new JLabel("MATRICULE");
		//TODO :: treat the civility 
		
		groupeHF = new ButtonGroup();
		Homme = new JRadioButton("Homme");
		Homme.setMnemonic (KeyEvent.VK_4);Homme.setBackground(new Color(113, 195, 227));
		panelAjout.add (Homme); groupeHF.add (Homme);
		Homme.setSelected(true);
		Femme = new JRadioButton("Femme");Femme.setBackground(new Color(113, 195, 227));
		Femme.setMnemonic (KeyEvent.VK_6);
		panelAjout.add (Femme); groupeHF.add (Femme);
		
		
		for(int i=0;i<4;)
		{
			panelAjout.add(labels[i]);
			panelAjout.add(inputs[i]);
			i++;
		}
			
		
		panelBtns=new JPanel(new  BorderLayout(10,30));
		
		
		boutons=new JButton[4];
		boutons[0]=new JButton("Louer");
		boutons[0].addActionListener(this);
		boutons[1]=new JButton("Rendre");
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
		table.getTableHeader().setDefaultRenderer(new MyHeaderRenderer());
		table.setDefaultRenderer(Object.class, (TableCellRenderer) new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ?  new Color(43, 100, 122) : new Color(113, 195, 227));
                c.setForeground(Color.WHITE);        
                return c;
            }
        });
		
        // Remove table borders
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("",Font.ITALIC,13));
        table.setRowHeight(30);
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
		this.add(panSplit);
		
		recupererAgence();
		RemplirTableau();
		
		
		this.setSize(1000,500);
		//this.setResizable(false);
		this.setVisible(true);
		this.setLocation(150, 100);

		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel modele=(DefaultTableModel) table.getModel();
		String buttonLable=((JButton)e.getSource( )).getText();
		switch (buttonLable) {
		case "Louer": {
			boolean ajout=true;
			for(int i=0;i<4;i++)
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
				String civilite = Homme.isSelected()?"Mr.":"Mme.";
				Voiture v=this.agence.getVoiture(inputs[3].getText());
				int test=this.agence.loueVoiture(new Client(inputs[0].getText(),inputs[1].getText(), inputs[2].getText(),civilite),v ) ;
				
				String mssge =(test==1 )?"client loueur":((test==2)?"la voiture est deja loue ":"la voiture n'est pas dans l'agence ");
				if(test==0)
				{
					modele.addRow(new Object[] {
							inputs[0].getText(),inputs[1].getText(), inputs[2].getText(),
							v.getMatricule(),v.getMarque(),v.getModele(),v.getAnneeProd(),v.getPrix()
					}) ;
					JOptionPane.showMessageDialog(this, "Location ajoute avec succe", "ajout ", JOptionPane.OK_OPTION);
					
				}
				else 
				{
					JOptionPane.showMessageDialog(this,mssge, "Impossible d'effectuer l'allocation", JOptionPane.ERROR_MESSAGE);
					 inputs[0].requestFocus();//cursor
				}
				viderInputs();
				break;
			}
		}
		case "Rendre": {
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				agence.rendVoiture(agence.getClient(o));
				modele.removeRow(ligne);
				viderInputs();
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une location pour la rendre!", "no selection", JOptionPane.ERROR_MESSAGE);
			break;
		}
		case "Modifier": {
			int ligne=table.getSelectedRow();
			if(ligne!=-1) 
			{
				String o= (String) table.getValueAt(ligne,0);
				System.out.println(o);
				for(int i=0;i<3;i++)
				modele.setValueAt(inputs[i].getText(), ligne, i);
				
				agence.getClient(o).setCIN(inputs[0].getText());
				agence.getClient(inputs[0].getText()).setNom(inputs[1].getText());
				agence.getClient(inputs[0].getText()).setPrenom(inputs[2].getText());
				String civilite = Homme.isSelected()?"Mr.":"Mme.";
				agence.getClient(inputs[0].getText()).setCivilite(civilite);
				
				
				viderInputs();
				
			}
			else JOptionPane.showMessageDialog(this, "Selectionner une location pour la modifier!", "no selection", JOptionPane.ERROR_MESSAGE);

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
			Client c=agence.getClient(o);
			inputs[0].setText(c.getCIN());
			inputs[1].setText(c.getNom());
			inputs[2].setText(c.getPrenom());
			inputs[3].setText(agence.locations.get(c).getMatricule());
		}
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

	public void RemplirTableau() {
	
		String matricule;
		String[] ch;
		DefaultTableModel modelee = (DefaultTableModel) table.getModel();
		this.agence.locations.forEach(
				(k, v) -> 
				modelee.addRow(new Object[]
						{k.getCIN(),
						k.getNom(),k.getPrenom(),
						v.getMatricule(),
						v.getMarque(),v.getModele(),
						v.getAnneeProd(),v.getPrix()}) 
				);
		

			

	}

	public void mousePressed(MouseEvent e) {}public void mouseReleased(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}public void mouseExited(MouseEvent e) {}
	/*
	 * public static void main(String[] args) { Agence agence =new Agence();
	 * 
	 * new InterfaceLocation(agence); }
	 */
}