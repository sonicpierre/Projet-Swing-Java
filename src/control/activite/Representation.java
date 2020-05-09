package control.activite;

import java.io.Serializable;

/**
 *<b>Representation</b> est la classe qui caractérise les informations relatives aux representations d'un comédien
 *@author ATANGANA Daniel
 *@version 2.0
 **/

public class Representation implements Serializable {

	private static final long serialVersionUID = 1875901704643110886L;
	
	/**
	 *Déclaration de la localisation de l'image, de la durée, de la date et du type de representation
	 **/
	 
	private String titre, cheminVersImage, duree, date, type;

	/**
	 *Permet d'initialiser les paramètres des representations
	 *@param titre
	 *	Titre de la representation
	 *@param duree
	 *	Duree de la representation
	 *@param cheminVersImage
	 *	Localisation de l'image
	 **/
	
	public Representation(String titre, String duree, String cheminVersImage, String date, String type) {
		this.setTitre(titre);
		this.duree = duree;
		this.cheminVersImage = cheminVersImage;
		this.date = date;
		this.setType(type);
	}
	
	@Override
	
	/**
	 *On réutilise la méthode equals pour comparer les representations selon leur titre et duree
	 *@param represAComparer
	 *	Representation à comparer
	 *@return True si les representations correspondent
	 **/
	
	public boolean equals(Object represAComparer) {
		Representation representationAComparer = (Representation) represAComparer;
		if(this.titre.equals(representationAComparer.getTitre()) && (this.duree == representationAComparer.duree))
			return true;
		return false;
	}
	
	@Override
	

	/**
	 *La méthode hashCode est utilisée afin de permettre le bon fonctionnement du Set
	 *@return Nombre de titre compté
	 **/
	
	public int hashCode() {
		int compteurFinal = 0;
		
		char[] monTitre = this.getTitre().toCharArray();

		for(char titre : monTitre)
			compteurFinal+= (int) titre;
		
		return compteurFinal;
	}
	
	
	/**
	 *Récupère le titre
	 *@return Titre de la chanson
	 **/
	
	public String getTitre() {
		return titre;
	}
	
	/**
	 *Initialise le titre de la musique
	 *@param titre
	 *	Titre de la chanson
	 **/
	
	public void setTitre(String titre) {
		this.titre = titre;
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
	 *Récupère la date de représentation
	 **/
	
	public String getDate() {
		return date;
	}
	
	/**
	 *Initialise la date de représentation
	 *@param date
	 *	Date de représentation
	 **/
	
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 *Récupère la durée de représentation
	 *@return Durée de représentation
	 **/
	
	public String getDuree() {
		return duree;
	}
	
	/**
	 *Initialisation de la durée
	 *	@param duree
	 *	Durée de représentation
	 **/
	
	public void setDuree(String duree) {
		this.duree = duree;
	}
	
	/**
	 *Récupère le type de représentation
	 *@return Type de représentation
	 **/
	
	public String getType() {
		return type;
	}
	
	/**
	 *Initialisation du type de représentation
	 *@param type
	 *	Type de représentation
	 **/
	
	public void setType(String type) {
		this.type = type;
	}
	
}
