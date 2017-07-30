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
	float orbitspeed = .005f;
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
		if(g.DEBUG == false){
			checkCollision();
			exertGravity();
		}
		
		if(parent != null) 
			orbitParent();
		//g.println(getPos());
	}
	
	public void draw(){
		g.pushMatrix();
		int r = (int) processing.core.PApplet.constrain(this.mass * 2, 0, 255);
		int green = (int) processing.core.PApplet.constrain(this.mass * 5, 0, 255);
		int b = (int) processing.core.PApplet.constrain(this.mass * 3, 0, 255);
		if(parent != null) {
			translate(parent.pos.x, parent.pos.y);
			g.rotate(angle);
			g.fill(r,green,b);
			g.ellipse(getPos().x, getPos().y, radius, radius);
			g.fill(r,green,b, 50);
			g.ellipse(getPos().x, getPos().y, influence, influence);
			g.popMatrix();
		} else {
			g.fill(r,green,b);
			g.ellipse(pos.x, pos.y, radius, radius);
			g.fill(r,green,b, 50);
			g.ellipse(pos.x, pos.y, influence, influence);
			g.popMatrix();
		}
	}
	
	protected void translate(float x, float y){
		g.translate(parent.pos.x, parent.pos.y);
	}
	
	public void setParent(Planet p){
		parent = p;
		angle = 1;
	}
	
	public PVector getPos(){
		if (parent != null){
			return pos.copy().sub(parent.pos);
		}
		return pos.copy();
	}
	
	public void setPos(PVector p){
		if (parent != null){
			pos = p.sub(parent.pos);
		}else{
			pos = p;
		}
		
	}
	
	public void orbitParent(){
		angle = angle + orbitspeed;
	}
	
	public void move(){
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
	}
	
	void applyForce(PVector force){
		acc.add(force);
	}
	
	public void checkCollision(){
		PVector sp = s.ship.pos;
		float dist = getPos().sub(sp).mag();
		if(dist < radius){
			s.reset();
		}
	}
	
	public void exertGravity(){
		PVector sp = s.ship.pos;
		float dist = getPos().copy().sub(sp).mag();
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
