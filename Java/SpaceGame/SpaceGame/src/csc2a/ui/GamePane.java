package csc2a.ui;



import csc2a.model.Enemy;
import csc2a.model.GameObject;
import csc2a.model.SpaceShip;
import csc2a.model.Velocity;
import csc2a.util.KMBuffer;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * 
 * GamePane provides a custom container to manage all game interactions
 * and host the GameCanvas
 *
 */
public class GamePane extends StackPane{
	
	//Attributes
	private GameCanvas canvas; //You need the canvas so the visitor can draw on it
	private AnimationTimer gameTimer; //Used if you want to make a game that runs at 60 frames per second
	
	
	
	/**
	 * Default constructor
	 */
	public GamePane() {
		super();
		
		//Create the canvas to draw on
		canvas = new GameCanvas();	
		//Bind the width and height so the canvas resizes with window		
		canvas.widthProperty().bind(this.widthProperty());
		canvas.heightProperty().bind(this.heightProperty());
		
		
		/*
		 * Animation Timer 
		 * 
		 * Animation timer is only needed if you want to have a game that runs at a set frame rate (~60fps) 
		 * 
		 * You can safely remove the ApplicationTimer if you would prefer to rather implement your own 
		 * event handlers to drive your game logic (then setup your event handlers for events such as: 
		 * 			this.setOnKeyPressed();
		 * 			this.setOnKeyReleased();		
		 * 			this.setOnMouseMoved();		
		 * 			this.setOnMousePressed();
		 * 			this.setOnMouseReleased();
		 * 			this.setOnMouseEntered();
		 * 			this.setOnMouseExited();
		 * 			this.setOnMouseDragged();  )
		 * 
		 (i.e. This object V V V ) */
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				canvas.requestFocus(); //This is important so that the canvas (with all the event handlers) intercepts the Key and Mouse events
				
				/*
				 * Do your game logic here
				 */
				
				/* 
				 * HINT: Look up AnimationTimer
				 * See: https://docs.oracle.com/javase/8/javafx/api/javafx/animation/AnimationTimer.html
				 * it provides a handle method to perform operations 
				 * roughly 60 times per second (@ 60fps)
				 * 
				 * 
				 * Note: if you use the Event Handler Code from the KMBuffer to test for events such as:
				 * 
				 * 	Key pressed: 
				 * 		KMBuffer.isKeyPressed(KeyCode.UP); //.UP is for the Up Arrow Key
				 * 		For more see: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html */
				 System.out.println("Up arrow is pressed: " + KMBuffer.isKeyPressed(KeyCode.UP)); 
				 
				/*  Mouse in window: 
				 *  	KMBuffer.isMouseInWindow(); */
				 System.out.println("Mouse is in window: " + KMBuffer.isMouseInWindow());   
				
				/*  Mouse location: 
				 *  	KMBuffer.getMouseNodeLocation(); //OR .getMouseSceneLocation() OR .getMouseScreenLocation() (but you shouldn't really need this one) */
				 System.out.println("Mouse location relative to canvas: (" + KMBuffer.getMouseNodeLocation().getX() + "," + KMBuffer.getMouseNodeLocation().getY() + ")");   
				
				 /*  Mouse button pressed:
				 *  	KMBuffer.isLeftMousePressed();
				 *  	KMBuffer.isRightMousePressed();
				 *  	KMBuffer.isMiddleMousePressed(); */
		
				 if(KMBuffer.isKeyPressed(KeyCode.UP)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(0,-8));
				 }if(KMBuffer.isKeyPressed(KeyCode.DOWN)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(0,8));
				 }if(KMBuffer.isKeyPressed(KeyCode.LEFT)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(-4,0));
				 }if(KMBuffer.isKeyPressed(KeyCode.RIGHT)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(8,0));
				 }if(KMBuffer.isKeyPressed(KeyCode.RIGHT) && KMBuffer.isKeyPressed(KeyCode.UP)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(8,-8));
				 }if(KMBuffer.isKeyPressed(KeyCode.LEFT) && KMBuffer.isKeyPressed(KeyCode.UP)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(-4,-4));
				 }if(KMBuffer.isKeyPressed(KeyCode.RIGHT) && KMBuffer.isKeyPressed(KeyCode.DOWN)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(8,8));
				 }if(KMBuffer.isKeyPressed(KeyCode.LEFT) && KMBuffer.isKeyPressed(KeyCode.DOWN)) {
					 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(-4,4));
				 }if(KMBuffer.isKeyPressed(KeyCode.X)) {
					 
					// ((SpaceShip) canvas.getContainer().getObjects().get(0)).shoot();
				 }
				 Duration firingInterval = Duration.millis(500);
			     Timeline firing = new Timeline(
			             new KeyFrame(Duration.ZERO, event ->
			             ((SpaceShip) canvas.getContainer().getObjects().get(0)).shoot()),
			             new KeyFrame(firingInterval));
			     firing.setCycleCount(1);
		       
			  
			        
			        
				 setOnKeyPressed(event -> {
			            if (event.getCode() == KeyCode.X && firing.getStatus() != Animation.Status.RUNNING) {
			                firing.playFromStart();
			            }
			      });
				 
				 setOnKeyReleased(event -> {
				  if (event.getCode() == KeyCode.X) {
		                firing.stop();
		            }
				 canvas.getContainer().getObjects().get(0).setVelocity(new Velocity(0, 0));});
				
				
				canvas.getContainer().moveObjects();
				canvas.getBg().move();
				
				 canvas.redrawCanvas();
			}
		};
		gameTimer.start();
		
		this.getChildren().add(canvas);
		
		
	}	
}
