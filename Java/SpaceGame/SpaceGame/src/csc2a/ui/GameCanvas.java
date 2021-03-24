package csc2a.ui;

import java.io.FileNotFoundException;

import csc2a.designpatterns.RenderGameObjectVisitor;
import csc2a.file.GameFileHandler;
import csc2a.model.Background;
import csc2a.model.Enemy;
import csc2a.model.GameObject;
import csc2a.model.GameObjectContainer;
import csc2a.model.SpaceShip;
import csc2a.model.Velocity;
import csc2a.util.KMBuffer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * 
 * Canvas used to render all of your GameObjects using the Visitor
 * This is the Client in the Visitor Design Pattern
 *
 */
public class GameCanvas extends Canvas{
	
	//Attributes
	RenderGameObjectVisitor visitor = new RenderGameObjectVisitor();
	/* TODO: Store all of your GameObjects (Using GameObjectContainers) here */
	private GameObjectContainer<GameObject> Container;
	GraphicsContext gc;
	private Background bg;
	/**
	 * Default Constructor
	 */
	public GameCanvas() {
		visitor = new RenderGameObjectVisitor();
		Container = new GameObjectContainer<GameObject>();
		Container.addGameObject(new SpaceShip("data\\v-red.png", 200,200));
		bg = new Background(new Velocity(-0.05,0));
		Container.addGameObject(new Enemy((SpaceShip) Container.getObjects().get(0),400,400));
		
		setUpEventListeners();
		
	}
	

	
	/**
	 * Method used to redraw the canvas whenever called
	 */
	public void redrawCanvas(){
		gc = this.getGraphicsContext2D();
		bg.draw(gc);
		visitor.setGraphicsContext(gc);
		for(GameObject obj :Container.getObjects()) {
			 obj.accept(visitor);
		}
	}
	
	/**
	 * Method to set that the KMBuffer is responsible for handling key and mouse events
	 * (Use the KMBuffer's static methods in your GamePane to check for key and mouse events)
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
	 * @return the container
	 */
	public GameObjectContainer<GameObject> getContainer() {
		return Container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(GameObjectContainer<GameObject> container) {
		Container = container;
	}

	/**
	 * @return the bg
	 */
	public Background getBg() {
		return bg;
	}

	/**
	 * @param bg the bg to set
	 */
	public void setBg(Background bg) {
		this.bg = bg;
	}
}
