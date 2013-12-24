package hanoi.exception;

@SuppressWarnings("serial")
public class DisqueTropGrandException extends Exception {
	public DisqueTropGrandException() {
		super("Le disque ne peut être placé ici, il est trop grand.");
	}
}
