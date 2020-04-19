package graphic.menusParametreFenetre;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;

@SuppressWarnings("serial")
public class MenuAjoutArtiste extends JPanel{
	
	private static MenuAjoutArtiste instance;

	private static final String[] mesTalents = {"Chanteur", "Acteur", "Comedien"};
	private String login;
	private String cheminVersImage;
	private JTextField prenom;
	private JTextField nom;
	private JTextField adresseMail;
	private JTextArea description;
	private JComboBox<String> type;
	
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
		JLabel imageProfil = new JLabel(monImage);
		imageProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				choixPhoto();
			}
		});
		menuTerminal.add(imageProfil, BorderLayout.WEST);
		menuTerminal.add(constructionEtiquetteDroite(), BorderLayout.EAST);
		return menuTerminal;
	}
	
	private JPanel constructionEtiquetteDroite() {
		JPanel menuDroite = new JPanel(new GridLayout(5,1));
		JPanel tempon1 = new JPanel(new FlowLayout());
		JPanel tempon2 = new JPanel(new FlowLayout());
		JPanel tempon3 = new JPanel(new FlowLayout());
		
		JLabel nomLabel = new JLabel("Nom :");
		JLabel prenomLabel = new JLabel("Prenom :");
		JLabel adresseMailLabel = new JLabel("Adresse mail :");
		nom = new JTextField("Nom");
		prenom = new JTextField("Prenom");
		adresseMail = new JTextField("Adresse mail");
		
		tempon1.add(nomLabel, FlowLayout.LEFT);
		tempon1.add(nom);
		tempon2.add(prenomLabel, FlowLayout.LEFT);
		tempon2.add(prenom);
		tempon3.add(adresseMailLabel, FlowLayout.LEFT);
		tempon3.add(adresseMail);
		
		menuDroite.add(tempon1, FlowLayout.LEFT);
		menuDroite.add(tempon2);
		menuDroite.add(tempon3);
		menuDroite.add(type);
		
		return menuDroite;
	}
	
	private void valider() {
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().add(new Artiste(description.getText(),cheminVersImage, nom.getText(), prenom.getText(), adresseMail.getText(), (String) type.getSelectedItem()));
		personnesDejaInscrite.getInstance().sauvegarder();
		MenuPrincipal.getInstance(login).update();

	}
	
	//PROBLEME MAJEUR ICI AU NIVEAU DE NOM ET PRENOM !!!
	
	private void choixPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    cheminVersImage = selectedFile.getAbsolutePath();
		}
	}
	
	
	private JPanel constructionBoutton() {
		JPanel panelBouttons = new JPanel(new FlowLayout());
		JButton valider = new JButton("Valider");
		valider.addActionListener((event)->valider());
		panelBouttons.add(valider);
		return panelBouttons;
	}
	
	
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

