package PROJET_BROUILLON;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


public class ControlerAR {
	@FXML
	private AnchorPane AP1;
	@FXML
	private TextFlow TF_Explicatif;
	@FXML
	private TextFlow TF_F1;
	@FXML
	private TextFlow TF_F2;
	@FXML
	private TextFlow TF_N1;
	@FXML
	private TextFlow TF_N2;
	@FXML
	private TextFlow TF_C1;
	@FXML
	private TextFlow TF_C2;
	@FXML
	private TextFlow TF_MC1;
	@FXML
	private TextFlow TF_MC2;
	@FXML
	private TextFlow TF_Cl1;
	@FXML
	private TextFlow TF_Cl2;

	private void description(){
		Text text1=new Text("Si vous ne savez pas comment marche notre "
				+ "système de recherche, voici une petite aide! ;) \n"
				+ "Nous avons un système qui permet de faire des recherches ET et OU. \n"
				+ "Ceci vous permettra d'améliorer votre recherche! \n"
				+ "\n"
				+ "Vous pouvez ne pas sélectionner un tri particulier!");
		text1.setFont(new Font(15));
		TF_Explicatif.getChildren().add(text1);
	}

	private void Desc_favoris(){
		Text text1= new Text("Favoris");
		text1.setFont(new Font(15));
		TF_F1.getChildren().add(text1);
		Text text2 = new Text("Vous avez le choix entre choisir une image en favoris ou non ou "
				+ "alors ne pas prendre en compte le tri favoris");
		text2.setFont(new Font(15));
		TF_F2.getChildren().add(text2);
	}

	private void Desc_notes(){

		Text text1=new Text("Notes");
		text1.setFont(new Font(15));
		TF_N1.getChildren().add(text1);
		Text text2=new Text("Vous avez le choix de vouloir des images notés de 1 à 5 "
				+ "(Vous pouvez choisir différentes notes");
		text2.setFont(new Font(15));
		text2.setTextAlignment(TextAlignment.CENTER);
		TF_N2.getChildren().add(text2);

	}

	private void Desc_couleurs(){
		Text text1=new Text("Couleurs");
		text1.setFont(new Font(15));
		TF_C1.getChildren().add(text1);
		Text description = new Text("Ecrivez les couleurs que vous voulez "
				+ "parmis : (possibilité choix multiple) \n");
		description.setFont(new Font(15));
		Text rouge= new Text("Rouge ");
		rouge.setFill(Color.RED);
		rouge.setFont(new Font(15));
		Text bleu= new Text("Bleu ");
		bleu.setFill(Color.BLUE);
		bleu.setFont(new Font(15));
		Text vert= new Text("Vert ");
		vert.setFill(Color.GREEN);
		vert.setFont(new Font(15));
		Text cyan= new Text("Cyan ");
		cyan.setFill(Color.CYAN);
		cyan.setFont(new Font(15));
		Text magenta= new Text("Magenta ");
		magenta.setFill(Color.MAGENTA);
		magenta.setFont(new Font(15));
		TF_C2.getChildren().addAll(description,rouge,bleu,vert,cyan,magenta);

	}

	private void Desc_Mots_clé(){
		Text text1= new Text("Mots Clé");
		text1.setFont(new Font(15));
		TF_MC1.getChildren().add(text1);

		Text text2= new Text("Choissisez les mots clés afin d'avoir les "
				+ "images que vous voulez voir s'afficher");
		text2.setFont(new Font(15));
		TF_MC2.getChildren().add(text2);

	}

	private void Desc_Classement(){
		Text text1=new Text("Classement");
		text1.setFont(new Font(15));
		TF_Cl1.getChildren().add(text1);
		Text text2=new Text("Choississez votre Classement parmis la taille,"
				+ " le poids, la note et le nombre d'ouverture");
		text2.setFont(new Font(15));
		TF_Cl2.getChildren().add(text2);
	}
	@FXML
	public void initialize() {
		description();
		Desc_favoris();
		Desc_notes();
		Desc_couleurs();
		Desc_Mots_clé();
		Desc_Classement();
	}
}
