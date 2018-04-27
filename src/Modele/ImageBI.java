package Modele;

import java.util.ArrayList;

import Autre.CouleurDominante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageBI {
	CouleurDominante cd;
	public String nom;
	public ArrayList<String> mots_clefs;
	public boolean favoris;
	public int etoile;
	public String couleur;
	public String path;

	public ImageBI(String n) {
		this.path = n;
		this.nom = n.substring(7, n.length());
		this.mots_clefs = new ArrayList<>();
		this.favoris = false;
		this.etoile = 5;
		cd = new CouleurDominante(this.path);
		this.couleur = cd.getDomintanteColor(this.path);
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

	public void Set_CouleurDominante() {
		// Récupérer la couleurs avec la class CouleursDominante
	}

}
