import csc2a.ui.GamePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry Point for the JavaFX Application
 * @author <YOUR DETAILS HERE>
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
		/* TODO: Set up your Scene and Stage */
		GamePane gPane = new GamePane();
		Scene scene = new Scene(gPane, 800, 600);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
