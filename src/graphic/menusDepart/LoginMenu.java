//UN ONGLET DU MENU DEMARRAGE
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
public class LoginMenu extends JPanel{
	
	private static LoginMenu instance;
	
	//On met ces variables en globale pour y avoir accés de partout et pouvoir valider la connexion
	JPanel menuLogin;
	JTextField login;//BARRE DE SAISIE LOGIN
	JPasswordField passeword;//IDEM POUR MDP
	

	private LoginMenu() {		
		menuLogin = new JPanel(new GridLayout(2,1, 0, 0));//POSITIONNE LA FENETRE COMME UN TABLEAU LIGNE COLONNE ET ESPACE EN HAUTEUR ET LARGEUR
		menuLogin.setBackground(new Color(200, 100, 100));
		menuLogin.add(InitialisationDuMenu());//AJOUT DE LOGIN ET MDP VIA UN TABLEAU 
		menuLogin.add(InitDesBouttons());
	}
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(2,2,15,10));
		login = new JTextField("Login");
		passeword = new JPasswordField("Mot de Passe");

		JLabel loginTexte = new JLabel("Login :");//ECRIRE UNE ETIQUETTE CENTRÉE
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		JLabel motDePasse = new JLabel("Mot de Passe :");//ECRIRE UNE ETIQUETTE CENTRÉE
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		//On ajoute au Panel les éléments pour saisir mot de pass et login.
		mesEntre.add(loginTexte);
		mesEntre.add(login);
		mesEntre.add(motDePasse);
		mesEntre.add(passeword);

		return mesEntre;
	}
	
	private JPanel InitDesBouttons() {//INITIALISATION DES BOUTONS
		JPanel mesBouttons = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 25));
		JButton valider = new JButton("Valider");
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener((event)->quitter());//AJOUT DES LSTENER SUR LE BOUTON AFIN DE REALISER L'ACTION
		valider.addActionListener((event)->valider());//AJOUT DES LSTENER SUR LE BOUTON AFIN DE REALISER L'ACTION
		//PERMET DE CHANGER LE CURSEUR QUAND ON PASSE DESSUS
		valider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mesBouttons.add(valider);
		mesBouttons.add(quitter);
		return mesBouttons;
	}
	
	public static LoginMenu getInstance() {
		if (instance == null)
			instance = new LoginMenu();
		return instance;
	}

	public JPanel getMenuLogin() {
		return menuLogin;
	}

	public void setMenuLogin(JPanel menuLogin) {
		this.menuLogin = menuLogin;
	}
	
	private void quitter() {
		FenetreLogin.getInstance().dispose();//LIBERE LA FENETRE SANS ARRETER LE PROCESSUS
		FenetreFond.getInstance().dispose();
		System.exit(0);//ARRET DU PROCESSSUS
	}
	
	private void valider() {//VALIDATION 
		String passewordTraduit = new String(passeword.getPassword());//RENVOIE D'UN TABLEAU DE CARACTERE QUI SERA TRNASFORMÉ EN CHAINE DE CARACTERES
		if(personnesDejaInscrite.getInstance().rechercher(login.getText(), passewordTraduit)) {//LOGIN.GETTEXT RECUPERE LE CONTENU DE LA BARRE DE SAISIE
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
