import processing.core.PApplet;
import processing.core.PVector;

public class Slinger extends PApplet {

	Scene scene;
	
	Boolean DEBUG = true; 
	
	public void settings(){
		size(800, 600);
	}

	public void setup(){
		this.scene = new Scene(this, 1); // 0 = main menu, 1 = level 1
	}
	
	
	public void draw(){
		background(0);
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
		PVector mp = new PVector(mouseX, mouseY);
		//println(mp);
	} 
	
	public static void main(String[] args) {
		PApplet.main("Slinger");
	}
	

}
