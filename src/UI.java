public class UI {
	Scene s;
	Slinger g;
	float alpha = 0; 
	boolean gameOverActive = false;
	boolean	mainMenuActive = false; 
	
	public UI(Scene s, Slinger g){
		this.s = s; 
		this.g = g;
	}
	
	public void update(){
		alpha = 100;
		if (s.ship != null && s.ship.shot == true){
			alpha = 50; 
		} 
	}
	
	public void draw(){
		g.pushMatrix();
		if (mainMenuActive){
			drawMM();
		} else if(gameOverActive){
			drawGO();
		}else{
			drawFrame();
			drawData();
			if(g.DEBUG) drawDebug();
		}
		
		g.popMatrix();
	}
	
	public void drawDebug(){
		int[] fs = getFrameSpace();
		g.text("Mouse " + g.mouseX +" / "+ g.mouseY, fs[0] + 20, fs[1] + 100);
	}
	
	public int[] getFrameSpace(){
		int x1 = 1, y1 = 1, x2 = g.width-1, y2 = 30;
		int[] frameSpace = {x1,y1,x2, y2};
		return frameSpace;
	}
	
	public void drawFrame(){
		// frame
		int[] fs = getFrameSpace();
		g.stroke(180, alpha);
		g.fill(175, alpha);
		g.rect(fs[0], fs[1], fs[2], fs[3]);
	}
	
	public void drawData(){
		// data
		int[] fs = getFrameSpace();
		g.textFont(g.f2);
		g.stroke(255,255,255,alpha);
		g.fill(255, alpha);
		StringBuilder str = new StringBuilder();
		str.append(" Level " + s.sceneNum );
		str.append(" Attempts " + s.attempts);
		str.append(" Time " + g.second());
		g.textAlign(g.RIGHT);
		g.text(str.toString(), g.width - 20, fs[1] + 20);
		
		// power
		g.textAlign(g.LEFT);
		String power = " Power " + (s.ship.vel.mag() * 100);
		g.text(power, 20, fs[1] + 20);
	}
	
	public void drawMM(){
		g.background(0);
		g.textSize(32);
		g.textAlign(g.CENTER);
		g.text("Your ship is adrift in space.", g.width/2, 50);
		g.textSize(30);
		g.text("You only got one shot to get to the next black hole.", g.width/2, 100);
		g.textSize(24);
		g.text("Use gravitational assist from nearby planets to slingshot. ", g.width/2, 150);
		g.textSize(20);
		g.text("Use the mouse to set your path and click when you are ready.", g.width/2, 200);
		g.textSize(32);
		g.text("Tap spacebar to begin.", g.width/2, 250);
	}
	
	public void drawGO(){}
	
	public void gameOver(){
		gameOverActive = true;
	}
	
	public void mainMenu(){
		mainMenuActive = true;
	}
	
	public void startGame(){
		mainMenuActive = false;
		gameOverActive = false;
	}
}
