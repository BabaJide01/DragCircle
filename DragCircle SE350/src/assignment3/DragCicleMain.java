package assignment3;

import java.util.ArrayList;

import java.util.Iterator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DragCicleMain extends Application {
	Point2D FinalPosition = null;

	private final double CircleR = 20;
	private final int RectWidth = 200;
	private final int RectHeight = 200;
	Scene scene;
	Pane root;
	ComponentInterface shape;
	ArrayList<ComponentInterface> Shapes = new ArrayList<>();
	boolean dragging = false;
	Composite rect;
	CircleClass circle;
	private ComponentInterface getShape(Point2D point) {
		Iterator<ComponentInterface> i = Shapes.iterator();
		ComponentInterface Pointer = null;
		while (i.hasNext()) {
			ComponentInterface temp = i.next();
			if (temp instanceof Composite) {
				if (((Composite) temp).contains(point))
					Pointer = temp;
			}
			if (temp instanceof CircleClass) {
				if (((CircleClass) temp).contains(point))
					Pointer = temp;
			}
		}
		return Pointer;
	}
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent mouseEvent) {
			Point2D clickedPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String event = mouseEvent.getEventType().getName();
			if (dragging == false)
				shape = getShape(clickedPoint);
			switch (event) {
			/// once mouse is dragged
			case ("MOUSE_DRAGGED"):
				dragging = true;
				if (shape != null && FinalPosition != null) {
					double deltaX = clickedPoint.getX() - FinalPosition.getX();
					double deltaY = clickedPoint.getY() - FinalPosition.getY();
					shape.move(deltaX, deltaY);
				}
				break;
			/// mouse released
			case ("MOUSE_RELEASED"):
				if (shape == null) {
					if (mouseEvent.getButton() == MouseButton.PRIMARY) {
						CircleClass circle = new CircleClass(clickedPoint, CircleR, Color.BLACK);
						Shapes.add(circle);
						root.getChildren().add(circle);
					}
					if (mouseEvent.getButton() == MouseButton.SECONDARY) {
						Composite rectangle = new Composite(clickedPoint.getX(), clickedPoint.getY(), RectWidth,
								RectHeight);
						Shapes.add(rectangle);
						root.getChildren().add(rectangle);
					}
				} else if (shape != null && shape instanceof CircleClass) {
					Iterator<ComponentInterface> i = Shapes.iterator();
					while (i.hasNext()) {
						ComponentInterface shp = i.next();
						if (shp instanceof Composite && ((Composite) shp).contains(clickedPoint)) {
							((Composite) shp).addCircle(shape);
							break;
						}
					}
				}
				dragging = false;
				break;
			}
			FinalPosition = clickedPoint;
		}
	};
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		root = new AnchorPane();
		scene = new Scene(root, 700, 500);
		stage.setScene(scene);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
