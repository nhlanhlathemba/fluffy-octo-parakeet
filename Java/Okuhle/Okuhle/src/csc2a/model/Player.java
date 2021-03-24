package csc2a.model;

import java.util.ArrayList;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import csc2a.designpatterns.iRenderVisitor;
import csc2a.util.KMBuffer;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Player extends GameObject {
    private OBJTYPE type;
	private AnimatedObject current;
	private AnimatedObject prev = null;
	private ArrayList<AnimatedObject> actions;
	private ArrayList<Shot> Shots;
	private ArrayList<Shot> ShotsFired;
	private double Height;
	private double Width;
	private int floorHeight = 565;
	private final int JS = 10;
	private float jumpStrength = 10, weight =1;
	private int NumShots =10; 
	private static int playerSpeed =20;
	private boolean isJumping=false;
	long start;

	public Player(double x, double y) {
		super(x,y,0,0);
		type = OBJTYPE.PLAYER;
		super.direction = DIRECTION.EAST;
		Shots = new ArrayList<Shot>(NumShots);
		ShotsFired = new ArrayList<Shot>();
		actions = new  ArrayList<AnimatedObject>();
		
		/**DO NOT CHANGE THE ORDER OF THE ANIMATED OBJECTS 
		 * THE PROGRAM IS ADAPTED IN THE ORDER OF THE OBJECTS
		 * */
		actions.add(new AnimatedObject(this.getType(),"crouch", x,y)); //0
		actions.add(new AnimatedObject(this.getType(),"crouchL", x,y));//1
		actions.add(new AnimatedObject(this.getType(),"hurt", x,y));//2
		actions.add(new AnimatedObject(this.getType(),"hurtL", x,y));//3
		actions.add(new AnimatedObject(this.getType(),"idle", x,y));//4
		actions.add(new AnimatedObject(this.getType(),"idleL", x,y));//5
		actions.add(new AnimatedObject(this.getType(),"jump", x,y));//6
		actions.add(new AnimatedObject(this.getType(),"jumpL", x,y));//7
		actions.add(new AnimatedObject(this.getType(),"run", x,y));//8
		actions.add(new AnimatedObject(this.getType(),"runL", x,y));//9
		actions.add(new AnimatedObject(this.getType(),"run-shoot", x,y));//10
		actions.add(new AnimatedObject(this.getType(),"run-shootL", x,y));//11
		actions.add(new AnimatedObject(this.getType(),"shoot", x,y));	//12
		actions.add(new AnimatedObject(this.getType(),"shootL", x,y));//13
		actions.add(new AnimatedObject(this.getType(),"back-jump",x,y));//14
		actions.add(new AnimatedObject(this.getType(),"climb",x,y));//15
		current = actions.get(4);
		Height = actions.get(0).getStrip().get(0).getHeight();
		Width = actions.get(0).getStrip().get(0).getWidth();
	    System.out.println("Height: " + Height + "Width: " + Width);
		
	}
	
	public void jump(long timeLapsed) {
		
	if(this.isJumping) {
		 start = timeLapsed;
        if(this.getDirection().equals(DIRECTION.EAST))
		this.setCurrent(this.getObjects().get(6));
        else
        	this.setCurrent(this.getObjects().get(7));
        if(jumpStrength!=0)
		 this.setLocation((int) this.getLocation().getX(), (int)this.getLocation().getY()-(int)jumpStrength);
		 if(!this.isOnfloor())
		  jumpStrength -= weight; 
		 if(jumpStrength<=0)
			 isJumping = false;
	}else		
		jumpStrength = JS;
	}
	
	public void release() {
		this.setJumping(false);
		this.setVelocityX(0);
		jumpStrength = JS;
		isJumping = false;
	}
	
	public void backJump() {
		this.setCurrent(this.getObjects().get(14));
	}
	
	public void climb() {
		this.setCurrent(this.getObjects().get(15));
	}
	
	
	
	public void crouch() {
	 if(isOnfloor()) {
		switch(this.getDirection()) {
		case EAST:
		{
			this.setCurrent(this.getObjects().get(0));
		}
			break;
		case WEST:
		{
			this.setCurrent(this.getObjects().get(1));
		}
			break;
		default:
			break;
			
		}
	  }
	}
	public void moveLeft() {
		if(isOnfloor()) {
			this.setDirection(DIRECTION.WEST);
			this.setCurrent(this.getObjects().get(9));
		}
		this.setVelocityX(-playerSpeed);
		super.move();
		System.out.println("distance: " + this.distance);
	}
	
	   public void moveRight(){	
		 if(isOnfloor()) {
		   this.setDirection(DIRECTION.EAST);
		   this.setCurrent(this.getObjects().get(8));
		 }
		   this.setVelocityX(playerSpeed);
		   super.move();
		 System.out.println("distance: " + this.distance);
	}
	
	public void shoot() {	
	  if(isOnfloor()) {
		 if(this.current.equals(actions.get(4)) || this.current.equals(actions.get(5))) { // when player is idle
			 switch(this.getDirection()) {
			 case EAST:
			 {
				    this.current = actions.get(12);
					Shot shot  = new Shot((int)this.getXLocation()+37,(int) this.getYLocation()+30,this.getDirection());
					ShotsFired.add(shot);
			 }
			 break;
			 case WEST:
			 {
				    this.current = actions.get(13);
					Shot shot2  = new Shot((int)this.getXLocation() + 45 ,(int) this.getYLocation()+30,this.getDirection());
					ShotsFired.add(shot2);
			 }
			 break;
			default:
				break;
				 
			 }			
		 }else if (this.current.equals(actions.get(8)) || this.current.equals(actions.get(9))) {//when player is running
			 switch(this.getDirection()) {
			 case EAST:
			 {
				 this.current = actions.get(10);
				 Shot shot  = new Shot((int)this.getXLocation()+50,(int) this.getYLocation()+35,this.getDirection());
				 ShotsFired.add(shot);
				 System.out.println("EAST shot running");
			 }
			 break;
			 case WEST:
			 {
				 this.current = actions.get(11);
				 Shot shot2  = new Shot((int)this.getXLocation()+35,(int) this.getYLocation()+35,this.getDirection());
				 ShotsFired.add(shot2);
				 System.out.println("WEST shot running");
			 }
			break;	 
			default:
				break;
			 }
		 }else if(this.current.equals(actions.get(0)) || (this.current.equals(actions.get(1)))) { //When player is crouching
			 switch(this.getDirection()) {
			 case EAST:
			 {
				    this.current = actions.get(0);
				    Shot shot  = new Shot((int)this.getXLocation()+35,(int) this.getYLocation()+53,this.getDirection());
					ShotsFired.add(shot);
					System.out.println("EAST shot crouch");
			 }
				 break;
			 case WEST:
			 {
				 this.current = actions.get(1);
				    Shot shot  = new Shot((int)this.getXLocation()+45,(int) this.getYLocation()+53,this.getDirection());
					ShotsFired.add(shot);
					System.out.println("WEST shot crouch");
			 }
				 break;
				 default:
					 break;
					 
			 }
		 }
	  }else {
		  switch(this.getDirection()) {
			 case EAST:{
				 this.current = actions.get(0);
				    Shot shot  = new Shot((int)this.getXLocation()+35,(int) this.getYLocation()+53,this.getDirection());
					ShotsFired.add(shot);
			 }break;
			 case WEST:
			 {
				    this.current = actions.get(1);
				    Shot shot  = new Shot((int)this.getXLocation()+45,(int) this.getYLocation()+53,this.getDirection());
					ShotsFired.add(shot);
					System.out.println("WEST shot crouch");
				 
			 }break;
			 default:
				 break;
		  }
	  }
	}
	
	public ArrayList<Shot> getShotsFired(){
		return ShotsFired;
	}
	
	public void idle() {
		this.setVelocityX(0);
		this.setVelocityY(0);
		if(this.getDirection().equals(DIRECTION.EAST))
		this.setCurrent(this.actions.get(4));
		else
			this.setCurrent(this.actions.get(5));
	}
	
	public boolean isOnfloor() {
		return this.getLocation().getY() > floorHeight;
	}
	public void fall() {
		if(!isJumping) {
			super.fall();
			//isJumping = false;
		}
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.render(this);

	}

	public ArrayList<AnimatedObject> getObjects(){
		return actions;
	}
	
	/**
	 * @return the current
	 */
	public AnimatedObject getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(AnimatedObject current) {
		prev =this.current;
		this.current = current;
	}

	/**
	 * @return the prev
	 */
	public AnimatedObject getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(AnimatedObject prev) {
		this.prev = prev;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return Height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		Height = height;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return Width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		Width = width;
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

	/**
	 * @return the isJumping
	 */
	public boolean isJumping() {
		return isJumping;
	}

	/**
	 * @param isJumping the isJumping to set
	 */
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
