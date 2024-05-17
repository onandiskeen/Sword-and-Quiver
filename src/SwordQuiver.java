import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class SwordQuiver extends GameBase{

	
	int x = 600;
	int y = 450;
	
	Animation loadBar = new Animation("load_bar/player_coolDown", 4, 0);

	// PLAYER
	int[] count = { 8, 8, 6, 6, 8, 8, 4, 4, 3, 8, 8, 14, 14, 3, 3}; // number of frames in the animations above.
	int[] duration = { 5, 5, 9, 9 , 5, 5, 5, 5, 10, 2, 2, 3, 3, 20, 20}; // higher the duration, slower the animation.

	String[] player_pose = {"runLT", "runRT", "idleLT", "idleRT", "walkLT", "walkRT", "attackLT", "attackRT", "smoke", "jumpLT", "jumpRT", "shotLT", "shotRT", "deadLT", "deadRT"}; // title of each animation.
	
	
	

	Sprite player = new Sprite("sword","player", player_pose, 10, y, count, duration);
	Sprite player2 = new Sprite("bow","player", player_pose, 10, y, count, duration);
	
	StartScreen startScreen = new StartScreen(player, player2, pressing);
	Area2 area2 = new Area2(player, player2, pressing);
	Area1 area1 = new Area1(player, player2, pressing);
	GameOver gameOver = new GameOver(player, player2, pressing);
	YouWin youWin = new YouWin(player, player2, pressing);
	
	
	public void init() {
		
		super.init();
		
		Area.area[0] = Area.area[1];
	}
	


	public void inGameLoop() {
			
		Area.area[0].inGameLoop();

	}

	public void paint(Graphics pen) {

		Area.area[0].paint(pen);
		

	}
}
