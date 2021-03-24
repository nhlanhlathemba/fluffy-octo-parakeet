package csc2a.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import csc2a.designpatterns.RenderGameObjectVisitor;
import csc2a.model.AnimatedObject;
import csc2a.model.DIRECTION;
import csc2a.model.Drone;
import csc2a.model.Enemy;
import csc2a.model.GameObject;
import csc2a.model.GameObjectContainer;
import csc2a.model.OBJTYPE;
import csc2a.model.Player;
import csc2a.model.Shot;
import csc2a.model.Slime;
import csc2a.util.KMBuffer;
import csc2a.util.Node;
import csc2a.util.SList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
/**
 * 
 * Canvas used to render all of your GameObjects using the Visitor
 * This is the Client in the Visitor Design Pattern
 * @author  nhlanhlaThemba
 *
 */
public class GameCanvas extends Canvas{
	
	//Attributes
	private RenderGameObjectVisitor visitor;
	/* TODO: Store all of your GameObjects (Using GameObjectContainers) here */
	private GameObjectContainer<GameObject> container;
	private ArrayList<AnimatedObject> effects;
	private ArrayList<Enemy> enemies;
	int counter =0;
	
	GraphicsContext gc;
	Drone drone;
	/**
	 * Default Constructor
	 */
	public GameCanvas() {
		visitor = new RenderGameObjectVisitor();
		container = new GameObjectContainer<GameObject>();
		effects = new ArrayList<>();
		enemies = new ArrayList<>();
	    container.addFirst(new Player(100,450));
	    enemies.add( new Drone(300,600));
	    enemies.add( new Drone(500,580));
	    enemies.add( new Drone(600,500));
	    enemies.add(new Slime(700, 620));
	    
		setUpEventListeners();
	
	}
	public GameObjectContainer<GameObject> getContainer(){
		return container;
	}
	
public void update(int timeLapsed) {
		 Random ran =new Random();
		 Timer time = new Timer();
	for(Enemy enemy: enemies) {
		time.schedule(new TimerTask() {

			@Override
			public void run() {
				 
					 if(enemy instanceof Drone) {
						 if(ran.nextInt()>0) {
						 ((Drone) enemy).moveLeft();
				     }else {
					   ((Drone) enemy).moveRight();
				     }
					}
					  
				
			}
			
		}, 0, 1000000000*5);
		/* if(timeLapsed-(counter*5) >= 5) {
		     if(ran.nextBoolean()) {
			   ((Drone) enemy).moveLeft();
		     }else {
			   ((Drone) enemy).moveRight();
		     }
		     counter++;
		   }*/
	}
	 System.out.println(counter); 
		
	for(Iterator<Enemy> EnemyIterator = enemies.iterator();EnemyIterator.hasNext();) {
		   Enemy enemy = EnemyIterator.next();
		  
		 // enemy.move();
		  enemy.update(timeLapsed);
		 System.out.println(timeLapsed);
		  
		   if(((Player) container.getGameObjects().first())!=null) {
			   Player player = (Player) container.getGameObjects().first();
			   if(enemy instanceof Slime) {
				   if((Math.abs(enemy.getXLocation()-player.getXLocation())<=20)) {
					   ((Slime) enemy).attack();
				   }
			   }
			   
		   for(Iterator<Shot> iterator = ((Player) container.getGameObjects().first()).getShotsFired().iterator();iterator.hasNext();) {
				Shot A = iterator.next();
				 if(enemy.isHit(new Point2D(A.getXLocation(),A.getYLocation()))) {
					 enemy.setHealth(enemy.getHealth()-enemy.getHitFactor());				 
					 effects.add(new AnimatedObject(OBJTYPE.EFFECT,"impact",A.getXLocation(),A.getYLocation()));
					
						 if(enemy.getHealth()<=0) {
							 if(enemy instanceof Drone) {
							 effects.add(new AnimatedObject(OBJTYPE.EFFECT,"enemy-death",enemy.getXLocation(),enemy.getYLocation()));
							
						 
					 }else if(enemy instanceof Slime) {
						 
							 effects.add(new AnimatedObject(OBJTYPE.EFFECT,"slime-death",enemy.getXLocation(),enemy.getYLocation()));
							 //((Slime) enemy).die();
							// if(((Slime) enemy).isDead)
							//	 EnemyIterator.remove();
							 
							
						 }
							 EnemyIterator.remove();
					 }
		
					 iterator.remove();
				 }
			}
		   }
		}
		//	drone.moveRight();
		
	//	System.out.println("drone distance: " + drone.distance);
	} 
	
