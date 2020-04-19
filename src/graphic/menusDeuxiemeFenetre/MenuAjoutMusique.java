package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;
import control.musique.Titre;
import control.personne.Artiste;

@SuppressWarnings("serial")
public class MenuAjoutMusique extends JPanel{
	
	private static MenuAjoutMusique instance;
	
	private String login;
	private Artiste artiste;
	private JTextField nouveauNom;
	private String cheminVersMusique;
	private JComboBox<String> listeAlbum;
	
	private MenuAjoutMusique(String login) {
		this.login = login;
		this.artiste = null;
		this.setLayout(new FlowLayout());
		this.add(panelChoixMusique());
	}
	
	private JPanel panelChoixMusique() {
		JPanel monPanel = new JPanel();
		JLabel etiquette = new JLabel("Nom musique");
		JButton choixFichier = new JButton("Browse");
		JButton valider = new JButton("Valider");
		nouveauNom = new JTextField("Titre musique");
		choixFichier.addActionListener((event)->choixFichier());
		valider.addActionListener((event)->valider());
		constructionListeAlbum();
		monPanel.add(etiquette);
		monPanel.add(nouveauNom);
		monPanel.add(listeAlbum);
		monPanel.add(choixFichier);
		monPanel.add(valider);
		
		return monPanel;
	}
	
	
	
	private void constructionListeAlbum() {
		List<String> listeTitreAlbum = new ArrayList<String>();
		if(artiste != null) {
			for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums()) {
				listeTitreAlbum.add(monAlbum.getTitre());
			}
			int index = 0;
			String[] listeConstruction = new String[listeTitreAlbum.size()];
			for(String monTitre : listeTitreAlbum) {
				listeConstruction[index] = monTitre;
				index++;
			}
			this.listeAlbum = new JComboBox<String>(listeConstruction);
		} else {
			this.listeAlbum = new JComboBox<String>();
		}
	}
	
	public void update() {
		artiste = MenuPrincipal.getInstance(login).getArtisteSelectionne();
		System.out.println(artiste);
		this.removeAll();
		this.add(panelChoixMusique());
		this.validate();
	}
	
	private void valider() {
		boolean passage = false;
		for(Album monAlbum : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getMaListeDeAlbums()) {
			if(monAlbum.getTitre().equals(listeAlbum.getSelectedItem())) {
				monAlbum.getChansonsDelAlbum().add(new Titre(nouveauNom.getText(), cheminVersMusique));
				passage = true;
				break;
			}
		}
		if (passage)
			MenuMusique.getInstance(login).update();
		else {
			MenuItem contenant = new MenuItem();
			JOptionPane.showInputDialog(contenant,"Champs incorrect !");
		}
	}
	
	private void choixFichier() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    cheminVersMusique = selectedFile.getAbsolutePath();
		}
	}
	
	public static MenuAjoutMusique getInstance(String login) {
		if (instance == null)
			instance = new MenuAjoutMusique(login);
		return instance;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
}
