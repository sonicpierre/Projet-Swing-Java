package graphic.menusDepart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.validator.EmailValidator;

import control.elementSauv.personnesDejaInscrite;
import control.personne.CompteActeur;
import control.personne.CompteChanteur;
import control.personne.CompteComedien;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;


/**
 *<b>CreerCompte</b> est la classe permettant de définir les actions de création de compte.
 *@author VIRGAUX Pierre
 **/

@SuppressWarnings({ "serial", "deprecation" })
public class CreerCompte extends JPanel{
	
	/**
	 *Instanciation de la création de compte
	 **/
	
	private static CreerCompte instance;
	
	/**
	 *Ajout fenêtre à renvoyer
	 **/
	
	JPanel menuCreation;
	
	/**
	 *Saisie login
	 **/
	
	JTextField login;
	
	/**
	 *Saisie mot de passe
	 **/
	
	JPasswordField passeword;
	
	/**
	 *Confirmation saisie mot de passe
	 **/
	
	JPasswordField confirmedPasseword;
	
	/**
	 *Saisie e-mail
	 **/
	
	JTextField adresseMail;
	
	/**
	 *Coche du talent artiste
	 **/
	
	JCheckBox checkArtiste;
	
	/**
	 *Coche du talent chanteur
	 **/
	
	JCheckBox checkChanteur;
	
	/**
	 *Coche du talent danseur
	 **/
	
	JCheckBox checkDanseur;
	
	/**
	 *Définition l'aspect du menu de création
	 **/
	
