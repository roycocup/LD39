import java.io.File;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.sound.sampled.*;


public class Audio {

	Slinger g;
	Player player; 
	
	public Audio(Slinger g){
		this.g = g;
		
		try {
			URL url = new File(g.inGameMusic).toURI().toURL();
			MediaLocator ml = new MediaLocator(url);
			player = Manager.createPlayer(ml);
			player.realize();
			player.start();
			player.addControllerListener(new ControllerListener(){
				public void controllerUpdate(ControllerEvent e){
					if (e instanceof EndOfMediaEvent){
						player.stop();
						player.close();
			         }
				}
			});
			
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
