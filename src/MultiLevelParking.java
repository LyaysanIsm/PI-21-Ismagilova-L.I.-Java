import java.util.ArrayList;

public class MultiLevelParking {

	ArrayList<Parking<ITransport, IWeapons>> parkingStages;

	private static final int countPlaces = 15;

	public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight) {
		parkingStages = new ArrayList<Parking<ITransport, IWeapons>>();
		for (int i = 0; i < countStages; ++i) {
			parkingStages.add(new Parking<ITransport, IWeapons>(countPlaces, pictureWidth, pictureHeight));
		}
	}

	public Parking<ITransport, IWeapons> getParking(int index) {

		if (index > -1 && index < parkingStages.size()) {
			return parkingStages.get(index);
		}
		return null;
	}

	public ITransport getTransport(int index, int lvl) {
		if (lvl > -1 && lvl < parkingStages.size()) {
			ITransport plane = parkingStages.get(lvl).Delete(index);
			return plane;
		}
		return null;
	}
}