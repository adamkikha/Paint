package paint.shapes;

import java.awt.Color;
import java.awt.Polygon;


public class Triangle extends Polygon implements Fillable {
	private int xo , yo , w , h;
    private Color color;
    private Boolean isFilled = false;
    
    public Triangle(int xo ,int  yo ,int w ,int h) {
    	this.xo = xo;
    	this.yo = yo;
    	this.w = w;
    	this.h = h;
    	construct();
    }
    
    private void construct() {
    	reset();
    	addPoint(xo, yo + h);
    	addPoint(xo + (w/2),yo);
    	addPoint(xo + w,yo + h);
    }
    
	@Override
	public Color getColor(){
        return color;
    }
	
    @Override
    public void setColor(Color c){
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
		xo = x;
	}

	@Override
	public void setYo(int y) {
		yo = y;
		construct();
	}

	@Override
	public void setWidth(int x) {
		w = x;
	}

	@Override
	public void setHeight(int y) {
		h = y;
		construct();
	}

	@Override
	public int getW() {
		return w;
	}

	@Override
	public int getH() {
		return h;
	}

	@Override
	public Boolean isFilled() {
		return isFilled;
	}

	@Override
	public void setFilled(Boolean b) {
		isFilled = b;
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
