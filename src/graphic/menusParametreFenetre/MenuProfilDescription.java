package graphic.menusParametreFenetre;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import control.elementSauv.personnesDejaInscrite;
import control.personne.Artiste;
import graphic.fenetre.FenetreParametre;


/**
 *<b>MenuProfilDescription</b> est la classe qui permet la création de la fenetre 
 *de description utilisateur.
 *@author VIRGAUX Pierre
 **/

@SuppressWarnings("serial")
public class MenuProfilDescription extends JPanel{
	
	private static MenuProfilDescription instance;
	
	/**
	 *Initialisation de la taille de la ligne selon celle de la fenetre
	 **/
	
	private static final int tailleDuneLigne = 75; 
	
	/**
	 *Définition du login utilisateur
	 **/
	
	private String login;
	
	/**
	 *Classe qui permet de redilensionner l'image, car action impossible avec la classe image.
	 **/
	
	private BufferedImage photoProfil;
	
	/**
	 *Définition de la description
	 **/
	
	private String description;
	
	/**
	 *Définition de la zone de saisie de texte
	 **/
	
	private JTextArea zoneEdition;
	
	/**Permet d'indentifier l'utilisateur grâce à son login.
	 *A partir du login on obtient le mot de passe, l'e-mail et le talent de l'utilisateur
	 *@param login
	 *	Login utilisateur
	 **/
	
	private Artiste artiste;
	
	private MenuProfilDescription(String login) {
		this.artiste = null;
		this.login = login;
	}
	
	
	@Override
	
	/**Permet de dessiner de manière plus agréable les paramètres du compte utilisateur.
	 * L'image de l'utilisateur sera aussi créer avec une position précise et en pixel
	 *@param g
	 *	Librairie graphique
	 *Il incluit une méthode qui resoud le problème du retour chariot.
	 **/
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/**
		 *Dessin de l'image
		 **/
		
		g.drawImage(photoProfil, 5, 10, null);
		
		/**
		 *Définition de la police
		 **/
		
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		/**
		 *Définition de la couleur de la police
		 **/
		
		g.setColor(new Color(100,100,100));
		g.drawString(artiste.getNom() + " " + artiste.getPrenom(), photoProfil.getWidth() + 15, photoProfil.getHeight()/2);
		g.drawString(artiste.getAdresseMail(), photoProfil.getWidth() + 15, photoProfil.getHeight()/2 + 30);
		
		/**
		 *Création d'un tableau de string qu'on split par rapport à tous les retours chariots.
		 *Ce qui permet la création d'un tabeau de mot de la phrase.
		 **/
		
		String[] monTabFinalEcrie = description.split("\n");
		
		/**
		 *Tableau de tableau vide
		 **/
		
		List<String[]> maListFinale = new ArrayList<String[]>();
		
		/**
		 *Création d'une boucle allant de ligne en ligne pour rajouter manuellement un retour chariot tous les 75 caractères
		 **/
		
		for(String monString : monTabFinalEcrie) {
			/**
			 *Tableau de caractères
			 **/
			
			StringBuffer descriptionTab = new StringBuffer(monString);
			for(int i=tailleDuneLigne; i<descriptionTab.length(); i+=tailleDuneLigne)
				
				/**
				 *Insertion à "i" d'un retour chariot
				 **/
				
				descriptionTab.insert(i, "\n");
			
			/**
			 *Retransformation de StringBuffer en String notmal
			 **/
			
			monString = descriptionTab.toString();
			
			/**
			 *Split en prenant tous les \n
			 **/
			
			maListFinale.add(monString.split("\n"));
		}
		
		/**
		 *Tableau ayant chaque case étant un tableau de phrase dont la taille 
		 *correspond à celle de la ligne. 
		 *NB : On utilise un compteur pour vérifier le non dépassement du maximum de la ligne
		 **/
		
		int compteur = 0;
		
		/**
		 *Point de départ
		 **/
		
