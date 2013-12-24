package figureFk;

import java.awt.Color;
import java.awt.Graphics;

import drawSolution.AbstractSolution;

public class F3kSolution extends AbstractSolution {

	public F3kSolution(int profondeur) {
		super(profondeur);
		// TODO Auto-generated constructor stub
	}

	public F3kSolution(int profondeur, int fill) {
		super(profondeur, fill);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawSolutionk(Graphics drawingArea, int... arg) {
		int x = arg[0];
		int y = arg[1];
		int rayon = arg[2];
		int fill = arg[3];
		int n = arg[4];
		int position;
		try {
			position = arg[5];
		} catch (ArrayIndexOutOfBoundsException e) {
			position = 0;
		}

		if (fill != 0) {
			drawingArea.setColor(Color.green);
			drawingArea.fillRect(x, y, rayon, rayon);
			drawingArea.setColor(Color.black);
		}
		drawingArea.drawRect(x, y, rayon, rayon);

		if (n > 0) {
			int nouveauRayon = rayon / 2;
			// à gauche
			if (position != 2) // si on est pas à droite
				drawSolutionk(drawingArea, x - nouveauRayon, y + nouveauRayon,
						nouveauRayon, fill, n - 1, 1);
			// à droite
			if (position != 1) // si on est pas à gauche
				drawSolutionk(drawingArea, x + rayon, y,
						nouveauRayon, fill, n - 1, 2);
			// en haut
			if (position != 4) // si on est pas en bas
				drawSolutionk(drawingArea, x, y
						- nouveauRayon, nouveauRayon, fill, n - 1, 3);
			// en bas
			if (position != 3) // si on est pas en haut
				drawSolutionk(drawingArea, x + nouveauRayon, y + rayon,
						nouveauRayon, fill, n - 1, 4);
		}
	}
}
