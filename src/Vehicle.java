import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {

	protected int _startPosX;
	protected int _startPosY;
	protected int _pictureWidth;
	protected int _pictureHeight;
	public int MaxSpeed;
	public float Weight;
	public Color MainColor;

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_pictureWidth = width;
		_pictureHeight = height;
	}

	public void setColor(Color main) {
		MainColor = main;
	}

	abstract public void DrawAirplane(Graphics g);

	abstract public void MoveTransport(Direction direction);

}
