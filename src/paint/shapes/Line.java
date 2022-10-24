package paint.shapes;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Line2D implements Colorable {
	private int xo , yo , x , y , centerX , centerY;
    private Color color;
    
    public Line(int x1,int y1,int x2,int y2) {
    	this.xo = x1;
		this.yo = y1;
		this.x =  x2;
		this.y =  y2;
		centerX = (x+xo)/2;
		centerY = (y+yo)/2;		
    }
	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}
	public Color getColor(){
        return color;
    }
    
    public void setColor(Color c){
        color = c;
    }

	@Override
	public double getX1() {
		return xo;
	}

	@Override
	public double getY1() {
		return yo;
	}

	@Override
	public Point2D getP1() {
		return null;
	}

	@Override
	public double getX2() {
		return x;
	}

	@Override
	public double getY2() {
		return y;
	}

	@Override
	public Point2D getP2() {
		return null;
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		this.xo = (int) x1;
		this.yo = (int) y1;
		this.x = (int) x2;
		this.y = (int) y2;
		centerX = (x+xo)/2;
		centerY = (y+yo)/2;
	}
	@Override
	public int getXo() {
		return centerX;
	}
	@Override
	public int getYo() {
		return centerY;
	}
	@Override
	public void setXo(int x) {
		if(x>centerX) {
			xo += x - centerX;
			this.x += x - centerX;
		}
		else {
			xo -= centerX - x;
			this.x -= centerX - x;
		}
		centerX=x;
	}
	@Override
	public void setYo(int y) {
		if(y>centerY) {
			yo += y - centerY;
			this.y += y - centerY;
		}
		else {
			yo -= centerY - y;
			this.y -= centerY - y;
		}
		centerY=y;
	}
	@Override
	public void setWidth(int x) {
		this.x = x;
		centerX = (x+xo)/2;
	}
	@Override
	public void setHeight(int y) {
		this.y = y;
		centerY = (y+yo)/2;
	}
	@Override
	public int getW() {
		return (int) getX2();
	}
	@Override
	public int getH() {
		return (int) getY2();
	}

	public void coordinate() {}
	@Override
	public int getXDraw() {
		return getXo();
	}
	@Override
	public int getYDraw() {
		return getYo();
	}
	@Override
	public void setXDraw(int x) {
		setXo(x);
	}
	@Override
	public void setYDraw(int y) {
		setYo(y);
	}
}
