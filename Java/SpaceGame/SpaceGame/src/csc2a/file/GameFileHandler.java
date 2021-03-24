package csc2a.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

/**
 * 
 * Class to handle all interactions with files
 * @author <YOUR DETAILS HERE>
 *
 */
public class GameFileHandler {
	/* TODO: Methods to handle text files */
	/* TODO: Methods to handle binary files */
	public static Image readImage(String path) throws FileNotFoundException {
		
			return (new Image(new FileInputStream(new File(path))));
		
	}
}
