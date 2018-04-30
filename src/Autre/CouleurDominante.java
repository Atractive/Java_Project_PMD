package Autre;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.androidpit.colorthief.ColorThief;
import javafx.scene.paint.Color;

public class CouleurDominante {
	public static Color[] colorRange = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.CYAN, Color.MAGENTA };
	public static String[] colorName = new String[] { "Red", "Blue", "Green", "Orange", "Cyan", "Magenta" };

	public CouleurDominante(String s) {
	}

	public static int[] ColortoRGBArray(Color c) {
		int[] rgb = new int[3];
		rgb[0] = (int) (c.getRed() * 255);
		rgb[1] = (int) (c.getBlue() * 255);
		rgb[2] = (int) (c.getGreen() * 255);
		return rgb;

	}

	public static String getDomintanteColor(String s) {
		int[] temp = getDominanteColorLib(s);
		return NearestColor3(Color.rgb(temp[0], temp[1], temp[2]));
	}

	public static int[] getDominanteColorLib(String s) {
		BufferedImage imgD = null;
		try {
			imgD = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] resultD = ColorThief.getColor(imgD); // lib
		return resultD;

	}

	double colorDistance(Color c1, Color c2) // distance metric dans un espace de couleur multidimensionel RGB,
												// dimension euclidienne advanced, version S.OF
	{
		int red1 = (int) c1.getRed();
		int red2 = (int) c2.getRed();
		int rmean = (red1 + red2) >> 1;
		int r = red1 - red2;
		int g = (int) (c1.getGreen() - c2.getGreen());
		int b = (int) (c1.getBlue() - c2.getBlue());
		return Math.sqrt((((512 + rmean) * r * r) >> 8) + 4 * g * g + (((767 - rmean) * b * b) >> 8));
	}

	public int NearestColor(Color rgb) { // Récupère la couleur la plus proche dans un espace de couleur
											// mutlidimensionel RGB version Dieog
		double min = (int) colorDistance(rgb, (colorRange[0]));
		int index = 0;
		for (int i = 0; i < colorRange.length; i++) {
			double temp = colorDistance(rgb, (colorRange[i]));
			if (min > temp) {
				min = temp;
				index = i;
			}
		}
		return index;
	}

	public static String NearestColor3(Color rgb) { // Utilisation des méthodes de LAB pour convertir RBG -> LAB puis
													// récuperer
		// la
		// différence entre 2 couleurs
		int[] Rlab1 = ColortoRGBArray(rgb);
		int[] Rlab2 = ColortoRGBArray(colorRange[0]);
		LAB lab1 = LAB.fromRGB(Rlab1[0], Rlab1[1], Rlab1[2], 1);
		LAB lab2 = LAB.fromRGB(Rlab2[0], Rlab2[1], Rlab2[2], 1);
		double diff = lab1.distance(lab2);
		int index = 0;
		for (int i = 1; i < colorRange.length; i++) {
			int[] Rlabtemp = ColortoRGBArray(colorRange[i]);
			LAB labtemp = LAB.fromRGB(Rlabtemp[0], Rlabtemp[1], Rlabtemp[2], 1);
			double temp = labtemp.distance(lab1);
			if (diff > temp) {
				diff = temp;
				index = i;
			}
		}
		return colorName[index];
	}

}
