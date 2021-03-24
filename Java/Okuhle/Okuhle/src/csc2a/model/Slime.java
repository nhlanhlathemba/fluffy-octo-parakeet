package csc2a.model;

import java.util.Random;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import csc2a.designpatterns.iRenderVisitor;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Slime extends Enemy {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean isDead =false;
    Random ran = new Random();
	
	public Slime(int x,int y) {
	  super(x, y);
		this.setHitFactor(30);
		this.setVelocityX(1);
		this.setType(OBJTYPE.SLIME);
	    this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_EAST,"attack",x,y));//0
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_WEST,"attack",x,y));//1
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_EAST,"die",x,y));//2
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_WEST,"die",x,y));//3
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_EAST,"hurt",x,y));//4
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_WEST,"hurt",x,y));//5
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_EAST,"idle",x,y));//6
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_WEST,"idle",x,y));//7
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_EAST,"move",x,y));//8
		this.getActions().add(new AnimatedObject(OBJTYPE.SLIME_WEST,"move",x,y));//9
		 if(ran.nextBoolean()) {
			  this.setDirection(DIRECTION.EAST);
			  this.setCurrent(this.getActions().get(6));
		  }else {
			  this.setDirection(DIRECTION.WEST);
			  this.setCurrent(this.getActions().get(7));
			 
		  }
		 Height = this.getActions().get(0).getStrip().get(0).getHeight();
			Width = this.getActions().get(0).getStrip().get(0).getWidth();
		 this.setHitZone(new Rectangle2D(x,y+20,Width,Height-30));
	  System.out.println("Slime created  " + this.getDirection());
	}
	
	public void attack() {
		if(this.getDirection().equals(DIRECTION.EAST)) {
			this.setCurrent(this.getActions().get(0));
		}else if(this.getDirection().equals(DIRECTION.WEST)) {
			this.setCurrent(this.getActions().get(1));
		}
		 this.setHitZone(new Rectangle2D(this.getXLocation(),this.getYLocation(),Width,Height));
	}
	
	public void update(int timeLapsed) {
		this.setHitZone(new Rectangle2D(this.getXLocation(),this.getYLocation()+20,Width,Height-30));
		
	}
	public void die() {
		if(this.getHealth()<=0) {
			if(this.getDirection().equals(DIRECTION.EAST)) {
				this.setCurrent(this.getActions().get(2));
			}else if(this.getDirection().equals(DIRECTION.WEST)) {
				this.setCurrent(this.getActions().get(3));
			}
		}
	}
	
	public void move() {
		if(this.getDirection().equals(DIRECTION.EAST)) {
			this.setCurrent(this.getActions().get(8));
			this.setVelocityX(2);
		}else if(this.getDirection().equals(DIRECTION.WEST)) {
			this.setCurrent(this.getActions().get(9));
			this.setVelocityX(-2);
		}
		
		super.move();
	}
	public void idle() {
		this.setVelocityX(0);
		if(this.getDirection().equals(DIRECTION.EAST)) {
			this.setCurrent(this.getActions().get(6));
		}else if(this.getDirection().equals(DIRECTION.WEST)) {
			this.setCurrent(this.getActions().get(7));
		}
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		// TODO Auto-generated method stub

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

	@Override
	public void render(GraphicsContext gc) {
		 AnimatedObject obj = this.getCurrent();
	  	 if(obj.getFrameN() >= obj.getStrip().size()-1 ) {
	  		 if(obj.equals(this.getActions().get(3)) || obj.equals(this.getActions().get(3))) {
	  			isDead =true;
	  		 }else if(obj.equals(this.getActions().get(3)) || obj.equals(this.getActions().get(3))) {
	  			this.idle();
	  		 }else if(obj.equals(this.getActions().get(0)) || obj.equals(this.getActions().get(1))) {
	  			 this.idle();
	  		 }
	  		obj.setFrameN(0);
	  	 }else{	   	        
	   	    	obj.setFrameN(obj.getFrameN()+1);
	  	     }
	         gc.drawImage(obj.getStrip().get(obj.getFrameN()), this.getXLocation(), this.getYLocation());
		
	}

}
