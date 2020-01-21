import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JPanel;

public class PanelParking extends JPanel {

	private final int countLevel = 5;
	private int presentLevel = 0;
	MultiLevelParking parking = new MultiLevelParking(countLevel, 800, 600);

	public PanelParking() {
	}

	public void paint(Graphics g) {
		super.paint(g);
		parking.getParking(presentLevel).Draw(g);
	}

	public void setLevel(int index) {
		if (index >= 0 && index < countLevel) {
			presentLevel = index;
		}
	}

	public int Add(ITransport transport) throws ParkingOverflowException, ParkingAlreadyHaveException {
		return parking.getParking(presentLevel).Add(transport);
	}

	public ITransport Delete(int index) {
		ITransport transport = parking.getParking(presentLevel).Delete(index);
		return transport;
	}

	public ITransport getTransport(int i) throws ParkingOccupiedPlaceException {
		return parking.getTransport(i, presentLevel);
	}

	public void SaveInfo(String filename) throws IOException {
		parking.Save(filename);
	}

	public void LoadInfo(String filename) throws ParkingOccupiedPlaceException, IOException {
		parking.Load(filename);
	}

	public void SavePresentLevel(String filename) throws IOException {
		parking.SaveLevel(filename, presentLevel);
	}

	public void LoadPresentLevel(String filename) throws IOException, ParkingOccupiedPlaceException {
		parking.LoadLevel(filename);
	}

	public void sort() {
		parking.sort();
	}

	public String getProperties() {
		return parking.getProperties();
	}
}