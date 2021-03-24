package csc2a.model;

import java.io.FileNotFoundException;

import csc2a.file.GameFileHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {
	private Image background1;
	private Image background2;
	Point2D point;
	Velocity velocity;
	public Background(Velocity v) {
		point = new Point2D(0,0);
		velocity = v;
		
		try {
			setBackground1(GameFileHandler.readImage("data\\spacefield_a.png"));
			setBackground2(GameFileHandler.readImage("data\\spacefield_b.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void move() {
		 Point2D newPoint = new Point2D(point.getX() + velocity.getX(), point.getY()+velocity.getY());
		   point = newPoint;	
	}
	
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(background1, point.getX(), point.getY());
		gc.drawImage(background2, point.getX() + background1.getWidth(), point.getY());
	}
	
	/**
	 * @return the background1
	 */
	public Image getBackground1() {
		return background1;
	}
	/**
	 * @param background1 the background1 to set
	 */
	public void setBackground1(Image background1) {
		this.background1 = background1;
	}
	/**
	 * @return the background2
	 */
	public Image getBackground2() {
		return background2;
	}
	/**
	 * @param background2 the background2 to set
	 */
	public void setBackground2(Image background2) {
		this.background2 = background2;
	}
}
