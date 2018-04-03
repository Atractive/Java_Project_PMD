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

		Parent root = FXMLLoader.load(getClass().getResource("GalleryView.fxml"));

		ModeleTest modele = new ModeleTest(ImageDirectory);
		// ControlerMDI Controler = new ControlerMDI(modele);

		Scene scene = new Scene(root);
		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
		stage.setMaximized(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
