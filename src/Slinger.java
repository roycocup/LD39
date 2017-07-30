import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Slinger extends PApplet {

	Scene scene;
	
	Boolean DEBUG = true; 
	PImage background;
	PFont f1,f2,f3;
	Audio audio; 
	String inGameMusic;
	int defaultScene = 5;
	
	public void settings(){
		size(800, 600);
	}

	public void setup(){
		this.scene = new Scene(this, defaultScene); // 0 = main menu, 1 = level 1
		
		background = loadImage("data/background.png");
		f1 = createFont("data/1900805.ttf", 10);
		f2 = createFont("data/F25_Bank_Printer.ttf", 10);
		f3 = createFont("data/F25_Bank_Printer_Bold.ttf", 10);
		inGameMusic = "data/SLINGER.wav";		
		
		audio = new Audio(this);
		audio.play();
	}
	
	public void draw(){
		background(0);
		image(background, 0, 0);
		updateAll();
		drawAll();
		debug();
	}
	
	public void updateAll(){
		scene.update();
		audio.update();
	}
	
	public void drawAll(){
		scene.draw();
	}
	
	public void keyPressed(){
//		println(key);
		scene.onKeyPressed(keyCode); 
		
		if (key == 'm'){
			audio.mute();
		}
		if (key == 'p'){
			audio.play();
		}
	}
	
	public void mousePressed(){
		scene.onKeyPressed(32);
	}

	void debug(){
		//PVector mp = new PVector(mouseX, mouseY);
		//println(mp);
	} 
	
	public static void main(String[] args) {
		PApplet.main("Slinger");
	}
	

}
