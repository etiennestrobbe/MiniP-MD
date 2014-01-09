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

	public Disque(int taille) {
		this.taille = taille;
	}

	public int getTaille() {
		return taille;
	}

	/**
	 * Renvoie vrai si le disque courant est plus grand que le disque donné en
	 * paramètre
	 * 
	 * @param d
	 * @return
	 */
	public boolean estPlusGrand(Disque d) {
		return (this.taille > d.taille);
	}
}
