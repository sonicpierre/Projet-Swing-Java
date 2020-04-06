package control.main;

import graphic.fenetre.Panneau;

public class MoteurGraphique extends Thread{
	
	private static MoteurGraphique instance;

	private boolean isRunning;
	
	private MoteurGraphique() {
		super("Mon Thread Graphique");

		setRunning(true);

		this.start(); // Lancement du run()
	}

	@Override
	public void run() {
		super.run();
		
		

		while (isRunning) {
				
			try {
				Thread.sleep(50); // Permet de déterminer le temps entre chaque regard sur le moteur
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Panneau.getInstance().repaint();
		}
	}

	// Fin de jeu

	public static MoteurGraphique getInstance() {
		if (instance == null)
			instance = new MoteurGraphique();
		return instance;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}