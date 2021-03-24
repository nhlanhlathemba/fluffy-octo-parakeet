package csc2a.designpatterns;


/**
 * 
 * AbstractVisitable interface for the Visitor Design Pattern
 * Used to make GameObjects renderable by the RenderGameObjectsVisitor
 *
 */
public interface iRenderable {
	public void accept(iRenderVisitor visitor);
}

