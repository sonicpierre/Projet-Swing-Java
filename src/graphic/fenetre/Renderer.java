package graphic.fenetre;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Renderer extends Canvas {
	
	//Variable pas directement utile mais pour relire le code pratique 
	
	public static final int TOP_LAYER = 2;
	public static final int CHARACTER_LAYER = 1;
	public static final int BACKGROUND_LAYER = 0;
	private static final int MAX_LAYER = 3;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	//Tentative de fenêtre adaptable
	
	private static final Color backgroundColor = new Color(100, 100, 100);
	private static final Color transparentColor = new Color(0, 0, 0, 0);

	private static Renderer instance;

	//BufferedImage représente une image et permet de par relire le fichier à chaque fois
	
	private BufferedImage[] layers; // Chaque image représente un calque.
	private BufferedImage image;
	
	private List<IRenderable> elements;

	private Renderer() {
		elements = new ArrayList<IRenderable>();
		layers = new BufferedImage[MAX_LAYER];
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < MAX_LAYER; ++i)
			layers[i] = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	}

	public void render(){
		
		//On peint le fond qui correspond à image 
		
		Graphics g = image.getGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		//On initialise les layers comme transparents
		
		for (BufferedImage layer : layers) {
			Graphics2D g2d = (Graphics2D) layer.getGraphics();
			g2d.setBackground(transparentColor);
			g2d.clearRect(0, 0, WIDTH, HEIGHT);
		}
		//On dessine sur le layer
		
		for (IRenderable e : elements) {
			if (e.getLayer() > TOP_LAYER) continue;				//Penser négation si on n'ai pas > faire...
				e.draw(layers[e.getLayer()].getGraphics());			//getGrphic() parce qu'il prend en paramètre des graphiques
			}
		
		//On dessine sur l'image
		
		for (BufferedImage layer : layers)
			g.drawImage(layer, 0, 0, null);
		
	}

	public static Renderer getInstance() {
		if (instance == null)
			instance = new Renderer();
		return instance;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		render();
		g.drawImage(image, 0, 0, null);
	}
	
	public void add(IRenderable renderable) {
		elements.add(renderable);
	}
	
	public void remove(IRenderable renderable) {
		elements.remove(renderable);
	}

}