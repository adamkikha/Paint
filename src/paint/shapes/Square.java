package paint.shapes;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Square extends Rectangle2D implements Fillable {
	private int x , xo , y , yo , w , h;
    private Color color;
    private Boolean isFilled = false;
    
	public Square(int x,int y,int w , int h) {
		this.x = x;
        this.xo = x;
        this.y = y;
        this.yo = y;
        this.w= w;
        this.h = h;
        coordinate();
	}
	@Override
	public void setWidth(int x) {
		w = x;
	}
	@Override
	public void setHeight(int y) {
		h = y;
	}
	@Override
	public Color getColor() {
		return color;
	}
	@Override
	public void setColor(Color c) {
		color = c;
	}
	@Override
	public int getXo() {
		return xo;
	}
	@Override
	public int getYo() {
		return yo;
	}
	@Override
	public void setXo(int x) {
		this.xo = x;
	}
	@Override
	public void setYo(int y) {
		this.yo = y;
	}
	@Override
	public Boolean isFilled() {
		return isFilled;
	}
	@Override
	public void setFilled(Boolean b) {
		isFilled = b;
	}
	@Override
	public void setRect(double x, double y, double w, double h) {
		this.x = (int) x;
        this.y = (int) y;
        this.w= (int) w;
	}
	@Override
	public int outcode(double x, double y) {
		return 0;
	}
	@Override
	public Rectangle2D createIntersection(Rectangle2D r) {
		return null;
	}
	@Override
	public Rectangle2D createUnion(Rectangle2D r) {
		return null;
	}
	@Override
	public double getX() {
		return x;
	}
	@Override
	public double getY() {
		return y;
	}
	@Override
	public double getWidth() {
		return w;
	}
	@Override
	public double getHeight() {
		return w;
	}
	@Override
	public boolean isEmpty() {
		if (w==0)
            return true;
        return false;
	}
	@Override
	public int getW() {
		return w;
	}
	@Override
	public int getH() {
		return getW();
	}
	public void coordinate() {
		int t = Math.abs(w)<Math.abs(h)? Math.abs(w):Math.abs(h);
	   if(h<0) {
		   if(w>0) {
			   y = yo - t;
			   h = t;
			   w = t;
		   }
		   else if(w<0) {
			   x = xo - t;
			   y = yo - t;
			   w = t;
			   h = t;
		   }
	   }
	   else if(h>0) {
		   if(w<0) {
			   x = xo - t;
			   w = t;
			   h = t;
		   }
		   else {
			   w = t;
			   h = t;
		   }
	   }
	}
	@Override
	public int getXDraw() {
		return (int) getX();
	}
	@Override
	public int getYDraw() {
		return (int) getY();
	}
	@Override
	public void setXDraw(int x) {
		this.x = x;
	}
	@Override
	public void setYDraw(int y) {
		this.y = y;
	}
}
