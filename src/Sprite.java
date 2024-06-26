import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Sprite extends Rect {
	
	Animation [] animations;
	boolean moving = false;
	boolean crouching = false;
	boolean attacking = false;
	boolean shooting = false;
	boolean jumping = false;
	boolean swap = false;
	boolean arrowShot = false;
	boolean dead  = false;

	
	Image[] arrow = new Image[2];
	
	int arrowX;
	int arrowY;
	int arrowDirection;
	boolean arrowCollision = false;

	
	int direction = 1; // 0 = left, 1 = right
	int action = 0;
	int attackingAction = 0;
	int jumpCounter = 0;
	int attackCounter = 0;
	int arrowCounter = 0;
	int deathCounter  = 0;
	
	
	
	public Sprite(String player,String name, String [] pose, int x, int y, int [] count, int [] duration) {
		
		
		super(x, y, 128, 128);
		
				
		animations  = new Animation[pose.length];

		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation(player, name + "_" + pose[i], count[i], duration[i]);
			
		}
		
		arrow[0] = Toolkit.getDefaultToolkit().getImage("player_arrow_0"+".png");
		arrow[1] = Toolkit.getDefaultToolkit().getImage("player_arrow_1"+".png");
		
	}
	


	public void draw(Graphics pen) {

		
		if (jumping) {
			if (direction == 0) {
				pen.drawImage(animations[9].nextImage(), x, y, w, h, null);
			}else {
				pen.drawImage(animations[10].nextImage(), x, y, w, h, null);
			}
			jumpCounter ++;
			
			if (jumpCounter == 16) {
				jumping = false;
				jumpCounter = 0;
			}
			
			
		}else if (attacking) {
			if (direction == 0) {
				pen.drawImage(animations[6].nextImage(), x, y, w, h, null);
			}else {
				pen.drawImage(animations[7].nextImage(), x, y, w, h, null);
			}
			
			attackCounter++;
			
			if (attackCounter == 20) {
				attacking = false;
				attackCounter = 0;
			}
			
			
		}else if (shooting) {
			
			if (direction == 0) {
				pen.drawImage(animations[11].nextImage(), x, y, w, h, null);
			}else {
				pen.drawImage(animations[12].nextImage(), x, y, w, h, null);
			}
			
			attackCounter++;
			
			if (attackCounter == 42) {

				arrowX = x;
				arrowY = y;
				arrowDirection = direction;
				arrowShot = true;
				shooting = false;
				attackCounter = 0;
			}
		}
		
		else if (moving){
			pen.drawImage(animations[action].nextImage(), x, y, w, h, null);
			
			moving = false;
		}else if (!moving && !dead){
			
			if (direction == 0) {
				pen.drawImage(animations[2].nextImage(), x, y, w, h, null);
				
				
			}else {
				pen.drawImage(animations[3].nextImage(), x, y, w, h, null);
			}
		}else if (dead && deathCounter < 60) {
			if (direction == 0) {
				pen.drawImage(animations[13].nextImage(), x, y, w, h, null);
				
				
			}else {
				pen.drawImage(animations[14].nextImage(), x, y, w, h, null);
			}
			
			deathCounter ++;
		}
		
		
		if (arrowShot) {
			arrowCounter ++;
			
			
			
			if (arrowDirection == 0) {
				
				pen.drawImage(arrow[1], (arrowX+10)-arrowCounter *10, arrowY+70, 48, 48, null);
			}else {
				pen.drawImage(arrow[0], (arrowX+40)+arrowCounter *10, arrowY+70, 48, 48, null);
			}
			
			if (arrowCounter == 42 || arrowCollision) {
				arrowShot = false;
				arrowCounter = 0;
				arrowCollision = false;
			}
			
				
		}

	}
	
	public void transition(Graphics pen) {
		pen.drawImage(animations[8].nextImage(), x, y, w, h, null);
	}

	
	public boolean  goLT(int vx, int action){
		
		if (!attacking && !shooting && !dead) {
			
			this.action = action;
			
			this.vx = -vx;	

			
			moving = true;
			direction = 0;
			
		}
		
		return !attacking && !shooting && !dead;
		
	}
	

	public boolean goRT(int vx, int action){
		
		if (!attacking && !shooting && !dead) {

			
			this.action = action;
			
			this.vx = +vx;

			
			moving = true;
			direction = 1;
			
		
		}
		
		return !attacking && !shooting && !dead;
		
	}


	
	public boolean jumping(int h) {
		
		if (!attacking && !shooting && !dead) {
		
			jumping = true;
			
			vy = -h;
			
		
		}
		
		return !attacking && !shooting && !dead;
		
	}
	
	public void shot() {
		shooting = true;
	}
	
	public void attack() {
		attacking = true;
	}
	
	public void dead() {
		dead = true;
	}
	
	
	
}