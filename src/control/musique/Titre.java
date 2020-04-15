package control.musique;

import java.io.Serializable;

public class Titre implements Serializable{

	private static final long serialVersionUID = 3738001417481945902L;
	
	private String titre;
	private double duree;
	private String cheminVersLaMusique;
	
	public Titre(String titre, double duree, String cheminVersLaMusique) {
		this.setTitre(titre);
		this.setDuree(duree);
		this.setCheminVersLaMusique(cheminVersLaMusique);
	}

	public Titre(String titre, String cheminVersLaMusique) {
		this.setTitre(titre);
		this.setDuree(-1);
		this.setCheminVersLaMusique(cheminVersLaMusique);
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
}
