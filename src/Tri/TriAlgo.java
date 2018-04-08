package Tri;

import java.util.ArrayList;

import Modele.ImageBI;

public class TriAlgo {

	ArrayList<ImageBI> ListTri;

	public TriAlgo(ArrayList lt) {
		this.ListTri = lt;
	}

	//Trier les images favoris ou non
	public ArrayList<ImageBI> TriFavoris(boolean choix) {
		ArrayList<ImageBI> ListFav = new ArrayList<>();
		for (int i=0; i<this.ListTri.size(); i++){
			if (this.ListTri.get(i).favoris == choix){
				ListFav.add(this.ListTri.get(i));
			}
		}
		return ListFav;
	}

	//Trier avec juste les images corespondante au nombre d'étoile
	public  ArrayList<ImageBI> ChoixEtoile(int e){
		ArrayList<ImageBI> ListEtoile = new ArrayList<>();
		for (int i=0; i<this.ListTri.size(); i++){
			if (this.ListTri.get(i).etoile == e){
				ListEtoile.add(this.ListTri.get(i));
			}
		}
		return ListEtoile;
	}

	//Trier les images dans l'ordre de leurs étoiles
	public ArrayList<ImageBI> TriEtoile() {
		ArrayList<ImageBI> ListEtoile = new ArrayList<>();
		for (int i=0; i<this.ListTri.size(); i++){
			for (int e=5; e>0; e--){
				if (this.ListTri.get(i).etoile == e){
					ListEtoile.add(this.ListTri.get(i));
				}
			}
		}
		return ListEtoile;
	}

}
