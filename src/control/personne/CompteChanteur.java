package control.personne;

import java.util.ArrayList;

import control.musique.Album;

public class CompteChanteur extends Compte{

	private static final long serialVersionUID = 3745382508866010183L;

	
	public CompteChanteur(String passeword, String talent, String CheminVersImage, String addresseMail) {
		super(passeword, talent, CheminVersImage, addresseMail);
		
		maListeDeAlbums = new ArrayList<Album>();
	}
	
	
}
