package graphic.fenetre;

import java.awt.Dimension;
import javax.swing.JFrame;
import control.activite.Album;
import control.activite.Titre;
import control.personne.Artiste;
import graphic.menusParametreFenetre.MenuAjoutAlbum;
import graphic.menusParametreFenetre.MenuAjoutArtiste;
import graphic.menusParametreFenetre.MenuAjoutMusique;
import graphic.menusParametreFenetre.MenuAjoutRepresentation;
import graphic.menusParametreFenetre.MenuFinalParametre;
import graphic.menusParametreFenetre.MenuRenommer;


/**
 * La classe <b>FentreParametre</b> permet de générer les différents types de fenêtres permettant des mises à jours :
 * <lu>
 * <li>Une fenêtre de description</li>
 * <li>Une fenêtre pour ajouter des albums</li>
 * <li>Une fenêtre pour ajouter un film ou un spectacle</li>
 * <li>Une fenêtre pour ajouter une musique</li>
 * <li>Une fenêtre pour renommer</li> </lu>
 * 
 * 
 * @author Pierre VIRGAUX
 * @version 2.0
 * 
 **/

@SuppressWarnings("serial")
public class FenetreParametre extends JFrame{
	
	private static FenetreParametre instance;
	
	/**
	 * {@value #dimChoixAlbum} est la dimension de la fenêtre pour ajouter des Albums
	 * {@value #dimChoixMusique} est la dimension de la fenêtre pour ajouter une musique
	 * {@value #dimChoixRepresentation} est la dimension de la fenêtre pour ajouter une représentation
	 * {@value #dimParametre} est la dimension de la fenêtre pour décrire les artistes
	 * {@value #dimAjoutArtiste} est la dimension de la fenêtre pour ajouter un artiste
	 * {@value #dimRenommer} est la dimension de la fenêtre pour renommer un album ou une représentation
	 */
	
	private final static Dimension dimChoixAlbum = new Dimension(600,100);
	private final static Dimension dimChoixMusique = new Dimension(600,100);
	private final static Dimension dimChoixRepresentation = new Dimension(500,500);
	private final static Dimension dimParametre = new Dimension(600,500);
	private final static Dimension dimAjoutArtiste = new Dimension(450,400);
	private final static Dimension dimRenommer = new Dimension(350,70);
	
	/**
	 * Le login est ici le nom d'utilisateur SQL, il est utile pour accéder à toutes les autres valeurs.
	 */
	private String login;
	
	/** 
	 * Ici on a le constructeur de la fenêtre qui permet de mettre de set certains paramètres qui ne changeront pas.
	 * 
	 * @param login
	 */
	
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
	
	/**
	 * @see MenuFinalParametre
	 * Permet de faire apparaitre la fenêtre correspondante à la description de l'artiste et à la réédition de ses paramètres.
	 */
	public void ajoutParametre() {
		//On rend la fenêtre invisible 
		dispose();
		//On change le titre
		this.setTitle("Paramètre");
		//On supprime l'ensemble du contenu
		this.getContentPane().removeAll();
		//On change la dimension de la fenêtre
		setSize(dimParametre);
		//On ajoute le panel correspondant
		this.getContentPane().add(MenuFinalParametre.getInstance(login));
		//On rend la fenêtre visible à nouveau
		setVisible(true);
		
	}
	
	/**
	 * Permet l'ajouter d'un Album et de toutes les musiques qu'il contient.
	 * Codé sur le modèle de ajoutParametre()
	 * 
	 * @see MenuAjoutAlbum
	 */
	
	public void ajoutAlbumFenetre() {
		dispose();//ON REND LA FEN INVISIBLE
		this.setTitle("Ajouter album");
		this.getContentPane().removeAll();//SUPPREMSSION CONTENU
		setSize(dimChoixAlbum);//REDIMENSION
		this.getContentPane().add(MenuAjoutAlbum.getInstance(login));//LECTURE CONTENU + AJOUT DU MENU CORRESPONDZNT À L'AJOUT ALBUM
		setVisible(true);//VISIBLITE DELA FEN
	}
	
	/**
	 * Permet d'ajouter une musique à un Album.
	 * Codé sur le modèle de ajoutParametre()
	 * 
	 * @see MenuAjoutMusique
	 * 
	 */
	
	public void ajoutMusiqueFenetre() {
		dispose();
		this.setTitle("Ajouter musique");
		this.getContentPane().removeAll();
		setSize(dimChoixMusique);
		this.getContentPane().add(MenuAjoutMusique.getInstance(login));
		setVisible(true);
	}
	
	/**
	 * Permet d'appeler la fenêtre pour l'ajout d'un artiste.
	 * Codé sur le modèle de ajoutParametre()
	 * 
	 * @see MenuAjoutArtiste
	 */
	
	public void ajoutArtisteFenetre() {
		dispose();
		this.setTitle("Ajouter artiste");
		this.getContentPane().removeAll();
		setSize(dimAjoutArtiste);
		this.getContentPane().add(MenuAjoutArtiste.getInstance(login));
		setVisible(true);
	}
	
	/**
	 * 	Cette fenêtre permettre d'ajouter des représentations.
	 * 	Codé sur le modèle de ajoutParametre()
	 * 
	 * 	@see MenuAjoutRepresentation
	 */

	
	public void ajoutRepresentationFenetre() {
		dispose();
		this.setTitle("Ajouter representation");
		this.getContentPane().removeAll();
		setSize(dimChoixRepresentation);
		this.getContentPane().add(MenuAjoutRepresentation.getInstance(login));
		setVisible(true);
	}
	
	/**
	 * 
	 * @param titre
	 * @param texLabel
	 * @param textField
	 * @param album
	 * @param artiste
	 * @param musique
	 * @see MenuRenommer
	 * 
	 * Cette fenêtre permettra de renommer les musiques, les représentation et les albums.
	 * Tous les paramètres ne sont pas forcément utilisés et seront mis à null au moment de l'appel si nécessaire.
	 * 
	 * Codé sur le modèle de ajoutParametre()
	 * 
	 */
	
	public void ajoutRenommageFenetre(String titre, String texLabel, String textField, Album album, Artiste artiste, Titre musique) {
		dispose();
		this.setTitle(titre);
		this.getContentPane().removeAll();
		this.getContentPane().add(MenuRenommer.getInstance(login, texLabel, textField, album, artiste, musique));
		setSize(dimRenommer);
		setVisible(true);
	}
	
	/**
	 * Cette fonction permet d'accéder à l'objet FenetreParametre
	 * 
	 * @param login
	 * @return L'objet singleton
	 */
	
	public static FenetreParametre getInstance(String login) {
		if (instance == null)
			instance = new FenetreParametre(login);
		return instance;
	}
}
