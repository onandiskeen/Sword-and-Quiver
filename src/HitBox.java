import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class HitBox extends Rect {
	
	int health = 100;
	
	boolean dead = false;
	
	Image [] healthBar = new Image[6];

	public HitBox(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		
		for (int i = 0; i < 6; i++) {
			healthBar[i] = Toolkit.getDefaultToolkit().getImage("player_healthbar_" + i + ".png");
		}
		
	}

	public void trackHealth(Sprite s) {

		if (health <= 0) {
			s.dead();
//			this.x = 1920;
//			this.y = 1080;
		}
	}
	
	
	public void  goLT(int vx){


			this.vx = -vx;	

		
	}
	
	public  void  goRT(int vx){

			this.vx = +vx;	

		
	}
	
	
	public void jump(int h, boolean attacking, boolean shooting) {
		
		if (!attacking && !shooting && !dead) {
			
			this.vy = -h;
		
		}
		
	}
	
	
	
	public void draw(Graphics pen){
		
		pen.setColor(Color.blue);
		pen.drawRect(x, y, w, h);
		
		if(health >= 0) {
			pen.drawImage(healthBar[health/20], -65, 0, 534, 75, null);
		}
		
	}
	
	
	public void hit() {
		health -= 20;
		
		
		
		if (health <= 0) {
			System.out.println("DEAD!");
			dead = true;
		}else {
			System.out.println("Health - " + health);
		}
	}
	
}
