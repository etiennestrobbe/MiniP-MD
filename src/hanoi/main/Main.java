/**
 * 
 */
package hanoi.main;
import hanoi.TraitementRecursif.*;

/**
 * @author Etienne Strobbe
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Simulation simulation = new Simulation(4);
		simulation.runSimulation();
	}

}
