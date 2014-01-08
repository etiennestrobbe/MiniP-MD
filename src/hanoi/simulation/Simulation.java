/**
 * 
 */
package hanoi.simulation;

import hanoi.Element.*;
import hanoi.exception.DisqueTropGrandException;
import hanoi.exception.TourVideException;
import hanoi.view.HanoiView;

/**
 * @author Etienne Strobbe (Récursif) et Jean-Christophe Isoard (Itératif)
 * 
 */
public class Simulation {
	private Plateau plateau;
	private int nbDisques;
	private HanoiView view;

	public Simulation(int nbDisques) {
		view = new HanoiView(nbDisques);
		plateau = new Plateau(nbDisques);
		this.nbDisques = nbDisques;
	}

	public void runSimulation(String mode) {
		Tour A = plateau.getTour(0);
		Tour B = plateau.getTour(1);
		Tour C = plateau.getTour(2);

		if (mode.contains("cursif"))
			HanoiRecursif(nbDisques, A, C, B);
		else if(mode.contains("cycle"))
			hanoiSensHoraire(nbDisques, A, B , C);
		else
			HanoiIteratif(A, B, C);
	}

	/**
	 * 
	 * @param nbDisques
	 * @param D
	 * @param A
	 * @param I
	 * @author Etienne Strobbe
	 */
	private void HanoiRecursif(int nbDisques, Tour D, Tour A, Tour I) {
		if (nbDisques != 0) {
			HanoiRecursif(nbDisques - 1, D, I, A);

			try {
				// déplace D vers A
				plateau.DeplacerDisque(D, A);
				view.display(plateau);
			} catch (Exception e) {
				System.err.println(e);
			}

			HanoiRecursif(nbDisques - 1, I, A, D);
		}
	}
	
	private void hanoiSensHoraire(int nbDisques, Tour A, Tour B, Tour C){
		if (nbDisques != 0) {
		hanoiSensHoraire(nbDisques-1,A,C,B);
		// move disc n  de A->B
		deplacerDisqueEntreDeuxTours(A,B);
		hanoiSensAntiHoraire(nbDisques-1,C,A,B);
		// move disc n  de B->C
		deplacerDisqueEntreDeuxTours(B,C);
		hanoiSensHoraire(nbDisques-1,A,C,B);
		}
	}
	
	private void hanoiSensAntiHoraire(int nbDisques, Tour A, Tour B, Tour C){
		if (nbDisques != 0) {
		hanoiSensHoraire(nbDisques-1,A,B,C);
		// move disc n de D->A
		deplacerDisqueEntreDeuxTours(A,C);
		hanoiSensHoraire(nbDisques-1,B,C,A);
		}
	}

	public void HanoiIteratif(Tour A, Tour B, Tour C) {
		view.display(plateau);
		if (!nbDisquesPair())
			deplacerDisqueEntreDeuxTours(A, C);
		while (C.getTaille() != nbDisques) {
			if (nbDisquesPair()) {
				deplacerDisqueEntreDeuxTours(A, B);
				deplacerDisqueEntreDeuxTours(A, C);
				deplacerDisqueEntreDeuxTours(B, C);
			} else {
				deplacerDisqueEntreDeuxTours(A, B);
				deplacerDisqueEntreDeuxTours(C, B);
				deplacerDisqueEntreDeuxTours(A, C);
			}
		}
	}

	private boolean nbDisquesPair() {
		Integer modulo = nbDisques % 2;
		if (modulo.equals(0))
			return true;
		return false;
	}

	public void deplacerDisqueEntreDeuxTours(Tour A, Tour B) {
		try {
			plateau.DeplacerDisque(A, B);
		} catch (DisqueTropGrandException | TourVideException e1) {
			try {
				plateau.DeplacerDisque(B, A);
			} catch (DisqueTropGrandException | TourVideException e2) {
				System.err.println("Something is wrong ...");
				if (e2 instanceof TourVideException)
					System.err
							.println("Vous avez essayé de déplacer un disque entre deux tour vides");
			}
		}
		view.display(plateau);
	}
}
