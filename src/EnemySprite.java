import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class EnemySprite extends Rect{

	
	Animation [] animations;
	
	Image [] healthBar;
	
	boolean moving = false;
	boolean agro = false;
	boolean attacking = false;
	boolean dead;
	boolean enemyDead = false;
	
	int direction = 1;
	int idleCounter = 0;
	int deathCounter = 0;
	int coolDown = 0;
	
	public static int deadEnemies = 0;
	
	int health = 100;
	
	
	EnemyHitBox enemy1 = new EnemyHitBox(200, 825, 40, 100);
	EnemySight sight = new EnemySight(x, y, 65, 10);
	EnemyHurtBox hurtBox = new EnemyHurtBox(0, 0, 70, 10);

	
	public EnemySprite(String name, String [] pose, int x, int y, int [] count, int [] duration) {
		
		
		super(x, y, 200, 200);
		
		animations  = new Animation[pose.length];
		healthBar = new Image[5];
				
		
		for (int i = 0; i < animations.length; i++) {
			animations[i] = new Animation("spear_skeleton/AllMoves/" + name + "_" + pose[i], count[i], duration[i]);
		}
		
		for (int i = 0; i < 5; i ++) {
			healthBar[i] = Toolkit.getDefaultToolkit().getImage("enemyHealthBar_" + i + ".png");

		}
		
	}
	
	public boolean isDead() {
		
		if (enemyDead == false && dead) {
			
			enemyDead = true;
			
			return true;
		}else {
			return false;
		}
		
	}


	public void draw(Graphics pen) {
		
		if (health <= 0 && deathCounter != 50) {
			if (direction == 0) {
				pen.drawImage(animations[2].nextImage(), x, y, w, h, null);
			}else {
				pen.drawImage(animations[3].nextImage(), x, y, w, h, null);
			}
			
			deathCounter++;
		}
		
		if (!dead) {
			 if (attacking == true) {
				if (direction == 0) {
					pen.drawImage(animations[4].nextImage(), x, y, w, h, null);
				}else {
					pen.drawImage(animations[5].nextImage(), x, y, w, h, null);
				}
			}else if(moving) {
				
				if (direction == 0) {
					pen.drawImage(animations[0].nextImage(), x, y, w, h, null);
				}else {
					pen.drawImage(animations[1].nextImage(), x, y, w, h, null);
				}
			}
			 
		
			 pen.drawImage(healthBar[health/25], x + 40, y + 40, 107, 46, null);
			
			//enemy1.draw(pen);
			//sight.draw(pen);
			//hurtBox.draw(pen);
		}

		
	}
	
	
	public void idle() {
		
		enemy1.track(this, dead);
		sight.track(this, direction, dead);
		
		if (health <= 0) {
			moving = false;
			agro = false;
			dead = true;
		}else if (idleCounter < 200) {
	        if (direction == 1) {
	            this.goRT(1);
	        } else {
	            this.goLT(1);
	        }
	        moving = true;
	        idleCounter++;
	    } else if (idleCounter < 300) {
	    	//moving = false;
	    	idleCounter++;
	    }else {
	        idleCounter = 0; // Reset the idle counter
	        if (direction == 1) {
	            direction = 0;
	        } else {
	            direction = 1;
	        }
	    }
	    this.move();
		
	}
	
	
	public void hit() {

		health -=25;
	
	}
	
	public void attack() {
		if (health <= 0) {
			moving = false;
			agro = false;
			dead = true;
		}else {
			agro = true;
		}
		
	}
	
	public void chase(HitBox r, int dx){
		
		if (health <= 0) {
			moving = false;
			agro = false;
			dead = true;
		}else {
			enemy1.track(this, dead);
			sight.track(this, direction, dead);

			moving = true;
			
			if(isLeftOf(r)) {  
				direction = 1;
				goRT(dx); 
			}
			if(isRightOf(r)) { 
				direction = 0;
				goLT(dx);
			}
			
			this.move();
			
			if (sight.overlaps(r) && coolDown < 20) {
				attacking = true;
				hurtBox.track(this, direction, coolDown);
				coolDown++;
			}else {
				attacking = false;
				hurtBox.x = 0;
				hurtBox.y = 0;
				
				coolDown++;
			}
			
			if (coolDown == 200) {
				coolDown = 0;
			}
			
			if (hurtBox.overlaps(r)) {
				r.hit();
			}
			

		}
 
	}
	
	public boolean isLeftOf(Rect r)
	{
		return x + w- 20 < r.x;
	}
	
	public boolean isRightOf(Rect r)
	{
		return r.x + r.w -20 < x;
	}

	
}
