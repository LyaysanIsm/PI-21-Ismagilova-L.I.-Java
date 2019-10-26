import java.awt.Color;
import java.awt.Graphics;

public class Airplane {
	private int _startPosX;
	private int _startPosY;
	private int _pictureWidth;
	private int _pictureHeight;
	final private int planeWidth = 90;
	final private int planeHeight = 50;
	public int MaxSpeed;
	public float Weight;
	public Color MainColor;
	public Color DopColor;
	public boolean Keel;
	public boolean Bullets;
	public boolean Cabin;
	private Weapons weapons;

	public Airplane(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean keel, boolean bullets,
			boolean cabin) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		Keel = keel;
		Bullets = bullets;
		Cabin = cabin;
		
		weapons = new Weapons(4 + (int) (Math.random() * 6));
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
		g.setColor(MainColor);
		g.fillOval(_startPosX + 28, _startPosY + 40, 140, 30);
		g.setColor(Color.black);
		g.drawOval(_startPosX + 28, _startPosY + 40, 140, 30);
		g.setColor(MainColor);
		g.fillOval(_startPosX + 60, _startPosY + 130, 80, 15);
		g.setColor(Color.black);
		g.drawOval(_startPosX + 60, _startPosY + 130, 80, 15);
		g.setColor(MainColor);
		g.fillOval(_startPosX + 80, _startPosY - 6, 40, 160);
		g.setColor(Color.black);
		g.drawOval(_startPosX + 80, _startPosY - 6, 40, 160);

		weapons.DrawWeapons(g, DopColor, _startPosX + 50, _startPosY + 65);

		/*
		 * g.setColor(DopColor); if (Bullets) { g.fillOval( _startPosX + 48, _startPosY
		 * + 40, 5, 25); g.fillOval( _startPosX + 59, _startPosY + 35, 5, 25);
		 * g.fillOval( _startPosX + 70, _startPosY + 32, 5, 25);
		 * 
		 * g.fillOval( _startPosX + 145, _startPosY + 40, 5, 25); g.fillOval( _startPosX
		 * + 134, _startPosY + 35, 5, 25); g.fillOval( _startPosX + 122, _startPosY +
		 * 32, 5, 25); }
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
