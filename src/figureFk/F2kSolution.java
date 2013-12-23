/**
 * 
 */
package figureFk;

import java.awt.Graphics;

import drawSolution.AbstractSolution;

/**
 * @author Jean-Christophe Isoard
 * 
 */
public class F2kSolution extends AbstractSolution {

	/**
	 * @param profondeur
	 */
	public F2kSolution(int profondeur) {
		super(profondeur);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see drawSolution.AbstractSolution#drawSolutionk(java.awt.Graphics,
	 * int[])
	 */
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
		} catch(ArrayIndexOutOfBoundsException e) {
			position = 0;
		}

		if (fill == 0) {
			drawingArea.drawOval(x, y, rayon, rayon);
		} else {
			drawingArea.fillOval(x, y, rayon, rayon);
		}

		if (n > 0) {
			int nouveauRayon = rayon / 2;
			// cercle à gauche
			if (position != 2) // si on est pas à droite
				drawSolutionk(drawingArea, x - nouveauRayon, y + nouveauRayon
						/ 2, nouveauRayon, fill, n - 1, 1);
			// cercle à droite
			if (position != 1) // si on est pas à gauche
			drawSolutionk(drawingArea, x + rayon, y + nouveauRayon / 2,
					nouveauRayon, fill, n - 1, 2);
			// cercle en haut
			if (position != 4) // si on est pas en bas
			drawSolutionk(drawingArea, x + nouveauRayon / 2, y - nouveauRayon,
					nouveauRayon, fill, n - 1, 3);
			// cercle en bas
			if (position != 3) // si on est pas en haut
			drawSolutionk(drawingArea, x + nouveauRayon / 2, y + rayon,
					nouveauRayon, fill, n - 1, 4);
		}
	}
}
