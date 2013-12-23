package hanoi.view;

import hanoi.Element.Disque;
import hanoi.Element.Plateau;
import hanoi.Element.Tour;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HanoiView extends JFrame {
	private Dimension dims;
	private TowersView towersView;
	private int nbDiscs;
	
	public HanoiView(int nbDiscs) {
		this.nbDiscs = nbDiscs;
		
		towersView = new TowersView();
		this.add(towersView);
		
		this.pack();
		this.addWindowListener(new WindowHandler());
	}
	
	private class WindowHandler extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	
	public void display(Plateau p) {
		towersView.preparePaint();
		towersView.blank();
		if (!this.isVisible())
			this.setVisible(true);
		
		towersView.drawPlateau(Color.red);
		for(int tour=0; tour<3; tour++) {
			LinkedList<Disque> discs = p.getTour(tour).getDisques();
			int e = 0;
			for(Disque disc:discs) {
				towersView.drawDisc(tour, e++, disc.getTaille(), Color.blue);
			}
		}
		towersView.repaint();
		
	}
	
	private class TowersView extends JPanel {
		private static final int DEFAULT_BASE_WIDTH = 400;
		private static final int BASE_HEIGHT = 20;
		private static final int TOWER_WIDTH = 5;
		private static final int MARGINS = 20;
		private static final int DISC_HEIGHT = 10;
		private Dimension size;
		private Image towersImage;
		private int spacebetweentowers;
		private Graphics g;
		private int towerheight;
		private int basewidth;
		private int firsttower;
		
		/**
		 * Create a new FieldView component.
		 */
		public TowersView() {
			size = new Dimension(0, 0);
		}

		public void blank() {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		/**
		 * Tell the GUI manager how big we would like to be.
		 */
		public Dimension getPreferredSize() {
			return new Dimension(DEFAULT_BASE_WIDTH, nbDiscs*(DISC_HEIGHT+1)+BASE_HEIGHT+MARGINS);
		}

		/**
		 * Prepare for a new round of painting. Since the component may be
		 * resized, compute the scaling factor again.
		 */
		public void preparePaint() {
			if (!size.equals(getSize())) { // if the size has changed...
				size = getSize();
				towersImage = towersView.createImage(size.width, size.height);
				g = towersImage.getGraphics();
				
				towerheight = (int) (size.getHeight()-(BASE_HEIGHT+MARGINS));
				basewidth = (int) (size.getWidth()-MARGINS*2);

				firsttower = (basewidth-TOWER_WIDTH*3)/8;
				spacebetweentowers = (basewidth-TOWER_WIDTH*3)/4;
			}
		}

		/**
		 * Paint on grid location on this field in a given color.
		 */
		public void drawPlateau(Color color) {
			g.setColor(color);
			int x = MARGINS;
			int y = MARGINS;
			// draw base 
			g.fillRect(x, y + towerheight, basewidth, BASE_HEIGHT);
			// draw towers
			for(int i=0; i<3; i++) {
				if(i==0) x += firsttower;
				else x += spacebetweentowers+firsttower;
				g.fillRect(x, y, TOWER_WIDTH, towerheight);
				x+=TOWER_WIDTH;
			}
		}
		
		public void drawDisc(int tour, int etage, int size, Color color) {
			g.setColor(color);
			int x = MARGINS;
			int y = towerheight;
			
			int discsize = size*(spacebetweentowers/nbDiscs);
			
			if(tour==0) {
				x+=firsttower+tour*TOWER_WIDTH-discsize/2+TOWER_WIDTH/2;
			} else {
				x+=(tour+1)*spacebetweentowers+tour*TOWER_WIDTH-discsize/2+TOWER_WIDTH/2;
			}
			if(tour==2) {
				x+=firsttower;
			}
			y -= (etage-1)*(DISC_HEIGHT+1);
			
			//g.fillRect(x, y, discsize, DISC_HEIGHT);
			
			int[] xpts = new int[] { x + DISC_HEIGHT / 2, x,
					x + DISC_HEIGHT / 2, x + discsize - DISC_HEIGHT / 2,
					x + discsize, x + discsize - DISC_HEIGHT / 2 };
			int[] ypts = new int[] { y, y + DISC_HEIGHT / 2, y + DISC_HEIGHT,
					y + DISC_HEIGHT, y + DISC_HEIGHT / 2, y };
			g.fillPolygon(xpts, ypts, 6);
		}

		/**
		 * The field view component needs to be redisplayed. Copy the internal
		 * image to screen.
		 */
		public void paintComponent(Graphics g) {
			if (towersImage != null) {
				Dimension currentSize = getSize();
				if (size.equals(currentSize)) {
					g.drawImage(towersImage, 0, 0, null);
				} else {
					// Rescale the previous image.
					g.drawImage(towersImage, 0, 0, currentSize.width,
							currentSize.height, null);
				}
			}
		}
	}
}
