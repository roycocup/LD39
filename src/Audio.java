import java.io.File;
import java.net.URL;
import javax.media.Manager;
import javax.media.Player;
import javax.sound.sampled.*;


public class Audio {

	Slinger g;
	
	public Audio(Slinger g){
		this.g = g;
		
		try {
			Player player = Manager.createPlayer(g.inGameMusic);
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
