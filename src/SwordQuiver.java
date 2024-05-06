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
	boolean Change_Character = false;
	boolean Attack_Pressed = false;

	int player_selected = 0;
	
	int direction = 1;
	
	int x = 600;
	int y = 450;

	// PLAYER
	int[] count = { 8, 8, 6, 6, 8, 8, 4, 4}; // number of frames in the animations above.
	int[] duration = { 5, 5, 9, 9 , 5, 5, 5, 5}; // higher the duration, slower the animation.

	String[] player_pose = {"runLT", "runRT", "idleLT", "idleRT", "walkLT", "walkRT", "attackLT", "attackRT"}; // title of each animation.

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

			player.moving = false;
			player.attacking = false;
			
			player2.moving = false;
			player2.attacking = false;
			
			
			
			if (Change_Character) {
				player_selected = (player_selected+1)%2 ;
				
				if (player_selected == 0) {
					player.x = player2.x;
					player.direction = player2.direction;
				}else {
					player2.x = player.x;
					player2.direction = player.direction;
				}
				
				Change_Character = false;
				
			}
			

			if (LT_Pressed) {
				
				direction = 0;
				
				if (player_selected == 0) {
					player.moveLT(5, 0);
				}else {
					player2.moveLT(2, 4);
				}
			}
			
			if (RT_Pressed) {
				
				direction = 1;
				
				if (player_selected == 0) {
					player.moveRT(5, 1);
				}else {
					player2.moveRT(2, 5);
				}
			}
			
			if (Attack_Pressed) {
				player.attack();
			}
			
			

			repaint();

			try {
				Thread.sleep(16);
			} catch (Exception x) {}
		}

	}

	public void paint(Graphics pen) {
		
		if (player_selected == 0) {
			player.draw(pen);
		}else {
			player2.draw(pen);
		}
		

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
		
		if (code == e.VK_A )   LT_Pressed = true;  
		if (code == e.VK_D)    RT_Pressed = true; 
		
		
		if (code == e.VK_K)    Attack_Pressed = true;
		
		if (code == e.VK_I)    Change_Character = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_A )   LT_Pressed = false;  
		if (code == e.VK_D)   RT_Pressed = false; 

		if (code == e.VK_K)    Attack_Pressed = false;


	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
