package graphic.fenetre;

import java.awt.Dimension;

import javax.swing.JFrame;

import graphic.menusParametreFenetre.MenuAjoutAlbum;
import graphic.menusParametreFenetre.MenuAjoutArtiste;
import graphic.menusParametreFenetre.MenuAjoutMusique;
import graphic.menusParametreFenetre.MenuAjoutRepresentation;
import graphic.menusParametreFenetre.MenuFinalParametre;


//
@SuppressWarnings("serial")
public class FenetreParametre extends JFrame{
	//On ajoute la partie principale
	//DEFINITION D'UNE FENETRE PREMETTANT 
	//FENETRE LOGIN MDP
	//FEN FOND
	//FN D'ORPERATIONS
	
	private static FenetreParametre instance;
	private final static Dimension dimChoixAlbum = new Dimension(600,100);//DIMENSION FEN CHOIX ALBUM
	private final static Dimension dimChoixMusique = new Dimension(600,100);//DIMENSION FEN CHOIX MUSIQUE
	private final static Dimension dimChoixRepresentation = new Dimension(500,500);//DIMENSION FEN CHOIX MUSIQUE
	private final static Dimension dimParametre = new Dimension(600,500);//DIM FEN PARAM
	private final static Dimension dimAjoutArtiste = new Dimension(450,400);//DIMENSION FEN CHOIX ALBUM
	
	private String login;//CLÉ VERS UTILISATEUR
	
	private FenetreParametre(String login){
		this.login = login;
		FenetreFond.getInstance();
		this.setTitle("Paramètre");
		setSize(new Dimension(600,500));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	//IDEM EN BAS
	public void ajoutParametre() {
		dispose();
		this.setTitle("Paramètre");
		this.getContentPane().removeAll();
		setSize(dimParametre);
		this.getContentPane().add(MenuFinalParametre.getInstance(login));
		setVisible(true);
		
	}
	
	//
	public void ajoutAlbumFenetre() {
		dispose();//ON REND LA FEN INVISIBLE
		this.setTitle("Ajouter album");
		this.getContentPane().removeAll();//SUPPREMSSION CONTENU
		setSize(dimChoixAlbum);//REDIMENSION
		this.getContentPane().add(MenuAjoutAlbum.getInstance(login));//LECTURE CONTENU + AJOUT DU MENU CORRESPONDZNT À L'AJOUT ALBUM
		setVisible(true);//VISIBLITE DELA FEN
	}
	
	public void ajoutMusiqueFenetre() {
		dispose();//ON REND LA FEN INVISIBLE
		this.setTitle("Ajouter musique");
		this.getContentPane().removeAll();//SUPPREMSSION CONTENU
		setSize(dimChoixMusique);//REDIMENSION
		this.getContentPane().add(MenuAjoutMusique.getInstance(login));//LECTURE CONTENU + AJOUT DU MENU CORRESPONDZNT À L'AJOUT ALBUM
		setVisible(true);//VISIBLITE DELA FEN
	}
	
	public void ajoutArtisteFenetre() {
		dispose();//ON REND LA FEN INVISIBLE
		this.setTitle("Ajouter artiste");
		this.getContentPane().removeAll();//SUPPREMSSION CONTENU
		setSize(dimAjoutArtiste);//REDIMENSION
		this.getContentPane().add(MenuAjoutArtiste.getInstance(login));//LECTURE CONTENU + AJOUT DU MENU CORRESPONDZNT À L'AJOUT ALBUM
		setVisible(true);//VISIBLITE DELA FEN
	}
	
	public void ajoutRepresentationFenetre() {
		dispose();//ON REND LA FEN INVISIBLE
		this.setTitle("Ajouter representation");
		this.getContentPane().removeAll();//SUPPRESSION CONTENU
		setSize(dimChoixRepresentation);//REDIMENSION
		this.getContentPane().add(MenuAjoutRepresentation.getInstance(login));//LECTURE CONTENU + AJOUT DU MENU CORRESPONDZNT À L'AJOUT ALBUM
		setVisible(true);//VISIBLITE DELA FEN
	}
	
	public static FenetreParametre getInstance(String login) {
		if (instance == null)
			instance = new FenetreParametre(login);
		return instance;
	}
}
