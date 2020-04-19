package control.personne;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.List;

import control.elementSauv.personnesDejaInscrite;
import control.musique.Album;


/**
 *<b>Compte</b> est la classe qui permet de gérer la sécurité des sessions sur l'interface.
 *@author VIRGAUX Pierre
 **/

public class Artiste implements Serializable{
	
	/**
	 *Détermination du format de représentation à écrire dans le fichier.
	 **/
	
	private static final long serialVersionUID = 6421959812909952648L;
	
	private String nom;
	private String prenom;
	private Color couleurDuFond;
	private Color couleurEcriture;
	private String cheminVersImage;
	private String description;
	private String adresseMail;
	private String type;

	//Utilise que dans le compteChanteur
	
	/**
	 *Tableau d'album, utilisé dans le compte chanteur
	 **/
	
	List<Album> maListeDeAlbums;
	/**
	 *<p>Initialisation d'un objet sécurisé et sérializable puis défintion
	 *d'une couleur de fond et d'écriture</p>
	 **/
	
	public Artiste(String description, Color couleurDeFond, Color couleurEcriture, String CheminVersImage, String nom, String prenom, String adresseMail, String type) {
		maListeDeAlbums = new ArrayList<Album>();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresseMail(adresseMail);
		this.setType(type);
		this.couleurDuFond = couleurDeFond;
		this.couleurEcriture = couleurEcriture;
		this.cheminVersImage = CheminVersImage;
		this.setDescription("Vous n'avez pas de description pour le moment...");
		
	}
	
	/**Applique des couleurs d'ecriture et de fond lors de la saisie des informations. 
	 * Il définit également le chemin vers l'image, l'e-mail et affiche un message en cas d'abscence de description.
	 *@param passeword
	 *	Mot de passe
	 *@param talent
	 *	Le talent de l'utilisateur
	 *@param CheminVersImage
	 *	Chemin vers l'image
	 *@param addresseMail
	 *	Adresse e-mail
	 **/
	
	
	public Artiste(String description, String CheminVersImage,  String nom, String prenom, String adresseMail, String type) {
		this.description = description;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresseMail(adresseMail);
		this.setType(type);
		maListeDeAlbums = new ArrayList<Album>();
		this.couleurDuFond = Color.BLACK;
		this.couleurEcriture = Color.WHITE;
		this.cheminVersImage = CheminVersImage;
		this.setDescription("Vous n'avez pas de description pour le moment...");
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
	
	public List<Album> getMaListeDeAlbums() {
		return maListeDeAlbums;
	}

	public void setMaListeDeAlbums(List<Album> maListeDeChansons) {
		this.maListeDeAlbums = maListeDeChansons;
	}
	

	public void ajouterAlbum(Album AlbumASupprimer) {
		this.getMaListeDeAlbums().add(AlbumASupprimer);
		personnesDejaInscrite.getInstance().sauvegarder();
	}
	
	public void supprimerAlbum(Album AlbumASupprimer) {
		this.getMaListeDeAlbums().remove(AlbumASupprimer);
		personnesDejaInscrite.getInstance().sauvegarder();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	
	
}
