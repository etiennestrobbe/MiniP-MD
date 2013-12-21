/**
 * Classe héritant de AbstractSolution.
 * Cette classe permet de dessiner une famille de cercle Fk récursivement.
 * La famille est définie comme suit: on obtient Fk+1 à partir de Fk en ajoutant à chaque 
 * plus petit cercle de c de Fk, 2 nouveaux cercles:
 * 		- tangents à c
 * 		- de diamètre la moitié du diamètre de c
 * 		- et tels que le centre de c et d'un des nouveaux cercles sont sur une verticale
 * 		  ou une horizontale
 */
package figureFk;

import java.awt.Graphics;

import drawSolution.AbstractSolution;

/**
 * @author Etienne STROBBE
 * @version 21/12/2013
 * 
 */
public class FkSolution extends AbstractSolution {
	
	/**
	 * Constructeur de la classe.
	 * Le paramètre profondeur représente la profondeur de récursivité du dessin
	 * @param profondeur
	 */

	public FkSolution(int profondeur) {
		super(profondeur);
	}

	/**
	 * Méthode récursive pour dessiner la famille Fk de la classe.
	 * On dessine deux petit cercles tangents au cercle actuel tout
	 * en conservant les propriétés de la famille Fk.
	 * 
	 * @param drawingArea:object graphique dans lequel on dessine
	 * @param arg : liste d'arguments
	 * 
	 * @see drawSolution.AbstractSolution#drawSolutionk(java.awt.Graphics,
	 * int[])
	 */
	@Override
	public void drawSolutionk(Graphics drawingArea, int... arg) {
		if (arg[3] == 0) {
			drawingArea.drawOval(arg[0], arg[1], arg[2], arg[2]);
		} else {
			drawingArea.fillOval(arg[0], arg[1], arg[2], arg[2]);
		}

		if (arg[4] > 0) {
			drawSolutionk(drawingArea, arg[0] + arg[2], arg[1] + arg[2] / 4,
					arg[2] / 2, arg[3], arg[4] - 1);
			drawSolutionk(drawingArea, arg[0] + arg[2] / 4, arg[1] + arg[2],
					arg[2] / 2, arg[3], arg[4] - 1);

		}

	}

}
