package PROJET_BROUILLON;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

	ArrayList<String> LimagesPATH = new ArrayList<String>();

	//// TRI

	// FAVORIS
	HashSet<String> ImagesFav = new HashSet<String>();

	// ETOILE
	HashSet<String> SetImages1Etoile = new HashSet<String>();
	HashSet<String> SetImages2Etoile = new HashSet<String>();
	HashSet<String> SetImages3Etoile = new HashSet<String>();
	HashSet<String> SetImages4Etoile = new HashSet<String>();
	HashSet<String> SetImages5Etoile = new HashSet<String>();

	ArrayList<HashSet<String>> ListeSetImagesEtoiles = new ArrayList<HashSet<String>>(
			Arrays.asList(SetImages1Etoile, SetImages2Etoile, SetImages3Etoile, SetImages4Etoile, SetImages5Etoile));

	HashSet<String> SetImagesRed = new HashSet<String>();
	HashSet<String> SetImagesBlue = new HashSet<String>();
	HashSet<String> SetImagesGreen = new HashSet<String>();
	HashSet<String> SetImagesOrange = new HashSet<String>();
	HashSet<String> SetImagesCyan = new HashSet<String>();
	HashSet<String> SetImagesMagenta = new HashSet<String>();

	ArrayList<HashSet<String>> ListeSetImagesCouleurs = new ArrayList<HashSet<String>>(Arrays.asList(SetImagesRed,
			SetImagesBlue, SetImagesGreen, SetImagesOrange, SetImagesCyan, SetImagesMagenta));

	Map<Integer, HashSet<String>> MapImagesTaille = new TreeMap<Integer, HashSet<String>>();

	// POIDS
	Map<Integer, HashSet<String>> MapImagesPoids = new TreeMap<Integer, HashSet<String>>();

	// TAGS
	Map<String, HashSet<String>> MapTags = new HashMap<String, HashSet<String>>();

	// NBR D'OUVERTURE
	Map<Integer, HashSet<String>> MapImagesCptOpen = Collections
			.synchronizedMap(Collections.synchronizedMap(new TreeMap<Integer, HashSet<String>>()));

	HashSet<String> SetEveryImagesName = new HashSet<String>();

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
		DataFavoris(); // On rempli aussi dans cette fonction SetEveryImagesName
		ImagesEtoiles();
		ImagesTags();
		ImagesTailles();
		ImagesCptOpen();
		this.MapImagesPoids = this.MapImagesTaille;
		this.SetEveryImagesName = new HashSet<String>(this.LimagesPATH);
		// ImagesPoids();
		ImagesCouleurs();
		// System.out.println(this.MapImagesTaille);
		// for (Integer key : MapImagesCptOpen.keySet()) {
		// System.out.println(key + " " + MapImagesCptOpen.get(key));
		// }
		// System.out.println(this.ImagesFav);
		// System.out.println("--------------------");
		// System.out.println(this.MapTags);
		// System.out.println("--------------------");
		// System.out.println(this.SetImages1Etoile);
		// System.out.println(this.SetImages2Etoile);
		// System.out.println(this.SetImages3Etoile);
		// System.out.println(this.SetImages4Etoile);
		// System.out.println(this.SetImages5Etoile);
		// System.out.println("--------------------");
		System.out.println("ROUGE" + this.SetImagesRed);
		System.out.println("BLUE" + this.SetImagesBlue);
		System.out.println("GREEN" + this.SetImagesGreen);
		System.out.println("CYAN" + this.SetImagesCyan);
		System.out.println("MAGENTA" + this.SetImagesMagenta);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		// System.out.println("TAILLE" + this.MapImagesTaille);

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
			this.LimagesPATH.add(this.Limages.get(i).path);
			if (this.Limages.get(i).favoris == true) {
				this.ImagesFav.add(this.Limages.get(i).path);
			}

		}
	}

	public void ImagesEtoiles() {
		for (int i = 0; i < this.Limages.size(); i++) {
			String temp = this.Limages.get(i).path;
			int tempstar = this.Limages.get(i).etoile;
			if (tempstar == 1) {
				this.SetImages1Etoile.add(temp);
			} else if (tempstar == 1) {
				this.SetImages1Etoile.add(temp);

			} else if (tempstar == 2) {
				this.SetImages2Etoile.add(temp);

			} else if (tempstar == 3) {
				this.SetImages3Etoile.add(temp);

			} else if (tempstar == 4) {
				this.SetImages4Etoile.add(temp);

			} else if (tempstar == 5) {
				this.SetImages5Etoile.add(temp);

			}
		}
	}

	public void ImagesCouleurs() {
		for (int i = 0; i < this.Limages.size(); i++) {
			String couleur = CouleurDominante.getDomintanteColor(this.Limages.get(i).path);
			String chemin = this.Limages.get(i).path;
			if (couleur == "Red") {
				this.SetImagesRed.add(chemin);
			}
			if (couleur == "Blue") {
				this.SetImagesBlue.add(chemin);
			}
			if (couleur == "Green") {
				this.SetImagesGreen.add(chemin);
			}
			if (couleur.equals("Cyan")) {
				this.SetImagesCyan.add(chemin);
			}
			if (couleur == "Magenta") {
				this.SetImagesMagenta.add(chemin);
			}
			if (couleur == "Orange") {
				this.SetImagesOrange.add(chemin);
			}
		}
	}

	public void ImagesTags() {
		for (int i = 0; i < this.Limages.size(); i++) {
			for (int j = 1; j < this.Limages.get(i).mots_clefs.size(); j++) {
				if (this.MapTags.containsKey(this.Limages.get(i).mots_clefs.get(j))) {
					this.MapTags.get(this.Limages.get(i).mots_clefs.get(j)).add(this.Limages.get(i).path);

				} else {
					HashSet<String> temp = new HashSet<String>();
					temp.add(this.Limages.get(i).path);
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
				this.MapImagesTaille.get(taille).add(this.Limages.get(i).path);
			} else {
				HashSet<String> temp1 = new HashSet<String>();
				temp1.add(this.Limages.get(i).path);
				this.MapImagesTaille.put(taille, temp1);
			}

		}
	}

	// public void ImagesPoids() {
	// for (int i = 0; i < this.Limages.size(); i++) {
	// ImageBI tempImagePerso = this.Limages.get(i);
	// Image tempImage = new Image("file:" + tempImagePerso.path);
	// int taille = (int) Math.round(tempImage.getWidth() * tempImage.getHeight());
	// int poids = taille * 4;
	// if (this.MapImagesPoids.containsKey(poids)) {
	// this.MapImagesPoids.get(poids).add(i);
	// } else {
	// HashSet<Integer> temp1 = new HashSet<Integer>();
	// temp1.add(i);
	// this.MapImagesPoids.put(poids, temp1);
	// }
	//
	// }
	// }

	public void ImagesCptOpen() {
		for (int i = 0; i < this.Limages.size(); i++) {
			ImageBI tempImagePerso = this.Limages.get(i);
			int tempCpt = tempImagePerso.nb_ouverture;
			if (this.MapImagesCptOpen.containsKey(tempCpt)) {
				this.MapImagesCptOpen.get(tempCpt).add(this.Limages.get(i).path);
			} else {
				HashSet<String> temp1 = new HashSet<String>();
				temp1.add(this.Limages.get(i).path);
				this.MapImagesCptOpen.put(tempCpt, temp1);
			}

		}
	}

}
