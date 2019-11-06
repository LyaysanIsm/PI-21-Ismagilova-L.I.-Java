import java.awt.Color;
import java.awt.Graphics;

public class Fighter extends Airplane {

	public Color DopColor;
	public Color FlagColor;
	public boolean Bombs;
	public boolean Bullets;
	public boolean Keel;
	public boolean Cabin;

	public Fighter(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean bombs, boolean bullets,
			boolean keel, boolean cabin) {
		super(maxSpeed, weight, mainColor, keel, cabin, (int) (Math.random() * 3));
		DopColor = dopColor;
		Keel = keel;
		Bullets = bullets;
		Cabin = cabin;
		Bombs = bombs;
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 100 / Weight;
		switch (direction) {
		// вправо
		case Right:
			if (_startPosX + step < _pictureWidth - planeWidth - 85) {
				_startPosX += step;
			}
			break;
		// влево
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// вверх
		case Up:
			if (_startPosY - step > 3) {

				_startPosY -= step;
			}
			break;
		// вниз
		case Down:
			if (_startPosY + step < _pictureHeight - planeHeight - 100) {
				_startPosY += step;
			}
			break;
		}
	}

	public void DrawAirplane(Graphics g) {

		g.setColor(DopColor);
		if (Bombs) {
			g.drawLine(_startPosX + 95, _startPosY - 20, _startPosX + 95, _startPosY);
			g.drawLine(_startPosX + 105, _startPosY - 20, _startPosX + 105, _startPosY);
		}
		super.DrawAirplane(g);

		/*
		 * g.setColor(DopColor); if (Bullets) { g.fillOval(_startPosX + 48, _startPosY +
		 * 40, 5, 25); g.fillOval(_startPosX + 59, _startPosY + 35, 5, 25);
		 * g.fillOval(_startPosX + 70, _startPosY + 32, 5, 25);
		 * 
		 * g.fillOval(_startPosX + 145, _startPosY + 40, 5, 25); g.fillOval(_startPosX +
		 * 134, _startPosY + 35, 5, 25); g.fillOval(_startPosX + 122, _startPosY + 32,
		 * 5, 25); }
		 */
		g.setColor(Color.yellow);
		if (Cabin) {
			g.fillRect(_startPosX + 87, _startPosY + 26, 25, 15);
			g.setColor(Color.black);
			g.drawRect(_startPosX + 87, _startPosY + 26, 25, 15);
		}

		g.setColor(Color.black);
		if (Keel) {
			g.fillOval(_startPosX + 98, _startPosY + 120, 5, 30);
			g.drawOval(_startPosX + 98, _startPosY + 120, 5, 30);
		}
	}
}
