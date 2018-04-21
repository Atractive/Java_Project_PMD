package Modele;

import java.util.ArrayList;

import Autre.CouleurDominante;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageBI {

	public String nom;
	public ArrayList<String> mots_clefs;
	public boolean favoris;
	public String etoile;
	public Color couleur;

	public ImageBI (String n){
		this.nom = n;
		this.mots_clefs = new ArrayList<>();
		this.favoris = false;
		this.etoile = "0";
		this.couleur = Color.WHITE;
	}

	public void Add_MotsClefs(String mc){
		mots_clefs.add(mc);
	}

	public void Set_Favoris(){
		if (this.favoris){
			this.favoris = false;
		} else {
			this.favoris = true;
		}
	}

	public void Set_Etoile(String nbe){
		this.etoile = nbe;
	}

	public void Set_CouleurDominante(){
		//Récupérer la couleurs avec la class CouleursDominante
	}

}
