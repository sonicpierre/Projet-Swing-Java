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


/**
 *<b>Compte</b> est la classe qui permet de gérer la sécurité des sessions sur l'interface.
 **/

public class Compte implements Serializable{
	
	private static final long serialVersionUID = 6421959812909952648L;
	
	/**
	 *<p>Initialisation d'un objet sécurisé et sérializable puis défintion
	 *d'une couleur de fond et d'éciture</p>
	 **/
	
	SignedObject passewordCrypte;
	Color couleurDuFond;
	Color couleurEcriture;
	
	/**<p>Compte contient le mot de passe mais aussi les préférences de l'utilisateur.
	 *@param passeword
	 *	le mot de passe
	 *@param couleurDeFond 
	 *	une couleur de fond
	 *@param couleurEcriture 
	 *	une couleur d'écriture
	 *</p>
	 *Sécurise le mot de passe */
	
	public Compte(String passeword, Color couleurDeFond, Color couleurEcriture) {
		this.couleurDuFond = couleurDeFond;
		this.couleurEcriture = couleurEcriture;
		securiser(passeword);
	}
	
	/**Applique des couleurs d'ecriture et de fond lors de la saisie du mot de passe
	 *@param passeword
	 *	mot de passe
	 **/
	
	public Compte(String passeword) {	
		securiser(passeword);
		this.couleurDuFond = Color.BLACK;
		this.couleurEcriture = Color.WHITE;
	}
	
	/**Récupère le mot de passe à encoder
	 *@return Mot de passe crypté
	 **/
	
	public SignedObject getPasseword() {
		return passewordCrypte;
	}
	
	/**Met en place la sécurité du mot de passe
	 * */
	
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
	 *	couleur de fond
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
			
			/**Création d'un signature
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
}
