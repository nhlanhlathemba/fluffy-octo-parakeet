
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.AppPane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("");
		AppPane root = new AppPane();
		primaryStage.setScene(new Scene(root, 900, 600));
		// primaryStage.setMaximized(true);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
