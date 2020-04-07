package graphic.menusDepart;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphic.fenetre.FenetreLogin;

@SuppressWarnings("serial")
public class MenuDemmarrage extends JPanel{
	
	JTabbedPane mesOnglets;
	
	public MenuDemmarrage() {
		mesOnglets = new JTabbedPane(JTabbedPane.TOP);
		mesOnglets.add("Membre", LoginMenu.getInstance().getMenuLogin());
		mesOnglets.add("Nouveau", CreerCompte.getInstance().getMenuCreation());
		mesOnglets.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				FenetreLogin.getInstance().changerLadim();
				
			}
		});
	}

	public JTabbedPane getMesOnglets() {
		return mesOnglets;
	}

	public void setMesOnglets(JTabbedPane mesOnglets) {
		this.mesOnglets = mesOnglets;
	}
	
	
}
