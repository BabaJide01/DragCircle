package assignment3;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleClass extends Circle implements ComponentInterface{
	Color circleColor;

	
	public CircleClass(Point2D p, double r, Color c) {
		super(p.getX(), p.getY(), r);
		
		this.circleColor = c;
		this.setFill(circleColor);
	}
	
	public void ChangeColor(Color c) {
		this.setFill(c);
	}

	@Override
	public void move(double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		this.setCenterX(this.getCenterX()+deltaX);
		this.setCenterY(this.getCenterY()+deltaY);
	}

	@Override
	public Point2D getLocation() {
		// TODO Auto-generated method stub
		Point2D p = new Point2D(this.getCenterX(), this.getCenterY());
		return p;
	}

}
