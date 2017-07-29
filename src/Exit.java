import processing.core.PVector;

public class Exit implements iObserver{

	Slinger g; 
	
	PVector pos; 
	int size = 20; 
	
	public Exit(Slinger g, PVector pos){
		this.g = g;
		this.pos = pos; 
	}
	
	public void update(){
		
	}
	
	public void draw(){
		g.pushMatrix();
		g.noStroke();
		g.fill(255);
		g.rect(this.pos.x - size, this.pos.y - size, size, size);
		g.popMatrix();
	}
	
	public void inform(int keycode){}
}
