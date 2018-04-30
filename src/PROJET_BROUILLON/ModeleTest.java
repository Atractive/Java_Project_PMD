package PROJET_BROUILLON;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import Autre.CouleurDominante;
import Modele.ImageBI;
import javafx.scene.image.Image;

public class ModeleTest {

	ArrayList<ImageBI> Limages = new ArrayList<ImageBI>();

	ArrayList<ImageBI> Limages_unserialized = new ArrayList<ImageBI>();
	ArrayList<ImageBI> Limages_loaddata = new ArrayList<ImageBI>();

	//// TRI

	HashSet<Integer> ImagesFav = new HashSet<Integer>();

	HashSet<Integer> SetImages1Etoile = new HashSet<Integer>();
	HashSet<Integer> SetImages2Etoile = new HashSet<Integer>();
	HashSet<Integer> SetImages3Etoile = new HashSet<Integer>();
	HashSet<Integer> SetImages4Etoile = new HashSet<Integer>();
	HashSet<Integer> SetImages5Etoile = new HashSet<Integer>();

	// "Red", "Blue", "Green", "Orange", "Cyan", "Magenta"

	HashSet<Integer> SetImagesRed = new HashSet<Integer>();
	HashSet<Integer> SetImagesBlue = new HashSet<Integer>();
	HashSet<Integer> SetImagesGreen = new HashSet<Integer>();
	HashSet<Integer> SetImagesOrange = new HashSet<Integer>();
	HashSet<Integer> SetImagesCyan = new HashSet<Integer>();
	HashSet<Integer> SetImagesMagenta = new HashSet<Integer>();

	Map<String, HashSet<Integer>> MapTags = new HashMap<String, HashSet<Integer>>();

	Map<Integer, HashSet<Integer>> MapImagesTaille = new TreeMap<Integer, HashSet<Integer>>();
	Map<Integer, HashSet<Integer>> MapImagesPoids = new TreeMap<Integer, HashSet<Integer>>();

	// Map<Integer, HashSet<Integer>> MapImagesCptOpen = new TreeMap<Integer,
	// HashSet<Integer>>();

	Map<Integer, HashSet<Integer>> MapImagesCptOpen = Collections
			.synchronizedMap(Collections.synchronizedMap(new TreeMap<Integer, HashSet<Integer>>()));

	//// TRI

	private ModeleTest modele;

	public ModeleTest(String s) throws ClassNotFoundException, IOException {
		File temp = new File("images.dat");
		File[] imagesListe = new File(s).listFiles();
		
		LoadDataF(s);
		if (temp.exists() && imagesListe.length == Limages_unserialized.size()) {
			this.Limages = Limages_unserialized;
		} else {
			chargerDonnées(s);
			this.Limages = this.Limages_loaddata;
		}

		System.out.println(this.Limages.size());
		DataFavoris();
		ImagesEtoiles();
		ImagesTags();
		ImagesCouleurs();
		ImagesTailles();
		ImagesPoids();
		ImagesCptOpen();
		 System.out.println(this.MapImagesTaille);
		 for (Integer key : MapImagesCptOpen.keySet()) {
		 System.out.println(key + " " + MapImagesCptOpen.get(key));
		 }
		 System.out.println(this.ImagesFav);
		 System.out.println("--------------------");
		 System.out.println(this.MapTags);
		 System.out.println("--------------------");
		 System.out.println(this.SetImages1Etoile);
		 System.out.println(this.SetImages2Etoile);
		 System.out.println(this.SetImages3Etoile);
		 System.out.println(this.SetImages4Etoile);
		 System.out.println(this.SetImages5Etoile);
		 System.out.println("--------------------");
		 System.out.println(this.SetImagesRed);
		 System.out.println(this.SetImagesBlue);
		 System.out.println(this.SetImagesGreen);
		 System.out.println(this.SetImagesCyan);
		 System.out.println(this.SetImagesMagenta);

	}

	public void initModele(ModeleTest modele) {
		this.modele = modele;
	}

	public void chargerDonnées(String dir) {
		File[] imagesListe = new File(dir).listFiles();
		for (File file : imagesListe) {
			ImageBI Imagetemp = new ImageBI(file.toString());
			this.Limages_loaddata.add(Imagetemp);
		}
	}

	public void SerializeData(File fichier) throws IOException {

		FileOutputStream fos = new FileOutputStream(fichier);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(this.Limages);
		oos.close();
		fos.close();
	}

