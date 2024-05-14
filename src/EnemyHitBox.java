import java.awt.Color;
import java.awt.Graphics;

public class EnemyHitBox extends Rect{

	public EnemyHitBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	
	public void track(EnemySprite s, boolean dead) {
		
		if (!dead) {
			this.x = s.x + 80;
			this.y = s.y + 100;
		}else {
			this.x = 1920;
			this.y = 1920;
		}
		
	}
	
	
	public void draw(Graphics pen)
	{
		
		pen.setColor(Color.red);
		pen.drawRect(x, y, w, h);
	}
	
	
	
	

}
