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
	
	PVector acc = new PVector(0,0); 
	PVector vel = new PVector(0,0); 
	PVector pos = new PVector(0,0); 
	
	public Ship(Slinger g, PVector pos){
		this.g = g;
		this.pos = pos;
		this.initialPos = pos.copy();
		mouse = new Mouse(g);
	}
	
	public void reset(){
		pos = initialPos.copy();
		acc = new PVector(0,0); 
		vel = new PVector(0,0); 
	}
	
	public void update(){
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
		// dampening
		vel.mult(dampening);
		mouse.update();
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
		g.stroke(255);
		g.fill(255,0,0);
		int size = 10; 
		float x1 = (this.pos.x - size) ;
		float y1 = (this.pos.y - size) ;
		float x2 = (this.pos.x + size) ;
		float y2 = (this.pos.y - size) ;
		float x3 = (this.pos.x) ;
		float y3 = (this.pos.y + size) ;
		
		//float c = g.cos(pos.copy().angleBetween(vel, vecToMouse()));
		//g.rotate(c);
		g.triangle(x1, y1, x2, y2, x3, y3);
		
	}

	void applyForce(PVector force){
		acc.add(force);
	}
	
	PVector vecToMouse(){
		PVector mp = new PVector(g.mouseX, g.mouseY);
		PVector force = pos.copy().sub(mp).normalize().mult(-1);
		return force;
	}
	
	public void inform(int keycode){
		// if space
		if (keycode == 32){
			shot = true;
			applyForce(vecToMouse().mult(4));
		}
		
		if (keycode == 82 && g.DEBUG){
			reset();
		}
	}
}
