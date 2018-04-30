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
import java.util.HashSet;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
	private ChoiceBox MenuB1;
	@FXML
	private ChoiceBox MenuB2;
	@FXML
	private ChoiceBox MenuB3;
	@FXML
	private TextField MenuB4;
	@FXML
	private ChoiceBox MenuB5;
	@FXML
	private Button MenuB6;
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
	
	public Object[] rech = new Object[5];
	@FXML
	public void initialize() {
		Snote.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
		MenuB1.getItems().addAll("Oui","Non","ND");
		MenuB2.getItems().addAll("1","2","3","4","5","ND");
		MenuB3.getItems().addAll("Rouge","Bleu","Vert","Cyan","Magenta","ND");
		MenuB5.getItems().addAll("Taille","Poids","Nombre d'ouverture","ND");
		ajouter_image();
		supprimer_image();
		r�sultat_recherche();
		InjectImages();
		System.out.println("SOS");
	}

	private void r�sultat_recherche(){
		MenuB6.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object Tf="";
				if (MenuB4.getCharacters().length()==0){
					Tf="ND";
				}else{
					Tf=MenuB4.getCharacters();
				}
				Object[] tab = {MenuB1.getValue(),MenuB2.getValue(),MenuB3.getValue(),Tf,MenuB5.getValue()};
				
				//System.arraycopy(rech, 0, tab, 0, tab.length);
				for (int i = 0; i < tab.length; i++) { 
				       rech[i] = tab[i]; 
				    } 
				
				System.out.println(Arrays.toString(rech));
			};
		});
		
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
						e1.printStackTrace();
					}
					TilePaneGalerie.getChildren().clear();
					InjectImages();
				}
			}
		});
	}

	private void InjectImages() { // ajouter un param�tre �tant une liste de string que sont les paths des images
									// � display.

		TilePaneGalerie.setPadding(new Insets(15, 15, 15, 15));
		TilePaneGalerie.setHgap(10);

		ArrayList<ImageBI> LimagesC = this.modele.Limages;

		for (int i = 0; i < LimagesC.size(); i++) {
			ImageView imageView;
			imageView = createImageView(LimagesC.get(i));
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
							img.Increase_nbOuverture();
							Sopen.setText(String.valueOf(img.nb_ouverture));

							Scolors.setText(img.couleur);
							Sfavoris.setSelected(img.favoris);
							Snote.setValue(img.etoile);

							Snom.setEditable(false);
							Staille.setEditable(false);
							Spoids.setEditable(false);
							Scolors.setEditable(false);
							HashSet<Integer> toworkValue = new HashSet<Integer>();
							int toworkKey = 0;
							// System.out.println("before" + modele.MapImagesCptOpen);
							for (Integer key : modele.MapImagesCptOpen.keySet()) {
								if (modele.MapImagesCptOpen.get(key).contains(Integer.parseInt(temp_index))) {
									toworkValue = modele.MapImagesCptOpen.get(key);
									toworkKey = key;
								}

							}

							if (modele.MapImagesCptOpen.containsKey(toworkKey + 1)) {
								modele.MapImagesCptOpen.get(toworkKey + 1).add(Integer.parseInt(temp_index));
								modele.MapImagesCptOpen.get(toworkKey).remove(Integer.parseInt(temp_index));

							} else {
								HashSet<Integer> tempAdd = new HashSet<Integer>();
								tempAdd.add(Integer.parseInt(temp_index));
								modele.MapImagesCptOpen.put(toworkKey + 1, tempAdd);
								modele.MapImagesCptOpen.get(toworkKey).remove(Integer.parseInt(temp_index));

							}
							// System.out.println("after" + modele.MapImagesCptOpen);
							// System.out.println("-------------------");
						}
					}
				}
			});

			Stags.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode() == KeyCode.ENTER) {
						ArrayList<String> before = new ArrayList<>();
						before = LimagesC.get(Integer.parseInt(temp_index)).mots_clefs;
						// System.out.println("before" + " " +
						// LimagesC.get(Integer.parseInt(temp_index)).mots_clefs);
						String text = Stags.getText().trim().replaceAll("\n", "").replaceAll("\r", "")
								.replaceAll("\\s+", "");
						LimagesC.get(Integer.parseInt(temp_index)).Set_Tags(text);
						ArrayList<String> after = new ArrayList<>();
						after = LimagesC.get(Integer.parseInt(temp_index)).mots_clefs;
						// System.out.println("after" + " " +
						// LimagesC.get(Integer.parseInt(temp_index)).mots_clefs);
						Tags_soustraction(Integer.parseInt(temp_index), before, after);
						// System.out.println(modele.MapTags);
					}
				}
			});

			Sfavoris.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					int index = Integer.parseInt(temp_index);
					modele.Limages.get(Integer.parseInt(temp_index)).Set_Favoris();
					if (modele.ImagesFav.contains(index)) {
						modele.ImagesFav.remove(index);
					} else {
						modele.ImagesFav.add(Integer.parseInt(temp_index));

					}
				}

			});

			Snote.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				@Override

				public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
					if ((int) number == -1) {
						return;
					}
					ArrayList<HashSet<Integer>> temp = new ArrayList<HashSet<Integer>>(
							Arrays.asList(modele.SetImages1Etoile, modele.SetImages2Etoile, modele.SetImages3Etoile,
									modele.SetImages4Etoile, modele.SetImages5Etoile));

					modele.Limages.get(Integer.parseInt(temp_index)).Set_Etoile((Integer) number2);
					modele.Limages.get(Integer.parseInt(temp_index)).etoile = (Integer) number2 + 1;
					temp.get((int) number).remove(Integer.parseInt(temp_index));
					temp.get((int) number2).add(Integer.parseInt(temp_index));

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

	private ImageView createImageView(ImageBI img) {
		Image temp = new Image("file:" + img.path, 150, 0, true, true);
		ImageView imageView = new ImageView(temp);
		imageView.setFitWidth(150);
		imageView.getStyleClass().add("image");
		return imageView;
	}

	private void Tags_soustraction(int indeximg, ArrayList<String> A, ArrayList<String> B) {
		for (int i = 1; i < A.size(); i++) {
			HashSet<Integer> temp = (modele.MapTags.get(A.get(i)));
			if (!B.contains(A.get(i))) {
				temp.remove(indeximg);
			}
		}

		if (B.size() != A.size()) {
			HashSet<String> Aset = new HashSet<String>(A);
			HashSet<String> Bset = new HashSet<String>(B);
			Bset.removeAll(Aset);

			ArrayList<String> SetDiff = new ArrayList<String>();
			SetDiff.addAll(Bset);
			for (int j = 0; j < SetDiff.size(); j++) {
				if (modele.MapTags.containsKey(SetDiff.get(j))) {
					modele.MapTags.get(SetDiff.get(j)).add(indeximg);
				} else {
					HashSet<Integer> tempp = new HashSet<Integer>();
					tempp.add(indeximg);
					modele.MapTags.put(SetDiff.get(j), tempp);
				}
			}
		}

	}

}