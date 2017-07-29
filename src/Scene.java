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
			this.getClass().getMethod("level"+sceneNum).invoke(this);
		} catch(Exception e){
			e.printStackTrace();
			g.println("No such level/scene");
			System.exit(0);
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
		observers.clear();
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
	
	public void levelSetup(){
		try{
			planets.clear();
			
			if(ship == null)
				ship = new Ship(g, new PVector(500,500) );
			if(exit == null)
				exit = new Exit(this, g, new PVector(50, 50));
			
			registerObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void level1(){
		levelSetup();
		
	}
	
	public void level2(){
		levelSetup();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		exit.pos = new PVector(208, 35);
	}
	
	public void level3(){
		levelSetup();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		planets.add(new Planet(this, g, new PVector(100, g.height/2-100), 30, 30));
		exit.pos = new PVector(208, 35);
		ship.reset(new PVector(600, 500));
	}
	
	public void level4(){
		levelSetup();
		exit.pos = new PVector(g.width/2, 30);
		ship.reset(new PVector(g.width/2, g.height-40));
		
		planets.add(new Planet(this, g, new PVector(g.width/2, 300), 20, 40));
		planets.add(new Planet(this, g, new PVector(367,107), 10, 40));
		planets.add(new Planet(this, g, new PVector(100, g.height/2), 10, 40));
		planets.add(new Planet(this, g, new PVector(145, (g.height/2)+30), 10, 40));
		planets.add(new Planet(this, g, new PVector(534, (g.height/2)+40), 10, 40));
		planets.add(new Planet(this, g, new PVector(192, 144), 10, 40));
		planets.add(new Planet(this, g, new PVector(713, 361), 10, 40));
		planets.add(new Planet(this, g, new PVector(646, 76), 10, 40));
		
		
	}
}
