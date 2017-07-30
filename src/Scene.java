import java.util.ArrayList;
import processing.core.PVector;

public class Scene {

	Slinger g; 
	int sceneNum = 0;
	int attempts = 0;
	
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
			System.out.println("No such level/scene");
			System.exit(0);
		}
		
	}
	
	public void onKeyPressed(int key){
		informObservers(key);
		if (key == 32 && sceneNum == 0){
			sceneNum++;
			loadScene();
		}
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
		if(ship != null){
			ship.update();
			checkBoundaries();
		}
			
		if(exit != null)
			exit.update();
		
		ui.update();
		
	}
	
	public void draw(){
		ui.draw();
		for(Planet p : planets){
			p.draw();
		}
		if(ship != null)
			ship.draw();
		if(exit != null)
			exit.draw();
	}
	
	protected void checkBoundaries(){
		if (ship.pos.x < 0 || ship.pos.y < 0 ||
				ship.pos.x > g.width || ship.pos.y > g.height){
			reset();
		}
	}
	
	public void reset(){
		attempts++;
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
	
	public void gameOver(){
		ui.gameOver();
	}
	
	public void levelSetup(){
		try{
			planets.clear();
			
			if(ship == null)
				ship = new Ship(this, g, new PVector(500,500) );
			if(exit == null)
				exit = new Exit(this, g, new PVector(50, 70));
			
			registerObservers();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void level0(){
		ui.mainMenu();
	}
	
	public void level1(){
		ui.startGame();
		levelSetup();
	}
	
	public void level2(){
		levelSetup();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		exit.pos = new PVector(200, 46);
	}
	
	public void level3(){
		levelSetup();
		planets.add(new Planet(this, g, new PVector(g.width/2, g.height/2), 30, 80));
		planets.add(new Planet(this, g, new PVector(255, 124), 30, 30));
		exit.pos = new PVector(190, 60);
		ship.reset(new PVector(600, 500));
	}
	
	public void level4(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(390, 50);
		
		planets.add(new Planet(this, g, new PVector(g.width/2, 300), 20, 40));
		planets.add(new Planet(this, g, new PVector(370,107), 10, 40));
		planets.add(new Planet(this, g, new PVector(100, g.height/2), 10, 40));
		planets.add(new Planet(this, g, new PVector(145, (g.height/2)+30), 10, 40));
		planets.add(new Planet(this, g, new PVector(534, (g.height/2)+40), 10, 40));
		planets.add(new Planet(this, g, new PVector(192, 144), 10, 40));
		planets.add(new Planet(this, g, new PVector(713, 361), 10, 40));
		planets.add(new Planet(this, g, new PVector(646, 76), 10, 40));
		
	}
	
	public void level5(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(389,135);
		
		planets.add(new Planet(this, g, new PVector(717,194), 30, 20));
		planets.add(new Planet(this, g, new PVector(267,117), 20, 20));
		planets.add(new Planet(this, g, new PVector(594,280), 20, 20));
		planets.add(new Planet(this, g, new PVector(381,200), 20, 80));
		planets.add(new Planet(this, g, new PVector(400,470), 20, 80));
		planets.add(new Planet(this, g, new PVector(241,317), 20, 80));
		planets.add(new Planet(this, g, new PVector(217,176), 30, 40));
		
		
	}
	
	public void level6(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(390, 280);
		
		planets.add(new Planet(this, g, new PVector(717,194), 30, 200));
		planets.add(new Planet(this, g, new PVector(267,117), 20, 20));
		planets.add(new Planet(this, g, new PVector(627,113), 20, 200));
		planets.add(new Planet(this, g, new PVector(381,200), 20, 80));
		planets.add(new Planet(this, g, new PVector(400,470), 20, 80));
		planets.add(new Planet(this, g, new PVector(241,317), 20, 80));
		planets.add(new Planet(this, g, new PVector(217,176), 30, 40));
		
	}
	
	public void level7(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(390, 46);
		
		planets.add(new Planet(this, g, new PVector(g.width/2, 300), 20, 40));
		planets.add(new Planet(this, g, new PVector(367,107), 10, 40));
		planets.add(new Planet(this, g, new PVector(100, g.height/2), 10, 40));
		planets.add(new Planet(this, g, new PVector(145, (g.height/2)+30), 10, 40));
		planets.add(new Planet(this, g, new PVector(534, (g.height/2)+40), 10, 40));
		planets.add(new Planet(this, g, new PVector(192, 144), 10, 40));
		planets.add(new Planet(this, g, new PVector(713, 361), 10, 40));
		planets.add(new Planet(this, g, new PVector(646, 76), 10, 40));
		
	}
	
	public void level8(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(390, 46);
		
		planets.add(new Planet(this, g, new PVector(g.width/2, 300), 20, 40));
		planets.add(new Planet(this, g, new PVector(367,107), 10, 40));
		planets.add(new Planet(this, g, new PVector(100, g.height/2), 10, 40));
		planets.add(new Planet(this, g, new PVector(145, (g.height/2)+30), 10, 40));
		planets.add(new Planet(this, g, new PVector(534, (g.height/2)+40), 10, 40));
		planets.add(new Planet(this, g, new PVector(192, 144), 10, 40));
		planets.add(new Planet(this, g, new PVector(713, 361), 10, 40));
		planets.add(new Planet(this, g, new PVector(646, 76), 10, 40));
		
	}
	
	public void level9(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(390, 46);
		
		planets.add(new Planet(this, g, new PVector(g.width/2, 300), 20, 40));
		planets.add(new Planet(this, g, new PVector(367,107), 10, 40));
		planets.add(new Planet(this, g, new PVector(100, g.height/2), 10, 40));
		planets.add(new Planet(this, g, new PVector(145, (g.height/2)+30), 10, 40));
		planets.add(new Planet(this, g, new PVector(534, (g.height/2)+40), 10, 40));
		planets.add(new Planet(this, g, new PVector(192, 144), 10, 40));
		planets.add(new Planet(this, g, new PVector(713, 361), 10, 40));
		planets.add(new Planet(this, g, new PVector(646, 76), 10, 40));
		
	}
	
	public void level10(){
		levelSetup();
		ship.reset(new PVector(g.width/2, g.height-40));
		exit.pos = new PVector(390, 50);
		
		Planet p1 = new Planet(this, g, new PVector(g.width/2, g.height/2), 20, 80);
		planets.add(p1);
		
		Planet p2 = new Planet(this, g, new PVector(0,0), 20, 200);
		p2.setPos(new PVector(357,256));
		p2.setParent(p1);
		planets.add(p2);
		
	}

	public void level11(){
		gameOver();
	}
}
