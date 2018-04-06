package PROJET_BROUILLON;

import java.io.File;

import Modele.ImageBI;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

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
		TilePaneGalerie.setHgap(15);

		File folder = new File("Images");
		File[] listOfFiles = folder.listFiles();


		for (final File file : listOfFiles) {
			ImageView imageView;
			imageView = createImageView(file.toString());

			VBox vbox = new VBox();
			Label label1 = new Label(file.toString());
		    //label1.setGraphic(imageView);
		    vbox.setSpacing(10);
		    vbox.getChildren().addAll(imageView,label1);

			TilePaneGalerie.getChildren().addAll(vbox);

		}

		//ScrollPaneGalerie.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //
		//ScrollPaneGalerie.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); //
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