import gui.ApplicationPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ApplicationPane aPane = new ApplicationPane();
		primaryStage.setTitle("Practical X");
		Scene scene = new Scene(aPane,800,700);
		
		//primaryStage.setFullScreen(true);
		//primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}

}


