package control.musique;

import java.io.File;
import java.io.Serializable;

public class Titre implements Serializable{

	private static final long serialVersionUID = 3738001417481945902L;
	
	private String titre;
	private double duree;
	private String cheminVersLaMusique;
	private boolean isPlaying;
	private boolean isEnPause;
	private Lecteur lecteurAssocie;
	
	public Titre(String titre, double duree, String cheminVersLaMusique) {
		this.lecteurAssocie = null;
		this.setEnPause(false);
		this.setPlaying(false);
		this.setTitre(titre);
		this.setDuree(duree);
		this.setCheminVersLaMusique(cheminVersLaMusique);
	}

	public Titre(String titre, String cheminVersLaMusique) {
		this.setTitre(titre);
		this.setDuree(-1);
		this.setCheminVersLaMusique(cheminVersLaMusique);
	}

	
	@SuppressWarnings("deprecation")
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
	@SuppressWarnings("deprecation")
	public void stop() {
		if(this.isPlaying) {
			lecteurAssocie.suspend();
			this.isEnPause = true;
			this.isPlaying = false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void reset() {
		if(this.isPlaying) {
			lecteurAssocie.stop();
			isPlaying = false;
			this.play();
		}
			
	}
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public String getCheminVersLaMusique() {
		return cheminVersLaMusique;
	}

	public void setCheminVersLaMusique(String cheminVersLaMusique) {
		this.cheminVersLaMusique = cheminVersLaMusique;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public boolean isEnPause() {
		return isEnPause;
	}

	public void setEnPause(boolean isEnPause) {
		this.isEnPause = isEnPause;
	}
}
