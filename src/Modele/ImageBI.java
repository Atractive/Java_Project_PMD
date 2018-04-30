package Modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;

public class ImageBI implements Serializable {
	public String nom;
	public ArrayList<String> mots_clefs;
	public String tags;
	public boolean favoris;
	public int etoile;
	public String path;
	public int nb_ouverture;
	public String couleur;
	
	public ImageBI(String n) {
		this.tags = new String();
		this.path = n;
		this.nom = n.substring(7, n.length());
		this.mots_clefs = new ArrayList<>();
		this.favoris = false;
		this.etoile = 3;
		this.nb_ouverture = 0;
		this.couleur= "";
	}

	public void Add_MotsClefs(String mc) {
		mots_clefs.add(mc);
	}

	public void Set_Favoris() {
		if (this.favoris) {
			this.favoris = false;
		} else {
			this.favoris = true;
		}
	}

	public void Set_Etoile(int nbe) {
		this.etoile = nbe;
	}

	public void Increase_nbOuverture() {
		this.nb_ouverture++;
	}

	public void Set_Tags(String s) {
		ArrayList<String> T = new ArrayList<String>(Arrays.asList(s.split("#")));
		this.mots_clefs = T;
	}
	
	public String show_Tags(ArrayList<String> t) {
		String s = "";
		for (int i = 1 ; i < t.size(); i++ ) {
			s += "#" + t.get(i) + " ";
		}
		return s;
	}
	public String toString() {
		return this.path + "";
	}
	


}
