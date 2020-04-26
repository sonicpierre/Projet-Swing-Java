package graphic.menusDeuxiemeFenetre;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreParametre;
import graphic.menusParametreFenetre.MenuAjoutMusique;

/**
 * Permet de faire le lien entre le cote serveur(actions) et le cote client (bouton)
 *fonctionne de façon analogue aux liteners
 *ingleton, deux var clés login et article
 *Elle peut */
public class MenuRaccourcis {

	private static MenuRaccourcis instance;
	private String login;
	private Artiste artiste;

	private MenuRaccourcis(String login, Artiste artiste) {
		this.login = login;
		this.artiste = artiste;
	}

	private MenuRaccourcis(String login) {
		this.login = login;
		this.artiste = null;
	}


	@SuppressWarnings("serial")
	
	/**
	 * On donne un nom, une icone puis un emonic qui correction au tab+lettre*/
	public AbstractAction actParametre = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Paramètre...");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/parametre.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_P);
			/**
			 *Petite fenetre de decription
			 **/
			
			putValue( Action.SHORT_DESCRIPTION, "Paramètre (CTRL+P)");
			
			/**
			 *Raccourci clavier
			 **/
			
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action ouvrir la fenetre param 
		 **/

		public void actionPerformed(ActionEvent arg0) {
			FenetreParametre.getInstance(login).ajoutParametre();
		}
	};

	
	@SuppressWarnings("serial")
	public AbstractAction actAjoutAlb = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Ajout album...");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_A);
			putValue( Action.SHORT_DESCRIPTION, "Ajout album (CTRL+A)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutAlbumFenetre();
		}
	};

	@SuppressWarnings("serial")
	public AbstractAction actAjoutMus = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Ajout musique...");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_M);
			putValue( Action.SHORT_DESCRIPTION, "Ajout musique (CTRL+M)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutMusiqueFenetre();
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actDeco = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Deconnexion");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/deconnexion.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_D);
			putValue( Action.SHORT_DESCRIPTION, "Deconnexion (CTRL+Q)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			TopMenuDescriptif.getInstance(login).deconnexion();
		}
	};

	@SuppressWarnings("serial")
	public AbstractAction actSuppressionMusique = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Supprimer la musique");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Supprimer.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_S);
			putValue( Action.SHORT_DESCRIPTION, "Supprimer musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Supprimer");
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actPlay = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Play");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Play.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_J);
			putValue( Action.SHORT_DESCRIPTION, "Jouer musique (CTRL+R)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Play");
		}
	};

	@SuppressWarnings("serial")
	public AbstractAction actStop = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Stop");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/pause.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_I);
			putValue( Action.SHORT_DESCRIPTION, "Interrupt musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Stop");
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actReset = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Reset");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Reset.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_R);
			putValue( Action.SHORT_DESCRIPTION, "Rejouer musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Reset");
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actBack = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Revenir");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_B);
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/retour.png"));
			putValue( Action.SHORT_DESCRIPTION, "Revenir en arrière (CTRL+B)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			FenetreFond.getInstance().retourEtatInitial(login);
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actAjoutArtiste = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Ajouter artiste");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_A);
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue( Action.SHORT_DESCRIPTION, "Ajouter artiste (CTRL+A)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutArtisteFenetre();
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actContacter = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Contacter artistes");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_C);
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/mail.png"));
			putValue( Action.SHORT_DESCRIPTION, "Contacter artistes (CTRL+C)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			TopMenuDescriptif.getInstance(login).ouvertureFenetreMail();
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actSupressionArtiste = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Supprimer artiste");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_S);
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().remove(artiste);
			MenuPrincipal.getInstance(login).update();
			personnesDejaInscrite.getInstance().sauvegarder();
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actAjoutRepresentation = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Ajout representation");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_A);
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutRepresentationFenetre();
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actChangerImage = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Changer image album");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/paste.png"));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).changerImage();
			MenuMusique.getInstance(login).update();
		}
	};
	

	@SuppressWarnings("serial")
	public AbstractAction actRenommer = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Renommer");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/paste.png"));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).renommerAlbum();
			MenuMusique.getInstance(login).update();
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actSuppressionAlbum = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Supprimer album");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/paste.png"));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).supprimerAlbum();
			MenuMusique.getInstance(login).update();
			MenuAjoutMusique.getInstance(login).update();
			MenuPrincipal.getInstance(login).update();
		}
	};
	
	
	public static MenuRaccourcis getInstance(String login, Artiste artiste) {
		if (instance == null)
			instance = new MenuRaccourcis(login, artiste);
		return instance;
	}
	
	public static MenuRaccourcis getInstance(String login) {
		if (instance == null)
			instance = new MenuRaccourcis(login);
		return instance;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	
}
