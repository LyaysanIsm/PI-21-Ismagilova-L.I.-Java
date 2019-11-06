import java.awt.Color;
import java.awt.Graphics;

public class Airplane extends Vehicle {
	protected final int planeWidth = 90;
	protected final int planeHeight = 50;
	public boolean Keel;
	public boolean Cabin;
	protected IWeapons weapons;

	public Airplane(int maxSpeed, float weight, Color mainColor, boolean keel, boolean cabin, int m) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		Keel = keel;
		Cabin = cabin;

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
		// âïðàâî
		case Right:
			if (_startPosX + step < _pictureWidth - planeWidth - 85) {
				_startPosX += step;
			}
			break;
		// âëåâî
		case Left:
			if (_startPosX - step > 0) {
				_startPosX -= step;
			}
			break;
		// ââåðõ
		case Up:
			if (_startPosY - step > 3) {

				_startPosY -= step;
			}
			break;
		// âíèç
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

			weapons.DrawWeapons(g, Color.black, _startPosX + 48, _startPosY + 43);
		}
	}
}
