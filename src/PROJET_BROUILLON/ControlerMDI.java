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
import java.util.Collection;
import java.util.List;

import javax.xml.transform.Source;

import Autre.CouleurDominante;
import Modele.ImageBI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableSetValue;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

	public String temp_index;

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
	private TextArea Stags;
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
	}

	@FXML
	public void initialize() {
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

				List<File> list = filechooser.showOpenMultipleDialog(newWindow);
				if (list != null) {

					try {
						for(File file:list){
							//Files.copy(FileSystems.getDefault().getPath(file.getPath()),FileSystems.getDefault().getPath(file.getParent()+file.getName()));
							Files.copy(FileSystems.getDefault().getPath(file.getParent()+"\\"+file.getName()),
									FileSystems.getDefault().getPath(new File("Images/" + file.getName()).getPath()),
									StandardCopyOption.REPLACE_EXISTING);
									//LimagesC.addAll((Collection<? extends ImageBI>) new File("Images/" + file.getName()));
							//this.modele.Limages.add(new File("Images/" + file.getName()).getPath());
							//Files.copy(FileSystems.getDefault().getPath(file.getPath()),FileSystems.getDefault().getPath(file.getParent()));
						}


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

				List<File> list = filechooser.showOpenMultipleDialog(newWindow);

				if (list != null) {
					try {
						for (File file:list){
							Files.delete(FileSystems.getDefault().getPath(new File("Images/" + file.getName()).getPath()));
						}
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
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						if (event.getClickCount() == 2) {
							TabP.getSelectionModel().selectNext(); // Change de tab
							String source2 = event.getPickResult().getIntersectedNode().getId();
							temp_index = source2;
							ImageView temp = new ImageView();
							final ImageBI img = LimagesC.get(Integer.parseInt(source2));
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
									(int) Math.round(tempI.getWidth()) + " x " + (int) Math.round(tempI.getHeight()));
							Spoids.textProperty()
							.setValue(String.valueOf(Math.round(tempI.getWidth() * tempI.getHeight() * 4)));
							Stags.textProperty().setValue(img.show_Tags(img.mots_clefs));
							SplitPaneImgComplete.setDividerPositions(0.8f, 0.2f);
							Snote.setValue(img.etoile);
							System.out.println(img.etoile);
							img.Increase_nbOuverture();
							Sopen.setText(String.valueOf(img.nb_ouverture));
							Scolors.setText(CouleurDominante.getDomintanteColor(img.path));
							Sfavoris.setSelected(img.favoris);

							Snom.setEditable(false);
							Staille.setEditable(false);
							Spoids.setEditable(false);
							Scolors.setEditable(false);

						}
					}
				}
			});

			Stags.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode() == KeyCode.ENTER) {

						String text = Stags.getText().trim().replaceAll("\n", "").replaceAll("\r", "")
								.replaceAll("\\s+", "");
						System.out.println(text);
						LimagesC.get(Integer.parseInt(temp_index)).Set_Tags(text);
					}
				}
			});

			Sfavoris.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					LimagesC.get(Integer.parseInt(temp_index)).Set_Favoris();
				}

			});

			Snote.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
					LimagesC.get(Integer.parseInt(temp_index)).Set_Etoile((Integer) number2);
					LimagesC.get(Integer.parseInt(temp_index)).etoile = (Integer) number2 + 1;

				}
			});

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