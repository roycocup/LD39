import java.io.File;
import java.net.URL;

import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;


public class Audio {

	Slinger g;
	Player player; 
	
	public Audio(Slinger g){
		this.g = g;
		
		try {
			URL url = new File(g.inGameMusic).toURI().toURL();
			MediaLocator ml = new MediaLocator(url);
			player = Manager.createPlayer(ml);
			player.addControllerListener(new ControllerListener(){
				public void controllerUpdate(ControllerEvent e){
					if (e instanceof EndOfMediaEvent){
						player.stop();
						player.close();
			         }
				}
			});
			
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	}
	
	public void mute(){
		player.stop();
	}
	
	public void play(){
		player.start();
	}
	
	public int getState(){
		return player.getState();
	}
	
	public void update(){
		if (player.getState() == Controller.Started){}
	}
	
	public void draw(){}
	
}
