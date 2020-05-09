package graphic.menusParametreFenetre;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.validator.EmailValidator;

import control.BDD.Modification;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;


/**
 *<b>MenuAjoutArtiste</b> est la classe qui crée l'interface dédiée à l'artiste quelque soit son talent
 *@author BUISSON-CHAVOT Julien
 *@version 2.0
 **/

@SuppressWarnings({ "serial", "deprecation" })
public class MenuAjoutArtiste extends JPanel{
	
	/**
	 *Déclaration del'instance du menu d'ajout artiste
	 **/
	
	private static MenuAjoutArtiste instance;
	
	/**
	 *Déclaration des talents possibles pour les artistes
	 **/
	
	private static final String[] mesTalents = {"Chanteur", "Acteur", "Comedien"};
	
	/**
	 *Déclaration du login et du chemin vers l'image de l'artiste
	 **/
	
	private String login, cheminVersImage;
	
	/**
	 *Déclaration du nom, prenom et e-mail artiste
	 **/
	
	private JTextField prenom, nom, adresseMail;
	
	/**
	 *Déclaration de la zone de texte de description artiste
	 **/
	
	private JTextArea description;
	
	/**
	 *Talent possible des artistes
	 **/
	
	private JComboBox<String> type;
	
	/**
	 *Déclaration de l'image de photo utilisateur
	 **/
	
	private JLabel imageProfil;
	
	/**
	 *Construction du menu artiste
	 *@param login
	 *	LOgin utilisateur
	 **/
	
	private MenuAjoutArtiste(String login) {
		this.login = login;
		this.type = new JComboBox<String>(mesTalents);
		this.cheminVersImage = "ImageProfil/inconnu.jpg";
		this.setLayout(new BorderLayout());
		this.add(constructionArtiste(), BorderLayout.NORTH);
		description = new JTextArea("Description de l'artiste", 50, 50);
		this.add(description, BorderLayout.CENTER);
		this.add(constructionBoutton(), BorderLayout.SOUTH);
	}
	
	/**
	 *Construction des caractéristques de la fenete dédiée à l'artiste
	 *@return Menu d'artiste
	 **/
	
