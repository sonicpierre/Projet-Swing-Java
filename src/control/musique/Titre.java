package control.musique;

import java.io.File;
import java.io.Serializable;

/**
 *<p><b>Titre</b> est la classe permettant de définir les actions 
 *relatives aux titres des albums.
 *</p>
 **/

public class Titre implements Serializable{

	private static final long serialVersionUID = 3738001417481945902L;
	
	/**
	 *Nom du titre
	 **/
	
	private String titre;
	
	/**
	 *Durée de la musique
	 **/
	
	private double duree;
	
	/**
	 *Localisation de la musique
	 **/
	
	private String cheminVersLaMusique;
	
	/**
	 *Etat de lecture de la musique
	 **/
	
	private boolean isPlaying;
	
	/**
	 *Etat de pause de la musique
	 **/
	
	private boolean isEnPause;
	
	
	/**
	 *Lecteur de la musique en cours
	 *@see Lecteur
	 **/
	
	private Lecteur lecteurAssocie;
	
	/**
	 *<p>Initialisation des variables d'état de la musique.</p>
	 *<b>NB : </b>Chaque musique a un lecteur dédié
	 **/
	
	public Titre(String titre, double duree, String cheminVersLaMusique) {
		
		/**
		 *Initialisation et unicité du lecteur
		 **/
		
		this.lecteurAssocie = null;
		
		/**
		 *Initialisation de l'état en pause
		 **/
		
		this.setEnPause(false);
		
		/**
		 *Initialisation de l'état de lecture
		 **/
		
		this.setPlaying(false);
		
		/**
		 *Initialisation du titre de la musique
		 **/
		
		this.setTitre(titre);
		
		/**
		 *Initialisation de la duréee
		 **/
		
		this.setDuree(duree);
		
		/**
		 *Initialisation du chemin
		 **/
		
		this.setCheminVersLaMusique(cheminVersLaMusique);
	}
	
	/**
	 *Permet de définir le titre, la durée et le chemin d'une musique
	 *@param titre
	 *	Titre musique
	 *@param cheminVersLaMusique
	 *	Localisation de la musique
	 **/
	
	public Titre(String titre, String cheminVersLaMusique) {
		this.setTitre(titre);
		this.setDuree(-1);
		this.setCheminVersLaMusique(cheminVersLaMusique);
	}
	
	/**
	 *<p>Lancement de la lecture musique dans les cas où : 
	 *<ul>
	 *<li> La musique n'est ni en lecture ni en pause</li>
	 *<li>La musique n'est pas en lecture mais en pause</li>
	 *</ul>
	 *@deprecated Stop
	 *@see Lecteur
	 *<b>NB : </b> On a fait le choix de ne pas utiliser le FrameWork Executor car il n'est pas adapté à nos choix et est 
	 *trop volumineux par rapport à son utilisation prévue. De ce fait on utilise de méthodes dépréciées car elles suffisent
	 *et correspondent à nos besoins.
	 *On peut donc dire qu'il s'agit d'une méthode dépréciée car elle ne femre pas directement les fluxs 
	 *</p>
	 **/
	
	public void play() {
		if(!this.isPlaying() && !this.isEnPause) {
			lecteurAssocie = new Lecteur(new PisteAudio(new File(this.getCheminVersLaMusique()), this.getTitre()));
			this.setPlaying(true);
		}
		else if(!this.isPlaying && this.isEnPause) {
			lecteurAssocie.resume();
			this.isEnPause = false;
			this.isPlaying = true;
		}
			
	}
	
	/**
	 *Arrêt de la musique.
	 *<p>Elle ne sera arrêtée que si elle est en lecture</p>
	 **/
	
	@SuppressWarnings("deprecation")
	public void stop() {
		if(this.isPlaying) {
			lecteurAssocie.suspend();
			this.isEnPause = true;
			this.isPlaying = false;
		}
	}
	
	/**
	 *Réinitialisation de la lecture musique.
	 *<p>La lecture ne sera réinitialisée que si elle est en lecture</p>
	 **/
	
	@SuppressWarnings("deprecation")
	public void reset() {
		if(this.isPlaying) {
			lecteurAssocie.stop();
			isPlaying = false;
			this.play();
		}
			
	}
	
	/**
	 *Récupération du titre
	 *@return Titre musique
	 **/
	
	public String getTitre() {
		return titre;
	}
	
	/**
	 *Saisie titre musique
	 *@param titre
	 *	Titre de la musique
	 **/
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/**
	 *Récupère la durée 
	 *@return Durée musique
	 **/
	
	public double getDuree() {
		return duree;
	}
	
	/**
	 *Saisie durée musique
	 *@param duree
	 *	Durée de la musique
	 **/
	
	public void setDuree(double duree) {
		this.duree = duree;
	}
	
	/**
	 *Récupère le chemin de la musique
	 *@return Localisation de la musique
	 **/
	
	public String getCheminVersLaMusique() {
		return cheminVersLaMusique;
	}
	
	/**
	 *Saisie du chemin vers la musique
	 *@param cheminVersLaMusique
	 *	Localisation musique
	 **/
	
	public void setCheminVersLaMusique(String cheminVersLaMusique) {
		this.cheminVersLaMusique = cheminVersLaMusique;
	}
	
	/**
	 *Etat de lecture musique
	 *@return Booléen de d'état de la lecture
	 **/
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	/**
	 *Saisie état lecture musique
	 *@param isPlaying
	 *	Etat de lecture
	 **/
	
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	
	/**
	 *Récupération de l'état en pause
	 *@return Booléen de l'état de la pause
	 **/
	
	public boolean isEnPause() {
		return isEnPause;
	}
	
	/**
	 *Saisie valeur état de pause
	 *@param isEnPause
	 *	Etat de la pause musique
	 **/
	
	public void setEnPause(boolean isEnPause) {
		this.isEnPause = isEnPause;
	}
}
