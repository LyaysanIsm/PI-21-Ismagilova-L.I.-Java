import java.awt.Color;
import java.awt.Graphics;

public class Fighter extends Airplane {

	public Color DopColor;
	public Color FlagColor;
	public boolean Bombs;
	public boolean Bullets;
	public boolean Keel;
	public boolean Cabin;
	protected IWeapons weapons;

	public Fighter(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean bombs, boolean bullets,
			boolean keel, boolean cabin, int m) {
		super(maxSpeed, weight, mainColor, keel, cabin, (int) (Math.random() * 3));
		DopColor = dopColor;
		Keel = keel;
		Bullets = bullets;
		Cabin = cabin;
		Bombs = bombs;
		weapons = new Weapons((int) (Math.random() * 3) + 4);
	}

	public Fighter(String info) {
		super(info);
		String[] strs = info.split(";");
		if (strs.length == 7) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = new Color(Integer.parseInt(strs[2]));
			Keel = Boolean.parseBoolean(strs[3]);
			Cabin = Boolean.parseBoolean(strs[4]);
			DopColor = new Color(Integer.parseInt(strs[5]));
			Bombs = Boolean.parseBoolean(strs[6]);
		}
		weapons = new Weapons((int) (Math.random() * 3) + 4);
	}

	@Override
	public String ToString() {
		return super.ToString() + ";" + DopColor.getRGB() + ";" + Bombs;
	}

	public void setWeapons(IWeapons weap) {
		weapons = weap;
	}

	public void setDopColor(Color dop) {
		DopColor = dop;
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
		g.setColor(DopColor);
		if (Bombs) {
			g.drawLine(_startPosX + 94 / 3, _startPosY - 20 / 3, _startPosX + 94 / 3, _startPosY + 100 / 3);
			g.drawLine(_startPosX + 104 / 3, _startPosY - 20 / 3, _startPosX + 104 / 3, _startPosY + 100 / 3);
		}
		super.DrawAirplane(g);
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
		}
		weapons.DrawWeapons(g, Color.black, _startPosX + 48 / 3, _startPosY + 43 / 3);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		} else {
			return equals((Fighter) obj);
		}
	}

	public boolean equals(Fighter other) {
		if (!MainColor.equals(other.MainColor))
			return false;
		if (!DopColor.equals(other.DopColor))
			return false;
		if (Keel != other.Keel)
			return false;
		if (Cabin != other.Cabin)
			return false;
		if (Bombs != other.Bombs)
			return false;
		return true;
	}

	public int compareTo(Fighter other) {
		if (other == null)
			return 1;
		if (!MainColor.equals(other.MainColor))
			return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
		if (!DopColor.equals(other.DopColor))
			return Integer.compare(DopColor.getRGB(), other.DopColor.getRGB());
		if (Keel != other.Keel)
			return Boolean.compare(Keel, other.Keel);
		if (Cabin != other.Cabin)
			return Boolean.compare(Cabin, other.Cabin);
		if (Bombs != other.Bombs)
			return Boolean.compare(Bombs, other.Bombs);
		return 0;
	}
}