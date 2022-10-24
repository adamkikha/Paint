/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.shapes;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Adam
 */
public class Rectangle extends Rectangle2D implements Fillable {
    private int x , xo , yo , y , w , h;
    private Color color;
    private Boolean isFilled = false;
    
    public Rectangle(double x, double y, double w, double h) {
    	this.x = (int) x;
        this.xo = (int)x;
        this.y = (int)y;
        this.yo = (int)y;
        this.w= (int)w;
        this.h = (int)h;
        coordinate();
    }
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color c){
        color = c;
    }
    
    @Override
    public Rectangle2D createIntersection(Rectangle2D r){return null;}
    @Override
    public Rectangle2D createUnion(Rectangle2D r){return null;}

    @Override
    public void setRect(double x, double y, double w, double h) {
        this.x = (int) x;
        this.y = (int) y;
        this.w= (int) w;
        this.h = (int) h;
    }
    @Override
    public int outcode(double x, double y) {return -1;}

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
        return h;
    }

    @Override
    public boolean isEmpty() {
        if (h==0 && w==0)
            return true;
        return false;
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
	public void setWidth(int x) {
		w = x;
	}

	@Override
	public void setHeight(int y) {
		h = y;
	}

	@Override
	public int getW() {
		return w;
	}

	@Override
	public int getH() {
		return h;
	}
    
	public void coordinate() {
	   if(h<0) {
		   if(w>0) {
			   y = yo + h;
			  h = -h;
		   }
		   else if(w<0) {
			   x = xo + w;
			   y = yo + h;
			   w = -w;
			   h = -h;
		   }
	   }
	   else if(h>0) {
		   if(w<0) {
			   x = xo + w;
			   w = -w;
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
