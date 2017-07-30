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
		
		// frame
		g.stroke(180, alpha);
		g.fill(175, alpha);
		int x1 = 1, y1 = 1, x2 = g.width-1, y2 = 30; 
		g.rect(x1, y1, x2, y2);
		
		// data
		g.textFont(g.f2);
		g.stroke(255,255,255,alpha);
		g.fill(255, alpha);
		StringBuilder str = new StringBuilder();
		str.append(" Level " + s.sceneNum );
		str.append(" Attempts " + s.attempts);
		str.append(" Time " + g.second());
		g.textAlign(g.RIGHT);
		g.text(str.toString(), g.width - 20, y1 + 20);
		
		// power
		g.textAlign(g.LEFT);
		String power = " Power " + (s.ship.vel.mag() * 100);
		g.text(power, 20, y1 + 20);
		
		if(g.DEBUG) 
			g.text("Mouse " + g.mouseX +" / "+ g.mouseY, x1 + 20, y1 + 100);
		
		g.popMatrix();
	}
	
	
}
