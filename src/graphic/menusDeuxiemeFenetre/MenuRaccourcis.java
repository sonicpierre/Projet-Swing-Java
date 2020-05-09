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
 *<b>MenuRaccourcis</b> est la classe qui permet de lier le côté serveur (actions) et le côté client (bouton). 
 *Son fonctionnement suit celui des listeners, singleton avec deux variables clés : login et artiste
 *@author BUISSON-CHAVOT Julien
 *@version 2.0
 **/
public class MenuRaccourcis {
	
	/**
	 *Déclaration de l'instabce du menu raccourcis
	 **/
	
	private static MenuRaccourcis instance;
	
	/**
	 *Déclaration du login utilisateur
	 **/
	
	private String login;
	
	/**
	 *Déclaration de l'artiste
	 **/
	
	private Artiste artiste;
	
	/**
	 *Création du menu raccourcis
	 *@param login
	 *	Login utilisateur
	 *@param artiste
	 *Artiste
	 **/
	
	private MenuRaccourcis(String login, Artiste artiste) {
		this.login = login;
		this.artiste = artiste;
	}
	
	/**
	 *Création du menu raccourcis
	 *@param login
	 *	Login utilisateur
	 **/
	
	private MenuRaccourcis(String login) {
		this.login = login;
		this.artiste = null;
	}


	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur les paramètres en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actParametre = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Description...");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/personne.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_P);
			/**
			 *Petite fenetre de decription
			 **/
			
			putValue( Action.SHORT_DESCRIPTION, "Description (CTRL+P)");
			
			/**
			 *Raccourci clavier
			 **/
			
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action ouvrir la fenetre de paramètres
		 *@param arg0
		 *	Argument
		 *@see FenetreParametre
		 **/

