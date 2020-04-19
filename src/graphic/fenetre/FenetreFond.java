package graphic.fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphic.menusDeuxiemeFenetre.MenuAjoutAlbum;
import graphic.menusDeuxiemeFenetre.MenuAjoutMusique;
import graphic.menusDeuxiemeFenetre.MenuMusique;
import graphic.menusDeuxiemeFenetre.MenuPrincipal;
import graphic.menusDeuxiemeFenetre.TopMenuDescriptif;
import graphic.menusParametreFenetre.MenuProfilDescription;


@SuppressWarnings("serial")
public class FenetreFond extends JFrame {
	
	//On ajoute la partie principale
	//Panneau paneau;
	
	private static FenetreFond instance;
	private JLabel ImageFond;
	
	boolean FenetreFondDepartActive = true;
	
	private FenetreFond() {
		//Fenêtre de démarrage 
		setSize(new Dimension(1200, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		ImageFond = new JLabel(new ImageIcon("ImageDeFond/ImageAnime.gif"));
		this.add(ImageFond);
		setUndecorated(true);//ENLEVE LA BARRE DU DESSUS POUR REDIMENTIONNER ET FERMER LA FENETRE
		setVisible(true);
		setResizable(false);//NON POSSIBILITÉ DE REDIMENSIONNER LA FENETRE
		setFocusable(true);
	}
	
	public static FenetreFond getInstance() {
		if (instance == null)
			instance = new FenetreFond();
		return instance;
	}

	public void changerFenetre(String login) {
		if(FenetreFondDepartActive) {
			dispose();
			this.remove(ImageFond);
			setUndecorated(false);
			getContentPane().setBackground(new Color(100,100,100));
			JPanel intermediaire = new JPanel(new BorderLayout());
			intermediaire.add(TopMenuDescriptif.getInstance(login));
			this.add(intermediaire, BorderLayout.NORTH);
			this.add(MenuPrincipal.getInstance(login), BorderLayout.CENTER);
			setResizable(true);
			setVisible(true);
			setFocusable(true);
			FenetreFondDepartActive = false;
		}
		else {
			dispose();
			this.add(ImageFond);
			setUndecorated(true);
			setResizable(false);
			setFocusable(true);
			FenetreFondDepartActive = true;
			setVisible(true);
			FenetreLogin.getInstance().setVisible(true);
		}
	}
	
	public void retourEtatInitial(String login) {
		dispose();
		MenuPrincipal.getInstance(login).setArtisteSelectionne(null);
		MenuMusique.getInstance(login).setArtiste(null);
		MenuAjoutAlbum.getInstance(login).setArtiste(null);
		MenuAjoutMusique.getInstance(login).setArtiste(null);
		MenuProfilDescription.getInstance(login).setArtiste(null);
		TopMenuDescriptif.getInstance(login).setArtiste(null);
		TopMenuDescriptif.getInstance(login).updateVersInitial();
		
		this.getContentPane().removeAll();
		JPanel intermediaire = new JPanel(new BorderLayout());
		
		
		
		intermediaire.add(TopMenuDescriptif.getInstance(login));
		this.add(intermediaire, BorderLayout.NORTH);
		MenuMusique.getInstance(login).update();
		MenuPrincipal.getInstance(login).update();
		this.add(MenuPrincipal.getInstance(login), BorderLayout.CENTER);
		setVisible(true);
	}
	

}