		int espaceInterLigne = 200;
		for(String[] monTabString : maListFinale) {
			for(String monString : monTabString) {
				if(compteur<14) {
					g.drawString(monString, 10, espaceInterLigne);
					
					/**
					 *Espace ente chaque ligne
					 **/
					
					espaceInterLigne+=14;
					
					/**
					 *Saut de ligne
					 **/
					
					compteur++;
				}
			}
		}

			
	}
	
	/**
	 *Permet de changer la photo de l'utilisateur selon le processus suivant : 
	 *<ul>
	 *<li>Ouverture de la navigation de fichier</li>
	 *<li>Choix du fichier à selectionner</li>
	 *<li>Indexation du fichier</li>
	 *<li>Redefinition du chemin</li>
	 *</ul>
	 *@see paintComponent
	 **/
	
	private void changerLaPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).setCheminVersImage(selectedFile.getAbsolutePath());
		}
	    chargerImageNouvelle();
	    personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).setCheminVersImage("ImageProfil/"+login+".png");
		personnesDejaInscrite.getInstance().sauvegarder();
	    this.repaint();
	}
	
	/**Permet de charger l'image et de la redimmentionner.
	 *Ainsi elle correspondrait bien dans l'espace de description.
	 *@see paintComponent
	 **/
	
	private void chargerImageNouvelle() {
		
		try {
			
			/**
			 *Photo obtenue avec le nouveau chemin
			 **/
			
			photoProfil = ImageIO.read(new File(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getCheminVersImage()));
			
			/**
			 *Redimensionne l'image avec une image de 150 pixels par 150
			 **/
			
			BufferedImage nouvelleImage=redimentionne(photoProfil, (double) 150/photoProfil.getWidth(), (double) 150/photoProfil.getHeight());
			
			/**
			 *Enregistrement de l'image
			 **/
			
			ImageIO.write(nouvelleImage, "png", new File("ImageProfil/"+artiste.getNom()+".png"));
			
			/**
			 *Renommage de l'image
			 **/
			
			photoProfil = ImageIO.read(new File("ImageProfil/"+artiste.getNom()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 *Appel de paintComponent pour redessiner l'image
		 **/
		
		artiste.setCheminVersImage("ImageProfil/"+artiste.getNom()+".png");
		this.repaint();
	}
	
	
	/**
	 *Permet de définir les boutons de la fenêtre
	 *@return Bouton centré
	 **/
	
	private JPanel bouttons() {
		JPanel tempon = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		/**
		 *Change la photo
		 **/
		
		JButton changerPhotoBoutton = new JButton("Changer Photo");
		JButton edit = new JButton("Edit");
		
		/**
		 *Permet de rendre la fenetre invisible
		 **/
		
		JButton exit = new JButton("Exit");
		
		changerPhotoBoutton.addActionListener((event)->changerLaPhoto());
		edit.addActionListener((event)->passerEnModeEdition());
		
		exit.addActionListener((event)->FenetreParametre.getInstance(login).dispose());
		tempon.add(changerPhotoBoutton);
		tempon.add(edit);
		tempon.add(exit);
		
		return tempon;
	}
	
	
	/**
	 *Permet de redimentionner l'image importée.
	 *@param imageDeBase
	 *	Image initiale
	 *@param factorx
	 *	Facteur de redimention de la largeur
	 *@param factory
	 *	Facteur de redimention de la hauteur
	 *@return Image modifiée
	 **/
	
    public static BufferedImage redimentionne(BufferedImage imageDeBase, double factorx, double factory) {
    	
    	/**
    	 *Largeur finale
    	 **/
    	
        int destWidth=(int) (imageDeBase.getWidth() * factorx);
        
        /**
         *Hauteur finale
         **/
        
        int destHeight=(int) (imageDeBase.getHeight() * factory);
        
        /**Création de l'image de destination
         **/
        
        GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage bImageNew = configuration.createCompatibleImage(destWidth, destHeight);
        Graphics2D graphics = bImageNew.createGraphics();
        
        /**
         * Utilisation de l'interpolation pour rajouter ou enlever les pixels CREATION NOUVELLE IMAGE
         **/
        
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        
        /**
         *Dessin de l'image de destination
         */
        
        graphics.drawImage(imageDeBase, 0, 0, destWidth, destHeight, 0, 0, imageDeBase.getWidth(), imageDeBase.getHeight(), null);
        
        /**On libère le dessinateur
         **/
        
        graphics.dispose();
 
        return bImageNew;
    }
    
    /**
     *Permet le passage de la fenetre en mode édition et saisie texte.
     **/
    
    private void passerEnModeEdition() { 
    	JPanel edition = new JPanel(new BorderLayout());
    	zoneEdition = new JTextArea(description);
    	
    	/**
    	 *Permet le retour chariot
    	 **/
    	
    	zoneEdition.setLineWrap(true);
    	
    	/**
    	 *Bouton de validation édition
    	 **/
    	
    	JButton valider = new JButton("Valider");
    	valider.addActionListener((event)->validationEdition());
    	edition.add(zoneEdition, BorderLayout.CENTER);
    	edition.add(valider, BorderLayout.SOUTH);
    	this.add(edition);
    	/**Permet de recalculer la place des différents Panels
    	 **/
    	
    	this.validate();
    }
    
    /**
     *<p>Permet comme son nom l'indique de valider l'édition de la fenetre puis de 
     *sauvegarder les informations</p>
     **/
    
    private void validationEdition() {
    	personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).setDescription(zoneEdition.getText());
    	description = zoneEdition.getText();
    	personnesDejaInscrite.getInstance().sauvegarder();
    	this.remove(this.getComponentCount() - 1);
    	
    	/**
    	 *Recalcul de la fenetre et des espaces ente chaque panel
    	 **/
    	
    	this.validate();
    	
    	/**
    	 *Rappel de paintComponent pour l'édition de la description
    	 *@see paintComponent
    	 **/
    	
    	this.repaint();
    }
    
    
    
    
    public Artiste getArtiste() {
		return artiste;
	}
    
    


	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
		if(artiste != null) {
			this.setLayout(new BorderLayout());
			this.setAutoscrolls(true);
			this.add(bouttons(), BorderLayout.PAGE_END);
			File imageProfil = new File("ImageProfil/"+artiste.getNom()+".png");
			if(!imageProfil.exists())
				chargerImageNouvelle();
			else
				try {
					photoProfil = ImageIO.read(new File("ImageProfil/"+artiste.getNom()+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			description = personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).rechercher(artiste).getDescription();
		}
	}


	/**
     *Instances du menu de profil utilisateur
     *@return Le menu de description utilisateur
     **/
    
	public static MenuProfilDescription getInstance(String login) {
		if (instance == null)
			instance = new MenuProfilDescription(login);
		return instance;
	}
}
