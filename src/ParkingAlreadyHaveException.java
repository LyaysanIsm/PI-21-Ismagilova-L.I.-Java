public class ParkingAlreadyHaveException extends Exception {
	public ParkingAlreadyHaveException() {
		super("В ангаре уже есть такой самолет");
	}
}