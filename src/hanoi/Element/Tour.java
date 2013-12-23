/**
 * 
 */
package hanoi.Element;

import java.util.LinkedList;

/**
 * @author Etienne Strobbe
 *
 */
public class Tour {
	
	private LinkedList<Disque> disques;
	private boolean initialise;
	private int taille;
	
	public Tour(){
		disques = new LinkedList<Disque>();
		initialise = false;
		taille = 0;
	}
	
	public Disque retirerDisque(){
		return disques.pollLast();
		
	}
	
	private void ajoutDebut(Disque d){
		disques.addFirst(d);
		taille--;
	}
	
	private void ajoutFin(Disque d){
		disques.addLast(d);
		taille--;
	}
	
	public void ajouterDisqueDebut(Disque d){
		if(!initialise){
			ajoutDebut(d);
		}
	}
	public void ajouterDisque(Disque d){
		if(taille>0){
			if(disques.peekLast().estPlusGrand(d)){
				ajoutFin(d);
			}
			else{
				System.out.println("Erreur, disque trop grand");
			}
		}
		else{
			ajoutFin(d);
		}
	}
	
	public void estInitialise(){
		initialise = true;
	}
	
	public String toString(){
		String res="";
		for(Disque d:disques){
			res=d+"\n"+res;
		}
		res+="_______";
		return res;
	}
	
	public LinkedList<Disque> getDisques() {
		return disques;
	}
}
