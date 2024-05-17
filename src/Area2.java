import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Area2 extends Area{
	
	//Rect floor = new Rect(0, 775, 1920, 50);
	Image background = Toolkit.getDefaultToolkit().getImage("level1.png");
	
	Door door = new Door (1800, 137, 85, 119);
	
	
	Rect[] floor = {
			new Rect2(1227, 254, 668, 20),
			new Rect2(0, 973, 1923, 22),
			new Rect2(1, 687, 354, 20),
			new Rect2(519, 923, 636, 18),
			new Rect2(413, 695, 333, 22),
			new Rect2(244, 890, 279, 18),
			new Rect2(771, 778, 260, 18),
			new Rect2(1755, 777, 226, 21),
			new Rect2(1179, 771, 392, 20),
			new Rect2(1539, 586, 385, 18),
			new Rect2(-28, 385, 111, 18),
			new Rect2(1026, 633, 268, 21),
			new Rect2(624, 414, 552, 18),
			new Rect2(1464, 434, 172, 12),
			new Rect2(1581, 477, 229, 16),
			new Rect2(2, 506, 549, 15),
	};
	
	// SPEAR ENEMY
	
	int [] enemy_count = {7,7,5,5,4,4};
	int [] enemy_duration = {10,10,10,10,5,5};
			
	String [] enemy_pose = {"walkLT", "walkRT", "deadLT", "deadRT", "attackLT", "attackRT"};
	
	EnemySprite [] enemy = {
			new EnemySprite("spear", enemy_pose, 200, 100,enemy_count, enemy_duration),
			new EnemySprite("spear", enemy_pose, 700, 100,enemy_count, enemy_duration),
			new EnemySprite("spear", enemy_pose, 1200, 30,enemy_count, enemy_duration),
			new EnemySprite("spear", enemy_pose, 1200, 500,enemy_count, enemy_duration),
			new EnemySprite("spear", enemy_pose, 1500, 300,enemy_count, enemy_duration),
			new EnemySprite("spear", enemy_pose, 800, 750,enemy_count, enemy_duration),
	};

	int player_selected = 0;
	int coolDown = 0; // keeps track of when the player can switch characters.
	int jumpTime = 0;
	int attackCoolDown = 0;
	int deathCounter = 0;
	
	int direction = 1;
	
	int x = 600;
	int y = 450;
	
	Animation loadBar = new Animation("load_bar/player_coolDown", 4, 0);
	
	
	
	
	
	
	HitBox player_hitbox = new HitBox(10+35, y, 54, 72);
	HurtBox player_hurtbox = new HurtBox(x,y, 45, 7);
	boolean canJump = false;

	public Area2(Sprite player, Sprite player2, boolean [] pressing) {
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
				if(player.goLT(5, 0)) {
					player_hitbox.goLT(5);
				}
			}else {
				if(player2.goLT(2, 4)) {
					player_hitbox.goLT(2);
				}
			}
			
			
		}
		
		
		if (pressing[_D]) {
			
			direction = 1;
			
			if (player_selected == 0) {
				if (player.goRT(5, 1)) {
					player_hitbox.goRT(5);
				}
					

			}else {
				if(player2.goRT(2, 5)) {
					player_hitbox.goRT(2);
				}
			}
			
			
		}
		
		if (player_selected == 0) {
			player_hitbox.trackHealth(player);
			player_hurtbox.track(player, direction, pressing[_K], attackCoolDown);
			player.move();
		}else {
			player_hitbox.trackHealth(player2);
			player2.move();
		}
		
		player_hitbox.move();
		
		if (player.dead || player2.dead) {
			deathCounter++;	
		}
		
		if (deathCounter >= 60) {
			setCurrent(4);
		}
		
		
		
//		if (player.overlaps(enemy1) || player2.overlaps(enemy1)) {
//			player.pushedOutOf(enemy1);
//			player2.pushedOutOf(enemy1);
//		}
		
		
//		for (int i = 0; i < wall.length; i ++) {
//			if (player.overlaps(wall[i]) || player2.overlaps(wall[i])) {
//				player.pushedOutOf(wall[i]);
//				player2.pushedOutOf(wall[i]);
//				
//				canJump = true;
//			}
//		}
		
//		for (int i = 0; i < wall.length; i ++) {
//			if (player.overlaps(wall[i])) {
//				player.pushedOutOf(wall[i]);
//				
//				canJump = true;
//			}
//		}
//		
	
		
		for (int i = 0; i < floor.length; i ++) {
			if (player_hitbox.overlaps(floor[i])) {
				
				if (player_hitbox.cameFromAbove(floor[i])) {
					
					player.pushbackUpFrom(floor[i]);
					player2.pushbackUpFrom(floor[i]);
					player_hitbox.pushbackUpFrom(floor[i]);
	
					
					player.vx = 0;
					player2.vx = 0;
					player_hitbox.vx = 0;
					
					
					
					
				}
					
				canJump = true;
			}
				
				
				
			}
		
		
		
	
		
		
		for (int s = 0; s < enemy.length; s++) {
			if (player_hurtbox.overlaps(enemy[s].enemy1)) {
				player_hurtbox.collision = true;
				player2.arrowCollision = true;

				enemy[s].hit();
			}
		}
		
		
		
		
		
		
		
		
		
		if (pressing[_W] && jumpTime == 0 && canJump) {
			if (player_selected == 0) {
				if(player.jumping(16)) {
					player_hitbox.jump(16);
				}
			}else{
				if(player2.jumping(10)) {
					player_hitbox.jump(10);
				}
			}
			
			jumpTime = 60;

			
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
		
		
		for (int s = 0; s < enemy.length; s++) {
			for (int i = 0; i < floor.length; i ++) {
				if (enemy[s].overlaps(floor[i])) {
					enemy[s].pushedOutOf(floor[i]);
					canJump = true;
				}
			}
		}
		
		

		
		for (int s = 0; s < enemy.length; s++) {
			if (enemy[s].sight.overlaps(player_hitbox)) {
				enemy[s].attack();
				
			}
		}
		

		
		
		for (int s = 0; s < enemy.length; s++) {
		
			if (enemy[s].agro == false) {
				enemy[s].idle();
			}else {
				enemy[s].chase(player_hitbox, 4);
			}
		}
		
		for (int s = 0; s < enemy.length; s++) {
			
			if (enemy[s].isDead()) {
				deathCounter++;
				System.out.println(deathCounter);
			}
			
		}
		
		if (deathCounter == 6) {
			door.openDoor();
		}
		
		if (player_hitbox.overlaps(door) && door.doorOpenClosed) {
			setCurrent(3);
			
			player.x = 10;
		}
			
		
		
	}
	



	public void paint(Graphics pen) {
		pen.drawImage(background, 0,0, 1920, 1080, null);
		
		door.draw(pen);
		
		
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
		
		for (int s = 0; s < enemy.length; s++) {
			enemy[s].draw(pen);
		}
		
		

		
//		pen.setColor(Color.white);
//		for (int i = 0; i < floor.length; i++) {
//			floor[i].draw(pen);
//		}
		
		
		
	}

}
