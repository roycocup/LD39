import processing.core.PVector;

public class Planet {

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
		g.fill(255);
		g.ellipse(pos.x, pos.y, radius, radius);
		g.popMatrix();
	}
	
	
	
}
