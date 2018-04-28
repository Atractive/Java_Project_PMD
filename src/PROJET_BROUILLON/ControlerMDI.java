package PROJET_BROUILLON;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Autre.CouleurDominante;
import Modele.ImageBI;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControlerMDI {

	@FXML
	public ModeleTest modele;

	public int test = 5;
	
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
	private Button Button7;
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
	private SplitPane SplitPaneImgComplete;
	//
	@FXML
	private TextField Snom;
	@FXML
	private TextField Staille;
	@FXML
	private TextField Spoids;
	@FXML
	private TextField Stags;
	@FXML
	private RadioButton Sfavoris;
	@FXML
	private ChoiceBox<Integer> Snote;
	@FXML
	private TextField Scolors;
	@FXML
	private TextField Sopen;
	@FXML
	private AnchorPane LeftImgComplete;
	@FXML
	private AnchorPane RightImgComplete;
	@FXML
	private ImageView ImageViewImgComplete;

	public ControlerMDI(ModeleTest modele) {
		this.modele = modele;
		this.test = 5;
	}

	@FXML
	public void initialize() {
		this.test = 5;
		Snote.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		ajouter_image();
		supprimer_image();
		InjectImages();
		System.out.println("SOS");
	}

	private void ajouter_image() {
		Button6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent e) {
				FileChooser filechooser = new FileChooser();
				filechooser.getExtensionFilters()
						.addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*jpeg"));

				Stage newWindow = new Stage();

				newWindow.setTitle("Second Stage");

				// Specifies the modality for new window.
				newWindow.initModality(Modality.WINDOW_MODAL);

				File list = filechooser.showOpenDialog(newWindow);
				if (list != null) {

					try {
						Files.move(FileSystems.getDefault().getPath(list.getPath()),
								FileSystems.getDefault().getPath(new File("Images/" + list.getName()).getPath()),
								StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TilePaneGalerie.getChildren().clear();
					InjectImages();
				}
			}
		});
	}

	private void supprimer_image() {
		Button7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent e) {
				FileChooser filechooser = new FileChooser();
				filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

				filechooser.setInitialDirectory(new File("Images"));

				Stage newWindow = new Stage();

				newWindow.setTitle("Second Stage");

				// Specifies the modality for new window.
				newWindow.initModality(Modality.WINDOW_MODAL);

				File list = filechooser.showOpenDialog(newWindow);

				if (list != null) {
					try {
						Files.delete(FileSystems.getDefault().getPath(new File("Images/" + list.getName()).getPath()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TilePaneGalerie.getChildren().clear();
					InjectImages();
				}
			}
		});
	}

	private void InjectImages() {

		TilePaneGalerie.setPadding(new Insets(15, 15, 15, 15));
		TilePaneGalerie.setHgap(10);

		ArrayList<ImageBI> LimagesC = this.modele.Limages;
		
		for (int i = 0; i < LimagesC.size(); i++) {
			ImageView imageView;
			// System.out.println("controler " + file.toString());
			imageView = createImageView(LimagesC.get(i).path);
			imageView.setId(String.valueOf(i));
			imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) { // Au clic, changement de tab et affichage de l'image
					System.out.println();
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						if (event.getClickCount() == 2) {
							TabP.getSelectionModel().selectNext(); // Change de tab
							String source2 = event.getPickResult().getIntersectedNode().getId();
							System.out.println("Just the id: " + source2);
							System.out.println(" " + source2);
							ImageView temp = new ImageView();
							 final ImageBI img = new ImageBI(LimagesC.get(Integer.parseInt(source2)).path.toString());
							 Image tempI = new Image("file:" + img.path);
							 BorderPane borderPane = new BorderPane();
							 temp.setImage(tempI);
							 temp.setStyle("-fx-background-color: BLACK");
							 temp.setFitHeight(LeftImgComplete.getHeight());
							 temp.setPreserveRatio(true);
							 temp.setSmooth(true);
							 temp.setCache(true);
							 borderPane.setCenter(temp);
							 borderPane.setStyle("-fx-background-color: BLACK");
							 LeftImgComplete.getChildren().add(borderPane);
							 LeftImgComplete.getChildren().clear();
							 LeftImgComplete.getChildren().add(temp);
							
							 Snom.textProperty().setValue(img.nom);
							 Staille.textProperty().setValue(
							 (int) Math.round(tempI.getWidth()) + " x " + (int)
							 Math.round(tempI.getHeight()));
							 Spoids.textProperty()
							 .setValue(String.valueOf(Math.round(tempI.getWidth() * tempI.getHeight() *
							 4)));
							 Stags.textProperty().setValue(img.mots_clefs.toString());
							 SplitPaneImgComplete.setDividerPositions(0.8f, 0.2f);
							 Snote.setValue(img.etoile);
							 img.Increase_nbOuverture();
							 Sopen.setText(String.valueOf(img.nb_ouverture));
							 Scolors.setText(CouleurDominante.getDomintanteColor(img.path));
							
							 Snom.setEditable(false);
							 Staille.setEditable(false);
							 Spoids.setEditable(false);
							 Scolors.setEditable(false);

						}
					}
				}
			});

			// Favoris.setOnMouseClicked(new EventHandler<MouseEvent>(){
			//
			// @Override
			// public void handle(MouseEvent arg0) {
			// final ImageBI img = new ImageBI(file.toString());
			// img.Set_Favoris();
			// }
			//
			//
			// });

			// Notes.setOnMouseClicked(new EventHandler<MouseEvent>(){
			//
			// @Override
			// public void handle(MouseEvent event) {
			// final ImageBI img = new ImageBI(file.toString());
			// img.Set_Etoile(Notes.getValue());
			// }
			//
			// });

			VBox vbox = new VBox();

			String nom = new String(this.modele.Limages.get(i).path.toString().split("\\\\")[1].split("\\.")[0]);
			if (nom.length() > 15) {
				nom = nom.substring(0, 15) + "..."
						+ this.modele.Limages.get(i).path.toString().split("\\\\")[1].split("\\.")[1];
			} else {
				nom = nom + "." + this.modele.Limages.get(i).path.toString().split("\\\\")[1].split("\\.")[1];
			}

			Label label1 = new Label(nom);

			vbox.setSpacing(10);
			vbox.getChildren().addAll(imageView, label1);
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
		final ImageBI img = new ImageBI(nom);
		Image temp = new Image("file:" + img.path, 150, 0, true, true);
		ImageView imageView = new ImageView(temp);
		imageView.setFitWidth(150);
		imageView.getStyleClass().add("image");
		return imageView;
	}

}