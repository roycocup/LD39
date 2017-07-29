import java.util.ArrayList;

import processing.core.PVector;

public class Scene {

	Slinger g; 
	int sceneNum = 0;
	
	ArrayList<Planet> planets = new ArrayList<Planet>();
	Ship ship; 
	Exit exit; 
	
	public Scene(Slinger g, int sceneNum){
		this.g = g;
		this.sceneNum = sceneNum;
	}
	
	
	
	public void draw(){
		if (sceneNum == 1){
			level1();
		} 
		
		for(Planet p : planets){
			p.draw();
		}
	}
	
	
	protected void level1(){
		Planet p1 = new Planet(g, new PVector(10, 10), 10, 10);
		planets.add(p1);
		
		
		ship = new Ship(g, new PVector(5,5) );
	} 
}
