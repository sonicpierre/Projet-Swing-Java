package graphic.menusDeuxiemeFenetre;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;
import graphic.menusParametreFenetre.MenuAjoutRepresentation;
import graphic.menusParametreFenetre.MenuProfilDescription;

@SuppressWarnings("serial")
public class MenuPrincipal extends JTabbedPane{

	private static MenuPrincipal instance;
	
	private String login;
	private JPanel constructionPanel;
	private JScrollPane musique, album, artiste, representation;
	private Artiste artisteSelectionne;
	
	
	private MenuPrincipal(String login) {
		this.login = login;
		update();
	}
	
	
	private JPanel constructionDuMenuPrincipal() {
		int nombreDeLigne = personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().size();
		if(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().isEmpty())
			constructionPanel = new JPanel();
		else {
			constructionPanel = new JPanel(new GridLayout(nombreDeLigne%4 + 1, 4, 10, 20));
			for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
				constructionPanel.add(constructionCase(monArtiste));
		}
		return constructionPanel;
	}
	
	
	private JPanel constructionLabelCase(Artiste artiste) {
		JPanel panelLabel = new JPanel(new BorderLayout());
		
		JLabel nomArtiste = new JLabel(artiste.getNom());
		JLabel prenomArtiste = new JLabel(artiste.getPrenom());
		JLabel typeArtiste = new JLabel(artiste.getType());
		
		panelLabel.add(nomArtiste, BorderLayout.NORTH);
		panelLabel.add(prenomArtiste, BorderLayout.CENTER);
		panelLabel.add(typeArtiste, BorderLayout.SOUTH);
		
		return panelLabel;
	}
	
	
	private JPanel constructionCase(Artiste artiste) {
		JPanel constructionCase = new JPanel(new FlowLayout());
		ImageIcon monImage = new ImageIcon(new ImageIcon(artiste.getCheminVersImage()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		JLabel maPhoto = new JLabel(monImage);
		maPhoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JPopupMenu maPopup = new JPopupMenu();
		JMenuItem supprimer = new JMenuItem("Supprimer");
		JMenuItem contacter = new JMenuItem("Contacter");
		supprimer.addActionListener((event)->supprimerArtiste(artiste));
		contacter.addActionListener((event)->contacterArtiste(artiste));
		maPopup.add(supprimer);
		maPopup.add(contacter);
		maPhoto.setComponentPopupMenu(maPopup);
				
		
		maPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					setArtisteSelectionne(artiste);
					if(artiste.getType().equals("Chanteur")) {
						MenuAjoutAlbum.getInstance(login).setArtiste(artiste);
						MenuProfilDescription.getInstance(login).setArtiste(artiste);
						MenuAjoutMusique.getInstance(login).update();
						MenuMusique.getInstance(login).update();
						MenuPrincipal.getInstance(login).removeAll();
						JScrollPane temporaireMusique = new JScrollPane();
						temporaireMusique.setViewportView(MenuMusique.getInstance(login));
						MenuPrincipal.getInstance(login).add(temporaireMusique,"Musique de " + artiste.getNom());
						MenuPrincipal.getInstance(login).validate();
						TopMenuDescriptif.getInstance(login).setArtiste(artiste);
						TopMenuDescriptif.getInstance(login).updateVersChanteur();
					}
					if(artiste.getType().equals("Comedien") || artiste.getType().equals("Acteur")) {
						MenuAjoutRepresentation.getInstance(login).setArtiste(artiste);
						MenuRepresentation.getInstance(login).setArtiste(artiste);
						MenuRepresentation.getInstance(login).update();
						MenuPrincipal.getInstance(login).removeAll();
						JScrollPane temporaireRepres = new JScrollPane();
						temporaireRepres.setViewportView(MenuRepresentation.getInstance(login));
						MenuPrincipal.getInstance(login).add(temporaireRepres,"Représentation de " + artiste.getNom());
						MenuPrincipal.getInstance(login).validate();
						TopMenuDescriptif.getInstance(login).setArtiste(artiste);
						TopMenuDescriptif.getInstance(login).updateVersActeurComedien();
					}
					
				}
					
			}

		});
		constructionCase.add(maPhoto);
		constructionCase.add(constructionLabelCase(artiste));
		
		return constructionCase;
	}
	
	public void update() {
		this.removeAll();
		
		musique = new JScrollPane();
		JPanel menuMusique = MenuMusique.getInstance(login);
		musique.setPreferredSize(new Dimension(100, MenuMusique.getInstance(login).getNombreDeMusique() * 160));
		musique.setViewportView(menuMusique);
		
		album = new JScrollPane();
		JPanel menuAlbum = MenuAlbum.getInstance(login);
		album.setPreferredSize(new Dimension(100, MenuMusique.getInstance(login).getNombreDeMusique() * 160));
		album.setViewportView(menuAlbum);
		
		representation = new JScrollPane();
		JPanel menuRepresentation = MenuRepresentation.getInstance(login);
		menuRepresentation.setPreferredSize(new Dimension(100, MenuMusique.getInstance(login).getNombreDeMusique() * 100));
		representation.setViewportView(menuRepresentation);
		
		artiste = new JScrollPane();
		artiste.setPreferredSize(new Dimension(100,100));
		artiste.setViewportView(constructionDuMenuPrincipal());
		
		
		this.add(artiste, "Artiste");
		this.add(musique, "Musique");
		this.add(album, "Albums");
		this.add(representation, "Representations");
	}
	
	private void supprimerArtiste(Artiste artiste) {
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().remove(artiste);
		this.update();
	}
	
	private void contacterArtiste(Artiste artiste) {
		MenuDeMail.getInstance().setArtiste(artiste);
		FenetreMail.getInstance().contacter();
	}
	
	
	public static MenuPrincipal getInstance(String login) {
		if (instance == null)
			instance = new MenuPrincipal(login);
		return instance;
	}


	public Artiste getArtisteSelectionne() {
		return artisteSelectionne;
	}


	public void setArtisteSelectionne(Artiste artisteSelectionne) {
		this.artisteSelectionne = artisteSelectionne;
	}
	
}
