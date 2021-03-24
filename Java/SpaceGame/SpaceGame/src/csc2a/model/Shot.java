package csc2a.model;

import java.util.ArrayList;
import java.util.List;

import csc2a.designpatterns.AbstractObserver;
import csc2a.designpatterns.AbstractSubject;
import javafx.geometry.Point2D;

public class Shot implements AbstractSubject {
	private AnimatedImage image;
	private Velocity velocity;
	private Point2D point;
	 private ArrayList<AbstractObserver> observers = new ArrayList<>();
	 private boolean hit; 
	
     public Shot( double x, double y, Velocity velocity) {
    	this.velocity = velocity;
    	 point = new Point2D(x,y);
			image = new AnimatedImage("shot",x+120,y+60);
     }
     public void move() {
    	 Point2D newPoint = new Point2D(point.getX() + velocity.getX(), point.getY()+velocity.getY());
		   point = newPoint;	
     }
     
	/**
	 * @return the image
	 */
	public AnimatedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(AnimatedImage image) {
		this.image = image;
	}

	/**
	 * @return the velocity
	 */
	public Velocity getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the point
	 */
	public Point2D getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Point2D point) {
		this.point = point;
	}
	@Override
	public void attach(AbstractObserver obs) {
		 if(obs != null && !observers.contains(obs)){observers.add(obs) ;}
	}
	@Override
	public void detach(AbstractObserver obs) {
		observers.remove(obs);
		
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		 List<AbstractObserver> observersLocal = null; 
		 if(!hit){return;} 
		 observersLocal = new ArrayList<>(this.observers); 
		 this.hit = false; 
		 for(AbstractObserver obs: observersLocal){
			 obs.update();} 
		
	}
	@Override
	public Object getUpdate(AbstractObserver obs) {
		
		return null;
	}
	}
