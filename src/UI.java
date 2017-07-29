public class UI {
	Scene s;
	Slinger g;
	float alpha = 100; 
	
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
		int x1 = 100, y1 = 1, x2 = 600, y2 = 100; 
		g.rect(x1, y1, x2, y2);
		
		// data
		g.textFont(g.f2);
		g.stroke(255,255,255,alpha);
		g.fill(255);
		String str = "Scene " + s.sceneNum + " Speed " + (s.ship.vel.mag() * 100);
		g.text(str, x1 + 20, y1 + 50);
		
		if(g.DEBUG) 
			g.text("Mouse " + g.mouseX +" / "+ g.mouseY, x1 + 20, y1 + 100);
		
		g.popMatrix();
	}
	
	
}
