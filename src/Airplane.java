import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

public class Airplane extends Vehicle implements Comparable<Airplane>, Iterable<String>, Iterator<String> {
	protected final int planeWidth = 90;
	protected final int planeHeight = 50;
	public boolean Keel;
	public boolean Cabin;
	protected IWeapons weapons;
	public int m;
	private int curIndex = -1;

	public Airplane(int maxSpeed, int weight, Color mainColor, boolean keel, boolean cabin, int m) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		Keel = keel;
		Cabin = cabin;
	}

	public Airplane(String info) {
		String[] strs = info.split(";");
		if (strs.length == 5) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Integer.parseInt(strs[1]);
			MainColor = new Color(Integer.parseInt(strs[2]));
			Keel = Boolean.parseBoolean(strs[3]);
			Cabin = Boolean.parseBoolean(strs[4]);
		}
	}

	@Override
	public String ToString() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRGB() + ";" + Keel + ";" + Cabin;
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
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airplane other = (Airplane) obj;
		if (!MainColor.equals(other.MainColor))
			return false;
		if (Keel != other.Keel)
			return false;
		if (Cabin != other.Cabin)
			return false;
		return true;
	}

	@Override
	public int compareTo(Airplane other) {
		if (other == null)
			return 1;
		if (MaxSpeed != other.MaxSpeed)
			return Integer.compare(MaxSpeed, other.MaxSpeed);
		if (MainColor != other.MainColor)
			return Integer.compare(MainColor.getRGB(), other.MainColor.getRGB());
		if (Weight != other.Weight)
			return Integer.compare(Weight, other.Weight);
		if (Keel != other.Keel)
			return Boolean.compare(Keel, other.Keel);
		if (Cabin != other.Cabin)
			return Boolean.compare(Cabin, other.Cabin);
		return 0;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean hasNext() {
		if (curIndex + 1 >= ToString().split(";").length) {
			curIndex = -1;
			return false;
		}
		return true;
	}

	@Override
	public String next() {
		curIndex++;
		return ToString().split(";")[curIndex];
	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}
}
