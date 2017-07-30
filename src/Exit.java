import processing.core.PApplet;
import processing.core.PVector;

public class Exit implements iObserver{

	Scene s; 
	Slinger g; 
	
	PVector pos = new PVector(0,0); 
	int size = 20; 
	int haloSize = 1; 
	
	public Exit(Scene s, Slinger g, PVector pos){
		this.s = s;
		this.g = g;
		this.pos = pos; 
	}
	
	public void update(){
		float dist = pos.copy().sub(s.ship.pos).mag();
		if (dist < size){
			s.goal();
		}
	}
	
	public void draw(){
		g.pushMatrix();
		g.noStroke();
		drawHalo();
		g.fill(255);
		g.rect(pos.x, pos.y, size, size);
		g.popMatrix();
	}
	
	public void drawHalo(){
		
		if(haloSize > 5) haloSize = 1;
		g.fill(255, PApplet.constrain(haloSize * 100, 0, 100));
		g.rect(pos.x - haloSize, pos.y - haloSize, size + 2*haloSize, size + 2*haloSize);
		if(g.frameCount % 5 == 0) haloSize++;
		
	}
	
	public void inform(int keycode){}
}