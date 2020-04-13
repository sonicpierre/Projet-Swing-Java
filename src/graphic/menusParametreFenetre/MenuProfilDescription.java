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
import graphic.fenetre.FenetreParametre;


/**
 *<b>MenuProfilDescription</b> est la classe qui permet de */

@SuppressWarnings("serial")
public class MenuProfilDescription extends JPanel{
	
	private static MenuProfilDescription instance;
	
	private static final int tailleDuneLigne = 75;//INIT TAILLE LIGNE SELON TAILLE FENETRE 
	
	private String login;
	private BufferedImage photoProfil;//CLASSE QUI PERMET DE REDIMENSIONNER L'IMAGE, C'EST POUR CELA QUON UTILISE PAS LA CLASSE IMAGE
	private String description;
	private JTextArea zoneEdition;
	
	private MenuProfilDescription(String login) {//A PARTIR DU LOGIN ON A LE MDP, EMAIL, TALENT
		this.login = login;
		this.setLayout(new BorderLayout());
		this.setAutoscrolls(true);
		description = personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getDescription();//DESCRIPTION LOGIN DE LA PERSONNE
		this.add(bouttons(), BorderLayout.PAGE_END);
		chargerImage();
	}
	
	
	//Va permettre de dessiner joliment les paramètres du compte utilisateur
	
