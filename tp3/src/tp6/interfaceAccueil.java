package tp6;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class interfaceAccueil extends JPanel implements ActionListener{

	private JButton voitures,location;
	private InterfacePrincipale IP;
	public interfaceAccueil(InterfacePrincipale ip)
	{
		
		IP=ip;
		this.setLayout(new GridBagLayout());
		JPanel panelBtns=new JPanel(new  GridLayout(1,2,20,20));
		JPanel panelTextBtn=new JPanel(new BorderLayout());
		panelTextBtn.setBackground(NosCouleur.COLOR1);

		JLabel titre=new JLabel("Bienvenu Dans H.V Location");
		titre.setFont(new Font("",Font.BOLD,60));
		titre.setBackground(NosCouleur.COLOR1);
		titre.setForeground(NosCouleur.COLOR3);

		Font f=new Font("",Font.BOLD,30);
		
		voitures=new JButton("Gestion Voitures");
		voitures.setIcon(new ImageIcon("car.png"));
		voitures.setHorizontalTextPosition(JLabel.CENTER);
		voitures.setVerticalTextPosition(JLabel.BOTTOM);
		voitures.setFont(f);
		voitures.setBackground(NosCouleur.COLOR3);
		voitures.setForeground(NosCouleur.COLOR1);
		
		location=new JButton("Gestion Locations");
		location.setIcon(new ImageIcon("carRent.png"));
		location.setHorizontalTextPosition(JLabel.CENTER);
		location.setVerticalTextPosition(JLabel.BOTTOM);
		location.setFont(f);
		location.setBackground(NosCouleur.COLOR3);
		location.setForeground(NosCouleur.COLOR1);


			
		
		voitures.addActionListener(this);
		location.addActionListener(this);
		panelBtns.add(voitures);
		panelBtns.add(location);
		panelBtns.setBackground(NosCouleur.COLOR1);
		
		panelTextBtn.add(titre,BorderLayout.NORTH);
		panelTextBtn.add(panelBtns,BorderLayout.CENTER);

		this.add(panelTextBtn);
		this.setBackground(NosCouleur.COLOR1);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b.getText().equals("Gestion Voitures"))
			IP.tablePane.setSelectedComponent(IP.CV.interfaceVoitures);
		else IP.tablePane.setSelectedComponent(IP.interfaceLocation);
			
	}
	
	
}