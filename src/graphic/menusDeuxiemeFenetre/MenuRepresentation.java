package graphic.menusDeuxiemeFenetre;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import control.BDD.Modification;
import control.activite.Representation;
import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreFond;

@SuppressWarnings("serial")

/**
 *<b>MenuRepresentation</b> est la classe qui permet de gérer les actions possibles sur les representations de l'artiste
 *@author BUISSON-CHAVOT Julien
 *@version 2.0
 **/

public class MenuRepresentation extends JPanel{
	
	/**
	 *Déclaration de l'instance du menu de representation
	 **/
	
	private static MenuRepresentation instance;
	
	/**
	 *Déclaration de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Déclaration du login 
	 **/
	
	private String login;
	
	/**
	 *Déclaration du compteur
	 **/
	
	private int compteur;
	
	/**
	 *Construit le menu de representation de l'artiste
	 *@param login
	 *	LOgin de l'artiste
	 **/
	
	public MenuRepresentation(String login) {
		this.login = login;
		constructionPanel();
	}
	
	/**
	 *Permet la création de la fenetre du menu de representations avec les caractéristiques assiciées
	 *@see personnesDejaInscrite
	 *@see Representation
	 **/
	
	private void constructionPanel() {
		compteur = 0;
		if(artiste == null) {
			for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
				if(monArtiste.getType().equals("Acteur") || monArtiste.getType().equals("Comedien"))
					compteur += monArtiste.getMaListeDeRepresentations().size();
			
			this.setLayout(new GridLayout(compteur, 1));
			
			for(Artiste monArtiste : personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste())
				if(monArtiste.getType().equals("Acteur") || monArtiste.getType().equals("Comedien"))
					for(Representation maRepresentation : monArtiste.getMaListeDeRepresentations())
						this.add(constructionCase(artiste, maRepresentation));
		} else {
			compteur = artiste.getMaListeDeRepresentations().size();
			this.setLayout(new GridLayout(compteur, 1));

			if(artiste.getType().equals("Acteur") || artiste.getType().equals("Comedien"))
				for(Representation maRepresentation : artiste.getMaListeDeRepresentations())
					this.add(constructionCase(artiste, maRepresentation));
		}
		this.validate();
	}
	
	/**
	 *Permet la construction de l'interface modifiée
	 *@param artiste
	 *	Artiste
	 *@param marepres
	 *	Representation
	 *@return Interface modifiée
	 **/
	private JPanel constructionCase(Artiste artiste, Representation marepres) {
		JPanel constructionCase = new JPanel(new FlowLayout());
		ImageIcon monImage = new ImageIcon(new ImageIcon(marepres.getCheminVersImage()).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));//REDIMENSIUON IMG 150 PAR 150
		JLabel maPhoto = new JLabel(monImage);
		
		if(artiste != null) {
			maPhoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			JPopupMenu maPopup = new JPopupMenu();
			JMenuItem supprimer = new JMenuItem("Supprimer");
			JMenuItem changerImage = new JMenuItem("Changer imager");
			
			supprimer.addActionListener(e -> supprimerRepres(artiste, marepres));
			maPopup.add(supprimer);
			maPopup.add(changerImage);
			maPhoto.setComponentPopupMenu(maPopup);
		}
		constructionCase.add(maPhoto);
		constructionCase.add(constructionLabel(marepres));
		
		return constructionCase;
	}
	
	/**
	 *Permet la construction des labels de représentation
	 *@param masrepres
	 *	Representation
	 *@return Labels de representation
	 **/
	
	private JPanel constructionLabel(Representation marepres) {
		JPanel lesLabels = new JPanel(new GridLayout(3,2));
		JLabel labelTitre = new JLabel(marepres.getTitre());
		JLabel labelDuree = new JLabel(marepres.getDuree());
		JLabel labelDate = new JLabel(marepres.getDate().toString());
		
		lesLabels.add(labelTitre);
		lesLabels.add(labelDuree);
		lesLabels.add(labelDate);
		
		return lesLabels;
	}
	
	/**
	 *Permet la suppression de representation de la fenetre
	 *@param monArtiste
	 *	Artiste pour lequel on supprime la representation
	 *@param maRepres
	 *	Representation à supprimer
	 *@see personnesDejaInscrite
	 *@see Modification
	 *@see FenetreFond
	 **/
	
	private void supprimerRepres(Artiste monArtiste, Representation maRepres) {
		personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(monArtiste).getMaListeDeRepresentations().remove(maRepres);
		personnesDejaInscrite.getInstance().sauvegarder();
		if(maRepres.getType().equals("Film"))
			Modification.getInstance().supprimerFilm(maRepres.hashCode());
		if(maRepres.getType().equals("Comedie"))
			Modification.getInstance().supprimerSpectacle(maRepres.hashCode());
		FenetreFond.getInstance().retourEtatInitial(login);
	}
	
	/**
	 *Permet la mise à jour de la fenetre de representation
	 **/
	
	public void update() {
		this.removeAll();
		constructionPanel();
		
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
	 *Instanciation du menu de représentation 
	 *@param login
	 *	Login utilisateur
	 *@return Menu de representation
	 **/
	
	public static MenuRepresentation getInstance(String login) {
		if (instance == null)
			instance = new MenuRepresentation(login);
		return instance;
	}
	
	/**
	 *Récupère le compteur
	 *@return Compteur
	 **/
	
	public int getCompteur() {
		return compteur;
	}
	
	/**
	 *Initailisation du compteur
	 *@param compteur
	 *	Compteur
	 **/
	
	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}
	
}
