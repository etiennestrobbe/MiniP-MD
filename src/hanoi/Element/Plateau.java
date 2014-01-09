/**
 * 
 */
package hanoi.Element;

import hanoi.exception.DisqueTropGrandException;
import hanoi.exception.TourVideException;

import java.util.LinkedList;

/**
 * @author Etienne Strobbe
 * 
 */
public class Plateau {
	private int nbDisques;
	private LinkedList<Tour> tours;
	private int deplacements;

	public Plateau(int n) {
		nbDisques = n;
		tours = new LinkedList<Tour>();
		// on crée les trois tours
		for (int i = 0; i < 3; i++) {
			Tour t = new Tour(((char) ('A' + i)) + "");
			tours.add(t);
		}
		// on crée les n disques et on les places sur la 1ére tour
		for (int i = 0; i < n; i++) {
			Disque d = new Disque(i + 1);
			tours.get(0).ajouterDisqueDebut(d);
		}
		tours.get(0).estInitialise();
		tours.get(1).estInitialise();
		tours.get(2).estInitialise();

	}

	/**
	 * Permet de déplacer un disque d'une tour à l'autre<br />
	 * Renvoie une exception si le déplacement est illégal.
	 * @param source Tour source
	 * @param destination Tour de destination
	 * @throws DisqueTropGrandException Envoyée si le disque supérieur de la tour source est trop grand par rapport au disque de la tour de destination
	 * @throws TourVideException Envoyée si la tour source est vide
	 */
	public void DeplacerDisque(Tour source, Tour destination)
			throws DisqueTropGrandException, TourVideException {
		if (source.getTaille() != 0) {
			Disque tmp = source.retirerDisque();
			try {
				destination.ajouterDisque(tmp);
				System.out.println(source + " vers " + destination);
				deplacements++;
			} catch (DisqueTropGrandException e) {
				source.ajouterDisque(tmp);
				throw new DisqueTropGrandException();
			}
		} else {
			throw new TourVideException(source);
		}
	}

	/**
	 * Permet de récupérer une tour
	 * @param n Numéro
	 * @return Tour
	 */
	public Tour getTour(int n) {
		return tours.get(n);
	}

	/**
	 * Renvoie la valeur totale de déplacements effectués sur le plateau
	 * @return
	 */
	public int getDeplacements() {
		return deplacements;
	}
	
	@Override
	public String toString() {
		String plateau = "";
		// ligne de base
		String basetour = "#";
		for(int i=0; i<nbDisques; i++) {
			basetour = "#"+basetour+"#";
		}
		
		String noDisque = "|";
		for(int i=0; i<nbDisques; i++) {
			noDisque = " "+noDisque+" ";
		}
		
		for(int i=nbDisques-1; i>=0; i--) {
			String tourUn = noDisque;
			String tourDeux = noDisque;
			String tourTrois = noDisque;
			if(tours.get(0).getDisque(i) != null)
				tourUn = dessineDisque(tours.get(0).getDisque(i), nbDisques);
			if(tours.get(1).getDisque(i) != null)
				tourDeux = dessineDisque(tours.get(1).getDisque(i), nbDisques);
			if(tours.get(2).getDisque(i) != null)
				tourTrois = dessineDisque(tours.get(2).getDisque(i), nbDisques);
			plateau += tourUn+tourDeux+tourTrois+"\n";
		}
		
		
		plateau += basetour+basetour+basetour;
		return plateau;
	}
	
	private String dessineDisque(Disque d, int plusGrand) {
		String res = "-";
		for(int i = 1; i < d.getTaille(); i++) {
			res = "-"+res+"-";
		}
		for(int i = 0; i<=plusGrand-d.getTaille(); i++) {
			res = " "+res+" ";
		}
		return res;
	}
}
