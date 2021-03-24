package csc2a.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import csc2a.designpatterns.iRenderVisitor;
import csc2a.file.GameFileHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpaceShip extends GameObject {
	private Image Ship;
	private AnimatedImage exhaust;
	private ArrayList<Shot> shots;

	public SpaceShip(String path, double x, double y) {
		super(x,y,0,0);
		setShots(new ArrayList<Shot>());
		try {
			Ship = GameFileHandler.readImage(path);
			exhaust = new AnimatedImage("exhaust", x-Ship.getWidth(),y);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void shoot() {
		shots.add(new Shot(this.getXLocation(),this.getYLocation(), new Velocity(20,0)));
	}
	
	@Override
	public void move() {
		super.move();
		for(Shot s:shots) {
			s.move();
		}
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.render(this);

	}

	/**
	 * @return the Ship
	 */
	public Image getShip() {
		return Ship;
	}

	/**
	 * @param Ship the Ship to set
	 */
	public void setShip(Image Ship) {
		this.Ship = Ship;
	}

	
	/**
	 * @return the exhaust
	 */
	public AnimatedImage getExhaust() {
		return exhaust;
	}

	/**
	 * @param exhaust the exhaust to set
	 */
	public void setExhaust(AnimatedImage exhaust) {
		this.exhaust = exhaust;
	}

	/**
	 * @return the shots
	 */
	public ArrayList<Shot> getShots() {
		return shots;
	}

	/**
	 * @param shots the shots to set
	 */
	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
	}



}
