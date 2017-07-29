import processing.core.PVector;

public class Planet implements iObserver{

	Scene s;
	Slinger g; 
	PVector pos; 
	
	float radius;
	float mass; 
	float gravity;
	float influence; 
	
	public Planet(Scene s, Slinger g, PVector pos, float radius, float mass){
		this.s = s;
		this.g = g; 
		this.pos = pos;
		this.radius = radius; 
		this.mass = mass;
		this.gravity = mass;
		this.influence = radius * 4;
	}
	
	public void update(){
		PVector sp = s.ship.pos;
		float dist = pos.copy().sub(sp).mag();
		if(dist < radius){
			s.reset();
		}
		
		if (dist < influence *.5f ){
			s.ship.applyForce(
					pos
					.copy()
					.sub(sp)
					.normalize()
					.mult(gravity)
					.mult(dist)
					.mult(.00003f)
					);
		}
	}
	
	public void draw(){
		g.pushMatrix();
		int r = (int) processing.core.PApplet.constrain(this.mass * 2, 0, 255);
		int green = (int) processing.core.PApplet.constrain(this.mass * 5, 0, 255);
		int b = (int) processing.core.PApplet.constrain(this.mass * 3, 0, 255);
		g.fill(r,green,b);
		g.ellipse(pos.x, pos.y, radius, radius);
		g.fill(r,green,b, 50);
		g.ellipse(pos.x, pos.y, influence, influence);
		g.popMatrix();
	}
	
	public void inform(int keycode){}
	
}
