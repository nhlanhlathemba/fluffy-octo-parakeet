package csc2a.designpatterns;

import csc2a.model.Enemy;
import csc2a.model.SpaceShip;

/**
 * 
 * AbstractVisitor interface
 * Used to define all of the render functions for your different GameObjects
 *
 */
public interface iRenderVisitor {

	public void render(SpaceShip ship);

	public void render(Enemy enemy);

}
