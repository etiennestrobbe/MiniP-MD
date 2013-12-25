package figureFk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;

import drawSolution.AbstractSolution;

public class F4kSolution extends AbstractSolution {

	public F4kSolution(int profondeur) {
		super(profondeur);
		// TODO Auto-generated constructor stub
	}

	public F4kSolution(int profondeur, int fill) {
		super(profondeur, fill);
		// TODO Auto-generated constructor stub
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

		// dessin d'une étoile
		// Grand pentagone
		int xG = x+d/2; // coordonnée x du point supérieur du grand
							// pentagone
		int yG = y; // coordonnée y du point supérieur du grand pentagone
		int[] xGpoints = new int[5];
		int[] yGpoints = new int[5];
		for (int i = 0; i < 5; i++) {
			xGpoints[i] = (int) (xG - d / 2 * Math.sin(i * 2 * Math.PI / 5));
			yGpoints[i] = (int) (yG + d / 2
					* (1 - Math.cos(i * 2 * Math.PI / 5)));
		}

		// Petit pentagone
		int xP = xG; // x du petit pentagone
		int yP = y + d / 2 + d / 4; // y du petit pentagone
		int[] xPpoints = new int[5];
		int[] yPpoints = new int[5];
		for (int i = 0; i < 5; i++) {
			xPpoints[i] = (int) (xP + d / 4 * Math.sin(i * 2 * Math.PI / 5));
			yPpoints[i] = (int) (yP - d / 4
					* (1 - Math.cos(i * 2 * Math.PI / 5)));
		}

		// Etoile (on relie les points)
		int[] xEtoile = new int[] { xGpoints[0], xPpoints[3], xGpoints[1],
				xPpoints[4], xGpoints[2], xPpoints[0], xGpoints[3],
				xPpoints[1], xGpoints[4], xPpoints[2]

		};
		int[] yEtoile = new int[] { yGpoints[0], yPpoints[3], yGpoints[1],
				yPpoints[4], yGpoints[2], yPpoints[0], yGpoints[3],
				yPpoints[1], yGpoints[4], yPpoints[2]

		};

		//drawingArea.drawPolygon(xGpoints, yGpoints, 5);
		//drawingArea.drawPolygon(xPpoints, yPpoints, 5);
		drawingArea.drawPolygon(xEtoile, yEtoile, 10);
		
		if(n>0) {
			drawSolutionk(drawingArea, x,y,d/2,fill,n-1);
		}
	}
}
