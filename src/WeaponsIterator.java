import java.util.Iterator;

public class WeaponsIterator implements Iterator<Amount> {
	private Amount[] weapons;
	private int curIndex = -1;

	public WeaponsIterator() {
		weapons = Amount.values();
	}

	@Override
	public boolean hasNext() {
		if ((curIndex + 1) >= weapons.length) {
			curIndex = -1;
			return false;
		}
		return true;
	}

	@Override
	public Amount next() {
		curIndex++;
		return weapons[curIndex];
	}
}
