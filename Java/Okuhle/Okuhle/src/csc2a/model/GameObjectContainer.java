package csc2a.model;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.Iterator;

import csc2a.util.Node;
import csc2a.util.SList;
import javafx.geometry.Point2D;

/**
 * 
 * A generic GameObjectContainer container used to store all GameObjects in one data structure
 * (For example create a GameObjectContainer to store all of your Enemies or Powerup Items, provided Enemy and Powerup extend GameObject)
 *
 */
public class GameObjectContainer<T extends GameObject> implements Iterable<T>{
	
	//Attributes
  //  ArrayList<T> gameObjects ;
   private SList<GameObject> gameObjects;
   
	
	/**
	 * Default constructor
	 */
	public GameObjectContainer(){
		gameObjects = new SList<GameObject>();
	}
	
	
	public void addFirst(T obj) {
		gameObjects.addFirst(obj);
	}
	
	public SList<GameObject> getGameObjects(){
		return gameObjects;
	}
	
	/**
	 * Method to add a GameObject to the container
	 * @param go the GameObject to be added
	 */
	public void addGameObject(T go) {
		gameObjects.addLast(go);
	}
	
	
	/**
	 * Method to remove a particular GameObject from the container by reference
	 * @param go The GameObject reference to be removed 
	 */
	public void removeGameObject(T go) {
		
	}
	
	/**
	 * Method to remove all GameObjects from the container
	 * (eg. for use when moving to a different level)
	 */
	public void clearGameObjects() {
		
	}
	
	/**
	 * Method to get the number of GameObjects in the container
	 * @return The integer number of GameObjects in the container
	 */
	public int getSize() {
		return gameObjects.size();
	}

	
	/**
	 * Utility method to return any GameObject at a given location (if there is one)
	 * @param location A Point2D location
	 * @return null or a GameObject at the specified location
	 */
/*	public GameObject getGameObjectAtLocation(Point2D location) {
		GameObject temp = null;
		for(GameObject g: gameObjects) {
			Point2D goLocation = g.getLocation();
			if((goLocation.getX() == location.getX()) && (goLocation.getY() == location.getY())) {
				temp = g;
			}
		}
		return temp;
	}

	/**
	 * Method from the Iterable interface that allows iteration through the generic data structure
	 * (i.e. you can use the GameObjectContainer in for each loops)
	 * @return Returns an iterator for iteration through the container
	 */
	@Override
	public Iterator<T> iterator() {
		return null;
	}
	

}
