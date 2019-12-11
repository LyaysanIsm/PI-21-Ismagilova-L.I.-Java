import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

public class Parking<T extends ITransport, K extends IWeapons> {
	HashMap<Integer, T> _places;

	private int PictureWidth;
	private int PictureHeight;

	private final int _placeSizeWidth = 210;
	private final int _placeSizeHeight = 80;
	int _maxCount;

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

	public int Add(T airplane) {
		for (int i = 0; i < _maxCount; i++) {
			if (CheckFreePlace(i)) {
				_places.put(i, airplane);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, PictureWidth,
						PictureHeight);
				return i;
			}
		}
		return -1;
	}

	public T Delete(int index) {
		if (!CheckFreePlace(index)) {
			T airplane = _places.get(index);
			_places.remove(index);
			return airplane;
		}
		return null;
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
}