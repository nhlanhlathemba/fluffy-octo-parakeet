package csc2a.model;

import java.util.ArrayList;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.Rectangle;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import csc2a.designpatterns.iRenderVisitor;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Enemy extends GameObject {
	private OBJTYPE type;
	private AnimatedObject current;
	private ArrayList<AnimatedObject> actions;
	private ArrayList<Shot> ShotsFired;
	protected double Height;
	protected double Width;
	private static int Speed =15;
	protected Rectangle2D hitZone;
	private double health = 100;
	private double hitFactor;
	protected Player player;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Enemy(double x,double y) {
		super(x,y,0,0);
	//	super.direction = DIRECTION.EAST;
		ShotsFired = new ArrayList<Shot>();
		actions = new  ArrayList<AnimatedObject>();
	}

	public abstract void update(int timeLapsed);
	public abstract void render(GraphicsContext gc);
	
    public boolean isHit(Point2D point) {
    	return hitZone.contains(point);
    }


	/**
	 * @return the current
	 */
	public AnimatedObject getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(AnimatedObject current) {
		this.current = current;
	}

	/**
	 * @return the actions
	 */
	public ArrayList<AnimatedObject> getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(ArrayList<AnimatedObject> actions) {
		this.actions = actions;
	}

	/**
	 * @return the shotsFired
	 */
	public ArrayList<Shot> getShotsFired() {
		return ShotsFired;
	}

	/**
	 * @param shotsFired the shotsFired to set
	 */
	public void setShotsFired(ArrayList<Shot> shotsFired) {
		ShotsFired = shotsFired;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return Height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		Height = height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return Width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		Width = width;
	}

	/**
	 * @return the speed
	 */
	public static int getSpeed() {
		return Speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public static void setSpeed(int speed) {
		Speed = speed;
	}

	/**
	 * @return the type
	 */
	public OBJTYPE getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(OBJTYPE type) {
		this.type = type;
	}


	/**
	 * @return the hitZone
	 */
	public Rectangle2D getHitZone() {
		return hitZone;
	}


	/**
	 * @param rectangle2d the hitZone to set
	 */
	public void setHitZone(Rectangle2D rectangle2d) {
		this.hitZone = rectangle2d;
	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * @return the hitFactor
	 */
	public double getHitFactor() {
		return hitFactor;
	}

	/**
	 * @param hitFactor the hitFactor to set
	 */
	public void setHitFactor(double hitFactor) {
		this.hitFactor = hitFactor;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
