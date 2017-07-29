import java.util.ArrayList;

import processing.core.PVector;

public class Scene {

	Slinger g; 
	int sceneNum = 0;
	
	ArrayList<Planet> planets = new ArrayList<Planet>();
	Ship ship; 
	Exit exit; 
	
	ArrayList<iObserver> observers = new ArrayList<iObserver>();
	
	public Scene(Slinger g, int sceneNum){
		this.g = g;
		this.sceneNum = sceneNum;
		
		if (sceneNum == 1){
			level1();
		} 
	}
	
	public void onKeyPressed(int key){
		informObservers(key);
	}
	
	
	public void informObservers(int keycode){
		for(iObserver obs : observers){
			obs.inform(keycode);
		}
	}
	
	public void registerObservers(){
		for(Planet p : planets){
			registerObserver(p);
		}
		
		registerObserver(ship);
		
		registerObserver(exit);
	}
	
	public void registerObserver(iObserver o){
		observers.add(o);
	}
	
	public void deregisterObserver(iObserver o){
		observers.remove(o);
	}
	
	
	public void update(){
		for(Planet p : planets){
			p.update();
		}
		
		ship.update();
		
		exit.update();
		
		checkBoundaries();
	}
	
	public void draw(){
		
		for(Planet p : planets){
			p.draw();
		}
		
		ship.draw();
		
		exit.draw();
	}
	
	
	protected void checkBoundaries(){
		if (ship.pos.x < 0 || ship.pos.y < 0 ){
			reset();
		}
	}
	
	public void reset(){
		ship.reset();
	}
	
	protected void level1(){
		
		planets.add(new Planet(this, g, new PVector(80, 80), 80, 40));
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		
		
		ship = new Ship(g, new PVector(500,500) );
		
		exit = new Exit(g, new PVector(50, 50));
		registerObservers();
	} 
}
