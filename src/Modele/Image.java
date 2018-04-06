package Modele;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Image {

	String nom;
	Color Couleur;
	String resolution;
	String type;
	int note;
	boolean favoris;
	ArrayList<String> mots_clefs;
	String date;
	int poids;

	public Image (String n, String t){
		String nom = n;
		String type = t;
		this.mots_clefs = new ArrayList<>();
	}

	public void Ajout_Mots_clefs(String mc){
		mots_clefs.add(mc);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
