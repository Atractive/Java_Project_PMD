package PROJET_BROUILLON;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Modele.ImageBI;

public class ModeleTest {

	ArrayList<ImageBI> Limages = new ArrayList<ImageBI>();
	ArrayList<ImageBI> Limages_unserialized = new ArrayList<ImageBI>();
	ArrayList<ImageBI> Limages_loaddata = new ArrayList<ImageBI>();

	private ModeleTest modele;

	public ModeleTest(String s) throws ClassNotFoundException, IOException {
		File temp = new File("images.dat");
		File[] imagesListe = new File(s).listFiles();
		UnserializeData(temp);
		if (temp.exists() && imagesListe.length == Limages_unserialized.size()) {
			this.Limages = Limages_unserialized;
		} else {
			chargerDonnées(s);
			this.Limages = this.Limages_loaddata;
		}

	}

	public void initModele(ModeleTest modele) {
		this.modele = modele;
	}

	public void chargerDonnées(String dir) {
		File[] imagesListe = new File(dir).listFiles();
		for (File file : imagesListe) {
			// System.out.println(file);
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

	public void UnserializeData(File fichier) throws IOException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream(fichier);
		ObjectInputStream ois = new ObjectInputStream(fis);

		this.Limages_unserialized = (ArrayList<ImageBI>) ois.readObject();
		ois.close();
		fis.close();
	}

}
