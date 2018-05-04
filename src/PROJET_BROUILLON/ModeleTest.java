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
import java.util.Random;
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

		
		InitData(this.Limages);
//		System.out.println(this.Limages.size());
//		GetPathNames(this.Limages); // Rempli le tableau qui contient les noms de toutes les images, NECESSAIRE pour
//									// le tri.
//		DataFavoris(this.Limages);
//		ImagesEtoiles(this.Limages);
//		ImagesTags(this.Limages);
//		ImagesTailles(this.Limages);
//		ImagesCptOpen(this.Limages);
//		ImagesCouleurs(this.Limages);
//		this.MapImagesPoids = this.MapImagesTaille;
//		this.SetEveryImagesName = new HashSet<String>(this.LimagesPATH);

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
		// System.out.println("ROUGE" + this.SetImagesRed);
		// System.out.println("BLUE" + this.SetImagesBlue);
		// System.out.println("GREEN" + this.SetImagesGreen);
		// System.out.println("CYAN" + this.SetImagesCyan);
		// System.out.println("MAGENTA" + this.SetImagesMagenta);
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// System.out.println("TAILLE" + this.MapImagesTaille);

	}

	public void InitData(ArrayList<ImageBI> L) {
		GetPathNames(L); // Rempli le tableau qui contient les noms de toutes les images, NECESSAIRE pour
							// le tri.
		DataFavoris(L);
		ImagesEtoiles(L);
		ImagesTags(L);
		ImagesTailles(L);
		ImagesCptOpen(L);
		ImagesCouleurs(L);
		this.MapImagesPoids = this.MapImagesTaille;
		this.SetEveryImagesName = new HashSet<String>(this.LimagesPATH);

		

	}
	

	public void initModele(ModeleTest modele) {
		this.modele = modele;
	}

	public void chargerDonnées(String dir) {
		File[] imagesListe = new File(dir).listFiles();
		for (File file : imagesListe) {
			// System.out.println(file.toString());
			ImageBI Imagetemp = new ImageBI(file.toString());
			if (!this.Limages_loaddata.contains(Imagetemp)) {
				this.Limages_loaddata.add(Imagetemp);
			}
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

	public void GetPathNames(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			this.LimagesPATH.add(LimagesBI.get(i).path);
		}
	}

	public void DataFavoris(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			if (LimagesBI.get(i).favoris == true) {
				this.ImagesFav.add(LimagesBI.get(i).path);
			}

		}
	}

	public void ImagesEtoiles(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			String temp = LimagesBI.get(i).path;
			int tempstar = LimagesBI.get(i).etoile;
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

	public void ImagesCouleurs(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			// String couleur = CouleurDominante.getDomintanteColor(LimagesBI.get(i).path);
			Random rng = new Random();
			String couleur = CouleurDominante.colorName[rng.nextInt(CouleurDominante.colorName.length)];
			// System.out.println(couleur + " " + couleur2);
			String chemin = LimagesBI.get(i).path;
			LimagesBI.get(i).couleur = couleur;
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

	public void ImagesTags(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			for (int j = 1; j < LimagesBI.get(i).mots_clefs.size(); j++) {
				if (this.MapTags.containsKey(LimagesBI.get(i).mots_clefs.get(j))) {
					this.MapTags.get(LimagesBI.get(i).mots_clefs.get(j)).add(LimagesBI.get(i).path);

				} else {
					HashSet<String> temp = new HashSet<String>();
					temp.add(LimagesBI.get(i).path);
					this.MapTags.put(LimagesBI.get(i).mots_clefs.get(j), temp);
				}
			}
		}
	}

	public void ImagesTailles(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			ImageBI tempImagePerso = LimagesBI.get(i);
			Image tempImage = new Image("file:" + tempImagePerso.path);
			int taille = (int) Math.round(tempImage.getWidth() * tempImage.getHeight());
			if (this.MapImagesTaille.containsKey(taille)) {
				this.MapImagesTaille.get(taille).add(LimagesBI.get(i).path);
			} else {
				HashSet<String> temp1 = new HashSet<String>();
				temp1.add(LimagesBI.get(i).path);
				this.MapImagesTaille.put(taille, temp1);
			}

		}
	}

	public void ImagesCptOpen(ArrayList<ImageBI> LimagesBI) {
		for (int i = 0; i < LimagesBI.size(); i++) {
			ImageBI tempImagePerso = LimagesBI.get(i);
			int tempCpt = tempImagePerso.nb_ouverture;
			if (this.MapImagesCptOpen.containsKey(tempCpt)) {
				this.MapImagesCptOpen.get(tempCpt).add(LimagesBI.get(i).path);
			} else {
				HashSet<String> temp1 = new HashSet<String>();
				temp1.add(LimagesBI.get(i).path);
				this.MapImagesCptOpen.put(tempCpt, temp1);
			}

		}
	}

}
