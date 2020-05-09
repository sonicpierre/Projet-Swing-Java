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

import control.BDD.Modification;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreParametre;
import graphic.fenetreEnvoieMail.FenetreMail;
import graphic.fenetreEnvoieMail.MenuDeMail;
import graphic.menusParametreFenetre.MenuAjoutAlbum;
import graphic.menusParametreFenetre.MenuAjoutMusique;
import graphic.menusParametreFenetre.MenuAjoutRepresentation;
import graphic.menusParametreFenetre.MenuProfilDescription;


/**
 *<b>MenuPrincipal</b> est la classe qui permet la construction de l'interface du menu principal et ses fonctionnalités
 *@author VIRGAUX Pierre
 *@version 2.0
 **/

@SuppressWarnings("serial")
public class MenuPrincipal extends JTabbedPane{
	
	/**
	 *Déclaration de l'instance du menu principal
	 **/
	
	private static MenuPrincipal instance;
	
	/**
	 Déclaration du login utilisateur
	 **/
	
	private String login;
	
	/**
	 *Déclaration du panel de construction
	 **/
	private JPanel constructionPanel;

	/**
	 *Déclaration de la musique, album, artiste et representations
	 **/
	
	private JScrollPane musique, album, artiste, representation;
	
	/**
	 *Déclaration de l'artiste sélectionné
	 **/
	
	private Artiste artisteSelectionne;
	
	/**
	 *Génère la fenetre de menu principal
	 *@param login
	 *	Login utilisateur
	 **/
	private MenuPrincipal(String login) {
		this.login = login;
		update();
	}
	
	
	/**
	 *Permet de construire les caractéristiques du menu principal
	 *@return Fenetre de menu principal
	 *@see personnesDejaInscrite
	 *@return Fenete de menu principal
	 **/
	private JPanel constructionDuMenuPrincipal() {
		
		/**
		 *Savoir combien d'artiste on affiche sur le panel
		 **/
		
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
	
	/**
	 *Permet de construire la case de label artiste
	 *@param artiste
	 *	Artiste
	 *@return Panel d'informations
	 **/
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
	
	
	/**
	*Permet d'ajouter un listener sur la souris afin d'adapter les actions
	*@see MenuAjoutAlbum
	*@see MenuProfilDescription
	*@see MenuAjoutMusique
	*@see MenuMusique
	*@see MenuPrincipal
	*@see TopMenuDescriptif
	*@see MenuAjoutRepresentation
	*@return Fenetre construite
	**/
	
	private JPanel constructionCase(Artiste artiste) {
		JPanel constructionCase = new JPanel(new FlowLayout());
		ImageIcon monImage = new ImageIcon(new ImageIcon(artiste.getCheminVersImage()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		JLabel maPhoto = new JLabel(monImage);
		maPhoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		JPopupMenu maPopup = new JPopupMenu();
		JMenuItem description = new JMenuItem("Description");
		JMenuItem supprimer = new JMenuItem("Supprimer");
		JMenuItem contacter = new JMenuItem("Contacter");
		/**
		 *Chaque listener est associé à un artiste
		 **/
		
		supprimer.addActionListener((event)->supprimerArtiste(artiste));
		contacter.addActionListener((event)->contacterArtiste(artiste));
		description.addActionListener((event)->{
			MenuProfilDescription.getInstance(login).setArtiste(artiste);
			FenetreParametre.getInstance(login).ajoutParametre();
			});
		maPopup.add(description);
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
						JPanel menuRepresentation = MenuRepresentation.getInstance(login);
						menuRepresentation.setPreferredSize(new Dimension(100, MenuRepresentation.getInstance(login).getCompteur() * 130));
						temporaireRepres.setViewportView(menuRepresentation);
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
	
	/**
	 *Cette méthode permet la mise à jour du contenu fenetre. Ainsi, chaque onglet a un scroll panel auquel on ajoute le panel de l'onglet actif.
	 *Nous pouvons donc définir une taille avec set preferd size cependant il est recommandé d'éviter des onglets de tailles égales pour des questions de présentation
	 *@see MenuMusique
	 *@see MenuRepresentation
	 **/
	
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
		menuRepresentation.setPreferredSize(new Dimension(100, MenuRepresentation.getInstance(login).getCompteur() * 130));
		representation.setViewportView(menuRepresentation);
		
		artiste = new JScrollPane();
		artiste.setPreferredSize(new Dimension(100,100));
		artiste.setViewportView(constructionDuMenuPrincipal());
		
		
		this.add(artiste, "Artiste");
		this.add(musique, "Musique");
		this.add(album, "Albums");
		this.add(representation, "Representations");
	}
	
	/**
	 *Permet de gérer les Pop-up menu lors d'un clic droit sur l'image. Elle supprime aussi l'artiste et met à jour l'interface
	 *@param artiste
	 *	Artiste
	 *@see personnesDejaInscrite
	 *@see Modification
	 **/
	
	private void supprimerArtiste(Artiste artiste) {
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().remove(artiste);
		Modification.getInstance().supprimerArtiste(artiste.hashCode());
		personnesDejaInscrite.getInstance().sauvegarder();
		this.update();
	}
	
	/**
	 *Permet de contacter un artiste
	 *@param artiste
	 *	Artiste
	 **/
	
	private void contacterArtiste(Artiste artiste) {
		MenuDeMail.getInstance().setArtiste(artiste);
		FenetreMail.getInstance().contacter();
	}
	

	/**
	 *Permet de savoir l'artiste qui a été selectionné.
	 *@return artiste qui a été selectionné
	 */

	public Artiste getArtisteSelectionne() {
		return artisteSelectionne;
	}

	/**
	 *Permet de donner une valeur à artiste quand on en a selectionné un.
	 *@param artisteSelectionne
	 *	Artiste selectionné
	 */

	public void setArtisteSelectionne(Artiste artisteSelectionne) {
		this.artisteSelectionne = artisteSelectionne;
	}
	
	/**
	 *Cette fonction permet d'accéder à l'objet MenuPrincipal
	 *@param login 
	 *	Login utilisateur
	 *@return L'objet singleton
	 */
	
	public static MenuPrincipal getInstance(String login) {
		if (instance == null)
			instance = new MenuPrincipal(login);
		return instance;
	}
	
}
