package figureFk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;

import drawSolution.AbstractSolution;

public class F4kSolution extends AbstractSolution {

	public F4kSolution(int profondeur) {
		super(profondeur);
	}

	public F4kSolution(int profondeur, int fill) {
		super(profondeur, fill);
	}

	@Override
	public void drawSolutionk(Graphics drawingArea, int... arg) {
		int x = arg[0];
		int y = arg[1];
		int d = arg[2];
		int fill = arg[3];
		int n = arg[4];
		int position;
		try {
			position = arg[5];
		} catch (ArrayIndexOutOfBoundsException e) {
			position = 0;
		}
		drawingArea.setColor(Color.pink);
		drawingArea.fillPolygon(createStar(x, y, n+2, d, 0));
		drawingArea.setColor(Color.black);
		drawingArea.drawRect(x, y, d, d);
		if (n > 0) {
			if(position !=3){ // si on est pas en BD
				drawSolutionk(drawingArea, x - d/2, y-d/2,d/2, fill, n - 1, 1);
			}
			if(position !=4){// si on est pas en HD
				drawSolutionk(drawingArea, x - d/2, y + d, d/2, fill, n - 1,2);
			}
			if(position !=1){// si on est pas en HG
				drawSolutionk(drawingArea, x+d, y+ d, d/2,fill, n - 1, 3);
			}
			if(position !=2){// si on est pas en BG
				drawSolutionk(drawingArea, x + d, y - d/2,d/2, fill, n - 1, 4);
			}
		}

		
	}

	/**
	 * Fonction private qui permet de calculer les coordonn�es pour dessiner une
	 * �toile a partir de coordonn�es de deux pentagones.
	 * 
	 * @param xGpoints
	 * @param yGpoints
	 * @param xPpoints
	 * @param yPpoints
	 * @return un tableau de coordonn�es
	 */
	private Polygon createStar(int x, int y, int n, int d, double teta) {
		Polygon grandP = createRegularConvexePolygon(x, y, n, d, teta);
		Polygon petitP = createRegularConvexePolygon(x + d / 4, y + d / 4, n,
				d / 2, teta + Math.PI / n);
		Polygon star = new Polygon();

		for (int i = 0; i < n; i++) {
			star.addPoint(grandP.xpoints[i], grandP.ypoints[i]);
			star.addPoint(petitP.xpoints[i], petitP.ypoints[i]);
		}

		return star;
	}

	/**
	 * * Fonction private qui permet de calculer les coordonn�es d'un pentagone
	 * 
	 * @param type
	 * @param arg
	 * @return un tableau de coordonn�es
	 */
	private Polygon createRegularConvexePolygon(int x, int y, int n, int d,
			double teta) {
		int[] xs = new int[n];
		int[] ys = new int[n];
		x = x + d / 2;

		for (int i = 0; i < n; i++) {
			xs[i] = (int) (x - (d/2) * Math.sin(teta + (2 * i * Math.PI / n)));
			ys[i] = (int) (y + (d/2) * (1 - Math.cos(teta + (2 * i * Math.PI / n))));

		}
		return new Polygon(xs, ys, n);
	}
}
