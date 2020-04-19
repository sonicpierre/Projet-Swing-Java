package control.musique;

import java.io.Serializable;
import java.util.List;

import control.elementSauv.personnesDejaInscrite;


/**
 *La classe <b>Album</b> permet de définir les caractèristiques de l'album :
 *<lu>
 *<li>Titre</li>
 *<li>Type</li>
 *<li>Chemin vers le dossier</li>
 *<li>Chemin vers l'image associée au dossier</li>
 *</lu>
 *Elle est Serializable car on y sauvegarde des informations
 *<p> <b>NB : </b> On utilisera le chemin absolu puisque les fichiers sont volumineux.Ainsi on stocke le chemin du dossier 
 *plutot que le dossier lui-même </p>
 **/


public class Album implements Serializable{

	private static final long serialVersionUID = -7477910052318976290L;
	
	/**
	 *Nom du titre
	 **/
	
	private String titre;
	
	/**
	 *Type du titre
	 **/
	
	
	private String cheminVerDossier;
	
	/**
	 *Chemin vers l'image associée au titre
	 **/
	
	private String cheminVersImageAssocie;
	
	/**
	 * Liste de titres
	 *@see Titre
	 **/
	
	private List<Titre> chansonsDelAlbum;
	
	private boolean selected;
	
	/**
	 *Initialisation des composentes de l'album
	 *@param titre
	 *	Titre
	 *@param type
	 *	Type du titre
	 *@param titreDeLalbum
	 *	Titre album
	 *@param cheminVersImageAssocie
	 *	Localisation du chemin associé
	 **/
	
	public Album(String titre, List<Titre> titreDeLalbum, String cheminVersImageAssocie) {
		this.setTitre(titre);

		this.setChansonsDelAlbum(titreDeLalbum);
		this.setCheminVersImageAssocie(cheminVersImageAssocie);
		this.selected = false;
		
		for(Titre montTitre : titreDeLalbum)
			montTitre.setAlbumAssocie(this);
	}
	
	public void ajouterMusique(Titre titreAAjouter) {
		this.getChansonsDelAlbum().add(titreAAjouter);
		personnesDejaInscrite.getInstance().sauvegarder();
	}
	
	public void supprimerMusique(Titre titreAAjouter) {

	}
	
	public void rechercheSuppressionMusique(Titre musiqueATrouver) {
		for(Titre monTitre : this.getChansonsDelAlbum())
			if(monTitre.equals(musiqueATrouver)) {
				this.getChansonsDelAlbum().remove(monTitre);
				personnesDejaInscrite.getInstance().sauvegarder();
				break;
			}
	}
	
	
	/**
	 *Récupère le titre
	 *@return Titre
	 **/
	
	public String getTitre() {
		return titre;
	}
	
	/**
	 *Initialisation titre
	 *@param titre
	 **/
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 *Récupère le chemin vers le dossier
	 *@return cheminVerDossier
	 **/
	
	public String getCheminVerDossier() {
		return cheminVerDossier;
	}
	
	/**
	 *Initialisation de la localisation du dossier
	 *@param cheminVerDossier
	 **/
	
	public void setCheminVerDossier(String cheminVerDossier) {
		this.cheminVerDossier = cheminVerDossier;
	}

	
	/**
	 *Récupère les chansons de l'album
	 *@return chanson de l'album
	 **/
	
	public List<Titre> getChansonsDelAlbum() {
		return chansonsDelAlbum;
	}
	
	/**
	 *Initialisation des chansons de l'album
	 *@param chansonsDelAlbum
	 **/
	
	public void setChansonsDelAlbum(List<Titre> chansonsDelAlbum) {
		this.chansonsDelAlbum = chansonsDelAlbum;
	}
	
	/**
	 *Récupération de la localisation du chemin de l'image associée
	 *@return Localisation image associée
	 **/
	
	public String getCheminVersImageAssocie() {
		return cheminVersImageAssocie;
	}
	
	/**
	 *Initialisation du chemin de l'image album
	 *@param cheminVersImageAssocie
	 **/
	
	public void setCheminVersImageAssocie(String cheminVersImageAssocie) {
		this.cheminVersImageAssocie = cheminVersImageAssocie;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
