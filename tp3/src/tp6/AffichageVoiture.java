package tp6;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import tp3.Voiture;



public class AffichageVoiture  extends JPanel{
	private JTable table;
	private static  String[] titres= new String[] {"MATRICULE","MARQUE","MODELE","PRIX","ANNEE"};
	private static final long serialVersionUID = -7029700824422688255L;
	public AffichageVoiture(List<Voiture> voitures) {
		
		table=new JTable(new DefaultTableModel(titres,0));
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
		this.add(new JScrollPane(table));
		
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