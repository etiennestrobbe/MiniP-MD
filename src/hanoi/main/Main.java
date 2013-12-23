/**
 * 
 */
package hanoi.main;
import java.util.Scanner;

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
		System.out.println("Combien de disques ?");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.close();
		
		Simulation simulation = new Simulation(num);
		simulation.runSimulation();
	}

}
