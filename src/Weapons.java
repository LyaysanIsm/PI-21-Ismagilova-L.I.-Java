import java.awt.Color;
import java.awt.Graphics;

public class Weapons implements IWeapons {
	private Amount amount;

	public Weapons(int k) {
		if (k == 4) {
			amount = Amount.four;
		}
		if (k == 5) {
			amount = Amount.five;
		}
		if (k == 6) {
			amount = Amount.six;
		} else
			amount = Amount.four;
	}

	public void DrawWeapons(Graphics g, Color color, int PosX, int PosY) {
		g.setColor(color);

		int amt, n;

		switch (amount) {
		case four:
			amt = 4;
			n = 30;
			break;
		case five:
			amt = 5;
			n = 25;
			break;
		case six:
			amt = 6;
			n = 20;
			break;
		default:
			amt = 4;
			n = 30;
			break;
		}

		for (int i = 0; i < amt; i++) {
			g.setColor(color.red);
			g.fillOval(PosX - 20 + n * i, PosY + 5, 5 / 2, 25 / 2);
		}
	}
}
