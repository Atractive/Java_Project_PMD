 package PROJET_BROUILLON;

import java.awt.Button;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {

	// ModeleTest modele;
	public static String ImageDirectory = "Images";
	public static File[] AllImages;
	
	

	public void start(Stage stage) throws Exception {

		ModeleTest modele = new ModeleTest(ImageDirectory);
		FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("GalleryView.fxml"));
		firstLoader.setController(new ControlerMDI(modele));
		Parent firstUI = firstLoader.load();

		Scene scene = new Scene(firstUI);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setTitle("FXML Welcome");
		
		//System.out.println(Arrays.toString(ControlerMDI.class.getDeclaredFields()));
	
		stage.setScene(scene);
		stage.show();
		stage.setMaximized(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
