import java.net.URL;
import javax.sound.sampled.*;


public class Audio {

	URL inGameMusic; 
	
	public Audio(){
		try {
	        inGameMusic = this.getClass().getClassLoader().getResource("TESTER.wav");
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream(inGameMusic);
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioIn);
	        clip.start();
	     } catch (Exception e) {
	        e.printStackTrace();
	     }
	}
	
	
	public void update(){
		
	}
	
	public void draw(){}
	
}
