import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MultiLevelParking {

	ArrayList<Parking<ITransport, IWeapons>> parkingStages;

	private static final int countPlaces = 15;
	int pictureWidth;
	int pictureHeight;

	public MultiLevelParking(int countStages, int pw, int ph) {
		pictureWidth = pw;
		pictureHeight = ph;
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

	public ITransport getTransport(int index, int lvl) throws ParkingNotFoundException {
		if (lvl > -1 && lvl < parkingStages.size()) {
			try {
				ITransport plane = parkingStages.get(lvl).Delete(index);
				return plane;
			} catch (ParkingNotFoundException ex) {
				throw new ParkingNotFoundException(index);
			}
		}
		return null;
	}

	public boolean Save(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		WriteToFile("CountLeveles:" + parkingStages.size() + "\n", fw);
		for (Parking<ITransport, IWeapons> level : parkingStages) {
			WriteToFile("Level" + "\n", fw);
			for (int i = 0; i < countPlaces; i++) {
				ITransport airplane = level.getTransport(i);
				if (airplane != null) {
					if (airplane.getClass().getName() == "Airplane") {
						WriteToFile(i + ":Airplane:", fw);
					}
					if (airplane.getClass().getName() == "Fighter") {
						WriteToFile(i + ":Fighter:", fw);
					}
					WriteToFile(airplane.ToString() + "\n", fw);
				}
			}
		}
		fw.flush();
		return true;
	}

	private void WriteToFile(String text, FileWriter fw) {
		try {
			fw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean Load(String filename) throws ParkingOccupiedPlaceException, IOException {
		FileReader fr = new FileReader(filename);
		int counter = -1;
		try {
			String bufferTextFromFile = "";
			int c;
			while ((char) (c = fr.read()) != '\n') {
				bufferTextFromFile += (char) c;
			}
			if (bufferTextFromFile.contains("CountLeveles")) {
				int count = Integer.parseInt(bufferTextFromFile.split(":")[1]);
				bufferTextFromFile = "";
			} else {
				return false;
			}
			while ((c = fr.read()) != -1) {
				if ((char) c == '\n') {
					ITransport airplane = null;
					if (bufferTextFromFile.equals("Level")) {
						counter++;
						parkingStages.add(new Parking<ITransport, IWeapons>(countPlaces, pictureWidth, pictureHeight));
						bufferTextFromFile = "";
						continue;
					}
					if (bufferTextFromFile.split(":").length > 1) {
						if (bufferTextFromFile.split(":")[1].equals("Airplane")) {
							airplane = new Airplane(bufferTextFromFile.split(":")[2]);
						} else if (bufferTextFromFile.split(":")[1].equals("Fighter")) {
							airplane = new Fighter(bufferTextFromFile.split(":")[2]);
						}
						parkingStages.get(counter).AddTo(airplane, Integer.parseInt(bufferTextFromFile.split(":")[0]));
					}
					bufferTextFromFile = "";
				} else {
					bufferTextFromFile += (char) c;
				}
			}
		} catch (ParkingOccupiedPlaceException | IOException ex) {
			if (ex instanceof ParkingOccupiedPlaceException) {
				throw new ParkingOccupiedPlaceException(counter);
			} else
				throw new IOException();
		}
		return true;
	}

	public boolean SaveLevel(String filename, int lvl) {
		try {
			if ((lvl > parkingStages.size()) || (lvl < 0)) {
				return false;
			}
			FileWriter fw = new FileWriter(filename);
			WriteToFile("Level:" + lvl + "\n", fw);
			Parking<ITransport, IWeapons> level = parkingStages.get(lvl);
			for (int i = 0; i < countPlaces; i++) {
				ITransport airplane = level.getTransport(i);
				if (airplane != null) {
					if (airplane.getClass().getName() == "Airplane") {
						WriteToFile(i + ":Airplane:", fw);
					}
					if (airplane.getClass().getName() == "Fighter") {
						WriteToFile(i + ":Fighter:", fw);
					}
					WriteToFile(airplane.ToString() + "\n", fw);
				}
			}
			fw.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	public boolean LoadLevel(String filename) throws IOException, ParkingOccupiedPlaceException {
		FileReader fr = new FileReader(filename);
		try {
			String bufferTextFromFile = "";
			int lvl = 0;
			int c;
			while ((char) (c = fr.read()) != '\n') {
				bufferTextFromFile += (char) c;
			}
			if (bufferTextFromFile.contains("Level")) {
				lvl = Integer.parseInt(bufferTextFromFile.split(":")[1]);
				bufferTextFromFile = "";
			} else {
				return false;
			}
			if (parkingStages.size() < lvl) {
				return false;
			}
			while ((c = fr.read()) != -1) {
				if ((char) c == '\n') {
					ITransport airplane = null;
					if (bufferTextFromFile == null) {
						continue;
					}
					if (bufferTextFromFile.split(":").length > 2) {
						if (bufferTextFromFile.split(":")[1].equals("Airplane")) {
							airplane = new Airplane(bufferTextFromFile.split(":")[2]);
						} else if (bufferTextFromFile.split(":")[1].equals("Fighter")) {
							airplane = new Fighter(bufferTextFromFile.split(":")[2]);
						}
						parkingStages.get(lvl).AddTo(airplane, Integer.parseInt(bufferTextFromFile.split(":")[0]));
					}
					bufferTextFromFile = "";
				} else {
					bufferTextFromFile += (char) c;
				}
			}
		} catch (ParkingOccupiedPlaceException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		}
		return true;
	}
}