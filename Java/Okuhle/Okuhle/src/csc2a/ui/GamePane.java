package csc2a.ui;

import java.io.File;


import csc2a.model.DIRECTION;
import csc2a.model.Player;
import csc2a.util.KMBuffer;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

/**
 * 
 * GamePane provides a custom container to manage all game interactions
 * and host the GameCanvas
 * @author nhlanhlaThemba
 *
 */
public class GamePane extends StackPane{
	
	//Attributes
	public GameCanvas canvas; //You need the canvas so the visitor can draw on it
	private AnimationTimer gameTimer; //Used if you want to make a game that runs at 60 frames per second
	private FileChooser	fileChooser;
	private Camera camera;
	/**
	 * Default constructor
	 */
	public GamePane() {
		super();
		
		//Create the canvas to draw on
		canvas = new GameCanvas();	
		
		//Bind the width and height so the canvas resizes with window		
		canvas.setWidth(3000);
		canvas.setHeight(700); 
		
		this.setLayoutX(1500);
		  Player player = (Player) canvas.getContainer().getGameObjects().first();
		  player.translateXProperty().addListener((obs, old,newValue)->{
			  int offset = newValue.intValue();
			  if(offset>600 && offset<3000-600) {
				  this.setLayoutX(-(offset-2100));
			  }
		  });
		 long start = System.nanoTime();
		gameTimer = new AnimationTimer() {

	    long lastUpdate = 0;
	    long timeLapsed=0;

		  @Override
	      public void handle(long now) {
			canvas.requestFocus(); //This is important so that the canvas (with all the event handlers) intercepts the Key and Mouse events
             if(now - lastUpdate>=100000) {	
            	 if(player!=null) {
        			 if(player.getVelocityX() == 0) {
        		    	 if(player.getDirection() == DIRECTION.WEST) {
        		    		 player.setCurrent(player.getObjects().get(5));
        		    	 }else {
        		    		 player.setCurrent(player.getObjects().get(4));
        		    	 }	     	 
        			 }
        			
        				
        			 if(KMBuffer.isKeyPressed(KeyCode.C)) {
        				 if(player.isOnfloor())
        				 player.setJumping(true);
        				
        				
        			 }
        			  if(KMBuffer.isKeyPressed(KeyCode.LEFT)) {
        				 
        				 player.moveLeft();
        				}
        					
        				 if(KMBuffer.isKeyPressed(KeyCode.RIGHT)) {
        					
        					player.moveRight();	

        				} 
        				 if(KMBuffer.isKeyPressed(KeyCode.DOWN)) {
        					 player.crouch();
        				 }
        				 
        		        if(KMBuffer.isKeyPressed(KeyCode.X)) {	
        		        	player.shoot();
        				}
        			
        		        
        			}
                	 player.jump(timeLapsed);
                	// setOnKeyReleased(event -> player.setJumping(false) player.setVelocityX(0) );
                     setOnKeyReleased(event -> player.release());
                    
                     if(!player.isJumping())
                    	 player.fall();
                }
        		long finish = System.nanoTime();
        		 timeLapsed = (finish -start)/1000000000;
        		
            	
            	
	             canvas.update((int) timeLapsed);
				 canvas.redrawCanvas();
				 lastUpdate =now;
                }
		
		};
		gameTimer.start();
		
		this.getChildren().add(canvas);
		
		
		
	}	
}
