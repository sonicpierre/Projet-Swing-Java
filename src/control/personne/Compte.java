package control.personne;

import java.awt.Color;
import java.io.Serializable;

public class Compte implements Serializable{
	
	private static final long serialVersionUID = 6421959812909952648L;

	String passeword;
	Color couleurDuFond;
	Color couleurEcriture;
	
	public Compte(String passeword, Color couleurDeFond, Color couleurEcriture) {	
		this.passeword = passeword;
		this.couleurDuFond = couleurDeFond;
		this.couleurEcriture = couleurEcriture;
	}

	public String getPasseword() {
		return passeword;
	}

	public void setPasseword(String passeword) {
		this.passeword = passeword;
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
	
	
}