	private CreerCompte() {
		
		/**
		 *Définition du menu
		 **/
		
		menuCreation = new JPanel(new BorderLayout());
		
		/**
		 *Couleur de fond du menu
		 **/
		
		menuCreation.setBackground(new Color(200, 100, 100));
		
		/**
		 *Initialisation et centrage du menu
		 **/
		
		menuCreation.add(InitialisationDuMenu(), BorderLayout.CENTER);
		
		/**
		 *Initialisation et positionnement des boutons en bas
		 **/
		
		menuCreation.add(InitDesBouttons(), BorderLayout.SOUTH);
	}
	
	
	/**
	 *Permet l'initialisation des composantes du menu d'identification et de création de compte
	 *@return Information saisies
	 **/
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(9,2,25,10));
		login = new JTextField("Login");
		confirmedPasseword = new JPasswordField("Mot de Passe");
		adresseMail = new JTextField("...@gmail.com");
		passeword = new JPasswordField("Mot de Passe");
		
		
		/**
		 *Gestion de la saisie d'identifiants de connexion
		 **/
		
		JLabel loginTexte = new JLabel("Login :");
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		JLabel motDePasse = new JLabel("Mot de Passe :");
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		/**
		 *Gestion de saisie mot de passe
		 **/
		
		JLabel addresseMail = new JLabel("Adresse mail :");
		addresseMail.setHorizontalAlignment(JLabel.CENTER);
		JLabel confirmedMotDePasse = new JLabel("Confirmer Mot de Passe :");
		confirmedMotDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		/**
		 *On ajoute puis enregistre les saisies concernant le talent des artistes
		 **/
		
		JPanel choixCategorie = new JPanel(new GridLayout(1,3));
		checkArtiste = new JCheckBox("Acteur");
		checkChanteur = new JCheckBox("Chanteur");
		checkDanseur = new JCheckBox("Comedien");
		choixCategorie.add(checkArtiste);
		choixCategorie.add(checkChanteur);
		choixCategorie.add(checkDanseur);
		
		/**Ajoute au Panel des éléments de saisie mot de passe et login
		 **/
		
		mesEntre.add(loginTexte);
		mesEntre.add(login);
		mesEntre.add(motDePasse);
		mesEntre.add(passeword);
		mesEntre.add(confirmedMotDePasse);
		mesEntre.add(confirmedPasseword);
		mesEntre.add(addresseMail);
		mesEntre.add(adresseMail);
		mesEntre.add(choixCategorie);
		
		return mesEntre;
	}
	
	/**
	 *Initilisation des boutons
	 *<b>NB : </b> Vérification nécessaire de l'existence de compte, de la saisie des champs
	 **/
	
	private JPanel InitDesBouttons() {
		JPanel mesBouttons = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 25));
		JButton valider = new JButton("Valider");
		JButton quitter = new JButton("Quitter");
		valider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		/**Ajoute d'un listener sur le boutton
		 **/
		
		quitter.addActionListener((event)->quitter());
		valider.addActionListener((event)->valider());
		mesBouttons.add(valider);
		mesBouttons.add(quitter);
		return mesBouttons;
	}
	
	/**
	 *Instanciation de la fenetre de creation de compte
	 **/
	
	public static CreerCompte getInstance() {
		if (instance == null)
			instance = new CreerCompte();
		return instance;
	}

	/**
	 *Récupère le menu de création
	 *@return menuCreation
	 **/
	
	public JPanel getMenuCreation() {
		return menuCreation;
	}

	/**
	 *Initialisation du menu de création
	 *@param menuCreation
	 **/
	
	public void setMenuCreation(JPanel menuCreation) {
		this.menuCreation = menuCreation;
	}
	
	/**Permet de quitter quand on appuie sur le boutton
	 **/
	
	private void quitter() {
		FenetreLogin.getInstance().dispose();
		FenetreFond.getInstance().dispose();
		System.exit(0);
	}
	
	private void valider() {
		String passewordTranslate = new String(passeword.getPassword());
		String passewordCopiTranslate = new String(confirmedPasseword.getPassword());
		
		/**On regarde les différentes possiblités et on adapte les messages d'erreur selon l'utilisateur
		 **/
		
		/**
		 *Vérification d'un contenu existent des champs de saisie
		 **/
		
		if((login.getText() != null) && (passewordTranslate != null) && (passewordCopiTranslate !=null) && (adresseMail.getText() != null)) {
			
			/**
			 *Vérification de la concordance des mot de passe
			 **/
			
			if(passewordCopiTranslate.equals(passewordTranslate)) {
				
				/**
				 *Vérification de l'existence de l'utilisateur
				 **/
				
				if (!(personnesDejaInscrite.getInstance().rechercher(login.getText(), passewordTranslate))) {
					if(validateEmailAddress(adresseMail.getText())) {
						if(compteurDeCaseCoche() != null) {
							if(compteurDeCaseCoche().equals("Acteur")) {
								personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().put(login.getText(), new CompteActeur(passewordTranslate, compteurDeCaseCoche(), "ImageProfil/inconnu.jpg", adresseMail.getText()));
								personnesDejaInscrite.getInstance().sauvegarder();
								JOptionPane.showInternalMessageDialog(this, "Bienvenue l'artiste allez-vous login, allez vous login.", "Compte créé", JOptionPane.INFORMATION_MESSAGE);
							} else if(compteurDeCaseCoche().equals("Comedien")) {
								personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().put(login.getText(), new CompteComedien(passewordTranslate, compteurDeCaseCoche(), "ImageProfil/inconnu.jpg", adresseMail.getText()));
								personnesDejaInscrite.getInstance().sauvegarder();
								JOptionPane.showInternalMessageDialog(this, "Bienvenu comédien, allez vous login.", "Compte créé", JOptionPane.INFORMATION_MESSAGE);
							} else if(compteurDeCaseCoche().equals("Chanteur")) {
								personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().put(login.getText(), new CompteChanteur(passewordTranslate, compteurDeCaseCoche(), "ImageProfil/inconnu.jpg", adresseMail.getText()));
								personnesDejaInscrite.getInstance().sauvegarder();
								JOptionPane.showInternalMessageDialog(this, "Bienvenu chanteur, allez vous login.", "Compte créé", JOptionPane.INFORMATION_MESSAGE);
							}
						} 
						else {
							JOptionPane.showInternalMessageDialog(this, "Vous avez trop de talent", "Erreur", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showInternalMessageDialog(this, "Mail invalide", "Erreur", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					
					/**
					 *Génération de fenêtres d'erreurs qu'on ouvre avec le show
					 **/
					
					JOptionPane.showInternalMessageDialog(this, "Cette utilisateur existe déjà", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showInternalMessageDialog(this, "Vous n'avez pas rentré 2 fois le même mot de passe", "Erreur", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showInternalMessageDialog(this, "Vous n'avez pas rentré les champs", "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	/**Permet de vérifier si une seule case talent a été appuyée et renvoie son nom
	 **/
	
	private String compteurDeCaseCoche() {
		int compteur = 0;
		JCheckBox tempon = null;
		if(checkArtiste.isSelected()) {
			tempon = checkArtiste;
			compteur++;
		}
		if(checkChanteur.isSelected()) {
			tempon = checkChanteur;
			compteur++;
		}
		if(checkDanseur.isSelected()) {
			tempon = checkDanseur;
			compteur++;
		}
		System.out.println(compteur);
		if(compteur != 1)
			return null;
		else
			return tempon.getText();
	}
	
	/**
	 *Permet de valider l'adresse e-mail de l'utilisateur
	 *@param votreEmail
	 *	E-mail utilisateur
	 *@return True si e-mail valide
	 **/
	
	public static boolean validateEmailAddress(String votreEmail){
		EmailValidator emailvalidator = EmailValidator.getInstance();
        if(emailvalidator.isValid(votreEmail)) {
            return true;
        } else {
            return false;
        }
	}
}