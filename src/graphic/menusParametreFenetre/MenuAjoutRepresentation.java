package graphic.menusParametreFenetre;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

import com.toedter.calendar.JDateChooser;

import control.BDD.Modification;
import control.activite.Representation;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreParametre;
import graphic.menusDeuxiemeFenetre.MenuRepresentation;

/**
 *<b>MenuAjoutRepresentation</b> est la classe qui permet de créer le menu d'ajout de representation d'un artiste
 *@author VIRGAUX Pierre
 *@version 2.0
 **/

@SuppressWarnings("serial")
public class MenuAjoutRepresentation extends JPanel{
	
	/**
	 *Déclaration de l'instance du menu de representation
	 **/
	
	private static MenuAjoutRepresentation instance;
	/**
	 *Déclaration des types de représentations
	 **/
	
	private static final String[] listeRepres = {"Film", "Comedie"};
	
	/**
	 *Déclaration du login utilisateur et de la localisation de l'image
	 **/
	
	private String login, cheminVersImage;
	
	/**
	 *Déclaratio  de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Déclaration de la durée et du titre de la representation
	 **/
	
	private JTextField titre, duree;
	
	/**
	 *Déclaraction de la description utilisateur
	 **/
	
	private JTextArea description;
	
	/**
	 *Déclaration de l'image de profil utilisateur
	 **/
	
	private JLabel imageProfil;
	
	/**
	 *Déclaration du calendrier
	 **/
	
	private JDateChooser calendrier;
	
	/**
	 *Déclaration du type
	 **/
	
	private JComboBox<String> type;
	
	/**
	 *Génère le menu d'ajout representations
	 **/
	
	private MenuAjoutRepresentation(String login) {
		this.login = login;
		this.setLayout(new BorderLayout());
		this.cheminVersImage = "ImageProfil/inconnu.jpg";
		description = new JTextArea("Description du synopsis", 50, 50);
		this.add(constructionPanel(), BorderLayout.NORTH);
		this.add(description, BorderLayout.CENTER);
		this.add(constructionBoutton(), BorderLayout.SOUTH);
	}
	
	/**
	 *Permet de construire des caractéristques de la fenetre d'ajout représentations
	 *@return Fenetre d'ajout
	 **/
	
