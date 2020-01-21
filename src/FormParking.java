import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.List;

public class FormParking {

	PanelParking panel;
	private JFrame frame;
	private JPanel takeAirplane;
	private JTextField postextField;
	private ITransport airplane;
	private Logger logger;
	private Logger logger_error;

	ArrayList<ITransport> arrlist = new ArrayList<ITransport>();

	class Delegate extends AirplaneDelegate {
		@Override
		public void induce(ITransport airplane) throws ParkingOverflowException, ParkingAlreadyHaveException {
			panel.Add(airplane);
			panel.repaint();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormParking window = new FormParking();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormParking() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger = Logger.getLogger(FormParking.class.getName() + "1");
		logger_error = Logger.getLogger(FormParking.class.getName() + "2");
		try {
			FileHandler fh = null;
			FileHandler fh_error = null;
			fh = new FileHandler("D:\\file_info.txt");
			fh_error = new FileHandler("D:\\file_error.txt");
			logger.addHandler(fh);
			logger_error.addHandler(fh_error);
			logger.setUseParentHandlers(false);
			logger_error.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			fh_error.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.setTitle("Ангар");
		frame.setBounds(100, 100, 1000, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new PanelParking();
		panel.setBounds(12, 81, 639, 437);
		frame.getContentPane().add(panel);

		PanelAirplane takeAirplane = new PanelAirplane();
		takeAirplane.setBounds(695, 177, 236, 118);
		frame.getContentPane().add(takeAirplane);

		JButton btnTakeAirplane = new JButton("Take Airplane");
		btnTakeAirplane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s = postextField.getText();
					if (s != "") {
						int index = Integer.parseInt(s);
						airplane = panel.getTransport(index);
						takeAirplane.setAirplane(airplane);
						takeAirplane.repaint();
						panel.repaint();
						arrlist.add(airplane);
						logger.info("Удален самолет по месту " + index);
					}
				} catch (ParkingNotFoundException ex) {
					logger_error.warning("Не найдено");
					JOptionPane.showMessageDialog(null, "Не найдено", "Exception", 0, null);
				} catch (Exception ex) {
					logger_error.warning("Неизвестная ошибка");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка", "Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnTakeAirplane.setBounds(695, 138, 148, 29);
		frame.getContentPane().add(btnTakeAirplane);
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(695, 107, 69, 20);
		frame.getContentPane().add(lblPosition);

		postextField = new JTextField();
		postextField.setBounds(757, 107, 24, 20);
		frame.getContentPane().add(postextField);
		postextField.setColumns(10);

		JButton btnAddAirplane = new JButton("Airplane");
		btnAddAirplane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAirplaneConfig config = new FormAirplaneConfig(new Delegate());
				try {
					config.frame.setVisible(true);
					config.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (ParkingOverflowException ex) {
					logger.warning("Парковка переполнена");
					JOptionPane.showMessageDialog(null, "Парковка переполнена");
				}
			}
		});
		btnAddAirplane.setBounds(695, 49, 148, 29);
		frame.getContentPane().add(btnAddAirplane);

		List list = new List();
		for (int i = 0; i < 5; i++) {
			list.add("Level " + i);
		}
		list.setBounds(705, 301, 120, 118);
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setLevel(list.getSelectedIndex());
				panel.repaint();
			}
		});
		frame.getContentPane().add(list);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(30, 12, 139, 31);
		frame.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("Файл");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Сохранить");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
					filechooser.setFileFilter(filter);
					int loc = filechooser.showDialog(null, "Сохранить");
					if (loc == JFileChooser.APPROVE_OPTION) {
						File file = filechooser.getSelectedFile();
						panel.SaveInfo(file.getAbsolutePath() + ".txt");
						logger.info("Сохранение прошло успешно");
						JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
					}
				} catch (Exception e1) {
					logger.info("Неизвестная ошибка");
					JOptionPane.showMessageDialog(null, "Неизвестная ошибка");
				}
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Загрузить");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
					filechooser.setFileFilter(filter);
					int loc = filechooser.showDialog(null, "Загрузить");
					if (loc == JFileChooser.APPROVE_OPTION) {
						File file = filechooser.getSelectedFile();
						panel.LoadInfo(file.getAbsolutePath());
						JOptionPane.showMessageDialog(null, "Загрузили");
						logger.info("Загрузили");
						panel.repaint();
					} else {
						logger.info("Не удалось загрузить");
						JOptionPane.showMessageDialog(null, "Не удалось загрузить");
					}
				} catch (ParkingOccupiedPlaceException ex) {
					logger_error.warning("Ошибка загрузки " + ex.getMessage());
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					logger_error.warning("Неизвестная ошибка при загрузке");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке", "Exception",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnFile.add(mntmLoad);

		JMenu mnLevel = new JMenu("Уровень");
		menuBar.add(mnLevel);

		JMenuItem mntmLevelSave = new JMenuItem("Сохранить");
		mntmLevelSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("lvl", "lvl");
					filechooser.setFileFilter(filter);
					int loc = filechooser.showDialog(null, "Сохранить");
					if (loc == JFileChooser.APPROVE_OPTION) {
						File file = filechooser.getSelectedFile();
						panel.SavePresentLevel(file.getAbsolutePath() + ".lvl");
						logger.info("Сохранение прошло успешно");
						JOptionPane.showMessageDialog(null, "Сохранение прошло успешно");
						panel.repaint();
					}
				} catch (Exception ex) {
					logger_error.warning("Неизвестная ошибка при сохранении");
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при сохранении", "Exception",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnLevel.add(mntmLevelSave);

		JMenuItem mntmLevelLoad = new JMenuItem("Загрузить");
		mntmLevelLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("lvl", "lvl");
					filechooser.setFileFilter(filter);
					int loc = filechooser.showDialog(null, "Загрузить");
					if (loc == JFileChooser.APPROVE_OPTION) {
						File file = filechooser.getSelectedFile();
						panel.LoadPresentLevel(file.getAbsolutePath());
						JOptionPane.showMessageDialog(null, "Загрузили");
						logger.info("Загрузили");
						panel.repaint();
					} else {
						logger.info("Не удалось загрузить");
						JOptionPane.showMessageDialog(null, "Не удалось загрузить");
					}
				} catch (IOException ex) {
					logger_error.warning("Неизвестная ошибка при загрузке " + ex.getMessage());
					JOptionPane.showMessageDialog(frame, "Неизвестная ошибка при загрузке" + ex.getMessage(),
							"Exception", JOptionPane.ERROR_MESSAGE);
				} catch (ParkingOccupiedPlaceException ex) {
					logger_error.warning("Ошибка загрузки" + ex.getMessage());
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnLevel.add(mntmLevelLoad);

		JButton btnSort = new JButton("\u0421\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.sort();
				panel.repaint();
				logger.info("Сортировка уровней");
			}
		});
		btnSort.setBounds(703, 428, 122, 23);
		frame.getContentPane().add(btnSort);

		JButton btnShowProperties = new JButton("\u0421\u0432\u043E\u0439\u0441\u0442\u0432\u0430");
		btnShowProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Properties properties = new Properties();
				properties.showProperties(panel.getProperties());
				properties.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				properties.setVisible(true);
			}
		});
		btnShowProperties.setBounds(705, 454, 120, 23);
		frame.getContentPane().add(btnShowProperties);
	}
}