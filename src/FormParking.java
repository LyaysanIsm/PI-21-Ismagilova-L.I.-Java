import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.List;

public class FormParking {

	private JFrame frame;
	private JPanel takeAirplane;
	private JTextField postextField;
	private ITransport airplane;

	ArrayList<ITransport> arrlist = new ArrayList<ITransport>();

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
						Color.green, true, true, (int) (Math.random() * 3));
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
						Color.orange, Color.darkGray, true, true, true, true, (int) (Math.random() * 3));
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
	}
}
