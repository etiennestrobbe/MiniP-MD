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

	public Simulation(int n) {
		nbDisques = n;
		view = new HanoiView(nbDisques);
		plateau = new Plateau(nbDisques);
	}

	public void runSimulation(String mode) {
		Tour A = plateau.getTour(0);
		Tour B = plateau.getTour(1);
		Tour C = plateau.getTour(2);

		if (mode.contains("cursif"))
			HanoiRecursif(nbDisques, A, C, B);
		else if(mode.contains("cycle"))
			hanoiSens1(nbDisques, A, C , B);
		else
			HanoiIteratif(A, B, C);
		System.out.println(plateau.getDeplacements() + " déplacements");
	}

	/**
	 * Résolution des tour de Hanoï en récursif
	 * 
	 * @param nbDisques
	 * @param D
	 *            La tour de départ
	 * @param A
	 *            La tour d'arrivée
	 * @param I
	 *            La tour intermédiaire
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

	/**
	 * Résolution des tour de Hanoi en itératif
	 * 
	 * @param D
	 *            La tour de départ
	 * @param I
	 *            La tour intermédiaire
	 * @param A
	 *            La tour d'arrivée
	 * @author Jean-Christophe Isoard
	 */
	 public void HanoiIteratif(Tour D, Tour I, Tour A) {
         view.display(plateau);

         Tour tourPetitDisque = D;
         Tour deuxieme = I;
         Tour troisieme = A;

         // si le nombre de disque est impair, on doit tourner dans l'autre sens
         if (!nbDisquesPair()) {
                 deuxieme = A;
                 troisieme = I;
         }

         while (A.getTaille() != nbDisques) {
                 // deplace le petit disque
                 try {
                         plateau.DeplacerDisque(tourPetitDisque, deuxieme);
                         view.display(plateau);
                 } catch (DisqueTropGrandException | TourVideException e) {
                         e.printStackTrace();
                 }

                 Tour temp = deuxieme;
                 deuxieme = troisieme;
                 troisieme = tourPetitDisque;
                 tourPetitDisque = temp;

                 // deplace un autre disque
                 if (A.getTaille() != nbDisques)
                         deplacerDisqueEntreDeuxTours(deuxieme, troisieme);
         }
	 }
	private void hanoiSens1(int n, Tour A, Tour B, Tour C) {
		view.display(plateau);
        if (n != 0) {
        		hanoiSens2(n - 1, A, B, C);
                deplacer(A, B);
                hanoiSens2(n - 1, C, A, B);
	        }
        view.display(plateau);
	}
	
	private void hanoiSens2(int n, Tour A, Tour B, Tour C) {
		view.display(plateau);
	        if (n != 0) {
	        		hanoiSens2(n - 1, A, B, C);
	                deplacer(A, B);
	                hanoiSens1(n - 1, C, A, B);
	                deplacer(B, C);
	                hanoiSens2(n - 1, A, B, C);
	        }
	        view.display(plateau);
	}

	private boolean nbDisquesPair() {
		Integer modulo = nbDisques % 2;
		if (modulo.equals(0))
			return true;
		return false;
	}
	
	private void deplacer(Tour A, Tour B){
		try {
			plateau.DeplacerDisque(A,B);
		} catch (DisqueTropGrandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TourVideException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deplacerDisqueEntreDeuxTours(Tour A, Tour B) {
		try {
			plateau.DeplacerDisque(A, B);
		} catch (DisqueTropGrandException | TourVideException e1) {
			System.out.println(A + " vers " + B + " = Déplacement impossible");
			try {
				plateau.DeplacerDisque(B, A);
			} catch (DisqueTropGrandException | TourVideException e2) {
				System.err.print("Action impossible: ");
				if (e2 instanceof TourVideException)
					System.err
							.println("Vous avez essayé de déplacer un disque entre deux tour vides");
			}
		}
		view.display(plateau);
	}
}
