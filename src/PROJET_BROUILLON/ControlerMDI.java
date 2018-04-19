package PROJET_BROUILLON;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import Modele.ImageBI;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlerMDI {

	public ModeleTest modele;

	@FXML
	private AnchorPane AnchorP;
	@FXML
	private SplitPane SplitP;
	@FXML
	private AnchorPane SplitAnchor1;
	@FXML
	private ButtonBar ButtonB;
	@FXML
	private MenuButton MenuB1;
	@FXML
	private MenuButton MenuB2;
	@FXML
	private MenuButton MenuB3;
	@FXML
	private MenuButton MenuB4;
	@FXML
	private MenuButton MenuB5;
	@FXML
	private Button Button6;
	@FXML
	private TabPane TabP;
	@FXML
	private Tab TabGalerie;
	@FXML
	private AnchorPane AnchorPaneGalerie;
	@FXML
	private ScrollPane ScrollPaneGalerie;
	@FXML
	private TilePane TilePaneGalerie;
	@FXML
	private Tab ImageComplete;
	@FXML
	private AnchorPane AnchorPaneImgComplete;
	@FXML
	private SplitPane SplitPaneImgComplete;
	@FXML
	private AnchorPane VHaut;
	@FXML
	private AnchorPane VBas;

	public ControlerMDI(ModeleTest modele) {
		this.modele = modele;
	}

	@FXML
	public void initialize() {
		InjectImages();
		System.out.println("SOS");
	}

	private void InjectImages() {

		TilePaneGalerie.setPadding(new Insets(15, 15, 15, 15));
		TilePaneGalerie.setHgap(10);

		File folder = new File("Images");
		File[] listOfFiles = folder.listFiles();


		for (final File file : listOfFiles) {
			ImageView imageView;
			imageView = createImageView(file.toString());
			imageView.setOnMouseClicked(new EventHandler<MouseEvent>()
	        {
	            @Override
	            public void handle(MouseEvent t) { //Au clic, changement de tab et affichage de l'image
	            	TabP.getSelectionModel().selectNext(); //Change de tab
	            	//SplitPaneImgComplete.getItems().clear();

	                ImageView imageView2 = null;
	        		final ImageBI img = new ImageBI(file.toString());
	        		Image temp = new Image("file:"+img.nom);
	        		imageView2 = new ImageView(temp);
	        		VHaut.getChildren().clear();
	                VHaut.getChildren().add(imageView2);

	                //Affichage des Metadonnées

	                Label META = new Label("Nom : " + img.nom + "\n"
	                		+ "Mots-Clefs : " + img.mots_clefs.toString() + "\n"
	                		+ "Taille : " + temp.getWidth() + "x" + temp.getHeight() + "\n"
	                		+ "Poids : " + temp.getWidth()*temp.getHeight()*4 + "\n"
	                		+ "Favoris : " + img.favoris + "\n"
	                		+ "Nombre d'étoile : " + img.etoile + "\n"
	                		+ "Couleur Dominante : " + img.couleur);

	                VBas.getChildren().clear();
	                VBas.getChildren().addAll(META);

	                SplitPaneImgComplete.getItems().clear();
	                SplitPaneImgComplete.getItems().add(VHaut);
	                SplitPaneImgComplete.getItems().add(VBas);
	                AnchorPaneImgComplete.getChildren();

	            }
	        });
			VBox vbox = new VBox();

			String nom = new String(file.toString().split("\\\\")[1].split("\\.")[0]);
			if (nom.length()>15){nom=nom.substring(0,15)+"..."+file.toString().split("\\\\")[1].split("\\.")[1];}
			else{nom=nom+"."+file.toString().split("\\\\")[1].split("\\.")[1];}

			Label label1 = new Label(nom);

		    vbox.setSpacing(10);
		    vbox.getChildren().addAll(imageView,label1);
		    vbox.setAlignment(Pos.CENTER);
		    vbox.getStyleClass().add("vbox");
			TilePaneGalerie.getChildren().addAll(vbox);
		}

		ScrollPaneGalerie.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		ScrollPaneGalerie.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		ScrollPaneGalerie.setFitToWidth(true);
		ScrollPaneGalerie.setContent(TilePaneGalerie);

	}

	private ImageView createImageView(String nom) {
		ImageView imageView = null;
		final ImageBI img = new ImageBI(nom);
		Image temp = new Image("file:"+img.nom,150,0,true,true);
		imageView = new ImageView(temp);
		imageView.setFitWidth(150);
		imageView.getStyleClass().add("image");
		return imageView;
	}

}