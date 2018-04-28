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
	private ModeleTest modele;
	
	
	public ModeleTest(String s) throws ClassNotFoundException, IOException {
		File temp = new File("images.dat");
		if (temp.exists()) {
			UnserializeData(temp);			
		}
		else{
			chargerDonnées(s);
		}

	}

	public void initModele(ModeleTest modele) {
		this.modele = modele;
	}

	public void chargerDonnées(String dir) {
		ArrayList<ImageBI> temp = new ArrayList<ImageBI>();
		File[] imagesListe = new File(dir).listFiles();
		this.Limages = new ArrayList<>();
		for (File file : imagesListe) {
			this.Limages.add(new ImageBI(file.toString()));
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

		this.Limages = (ArrayList<ImageBI>) ois.readObject();
		ois.close();
		fis.close();
	}

}
