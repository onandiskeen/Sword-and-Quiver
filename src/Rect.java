import java.awt.Graphics;

public class Rect
{
	int x;
	int y;
	
	int w;
	int h;

	
	boolean held = false;

	double vy = 0;
	double vx = 0;
		
	static double G = .6;
		
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

	}
	
	
	public void setVelocity(double vx, double vy) {
		
		this.vx = vx;
		this.vy = vy;
		
	}
	
	
	
	
	public void goLT(int vx) {
		
		this.vx = -vx;
	}
	
	public void goRT (int vx) {
		
		this.vx = +vx;
	}
	
	public void jump(int h) {
		
		vy = -h;
		
	}
    
	public void move() {
		
		x +=vx;
		y += vy; // + G/2;
		

		vy += G;
	}
	
	public void grabbed()
	{
		held = true;
	}
	
	public void dropped()
	{
		held = false;
	}
	

	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		
		y += dy;
	}
	
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		
		h += dh;
	}
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x    )  &&
			   (mx <= x + w)  &&
			   (my >= y    )  &&
			   (my <= y + h);
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	public void pushedOutOf(Rect r)
	{
		if(cameFromAbove(r)) 	pushbackUpFrom(r);
		if(cameFromBelow(r))    pushbackDownFrom(r);
		if(cameFromLeftOf(r))   pushbackLeftFrom(r);		
		if(cameFromRightOf(r))	pushbackRightFrom(r);
	}
	
	public boolean cameFromLeftOf(Rect r)
	{
		return x-vx + w < r.x;
	}
	
	public boolean cameFromRightOf(Rect r)
	{
		return r.x + r.w < x-vx;
	}
	
	public boolean cameFromAbove(Rect r)
	{
		return y-vy + h < r.y;
	}
	
	public boolean cameFromBelow(Rect r)
	{
		return r.y + r.h < y-vy;
	}
	
	public void pushbackLeftFrom(Rect r)
	{
		x = r.x - w - 1;
	}
	
	public void pushbackRightFrom(Rect r)
	{
		x = r.x + r.w + 1;
	}
	
	public void pushbackUpFrom(Rect r)
	{
		y = r.y - h - 1;
		vy = 0; // sets velocity to zero which prevents the player from falling through the floor.
	}
	
	public void pushbackDownFrom(Rect r)
	{
		y = r.y + r.h + 1;
	}
	
	
	public void draw(Graphics pen)
	{
		pen.drawRect(x, y, w, h);
	}
	
	
	public String toString()
	{
		return "new Rect(" + x + ", " + y + ", " + w + ", " + h + "),";
	}


		
		
	

}
