package Vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Interface extends Application {

	public static void main(String[] args) {
		Application.launch(Interface.class,args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Group root = new Group();	//Groupe principal (root)
		Scene scene = new Scene(root,1150,650,Color.BEIGE);	//Scène utilisée

		//Zone pour les recherches
		Group rech = new Group();
		Rectangle TestRech = new Rectangle(10,10,1130,100);
		rech.getChildren().add(TestRech);

		//Zone pour la navigation
		Group nav = new Group();
		Rectangle TestNav = new Rectangle(10,120,100,520);
		nav.getChildren().add(TestNav);

		//Zone pour l'affichage des images
		Group aff = new Group();
		Rectangle TestAff = new Rectangle(120,120,1020,410);
		aff.getChildren().add(TestAff);

		//Zone pour gérer les metadonnées
		Group meta = new Group();
		Rectangle TestMeta = new Rectangle(120,540,1020,100);
		meta.getChildren().add(TestMeta);

		//Ajout des groupes dans root
		root.getChildren().add(rech);
		root.getChildren().add(nav);
		root.getChildren().add(aff);
		root.getChildren().add(meta);

		//Configuration du Stage
		primaryStage.setTitle("Projet PMD");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