	private JPanel constructionPanel() {
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
	 *Permet de faire le choix de la photo
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
	 *Permet de construire l'onglet droit de l'interface du menu d'ajout de représentations
	 **/
	
	private JPanel constructionEtiquetteDroite() {
		JPanel menuDroite = new JPanel(new BorderLayout());
		JPanel tempon1 = new JPanel(new GridLayout(3,2, 0, 10));
		
		/**
		 *On crée les labels
		 */
		
		JLabel titreLabel = new JLabel("Titre :");
		JLabel dureeLabel = new JLabel("Nombre spectateur :");
		JLabel typeLabel = new JLabel("Type :");
		
		calendrier = new JDateChooser();
		type = new JComboBox<String>(listeRepres);
		
		/**
		 *On les centre
		 */
		
		titreLabel.setHorizontalAlignment(JLabel.CENTER);
		dureeLabel.setHorizontalAlignment(JLabel.CENTER);
		typeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		/**
		 *On initialise les endroits de rentré de texte
		 */
		
		titre = new JTextField("Nom");
		duree = new JTextField("Duree");
		
		/**
		 *On ajoute dans le bonne ordre
		 */
		
		tempon1.add(titreLabel);
		tempon1.add(titre);
		tempon1.add(dureeLabel);
		tempon1.add(duree);
		tempon1.add(typeLabel);
		tempon1.add(type);

		
		menuDroite.add(tempon1, BorderLayout.CENTER);
		menuDroite.add(calendrier, BorderLayout.SOUTH);
		return menuDroite;
	}
	
	/**
	 *Construction du bouton de validation de choix
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
	 *Permet de valider les informations de représentation
	 *@see personnesDejaInscrite
	 *@see Representation
	 *@see Modification
	 *@see MenuRepresentation
	 **/
	
	private void valider() {
		/**
		 *Format de date jour/mois/année
		 **/
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		int dureeInt = Integer.parseInt(duree.getText());
    		
    		/**
    		 *Test du calendrier de représenttion et si non vide affichage des informations
    		 **/
    		
		if(calendrier.getDate() != null) {
			Representation maRepresentation = new Representation(titre.getText(), duree.getText(), cheminVersImage, dateFormat.format(calendrier.getDate()), (String) type.getSelectedItem());
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeRepresentations().add(maRepresentation);
			personnesDejaInscrite.getInstance().sauvegarder();
			if(maRepresentation.getType().equals("Film"))
				Modification.getInstance().insererFilm(maRepresentation.hashCode(), maRepresentation.getTitre(), calendrier.getY());
			if(maRepresentation.getType().equals("Comedie"))
				Modification.getInstance().insererSpectacle(maRepresentation.hashCode(), maRepresentation.getTitre(), calendrier.getY(), dureeInt);
			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"La représentation " + titre.getText(),"Représentation ajouté", JOptionPane.INFORMATION_MESSAGE);
			MenuRepresentation.getInstance(login).update();
			FenetreParametre.getInstance(login).dispose();
			
			/**
			 *Affichage message en cas de calendrier de représentations vide
			 **/
			
		} else {
			JOptionPane.showInternalMessageDialog(this, "Vous n'avez pas mis de date", "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}
		
    	} catch (NumberFormatException e){
			JOptionPane.showInternalMessageDialog(this, "Vous n'avez pas mis un chiffre en durée", "Erreur",
					JOptionPane.WARNING_MESSAGE);
    	}
	}
	

	/**
	 *Récupère le titre
	 *@return Titre de la représentation
	 **/
	
	public JTextField getTitre() {
		return titre;
	}
	
	/**
	 *Récupère l'artiste
	 *@return Artiste
	 **/
	
	public Artiste getArtiste() {
		return artiste;
	}
	
	/**
	 *Initialisation de l'artiste
	 *@param artiste
	 *	Artiste
	 **/
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	/**
	 *Initialisation du titre
	 *@param titre
	 *	Titre de la représentation
	 **/
	
	public void setTitre(JTextField titre) {
		this.titre = titre;
	}
	
	/**
	 *Récupère l'image de profil
	 *@return Photo de profil
	 **/
	
	public JLabel getImageProfil() {
		return imageProfil;
	}
	
	/**
	 *Initialisation de l'image de profil utilisateur
	 *@param imageProfil
	 *	Photo de profil utilisateur
	 **/
	
	public void setImageProfil(JLabel imageProfil) {
		this.imageProfil = imageProfil;
	}
	
	/**
	 *Récupère la localisation de l'image
	 *@return Chemin de l'image
	 **/
	
	public String getCheminVersImage() {
		return cheminVersImage;
	}
	
	/**
	 *Initialisation du chemin vers l'image
	 *@param cheminVersImage
	 *	Localisation de l'image
	 **/
	
	public void setCheminVersImage(String cheminVersImage) {
		this.cheminVersImage = cheminVersImage;
	}

	
	/**
	 *Récupère la description utilisateur
	 *@return Description
	 **/
	
	public JTextArea getDescription() {
		return description;
	}
	
	/**
	 *Initialisation de la description de l'utilisateur
	 *@param description
	 *	Description utilisateur
	 **/
	
	public void setDescription(JTextArea description) {
		this.description = description;
	}
	
	/**
	 *Instanciation du menu d'ajout de représentation
	 *@param login
	 *	LOgin utilisateur
	 *@return Menu de representation
	 **/
	
	public static MenuAjoutRepresentation getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutRepresentation(login);
		return instance;
	}
}
