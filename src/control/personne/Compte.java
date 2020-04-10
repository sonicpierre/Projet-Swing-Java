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

public class Compte implements Serializable{//VERIFICATION DE LA NATURE DES OBJET A SERIALIZER (INT, FLOAT ETC..)
	
	private static final long serialVersionUID = 6421959812909952648L;

	SignedObject passewordCrypte;//OBJET SECURISÉ ET SERIALISABLE
	Color couleurDuFond;//TYPE COULEUR
	Color couleurEcriture;//TYPE COULEUR
	String adresseMail;
	
	//Un Compte contient le passeword mais aussi les différentes préférences de l'utilisateur.
	
	public Compte(String passeword, Color couleurDeFond, Color couleurEcriture, String adresseMail) {
		this.couleurDuFond = couleurDeFond;
		this.couleurEcriture = couleurEcriture;
		securiser(passeword);
		this.adresseMail = adresseMail;
	}
	
	
	public Compte(String passeword) {	
		securiser(passeword);
		this.couleurDuFond = Color.BLACK;
		this.couleurEcriture = Color.WHITE;
	}

	public SignedObject getPasseword() {
		return passewordCrypte;
	}

	public void setPasseword(String passeword) {
		securiser(passeword);
	}

	public Color getCouleurDuFond() {
		return couleurDuFond;
	}

	public void setCouleurDuFond(Color couleurDuFond) {
		this.couleurDuFond = couleurDuFond;
	}

	public Color getCouleurEcriture() {
		return couleurEcriture;
	}

	public void setCouleurEcriture(Color couleurEcriture) {
		this.couleurEcriture = couleurEcriture;
	}
	
	//Permet de sécuriser le champ password. Par ce que le fichier Serializable serait trop simplement lu avec un simple éditeur
	//hexadécimal. Ici on utilise l'objet SignedObject qui permet d'utiliser l'algorithme de cryptage DSA. On a choisi cet objet car
	//il est serialisable et donc compatible avec notre façon de sauvegarder le fichier.
	
	private void securiser(String passeword) {//PRIVATISATION DU FICHIER DE MDP
		//Create a key
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA", "SUN");//SIGNATURE D'UN OBJET
			//Définit la source pour la génération de la clée
			SecureRandom random;
			random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			//Ici 1024 est la taille de la clée générée
			keyGen.initialize(1024, random);
			// create a private key
			PrivateKey signingKey = keyGen.generateKeyPair().getPrivate();
			// create a Signature
			Signature signingEngine = Signature.getInstance("DSA");
			signingEngine.initSign(signingKey);
			// sign our object
			this.passewordCrypte = new SignedObject(passeword, signingKey, signingEngine);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeyException | SignatureException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
