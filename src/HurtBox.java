import java.awt.Color;
import java.awt.Graphics;

public class HurtBox extends Rect{
	
	
	boolean collision = false;

	public HurtBox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	
	public void track(Sprite s, int direction, boolean attacking, int cooldown) {
		
		
		if (attacking && cooldown == 0) {
			if (direction == 1) {
				this.x = s.x + 70;
				this.y = s.y + 90;
			}else {
				this.x = s.x + 10;
				this.y = s.y + 90;
			}
		}else {
			this.x = 0;
			this.y = 0;
		}
		
		
		
	}
	
	public void trackArrow(int arrowX, int arrowY, int arrowCounter, int direction) {
		
		
		
		if  (arrowCounter == 0) {
			this.x = 0;
			this.y = 0;
			collision = false;
		}else if (collision) {
			this.x = 0;
			this.y = 0;
		}else {
			if (direction == 0) {
				this.x = arrowX - arrowCounter * 10;
			}else {
				this.x = (arrowX+50) + arrowCounter * 10;
			}

			this.y = arrowY + 90;
		 }
		
		

	}
	
	

	public void draw(Graphics pen)
	{
		
		pen.setColor(Color.green);
		pen.drawRect(x, y, w, h);
	}

}
