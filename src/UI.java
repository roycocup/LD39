public class UI {
	Scene s;
	Slinger g;
	float alpha = 0; 
	
	public UI(Scene s, Slinger g){
		this.s = s; 
		this.g = g;
	}
	
	public void update(){
		alpha = 100; 
		if (s.ship.shot == true){
			alpha = 50; 
		} 
	}
	
	public void draw(){
		g.pushMatrix();
		
		drawFrame();
		drawData(); 
		
		
		if(g.DEBUG) 
			drawDebug();
			
		
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
	
	public void gameOver(){}
	
	public void mainMenu(){}
}
