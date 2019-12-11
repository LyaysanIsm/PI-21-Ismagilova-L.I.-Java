import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;

public class PanelParking extends JPanel {
	public PanelParking() {
	}

	MultiLevelParking parking = new MultiLevelParking(15, 800, 600);

	private final int countLevel = 5;

	private int presentLevel = 0;

	public void paint(Graphics g) {
		super.paint(g);
		parking.getParking(presentLevel).Draw(g);
	}

	public void setLevel(int index) {
		if (index >= 0 && index < countLevel) {
			presentLevel = index;
		}
	}

	public int Add(ITransport transport) {
		return parking.getParking(presentLevel).Add(transport);
	}

	public ITransport Delete(int index) {
		ITransport transport = parking.getParking(presentLevel).Delete(index);

		return transport;
	}

	public ITransport getTransport(int i) {
		return parking.getTransport(i, presentLevel);
	}

	public void SaveInfo(String filename) {
		try {
			parking.Save(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void LoadInfo(String filename) {
		try {
			parking.Load(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void SavePresentLevel(String filename) {
		parking.SaveLevel(filename, presentLevel);

	}

	public void LoadPresentLevel(String filename) {
		try {
			parking.LoadLevel(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}