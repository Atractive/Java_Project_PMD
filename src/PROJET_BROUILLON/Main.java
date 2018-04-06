package PROJET_BROUILLON;

import java.io.File;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
		stage.setScene(scene);
		stage.show();
		stage.setMaximized(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
