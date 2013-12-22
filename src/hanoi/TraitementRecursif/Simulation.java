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
	private HanoiView view;
	
	public Simulation(int nbDisques){
		view = new HanoiView(nbDisques);
		plateau = new Plateau(nbDisques);
		this.nbDisques = nbDisques;
	}
	
	private void Hanoi(int nbDisques,Tour D,Tour A,Tour I) {
		if(nbDisques!=0){
			Hanoi(nbDisques-1,D,I,A);
			
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Deplace D vers A
			plateau.DeplacerDisque(D,A);
			Hanoi(nbDisques-1,I,A,D);
		}
		view.display(plateau);
	}
	
	public void runSimulation(){
		Hanoi(nbDisques,plateau.getTour(0),plateau.getTour(2),plateau.getTour(1));
	}
	
	public String toString(){
		return plateau.toString();
	}
}
