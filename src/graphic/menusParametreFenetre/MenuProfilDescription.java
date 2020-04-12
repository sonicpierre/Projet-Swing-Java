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


@SuppressWarnings("serial")
public class MenuProfilDescription extends JPanel{
	
	private static MenuProfilDescription instance;
	
	private static final int tailleDuneLigne = 75;
	
	private String login;
	private BufferedImage photoProfil; 
	private String description;
	private JTextArea zoneEdition;
	
	private MenuProfilDescription(String login) {
		this.login = login;
		this.setLayout(new BorderLayout());
		this.setAutoscrolls(true);
		description = personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getDescription();
		this.add(bouttons(), BorderLayout.PAGE_END);
		chargerImage();
	}
	
	
	//Va permettre de dessiner joliment les paramètres du compte utilisateur
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		g.drawImage(photoProfil, 5, 10, null);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		g.setColor(new Color(100,100,100));
		g.drawString(login, photoProfil.getWidth() + 15, photoProfil.getHeight()/2);
		g.drawString(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getAdresseMail(), photoProfil.getWidth() + 15, photoProfil.getHeight()/2 + 15);
		g.drawString(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getTalent(), photoProfil.getWidth() + 15, photoProfil.getHeight()/2 + 30);
		
		String[] monTabFinalEcrie = description.split("\n");
		List<String[]> maListFinale = new ArrayList<String[]>();
		
		for(String monString : monTabFinalEcrie) {
			StringBuffer descriptionTab = new StringBuffer(monString);
			for(int i=tailleDuneLigne; i<descriptionTab.length(); i+=tailleDuneLigne)
				descriptionTab.insert(i, "\n");
			monString = descriptionTab.toString();
			maListFinale.add(monString.split("\n"));
		}
		
		int compteur = 0;
		int espaceInterLigne = 200;
		for(String[] monTabString : maListFinale) {
			for(String monString : monTabString) {
				if(compteur<14) {
					g.drawString(monString, 10, espaceInterLigne);
					espaceInterLigne+=14;
					compteur++;
				}
			}
		}

			
	}
	
	
	private void changerLaPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).setCheminVersImage(selectedFile.getAbsolutePath());
		}
	    chargerImageNouvelle();
	    personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).setCheminVersImage("ImageProfil/"+login+".png");
		personnesDejaInscrite.getInstance().sauvegarder();
	    this.repaint();
	}
	
	//Charge l'image et la redimmentionne pour qu'elle rentre bien dans la description
	
	private void chargerImageNouvelle() {
		
		try {
			photoProfil = ImageIO.read(new File(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getCheminVersImage()));
			BufferedImage nouvelleImage=redimentionne(photoProfil, (double) 150/photoProfil.getWidth(), (double) 150/photoProfil.getHeight());
			ImageIO.write(nouvelleImage, "png", new File("ImageProfil/"+login+".png"));
			photoProfil = ImageIO.read(new File("ImageProfil/"+login+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
	}
	
	private void chargerImage() {
		try {
			photoProfil = ImageIO.read(new File(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getCheminVersImage()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private JPanel bouttons() {
		JPanel tempon = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton changerPhotoBoutton = new JButton("Changer Photo");
		JButton edit = new JButton("Edit");
		JButton exit = new JButton("Exit");
		
		changerPhotoBoutton.addActionListener((event)->changerLaPhoto());
		edit.addActionListener((event)->passerEnModeEdition());
		
		exit.addActionListener((event)->FenetreParametre.getInstance(login).dispose());
		tempon.add(changerPhotoBoutton);
		tempon.add(edit);
		tempon.add(exit);
		
		return tempon;
	}
	
	
	
    public static BufferedImage redimentionne(BufferedImage imageDeBase, double factorx, double factory) {
        int destWidth=(int) (imageDeBase.getWidth() * factorx);
        int destHeight=(int) (imageDeBase.getHeight() * factory);
        //créer l'image de destination
        GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage bImageNew = configuration.createCompatibleImage(destWidth, destHeight);
        Graphics2D graphics = bImageNew.createGraphics();
        //On utilise l'interpolation pour rajouter on enlever les pixels
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        //dessiner l'image de destination
        graphics.drawImage(imageDeBase, 0, 0, destWidth, destHeight, 0, 0, imageDeBase.getWidth(), imageDeBase.getHeight(), null);
        //On libère le dessinateur
        graphics.dispose();
 
        return bImageNew;
    }
    
    private void passerEnModeEdition() {
    	JPanel edition = new JPanel(new BorderLayout());
    	zoneEdition = new JTextArea(description);
    	zoneEdition.setLineWrap(true);
    	JButton valider = new JButton("Valider");
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
    	this.validate();
    	this.repaint();
    }
    
	public static MenuProfilDescription getInstance(String login) {
		if (instance == null)
			instance = new MenuProfilDescription(login);
		return instance;
	}
}
