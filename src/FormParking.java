import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class FormParking {

	private JFrame frame;
	private JTextField postextField;
	private ITransport airplane;
	private JPanel takeAirplane;

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

		PanelParking panel = new PanelParking();
		panel.setBounds(15, 16, 636, 484);
		frame.getContentPane().add(panel);

		JButton btnAirplane = new JButton("Airplane");
		btnAirplane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Airplane airplane = new Airplane((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000,
						Color.green, true, true);
				panel.Add(airplane);
				panel.repaint();
			}
		});
		btnAirplane.setBounds(695, 28, 148, 29);
		frame.getContentPane().add(btnAirplane);

		JButton btnFighter = new JButton("Fighter");
		btnFighter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fighter fighter = new Fighter((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000,
						Color.orange, Color.darkGray, true, true, true, true);
				panel.Add(fighter);
				panel.repaint();
			}
		});
		btnFighter.setBounds(695, 67, 148, 29);
		frame.getContentPane().add(btnFighter);

		PanelAirplane takeAirplane = new PanelAirplane();
		takeAirplane.setBounds(695, 177, 236, 118);
		frame.getContentPane().add(takeAirplane);

		JButton btnTakeAirplane = new JButton("Take Airplane");
		btnTakeAirplane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = Integer.parseInt(postextField.getText());
				ITransport airplane = panel.Delete(index);
				takeAirplane.setAirplane(airplane);
				panel.repaint();
				takeAirplane.repaint();
			}
		});
		btnTakeAirplane.setBounds(695, 138, 148, 29);
		frame.getContentPane().add(btnTakeAirplane);

		JButton btnAddSeveral = new JButton("Add Several Airplane");
		btnAddSeveral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 4;
				ITransport airplane;
				airplane = new Airplane((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000,
						Color.green, true, true);
				panel.AddSeveral(airplane, count);
				panel.repaint();
			}
		});
		btnAddSeveral.setBounds(695, 306, 170, 29);
		frame.getContentPane().add(btnAddSeveral);

		JButton btnNewButton = new JButton("Add Several Fighter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 4;
				ITransport airplane;
				airplane = new Fighter((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000,
						Color.orange, Color.darkGray, true, true, true, true);
				panel.AddSeveral(airplane, count);
				panel.repaint();
			}
		});
		btnNewButton.setBounds(695, 346, 170, 29);
		frame.getContentPane().add(btnNewButton);

		JButton btnDeleteSeveral = new JButton("Delete Several");
		btnDeleteSeveral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = Integer.parseInt(postextField.getText());
				airplane = panel.Delete(index);
				takeAirplane.setAirplane(airplane);
				panel.repaint();
				takeAirplane.repaint();
				for (int j = index; j < 15; j++) {
					airplane = panel.Delete(j);
					panel.repaint();
				}
			}
		});
		btnDeleteSeveral.setBounds(695, 386, 170, 29);
		frame.getContentPane().add(btnDeleteSeveral);

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(695, 107, 69, 20);
		frame.getContentPane().add(lblPosition);

		postextField = new JTextField();
		postextField.setBounds(757, 107, 24, 20);
		frame.getContentPane().add(postextField);
		postextField.setColumns(10);
	}
}