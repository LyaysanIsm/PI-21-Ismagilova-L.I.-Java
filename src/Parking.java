import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;

public class Parking<T extends ITransport, K extends IWeapons>
		implements Comparable<Parking<T, K>>, Iterable<T>, Iterator<T> {
	HashMap<Integer, T> _places;

	private int PictureWidth;
	private int PictureHeight;

	private final int _placeSizeWidth = 210;
	private final int _placeSizeHeight = 80;
	int _maxCount;
	private int curIndex;

	public Parking(int sizes, int pictureWidth, int pictureHeight) {
		_places = new HashMap<Integer, T>(sizes);
		_maxCount = sizes;
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;
	}

	public void setPlane(int index, T airplane) {
		_places.put(index, airplane);
		_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 15,
				PictureWidth, PictureHeight);
	}

	public int getPictureWidth() {
		return PictureWidth;
	}

	public void setPictureWidth(int PictureWidth) {
		this.PictureWidth = PictureWidth;
	}

	public int getPictureHeight() {
		return PictureHeight;
	}

	public void setPictureHeight(int PictureHeight) {
		this.PictureHeight = PictureHeight;
	}

	public int AddTo(T airplane, int index) throws ParkingOccupiedPlaceException {
		if (CheckFreePlace(index)) {
			_places.put(index, airplane);
			_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 15,
					PictureWidth, PictureHeight);
			return index;
		}
		throw new ParkingOccupiedPlaceException(index);
	}

	public int Add(T airplane) throws ParkingOverflowException, ParkingAlreadyHaveException {
		if (_places.containsValue(airplane))
			throw new ParkingAlreadyHaveException();
		for (int i = 0; i < _maxCount; i++) {
			if (CheckFreePlace(i)) {
				_places.put(i, airplane);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, PictureWidth,
						PictureHeight);
				return i;
			}
		}
		throw new ParkingOverflowException();
	}

	public T Delete(int index) throws ParkingNotFoundException {
		if (!CheckFreePlace(index)) {
			T airplane = _places.get(index);
			_places.remove(index);
			return airplane;
		}
		throw new ParkingNotFoundException(index);
	}

	private boolean CheckFreePlace(int index) {
		return !_places.containsKey(index);
	}

	public void Draw(Graphics g) {
		DrawMarking(g);
		for (int i = 0; i < _maxCount; i++) {
			if (!CheckFreePlace(i)) {
				_places.get(i).DrawAirplane(g);
			}
		}
	}

	public ITransport getTransport(int index) {
		if (!CheckFreePlace(index)) {
			return _places.get(index);
		}
		return null;
	}

	private void DrawMarking(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 480);
		for (int i = 0; i < _maxCount / 5; i++) {
			for (int j = 0; j < 6; j++) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
		}
	}

	public int getKey() {
		return (int) _places.keySet().toArray()[curIndex];
	}

	@Override
	public boolean hasNext() {
		if ((curIndex + 1) >= _places.size()) {
			curIndex = -1;
			return false;
		} else
			return true;
	}

	@Override
	public T next() {
		curIndex++;
		return _places.get(curIndex);
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public int compareTo(Parking<T, K> other) {
		if (_places.size() > other._places.size())
			return -1;
		else if (_places.size() < other._places.size())
			return 1;
		else if (_places.size() > 0) {
			Object[] thisKeys = _places.keySet().toArray();
			Object[] otherKeys = other._places.keySet().toArray();
			for (int i = 0; i < _places.size(); ++i) {
				if (_places.get(thisKeys[i]).getClass().getName().equals("Airplane")
						&& other._places.get(thisKeys[i]).getClass().getName().equals("Fighter"))
					return 1;
				if (_places.get(thisKeys[i]).getClass().getName().equals("Fighter")
						&& other._places.get(thisKeys[i]).getClass().getName().equals("Airplane"))
					return -1;
				if (_places.get(thisKeys[i]).getClass().getName().equals("Airplane")
						&& other._places.get(thisKeys[i]).getClass().getName().equals("Airplane")) {
					Airplane thisAirplane = (Airplane) _places.get(thisKeys[i]);
					Airplane otherAirplane = (Airplane) other._places.get(otherKeys[i]);
					return thisAirplane.compareTo(otherAirplane);
				}
				if (_places.get(thisKeys[i]).getClass().getName().equals("Fighter")
						&& other._places.get(thisKeys[i]).getClass().getName().equals("Fighter")) {
					Fighter thisAirplane = (Fighter) _places.get(thisKeys[i]);
					Fighter otherAirplane = (Fighter) other._places.get(otherKeys[i]);
					return thisAirplane.compareTo(otherAirplane);
				}
			}
		}
		return 0;
	}
}