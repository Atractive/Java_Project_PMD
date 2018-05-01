package PROJET_BROUILLON;

import java.io.File;
import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Files;

import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import Modele.ImageBI;
import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControlerMDI {

	@FXML
	public ModeleTest modele;
	@FXML
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
	private ChoiceBox<String> MenuB1;
	@FXML
	private TextField MenuB2;
	@FXML
	private TextField MenuB3;
	@FXML
	private TextField MenuB4;
	@FXML
	private ChoiceBox<String> MenuB5;
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
	@FXML
	private Button Aide_recherche;
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

	public String[] requete = new String[5];
	public ArrayList<String> ColorChoose = new ArrayList<String>(
			Arrays.asList("Rouge", "Bleu", "Vert", "Orange", "Cyan", "Magenta"));

	//Constructeur et lien avec le MODELE
	public ControlerMDI(ModeleTest modele) {
		this.modele = modele;
	}

	//Methode d'initialisation de tout les �l�ments de l'application
	@FXML
	public void initialize() {

		// ajouter_image();
		// supprimer_image();
		ModifFXML();
		InjectImages(modele.Limages);
		r�sultat_requeteerche();
		aide_recherche();
		System.out.println("SOS");

	}

	//Configuration des boutons pour le tri
	private void ModifFXML() {

		SplitP.setDividerPositions(0.1);
		Snote.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

		//Configuration des choix possibles pour les boutons
		MenuB1.getItems().addAll("Oui", "Non", "ND");
		MenuB5.getItems().addAll("Taille", "Poids", "Nombre d'ouverture", "ND");

		//Mise en place des choix par d�fault sur les boutons
		MenuB1.setValue("Oui");

		MenuB1.getStyleClass().add("but");
		MenuB2.setValue("3");
		MenuB2.getStyleClass().add("but");
		MenuB3.setValue("Vert");
		MenuB3.getStyleClass().add("but");
		MenuB4.setText("Paysage");
		MenuB4.getStyleClass().add("but");
		MenuB5.setValue("Taille");
		MenuB5.getStyleClass().add("but");

		MenuB6.getStyleClass().add("val");

	}


	//Configuration du bouton de validation
	private void new_contro() throws Exception{
		FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("AideRecherche.fxml"));
		//secondLoader.setController(ControlerAR.class);


		Stage stage = new Stage();
		//AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("AideRecherche.fxml"));

        Scene scene = new Scene(secondLoader.load(),400,400);
        stage.setScene(scene);
        stage.show();
	}
	private void aide_recherche(){
		Aide_recherche.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0){
				// TODO Auto-generated method stub
				System.out.println("coucou");
				try {
					new_contro();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};
		});

	}

	private void r�sultat_requeteerche() {
		MenuB6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				String menub2 = "";
				String menub3 = "";
				String menub4 = "";
				if (MenuB2.getText() == null || MenuB2.getText().trim().isEmpty()) {
					menub2 = "null";
				} else {
					menub2 = MenuB2.getText();
				}
				if (MenuB3.getText() == null || MenuB3.getText().trim().isEmpty()) {
					menub3 = "null";

				} else {
					menub3 = MenuB3.getText();
				}

				if (MenuB4.getText() == null || MenuB4.getText().trim().isEmpty()) {
					menub4 = "null";

				} else {
					menub4 = MenuB4.getText();
				}

				String[] tab = { MenuB1.getSelectionModel().getSelectedItem(), menub2, menub3, menub4,
						MenuB5.getSelectionModel().getSelectedItem() };

				buildListeTri(tab);

			};
		});

	}

	//Methode pour trier les images en fonction des choix sur les boutons
	private void buildListeTri(String[] requete) {
		HashSet<String> TriBin = new HashSet<String>();
		ArrayList<String> NotesRequetes = new ArrayList<String>(Arrays.asList(requete[1].split(" ")));
		ArrayList<String> CouleursRequetes = new ArrayList<String>(Arrays.asList(requete[2].split(" ")));
		System.out.println(NotesRequetes);
		System.out.println(CouleursRequetes);



		// System.out.println(modele.ImagesFav);
		// System.out.println(modele.SetEveryImagesName);
		//
		// System.out.println(requete[0] + " " + requete[1] + " " + requete[2] + " " +
		// requete[3] + " " + requete[4]);
		// if (requete[0] == "oui") {
		// System.out.println("seulement FAV" + " " + modele.ImagesFav);
		// } else if (requete[0] == "non") {
		// System.out.println("seulement non FAV" + " " + "todo");
		// } else {
		// System.out.println("toutes FAV" + " " + "todo");
		// }
		//
		// System.out.println("NOTES " + ((Integer.parseInt(requete[1])) - 1));
		// System.out.println("COLOR " + " " + ColorChoose.indexOf(requete[2]));
		// System.out.println("MOT CLES" + " " + modele.MapTags.get(requete[3]));

		HashSet<String> SetEveryImagesNameCopy = new HashSet<>(modele.SetEveryImagesName);
		ArrayList<String> RenvoiFinal = new ArrayList<String>();
		ArrayList<ImageBI> RenvoiFinalDisplay = new ArrayList<ImageBI>();

		if (requete[0] == "Oui") {
			// System.out.println("seulement FAV" + " " + modele.ImagesFav);
			TriBin.addAll(modele.ImagesFav);
		} else if (requete[0] == "Non") {
			// System.out.println("seulement non FAV" + " " + "todo");
			SetEveryImagesNameCopy.removeAll(modele.ImagesFav);
			TriBin.addAll(SetEveryImagesNameCopy);
			// System.out.println(SetEveryImagesNameCopy);

		} else {
			// System.out.println("toutes FAV" + " " + "todo");
			TriBin.addAll(modele.SetEveryImagesName);

		}

		if (requete[1] != "null") {
			ArrayList<String> Notesrequetes = new ArrayList<String>(Arrays.asList(requete[1].split(" ")));
			for (int i = 0; i < Notesrequetes.size(); i++) {
				int tempNote = Integer.parseInt(Notesrequetes.get(i));
				if (tempNote == 1) {
					TriBin.retainAll(modele.SetImages1Etoile);
				} else if (tempNote == 2) {
					TriBin.retainAll(modele.SetImages2Etoile);
				} else if (tempNote == 3) {
					TriBin.retainAll(modele.SetImages3Etoile);
				} else if (tempNote == 4) {
					TriBin.retainAll(modele.SetImages4Etoile);
				} else {
					TriBin.retainAll(modele.SetImages5Etoile);
				}
			}
		}

		if (requete[2] != "null") {
			ArrayList<String> Couleursrequetes = new ArrayList<String>(Arrays.asList(requete[2].split(" ")));
			// System.out.println(Couleursrequetes);
			for (int i = 0; i < Couleursrequetes.size(); i++) {
				// System.out.println(modele.ListeSetImagesCouleurs.get(ColorChoose.indexOf(Couleursrequetes.get(i))));
				//TriBin.retainAll(modele.ListeSetImagesCouleurs.get(ColorChoose.indexOf(Couleursrequetes.get(i))));
			}
		}

		if (requete[3] != "null") {
			ArrayList<String> Tagsrequetes = new ArrayList<String>(Arrays.asList(requete[3].split(" ")));
			// System.out.println(Tagsrequetes);
			// System.out.println(Tagsrequetes);
			for (int i = 0; i < Tagsrequetes.size(); i++) {
				// System.out.println(Tagsrequetes.get(i) + " " +
				// modele.MapTags.get(Tagsrequetes.get(i)));
				TriBin.retainAll(modele.MapTags.get(Tagsrequetes.get(i)));
			}
		}

		// System.out.println(TriBin);
		// System.out.println(requete[4]);

		if (requete[4] == "Taille" || requete[4] == "Poids") {
			for (Integer key : modele.MapImagesTaille.keySet()) {
				// System.out.println(key + " " + modele.MapImagesTaille.get(key));
				for (String s : TriBin) {
					// System.out.println(modele.MapImagesTaille.get(key) + " " + s + " | " +
					// modele.MapImagesTaille.get(key).contains(s));
					if (modele.MapImagesTaille.get(key).contains(s)) {
						RenvoiFinal.add(s);
					}
				}
			}
		} else {
			for (Integer key : modele.MapImagesCptOpen.keySet()) {
				// System.out.println(key + " " + modele.MapImagesCptOpen.get(key));
				for (String s : TriBin) {
					if ((modele.MapImagesCptOpen.get(key).contains(s))) {
						RenvoiFinal.add(s);
					}
				}
			}
		}


		// System.out.println(RenvoiFinal);
		for (int i = 0; i < RenvoiFinal.size(); i++) {
			int indexTempImageBI = (modele.LimagesPATH.indexOf(RenvoiFinal.get(i)));
			RenvoiFinalDisplay.add(modele.Limages.get(indexTempImageBI));
		}
		System.out.println(RenvoiFinalDisplay); // IMAGESBI DU TRI DE L'UTILISATEUR
		TilePaneGalerie.getChildren().clear();
		InjectImages(RenvoiFinalDisplay);

		System.out.println(RenvoiFinal);
	}

	// private void ajouter_image() {
	// Button6.setOnAction(new EventHandler<ActionEvent>() {
	//
	// @Override
	// public void handle(final ActionEvent e) {
	// FileChooser filechooser = new FileChooser();
	// filechooser.getExtensionFilters()
	// .addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif",
	// "*jpeg"));
	//
	// Stage newWindow = new Stage();
	//
	// newWindow.setTitle("Second Stage");
	//
	// // Specifies the modality for new window.
	// newWindow.initModality(Modality.WINDOW_MODAL);
	//
	// File list = filechooser.showOpenDialog(newWindow);
	// if (list != null) {
	//
	// try {
	// Files.move(FileSystems.getDefault().getPath(list.getPath()),
	// FileSystems.getDefault().getPath(new File("Images/" +
	// list.getName()).getPath()),
	// StandardCopyOption.REPLACE_EXISTING);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// TilePaneGalerie.getChildren().clear();
	// InjectImages();
	// }
	// }
	// });
	// }
	//
	// private void supprimer_image() {
	// Button7.setOnAction(new EventHandler<ActionEvent>() {
	//
	// @Override
	// public void handle(final ActionEvent e) {
	// FileChooser filechooser = new FileChooser();
	// filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files",
	// "*.png", "*.jpg", "*.gif"));
	//
	// filechooser.setInitialDirectory(new File("Images"));
	//
	// Stage newWindow = new Stage();
	//
	// newWindow.setTitle("Second Stage");
	//
	// // Specifies the modality for new window.
	// newWindow.initModality(Modality.WINDOW_MODAL);
	//
	// File list = filechooser.showOpenDialog(newWindow);
	//
	// if (list != null) {
	// try {
	// Files.delete(FileSystems.getDefault().getPath(new File("Images/" +
	// list.getName()).getPath()));
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// TilePaneGalerie.getChildren().clear();
	// InjectImages();
	// }
	// }
	// });
	// }

