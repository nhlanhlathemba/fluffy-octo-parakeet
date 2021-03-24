/**
 * 
 */
package ui;


import java.io.File;
import java.util.ArrayList;

import graphADT.Graph;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * @author 
 */
public class CanvasPane extends StackPane
{
	private Canvas			canvas;
	private GraphicsContext	gc;
	private Graph graph;
	



 
	/**
	 * 
	 */
    
	public CanvasPane()
	{
	//	super.layoutChildren();
		canvas = new Canvas(600,500);
		this.getChildren().add(canvas);
		canvas.widthProperty().bind(this.widthProperty());
		canvas.heightProperty().bind(this.heightProperty());
		gc = canvas.getGraphicsContext2D();
	
		AnimationTimer redrawTimer = new AnimationTimer()
		{

			@Override
			public void handle(long now)
			{
				CanvasPane.this.redrawCanvas();
			}
		};
		redrawTimer.start();  

	
		this.redrawCanvas();
	}


	public void redrawCanvas()
	{
	   gc.setFill(Color.BLACK);
	   if(graph!=null)
	   gc.fillText(graph.toString(), 100, 200);
	}

	

	/**
	 * @return the graph
	 */
	public Graph getGraph() {
		return graph;
	}


	/**
	 * @param graph the graph to set
	 */
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	
 
}
