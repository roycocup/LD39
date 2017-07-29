import processing.core.PVector;

public class Planet implements iObserver{

	Slinger g; 
	PVector pos; 
	
	float radius;
	float mass; 
	float gravity;
	
	public Planet(Slinger g, PVector pos, float radius, float mass){
		this.g = g; 
		this.pos = pos;
		this.radius = radius; 
		this.mass = mass; 
	}
	
	public void update(){
		
	}
	
	public void draw(){
		g.pushMatrix();
		int r = (int) processing.core.PApplet.constrain(this.radius * 2, 0, 255);
		int green = (int) processing.core.PApplet.constrain(this.radius * 5, 0, 255);
		int b = (int) processing.core.PApplet.constrain(this.radius * 3, 0, 255);
		g.fill(r,green,b);
		g.ellipse(pos.x, pos.y, radius, radius);
		g.popMatrix();
	}
	
	public void inform(int keycode){}
	
}
