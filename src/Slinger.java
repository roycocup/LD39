import processing.core.PApplet;

public class Slinger extends PApplet {

	Scene scene; 
	
	public void settings(){
		size(800, 600);
	}

	public void setup(){
		this.scene = new Scene(this, 1); // 0 = main menu, 1 = level 1
	}
	
	
	public void draw(){
		background(0);
		drawAll();
	}
	
	
	public void drawAll(){
		scene.draw();
	}
	
	
	public static void main(String[] args) {
		PApplet.main("Slinger");
	}

}
