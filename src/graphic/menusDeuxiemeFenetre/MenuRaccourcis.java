package graphic.menusDeuxiemeFenetre;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import graphic.fenetre.FenetreParametre;

public class MenuRaccourcis {

	private static MenuRaccourcis instance;
	private String login;
	private String typeArtiste;
	
	private MenuRaccourcis(String login, String typeArtiste) {
		this.login = login;
		this.typeArtiste = typeArtiste;
	}
	
	private MenuRaccourcis(String login) {
		this.login = login;
		this.typeArtiste = null;
	}


	@SuppressWarnings("serial")
	public AbstractAction actParametre = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Paramètre...");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/file.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_P);
			putValue( Action.SHORT_DESCRIPTION, "Paramètre (CTRL+P)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(login);
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
			TopMenuDescriptif.getInstance(login, typeArtiste).deconnexion();
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
			MenuChanteur.getInstance(login).checkOperation("Supprimer");
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
			MenuChanteur.getInstance(login).checkOperation("Play");
		}
	};

	@SuppressWarnings("serial")
	public AbstractAction actStop = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Stop");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_I);
			putValue( Action.SHORT_DESCRIPTION, "Interrupt musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuChanteur.getInstance(login).checkOperation("Stop");
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
			MenuChanteur.getInstance(login).checkOperation("Reset");
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
			MenuChanteur.getInstance(login).changerImage();
			MenuChanteur.getInstance(login).update();
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
			MenuChanteur.getInstance(login).renommerAlbum();
			MenuChanteur.getInstance(login).update();
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
			MenuChanteur.getInstance(login).supprimerAlbum();
			MenuChanteur.getInstance(login).update();
			MenuAjoutMusique.getInstance(login).update();
		}
	};
	

	
	public static MenuRaccourcis getInstance(String login, String typeArtiste) {
		if (instance == null)
			instance = new MenuRaccourcis(login, typeArtiste);
		return instance;
	}

	
	public static MenuRaccourcis getInstance(String login) {
		if (instance == null)
			instance = new MenuRaccourcis(login);
		return instance;
	}
}
