package graphic.fenetre;

import java.awt.Graphics;

public interface IRenderable {
		
		public void draw(Graphics g);
		
		public int getLayer();
		public int getX();
		public int getY();
}


