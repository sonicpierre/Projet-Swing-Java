package control.activite;

import java.io.File;
import java.io.Serializable;

/**
 *<b>PisteAudio</b> est la classe qui contient les matériaux des lecteurs
 *<p>On utilisera "transient" afin de ne pas prendre en compte la variable à laquelle elle est associée
 *lors de la sauvegarde.
 *</p>
 *@author BEZIAT Lucille
 *@version 2.0
 **/

public class PisteAudio implements Serializable{

	private static final long serialVersionUID = -887177718028395301L;
	
	/**
	 *Fichier de travail
	 **/
	
	transient private File fichierAssocie;
	
	/**
	 *Nom de la piste
	 **/
	
	private final String Name;
	
	/**
	 *Titre de l'audio associé
	 **/
	
	private final Titre titreAssocie;
	/**
	 *Définition de la pste audio 
	 *@param fichierAssocie
	 *	Fichier associé
	 *@param Name
	 *	Nom de la piste audio
	 **/
	
	public PisteAudio(File fichierAssocie, String Name, Titre titreAssocie) {
		this.setFichierAssocie(fichierAssocie);
		this.Name = Name;
		this.titreAssocie = titreAssocie;
	}
	
	/**
	 *Récupération du fichier associé
	 *@return Fichier associé
	 **/
	
	public File getFichierAssocie() {
		return fichierAssocie;
	}
	
	/**
	 *Initialisation du fichier associé
	 *@param fichierAssocie
	 *	Fichier associé
	 **/
	
	public void setFichierAssocie(File fichierAssocie) {
		this.fichierAssocie = fichierAssocie;
	}

	/**
	 *Récupération du nom du fichier
	 *@return Nom fichier
	 **/
	
	public String getName() {
		return Name;
	}
	
	/**
	 *Récupère le titre associé
	 *@return Titre associé
	 **/
	
	public Titre getTitreAssocie() {
		return titreAssocie;
	}
}