<<<<<<< HEAD
	//M�thode qui assure le chargement des images
	private void InjectImages(ArrayList<ImageBI> LimagesC) { // ajouter un param�tre �tant une liste de string que sont
																// les paths des images
		// � display.
=======

>>>>>>> 175703589699e826877178b373eb075550532ee9

	private void InjectImages(ArrayList<ImageBI> LimagesC) {
		TilePaneGalerie.setPadding(new Insets(15, 15, 15, 15));
		TilePaneGalerie.setHgap(10);
		TilePaneGalerie.getStyleClass().add("tpg");

		for (int i = 0; i < LimagesC.size(); i++) {
			ImageView imageView;
			imageView = createImageView(LimagesC.get(i));
			imageView.setId(String.valueOf(i));
			System.out.println(LimagesC.get(i));
			// imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// public void handle(MouseEvent event) { // Au clic, changement de tab et
			// affichage de l'image
			// if (event.getButton().equals(MouseButton.PRIMARY)) {
			// if (event.getClickCount() == 2) {
			// TabP.getSelectionModel().selectNext(); // Change de tab
			// String source2 = event.getPickResult().getIntersectedNode().getId();
			// temp_index = source2;
			// // System.out.println(temp_index);
			// ImageView temp = new ImageView();
			// final ImageBI img = LimagesC.get(Integer.parseInt(source2));
			// Image tempI = new Image("file:" + img.path);
			// BorderPane borderPane = new BorderPane();
			// temp.setImage(tempI);
			// temp.setStyle("-fx-background-color: BLACK");
			// temp.setFitHeight(LeftImgComplete.getHeight());
			// temp.setPreserveRatio(true);
			// temp.setSmooth(true);
			// temp.setCache(true);
			// borderPane.setCenter(temp);
			// borderPane.setStyle("-fx-background-color: BLACK");
			// LeftImgComplete.getChildren().add(borderPane);
			// LeftImgComplete.getChildren().clear();
			// LeftImgComplete.getChildren().add(temp);
			//
			// Snom.textProperty().setValue(img.nom);
			// Staille.textProperty().setValue(
			// (int) Math.round(tempI.getWidth()) + " x " + (int)
			// Math.round(tempI.getHeight()));
			// Spoids.textProperty()
			// .setValue(String.valueOf(Math.round(tempI.getWidth() * tempI.getHeight() *
			// 4)));
			// Stags.textProperty().setValue(img.show_Tags(img.mots_clefs));
			// SplitPaneImgComplete.setDividerPositions(0.8f, 0.2f);
			// img.Increase_nbOuverture();
			// Sopen.setText(String.valueOf(img.nb_ouverture));
			//
			// Scolors.setText(img.couleur);
			// Sfavoris.setSelected(img.favoris);
			// Snote.setValue(img.etoile);
			//
			// Snom.setEditable(false);
			// Staille.setEditable(false);
			// Spoids.setEditable(false);
			// Scolors.setEditable(false);
			//
			// @SuppressWarnings("unused")
			// HashSet<String> toworkValue = new HashSet<String>();
			// int toworkKey = 0;
			// // System.out.println("before" + modele.MapImagesCptOpen);
			// for (Integer key : modele.MapImagesCptOpen.keySet()) {
			// if (modele.MapImagesCptOpen.get(key)
			// .contains(modele.Limages.get(Integer.parseInt(temp_index)).path)) {
			// toworkValue = modele.MapImagesCptOpen.get(key);
			// toworkKey = key;
			// }
			//
			// }
			//
			// if (modele.MapImagesCptOpen.containsKey(toworkKey + 1)) {
			// modele.MapImagesCptOpen.get(toworkKey + 1)
			// .add(modele.Limages.get(Integer.parseInt(temp_index)).path);
			// modele.MapImagesCptOpen.get(toworkKey)
			// .remove(modele.Limages.get(Integer.parseInt(temp_index)).path);
			//
			// } else {
			// HashSet<String> tempAdd = new HashSet<String>();
			// tempAdd.add(modele.Limages.get(Integer.parseInt(temp_index)).path);
			// modele.MapImagesCptOpen.put(toworkKey + 1, tempAdd);
			// modele.MapImagesCptOpen.get(toworkKey)
			// .remove(modele.Limages.get(Integer.parseInt(temp_index)).path);
			//
			// }
			// // System.out.println("after" + modele.MapImagesCptOpen);
			// // System.out.println("-------------------");
			// }
			// }
			// }
			// });

			//Configuration de la zone de texte pour les tags
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

			//Configuration du bouton qui g�re les favoris
			Sfavoris.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					int index = Integer.parseInt(temp_index);
					modele.Limages.get(index).Set_Favoris();
					// System.out.println(modele.ImagesFav);
					if (modele.ImagesFav.contains(modele.Limages.get(index).path)) {
						modele.ImagesFav.remove(modele.Limages.get(index).path);
					} else {
						modele.ImagesFav.add(modele.Limages.get(index).path);

					}
					// System.out.println(modele.ImagesFav);

				}
			});

			//Configuration de la boite qui permet de g�rer les notes
			Snote.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				@Override

				public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
					// System.out.println("--------------------");
					// System.out.println("BEFORE");
					// System.out.println(modele.SetImages1Etoile);
					// System.out.println(modele.SetImages2Etoile);
					// System.out.println(modele.SetImages3Etoile);
					// System.out.println(modele.SetImages4Etoile);
					// System.out.println(modele.SetImages5Etoile);
					// System.out.println();
					if ((int) number == -1) {
						return;
					}
					ArrayList<HashSet<String>> temp = new ArrayList<HashSet<String>>(
							Arrays.asList(modele.SetImages1Etoile, modele.SetImages2Etoile, modele.SetImages3Etoile,
									modele.SetImages4Etoile, modele.SetImages5Etoile));

					modele.Limages.get(Integer.parseInt(temp_index)).Set_Etoile((Integer) number2);
					modele.Limages.get(Integer.parseInt(temp_index)).etoile = (Integer) number2 + 1;
					temp.get((int) number).remove(LimagesC.get(Integer.parseInt(temp_index)).path);
					temp.get((int) number2).add(LimagesC.get(Integer.parseInt(temp_index)).path);
					// System.out.println(modele.SetImages1Etoile);
					// System.out.println(modele.SetImages2Etoile);
					// System.out.println(modele.SetImages3Etoile);
					// System.out.println(modele.SetImages4Etoile);
					// System.out.println(modele.SetImages5Etoile);
					// System.out.println("AFTER");
					// System.out.println("--------------------");

				}
			});



			//Concat�nation du nom de l'image
			String nom = new String(this.modele.Limages.get(i).path.toString().split("\\\\")[1].split("\\.")[0]);
			if (nom.length() > 15) {nom = nom.substring(0, 15) + "..." + this.modele.Limages.get(i).path.toString().split("\\\\")[1].split("\\.")[1];
			} else {nom = nom + "." + this.modele.Limages.get(i).path.toString().split("\\\\")[1].split("\\.")[1];}
			Label label1 = new Label(nom);
			label1.getStyleClass().add("label1");

			//Cr�ation de la VBox qui accueille l'imageView et le label1
			VBox vbox = new VBox();
			vbox.setSpacing(10);
			vbox.getChildren().addAll(imageView, label1);
			vbox.setAlignment(Pos.CENTER);
			vbox.getStyleClass().add("vbox");
			TilePaneGalerie.getChildren().addAll(vbox);
		}

		ScrollPaneGalerie.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		ScrollPaneGalerie.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		ScrollPaneGalerie.setFitToWidth(true);
		ScrollPaneGalerie.getStyleClass().add("spg");
		ScrollPaneGalerie.setContent(TilePaneGalerie);

	}

	//M�thode qui permet la cr�ation de l'imageView
	private ImageView createImageView(ImageBI img) {
		Image temp = new Image("file:" + img.path, 100, 0, true, true);
		ImageView imageView = new ImageView(temp);
		imageView.setFitWidth(100);
		imageView.getStyleClass().add("image");
		return imageView;
	}

	private void Tags_soustraction(int indeximg, ArrayList<String> A, ArrayList<String> B) {
		for (int i = 1; i < A.size(); i++) {
			HashSet<String> temp = (modele.MapTags.get(A.get(i)));
			if (!B.contains(A.get(i))) {
				temp.remove(modele.Limages.get(indeximg).path);
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
					modele.MapTags.get(SetDiff.get(j)).add(modele.Limages.get(indeximg).path);
				} else {
					HashSet<String> tempp = new HashSet<String>();
					tempp.add(modele.Limages.get(indeximg).path);
					modele.MapTags.put(SetDiff.get(j), tempp);
				}
			}
		}

	}

	// private void ajouter_image() {
	// Button6.setOnAction(new EventHandler<ActionEvent>() {
	//
	// @Override
	// public void handle(final ActionEvent e) {
	// FileChooser filechooser = new FileChooser();
	// filechooser.getExtensionFilters()
	// .addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif",
	// "*jpeg"));
	//
	// Stage newWindow = new Stage();
	//
	// newWindow.setTitle("Second Stage");
	//
	// // Specifies the modality for new window.
	// newWindow.initModality(Modality.WINDOW_MODAL);
	//
	// File list = filechooser.showOpenDialog(newWindow);
	// if (list != null) {
	//
	// try {
	// Files.move(FileSystems.getDefault().getPath(list.getPath()),
	// FileSystems.getDefault().getPath(new File("Images/" +
	// list.getName()).getPath()),
	// StandardCopyOption.REPLACE_EXISTING);
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// TilePaneGalerie.getChildren().clear();
	// InjectImages();
	// }
	// }
	// });
	// }
	//
	// private void supprimer_image() {
	// Button7.setOnAction(new EventHandler<ActionEvent>() {
	//
	// @Override
	// public void handle(final ActionEvent e) {
	// FileChooser filechooser = new FileChooser();
	// filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files",
	// "*.png", "*.jpg", "*.gif"));
	//
	// filechooser.setInitialDirectory(new File("Images"));
	//
	// Stage newWindow = new Stage();
	//
	// newWindow.setTitle("Second Stage");
	//
	// // Specifies the modality for new window.
	// newWindow.initModality(Modality.WINDOW_MODAL);
	//
	// File list = filechooser.showOpenDialog(newWindow);
	//
	// if (list != null) {
	// try {
	// Files.delete(FileSystems.getDefault().getPath(new File("Images/" +
	// list.getName()).getPath()));
	// } catch (IOException e1) {
	// e1.printStackTrace();
	// }
	// TilePaneGalerie.getChildren().clear();
	// InjectImages();
	// }
	// }
	// });
	// }
}