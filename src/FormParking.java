import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.List;

public class FormParking {

	PanelParking panel;

	private JFrame frame;
	private JPanel takeAirplane;
	private JTextField postextField;
	private ITransport airplane;

	ArrayList<ITransport> arrlist = new ArrayList<ITransport>();

	class Delegate extends AirplaneDelegate {
		@Override
		public void induce(ITransport airplane) {
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
		frame = new JFrame();
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
				String s = postextField.getText();
				if (s != "") {
					int index = Integer.parseInt(s);
					airplane = panel.getTransport(index);
					takeAirplane.setAirplane(airplane);
					takeAirplane.repaint();
					panel.repaint();
					arrlist.add(airplane);
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
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				filechooser.setFileFilter(filter);
				int loc = filechooser.showDialog(null, "Сохранить");
				if (loc == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					panel.SaveInfo(file.getAbsolutePath() + ".txt");
				}
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Загрузить");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
				filechooser.setFileFilter(filter);
				int loc = filechooser.showDialog(null, "Загрузить");
				if (loc == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					panel.LoadInfo(file.getAbsolutePath());
					panel.repaint();
				}
			}
		});
		mnFile.add(mntmLoad);

		JMenu mnLevel = new JMenu("Уровень");
		menuBar.add(mnLevel);

		JMenuItem mntmLevelSave = new JMenuItem("Сохранить");
		mntmLevelSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("lvl", "lvl");
				filechooser.setFileFilter(filter);
				int loc = filechooser.showDialog(null, "Сохранить");
				if (loc == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					panel.SavePresentLevel(file.getAbsolutePath() + ".lvl");
					panel.repaint();
				}
			}
		});
		mnLevel.add(mntmLevelSave);

		JMenuItem mntmLevelLoad = new JMenuItem("Загрузить");
		mntmLevelLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("lvl", "lvl");
				filechooser.setFileFilter(filter);
				int loc = filechooser.showDialog(null, "Загрузить");
				if (loc == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					panel.LoadPresentLevel(file.getAbsolutePath());
					panel.repaint();
				}
			}
		});
		mnLevel.add(mntmLevelLoad);
	}
}