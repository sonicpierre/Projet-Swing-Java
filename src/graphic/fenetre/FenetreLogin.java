package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusDepart.MenuDemmarrage;

/**
 *La classe <b>FentreLogin</b> permet de générer la fenêtre permettant de se login et de créer un utilisateur SQL.
 *@author Pierre VIRGAUX
 *@version 2.0
 **/
@SuppressWarnings("serial")
public class FenetreLogin extends JFrame {
	
	/**
	 *Déclaration de l'instance de la fenetre de login
	 **/
	
	private static FenetreLogin instance;
	
	/**
	 * {@value #dimLogin} est la dimension de la fenêtre pour se login
	 * {@value #dimCreationMenu} est la dimension de la fenêtre pour créer un utilisateur SQL
	 */
	private static final Dimension dimLogin = new Dimension(400, 200);
	private static final Dimension dimCreationMenu = new Dimension(400, 350);

	/**
	 * Ici il s'agit du constructeur qui permet d'afficher la fenêtre de login et de création de mot de passe mais aussi la fenêtre de fond.
	 * On initialise tout les paramètres qui ne changeront pas forcément par la suite.
	 * @see MenuDemmarrage
	 */
	
	private FenetreLogin() {
		/**
		 *On lance la fenêtre de fond en même temps pour avoir l'image en fond.
		 */
		FenetreFond.getInstance();
		/**
		 *On définit le titre de la fenêtre
		 */
		this.setTitle("Connexion");
		setSize(dimLogin);
		/**
		 *Certes la fenêtre Login se fermera en appuyant sur la croix mais le programme tout entier s'arrêtera avec la fenêtre de fond associé.
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		/**
		 *On ajoute ici le menu avec les 2 onglets celui pour se connecter et celui pour la création de compte SQL
		 */
		getContentPane().add(new MenuDemmarrage().getMesOnglets());
		setVisible(true);
		setResizable(false);
	}

	/**
	 * Permet de switcher d'une fenêtre à l'autre en fonction de si on veut se login ou si on veut créer un compte.
	 */
	public void changerLadim() {
		/**
		 *Permet de savoir à quelle dimension on est et par conséquent savoir aussi sur quelle fenêtre on est.
		 */
		if (this.getSize().equals(dimLogin)) {
			/**
			 *On redonne un nouveau nom
			 */
			this.setTitle("Créer un compte");
			/**
			 *On change la dimension de la fenêtre
			 */
			this.setSize(dimCreationMenu);
		} else {
			this.setTitle("Connexion");
			this.setSize(dimLogin);
		}
		/**
		 *On centre la fenêtre
		 */
		this.setLocationRelativeTo(null);
	}

	/**
	 * Cette fonction permet d'accéder à l'objet FenetreLogin
	 * @return L'objet singleton
	 */
	
	public static FenetreLogin getInstance() {
		if (instance == null)
			instance = new FenetreLogin();
		return instance;
	}

}