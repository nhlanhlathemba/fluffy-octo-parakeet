package csc2a.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.javafx.geom.Dimension2D;

import csc2a.designpatterns.iRenderVisitor;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedObject {
     private double x,y;
	 private ArrayList<Image> strip;
	 private int frameN;
	 public boolean afPlayed;
	 private OBJTYPE type;
	 private String action;
	 
	 public AnimatedObject(OBJTYPE type,String action, double x,double y) {
		 this.x = x;
		 this.y = y;
		 this.action = action;
		    this.setType(type);
		   afPlayed = false;
		   frameN = 0;
		   strip = new ArrayList<Image>();
		   FileInputStream fis ;
		   int index =1;
		   
		   if(type.equals(OBJTYPE.PLAYER)) {
		   while(true) {
			   try {			 
				fis = new FileInputStream(new File( "data\\player\\" +action + "\\" + action + "-" + index +".png"));
				Image image = new Image(fis, 71*1.3,67*1.3,false,false);
				strip.add(image);
				fis.close();
				index++;
			} catch (FileNotFoundException e) {
				break;
			} catch (IOException e) {			
				e.printStackTrace();
			}
	     }
		    if(index==1) {
			   try {
					 
					fis = new FileInputStream(new File( "data\\player\\" +action + "\\" + action + ".png"));
					Image image = new Image(fis, 71*1.3,67*1.3,false,false);
					strip.add(image);
					fis.close();
					index++;
				} catch (FileNotFoundException e) {
					e.printStackTrace();					
				} catch (IOException e) {
				    e.printStackTrace();
				}
		   }
		}else if(type.equals(OBJTYPE.SHOT)) {
			 while(true) {
				   try {			 
					fis = new FileInputStream(new File( "data\\" +action + "\\" + action + "-" + index +".png"));
					Image image = new Image(fis, 10,10,false,false);
					strip.add(image);
					fis.close();
					index++;
				} catch (FileNotFoundException e) {
					break;
				} catch (IOException e) {			
					e.printStackTrace();
				}
		     }
		}else if(type.equals(OBJTYPE.ENEMY_DRONE)) {
			  while(true) {
				   try {			 
					fis = new FileInputStream(new File( "data\\enemy\\drone\\" +action + "\\" + action + "-" + index +".png"));
					Image image = new Image(fis, 50,50,false,false);
					strip.add(image);
					fis.close();
					index++;
				} catch (FileNotFoundException e) {
					break;
				} catch (IOException e) {			
					e.printStackTrace();
				}
		     }
			    if(index==1) {
				   try {
						 
						fis = new FileInputStream(new File( "data\\enemy\\drone\\" +action + "\\" + action + ".png"));
						Image image = new Image(fis, 50,50,false,false);
						strip.add(image);
						fis.close();
						index++;
					} catch (FileNotFoundException e) {
						e.printStackTrace();					
					} catch (IOException e) {
					    e.printStackTrace();
					}
			   }
		}else if(type.equals(OBJTYPE.EFFECT)) {
			int size = 10;
			if(action.equals("enemy-death") ||action.equals("enemy-explosion")) {
				size = 70;
			}
			if(action.equals("slime-death")) {
				size =60 ;
			}
			  while(true) {
				   try {			 
					fis = new FileInputStream(new File( "data\\effects\\" +action + "\\" + action + "-" + index +".png"));
					Image image = new Image(fis, size,size,false,false);
					strip.add(image);
					fis.close();
					index++;
				} catch (FileNotFoundException e) {
					break;
				} catch (IOException e) {			
					e.printStackTrace();
				}
		     }
		}else if(type.equals(OBJTYPE.SLIME_WEST)) {
			 while(true) {
				   try {			 
					fis = new FileInputStream(new File( "data\\enemy\\slime\\" +action + "\\" + action + "-" + index +".png"));
					Image image = new Image(fis, 50,50,false,false);
					strip.add(image);
					fis.close();
					index++;
				} catch (FileNotFoundException e) {
					break;
				} catch (IOException e) {			
					e.printStackTrace();
				}
		     }
		}else if(type.equals(OBJTYPE.SLIME_EAST)) {
			 while(true) {
				   try {			 
					fis = new FileInputStream(new File( "data\\enemy\\slime\\" +action + "\\" + action + "-" + index +".png"));
					Image image = new Image(fis, 50,50,false,false);
					ImageView view = new ImageView(image);
	                view.setLayoutY(1);
					strip.add(view.getImage());
					fis.close();
					index++;
				} catch (FileNotFoundException e) {
					break;
				} catch (IOException e) {			
					e.printStackTrace();
				}
		     }

		}
		   
		   
	 }
	
	/**
	 * @return the frameN
	 */
	public int getFrameN() {
		return frameN;
	}

	/**
	 * @param frameN the frameN to set
	 */
	public void setFrameN(int frameN) {
		this.frameN = frameN;
	}
	
	public ArrayList<Image> getStrip(){
		return strip;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the type
	 */
	public OBJTYPE getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(OBJTYPE type) {
		this.type = type;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

}
