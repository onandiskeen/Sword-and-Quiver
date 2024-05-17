import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Door extends Rect{
	
	Image doorClosed = Toolkit.getDefaultToolkit().getImage("door closed.png");
	
	Image doorOpen = Toolkit.getDefaultToolkit().getImage("door open.png");
	
	
	boolean doorOpenClosed = false;


	public Door(int x, int y, int w, int h) {
		super(x, y, w, h);

	}
	
	
	
	public void openDoor() {
		doorOpenClosed = true;
	}
	
	
	public void draw (Graphics pen) {
		
		if (doorOpenClosed) {
			pen.drawImage(doorOpen, x, y, w, h, null);
		}else {
			pen.drawImage(doorClosed, x, y, w, h, null);
		}
		
	}
	

}