	private JPanel constructionArtiste() {
		JPanel menuTerminal = new JPanel(new BorderLayout());
		ImageIcon monImage = new ImageIcon(new ImageIcon(cheminVersImage).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		imageProfil = new JLabel(monImage);
		imageProfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imageProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1)
					choixPhoto();
			}
		});
		
		menuTerminal.add(imageProfil, BorderLayout.WEST);
		menuTerminal.add(constructionEtiquetteDroite(), BorderLayout.CENTER);
		return menuTerminal;
	}
	
	/**
	 *Permet de faire le choix de la photo de l'artiste
	 **/
	
	private void choixPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    cheminVersImage = selectedFile.getAbsolutePath();
		}
	}
	
	/**
	 *Permet la création de l'onglet droit de la fenetre
	 **/
	
	private JPanel constructionEtiquetteDroite() {
		JPanel menuDroite = new JPanel(new BorderLayout());
		JPanel tempon1 = new JPanel(new GridLayout(4,2, 0, 10));
		
		/**
		 *On crée les labels
		 */
		
		JLabel nomLabel = new JLabel("Nom :");
		JLabel prenomLabel = new JLabel("Prenom :");
		JLabel adresseMailLabel = new JLabel("Adresse mail :");
		
		/**
		 *On les centre
		 */
		
		nomLabel.setHorizontalAlignment(JLabel.CENTER);
		prenomLabel.setHorizontalAlignment(JLabel.CENTER);
		adresseMailLabel.setHorizontalAlignment(JLabel.CENTER);
		
		/**
		 *On initialise les endroits de rentré de texte
		 */
		
		nom = new JTextField("Nom");
		prenom = new JTextField("Prenom");
		adresseMail = new JTextField("Adresse mail");
		
		/**
		 *On ajoute dans le bonne ordre
		 */
		
		tempon1.add(nomLabel);
		tempon1.add(nom);
		tempon1.add(prenomLabel);
		tempon1.add(prenom);
		tempon1.add(adresseMailLabel);
		tempon1.add(adresseMail);
		
		menuDroite.add(tempon1, BorderLayout.CENTER);
		menuDroite.add(type, BorderLayout.SOUTH);
		
		return menuDroite;
	}
	
	/**
	 *Construit le bouton de validation
	 *@return Bouton valider
	 **/
	
	private JPanel constructionBoutton() {
		JPanel panelBouttons = new JPanel(new FlowLayout());
		JButton valider = new JButton("Valider");
		valider.addActionListener((event)->valider());
		panelBouttons.add(valider);
		return panelBouttons;
	}
	
	/**
	 *Permet la validation du choix de l'utilisateur
	 *@see personnesDejaInscrite
	 *@see MenuPrincipal
	 *@see Modification
	 **/
	
	private void valider() {
		if(validateEmailAddress(adresseMail.getText())) {
			Artiste monArtiste = new Artiste(description.getText(),cheminVersImage, nom.getText(), prenom.getText(), adresseMail.getText(), (String) type.getSelectedItem());
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().add(monArtiste);
			personnesDejaInscrite.getInstance().sauvegarder();
			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"L'artiste " + nom.getText() +" a été ajouté","Artiste ajouté", JOptionPane.INFORMATION_MESSAGE);
			MenuPrincipal.getInstance(login).update();
			
			System.out.println(monArtiste.getDescription());
			Modification.getInstance().insererArtiste(monArtiste.hashCode(), monArtiste.getNom() + " " + monArtiste.getPrenom(), monArtiste.getDescription(), monArtiste.getType());
		} else {
			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"Email incorrect","Erreur", JOptionPane.WARNING_MESSAGE);
		}

	}
	

	/**
	 *Permet de valider l'adresse e-mail de l'utilisateur
	 *@param votreEmail 
	 * E-mail utilisateur
	 * @return True si e-mail valide
	 **/
	
	public static boolean validateEmailAddress(String votreEmail) {
		EmailValidator emailvalidator = EmailValidator.getInstance();
		if (emailvalidator.isValid(votreEmail)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *Récupère le chemin vers l'image
	 *@return Localisation image
	 **/
	
	public String getCheminVersImage() {
		return cheminVersImage;
	}
	
	/**
	 *Initialisation du chemin vers l'image
	 *@param cheminVersImage
	 *	Localisation del'image
	 **/
	
	public void setCheminVersImage(String cheminVersImage) {
		this.cheminVersImage = cheminVersImage;
	}
	
	/**
	 *Récupère le prenom de l'artiste
	 *@return Prenom artiste
	 **/
	
	public JTextField getPrenom() {
		return prenom;
	}
	
	/**
	 *Initialisation du prenom de l'artiste
	 *@param prenom
	 *	Prenom artiste
	 **/
	
	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}
	
	/**
	 *Récupère le nom de l'artiste
	 *@return Nom artiste
	 **/
	
	public JTextField getNom() {
		return nom;
	}	
	
	/**
	 *Initialisation du nom de lartiste
	 *@param nom
	 *	Nom artiste
	 **/
	
	public void setNom(JTextField nom) {
		this.nom = nom;
	}
	
	/**
	 *Récupère l'e-mail utilisateur
	 *@return E-mail
	 **/
	
	public JTextField getAdresseMail() {
		return adresseMail;
	}
	
	/**
	 *Initialisation de l'e-mail utilisateur
	 *@param adresseMail
	 *	E-mail utilisateur
	 **/
	
	public void setAdresseMail(JTextField adresseMail) {
		this.adresseMail = adresseMail;
	}
	
	/**
	 *Récupère la description de l'artiste
	 *@return Description artiste
	 **/
	
	public JTextArea getDescription() {
		return description;
	}
	
	/**
	 *Initialisation de la description de l'artiste
	 *@param description	
	 *	Description artiste
	 **/
	
	public void setDescription(JTextArea description) {
		this.description = description;
	}
	
	/**
	 *Instanciation du menu d'ajout artiste
	 *@param login
	 *	Login utilisateur
	 *@return Menu d'ajout artiste
	 **/
	
	public static MenuAjoutArtiste getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutArtiste(login);
		return instance;
	}
}

