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

public class Compte implements Serializable{
	
	/**
	 *Détermination du format de représentation à écrire dans le fichier.
	 **/
	
	private static final long serialVersionUID = 6421959812909952648L;
	
	private SignedObject passewordCrypte;
	private Color couleurDuFond;
	private Color couleurEcriture;
	private String cheminVersImage;
	private String description; 
	//Utilise que dans le compteChanteur
	
	/**
	 *Tableau d'album, utilisé dans le compte chanteur
	 **/
	
	List<Album> maListeDeAlbums;
	/**
	 *<p>Initialisation d'un objet sécurisé et sérializable puis défintion
	 *d'une couleur de fond et d'écriture</p>
	 **/
	
	public Compte(String passeword, Color couleurDeFond, Color couleurEcriture, String CheminVersImage) {
		maListeDeAlbums = new ArrayList<Album>();
		this.couleurDuFond = couleurDeFond;
		this.couleurEcriture = couleurEcriture;
		securiser(passeword);
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
	
	
	public Compte(String passeword, String CheminVersImage) {
		maListeDeAlbums = new ArrayList<Album>();
		securiser(passeword);
		this.couleurDuFond = Color.BLACK;
		this.couleurEcriture = Color.WHITE;
		this.cheminVersImage = CheminVersImage;
		this.setDescription("Vous n'avez pas de description pour le moment...");
	}
	
	/**Récupère le mot de passe à encoder
	 *@return Mot de passe crypté
	 **/
	
	public SignedObject getPasseword() {
		return passewordCrypte;
	}
	
	/**Met en place la sécurité du mot de passe
	 **/
	
	public void setPasseword(String passeword) {
		securiser(passeword);
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
	 *<p>Cette méthode est un point important de la classe Compte.
	 *En effet elle permet de sécuriser le champ du mot de passe. De plus, puisque le Serialize serait trop simplement lu
	 *via un éditeur de texte, nous avons sécurisé le fichier d'informations en utilisant l'objet SignedObject qui utilise
	 *l'algorithme de cryptage DSA.
	 *</p>
	 *<p>Le choix de cet objet s'est fait car :
	 *<ul>
	 *<li>Serializable</li>
	 *<li>Compatible avec notre méthode de sauvegarde fichier</li>
	 *</ul>
	 * </p>
	 **/
	
	private void securiser(String passeword) {//PRIVATISATION DU FICHIER DE MDP
		/**
		 *Création d'une clé
		 **/
		
		KeyPairGenerator keyGen;
		try {
			
			/**
			 *Signature d'un objet
			 **/
			
			keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
			
			/**Définit la source pour la génération de la clé
			 **/
			
			SecureRandom random;
			random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			
			/**Ici 1024 est la taille de la clé générée
			 **/
			
			keyGen.initialize(1024, random);
			
			/**
			 *Création d'une clé privée
			 **/
			
			PrivateKey signingKey = keyGen.generateKeyPair().getPrivate();
			
			/**Création d'une signature
			 **/
			
			Signature signingEngine = Signature.getInstance("DSA");
			signingEngine.initSign(signingKey);
			
			/**Signature de l'objet
			 **/
			
			this.passewordCrypte = new SignedObject(passeword, signingKey, signingEngine);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
	
	
}
