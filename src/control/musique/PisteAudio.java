package control.musique;

import java.io.File;

public class PisteAudio {

	private File fichierAssocie;
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
