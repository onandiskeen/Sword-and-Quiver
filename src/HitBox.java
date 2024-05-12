import java.awt.Color;
import java.awt.Graphics;

public class HitBox extends Rect {

	public HitBox(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}

	public void track(Sprite s) {
		this.x = s.x + 40;
		this.y = s.y + 55;
	}
	
	
	
	public void draw(Graphics pen)
	{
		
		pen.setColor(Color.blue);
		pen.drawRect(x, y, w, h);
	}
	
}
