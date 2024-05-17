import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LevelDesignTool extends GameBase implements MouseListener{
	
	Image background = Toolkit.getDefaultToolkit().getImage("level2.png");
	
	int mx = -1;
	int my = -1;
	
	Rect2[] wall =
		{
				new Rect2(1185, 307, 188, 28),
				new Rect2(-22, 1025, 1963, 28),
				new Rect2(1, 687, 727, 20),
	


				new Rect2(857, 887, 133, 20),
	
				new Rect2(1244, 764, 677, 26),

				new Rect2(220, 361, 358, 35),
				new Rect2(1059, 635, 190, 19),
				new Rect2(961, 377, 166, 26),
				new Rect2(1311, 444, 609, 26),

				new Rect2(-78, 539, 971, 29),
			
		};
	
	
	


	@Override
	public void inGameLoop() {
		
		if (pressing[_K]) {
			for (int i = 0; i < wall.length; i++) {
				System.out.println(wall[i]);
			}
			
			System.out.println("_____________________________________________");
		}
		
	}

	public void mouseDragged(MouseEvent e)
	{
		int nx = e.getX();
		int ny = e.getY();
		
		int dx = nx - mx;
		int dy = ny - my;
		
		for (int i = 0; i < wall.length; i++) {
			if(wall[i].resizer.held)  wall[i].resizeBy(dx,  dy);
			else
			if(wall[i].held)  wall[i].moveBy(dx, dy);
		}
		
	
		mx = nx;
		my = ny;
	}
	
	public void mousePressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		for (int i = 0; i < wall.length; i++) {
			if(wall[i].contains(mx,  my))  wall[i].grabbed();
			if(wall[i].resizer.contains(mx,  my))  wall[i].resizer.grabbed();
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e)
	{
		for (int i = 0; i < wall.length; i++) {
			wall[i].dropped();
			wall[i].resizer.dropped();
		}
		
	}
	
	
	
	@Override
	public void paint(Graphics pen) {
		pen.drawImage(background, 0,0, 1920, 1080, null);
		
		
		pen.setColor(Color.white);
		for (int i = 0; i < wall.length; i++) {
			wall[i].draw(pen);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	

}
