package csc2a.model;

import java.io.Serializable;
import java.util.Vector;

import csc2a.designpatterns.iRenderable;
import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * The class from which all of your game objects must inherit
 * Extend this class for all of your custom game objects
 */
public abstract class GameObject extends Node implements iRenderable ,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attributes
	
	private Point2D location; //Stores the (x,y) coordinate of the GameObject in the world	
	protected DIRECTION direction;
	private double VelocityX;
	private double VelocityY;
	private boolean isAllowed =true;
	private int floorHeight = 570;
	private int gravity = 20;
	public double distance =0;
	/**
	 * Default Constructor
	 */
	public GameObject() {
		location = new Point2D(0, 0);
		this.setTranslateX(0);
		this.setTranslateY(0);
		VelocityX = 0;
		VelocityY =0;
	}
	
	
	public void addVelocity(double x,double y) {
		VelocityX += x;
		VelocityY += y;
	}
	
	/**
	 * Parameterized Constructor
	 * @param x x-coord of the GameObject
	 * @param y y-coord of the GameObject
	 */
	public GameObject(double x, double y) {
		location = new Point2D(x, y);
		this.setTranslateX(x);
		this.setTranslateY(y);
		VelocityX = 0;
		VelocityY = 0;
		
	}
	
	/**
	 * Parameterized Constructor
	 * @param x x-coord of the GameObject
	 * @param y y-coord of the GameObject
	 * @param Vel velocity of the GameObject
	 */
	public GameObject(double x, double y, int VelX, int VelY) {
		location = new Point2D(x, y);
		this.setTranslateX(x);
		this.setTranslateY(y);
		VelocityX = VelX;
		VelocityY = VelY;
		
	}
	
	public DIRECTION getDirection() {
		return direction;
	}
	
	public void setDirection(DIRECTION direction) {
		 this.direction = direction;
	}
	
	/**
	 * Parameterized Constructor
	 * @param location Point2D representing the GameObject's location
	 */
	public GameObject(Point2D location) {
		this.location = location;
	}
	
	/**
	 * Accessor for the GameObject's location
	 * @return Location of GameObject
	 */
	public Point2D getLocation() {
		return location;
	}
	
	/**
	 * Accessor for the GameObject's X location
	 * @return X coordinate 
	 */
	public double getXLocation() {
		return location.getX();
	}
	
	/**
	 * Accessor for the GameObject's Y location
	 * @return Y coordinate
	 */
	public double getYLocation() {
		return location.getY();
	}
	
	/**
	 * Mutator for GameObject's location
	 * @param newLocation Point2D representing location
	 */
	public void setLocation(Point2D newLocation) {
		location = newLocation;
	}
	
	
	
	/**
	 * Mutator for GameObject's location
	 * @param x new x-coord of GameObject
	 * @param y new y-coord of GameObject
	 */
	public void setLocation(int x, int y) {
		location = new Point2D(x, y);
	}
	
	public void fall() {
		if ( this.getLocation().getY() <= floorHeight) {
		Point2D newPoint = new Point2D(location.getX(),location.getY()+gravity);
		location = newPoint;
		if ( this.getLocation().getY() <= floorHeight-50) {
		 isAllowed=false;
		}
	 }
	}

    public void move() {
    	
    //		if(direction==DIRECTION.EAST) {
    		Point2D newPoint = new Point2D(location.getX()+ getVelocityX(),location.getY()+VelocityY);
    		  location = newPoint;
    		  this.setTranslateX(location.getX());
    			this.setTranslateY(location.getY());
    		  distance+=Math.abs(getVelocityX());
    	/*	} else if(direction==DIRECTION.WEST) {
        		Point2D newPoint = new Point2D(location.getX() + VelocityX,location.getY());
        		location = newPoint;
    		}else if(direction==DIRECTION.NORTH) {
        		Point2D newPoint = new Point2D(location.getX() + VelocityX,location.getY() + VelocityY);
        		location = newPoint;
    		
 
 
 /*
    		switch(direction) {
    		case NORTH:
    			Point2D newPoint = new Point2D(location.getX(),location.getY() - Velocity);
        		location = newPoint;
    		case SOUTH:
    			Point2D newPoint2 = new Point2D(location.getX(),location.getY() + Velocity);
        		location = newPoint2;
    		case EAST:{
    			System.out.println("2");
    			Point2D newPoint3 = new Point2D(location.getX() + Velocity,location.getY());
        		location = newPoint3;
        		System.out.println("3");}
    		case WEST:
    			Point2D newPoint4 = new Point2D(location.getX() - Velocity,location.getY());
        		location = newPoint4;
    		} */ 
    	}
    
    


	/**
	 * @return the X velocity
	 */
	public double getVelocityX() {
		return VelocityX;
	}
	
	/**
	 * @return the Y velocity
	 */
	public double getVelocityY() {
		return VelocityY;
	}




	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocityX(double velocity) {
		VelocityX = velocity;
	}




	/**
	 * @param velocityY the velocityY to set
	 */
	public void setVelocityY(double velocityY) {
		VelocityY = velocityY;
	}


	/**
	 * @return the isAllowed
	 */
	public boolean isAllowed() {
		return isAllowed;
	}


	/**
	 * @param isAllowed the isAllowed to set
	 */
	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}
	
	
	
	/* 
	 * Note: All classes that extend this class MUST implement 
	 * the accept(iRenderVisitor visitor) method from iRenderable
	 */
}


