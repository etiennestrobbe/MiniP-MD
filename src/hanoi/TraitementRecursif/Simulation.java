/**
 * 
 */
package hanoi.TraitementRecursif;
import hanoi.Element.*;
/**
 * @author Etienne Strobbe
 *
 */
public class Simulation {
	private Plateau plateau;
	private int nbDisques;
	
	public Simulation(int nbDisques){
		plateau = new Plateau(nbDisques);
		this.nbDisques = nbDisques;
	}
	
	private void Hanoi(int nbDisques,Tour D,Tour A,Tour I){
		if(nbDisques!=0){
			Hanoi(nbDisques-1,D,I,A);
			//Deplace D vers A
			plateau.DeplacerDisque(D,A);
			System.out.println(plateau);
			Hanoi(nbDisques-1,I,A,D);
		}
	}
	
	public void runSimulation(){
		Hanoi(nbDisques,plateau.getTour(0),plateau.getTour(2),plateau.getTour(1));
	}
	
	public String toString(){
		return plateau.toString();
	}
}
