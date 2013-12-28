package drawSolution;

import java.awt.*; // Pour  Graphics, Frame
import java.awt.event.WindowAdapter; // Pour fermer
import java.awt.event.WindowEvent; // Pour fermer

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class AbstractSolution extends Frame {
	private static final int WIDTH = 800; // taille initiale de la frame
	private static final int HEIGHT = 600;
	private int profondeur; // profondeur de récursivité
	private int fill; // 1=remplir les figures
	private double dx;
	private double dy;

	public AbstractSolution(int profondeur) {
		this.setSize(WIDTH, HEIGHT);
		dx = dy = 0;
		fill = 0;
		this.setVisible(true);
		this.setBackground(Color.white);
		this.profondeur = profondeur;
		addWindowListener(new WindowHandler()); // pour fermer
		setTitle("Dessins recursifs au niveau : " + profondeur);
	}
	
	public AbstractSolution(int profondeur, int fill) {
		this(profondeur);
		this.fill = fill;
	}
	
	public AbstractSolution(double dx, double dy, int profondeur) {
		this(profondeur);
		this.dx = dx;
		this.dy = dy;
	}

	public AbstractSolution(double dx, double dy, int profondeur, int fill) {
		this(dx,dy,profondeur);
		this.fill = fill;
	}
	
	private class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int frameHeight = getSize().height+10; // taille de la fenêtre avec les bordures
		int frameWidth = getSize().width;
		g2d.setColor(Color.black); // La couleur avec laquelle on va dessiner
		g2d.translate(dx*frameWidth, dy*frameHeight); //pour déplacer l'origine si besoin

		// mettre ici un appel de drawSolutionk avec une liste d'arguments
		// adaptés, par exemple :
		int rayonOriginel = frameWidth/4;
		drawSolutionk(g2d, frameWidth / 2 - rayonOriginel/2, frameHeight / 2 - rayonOriginel/2, frameWidth / 8, fill,
				profondeur-1);
	}

	/**
	 * drawSolutionk Produit un dessin récursif <br />
	 * Parametres: <br />
	 * Graphics drawingArea : L'objet graphique dans lequel on dessine<br />
	 * int ... arg : les coordonnées, longueur et autres si besoin, et
	 * profondeur de récursivité
	 **/
	public abstract void drawSolutionk(Graphics drawingArea, int... arg);

}
