package control.musique;

import java.io.File;
import java.io.Serializable;

public class PisteAudio implements Serializable{

	private static final long serialVersionUID = -887177718028395301L;
	
	transient private File fichierAssocie;
	private final String Name;
	
	public PisteAudio(File fichierAssocie, String Name) {
		this.setFichierAssocie(fichierAssocie);
		this.Name = Name;
	}

	public File getFichierAssocie() {
		return fichierAssocie;
	}

	public void setFichierAssocie(File fichierAssocie) {
		this.fichierAssocie = fichierAssocie;
	}

	
	public String getName() {
		return Name;
	}
}
