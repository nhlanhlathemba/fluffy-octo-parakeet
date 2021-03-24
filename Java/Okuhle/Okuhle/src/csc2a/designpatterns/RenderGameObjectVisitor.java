package csc2a.designpatterns;

import csc2a.model.AnimatedObject;
import csc2a.model.DIRECTION;
import csc2a.model.Drone;
import csc2a.model.Enemy;
import csc2a.model.OBJTYPE;
import csc2a.model.Player;
import csc2a.model.Shot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * ConcreteVisitor class
 * Used to visit each GameObject and render them onto the GameCanvas
 * @author  nhlanhlaThemba
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
	public void render(Shot A) {
	    AnimatedObject obj = A.getCurrent();
	  	 if(obj.getFrameN() >= obj.getStrip().size()-1 ) {
	  		 obj.setFrameN(0);
	  	 }else{	   	        
	   	    	obj.setFrameN(obj.getFrameN()+1);
	  	     }
	         gc.drawImage(obj.getStrip().get(obj.getFrameN()), A.getXLocation(), A.getYLocation());
	}

	@Override
	public void render(Player player) {
		AnimatedObject obj = player.getCurrent();
		  	 if(obj.getFrameN() >= obj.getStrip().size()-1 ) {
		  		   //   obj.afPlayed = true;
		  		  //  player.setAllowed(true);
		  		  
	    		      obj.setFrameN(0);
	   	     }else{	   	        
	   	    	obj.setFrameN(obj.getFrameN()+1);
	  	     }
		  	 
		 /* 	if(player.isJumping()) {
	  		    if(player.getCurrent().getStrip().get(obj.getFrameN()).equals(player.getCurrent().getStrip().get(2)))
	  		    	 obj.setFrameN(1);
	  		  }*/
	         gc.drawImage(obj.getStrip().get(obj.getFrameN()), player.getXLocation(), player.getYLocation());
	       
	       /** HANDLES SHOTS **/
	       for(Shot A: player.getShotsFired()) {
	    	   A.move();
	    	   this.render(A);
	       }
	 
		
	}

	
	public void render(Drone enemy) {
	    AnimatedObject obj = enemy.getCurrent();
	  	 if(obj.getFrameN() >= obj.getStrip().size()-1 ) {
	  		/* if(enemy.getType().equals(OBJTYPE.ENEMY_DRONE)) {	  		 
	  		     if(obj.equals(enemy.getActions().get(2))) {
	  			     ((Drone) enemy).setTurning(false);
	  			     enemy.setDirection(DIRECTION.EAST);
	  	 	     }else if(obj.equals(enemy.getActions().get(3))) {
	  	 	    	((Drone) enemy).setTurning(false);
	  	 	    	enemy.setDirection(DIRECTION.WEST);
	  	 	     }
	  		 } */
	  		 obj.setFrameN(0);
	  	 }else{	   	        
	   	    	obj.setFrameN(obj.getFrameN()+1);
	  	     }
	         gc.drawImage(obj.getStrip().get(obj.getFrameN()), enemy.getXLocation(), enemy.getYLocation());
		
	} 
	
}
