package PROJET_BROUILLON;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

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
			System.out.println(file.toString());
			imageView = createImageView(file.toString());
			TilePaneGalerie.getChildren().addAll(imageView);
		}

		ScrollPaneGalerie.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //
		ScrollPaneGalerie.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); //
		ScrollPaneGalerie.setFitToWidth(true);
		ScrollPaneGalerie.setContent(TilePaneGalerie);

	}

	private ImageView createImageView(String string) {
		ImageView imageView = null;
		final Image image = new Image("file:" + string, 150, 0, true, true);
		imageView = new ImageView(image);
		imageView.setFitWidth(150);
		return imageView;
	}

}