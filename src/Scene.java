import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

public class Scene {

	Slinger g; 
	int sceneNum = 0;
	
	ArrayList<Planet> planets = new ArrayList<Planet>();
	Ship ship; 
	Exit exit; 
	UI ui; 
	
	ArrayList<iObserver> observers = new ArrayList<iObserver>();
	
	public Scene(Slinger g, int sceneNum){
		this.g = g;
		this.sceneNum = sceneNum;
		this.ui = new UI(this, g);
		loadScene();
	}
	
	public void loadScene(){
		try{
			this.getClass().getMethod("level"+sceneNum, new Class[] {}).invoke(this, new Object[] {});
		} catch(Exception e){
			g.println(e);
			System.exit(500);
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
		ui.update();
		checkBoundaries();
	}
	
	public void draw(){
		ui.draw();
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
	
	public void goal(){
//		playAnimation(){};
//		if (animationPlayed){
		sceneNum++;
		reset();
		loadScene();
//		}
	}
	
	public void level1(){
		//planets.add(new Planet(this, g, new PVector(80, 80), 80, 40));
		ship = new Ship(g, new PVector(500,500) );
		exit = new Exit(this, g, new PVector(50, 50));
		registerObservers();
	}
	
	public void level2(){
		planets.clear();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		exit.pos = new PVector(208, 35);
	}
	
	public void level3(){
		planets.clear();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		exit.pos = new PVector(208, 35);
		ship.reset(new PVector(600, 500));
	}
	
	public void level4(){
		planets.clear();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		exit.pos = new PVector(208, 35);
		ship.reset(new PVector(600, 500));
	}
}
