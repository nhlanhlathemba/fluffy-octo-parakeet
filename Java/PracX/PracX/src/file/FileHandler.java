package file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class FileHandler {
	
    public static File saveProgress(Image image) throws IOException {
    	File outputFile = new File("data//save");
    	BufferedImage bImage=SwingFXUtils.fromFXImage(image, null);
    	ImageIO.write(bImage, "jpg", outputFile);
    	return outputFile;
    }
    
    public static void saveFile(Image image) throws IOException {
    	FileChooser stegoImageSaver = new FileChooser();
    	File f1 = stegoImageSaver.showSaveDialog(null);
    	if (f1 != null) {
    	    String name = f1.getName();
    	    String extension = name.substring(1+name.lastIndexOf(".")).toLowerCase();
    	    BufferedImage bImage=SwingFXUtils.fromFXImage(image, null);
    	    ImageIO.write(bImage, extension, f1);
    	}
    }
}
