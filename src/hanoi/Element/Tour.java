/**
 * 
 */
package hanoi.Element;

import hanoi.exception.DisqueTropGrandException;

import java.util.LinkedList;

/**
 * @author Etienne Strobbe
 * 
 */
public class Tour {
	private String name;
	private LinkedList<Disque> disques;
	private boolean initialise;
	private int taille;

	public Tour(String n) {
		name = n;
		disques = new LinkedList<Disque>();
		initialise = false;
		taille = 0;
	}

	public Disque retirerDisque() {
		taille++;
		return disques.pollLast();
	}

	private void ajoutDebut(Disque d) {
		disques.addFirst(d);
		taille--;
	}

	private void ajoutFin(Disque d) {
		disques.addLast(d);
		taille--;
	}

	public void ajouterDisqueDebut(Disque d) {
		if (!initialise) {
			ajoutDebut(d);
		}
	}

	public void ajouterDisque(Disque d) throws DisqueTropGrandException {
		if (taille != 0) {
			if (disques.peekLast().estPlusGrand(d)) {
				ajoutFin(d);
			} else {
				throw new DisqueTropGrandException();
			}
		} else {
			ajoutFin(d);
		}
	}

	public void estInitialise() {
		initialise = true;
	}

	public String toString() {
		return name;
	}

	public LinkedList<Disque> getDisques() {
		return disques;
	}

	public int getTaille() {
		return Math.abs(taille);
	}

	public boolean estVide() {
		return Math.abs(taille) == 0;
	}
}
