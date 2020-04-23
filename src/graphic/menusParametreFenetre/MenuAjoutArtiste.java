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

import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;

@SuppressWarnings({ "serial", "deprecation" })
public class MenuAjoutArtiste extends JPanel{
	
	private static MenuAjoutArtiste instance;

	private static final String[] mesTalents = {"Chanteur", "Acteur", "Comedien"};
	private String login, cheminVersImage;
	private JTextField prenom, nom, adresseMail;
	private JTextArea description;
	private JComboBox<String> type;
	private JLabel imageProfil;
	
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
	
	
	private void choixPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    cheminVersImage = selectedFile.getAbsolutePath();
		}
	}
	
	
	private JPanel constructionEtiquetteDroite() {
		JPanel menuDroite = new JPanel(new BorderLayout());
		JPanel tempon1 = new JPanel(new GridLayout(4,2, 0, 10));
		
		//On crée les labels
		
		JLabel nomLabel = new JLabel("Nom :");
		JLabel prenomLabel = new JLabel("Prenom :");
		JLabel adresseMailLabel = new JLabel("Adresse mail :");
		
		//On les centres
		
		nomLabel.setHorizontalAlignment(JLabel.CENTER);
		prenomLabel.setHorizontalAlignment(JLabel.CENTER);
		adresseMailLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//On initialise les endroits de rentré de texte
		
		nom = new JTextField("Nom");
		prenom = new JTextField("Prenom");
		adresseMail = new JTextField("Adresse mail");
		
		//On ajoute dans le bonne ordre
		
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
	
	
	private JPanel constructionBoutton() {
		JPanel panelBouttons = new JPanel(new FlowLayout());
		JButton valider = new JButton("Valider");
		valider.addActionListener((event)->valider());
		panelBouttons.add(valider);
		return panelBouttons;
	}
	
	
	private void valider() {
		if(validateEmailAddress(adresseMail.getText())) {
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().add(new Artiste(description.getText(),cheminVersImage, nom.getText(), prenom.getText(), adresseMail.getText(), (String) type.getSelectedItem()));
			personnesDejaInscrite.getInstance().sauvegarder();
			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"L'artiste " + nom.getText() +" a été ajouté","Artiste ajouté", JOptionPane.INFORMATION_MESSAGE);
			MenuPrincipal.getInstance(login).update();
		} else {
			JMenuItem contenant = new JMenuItem();
			JOptionPane.showMessageDialog(contenant,"Email incorrect","Erreur", JOptionPane.WARNING_MESSAGE);
		}

	}
	
	

	/**
	 * Permet de vérifier si une seule case talent a été appuyée et renvoie son nom
	 **/

	/**
	 * Permet de valider l'adresse e-mail de l'utilisateur
	 * 
	 * @param votreEmail E-mail utilisateur
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
	
	//Ici les getter et les Setters
	
	
	public String getCheminVersImage() {
		return cheminVersImage;
	}

	public void setCheminVersImage(String cheminVersImage) {
		this.cheminVersImage = cheminVersImage;
	}

	public JTextField getPrenom() {
		return prenom;
	}

	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}

	public JTextField getNom() {
		return nom;
	}

	public void setNom(JTextField nom) {
		this.nom = nom;
	}

	public JTextField getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(JTextField adresseMail) {
		this.adresseMail = adresseMail;
	}

	public JTextArea getDescription() {
		return description;
	}

	public void setDescription(JTextArea description) {
		this.description = description;
	}

	public static MenuAjoutArtiste getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutArtiste(login);
		return instance;
	}
}

