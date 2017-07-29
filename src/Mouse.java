import processing.core.PVector;

public class Mouse implements iObserver{

	Slinger g; 
	
	PVector pos; 
	
	public Mouse(Slinger g){
		this.g = g;
	}
	
	public void update(){
		pos = new PVector(g.mouseX, g.mouseY);
	}
	
	public void draw(){
		g.pushMatrix();
		g.stroke(255);
		g.noCursor();
		g.point(pos.x, pos.y);
		int size = 10; 
		g.line(pos.x - size, pos.y - size, pos.x + size, pos.y + size);
		g.line(pos.x + size, pos.y - size, pos.x - size, pos.y + size);
		g.popMatrix();
	}
	
	public void inform(int keycode) {
		
	}
	
	PVector getMousePos(){
		return new PVector(g.mouseX, g.mouseY);
	}

}
