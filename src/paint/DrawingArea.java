/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import paint.shapes.*;
import paint.shapes.Rectangle;

/**
 *
 * @author Adam
 */
public class DrawingArea extends JComponent {
    private int x , y , xo, yo , noOfShapes , shape  , mode  , noOfActions;
    private Boolean selected = false , dragged = false;
    private Image image;
    private Main main;
    private List <Colorable> shapes;
    private List <Action> actions;
    private Graphics2D g;
    private Colorable c = null;
    
    public DrawingArea(Main m){
    	main = m;
        shapes = new ArrayList<Colorable>();
        actions = new ArrayList<Action>();
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
        
            public void mousePressed(MouseEvent e){
            	dragged = false;
                xo = e.getX();
                yo = e.getY();
                if(mode==0) {
                	while(noOfActions<actions.size()) {
                       	actions.remove(actions.size()-1);
                       }
	                switch (shape) {
	                	case 1:
	                		shapes.add(noOfShapes,new Square(xo,yo,xo,yo));
	                		break;
	                	case 0:
	                		shapes.add(noOfShapes,new Rectangle(xo,yo,xo,yo));
	                		break;
	                	case 2:
	                		shapes.add(noOfShapes,new Circle(xo,yo,xo,yo));
	                		break;
	                	case 3:
	                		shapes.add(noOfShapes,new Triangle(xo,yo,xo,yo));
	                		break;
	                	case 4:
	                		shapes.add(noOfShapes,new Line(xo,yo,xo,yo));
	                		break;
	                }
	                noOfShapes++;
                }
                else select(xo,yo);    
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            	if(mode == 0) {
	            	if(!dragged) 
	            		shapes.remove(--noOfShapes);
	            	else {
	            		actions.add(new Action("Draw",null,shapes.get(noOfShapes-1)));
	            		noOfActions++;
            		}
	            	if(!selected) {
    	                while(noOfShapes<shapes.size()) {
    	                	shapes.remove(shapes.size()-1);
    	                }
                	}
            	}	
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            
            @Override
			public void mouseMoved(MouseEvent e) {
				if(selected) {
					switch(mode) {
					case 1:
						c.setXo(e.getX());
						c.setYo(e.getY());
						c.setXDraw(e.getX());
						c.setYDraw(e.getY());
						repaint();
						break;
						
					case 2:
						if(c instanceof Line) {
							c.setWidth(e.getX());
							c.setHeight(e.getY());
						}
						else {
						c.setWidth(e.getX()-c.getXo());
						c.setHeight(e.getY()-c.getYo());
						c.coordinate();
						}
						repaint();
						break;
						
					case 3:
						c.setXDraw(e.getX());
						c.setYDraw(e.getY());
						c.setXo(e.getX());
						c.setYo(e.getY());
						repaint();
						break;
						
					default:
						break;
					}
				}
			}

			@Override
            public void mouseDragged(MouseEvent e){
				dragged = true;
            	if(mode==0) {
	            	x = e.getX()-xo;
	                y = e.getY()-yo;
	                switch (shape) {
	                	case 0:
			                Rectangle r = new Rectangle(xo,yo,x,y);
			                r.setColor(g.getColor());
			                r.setFilled(main.getFill());
			                shapes.set(noOfShapes-1,r);
			                break;
			             
	                	case 1:
	                		Square s = new Square(xo,yo,x,y);
			                s.setColor(g.getColor());
			                s.setFilled(main.getFill());
			                shapes.set(noOfShapes-1,s);
			                break;
			                
	                	case 2:
	                		Circle c = new Circle(xo,yo,x,y);
	                		c.setColor(g.getColor());
			                c.setFilled(main.getFill());
			                shapes.set(noOfShapes-1,c);
			                break;
			                
	                	case 3:
	                		Triangle t = new Triangle(xo,yo,x,y);
	                		t.setColor(g.getColor());
	                		t.setFilled(main.getFill());
	                		shapes.set(noOfShapes-1, t);
	                		break;
	                		
	                	case 4:
	                		Line line = new Line(xo,yo,e.getX(),e.getY());
	                		line.setColor(g.getColor());
	                		shapes.set(noOfShapes-1,line);
			                break;
	                }
	                repaint();
            	}
            }
        });
      
    }
    
    @Override
    protected void paintComponent(Graphics gr){
    if (image == null) {
    image = createImage(getSize().width, getSize().height);
    g = (Graphics2D) image.getGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setBackground(Color.WHITE);
    blank();
    shapes.clear();
    actions.clear();
    noOfActions=0;
    noOfShapes=0;
    repaint();
    }
    else {
    	blank();
    	for(int i=0;i<noOfShapes;i++) {
    		Colorable c = shapes.get(i);
    		g.setColor(c.getColor());
    		g.draw(c);
    		if(c instanceof Fillable) {
    			if(((Fillable) c).isFilled())
    				g.fill(c);
    		}
    	}	
    }
    gr.drawImage(image, 0, 0, null);
    }
    
   public void clear() {
	   if(JOptionPane.showConfirmDialog(main, "Are you sure you want to clear the canvas?", "Confirm clear", 0, 2, null) == 0) {
	    blank();
	    shapes.clear();
	    actions.clear();
	    noOfActions=0;
	    noOfShapes=0;
	    repaint();
	   }
  }
   
   private void blank() {
	   Paint p = g.getPaint();
	   g.setPaint(Color.WHITE);
	   g.fillRect(0, 0, getSize().width, getSize().height);
	   g.setPaint(p);
   }
   
   public void setPaint(Color c) {
	   g.setColor(c);
   }

   public void undo() {
	   if(noOfActions>0) {
		   Action a = actions.get(--noOfActions);
		   Colorable current = a.getCurrentShape();
		   switch(a.getAction()) {
		   		case "Draw":
		   			if(noOfShapes>0)
		   			   noOfShapes--;
		   			break;
		   			
		   		case "Move":
		   			int x = current.getXDraw();
		   			int y = current.getYDraw();
		   			current.setXDraw(a.getDesiredShape().getXDraw());
		   			current.setYDraw(a.getDesiredShape().getYDraw());
		   			a.getDesiredShape().setXDraw(x);
		   			a.getDesiredShape().setYDraw(y);
		   			if(! (current instanceof Triangle) && ! (current instanceof Line)) {
			   			int xo = current.getXo();
			   			int yo = current.getYo();
			   			current.setXo(a.getDesiredShape().getXo());
			   			current.setYo(a.getDesiredShape().getYo());
			   			a.getDesiredShape().setXo(xo);
			   			a.getDesiredShape().setYo(yo);
		   			}
		   			shapes.remove(current);
		   			shapes.add(a.getIndex(),current);
		   			break;
		   			
		   		case "Resize":
		   			int w = current.getW();
		   			int h = current.getH();
		   			current.setWidth(a.getDesiredShape().getW());
		   			current.setHeight(a.getDesiredShape().getH());
		   			a.getDesiredShape().setWidth(w);
		   			a.getDesiredShape().setHeight(h);
		   			if(! (current instanceof Line)) {
	   				int x1 =  current.getXDraw();
	   				int y1 = current.getYDraw();
	   				current.setXDraw(a.getDesiredShape().getXDraw());
	   				current.setYDraw(a.getDesiredShape().getYDraw());
	   				a.getDesiredShape().setXDraw(x1);
	   				a.getDesiredShape().setYDraw(y1);
		   			}
		   			break;
		   			
		   		case "Copy":
		   			a.setIndex(shapes.indexOf(current));
		   			shapes.remove(current);
		   			noOfShapes--;
		   			break;
		   			
		   		case "Color":
		   			Color c = current.getColor();
		   			current.setColor(a.getDesiredShape().getColor());
		   			a.getDesiredShape().setColor(c);
		   			if(current instanceof Fillable) {
		   				Boolean b = ((Fillable) current).isFilled();
		   				((Fillable) current).setFilled(((Fillable) a.getDesiredShape()).isFilled());
		   				((Fillable) a.getDesiredShape()).setFilled(b);
		   			}
		   			break;
		   			
		   		case "Delete":
		   			shapes.add(a.getIndex(), current);
		   			noOfShapes++;
		   			repaint();
		   			break;
		   }
	   }
	   repaint();
   }
   
   public void redo() {
	   if(noOfActions<actions.size()) {
		   Action a = actions.get(noOfActions++);
		   Colorable current = a.getCurrentShape();
		   switch(a.getAction()) {
		   		case "Draw":
		   			if(noOfShapes<shapes.size())
		   			   noOfShapes++;
		   			break;
		   			
		   		case "Move":
		   			int x = current.getXDraw();
		   			int y = current.getYDraw();
		   			current.setXDraw(a.getDesiredShape().getXDraw());
		   			current.setYDraw(a.getDesiredShape().getYDraw());
		   			a.getDesiredShape().setXDraw(x);
		   			a.getDesiredShape().setYDraw(y);
		   			if(! (current instanceof Triangle) && ! (current instanceof Line)) {
			   			int xo = current.getXo();
			   			int yo = current.getYo();
			   			current.setXo(a.getDesiredShape().getXo());
			   			current.setYo(a.getDesiredShape().getYo());
			   			a.getDesiredShape().setXo(xo);
			   			a.getDesiredShape().setYo(yo);
		   			}
		   			a.setIndex(shapes.indexOf(current));
		   			shapes.remove(current);
		       		shapes.add(current);
		   			break;
		   			
		   		case "Resize":
		   			int w = current.getW();
		   			int h = current.getH();
		   			current.setWidth(a.getDesiredShape().getW());
		   			current.setHeight(a.getDesiredShape().getH());
		   			a.getDesiredShape().setWidth(w);
		   			a.getDesiredShape().setHeight(h);
		   			if(! (current instanceof Line)) {
		   				int x1 =  current.getXDraw();
		   				int y1 = current.getYDraw();
		   				current.setXDraw(a.getDesiredShape().getXDraw());
		   				current.setYDraw(a.getDesiredShape().getYDraw());
		   				a.getDesiredShape().setXDraw(x1);
		   				a.getDesiredShape().setYDraw(y1);
			   			}
		   			break;
		   			
		   		case "Copy":
		   			shapes.add(a.getIndex(), current);
		   			noOfShapes++;
		   			break;
		   			
		   		case "Color":
		   			Color c = current.getColor();
		   			current.setColor(a.getDesiredShape().getColor());
		   			a.getDesiredShape().setColor(c);
		   			if(current instanceof Fillable) {
		   				Boolean b = ((Fillable) current).isFilled();
		   				((Fillable) current).setFilled(((Fillable) a.getDesiredShape()).isFilled());
		   				((Fillable) a.getDesiredShape()).setFilled(b);
		   			}
		   			break;
		   			
		   		case "Delete":
		   			a.setIndex(shapes.indexOf(current));
		   			shapes.remove(current);
		   			noOfShapes--;
		   			break;
		   }
	   }
	   repaint();
   }
   
   public void setShape(int s) {
	   shape = s;
   }
   
   public void setMode(int m) {
	   mode = m;
   }
   
   private void select(int xo,int yo) {
	   if(selected) {
		   selected = false;
		   c = null;
   		}
	   	else {
	   		while(noOfActions<actions.size()) {
	   	       	actions.remove(actions.size()-1);
	   	       }
	   		Colorable s;
	       	for(int i=0;i<noOfShapes;i++) {
	       		s = shapes.get(noOfShapes-i-1);
	       		if(s instanceof Line) {
	       			if(((Line)s).ptSegDist(xo, yo)<10) {
	       				c=s;
	       				break;
	       			}
	       		}
	       		else if(s.contains(xo, yo)) {
	       			c=s;
	       			break;
	       		}	
	       	}
	       	if (c!=null) {
	       		selected = true;
	       		switch(mode) {
	       			case 1:
	       				actions.add(new Action("Move",copy(c),c));
	       				noOfActions++;
	       				actions.get(noOfActions-1).setIndex(shapes.indexOf(c));
	       				shapes.remove(c);
			       		shapes.add(c);
	       				break;
	       				
	       			case 2:
	       				actions.add(new Action("Resize",copy(c),c));
	       				noOfActions++;
	       				break;
	       				
	       			case 3:
	       				shapes.add(copy(c));
		       			noOfShapes++;
		       			c = shapes.get(noOfShapes-1);
		       			actions.add(new Action("Copy",null,c));
		       			noOfActions++;
	       				break;
	       				
	       			case 4:
	       				if(!g.getColor().equals(c.getColor()) && c instanceof Line) {
	       				actions.add(new Action("Color",copy(c),c));
		       			c.setColor(g.getColor());
						noOfActions++;
	       				}
	       				else if(c instanceof Fillable) {
	       					if(!g.getColor().equals(c.getColor()) || ! ((Fillable)c).isFilled().equals(main.getFill())) {
	       						actions.add(new Action("Color",copy(c),c));
	    		       			c.setColor(g.getColor());
	    		       			((Fillable)c).setFilled(main.getFill());
	    		       			noOfActions++;
	       					}
       					}
						selected = false;
						c = null;
	       				break;
	       				
	       			case 5:
	       				actions.add(new Action("Delete",null,c));
	       				noOfActions++;
		       			actions.get(noOfActions-1).setIndex(shapes.indexOf(c));
		       			shapes.remove(c);
		       			noOfShapes--;
		       			selected = false;
		       			c = null;
	       				break;
	       		}
	       		repaint();
	       	}
	   
	   	}
   }

   private Colorable copy(Colorable c) {
	  if(c instanceof Rectangle) {
		 Rectangle copy = new Rectangle(c.getXo(),c.getYo(),((Rectangle) c).getWidth(),((Rectangle) c).getHeight());
		 copy.setColor(c.getColor());
		 copy.setFilled(((Rectangle) c).isFilled());
		 copy.setXDraw(c.getXDraw());
		 copy.setYDraw(c.getYDraw());
		 return copy;
	  }
	  if(c instanceof Square) {
		 Square copy = new Square(c.getXo(),c.getYo(),(int)((Square) c).getWidth(),(int)((Square) c).getHeight());
		 copy.setColor(c.getColor());
		 copy.setFilled(((Square) c).isFilled());
		 copy.setXDraw(c.getXDraw());
		 copy.setYDraw(c.getYDraw());
		 return copy;
	  }
	  if(c instanceof Circle) {
		 Circle copy = new Circle(c.getXo(),c.getYo(),(int)((Circle) c).getWidth(),(int)((Circle) c).getHeight());
		 copy.setColor(c.getColor());
		 copy.setFilled(((Circle) c).isFilled());
		 copy.setXDraw(c.getXDraw());
		 copy.setYDraw(c.getYDraw());
		 return copy;
	  }
	  if(c instanceof Line) {
		  Line copy = new Line((int)((Line) c).getX1(),(int)((Line) c).getY1(),(int)((Line) c).getX2(),(int)((Line) c).getY2());
		  copy.setColor(c.getColor());
		  copy.setXDraw(c.getXDraw());
		  copy.setYDraw(c.getYDraw());
		  return copy;
	  }
	  if(c instanceof Triangle) {
		  Triangle copy = new Triangle(c.getXo(),c.getYo(),c.getW(),c.getH());
		  copy.setColor(c.getColor());
		  copy.setFilled(((Triangle) c).isFilled());
		  copy.setXDraw(c.getXDraw());
		  copy.setYDraw(c.getYDraw());
		  return copy;
	  }
	  return null;
   } 
}