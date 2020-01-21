public class ParkingNotFoundException extends NullPointerException {
	public ParkingNotFoundException(int i) {
		super("Не найден самолет по месту " + i);
	}
}
