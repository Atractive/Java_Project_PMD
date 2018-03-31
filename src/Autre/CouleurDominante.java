package Autre;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.androidpit.colorthief.ColorThief;
import javafx.scene.paint.Color;

public class CouleurDominante {
	public static Color[] colorRange = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
			Color.CYAN, Color.RED, Color.MAGENTA, Color.BROWN, Color.WHITE, Color.BLACK };

	public CouleurDominante(String s) {
		int[] temp2 = new int[3];
		BufferedImage imgD = null;
		try {
			imgD = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int[] resultD = ColorThief.getColor(imgD);

		temp2[0] = resultD[0];
		temp2[1] = resultD[1];
		temp2[2] = resultD[2];
		System.out.println("R: " + temp2[0] + "\nG: " + temp2[1] + "\nB: " + temp2[2]);
		System.out.println("indice dans le talbeau ColorRange avec version Diego : " + getNearestColor(temp2));
		System.out.println();
		System.out.println("indice dans le talbeau ColorRange avec version S.OF: "
				+ NearestColor(Color.rgb(temp2[0], temp2[1], temp2[2])));
		// int[] temp = (DC(s));
		// System.out.println(temp[0] + " " + temp[1] + " " + temp[2]);
		// System.out.println("Diego" + colorRange[getNearestColor(temp)]);
	}

	public int[] getRGB(Color c) {
		int[] rgb = new int[3];
		rgb[0] = (int) (c.getRed() * 255);
		rgb[1] = (int) (c.getBlue() * 255);
		rgb[2] = (int) (c.getGreen() * 255);
		return rgb;

	}

	public int[] getDominanteColor(String s) { // Fonction obsolète, remplacé par une lib.
		BufferedImage img = null;
		File f = null;

		try {
			f = new File(s);
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
		}

		int width = img.getWidth();
		int height = img.getHeight();
		int p, r, g, b;
		int t = width * height;
		r = g = b = 0;
		int[] rgb = new int[] { r, g, b };

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				p = img.getRGB(i, j);
				rgb[0] += (p >> 16) & 0xff;
				rgb[1] += (p >> 8) & 0xff;
				rgb[2] += p & 0xff;

			}
		}
		rgb[0] /= t;
		rgb[1] /= t;
		rgb[2] /= t;

		return rgb;
	}

	public int GetDistance(int[] colorA, int[] colorB) { // distance metric dans un espace de couleur multidimensionel
															// RGB, dimension
		// euclidienne, Version diégo
		int redDifference;
		int greenDifference;
		int blueDifference;

		redDifference = colorA[0] - colorB[0];
		greenDifference = colorA[1] - colorB[1];
		blueDifference = colorA[2] - colorB[2];

		return (int) Math.sqrt(
				redDifference * redDifference + greenDifference * greenDifference + blueDifference * blueDifference);
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

	public int getNearestColor(int[] rgb) {// Récupère la couleur la plus proche dans un espace de couleur
											// mutlidimensionel RGB version S.OF
		int min = GetDistance(rgb, getRGB(colorRange[0]));
		int index = 0;
		for (int i = 0; i < colorRange.length; i++) {
			int temp = GetDistance(rgb, getRGB(colorRange[i]));
			if (min > temp) {
				min = temp;
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		CouleurDominante gt = new CouleurDominante("C:\\Users\\Ortiz Diego\\eclipse\\Projet\\Images\\Oasis.jpg");
	}

}