	public void LoadDataF(String dir) throws FileNotFoundException, IOException, ClassNotFoundException {

		File fichier = new File("images.dat");
		ObjectInputStream ois;
		ois = new ObjectInputStream(new FileInputStream(fichier));
		this.Limages_unserialized = (ArrayList<ImageBI>) ois.readObject();
		ois.close();

	}

	public void DataFavoris() {
		for (int i = 0; i < this.Limages.size(); i++) {
			if (this.Limages.get(i).favoris == true) {
				this.ImagesFav.add(i);
			}

		}
	}

	public void ImagesEtoiles() {
		for (int i = 0; i < this.Limages.size(); i++) {
			if (this.Limages.get(i).etoile == 1) {
				this.SetImages1Etoile.add(i);
			} else if (this.Limages.get(i).etoile == 1) {
				this.SetImages1Etoile.add(i);

			} else if (this.Limages.get(i).etoile == 2) {
				this.SetImages2Etoile.add(i);

			} else if (this.Limages.get(i).etoile == 3) {
				this.SetImages3Etoile.add(i);

			} else if (this.Limages.get(i).etoile == 4) {
				this.SetImages4Etoile.add(i);

			} else if (this.Limages.get(i).etoile == 5) {
				this.SetImages5Etoile.add(i);

			}
		}
	}

	public void ImagesCouleurs() {
		for (int i = 0; i < this.Limages.size(); i++) {
			String couleur = CouleurDominante.getDomintanteColor(this.Limages.get(i).path);
			if (couleur == "Red") {
				this.SetImagesRed.add(i);
			}
			if (couleur == "Blue") {
				this.SetImagesBlue.add(i);
			}
			if (couleur == "Green") {
				this.SetImagesGreen.add(i);
			}
			if (couleur.equals("Cyan")) {
				this.SetImagesCyan.add(i);
			}
			if (couleur == "Magenta") {
				this.SetImagesMagenta.add(i);
			}
			if (couleur == "Orange") {
				this.SetImagesOrange.add(i);
			}
		}
	}

	public void ImagesTags() {
		for (int i = 0; i < this.Limages.size(); i++) {
			for (int j = 1; j < this.Limages.get(i).mots_clefs.size(); j++) {
				if (this.MapTags.containsKey(this.Limages.get(i).mots_clefs.get(j))) {
					this.MapTags.get(this.Limages.get(i).mots_clefs.get(j)).add(i);

				} else {
					HashSet<Integer> temp = new HashSet<Integer>();
					temp.add(i);
					this.MapTags.put(this.Limages.get(i).mots_clefs.get(j), temp);
				}
			}
		}
	}

	public void ImagesTailles() {
		for (int i = 0; i < this.Limages.size(); i++) {
			ImageBI tempImagePerso = this.Limages.get(i);
			Image tempImage = new Image("file:" + tempImagePerso.path);
			int taille = (int) Math.round(tempImage.getWidth() * tempImage.getHeight());
			// int poids = taille * 4;
			if (this.MapImagesTaille.containsKey(taille)) {
				this.MapImagesTaille.get(taille).add(i);
			} else {
				HashSet<Integer> temp1 = new HashSet<Integer>();
				temp1.add(i);
				this.MapImagesTaille.put(taille, temp1);
			}

		}
	}

	public void ImagesPoids() {
		for (int i = 0; i < this.Limages.size(); i++) {
			ImageBI tempImagePerso = this.Limages.get(i);
			Image tempImage = new Image("file:" + tempImagePerso.path);
			int taille = (int) Math.round(tempImage.getWidth() * tempImage.getHeight());
			int poids = taille * 4;
			if (this.MapImagesPoids.containsKey(poids)) {
				this.MapImagesPoids.get(poids).add(i);
			} else {
				HashSet<Integer> temp1 = new HashSet<Integer>();
				temp1.add(i);
				this.MapImagesPoids.put(poids, temp1);
			}

		}
	}

	public void ImagesCptOpen() {
		for (int i = 0; i < this.Limages.size(); i++) {
			ImageBI tempImagePerso = this.Limages.get(i);
			int tempCpt = tempImagePerso.nb_ouverture;
			if (this.MapImagesCptOpen.containsKey(tempCpt)) {
				this.MapImagesCptOpen.get(tempCpt).add(i);
			} else {
				HashSet<Integer> temp1 = new HashSet<Integer>();
				temp1.add(i);
				this.MapImagesCptOpen.put(tempCpt, temp1);
			}

		}
	}

}
