package hanoi.exception;

import hanoi.Element.Tour;

public class TourVideException extends Exception {

	public TourVideException(Tour tour) {
		super("La tour "+tour+" était vide.");
	}
}
