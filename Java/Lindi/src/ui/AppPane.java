package ui;

import java.io.File;
import java.util.Optional;

import graphADT.Graph;
import graphADT.Vertex;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AppPane extends StackPane {
   CanvasPane canvas;
   Graph graph;
   FileChooser fileChooser;
   
   public AppPane() {
	  
	   BorderPane tpane = new BorderPane();
  // 	this.getChildren().add(tpane);
	   VBox controls = new VBox();
		Button addNode = new Button("New Location");
		addNode.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setContentText("Please insert name of loaction");
				Optional<String> result = dialog.showAndWait();
				result.ifPresent(name->graph.addVertex(new Vertex(name, 0,0)) );
			//	graph.addVertex(new Vertex("Dobsie", 0,0));
				
			}
			
		});
		Button reNode = new Button("remove");
		controls.getChildren().addAll(addNode, reNode);
      
   	fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("docs"));
		
		 MenuBar menubar= new MenuBar();
		 Menu menu = new Menu("_File");
		 MenuItem fileItem = new MenuItem("_open");
		 fileItem.setAccelerator(KeyCombination.keyCombination("shortcut+o"));
		 menubar.getMenus().add(menu);
		 menu.getItems().add(fileItem);
		 Pane pane = new Pane(); 
		 pane.getChildren().add(menubar);
		 
		 
		
		 
		
		 fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File("data"));
		
			fileItem.setOnAction(e->{
			 File chosenFile = fileChooser.showOpenDialog(this.getScene().getWindow());
			 if(chosenFile!=null) {			 
				
			 }
		});
		 
		
	   
	   graph = new Graph();
	   canvas = new CanvasPane();
	   canvas.setGraph(graph);
	   
	   tpane.setTop(pane);
	   tpane.setLeft(controls);
	   tpane.setCenter(canvas);
	   getChildren().add(tpane);
	   
	   
	
   }
}