		public void actionPerformed(ActionEvent arg0) {
			FenetreParametre.getInstance(login).ajoutParametre();
		}
	};

	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur l'labum en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actAjoutAlb = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Ajout album...");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_A);
			putValue( Action.SHORT_DESCRIPTION, "Ajout album (CTRL+A)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action ajout album à la fenetre
		 *@param event
		 *	Action
		 *@see FenetreParametre
		 **/
		
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutAlbumFenetre();
		}
	};

	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur les musiques en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actAjoutMus = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Ajout musique...");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_M);
			putValue( Action.SHORT_DESCRIPTION, "Ajout musique (CTRL+M)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action ajout album à la fenetre
		 *@param event
		 *	Action
		 *@see FenetreParametre
		 **/
		
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutMusiqueFenetre();
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur la deconnexion en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actDeco = new AbstractAction() {


		{//C'est le constructeur
			putValue (Action.NAME, "Deconnexion");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/deconnexion.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_D);
			putValue( Action.SHORT_DESCRIPTION, "Deconnexion (CTRL+Q)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action deconnexion
		 *@param event
		 *	Action
		 *@see TopMenuDescriptif
		 **/
		
		public void actionPerformed(ActionEvent event) {
			TopMenuDescriptif.getInstance(login).deconnexion();
		}
	};

	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur la suppression de musique en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actSuppressionMusique = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Supprimer la musique");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Supprimer.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_S);
			putValue( Action.SHORT_DESCRIPTION, "Supprimer musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action suppression
		 *@param event
		 *	Action
		 *@see MenuMusique
		 **/
		
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Supprimer");
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur la lecture musique de musique en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actPlay = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Play");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Play.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_J);
			putValue( Action.SHORT_DESCRIPTION, "Jouer musique (CTRL+R)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action lecture
		 *@param event
		 *	Action
		 *@see MenuMusique
		 **/
		
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Play");
		}
	};

	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur l'arrêt musique de musique en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actStop = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Stop");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/pause.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_I);
			putValue( Action.SHORT_DESCRIPTION, "Interrupt musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action l'arrêt musique
		 *@param event
		 *	Action
		 *@see MenuMusique
		 **/
		
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Stop");
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur la reprise musique de musique en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actReset = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Reset");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Reset.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_R);
			putValue( Action.SHORT_DESCRIPTION, "Rejouer musique (CTRL+S)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action la reprise musique
		 *@param event
		 *	Action
		 *@see MenuMusique
		 **/
		
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).checkOperation("Reset");
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur le retour en arrière musique de musique en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actBack = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Revenir");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_B);
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/retour.png"));
			putValue( Action.SHORT_DESCRIPTION, "Revenir en arrière (CTRL+B)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		/**
		 *Associe au bouton l'action retour arrière de la musique
		 *@param event
		 *	Action
		 *@see FenetreFond
		 **/
		
		public void actionPerformed(ActionEvent event) {
			FenetreFond.getInstance().retourEtatInitial(login);
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur l'ajout artiste en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actAjoutArtiste = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Ajouter artiste");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_A);
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue( Action.SHORT_DESCRIPTION, "Ajouter artiste (CTRL+A)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action ajout artiste
		 *@param event
		 *	Action
		 *@see FenetreFond
		 **/
		
		
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutArtisteFenetre();
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur contacter artiste en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actContacter = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Contacter artistes");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_C);
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/mail.png"));
			putValue( Action.SHORT_DESCRIPTION, "Contacter artistes (CTRL+C)");
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action contacter artiste
		 *@param event
		 *	Action
		 *@see TopMenuDescriptif
		 **/
		
		public void actionPerformed(ActionEvent event) {
			TopMenuDescriptif.getInstance(login).ouvertureFenetreMail();
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur suppression artiste en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actSupressionArtiste = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Supprimer artiste");
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_S);
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action supprimer artiste
		 *@param event
		 *	Action
		 *@see personnesDejaInscrite
		 *@see MenuPrincipal
		 **/
		
		public void actionPerformed(ActionEvent event) {
			personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getMaListeArtiste().remove(artiste);
			MenuPrincipal.getInstance(login).update();
			personnesDejaInscrite.getInstance().sauvegarder();
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur ajout representation en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actAjoutRepresentation = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Ajout representation");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/Ajouter.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_A);
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action ajout representation
		 *@param event
		 *	Action
		 *@see personnesDejaInscrite
		 *@see MenuPrincipal
		 **/
		
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutRepresentationFenetre();
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée les actions à mener sur remplissage par un fichier CSV en donnant un nom et une icone qui correspont à la saisie clavier
	 **/
	
	public AbstractAction actRemplirParUnCSV = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Remplir a partir d'un CSV");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/csv.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_C);
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		
		/**
		 *Associe au bouton l'action remplissage par CSV
		 *@param event
		 *	Action
		 *@see TopMenuDescriptif
		 **/
		
		
		public void actionPerformed(ActionEvent event) {
			TopMenuDescriptif.getInstance(login).copieEtRemplissage();
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Génère l'interface de remplissage à partir d'une base de données
	 **/
	
	public AbstractAction actRemplirParUneBDD = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Remplir a partir d'une BDD");
			putValue (Action.SMALL_ICON, new ImageIcon("Icons/bdd.png"));
			putValue (Action.MNEMONIC_KEY, KeyEvent.VK_B);
			putValue ( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
		}

		@Override
		/**
		 *Crée l'action de remplissage de la base de données
		 *@see FenetreParametre
		 **/
		
		public void actionPerformed(ActionEvent event) {
			FenetreParametre.getInstance(login).ajoutRenommageFenetre("Remplir à partir d'une BDD", "Nom BDD", "Titre BDD", null, null, null);
		}
	};
	
	@SuppressWarnings("serial")
	
	/**
	 *Crée l'action de changement d'image album
	 **/
	
	public AbstractAction actChangerImage = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Changer image album");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/paste.png"));
		}

		@Override
		
		/**
		 *Permet de définir les actions de changement image sur le menu
		 *@param event
		 *	Evènement
		 *@see MenuMusique
		 **/
		
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).changerImage();
			MenuMusique.getInstance(login).update();
		}
	};
	
	
	@SuppressWarnings("serial")
	
	/**
	 *Permet de créer une action de suppression album
	 **/
	
	public AbstractAction actSuppressionAlbum = new AbstractAction() {

		{//C'est le constructeur
			putValue (Action.NAME, "Supprimer album");
			//putValue (Action.SMALL_ICON, new ImageIcon("icons/paste.png"));
		}

		@Override
		/**
		 *Permet de définir les actions de mise à jour à mener sur l'interface de menu
		 *@param event
		 *	Evènement
		 *@see MenuMusique
		 *@see MenuAjoutMusique
		 *@see MenuPrincipal
		 **/
		
		public void actionPerformed(ActionEvent event) {
			MenuMusique.getInstance(login).supprimerAlbum();
			MenuMusique.getInstance(login).update();
			MenuAjoutMusique.getInstance(login).update();
			MenuPrincipal.getInstance(login).update();
		}
	};
	
	/**
	 *Instanciation du menu de raccourcis pour un artiste en fonction de son login
	 *@param login
	 *	Login utilisateur
	 *@param artiste
	 *	Artiste
	 *@return Menu raccourcis
	 **/
	
	public static MenuRaccourcis getInstance(String login, Artiste artiste) {
		if (instance == null)
			instance = new MenuRaccourcis(login, artiste);
		return instance;
	}
	
	/**
	 *Instanciation du menu raccourcis
	 *@param login
	 *	Login utilisateur
	 *@return Menu raccourcis
	 **/
	
	public static MenuRaccourcis getInstance(String login) {
		if (instance == null)
			instance = new MenuRaccourcis(login);
		return instance;
	}
	
	/**
	 *Récupère l'artiste
	 *@return Artiste
	 **/
	
	public Artiste getArtiste() {
		return artiste;
	}
	
	/**
	 *Initialisation de l'artiste
	 *@param artiste
	 *	Artiste
	 **/
	
	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	
}
