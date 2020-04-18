package control.musique;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *<p><b>Lecteur</b> est la classe héritée de thread, qui nous permet de séparer le programme de la lecture musique.
 *Cela nous permet ainsi de pouvoir effectuer des actions simultannées entre les côtés graphique et contrôle
 *
 *On utilisera "transient" afin de ne pas prendre en compte la variable à laquelle elle est associée
 *lors de la sauvegarde.
 *</p>
 **/

public class Lecteur extends Thread implements Serializable{

	private static final long serialVersionUID = -4883518060149659730L;
	
	/**
	 *Déclaration de la piste audio
	 **/
	
	private final PisteAudio maPiste;
	
	/**
	 *Déclaration d'un playeur
	 *NB : AdvancedPlayer est un élément de la bibliothèque JLayer
	 **/
	
	transient private AdvancedPlayer player;
	
	/**
	 *Fichier à mettre en lecture
	 **/
	
	transient private FileInputStream fileInputStream;

	/**
	 *Définit les actions du lecteur
	 *@param maPiste
	 *	Piste audio
	 **/
	
	public Lecteur(PisteAudio maPiste) {
		
		/**
		 *Récupère le contenu de la construction du thread, nécéssitant une piste audio lors de sa déclaration 
		 **/
		
		super("Thread de Son");
		this.maPiste = maPiste;
		try {
			
			/**
			 *Flux entrant représentant le fichier de la source
			 **/
			
			this.fileInputStream = new FileInputStream(maPiste.getFichierAssocie());
			this.player = new AdvancedPlayer(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.start();
	}
	
	/**
	 *Fonction de lancement piste
	 *@see jouer
	 **/
	
	public void run() {
		super.run();
		jouer(maPiste);
		maPiste.getTitreAssocie().setPlaying(false);
		maPiste.getTitreAssocie().setEnPause(false);
	}
	
	/**
	 *Permet de lancer la piste audio
	 *@param Piste
	 *	Piste audio
	 *
	 **/
	
	public void jouer(PisteAudio Piste) {
		
		/**
		 *Lecture du AdvancedPlayer
		 **/
		
		try {
			player.play();

		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getExecutorService() {
		// TODO Auto-generated method stub

	}
	
	/**
	 *Récupération du player
	 *@return Player 
	 **/
	
	public AdvancedPlayer getPlayer() {
		return player;
	}
	
	/**
	 *Initialisation du player
	 *@param player
	 *	Player de musique
	 **/
	
	public void setPlayer(AdvancedPlayer player) {
		this.player = player;
	}

}
