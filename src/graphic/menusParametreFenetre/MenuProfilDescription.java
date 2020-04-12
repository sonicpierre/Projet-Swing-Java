package graphic.menusParametreFenetre;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import control.elementSauv.personnesDejaInscrite;
import graphic.fenetre.FenetreParametre;


@SuppressWarnings("serial")
public class MenuProfilDescription extends JPanel{
	
	private static MenuProfilDescription instance;
	
	
	private String login;
	private BufferedImage photoProfil; 
	private String description;
	
	private MenuProfilDescription(String login) {
		this.login = login;
		this.setLayout(new BorderLayout());
		this.add(bouttons(), BorderLayout.SOUTH);
		chargerImage();
	}
	
	
	//Va permettre de dessiner joliment les paramètres du compte utilisateur
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(photoProfil, 5, 10, null);
		g.drawString(login, photoProfil.getWidth() + 15, 10);
		g.drawString(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getAdresseMail(), photoProfil.getWidth() + 15, 30);
		g.drawString("Vous n'avez pas de description pour le moment...", 10, 300);
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
			BufferedImage nouvelleImage=redimentionne(photoProfil, 1.5);
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
		
		exit.addActionListener((event)->FenetreParametre.getInstance(login).dispose());
		tempon.add(changerPhotoBoutton);
		tempon.add(edit);
		tempon.add(exit);
		
		return tempon;
	}
	
	
	
    public static BufferedImage redimentionne(BufferedImage imageDeBase, double factor) {
        int destWidth=(int) (imageDeBase.getWidth() * factor);
        int destHeight=(int) (imageDeBase.getHeight() * factor);
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
    
	public static MenuProfilDescription getInstance(String login) {
		if (instance == null)
			instance = new MenuProfilDescription(login);
		return instance;
	}
}
