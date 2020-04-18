package graphic.menusDeuxiemeFenetre;

import java.awt.FlowLayout;

import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBarChanteur extends JToolBar{
	private static ToolBarChanteur instance;
	
	private final String login;
	
	private ToolBarChanteur(String login) {
		this.login = login;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		this.add(MenuRaccourcis.getInstance(login).actPlay);
		this.add(MenuRaccourcis.getInstance(login).actStop);
		this.add(MenuRaccourcis.getInstance(login).actReset);
		this.add(MenuRaccourcis.getInstance(login).actSuppressionAlbum);
	}
	
	
	public static ToolBarChanteur getInstance(String login) {
		if (instance == null)
			instance = new ToolBarChanteur(login);
		return instance;
	}
}
