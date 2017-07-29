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
		
		ship.draw();
		
		exit.draw();
	}
	
	
	protected void level1(){
		
		planets.add(new Planet(g, new PVector(80, 80), 50, 50));
		planets.add(new Planet(g, new PVector(g.width/2, g.height/2), 30, 30));
		planets.add(new Planet(g, new PVector(g.width/2, g.height/2), 30, 30));
		
		ship = new Ship(g, new PVector(50,50) );
		
		exit = new Exit(g, new PVector(g.width, g.height));
	} 
}
