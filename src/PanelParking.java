import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelParking extends JPanel {

	public PanelParking() {
	}

	Parking<ITransport, IWeapons> parking = new Parking<ITransport, IWeapons>(15, 800, 600);

	public void paint(Graphics g) {
		super.paint(g);
		parking.Draw(g);
	}

	public int Add(ITransport airplane) {
		return parking.Add(airplane);
	}

	public ITransport Delete(int index) {
		return parking.Delete(index);
	}

	public int AddSeveral(ITransport airplane, int count) {
		return parking.AddSeveral(airplane, count);
	}	
}