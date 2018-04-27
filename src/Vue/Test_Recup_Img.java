package Vue;

import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Test_Recup_Img {


	static public File Rep = new File("Images");

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

	public static File[] getImage(int dossier){
		File[] r = Rep.listFiles(); //Tableau de tous les dossiers


		File[] f = r[dossier].listFiles();
		File[] all= new File[f.length];

		for (int j=0;j<f.length;j++){ //
			all[j]=(f[j]);
		}

		return all;
	}

}
