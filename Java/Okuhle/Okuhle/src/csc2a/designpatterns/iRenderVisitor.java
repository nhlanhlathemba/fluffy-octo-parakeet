package csc2a.designpatterns;

import csc2a.model.AnimatedObject;
import csc2a.model.Drone;
import csc2a.model.Enemy;
import csc2a.model.Player;
import csc2a.model.Shot;

/**
 * 
 * AbstractVisitor interface
 * Used to define all of the render functions for your different GameObjects
 *
 */
public interface iRenderVisitor {
	/* TODO: render(YourGameObjectA a); method */
	
	public void render(Shot A);
	/* TODO: render(YourGameObjectB b); method */

	public void render(Player player);

	public void render(Drone drone);


	
	// ...
	
	/* TODO: render(YourGameObjectC c); method */
}
