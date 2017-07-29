
public class UI {
	Scene s;
	Slinger g;
	
	public UI(Scene s, Slinger g){
		this.s = s; 
		this.g = g;
	}
	
	
	public void update(){}
	
	
	public void draw(){
		g.pushMatrix();
		g.stroke(123,123,123);
		g.fill(123,123,123);
		g.text("Scene " + s.sceneNum, 100, 100);
		g.text("Mouse " + g.mouseX +" / "+ g.mouseY, 100, 120);
		g.popMatrix();
	}
	
	
}
