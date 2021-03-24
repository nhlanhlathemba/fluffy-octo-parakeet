package csc2a.model;


import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import csc2a.designpatterns.iRenderVisitor;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;



public class Drone extends Enemy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isTurning = false;

	public Drone(double x, double y) {
		super(x, y);
		this.setDirection(DIRECTION.WEST);
		this.setType(OBJTYPE.ENEMY_DRONE);
		this.setHitFactor(30);
		this.setVelocityX(1);
		this.getActions().add(new AnimatedObject(this.getType(),"move_right",x,y));//0
		this.getActions().add(new AnimatedObject(this.getType(),"move_left",x,y));//1
		this.getActions().add(new AnimatedObject(this.getType(),"turn_right",x,y));//2
		this.getActions().add(new AnimatedObject(this.getType(),"turn_left",x,y));//3
		this.setCurrent(new AnimatedObject(this.getType(),"move_right",x,y));
		Height = this.getActions().get(0).getStrip().get(0).getHeight();
		Width = this.getActions().get(0).getStrip().get(0).getWidth();
		this.setHitZone(new Rectangle2D(x,y,Width,Height));
		System.out.println("Drone created");
	}
	
	public void update(int timeLapsed) {
		this.setHitZone(new Rectangle2D(this.getXLocation(),this.getYLocation(),Width,Height));
		if(player!=null) {
			
		}else {
			
		}
	}

	public void moveLeft() {
		//if(!isTurning) {
			if(this.getDirection().equals(DIRECTION.EAST)) {
				this.turnLeft();
			}else {
				this.setDirection(DIRECTION.WEST);
				this.setCurrent(this.getActions().get(1));
				this.setVelocityX(-1);
			//}
			super.move();
		}
		
	}
	public void turnLeft() {
		isTurning = true;
		this.setCurrent(this.getActions().get(3));
		this.setVelocityX(-1);
	
	}
	public void moveRight() {
	//	if(!isTurning) {
			if(this.getDirection().equals(DIRECTION.WEST)) {
				this.turnRight();
			}else {
				this.setDirection(DIRECTION.EAST);
				this.setCurrent(this.getActions().get(0));
				this.setVelocityX(1);
				
		//	}
			super.move();
		}
	}
	public void turnRight() {
		isTurning = true;
		this.setCurrent(this.getActions().get(2));
		this.setVelocityX(1);
	}


	/**
	 * @return the isTurning
	 */
	public boolean isTurning() {
		return isTurning;
	}

	/**
	 * @param isTurning the isTurning to set
	 */
	public void setTurning(boolean isTurning) {
		this.isTurning = isTurning;
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.render(this);

	}
	
	public void render(GraphicsContext gc) {
		   AnimatedObject obj = this.getCurrent();
		   System.out.println();
		  	 if(obj.getFrameN() >= obj.getStrip().size()-1) {  		 
		  		     if(obj.equals(this.getActions().get(2))) {
		  			     this.setTurning(false);
		  			     this.setDirection(DIRECTION.EAST);
		  			   this.setCurrent(this.getActions().get(1));
		  			   this.distance = 0;
		  	 	     }else if(obj.equals(this.getActions().get(3))) {
		  	 	    	 this.setTurning(false);
		  	 	    	this.setCurrent(this.getActions().get(0));
		  	 	    	this.setDirection(DIRECTION.WEST);
		  	 	    	this.distance = 0;
		  	 	     } 
		  		  
		  		 obj.setFrameN(0);
		  	 }else{	   	        
		   	    	obj.setFrameN(obj.getFrameN()+1);
		  	     }
		         gc.drawImage(obj.getStrip().get(obj.getFrameN()), this.getXLocation(), this.getYLocation());
			
	}

	@Override
	protected boolean impl_computeContains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object impl_processMXNode(MXNodeAlgorithm arg0, MXNodeAlgorithmContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}



}
