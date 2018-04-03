package PROJET_BROUILLON;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class ModeleTest {

	public String ImagesDirectory = null;
	ArrayList<Image> Limages = new ArrayList<Image>();
	File[] NameImages;

	private ModeleTest modele;

	public ModeleTest(String s) {
		System.out.println("modele cons");
		this.ImagesDirectory = s;
		this.NameImages = getImages();
		// getImages();
	}

	public void initModele(ModeleTest modele) {
		this.modele = modele;
	}

	public File[] getImages() {
		return new File(this.ImagesDirectory).listFiles(); // Récupère toutes les ID (ex : "Images\Irlande.jpg") des
		// images du repertoire en paramètre

	}

}
