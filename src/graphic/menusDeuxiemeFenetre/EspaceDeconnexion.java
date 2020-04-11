package graphic.menusDeuxiemeFenetre;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import control.elementSauv.personnesDejaInscrite;
import graphic.fenetre.FenetreFond;
import graphic.fenetre.FenetreLogin;


public class EspaceDeconnexion {
	
	String login;
	JPanel monPannelDeconexion;
	JLabel monLabelImage;
	
	public EspaceDeconnexion(String login){
		monPannelDeconexion = new JPanel(new GridLayout(2,1,10,0));

		this.login = login;
		monPannelDeconexion.add(nomEtMetier());
		monPannelDeconexion.add(bouttonDecoEtChoixDePhoto());
	}
	
	private JLabel nomEtMetier() {
		System.out.println(login);
		if(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getCheminVersImage() != null) {
			monLabelImage = new JLabel(personnesDejaInscrite.getInstance().getMaListDePersonneInscrite().get(login).getCheminVersImage());
			monLabelImage.setFocusable(true);
			monLabelImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changerLaPhoto();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				}
			});
			return monLabelImage;
		}
		else {
			monLabelImage = new JLabel(new ImageIcon("ImageDeFond/inconnu.jpg"));
			monLabelImage.setFocusable(true);
			monLabelImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changerLaPhoto();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				}
			});
			return monLabelImage;
		}
	}
	
	private JPanel bouttonDecoEtChoixDePhoto() {
		JPanel panelBouttons = new JPanel(new FlowLayout());
		JButton deconnexion = new JButton("Deconnexion");
		deconnexion.addActionListener((event)->deconnexion());
		panelBouttons.add(deconnexion);
		
		return panelBouttons;
	}
	
	
	public JPanel getMonPannelDeconexion() {
		return monPannelDeconexion;
	}

	public void setMonPannelDeconexion(JPanel monPannelDeconexion) {
		this.monPannelDeconexion = monPannelDeconexion;
	}

	private void deconnexion() {
		FenetreFond.getInstance().changerFenetre(null);
		FenetreLogin.getInstance().setVisible(true);
	}
	
	private void changerLaPhoto() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = jfc.getSelectedFile();
		    System.out.println(selectedFile.getAbsolutePath());
		}
	}
}
