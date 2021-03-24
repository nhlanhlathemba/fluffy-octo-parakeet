package csc2a.model;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import csc2a.designpatterns.iRenderVisitor;

public class Shot extends GameObject {	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private OBJTYPE type;
	 private AnimatedObject current; 

	public Shot(int x,int y,DIRECTION dir) {
		super(x, y,35,0);
		setType(OBJTYPE.SHOT);
		super.direction = dir;
		if(super.direction == DIRECTION.WEST) {
			super.setVelocityX(-35);
		}
		setCurrent(new AnimatedObject(OBJTYPE.SHOT,"shot",x,y));
	}
	
	@Override
	public void accept(iRenderVisitor visitor) {
		visitor.render(this);
        
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
	 * @return the current
	 */
	public AnimatedObject getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(AnimatedObject current) {
		this.current = current;
	}

}
