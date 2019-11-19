
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelAirplane extends JPanel {
	ITransport airplane;

	public PanelAirplane() {

	}

	public void setAirplane(ITransport p) {
		airplane = p;
		airplane.SetPosition(10, 10, 90, 50);
	}

	public void renovate(int width, int height, boolean itFighter) {
		if (itFighter) {
			airplane = new Fighter((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000, Color.orange,
					Color.darkGray, true, true, true, true, (int) (Math.random() * 3));
		} else {
			airplane = new Airplane((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000, Color.green,
					true, true, (int) (Math.random() * 3));
		}
		airplane.SetPosition((int) (Math.random() * 200) + 100, (int) (Math.random() * 100) + 50, width, height);
	}

	public void MoveTransport(Direction direction) {
		switch (direction) {
		case Right:
			airplane.MoveTransport(Direction.Right);
			break;
		case Left:
			airplane.MoveTransport(Direction.Left);
			break;
		case Up:
			airplane.MoveTransport(Direction.Up);
			break;
		case Down:
			airplane.MoveTransport(Direction.Down);
			break;
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (airplane != null) {
			airplane.DrawAirplane(g);
		}
	}
}
