import java.io.File;
import java.net.URL;

import javax.media.Manager;
import javax.media.Player;
import javax.sound.sampled.*;


public class Audio {

	Slinger g;
	URL inGameMusic;
	
	public Audio(Slinger g){
		this.g = g;
		
		try {
			inGameMusic = this.getClass().getClassLoader().getResource("SLINGER.wav");
			Player player = Manager.createPlayer(inGameMusic);
			player.realize();
			player.start();
//			AudioInputStream audioIn = AudioSystem.getAudioInputStream(g.inGameMusic);
//	        Clip clip = AudioSystem.getClip();
//	        clip.open(audioIn);
//	        clip.start();
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	}
	
	
	public void update(){
		
	}
	
	public void draw(){}
	
}
