import java.awt.Color;
import java.awt.Graphics;

public class Airplane extends Vehicle {
	protected final int planeWidth = 90;
	protected final int planeHeight = 50;
	public boolean Keel;
	public boolean Cabin;
	protected IWeapons weapons;
	public int m;

	public Airplane(int maxSpeed, float weight, Color mainColor, boolean keel, boolean cabin, int m) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		Keel = keel;
		Cabin = cabin;
		this.m = m;

		if (m == 0) {
			weapons = new Weapons((int) (Math.random() * 6) + 4);
		}
		if (m == 1) {
			weapons = new WeaponsSquare((int) (Math.random() * 6) + 4);
		}
		if (m == 2) {
			weapons = new WeaponsRectangle((int) (Math.random() * 6) + 4);
		}
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
		g.fillOval(_startPosX + 28 / 3, _startPosY + 40 / 3, 140 / 3, 30 / 3);
		g.setColor(Color.black);
		g.drawOval(_startPosX + 28 / 3, _startPosY + 40 / 3, 140 / 3, 30 / 3);
		g.setColor(MainColor);
		g.fillOval(_startPosX + 60 / 3, _startPosY + 130 / 3, 80 / 3, 15 / 3);
		g.setColor(Color.black);
		g.drawOval(_startPosX + 60 / 3, _startPosY + 130 / 3, 80 / 3, 15 / 3);
		g.setColor(MainColor);
		g.fillOval(_startPosX + 80 / 3, _startPosY - 6 / 3, 40 / 3, 160 / 3);
		g.setColor(Color.black);
		g.drawOval(_startPosX + 80 / 3, _startPosY - 6 / 3, 40 / 3, 160 / 3);

		g.setColor(Color.yellow);
		if (Cabin) {
			g.fillRect(_startPosX + 87 / 3, _startPosY + 26 / 3, 25 / 3, 15 / 3);
			g.setColor(Color.black);
			g.drawRect(_startPosX + 87 / 3, _startPosY + 26 / 3, 25 / 3, 15 / 3);
		}

		g.setColor(Color.black);
		if (Keel) {
			g.fillOval(_startPosX + 98 / 3, _startPosY + 120 / 3, 5 / 3, 30 / 3);
			g.drawOval(_startPosX + 98 / 3, _startPosY + 120 / 3, 5 / 3, 30 / 3);

			weapons.DrawWeapons(g, Color.black, _startPosX + 48 / 3, _startPosY + 43 / 3);
		}
	}

	public ITransport Clone() {
		ITransport air = new Airplane(this.MaxSpeed, this.Weight, this.MainColor, this.Keel, this.Cabin, m);
		return air;
	}
}