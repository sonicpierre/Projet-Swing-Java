package control.musique;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Lecteur extends Thread {
	private final PisteAudio maPiste;
	
	public Lecteur(PisteAudio maPiste) {
		super("Thread de Son");
		this.maPiste = maPiste;
		this.start();
	}

	public void run() {
		super.run();
		jouer(maPiste);
	}

	public void jouer(PisteAudio Piste) {
		try {
			FileInputStream fileInputStream;
			try {
				fileInputStream = new FileInputStream(Piste.getFichierAssocie());

				AdvancedPlayer player;
				player = new AdvancedPlayer(fileInputStream);
				player.play();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
