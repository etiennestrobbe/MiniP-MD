/**
 * 
 */
package hanoi.main;
import java.util.Scanner;

import hanoi.simulation.*;

/**
 * @author Etienne Strobbe
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Combien de disques ?");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.close();
		
<<<<<<< HEAD
		Simulation simulation = new Simulation(num);
		simulation.runSimulation("recursif");
		//simulation.runSimulation("iteratif");
=======
		new Simulation(num).runSimulation("iteratif");
		new Simulation(num).runSimulation("recursif");
>>>>>>> ab567484e983bfb44b56054127dfd4c20ddb9d54
	}
}
