package paint.shapes;

import java.awt.Color;
import java.awt.Shape;

public interface Colorable extends Shape {
	public Color getColor();
    public void setColor(Color c);
    public int getXo();
    public int getYo();
    public void setXo(int x);
    public void setYo(int y);
    public void setWidth(int x);
    public void setHeight(int y);
    public int getW();
    public int getH();
    public int getXDraw();
    public int getYDraw();
    public void setXDraw(int x);
    public void setYDraw(int y);
    public void coordinate();
}
