import java.awt.Graphics;

public class Sprite extends Rect {
	
	Animation [] animations;
	boolean moving = false;
	boolean crouching = false;
	boolean attacking = false;
	
	int direction = 1; // 0 = left, 1 = right
	int action = 0;
	
	public Sprite(String player,String name, String [] pose, int x, int y, int [] count, int [] duration) {
		
		
		super(x, y, 128, 128);
		
		animations  = new Animation[pose.length];

		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation(player, name + "_" + pose[i], count[i], duration[i]);
			
		}
		
	} 
	


	public void draw(Graphics pen) {
			
		if (attacking) {
			if (direction == 0) {
				pen.drawImage(animations[6].nextImage(), x, y, w, h, null);
			}else {
				pen.drawImage(animations[7].nextImage(), x, y, w, h, null);
			}	
			
		}else if (moving){
			pen.drawImage(animations[action].nextImage(), x, y, w, h, null);
		}else {
			
			if (direction == 0) {
				pen.drawImage(animations[2].nextImage(), x, y, w, h, null);
				
				
			}else {
				pen.drawImage(animations[3].nextImage(), x, y, w, h, null);
			}
			
			

		}

		
	}

	
	public void moveLT(int dx, int action){
		
		
		old_x = x;
		
		this.action = action;
		
		x -= dx;	
		
		moving = true;
		direction = 0;
	}
	

	public void moveRT(int dx, int action){
		
		old_x = x;
		
		this.action = action;
		
		x += dx;	
		
		moving = true;
		direction = 1;
	}
	

	
	public void attack() {
		attacking = true;
	}
	
}