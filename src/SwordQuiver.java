import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SwordQuiver extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	Image offScreenImg;
	Graphics offScreenPen;

	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean Jump_Pressed = false;
	boolean Change_Character = false;
	boolean Attack_Pressed = false;
	
	Rect floor = new Rect(0, 900, 1920, 50);

	int player_selected = 0;
	int coolDown = 0; // keeps track of when the player can switch characters.
	int jumpTime = 0;
	int attackCoolDown = 0;
	
	int direction = 1;
	
	int x = 600;
	int y = 450;

	// PLAYER
	int[] count = { 8, 8, 6, 6, 8, 8, 4, 4, 3, 8, 8, 14, 14}; // number of frames in the animations above.
	int[] duration = { 5, 5, 9, 9 , 5, 5, 5, 5, 10, 2, 2, 3, 3}; // higher the duration, slower the animation.

	String[] player_pose = {"runLT", "runRT", "idleLT", "idleRT", "walkLT", "walkRT", "attackLT", "attackRT", "smoke", "jumpLT", "jumpRT", "shotLT", "shotRT"}; // title of each animation.

	Sprite player = new Sprite("sword","player", player_pose, x, y, count, duration);
	Sprite player2 = new Sprite("bow","player", player_pose, x, y, count, duration);

	
	public void init() {


		addKeyListener(this);
		requestFocus();
		offScreenImg = this.createImage(1920, 1080);
		offScreenPen = offScreenImg.getGraphics();

		Thread t = new Thread(this);
		t.start();
	}

	public void run() {

		while (true) {


			
			
			
			if (Change_Character && coolDown == 0) {
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
				
				Change_Character = false;		
			}else {
				Change_Character = false;	
			}
			
			
			if (coolDown != 0) {
				coolDown --;
			}
			
			

			if (LT_Pressed) {
				
				direction = 0;
				
				if (player_selected == 0) {
					player.goLT(5, 0);
				}else {
					player2.goLT(2, 4);
				}
				
				
			}
			
			
			if (RT_Pressed) {
				
				direction = 1;
				
				if (player_selected == 0) {
					player.goRT(5, 1);
				}else {
					player2.goRT(2, 5);
				}
			}
			
			if (player_selected == 0) {
				player.move();
			}else {
				player2.move();
			}
			
			if (player.overlaps(floor) || player2.overlaps(floor)) {
				player.pushedOutOf(floor);
				player2.pushedOutOf(floor);
				
				player.vx = 0;
				player2.vx = 0;
				
			}
			
			
			if (Jump_Pressed && jumpTime == 0) {
				if (player_selected == 0) {
					player.jump(10);
				}else {
					player2.jump(10);
				}
				
				jumpTime = 20;
			}
			
			if (jumpTime != 0) {
				jumpTime --;
			}

			
			if (Attack_Pressed && attackCoolDown == 0) {
				
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
			

			repaint();

			try {
				Thread.sleep(16);
			} catch (Exception x) {}
		}

	}

	public void paint(Graphics pen) {
		
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
		
		floor.draw(pen);
		

	}

	public void update(Graphics pen) {
		offScreenPen.clearRect(0, 0, 1920, 1080);

		paint(offScreenPen);

		pen.drawImage(offScreenImg, 0, 0, null);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_A )   LT_Pressed =     true;  
		if (code == e.VK_D)    RT_Pressed =     true; 
		if (code == e.VK_K)    Attack_Pressed = true;
		if (code == e.VK_W)    Jump_Pressed =   true;
		
		
		
		
		if (code == e.VK_I)    Change_Character = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_A )   LT_Pressed =     false;  
		if (code == e.VK_D)    RT_Pressed =     false; 
		if (code == e.VK_K)    Attack_Pressed = false;
		if (code == e.VK_W)    Jump_Pressed =   false;


	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
