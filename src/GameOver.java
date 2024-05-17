import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameOver extends Area{

int counter = 0;
	
	Image background = Toolkit.getDefaultToolkit().getImage("GameOver.png");
	
	
	Rect[] select =
		{
				new Rect(774, 977, 381, 81),
			
		};

	public GameOver(Sprite player, Sprite player2, boolean[] pressing) {
		super(player, player2, pressing);

		
	}

	@Override
	public void inGameLoop() {

		if (pressing[_K] ) {
			System.exit(0);
		}

	}


	@Override
	public void paint(Graphics pen) {
		
		pen.drawImage(background, 0,0, 1920, 1080, null);
		
		pen.setColor(Color.yellow);
		
		
		select[counter].draw(pen);
		
	}
	

}
