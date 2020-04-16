package control.musique;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Lecteur extends Thread {
	private final PisteAudio maPiste;
	private AdvancedPlayer player;
	private FileInputStream fileInputStream;

	public Lecteur(PisteAudio maPiste) {
		super("Thread de Son");
		this.maPiste = maPiste;
		try {
			this.fileInputStream = new FileInputStream(maPiste.getFichierAssocie());
			this.player = new AdvancedPlayer(fileInputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.start();
	}

	public void run() {
		super.run();
		jouer(maPiste);
	}

	public void jouer(PisteAudio Piste) {

		try {
			player.play();

		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getExecutorService() {
		// TODO Auto-generated method stub

	}

	public AdvancedPlayer getPlayer() {
		return player;
	}

	public void setPlayer(AdvancedPlayer player) {
		this.player = player;
	}

}
