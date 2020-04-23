package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusDepart.MenuDemmarrage;

@SuppressWarnings("serial")
public class FenetreLogin extends JFrame {// LE CONSTRUCTEUR N'EST ACCESSIBLE QUE DE L'INTERIEUR => CREATION OBJET A
											// PARTIR DE GET INSTANCE

	// On ajoute la partie principale

	private static FenetreLogin instance;

	// On a pas la même dimension pour la fenêtre de menu de login et de création

	private static final Dimension dimLogin = new Dimension(400, 200);
	private static final Dimension dimCreationMenu = new Dimension(400, 350);

	private FenetreLogin() {
		FenetreFond.getInstance();
		this.setTitle("Connexion");
		setSize(dimLogin);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().add(new MenuDemmarrage().getMesOnglets());// AVOIR L'INTÉRIEUR DE LA FENETRE VU QU'IL EST VIDE
																	// ET ON Y AJOUTE QUELQUE CHOSE DE CENTRÉ
		setVisible(true);
		setResizable(false);
	}

	public void changerLadim() {

		if (this.getSize().equals(dimLogin)) {// CONDITION DE TAILLE UNIQUEMENT LORS DU CHANGEMENT D'ONGLET
			this.setTitle("Créer un compte");// CHANGEMENT DU NOM
			this.setSize(dimCreationMenu);
		} else {
			this.setTitle("Connexion");
			this.setSize(dimLogin);// DIMENSION FENETRE
		}
		this.setLocationRelativeTo(null);// CENTRER LA FENETRE
	}

	public static FenetreLogin getInstance() {
		if (instance == null)
			instance = new FenetreLogin();
		return instance;
	}

}