import csc2a.model.Player;
import csc2a.ui.GamePane;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Entry Point for the JavaFX Application
 *
 */
public class Main extends Application{
	
	/**
	 * Main entry point for the program
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Default start method provided by the JavaFX Application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GamePane gPane = new GamePane();
		primaryStage.setTitle("Demo");
		Scene scene = new Scene(gPane,1500,700);
		
		//primaryStage.setFullScreen(true);
		//primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	

}
