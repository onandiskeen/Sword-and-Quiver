import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartScreen extends Area {

	int counter = 0;
	
	Image background = Toolkit.getDefaultToolkit().getImage("Sword_and_Quiver_start_screen.png");
	
	
	Rect[] select =
		{
				new Rect(797, 845, 312, 82),
				new Rect(796, 962, 308, 79),
			
		};

	public StartScreen(Sprite player, Sprite player2, boolean[] pressing) {
		super(player, player2, pressing);
		
		
		
	}

	@Override
	public void inGameLoop() {

		if (pressing[_K] && counter == 0) {
			setCurrent(2);
			
			player.x = 10;
		}
		
		if (pressing[_K] && counter == 1) {
			System.exit(0);
		}
		
		
		if (pressing[_W]) {
			counter = 0;
		}
		
		if (pressing[_S]) {
			counter = 1;
		}
	}

	@Override
	public void paint(Graphics pen) {
		
		pen.drawImage(background, 0,0, 1920, 1080, null);
		
		pen.setColor(Color.yellow);
		
		
		select[counter].draw(pen);
		
	}
	


}
