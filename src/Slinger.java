import java.io.File;
import java.net.URL;

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
	
	public void settings(){
		size(800, 600);
	}

	public void setup(){
		this.scene = new Scene(this, 1); // 0 = main menu, 1 = level 1
		
		background = loadImage("data/background.png");
		f1 = createFont("data/1900805.ttf", 32);
		f2 = createFont("data/F25_Bank_Printer.ttf", 24);
		f3 = createFont("data/F25_Bank_Printer_Bold.ttf", 24);
		inGameMusic = "data/SLINGER.wav";
		//inGameMusic = this.getClass().getClassLoader().getResource("../data/SLINGER.wav");
		
		
		audio = new Audio(this);
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
	}
	
	public void drawAll(){
		scene.draw();
	}
	
	public void keyPressed(){
//		println(keyCode);
		scene.onKeyPressed(keyCode); 
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
