package control.personne;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import control.activite.Album;
import control.activite.Representation;
import control.elementSauv.personnesDejaInscrite;


/**
 *<b>Compte</b> est la classe qui permet de gérer la sécurité des sessions sur l'interface.
 *@author BUISSON-CHAVOT Julien
 *@version 2.0
 **/

public class Artiste implements Serializable{
	
	/**
	 *Détermination du format de représentation à écrire dans le fichier.
	 **/
	
	private static final long serialVersionUID = 6421959812909952648L;
	
	/**
	 *Déclaration des éléments descriptifs de l'utilisateur et du chemin vers l'image
	 **/
	
	private String nom, prenom , cheminVersImage, description, adresseMail, type;
	
	/**
	 *Déclaration des couleurs de fond et d'écriture
	 **/
	
	private Color couleurDuFond, couleurEcriture;


	//Utilise que dans le compteChanteur
	
	/**
	 *Tableau d'album, utilisé dans le compte chanteur
	 *@see Album
	 **/
	
	Set<Album> maListeDeAlbums;
	
	/**
	 *<p>Initialisation d'un objet sécurisé et sérializable puis défintion
	 *d'une couleur de fond et d'écriture</p>
	 *@see Representation
	 **/
	
	Set<Representation> maListeDeRepresentations;
	
	
	/**
	 *Permet de définir les éléments d'identification utilisateur
	 *@param description
	 *	Description de l'utilisateur
	 *@param couleurDeFond
	 *	Couleur de fond fenêtre
	 *@param couleurEcriture
	 *	Couleur d'écriture
	 *@param CheminVersImage
	 *	Chemin vers l'image
	 *@param nom
	 *	Nom utilisateur
	 *@param prenom
	 *	Prenom utilisateur
	 *@param adresseMail
	 *	E-mail utilisateur
	 *@param type
	 *	Type d'utilisateur
	 **/
	
	public Artiste(String description, Color couleurDeFond, Color couleurEcriture, String CheminVersImage, String nom, String prenom, String adresseMail, String type) {
		maListeDeRepresentations = new HashSet<Representation>();
		maListeDeAlbums = new HashSet<Album>();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresseMail(adresseMail);
		this.setType(type);
		this.couleurDuFond = couleurDeFond;
		this.couleurEcriture = couleurEcriture;
		this.cheminVersImage = CheminVersImage;
		this.setDescription(description);
		
	}
	
	/**Applique des couleurs d'ecriture et de fond lors de la saisie des informations. 
	 * Il définit également le chemin vers l'image, l'e-mail et affiche un message en cas d'abscence de description.
	 *@param description
	 *	Description utilisateur
	 *@param CheminVersImage
	 *	Localisation de l'image
	 *@param nom
	 *	Nom utilisateur
	 *@param prenom
	 *	Prenom utilisateur
	 *@param adresseMail
	 *	Adresse e-mail
	 *@param type
	 *	Talent de l'utilisateur
	 **/
	
	
	public Artiste(String description, String CheminVersImage,  String nom, String prenom, String adresseMail, String type) {
		this.description = description;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresseMail(adresseMail);
		this.setType(type);
		maListeDeRepresentations = new HashSet<Representation>();
		maListeDeAlbums = new HashSet<Album>();
		this.couleurDuFond = Color.BLACK;
		this.couleurEcriture = Color.WHITE;
		this.cheminVersImage = CheminVersImage;
		this.setDescription(description);
	}
	
	
	/**
	 * Cette définition de artiste est spécialement pour le remplissage par copie de BDD ou par copie de csv
	 * @param description
	 * 	Description de l'artiste
	 * @param nom
	 * 	Nom de l'artiste
	 * @param prenom
	 * 	Prenom de l'artiste
	 * @param type
	 * 	Talent de l'artiste
	 */
	
	public Artiste(String description, String nom, String prenom, String type) {
		this.description = description;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresseMail("");
		this.setType(type);
		maListeDeRepresentations = new HashSet<Representation>();
		maListeDeAlbums = new HashSet<Album>();
		this.couleurDuFond = Color.BLACK;
		this.couleurEcriture = Color.WHITE;
		this.cheminVersImage = "ImageProfil/inconnu.jpg";
		this.setDescription(description);
	}

	
	/** Récupère la couleur de fond
	 *@return La couleur de fond
	 **/
	
	public Color getCouleurDuFond() {
		return couleurDuFond;
	}
	
	/**Initialise la couleur de fond
	 *@param couleurDuFond
	 *	Couleur de fond
	 **/
	
	public void setCouleurDuFond(Color couleurDuFond) {
		this.couleurDuFond = couleurDuFond;
	}
	
	/**Récupère la couleur d'écriture
	 *@return La couleur d'écriture
	 **/
	
	public Color getCouleurEcriture() {
		return couleurEcriture;
	}
	
