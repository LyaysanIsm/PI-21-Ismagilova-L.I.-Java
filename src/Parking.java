import java.awt.Color;
import java.awt.Graphics;

public class Parking<T extends ITransport, K extends IWeapons> {
	private T[] _places;

	private int PictureWidth;
	private int PictureHeight;

	private final int _placeSizeWidth = 210;
	private final int _placeSizeHeight = 80;

	public Parking(int sizes, int pictureWidth, int pictureHeight) {
		_places = (T[]) new ITransport[sizes];
		PictureWidth = pictureWidth;
		PictureHeight = pictureHeight;

		for (int i = 0; i < _places.length; i++) {
			_places[i] = null;
		}
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
		for (int i = 0; i < _places.length; i++) {
			if (CheckFreePlace(i)) {
				_places[i] = airplane;
				_places[i].SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, PictureWidth,
						PictureHeight);
				return i;
			}
		}
		return -1;
	}

	public T Delete(int index) {
		if (index < 0 || index > _places.length) {
			return null;
		}
		if (!CheckFreePlace(index)) {
			T airplane = _places[index];
			_places[index] = null;
			return airplane;
		}

		return null;
	}

	public int AddSeveral(T airplane, int count) {
		for (int i = 0; i < count; i++) {
			ITransport air = airplane.Clone();
			Add((T) air);
		}
		return count;
	}	

	private boolean CheckFreePlace(int index) {
		return _places[index] == null;
	}

	public void Draw(Graphics g) {
		DrawMarking(g);
		for (int i = 0; i < _places.length; i++) {
			if (!CheckFreePlace(i)) {
				_places[i].DrawAirplane(g);
			}
		}
	}

	private void DrawMarking(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, (_places.length / 5) * _placeSizeWidth, 480);
		for (int i = 0; i < _places.length / 5; i++) {
			for (int j = 0; j < 6; j++) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
		}
	}
}