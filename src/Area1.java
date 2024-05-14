import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Area1 extends Area{
	
	Rect floor = new Rect(0, 775, 1920, 50);
	
	Rect portal = new Rect(1800, 700, 50, 75);
	
	Image background = Toolkit.getDefaultToolkit().getImage("background_0.png");

	int player_selected = 0;
	int coolDown = 0; // keeps track of when the player can switch characters.
	int jumpTime = 0;
	int attackCoolDown = 0;
	
	int direction = 1;
	
	int x = 600;
	int y = 450;
	
	Animation loadBar = new Animation("load_bar/player_coolDown", 4, 0);
	
	// SPEAR ENEMY
	
	int [] enemy_count = {7,7,5,5,4,4};
	int [] enemy_duration = {10,10,10,10,5,5};
		
	String [] enemy_pose = {"walkLT", "walkRT", "deadLT", "deadRT", "attackLT", "attackRT"};
	
	EnemySprite spear = new EnemySprite("spear", enemy_pose, 200, 500,enemy_count, enemy_duration);
	EnemySprite spear2 = new EnemySprite("spear", enemy_pose, 700, 500,enemy_count, enemy_duration);
	
	
	
	HitBox player_hitbox = new HitBox(x, y, 54, 72);
	HurtBox player_hurtbox = new HurtBox(x,y, 45, 7);
	boolean canJump = false;

	public Area1(Sprite player, Sprite player2, boolean [] pressing) {
		super(player, player2, pressing);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inGameLoop() {
		if (pressing[_I] && coolDown == 0) {
			player_selected = (player_selected+1)%2 ;
			
			if (player_selected == 0) {
				player.x = player2.x;
				player.y = player2.y;
				player.direction = player2.direction;
				player.swap = true;
			}else {
				player2.x = player.x;
				player2.y = player.y;
				player2.direction = player.direction;
				player2.swap = true;
			}
			
			
			coolDown = 100;	
			
		}
		
		
		if (coolDown != 0) {
			coolDown --;
		}
		


		if (pressing[_A]) {
			
			direction = 0;
			
			if (player_selected == 0) {
				player.goLT(5, 0);
			}else {
				player2.goLT(2, 4);
			}
			
			
		}
		
		
		if (pressing[_D]) {
			
			direction = 1;
			
			if (player_selected == 0) {
				player.goRT(5, 1);
			}else {
				player2.goRT(2, 5);
			}
		}
		
		if (player_selected == 0) {
			player_hitbox.track(player);
			player_hurtbox.track(player, direction, pressing[_K], attackCoolDown);
			player.move();
		}else {
			player_hitbox.track(player2);
			player2.move();
		}
		
		
		
//		if (player.overlaps(enemy1) || player2.overlaps(enemy1)) {
//			player.pushedOutOf(enemy1);
//			player2.pushedOutOf(enemy1);
//		}
		
		
		
		if (player.overlaps(floor) || player2.overlaps(floor)) {
			player.pushedOutOf(floor);
			player2.pushedOutOf(floor);
			
			canJump = true;
		}
		
		
		if (player_hurtbox.overlaps(spear.enemy1)) {
			player_hurtbox.collision = true;
			player2.arrowCollision = true;

			spear.hit();
		}
		
		
		if (player_hurtbox.overlaps(spear2.enemy1)) {
			player_hurtbox.collision = true;
			player2.arrowCollision = true;
			
			spear2.hit();
		}
		
		
		
		
		
		if (pressing[_W] && jumpTime == 0 && canJump) {
			if (player_selected == 0) {
				player.jump(10);
			}else{
				player2.jump(10);
			}
			
			jumpTime = 20;
			
			canJump = false;
		}
		
		if (jumpTime != 0) {
			jumpTime --;
		}
		

		
		if (player_selected == 1) {
			player_hurtbox.trackArrow(player2.arrowX, player2.arrowY, player2.arrowCounter, player2.arrowDirection);
		}
		
		
		if (pressing[_K] && attackCoolDown == 0) {
			
			if (player_selected == 0) {
				player.attack();
			}else {
				player2.shot();		
			}
			
			
			
			attackCoolDown = 40;
		}
		
		if (attackCoolDown != 0) {
			attackCoolDown --;
		}
		
		
		if (spear.overlaps(floor)) {
			spear.pushedOutOf(floor);
		}
		
		if (spear2.overlaps(floor)) {
			spear2.pushedOutOf(floor);
		}
		
		if (spear.sight.overlaps(player_hitbox)) {
			spear.attack();
			
		}
		
		if (spear2.sight.overlaps(player_hitbox)) {
			spear2.attack();
			
		}
		
		
		
		
		if (spear.agro == false) {
			spear.idle();
		}else {
			spear.chase(player_hitbox, 4);
		}
		
		if (spear2.agro == false) {
			spear2.idle();
		}else {
			spear2.chase(player_hitbox, 4);
		}
		
		if (player_hitbox.overlaps(portal)) {
			setCurrent(2);
			
			player.x = 10;
		}
		
	}


	public void paint(Graphics pen) {
		pen.drawImage(background, 0,0, 1920, 1080, null);
		
		
		if (coolDown > 0 && coolDown % 25 == 0 || coolDown ==1) {
			if (player_selected == 0) {
				pen.drawImage(loadBar.loadBarImage(), player.x + 15, player.y + 30, 100, 20, null);
			}else {
				pen.drawImage(loadBar.loadBarImage(), player2.x + 15, player2.y + 30, 100, 20, null);
			}
		}else if (coolDown > 0 && coolDown % 25 != 0) {
			if (player_selected == 0) {
				pen.drawImage(loadBar.currentImage(), player.x + 15, player.y + 30, 100, 20, null);
			}else {
				pen.drawImage(loadBar.currentImage(), player2.x + 15, player2.y + 30, 100, 20, null);
			}
		}
		
		
		
		if (coolDown < 85) {
			if (player_selected == 0) {
				player.draw(pen);
			}else {
				player2.draw(pen);
			}
		}else {
			if (player_selected == 0) {
				player.transition(pen);
				
			}else {
				player2.transition(pen);
			}
		}
		
		//pen.setColor(Color.black);
		//floor.draw(pen);
		
		
		player_hitbox.draw(pen);
		//player_hurtbox.draw(pen);
		
		
		
		spear.draw(pen);
		spear2.draw(pen);
		
		portal.draw(pen);
	}


}
