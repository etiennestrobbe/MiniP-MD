/**
 * 
 */
package hanoi.Element;

/**
 * @author Etienne Strobbe
 *
 */
public class Disque {
	
	private int taille;
	
	public Disque(int taille){
		this.taille = taille;
	}
	
	public int getTaille(){
		return taille;
	}
	
	public boolean estPlusGrand(Disque d){
		return (this.taille > d.taille);
	}
	
	public String toString(){
		String res="";
		for(int i=0;i<taille;i++){
			res+="#";
		}
		return res;
	}

}
