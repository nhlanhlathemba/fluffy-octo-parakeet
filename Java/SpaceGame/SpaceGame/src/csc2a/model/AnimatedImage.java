package csc2a.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import csc2a.file.GameFileHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimatedImage {

	 private ArrayList<Image> images;
	 private int frame;
	 private Point2D point;
	 
	 public AnimatedImage(String folder, double x, double y) {
		 frame = 0;
		point = new Point2D(x,y);
		   images = new ArrayList<Image>();
		   int index =1;
		   while(true) {	
			   try {
				Image image =GameFileHandler.readImage("data\\" +folder + "\\" + folder  + index +".png");
				images.add(image);
				index++;
			} catch (FileNotFoundException e) {
				break;
			}
	   }
		   if(index==1) {
			   try {
					Image image =GameFileHandler.readImage( "data\\" +folder + "\\" + folder + ".png");
					images.add(image);
					index++;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					
				}
		   }
	 }
	 
	 public void drawAnimation(GraphicsContext gc, double x, double y) {
		
	 
	   
	    	  if(frame >= images.size()-1) {
	   		   frame = 0;
	   	       }else {
	   	    //x = frameN * WIDTH
	    	 
	    	 frame++;
	    	 
	    	 }
	   
	    gc.drawImage(images.get(frame), x-42,y-18);
	 }
	
	/**
	 * @return the frame
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * @param frameN the frameN to set
	 */
	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public ArrayList<Image> getImages(){
		return images;
	}

}

