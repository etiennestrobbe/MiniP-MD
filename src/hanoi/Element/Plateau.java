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
	
	private LinkedList<Tour> tours;
	
	public Plateau(int nbDisque){
		tours = new LinkedList<Tour>();
		for(int i=0;i<3;i++){
			Tour t = new Tour(((char) ('A'+i))+"");
			tours.add(t);
		}
		for(int i=0;i<nbDisque;i++){
			Disque d = new Disque(i+1);
			tours.get(0).ajouterDisqueDebut(d);
		}
		tours.get(0).estInitialise();
		tours.get(1).estInitialise();
		tours.get(2).estInitialise();

	}
	
	public void DeplacerDisque(Tour source, Tour destination) throws DisqueTropGrandException, TourVideException {
		if(source.getTaille() != 0) {
			Disque tmp = source.retirerDisque();
			try {
				destination.ajouterDisque(tmp);
				System.out.println(source+" vers "+destination);
			} catch (DisqueTropGrandException e) {
				source.ajouterDisque(tmp);
				throw new DisqueTropGrandException();
			}
		} else {
			throw new TourVideException(source);
		}
	}
	
	public Tour getTour(int n){
		return tours.get(n);
	}
}
