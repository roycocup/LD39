import java.util.ArrayList;

import processing.core.PVector;

public class Ship implements iObserver{

	Slinger g;
	
	float speed; 
	float energy;
	float size = 10;
	float dampening = .992f;
	
	PVector initialPos = new PVector(0,0);
	Mouse mouse;
	Boolean shot = false;
	ArrayList<PVector> hPos = new ArrayList<PVector>(); 
	
	PVector acc = new PVector(0,0); 
	PVector vel = new PVector(0,0); 
	PVector pos = new PVector(0,0); 
	
	public Ship(Slinger g, PVector pos){
		this.g = g;
		this.pos = pos;
		this.initialPos = pos.copy();
		mouse = new Mouse(g);
		hPos.add(pos);
	}
	
	public void reset(){
		pos = initialPos.copy();
		acc = new PVector(0,0); 
		vel = new PVector(0,0); 
		shot = false;
	}
	
	public void update(){
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
		// dampening
		vel.mult(dampening);
		mouse.update();
		
		PVector lastPos = hPos.get(hPos.size()-1);
		if (lastPos.equals(pos)){
			//g.println(pos);
			hPos.add(pos);
		}
		
	}
	
	public void draw(){
		g.pushMatrix();
		drawShip();
		g.popMatrix();
		drawMouse();
	}
	
	public void drawMouse(){
		mouse.draw();
		if (shot.equals(false)){
			g.stroke(34, 113, 192);
			g.line(pos.x, pos.y, mouse.pos.x, mouse.pos.y);
		}
	}
	
	public void drawShip(){
		int size = 10; 
		float x1 = (this.pos.x - size) ;
		float y1 = (this.pos.y - size) ;
		float x2 = (this.pos.x + size) ;
		float y2 = (this.pos.y - size) ;
		float x3 = (this.pos.x) ;
		float y3 = (this.pos.y + size) ;
		
		//float c = g.cos(pos.copy().angleBetween(vel, vecToMouse()));
		//g.rotate(c);
		
		drawTrail();
		g.stroke(255);
		g.fill(255,0,0);
		g.triangle(x1, y1, x2, y2, x3, y3);
		
	}
	
	void drawTrail(){
		if (shot.equals(true)){
			g.stroke(34, 113, 192);
			for(int i = hPos.size()-1; i > 0; i--){
				PVector cp = hPos.get(i);
				PVector pp = hPos.get(i-1);
				g.line(cp.x, cp.y, pp.x, pp.y);
				//g.println("cp " +cp.x);
				//g.println("pp "+pp.x);
			}
		}
	}

	void applyForce(PVector force){
		acc.add(force);
	}
	
	PVector vecToMouse(){
		PVector mp = new PVector(g.mouseX, g.mouseY);
		PVector dist = pos.copy().sub(mp);
		PVector force = dist.copy().normalize().mult(-1);
		force.mult(dist.copy().mag() * .005f);
		return force;
	}
	
	public void inform(int keycode){
		// if space
		if (keycode == 32 && shot == false){
			shot = true;
			applyForce(vecToMouse().mult(4));
		}
		
		if (keycode == 82 && g.DEBUG){
			reset();
		}
	}
	
}
