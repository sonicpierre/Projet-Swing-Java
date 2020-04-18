package graphic.menusDepart;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import graphic.fenetre.FenetreLogin;

@SuppressWarnings("serial")
public class MenuDemmarrage extends JPanel{
	
	JTabbedPane mesOnglets;//PANEL D'ONGLETS
	
	public MenuDemmarrage() {//CONSTRUCTEUR
		mesOnglets = new JTabbedPane(JTabbedPane.TOP);//INITIALISATION DU JPanel EN LES PLAÇANT EN HAUT 
		mesOnglets.add("Membre", LoginMenu.getInstance().getMenuLogin());//AJOUT DU MENU LOGIN
		mesOnglets.add("Nouveau", CreerCompte.getInstance().getMenuCreation());//AJOUT DE LA FENETRE CREATION DE COMPTE
		mesOnglets.addChangeListener(new ChangeListener() {//LISTENER ECOUTE LES ACTIONS SUR LA FENETRE, 
			
			@Override
			public void stateChanged(ChangeEvent e) {
				FenetreLogin.getInstance().changerLadim();//APPEL DU SINGLETON
				
			}
		});
	}

	public JTabbedPane getMesOnglets() {//RETOURNE LES ONGLETS
		return mesOnglets;
	}

	public void setMesOnglets(JTabbedPane mesOnglets) {
		this.mesOnglets = mesOnglets;
	}
	
	
}

//ON CREE UN PANEL QUI CONTIENT DES OBJETS 
//PUIS ON Y COLLE SUR LA FENETRE
//LE JPanel RETOURNE LE MEMBRE ET NOUVEAU