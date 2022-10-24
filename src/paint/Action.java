package paint;
import paint.shapes.*;
public class Action {
	private String action;
	private Colorable desiredShape , currentShape;
	private int index;
	
	public Action(String mode , Colorable o , Colorable n) {
		action = mode;
		desiredShape = o;
		currentShape = n;
	}


	public Colorable getDesiredShape() {
		return desiredShape;
	}

	public void setDesiredShape(Colorable oldShape) {
		this.desiredShape = oldShape;
	}

	public Colorable getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Colorable newShape) {
		this.currentShape = newShape;
	}

	public String getAction() {
		return action;
	}
	
	public void setIndex(int i) {
		index = i;
	}
	
	public int getIndex() {
		return index;
	}
}