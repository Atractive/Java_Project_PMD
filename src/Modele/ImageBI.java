package Modele;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageBI implements Serializable {
	public String nom;
	public ArrayList<String> mots_clefs;
	public boolean favoris;
	public int etoile;
	public String path;
	public int nb_ouverture;

	public ImageBI(String n) {
		this.path = n;
		this.nom = n.substring(7, n.length());
		this.mots_clefs = new ArrayList<>();
		this.favoris = false;
		this.etoile = 5;
		this.nb_ouverture = 0;
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

}
