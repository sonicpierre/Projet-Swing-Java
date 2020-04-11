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
import control.personne.Compte;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;

@SuppressWarnings({ "serial", "deprecation" })
public class CreerCompte extends JPanel{

	private static CreerCompte instance;
	
	//Elements dont on a besoin de partout... Voilà voilà
	JPanel menuCreation;//PANEL D'AJOUT FENETRE QU'ON RENVOIE 
	JTextField login;
	JPasswordField passeword;
	JPasswordField confirmedPasseword;
	JTextField adresseMail;
	JCheckBox checkArtiste;
	JCheckBox checkChanteur;
	JCheckBox checkDanseur;
	
	private CreerCompte() {
		menuCreation = new JPanel(new BorderLayout());
		menuCreation.setBackground(new Color(200, 100, 100));
		menuCreation.add(InitialisationDuMenu(), BorderLayout.CENTER);
		menuCreation.add(InitDesBouttons(), BorderLayout.SOUTH);
	}
	
	
	private JPanel InitialisationDuMenu() {
		JPanel mesEntre = new JPanel(new GridLayout(9,2,25,10));
		login = new JTextField("Login");
		confirmedPasseword = new JPasswordField("Mot de Passe");
		adresseMail = new JTextField("...@gmail.com");
		passeword = new JPasswordField("Mot de Passe");
		
		JLabel loginTexte = new JLabel("Login :");
		loginTexte.setHorizontalAlignment(JLabel.CENTER);
		JLabel motDePasse = new JLabel("Mot de Passe :");
		motDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel addresseMail = new JLabel("Adresse mail :");
		addresseMail.setHorizontalAlignment(JLabel.CENTER);
		JLabel confirmedMotDePasse = new JLabel("Confirmer Mot de Passe :");
		confirmedMotDePasse.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel choixCategorie = new JPanel(new GridLayout(1,3));
		checkArtiste = new JCheckBox("Artiste");
		checkChanteur = new JCheckBox("Chanteur");
		checkDanseur = new JCheckBox("Danseur");
		choixCategorie.add(checkArtiste);
		choixCategorie.add(checkChanteur);
		choixCategorie.add(checkDanseur);
		
		//On ajoute au Panel les éléments pour saisir mot de pass et login.
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
	
	private JPanel InitDesBouttons() {
		JPanel mesBouttons = new JPanel(new FlowLayout(FlowLayout.CENTER,20, 25));
		JButton valider = new JButton("Valider");//DOIT VERIFIER L'EXISTENCE DE COMPTE, LA SAISIE DES CHAMPS, LES CHAMPS DE SAISIE NON VIDES
		JButton quitter = new JButton("Quitter");
		valider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		quitter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//On ajoute un listener sur le boutton
		quitter.addActionListener((event)->quitter());
		valider.addActionListener((event)->valider());
		mesBouttons.add(valider);
		mesBouttons.add(quitter);
		return mesBouttons;
	}
	
	public static CreerCompte getInstance() {
		if (instance == null)
			instance = new CreerCompte();
		return instance;
	}


	public JPanel getMenuCreation() {
		return menuCreation;
	}


	public void setMenuCreation(JPanel menuCreation) {
		this.menuCreation = menuCreation;
	}
	
	//Permet de quitter quand on appuie sur le boutton
	private void quitter() {
		FenetreLogin.getInstance().dispose();
		FenetreFond.getInstance().dispose();
		System.exit(0);
	}
	
	private void valider() {
		String passewordTranslate = new String(passeword.getPassword());
		String passewordCopiTranslate = new String(confirmedPasseword.getPassword());
		
		//On regarde les différentes possiblités et on adapte les messages d'erreur en se rappelant que l'utilisateur est fourbe !!
		
		if((login.getText() != null) && (passewordTranslate != null) && (passewordCopiTranslate !=null) && (adresseMail.getText() != null)) {//VERIFIER SI TOUS LES CHAMPS CONTEINNENT QUELQUE CHOSE
			if(passewordCopiTranslate.equals(passewordTranslate)) {//VERIFIE LA CONCORDANCE DU MDP
				if (!(personnesDejaInscrite.getInstance().rechercher(login.getText(), passewordTranslate))) {//VERIFICATION DE L'EXISTENCE
					if(validateEmailAddress(adresseMail.getText())) {
						if(compteurDeCaseCoche() != null) {
							personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().put(login.getText(), new Compte(passewordTranslate, compteurDeCaseCoche(), null));
							personnesDejaInscrite.getInstance().sauvegarder();
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
					JOptionPane.showInternalMessageDialog(this, "Cette utilisateur existe déjà", "Erreur", JOptionPane.WARNING_MESSAGE);//GENRATION DE FENETRE D'ERREUR QU'ON OUVRE AVEC LE SHOW 
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
	
	//Permet de vérifier si une seule case a été appuyé et renvoie son nom
	
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
	
	public static boolean validateEmailAddress(String votreEmail){
		EmailValidator emailvalidator = EmailValidator.getInstance();
        if(emailvalidator.isValid(votreEmail)) {
            return true;
        } else {
            return false;
        }
	}
}