package graphic.menusDepart;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.elementSauv.personnesDejaInscrite;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;


@SuppressWarnings("serial")

/**
 *<b>LoginMenu</b> est la classe qui va créer le menu d'indentification
 *@author VIRGAUX Pierre
 **/

public class LoginMenu extends JPanel{
	
	private static LoginMenu instance;
	
	/**On met ces variables en globales afin d'y avoir accés partout et pouvoir valider la connexion
	 **/
	
	JPanel menuLogin;
	
	/**
	 *Barre de saisie login
	 **/
	
	JTextField login;
	
	/**
	 *Barre saisie mot de passe
	 **/
	
	JPasswordField passeword;
	
	/**
	 *Initialise les positions de la fenetre de login
	 **/
	
	private LoginMenu() {		
		
		/**
		 *Fenêtre positionnée comme tableau de ligne/colonne avec un espace en hauteur et largeur
		 **/
		
		menuLogin = new JPanel(new GridLayout(2,1, 0, 0));
		menuLogin.setBackground(new Color(200, 100, 100));
		
		/**
		 *Ajout du login et mot de passe via un tableau
		 **/
		
		menuLogin.add(InitialisationDuMenu()); 
		menuLogin.add(InitDesBouttons());
	}
	
	/**
	 *Initialisation de menu 
	 **/
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(2,2,15,10));
		login = new JTextField("Utilisateur");
		passeword = new JPasswordField("Mot de Passe");
		
		/**
		 *Centrage du login
		 **/
		
		JLabel loginTexte = new JLabel("Utilisateur :");
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		
		/**
		 *Centrage du mot de passe
		 **/
		
		JLabel motDePasse = new JLabel("Mot de Passe :");
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		/**On ajoute au Panel les éléments de saisie mot de passe et login
		 **/
		
		mesEntre.add(loginTexte);
		mesEntre.add(login);
		mesEntre.add(motDePasse);
		mesEntre.add(passeword);

		return mesEntre;
	}
	
	/**
	 *Création des boutons de validationde choix
	 *@return Boutons de validation
	 **/
	 
	private JPanel InitDesBouttons() {
		
		/**
		 *Initialisation des boutons centrés
		 **/
		
		JPanel mesBouttons = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 25));
		JButton valider = new JButton("Valider");
		JButton quitter = new JButton("Quitter");
		
		/**
		 *Ajout de listener sur le bouton afin de realiser l'action
		 **/
		
		quitter.addActionListener((event)->quitter());
		
		/**
		 *Ajout de listener sur le bouton afin de realiser l'action
		 **/
		
		valider.addActionListener((event)->valider());
		
		/**
		 *Permet de changer le curseur lorsqu'on passe dessus
		 **/
		
		valider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		/**
		 *Ajout des actions
		 **/
		
		mesBouttons.add(valider);
		
		/**
		 *Ajout des actions
		 **/
		
		mesBouttons.add(quitter);
		return mesBouttons;
	}
	
	/**
	 *Instanciation du menu de login
	 *@return Menu de login
	 **/
	
	public static LoginMenu getInstance() {
		if (instance == null)
			instance = new LoginMenu();
		return instance;
	}
	
	
	/**
	 *Retourne le menu de login
	 *@return Menu login
	 **/
	
	public JPanel getMenuLogin() {
		return menuLogin;
	}

	/**
	 *Initialisation du menu de login
	 *@param menuLogin
	 **/
	
	public void setMenuLogin(JPanel menuLogin) {
		this.menuLogin = menuLogin;
	}
	
	/**
	 *Permet de quitter la fenêtre en cours, sans arrêter le processus
	 **/
	
	private void quitter() {
		
		/**
		 *On rend invisible la fenêtre de login
		 **/
		
		FenetreLogin.getInstance().dispose();
		
		/**
		 *On rend invisible la fenêtre de fond
		 **/
		
		FenetreFond.getInstance().dispose();
		System.exit(0);
	}
	
	/**
	 *Permet la valider de la saisie d'informations
	 **/
	
	private void valider() {
		
		/**
		 *Renvoie un tableau de caractères qui sera transformé en chaine de caractère
		 **/
		
		String passewordTraduit = new String(passeword.getPassword());
		
		/**
		 *login.getText() récupère le contenu de la barre de saisie
		 **/
		
		
		//ICI TRAVAILLE BDD A LA PLACE DE CETTE CONDITION QUE JE GARDE SUR MA BRANCHE POUR POUVOIR CONTINUER À BOSSER IL FAUT QUE TU METTES
		//TA PROPRE FONCTION POUR SAVOIR SI L UTILISATEUR EXISTE BIEN ET 
		//PENSE A REGARDER L ERREUR QUE CA FAIT QD C LE MAUVAIS UTILISATEUR ET FAIS UN TRY CATCH CA SERA LE MIEU
		
		
		if(personnesDejaInscrite.getInstance().rechercher(login.getText(), passewordTraduit)) {
			FenetreLogin.getInstance().dispose();
			FenetreFond.getInstance().changerFenetre(login.getText());
			login.setText("");
			passeword.setText("");
		}
		else {
			JOptionPane.showInternalMessageDialog(this, "Utilisateur ou mot de passe inconnu", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
	}
}
