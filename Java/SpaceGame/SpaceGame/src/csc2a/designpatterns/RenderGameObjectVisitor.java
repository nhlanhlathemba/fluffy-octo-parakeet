package csc2a.designpatterns;

import csc2a.model.Enemy;
import csc2a.model.Shot;
import csc2a.model.SpaceShip;
import javafx.scene.canvas.GraphicsContext;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 *
 */
public class RenderGameObjectVisitor implements iRenderVisitor{
	
	//Attributes
	GraphicsContext gc = null;
	
	/**
	 * Mutator for the GraphicsContext from the GameCanvas
	 * Used to set gc so the Visitor can draw things on the Canvas
	 * @param gc the GraphicsContext from the GameCanvas
	 */
	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;
	}

	@Override
	public void render(SpaceShip ship) {
		ship.getExhaust().drawAnimation(gc,ship.getXLocation(), ship.getYLocation());
		gc.drawImage(ship.getShip(), ship.getXLocation(), ship.getYLocation());
		for(Shot s: ship.getShots()) {
			s.getImage().drawAnimation(gc, s.getPoint().getX()+120, s.getPoint().getY()+60);
		}
		
	}

	@Override
	public void render(Enemy enemy) {
		gc.drawImage(enemy.getImage(), enemy.getXLocation(), enemy.getYLocation());
		for(Shot s: enemy.getBullets()) {
			s.getImage().drawAnimation(gc, s.getPoint().getX()+120, s.getPoint().getY()+60);
		}
	}
	
	

	

}
