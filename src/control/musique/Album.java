package control.musique;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable{

	private static final long serialVersionUID = -7477910052318976290L;
	
	private String titre;
	private String type;
	private String cheminVerDossier;
	private String cheminVersImageAssocie;
	private List<Titre> chansonsDelAlbum;
	
	public Album(String titre, String type, List<Titre> titreDeLalbum, String cheminVersImageAssocie) {
		this.setTitre(titre);

		this.setType(type);
		this.setChansonsDelAlbum(titreDeLalbum);
		this.setCheminVersImageAssocie(cheminVersImageAssocie);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
	public String getCheminVerDossier() {
		return cheminVerDossier;
	}

	public void setCheminVerDossier(String cheminVerDossier) {
		this.cheminVerDossier = cheminVerDossier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Titre> getChansonsDelAlbum() {
		return chansonsDelAlbum;
	}

	public void setChansonsDelAlbum(List<Titre> chansonsDelAlbum) {
		this.chansonsDelAlbum = chansonsDelAlbum;
	}

	public String getCheminVersImageAssocie() {
		return cheminVersImageAssocie;
	}

	public void setCheminVersImageAssocie(String cheminVersImageAssocie) {
		this.cheminVersImageAssocie = cheminVersImageAssocie;
	}

}
