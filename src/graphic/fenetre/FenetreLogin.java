package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusDepart.MenuDemmarrage;

@SuppressWarnings("serial")
public class FenetreLogin extends JFrame {
	
	//On ajoute la partie principale
	
	private static FenetreLogin instance;
	
	//On a pas la même dimension pour la fenêtre de menu de login et de création
	private static Dimension dimLogin = new Dimension(400, 200);
	private static Dimension dimCreationMenu = new Dimension(400, 400);
	
	
	private FenetreLogin() {
		Fenetre.getInstance();
		this.setTitle("Connexion");
		setSize(dimLogin);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().add(new MenuDemmarrage().getMesOnglets());
		setVisible(true);
		setResizable(false);
		
	}
	
	public void changerLadim() {
		
		if(this.getSize().equals(dimLogin)) {
			this.setSize(dimCreationMenu);
		}
		else {
			this.setTitle("Connexion");
			this.setSize(dimLogin);
		}
		this.setLocationRelativeTo(null);
	}
	
	
	public static FenetreLogin getInstance() {
		if (instance == null)
			instance = new FenetreLogin();
		return instance;
	}


}