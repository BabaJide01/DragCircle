package assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Composite extends Rectangle implements ComponentInterface {
	ArrayList<ComponentInterface> PlacedCircles = new ArrayList<>();
	Color color;
	Random StanColor = new Random();
	
	public Composite(double x, double y, int width, int height) {
		super(x,y,width,height);
		this.color = RandomColor();
		this.setStroke(this.color);
		this.setStrokeWidth(6);
		
		this.setFill(Color.TRANSPARENT);
	}
	
	public Color RandomColor() {
		float r = StanColor.nextFloat();
		float g = StanColor.nextFloat();
		float b = StanColor.nextFloat();
		float a = StanColor.nextFloat();
		
		while(r == 1.0 || g == 1.0  || b == 1.0 ) {
			r  = StanColor.nextFloat();
			g  = StanColor.nextFloat();
			b  = StanColor.nextFloat();
		}
		
		return new Color(b, g, r, a);		
	}
	
	public void addCircle(ComponentInterface circle) {
		PlacedCircles.add(circle);
		Iterator<ComponentInterface> replaceCircle = PlacedCircles.iterator();
		while(replaceCircle.hasNext()) {
			ComponentInterface cricle = replaceCircle.next();
			if(circle instanceof CircleClass) ((CircleClass)  circle).ChangeColor(this.color);
		}
		
	}
	
	public boolean IfCircle(ComponentInterface circle) {
		if(circle instanceof CircleClass) {
			if(this.contains(((CircleClass) circle).getCenterX(), ((CircleClass) circle).getCenterY())){
				return true;
			}
		}
		return false;
	}


	@Override
	public void move(double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
		Iterator<ComponentInterface> CircleLst = PlacedCircles.iterator();
		while(CircleLst.hasNext()) {
			ComponentInterface circle = CircleLst.next();
			if(circle instanceof CircleClass) ((CircleClass) circle).move(deltaX, deltaY);
 		}
	}

	@Override
	public Point2D getLocation() {
		// TODO Auto-generated method stub
		return new Point2D(this.getX(), this.getY());
	}

}
