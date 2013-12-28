package figureFk;

import java.awt.Color;
import java.awt.Graphics;
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
/*/		if (n > 0) {
	       int nouveauRayon = d / 2;
	       // à gauche
	       if (position != 2) // si on est pas à droite
	         drawSolutionk(drawingArea, x - nouveauRayon, y + nouveauRayon,
	             nouveauRayon, fill, n - 1, 1);
	       // à droite
	       if (position != 1) // si on est pas à gauche
	         drawSolutionk(drawingArea, x + d, y, nouveauRayon, fill, n - 1,
	             2);
	       // en haut
	       if (position != 4) // si on est pas en bas
	         drawSolutionk(drawingArea, x, y - nouveauRayon, nouveauRayon,
	             fill, n - 1, 3);
	       // en bas
	       if (position != 3) // si on est pas en haut
	         drawSolutionk(drawingArea, x + nouveauRayon, y + d,
	             nouveauRayon, fill, n - 1, 4);
	     }
/*/
		if (n > 0) {
			double teta;
			int r=d;
			teta = (2*Math.PI);
			int nouveauRayon = d / 2;
			// Ã  gauche
			if (position != 2) { // si on est pas Ã  droite
				int newX = (int)((x-nouveauRayon)-r*Math.round(Math.cos(teta)));
				int newY = (int)((y+nouveauRayon/4)-r*Math.round(Math.sin(teta)));
				drawSolutionk(drawingArea, newX, newY, nouveauRayon, fill, n - 1, 1);
			}
			// Ã  droite
			if (position != 1) { // si on est pas Ã  gauche
				int newX = (int)((x+2*nouveauRayon)+r*Math.round(Math.cos(teta)));
				int newY = (int)((y+nouveauRayon)+r*Math.round(Math.sin(teta)));
				drawSolutionk(drawingArea, newX, newY, nouveauRayon, fill, n - 1, 2);
			}
			// en haut
			if (position != 4) {// si on est pas en bas
				int newX = (int)((x)+r*Math.round(Math.cos(2*teta)));
				int newY = (int)((y-nouveauRayon)+r*Math.round(Math.sin(teta)));
				drawSolutionk(drawingArea, newX, newY, nouveauRayon, fill, n - 1, 3);
			}
			// en bas
			if (position != 3) {// si on est pas en haut
				int newX = (int)((x+nouveauRayon)-r*Math.round(Math.cos(teta)));
				int newY = (int)((y+2*nouveauRayon)+r*Math.round(Math.sin(2*teta)));
				drawSolutionk(drawingArea, newX, newY, nouveauRayon, fill, n - 1, 4);
			}
		}
/**/

		drawStar(drawingArea, x, y, d);

	}
	
	private void pivotStar(int...arg ){
		//TODO faire pivoter une etoile
	}
/**
 * Fonction private qui dessine une etoile sur l'object graphique donné en argument.
 * @param drawingArea
 * @param arg
 */
	private void drawStar(Graphics drawingArea, int... arg) {
		int x = arg[0];
		int y = arg[1];
		int d = arg[2];
		
		// on cree les coordonnees des deux pentagones
		int[] xGpoints = createCoordinatePent("BIG",x,y,d)[0];
		int[] yGpoints = createCoordinatePent("BIG",x,y,d)[1];
		int[] xPpoints = createCoordinatePent("SMALL",x,y,d)[0];
		int[] yPpoints = createCoordinatePent("SMALL",x,y,d)[1];

		// on cree les coordonnes de l'etoile
		int[] xEtoile = createCoordinateStar(xGpoints,yGpoints,xPpoints,yPpoints)[0];
		int[] yEtoile = createCoordinateStar(xGpoints,yGpoints,xPpoints,yPpoints)[1];
		
		// on dessine l'etoile
		drawingArea.setColor(Color.pink);
		drawingArea.fillPolygon(xGpoints, yGpoints, 5);
		drawingArea.setColor(Color.yellow);
		drawingArea.fillPolygon(xEtoile, yEtoile, 10);
		drawingArea.setColor(Color.gray);
		drawingArea.fillPolygon(xPpoints, yPpoints, 5);
	}
	
	/**
	 * Fonction private qui permet de calculer les coordonnées pour dessiner une étoile a partir de coordonnées de deux pentagones.
	 * @param xGpoints
	 * @param yGpoints
	 * @param xPpoints
	 * @param yPpoints
	 * @return un tableau de coordonnées
	 */
	private int[][] createCoordinateStar(int[] xGpoints ,int[] yGpoints,int[] xPpoints, int[] yPpoints){
		int [][] res = new int [2][10];
		int[] xEtoile = new int[] { xGpoints[0], xPpoints[3], xGpoints[1],
				xPpoints[4], xGpoints[2], xPpoints[0], xGpoints[3],
				xPpoints[1], xGpoints[4], xPpoints[2] };
		int[] yEtoile = new int[] { yGpoints[0], yPpoints[3], yGpoints[1],
				yPpoints[4], yGpoints[2], yPpoints[0], yGpoints[3],
				yPpoints[1], yGpoints[4], yPpoints[2] };
		res[0]=xEtoile;
		res[1]=yEtoile;
		return res;
	}
	
	/**
	 * * Fonction private qui permet de calculer les coordonnées d'un pentagone
	 * @param type
	 * @param arg
	 * @return un tableau de coordonnées
	 */
	private int[][] createCoordinatePent(String type,int... arg){
		int x = arg[0];
		int y = arg[1];
		int d = arg[2];
		int [][] res = new int[2][5];
		int xG = x + d / 2; // coordonnÃ©e x du point supÃ©rieur du grand
		// pentagone
		int yG = (type=="BIG")?y:y + d / 2 + d / 4; // coordonnÃ©e y du point supÃ©rieur du grand pentagone
		int coeff1 = (type=="BIG")?2:4;
		int coeff2 = (type=="BIG")?1:-1;

		int[] xGpoints = new int[5];
		int[] yGpoints = new int[5];
		for (int i = 0; i < 5; i++) {
			xGpoints[i] = (int) (xG - coeff2*d / coeff1 * Math.sin(i * 2 * Math.PI / 5));
			yGpoints[i] = (int) (yG + coeff2*d / coeff1 * (1 - Math.cos(i * 2 * Math.PI / 5)));
		}
		res[0] = xGpoints;
		res[1] = yGpoints;
		return res;
	}
}
