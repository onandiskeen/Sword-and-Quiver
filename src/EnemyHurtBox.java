import java.awt.Color;
import java.awt.Graphics;

public class EnemyHurtBox extends Rect{

	public EnemyHurtBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	
	public void track(EnemySprite s, int direction, int coolDown) {
		
		if (coolDown == 0) {
			if (direction == 1) {
				this.x = s.x + 120;
				this.y = s.y + 125;
			}else {
				this.x = s.x + 17;
				this.y = s.y + 125;
			}
		}else {
			this.x = 0;
			this.y = 0;
		}
		

	}


	public void draw(Graphics pen){
	
		pen.setColor(Color.orange);
		pen.drawRect(x, y, w, h);
	
	}
	
	
}
