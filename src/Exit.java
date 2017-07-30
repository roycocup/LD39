import processing.core.PVector;

public class Exit implements iObserver{

	Scene s; 
	Slinger g; 
	
	PVector pos = new PVector(0,0); 
	int size = 20; 
	
	public Exit(Scene s, Slinger g, PVector pos){
		this.s = s;
		this.g = g;
		this.pos = pos; 
	}
	
	public void update(){
		float dist = pos.copy().sub(s.ship.pos).mag();
		if (dist < size){
			s.goal();
		}
	}
	
	public void draw(){
		g.pushMatrix();
		g.noStroke();
		g.fill(255);
		g.rect(pos.x, pos.y, size, size);
		g.popMatrix();
	}
	
	public void inform(int keycode){}
}
