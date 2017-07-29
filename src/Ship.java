import processing.core.PVector;

public class Ship {

	Slinger g;
	
	float speed; 
	float energy;
	
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
		
	}

	void applyForce(PVector force){
		acc.add(force);
	}
}
