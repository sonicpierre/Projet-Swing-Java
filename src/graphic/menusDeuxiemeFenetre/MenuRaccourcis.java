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
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/copy.png"));
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
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_S);
			putValue( Action.SHORT_DESCRIPTION, "Supprimer musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			MenuChanteur.getInstance(login).checkSupprimer();
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
			System.out.println("coucou");
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
			System.out.println("coucou");
		}
	};
	
	@SuppressWarnings("serial")
	public AbstractAction actRenommer = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Renomer");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/paste.png"));
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("coucou");
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