	/**Initialise la couleur d'écriture
	 *@param couleurEcriture
	 *	couleur d'écriture
	 **/
	
	public void setCouleurEcriture(Color couleurEcriture) {
		this.couleurEcriture = couleurEcriture;
	}
		
	
	/**
	 *Récupère le chemin vers l'image
	 *@return Chemin vers l'image
	 **/
	
	public String getCheminVersImage() {
		return cheminVersImage;
	}

	/**
	 *Permet de définir le chemin vers l'image
	 **/
	
	public void setCheminVersImage(String cheminVersImage) {
		this.cheminVersImage = cheminVersImage;
	}
	
	/**
	 * Permet de récupérer la description utilisateur
	 *@return Description utilisateur
	 **/
	
	public String getDescription() {
		return description;
	}
	
	/**
	 *Permet de définir la description de l'utilisateur
	 **/
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 *Récupère la liste d'album
	 *@see Album
	 *@return Liste d'album
	 **/
	
	public Set<Album> getMaListeDeAlbums() {
		return maListeDeAlbums;
	}
	
	/**
	 *Initialisation de la liste d'album
	 *@param maListeDeChansons
	 *	Liste de musiques
	 *@see Album
	 **/
	
	public void setMaListeDeAlbums(Set<Album> maListeDeChansons) {
		this.maListeDeAlbums = maListeDeChansons;
	}
	
	/**Permet l'ajout d'un Album et la sauvegarde
	 *@param AlbumASupprimer
	 *	Album à supprimer
	 **/
	
	public void ajouterAlbum(Album AlbumASupprimer) {
		this.getMaListeDeAlbums().add(AlbumASupprimer);
		personnesDejaInscrite.getInstance().sauvegarder();
	}
	
	/**Permet la suppression d'un Album et la sauvegarde
	 *@param AlbumASupprimer
	 *	Album à supprimer
	 **/
	
	public void supprimerAlbum(Album AlbumASupprimer) {
		this.getMaListeDeAlbums().remove(AlbumASupprimer);
		personnesDejaInscrite.getInstance().sauvegarder();
	}
	
	
	@Override
	
	/**Redéfinition de la methode equals car on ne veut pas que la photo soit prise en compte
	 *@param autreArtiste
	 *@return True s'il s'agit de l'artiste recherché
	 **/
	
	public boolean equals(Object autreArtiste) {
		Artiste artistePourComparaison = (Artiste) autreArtiste;
		if(this.nom.equals(artistePourComparaison.getNom()) && this.prenom.equals(artistePourComparaison.getPrenom()) && this.type.equals(artistePourComparaison.getType()))
			return true;
		
		return false;
	}
	
	/**
	 *La méthode hashCode est utilisée afin de permettre le bon fonctionnement du Set
	 *NB : Ainsi deux artistes seront égaux s'ils ont le même nom, prenom et Type. On évite également les doublons dans la liste
	 **/
	
	@Override
	public int hashCode() {
		int compteurFinal = 0;
		
		char[] monNom = this.getNom().toCharArray();
		char[] monPrenom = this.getPrenom().toCharArray();
		char[] monType = this.getType().toCharArray();
		
		for(char nom : monNom)
			compteurFinal+= (int) nom;
		
		for(char prenom : monPrenom)
			compteurFinal+= (int) prenom;
		
		for(char type : monType)
			compteurFinal+= (int) type;
		
		return compteurFinal;
	}
	
	/**
	 *Récupère le nom utilisateur
	 *@return Nom utilisateur
	 **/
	
	public String getNom() {
		return nom;
	}
	
	/**
	 *Définition du nom utilisateur
	 *@param nom
	 *	Nom utilisateur
	 **/
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	

	/**
	 *Récupère le prénom utilisateur
	 *@return Prenom utilisateur
	 **/
	
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 *Définition du prénom utilisateur
	 *@param prenom
	 *	Prenom utilisateur
	 **/
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 *Récupère l'e-mail utilisateur
	 *@return E-mail utilsateur
	 **/
	
	public String getAdresseMail() {
		return adresseMail;
	}
	
	/**
	 *Définie l'adresse e-m	il utilisateur
	 *@param adresseMail
	 **/
	
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}
	
	/**
	 *Récupère le type de l'artiste
	 *@return Type artiste
	 **/
	
	public String getType() {
		return type;
	}
	
	/**
	 *Définie le type de l'artiste
	 *@param type
	 *	Type artiste
	 **/
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 *Récupère la liste de representation artiste
	 *@return Liste de representation
	 **/
	
	public Set<Representation> getMaListeDeRepresentations() {
		return maListeDeRepresentations;
	}
	
	/**
	 *Initialise la liste de representation d'un artiste
	 *@param maListeDeRepresentations
	 *	Liste de representation
	 **/
	
	public void setMaListeDeRepresentations(Set<Representation> maListeDeRepresentations) {
		this.maListeDeRepresentations = maListeDeRepresentations;
	}
	
}