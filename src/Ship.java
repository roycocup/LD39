import processing.core.PVector;

public class Ship {

	Slinger g;
	
	float speed; 
	float energy;
	float size = 10;
	
	PVector acc; 
	PVector vel; 
	PVector pos; 
	
	public Ship(Slinger g, PVector pos){
		this.g = g;
		this.pos = pos;
	}
	
	public void update(){
		//applyForce(vecToBall());
		vel.add(acc);
		pos.add(vel);
		acc.mult(0);
	}
	
	public void draw(){
		g.pushMatrix();
		drawShip();
		g.popMatrix();
	}
	
	public void drawShip(){
		g.noStroke();
		g.fill(255,0,0);
		float x1 = (this.pos.x - 1) * size;
		float y1 = (this.pos.y - 1) * size;
		float x2 = (this.pos.x + 1) * size;
		float y2 = (this.pos.y - 1) * size;
		float x3 = (this.pos.x) * size;
		float y3 = (this.pos.y + 1) * size;
		g.triangle(x1, y1, x2, y2, x3, y3);
	}

	void applyForce(PVector force){
		acc.add(force);
	}
}
