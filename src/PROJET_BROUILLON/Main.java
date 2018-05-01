package PROJET_BROUILLON;

import java.io.File;
import java.io.IOException;

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

		//Lien entre le MODELE, la VUE (fxml) et le CONTROLEUR
		ModeleTest modele = new ModeleTest(ImageDirectory);
		FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("GalleryView.fxml"));
		firstLoader.setController(new ControlerMDI(modele));

		//Création de la SCENE
		Parent firstUI = firstLoader.load();
		Scene scene = new Scene(firstUI);

		//Lien entre la SCENE et le CSS
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		//Paramètres du STAGE
		stage.setTitle("BANQUE D'IMAGE - PMD");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();

		//Sérialisation des données dans le fichier images.dat
		stage.setOnHiding(event -> {
			try {
				modele.SerializeData(new File("images.dat"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

	}

	//Lancement de l'application
	public static void main(String[] args) {
		launch(args);
	}

}