	/* TODO: Set your GameObjects and redrawCanvas() */
	
	/**
	 * Method used to redraw the canvas whenever called
	 */
	public void redrawCanvas(){		
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		Image image =null;
		Image image2 =null;
		Image image3 =null;
		Image image4 =null;
		Image image5 =null;
		FileInputStream fis,fis2,fis3,fis4,fis5;
		try {
			fis = new FileInputStream(new File( "data\\background\\foreground.png"));
			fis5 = new FileInputStream(new File( "data\\props\\red.png"));
			fis2 = new FileInputStream(new File( "data\\background\\back-buildings.png"));
			fis3 = new FileInputStream(new File( "data\\background\\buildings.png"));
			fis4 = new FileInputStream(new File( "data\\background\\starry-skyMoon-background.png"));
			 image = new Image(fis, 600,400,false,false);
			 image2 = new Image(fis2, 600,500,false,false);
			 image3 = new Image(fis3, 400,400,false,false);
			 image4 = new Image(fis4, 1600,600,false,false);
			 image5 = new Image(fis5, 120,160,false,false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gc.drawImage(image4, 0, 0);
		gc.drawImage(image3, 1000, 300);
		gc.drawImage(image2, 0, 130);
		gc.drawImage(image2, 500, 130);
		gc.drawImage(image,0,300);
		gc.drawImage(image, 500, 300);
		gc.drawImage(image, 1000, 300);
		//gc.drawImage(image5, 640, 530);
		
		visitor.setGraphicsContext(gc);
		Node<GameObject> currentNode = container.getGameObjects().firstNode();
	        if(currentNode.getElement()!=null) {
			currentNode.getElement().accept(visitor);
	        }
			for(Enemy enemy: enemies) {
				enemy.render(gc);
			}
	   
	   for(Iterator<AnimatedObject> iterator = effects.iterator();iterator.hasNext();) {
		   AnimatedObject obj = iterator.next();
		   if(obj.getFrameN() >= obj.getStrip().size() -1) {
		  		iterator.remove();
		  	 }else{	   	        
		   	    	obj.setFrameN(obj.getFrameN()+1);
		  	     }
		         gc.drawImage(obj.getStrip().get(obj.getFrameN()), obj.getX(), obj.getY());

	   }
	
		
	/*	
		for(int i = 0; i< container.getGameObjects().size()-1;i++) {			
			if(currentNode!=null) {	
				currentNode.getElement().accept(visitor);
			}
			currentNode = currentNode.getNext();
		} */
		
	   //gc.translate(-camX, -camY);
	}
	
	public Player getPlayer() {
	   return (Player) container.getGameObjects().first();
	}
	
	/**
	 * Method to set that the KMBuffer is responsible for handling key and mouse events
	 * 
	 */
	private void setUpEventListeners() {
		/*
		 * Set the event listeners to handle the key press and release events in the KMBuffer
		 */
		this.setOnKeyPressed((event)    -> { KMBuffer.handleKeyPressed(event);    });
		this.setOnKeyReleased((event)   -> { KMBuffer.handleKeyReleased(event);   });
		
		/*
		 * Set the event listeners to handle the mouse events in the KMBuffer
		 */
		this.setOnMouseMoved((event)    -> { KMBuffer.handleMouseMoved(event);    });		
		this.setOnMousePressed((event)  -> { KMBuffer.handleMousePressed(event);  });
		this.setOnMouseReleased((event) -> { KMBuffer.handleMouseReleased(event); });
		this.setOnMouseDragged((event)  -> { KMBuffer.handleMouseDragged(event);  });
		this.setOnMouseEntered((event)  -> { KMBuffer.handleMouseEntered(event);  });
		this.setOnMouseExited((event)   -> { KMBuffer.handleMouseExited(event);   });
		//this.setOnMouseClicked(event -> {}); //You need to add an event handler to deal with this event in this Class
		
		/*--------------------------------------------------------------------
		 * 
		 * End of Event Handler Code
		 * 
		 *--------------------------------------------------------------------*/
	}
	/**
	 * @return the enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	/**
	 * @param enemies the enemies to set
	 */
	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
}
