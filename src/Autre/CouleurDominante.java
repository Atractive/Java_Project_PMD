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
		int[] temp2 = getDominanteColorLib(s);
		System.out.println("indice de la couleur dominante dans le talbeau Colorrange"
				+ NearestColor(Color.rgb(temp2[0], temp2[1], temp2[2])));
		// System.out.println(NearestColor(Color.rgb(temp2[0], temp2[1], temp2[2])));
	}

	public int[] ColortoRGBArray(Color c) {
		int[] rgb = new int[3];
		rgb[0] = (int) (c.getRed() * 255);
		rgb[1] = (int) (c.getBlue() * 255);
		rgb[2] = (int) (c.getGreen() * 255);
		return rgb;

	}

	public int[] getDominanteColorLib(String s) {
		BufferedImage imgD = null;
		try {
			imgD = ImageIO.read(new File(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("diego");
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

	public static void main(String[] args) {
		CouleurDominante gt = new CouleurDominante("C:\\Users\\Ortiz Diego\\eclipse\\Projet\\Images\\Oasis.jpg");
	}

}