	@Override
	protected void paintComponent(Graphics g) {//PERMET DE DESSINER L'IMAGE AVEC UNE POSITION PRECISE EN PIXEL
		super.paintComponent(g);
		
		
		g.drawImage(photoProfil, 5, 10, null);//DESSINER IMAGE
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 13));//DEFINITION DE LA POLICE
		g.setColor(new Color(100,100,100));//DEFINITION COULEUR POLICE
		g.drawString(login, photoProfil.getWidth() + 15, photoProfil.getHeight()/2);
		g.drawString(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getAdresseMail(), photoProfil.getWidth() + 15, photoProfil.getHeight()/2 + 15);
		g.drawString(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getTalent(), photoProfil.getWidth() + 15, photoProfil.getHeight()/2 + 30);
		
		String[] monTabFinalEcrie = description.split("\n");//CREATION TABLEAU DE STRING A SPLITER PAR RAPP À TOUS LES /n => TABLEAU DE MOT DE LA PHRASE
		List<String[]> maListFinale = new ArrayList<String[]>();//TABLEAU DE TABLEAU VIDE
		
		for(String monString : monTabFinalEcrie) {//ON FAIT UNE BOUCLE QUI VA DE LIGNE EN LIGNE => RAJOUT MANUELLE D'UN /n TOUS LES 20 CARACTÈRES
			StringBuffer descriptionTab = new StringBuffer(monString);//TABLEAU DE CARACTÈRES
			for(int i=tailleDuneLigne; i<descriptionTab.length(); i+=tailleDuneLigne)
				descriptionTab.insert(i, "\n");//INSERTION A i D'UN /n 
			monString = descriptionTab.toString();//RETRANSFORMATION DU STRINGBUFFER EN STRING NORMAL
			maListFinale.add(monString.split("\n"));//SPLIT EN PRENANT TOUS LES \n
		}
		//TABLEAU AYANT CHAQUE CASSE ETANT UN TABLEAU DE PHRASE DE BONNE TAILLE DE LIGNE 
		int compteur = 0;//UTILSATION D'UN COMPTEUR POUR VERIFIER LE MAXIMUM DE LIGNE
		int espaceInterLigne = 200;//POINT DE DEPART
		for(String[] monTabString : maListFinale) {
			for(String monString : monTabString) {
				if(compteur<14) {
					g.drawString(monString, 10, espaceInterLigne);
					espaceInterLigne+=14;//ENTRE INTERLIGNE 14
					compteur++;//SAUT DE LIGNE
				}
			}
		}

			
	}
	
	
	private void changerLaPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());//PERMET D'AVOIR LE NAVIGATEUR DE FICHIER
		int returnValue = jfc.showOpenDialog(null);//CENTRAGE
		if (returnValue == JFileChooser.APPROVE_OPTION) {//VERIF CHOIX FICHIER
		    File selectedFile = jfc.getSelectedFile();//INDEXATION DU FICHIER SELECTIONNE
		    personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).setCheminVersImage(selectedFile.getAbsolutePath());//LE CHEMIN VERS L'IMAGE EST LE CHEMIN SELECTIONÉ
		}
	    chargerImageNouvelle();
	    personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).setCheminVersImage("ImageProfil/"+login+".png");
		personnesDejaInscrite.getInstance().sauvegarder();
	    this.repaint();
	}
	
	//Charge l'image et la redimmentionne pour qu'elle rentre bien dans la description
	
	private void chargerImageNouvelle() {
		
		try {
			photoProfil = ImageIO.read(new File(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getCheminVersImage()));//PHOTO OBTENUE AVEC LE NOUVEAU CHEMIN
			BufferedImage nouvelleImage=redimentionne(photoProfil, (double) 150/photoProfil.getWidth(), (double) 150/photoProfil.getHeight());//REDIMENSIONNER L'IMAGE AVEC UNE PHOTO DE 150PXL PAR 150
			ImageIO.write(nouvelleImage, "png", new File("ImageProfil/"+login+".png"));//ENREGISTEREMENT DE L'IMAGE
			photoProfil = ImageIO.read(new File("ImageProfil/"+login+".png"));//RENOMMAGE
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();//APPEL DE LA FONCTION PAINTCOMPONENT PERMETTANT DE REDESSINER L'IMAGE
	}
	
	private void chargerImage() {//QUAND ON CHARGE LA PGAE AU DEART, ON CHAREGE L'IAGE QUI ETAIT AVANT
		try {//ON ASSOCIE A UN FICIER POUR OBSERVER LES PERSONNES DJA INSCRITES ET A PARTIR DE LA CLE LOGIN ON RECIPERE NON PAS LA DESCRIPTION MAIS LE CHEMIN DE LA PHOTO
			photoProfil = ImageIO.read(new File(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getCheminVersImage()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private JPanel bouttons() {
		JPanel tempon = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton changerPhotoBoutton = new JButton("Changer Photo");//CHANGER LA PHOTO
		JButton edit = new JButton("Edit");
		JButton exit = new JButton("Exit");//REND INVISIBLE LA FENTRE PARAMETRE
		
		changerPhotoBoutton.addActionListener((event)->changerLaPhoto());
		edit.addActionListener((event)->passerEnModeEdition());
		
		exit.addActionListener((event)->FenetreParametre.getInstance(login).dispose());
		tempon.add(changerPhotoBoutton);
		tempon.add(edit);
		tempon.add(exit);
		
		return tempon;
	}
	
	
	
    public static BufferedImage redimentionne(BufferedImage imageDeBase, double factorx, double factory) {
        int destWidth=(int) (imageDeBase.getWidth() * factorx);//LARGEUR FINALE
        int destHeight=(int) (imageDeBase.getHeight() * factory);//HAUTEUR FINALE
        //créer l'image de destination
        GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage bImageNew = configuration.createCompatibleImage(destWidth, destHeight);
        Graphics2D graphics = bImageNew.createGraphics();
        //On utilise l'interpolation pour rajouter on enlever les pixels CREATION NOUVELLE IMAGE
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        //dessiner l'image de destination
        graphics.drawImage(imageDeBase, 0, 0, destWidth, destHeight, 0, 0, imageDeBase.getWidth(), imageDeBase.getHeight(), null);
        //On libère le dessinateur
        graphics.dispose();
 
        return bImageNew;
    }
    
    private void passerEnModeEdition() {//PRBL : COMMENT FAIRE POUR SAUTER DES LIGNES PUISQUE \n NE FONCTIONNE PAS. 
    	JPanel edition = new JPanel(new BorderLayout());
    	zoneEdition = new JTextArea(description);
    	zoneEdition.setLineWrap(true);//PERMET LE RETOUR CHARIOT
    	JButton valider = new JButton("Valider");//BOUTON DE VALIDATION EDITION
    	valider.addActionListener((event)->validationEdition());
    	edition.add(zoneEdition, BorderLayout.CENTER);
    	edition.add(valider, BorderLayout.SOUTH);
    	this.add(edition);
    	//Permet de recalculer la place des différents Panels
    	this.validate();
    }
    
    private void validationEdition() {
    	personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).setDescription(zoneEdition.getText());
    	description = zoneEdition.getText();
    	personnesDejaInscrite.getInstance().sauvegarder();
    	this.remove(this.getComponentCount() - 1);
    	this.validate();//RECALCUL DE LA FENETRE + ESPACE DE CHAQUE PANEL
    	this.repaint();//RAPPEL DE PAINT COMPONENT POUR L'EDITION DE LA DESCRIPTION ENTRÉE CORRECTEMENT
    }
    
	public static MenuProfilDescription getInstance(String login) {
		if (instance == null)
			instance = new MenuProfilDescription(login);
		return instance;
	}
}
