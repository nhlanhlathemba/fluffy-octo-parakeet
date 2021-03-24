package csc2a.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import csc2a.designpatterns.iRenderVisitor;
import csc2a.file.GameFileHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Enemy extends GameObject {

	private Image image;
	private ArrayList<Shot> bullets;
	SpaceShip ship;
	
	public Enemy(SpaceShip ship,double x,double y) {
		this.ship = ship;
		this.setLocation(new Point2D(x,y));
		bullets = new ArrayList<Shot>();
		try {
			image = GameFileHandler.readImage("data\\drone.png");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void move() {
		Random r = new Random(2);
		if(r.nextBoolean())
		this.setVelocity(new Velocity(-r.nextDouble(), -r.nextDouble()));
		else
			this.setVelocity(new Velocity(r.nextDouble(), r.nextDouble()));
		super.move();
		for(Shot s:bullets) {
			s.move();
		}
	}
	
	public ArrayList<Shot> getBullets(){
		return bullets;
	}
	
	public void shoot() {
		double xv =0,yv=0;
		if(this.getXLocation()>ship.getXLocation()) {
			xv = -10;
		}if(this.getXLocation()<ship.getXLocation()) {
			xv =10;
		}if(this.getYLocation()>ship.getYLocation()) {
			yv=-10;
		}if(this.getYLocation()<ship.getYLocation()) {
			yv =10;
		}
		
		bullets.add(new Shot(this.getXLocation(),this.getYLocation(),new Velocity(xv,yv)));
	}
	
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.render(this);
	}



	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}



	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

}
