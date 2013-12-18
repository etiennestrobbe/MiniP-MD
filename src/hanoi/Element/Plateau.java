/**
 * 
 */
package hanoi.Element;

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
			Tour t = new Tour();
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
	
	public void DeplacerDisque(Tour source,Tour destination){
		Disque tmp = source.retirerDisque();
		destination.ajouterDisque(tmp);
	}
	
	public String toString(){
		String res="";
		for(Tour t:tours){
			res+=t+"\n\n";
		}
		return res;
	}
	
	public Tour getTour(int n){
		return tours.get(n);
	}
}
