import processing.core.PApplet;
import processing.core.PVector;

public class Planet implements iObserver{

	Scene s;
	Slinger g; 
	Planet parent = null;
	PVector pos = new PVector(0,0);
	PVector acc = new PVector(0,0);
	PVector vel = new PVector(0,0);
	
	
	float radius;
	float mass; 
	float gravity;
	float influence;
	float orbitspeed = .001f;
	float angle = 0;
	
	public Planet(Scene s, Slinger g, PVector pos, float radius, float mass){
		this.s = s;
		this.g = g; 
		this.pos = pos;
		this.radius = radius; 
		this.mass = mass;
		this.gravity = mass * 1.2f;
		this.influence = radius * 4;
	}
	
	public void update(){
		checkCollision();
		exertGravity();
		if(parent != null) orbitParent();
	}
	
	public void draw(){
		g.pushMatrix();
		int r = (int) processing.core.PApplet.constrain(this.mass * 2, 0, 255);
		int green = (int) processing.core.PApplet.constrain(this.mass * 5, 0, 255);
		int b = (int) processing.core.PApplet.constrain(this.mass * 3, 0, 255);
		if(parent != null) {
			g.translate(parent.pos.x, parent.pos.y);
			g.rotate(angle);
			g.fill(r,green,b);
			g.ellipse(pos.x - parent.pos.x, pos.y - parent.pos.y, radius, radius);
			g.fill(r,green,b, 50);
			g.ellipse(pos.x - parent.pos.x, pos.y - parent.pos.y, influence, influence);
			g.popMatrix();
		} else {
			g.fill(r,green,b);
			g.ellipse(pos.x, pos.y, radius, radius);
			g.fill(r,green,b, 50);
			g.ellipse(pos.x, pos.y, influence, influence);
			g.popMatrix();
		}
		
	}
	
	public void setParent(Planet p){
		parent = p;
		angle = .0001f;
	}
	
	public void orbitParent(){
		angle = angle + orbitspeed;
	}
	
	public void checkCollision(){
		PVector sp = s.ship.pos;
		PVector p = pos.copy();
		if (parent != null) p = p.add(parent.pos);
		float dist = p.sub(sp).mag();
		if(dist < radius){
			s.reset();
		}
	}
	
	public void exertGravity(){
		PVector sp = s.ship.pos;
		float dist = pos.copy().sub(sp).mag();
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
	
	
	
	public void inform(int keycode){}
	
}
