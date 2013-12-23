package hanoi.TraitementItératif;

import hanoi.Element.Plateau;
import hanoi.view.HanoiView;

/**
 * @author Jean-Christophe Isoard
 *
 */
public class Simulation {
	private Plateau plateau;
	private int nbDisques;
	private HanoiView view;
	
	public Simulation(int nbDisques){
		view = new HanoiView(nbDisques);
		plateau = new Plateau(nbDisques);
		this.nbDisques = nbDisques;
	}
	
	public void Hanoi() {
		
		
	}
}
