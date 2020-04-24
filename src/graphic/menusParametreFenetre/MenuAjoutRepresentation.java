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

import control.activite.Representation;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;

@SuppressWarnings("serial")
public class MenuAjoutRepresentation extends JPanel{
	
	private static MenuAjoutRepresentation instance;

	private String login, cheminVersImage;
	private Artiste artiste;
	private JTextField titre, duree;
	private JTextArea description;
	private JLabel imageProfil;
	
	private MenuAjoutRepresentation(String login) {
		this.login = login;
		this.setLayout(new BorderLayout());
		this.cheminVersImage = "ImageProfil/inconnu.jpg";
		description = new JTextArea("Description du synopsis", 50, 50);
		this.add(constructionPanel(), BorderLayout.NORTH);
		this.add(description, BorderLayout.CENTER);
		this.add(constructionBoutton(), BorderLayout.SOUTH);
	}
	
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
		JPanel tempon1 = new JPanel(new GridLayout(2,2, 0, 10));
		
		//On crée les labels
		
		JLabel titreLabel = new JLabel("Nom :");
		JLabel dureeLabel = new JLabel("Duree :");

		
		//On les centres
		
		titreLabel.setHorizontalAlignment(JLabel.CENTER);
		dureeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//On initialise les endroits de rentré de texte
		
		titre = new JTextField("Nom");
		duree = new JTextField("Duree");
		
		//On ajoute dans le bonne ordre
		
		tempon1.add(titreLabel);
		tempon1.add(titre);
		tempon1.add(dureeLabel);
		tempon1.add(duree);

		
		menuDroite.add(tempon1, BorderLayout.CENTER);
		
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

		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeRepresentations().add(new Representation(titre.getText(), duree.getText(), cheminVersImage));
		personnesDejaInscrite.getInstance().sauvegarder();
		JMenuItem contenant = new JMenuItem();
		JOptionPane.showMessageDialog(contenant,"La représentation " + titre.getText(),"Représentation ajouté", JOptionPane.INFORMATION_MESSAGE);
		MenuPrincipal.getInstance(login).update();
	}
	
	
	
	//Ici les getter et les Setters
	
	


	public JTextField getTitre() {
		return titre;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public void setTitre(JTextField titre) {
		this.titre = titre;
	}

	public JLabel getImageProfil() {
		return imageProfil;
	}

	public void setImageProfil(JLabel imageProfil) {
		this.imageProfil = imageProfil;
	}

	public String getCheminVersImage() {
		return cheminVersImage;
	}

	public void setCheminVersImage(String cheminVersImage) {
		this.cheminVersImage = cheminVersImage;
	}


	public JTextArea getDescription() {
		return description;
	}

	public void setDescription(JTextArea description) {
		this.description = description;
	}

	public static MenuAjoutRepresentation getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutRepresentation(login);
		return instance;
	}
}
