
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FormAirplane {

	private JFrame frame;

	Airplane airplane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAirplane window = new FormAirplane();
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
	public FormAirplane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new PanelAirplane();
		panel.setBounds(0, 0, 681, 385);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnAirplane = new JButton("Airplane");
		btnAirplane.setBackground(Color.ORANGE);
		btnAirplane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((PanelAirplane) panel).renovate(panel.getSize().width, panel.getSize().height, false);
				panel.repaint();
			}
		});
		btnAirplane.setBounds(10, 43, 107, 23);
		panel.add(btnAirplane);

		JButton btnFighter = new JButton("Fighter");
		btnFighter.setBackground(Color.BLUE);
		btnFighter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((PanelAirplane) panel).renovate(panel.getSize().width, panel.getSize().height, true);
				panel.repaint();
			}
		});
		btnFighter.setBounds(10, 16, 107, 23);
		panel.add(btnFighter);

		JButton btnNewButton_Up = new JButton("U");
		btnNewButton_Up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelAirplane) panel).MoveTransport(Direction.Up);
				panel.repaint();
			}
		});
		btnNewButton_Up.setBounds(589, 302, 45, 40);
		panel.add(btnNewButton_Up);

		JButton btnNewButton_Left = new JButton("L");
		btnNewButton_Left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelAirplane) panel).MoveTransport(Direction.Left);
				panel.repaint();
			}
		});
		btnNewButton_Left.setBounds(541, 345, 45, 40);
		panel.add(btnNewButton_Left);

		JButton btnNewButton_Down = new JButton("D");
		btnNewButton_Down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelAirplane) panel).MoveTransport(Direction.Down);
				panel.repaint();
			}
		});
		btnNewButton_Down.setBounds(589, 345, 45, 40);
		panel.add(btnNewButton_Down);

		JButton btnNewButton_Right = new JButton("R");
		btnNewButton_Right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((PanelAirplane) panel).MoveTransport(Direction.Right);
				panel.repaint();
			}
		});
		btnNewButton_Right.setBounds(636, 345, 45, 40);
		panel.add(btnNewButton_Right);
	}
}
