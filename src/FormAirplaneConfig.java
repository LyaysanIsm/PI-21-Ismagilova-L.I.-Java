import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FormAirplaneConfig {

	JFrame frame;
	private ITransport plane = null;
	private IWeapons weapons = null;
	private Color color = null;
	private Logger logger_error;

	AirplaneDelegate AddAirplane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAirplaneConfig window = new FormAirplaneConfig(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormAirplaneConfig(AirplaneDelegate delegate) {
		AddAirplane = delegate;
		initialize();
	}

	private void initialize() {
		logger_error = Logger.getLogger(FormParking.class.getName() + "2");
		try {
			FileHandler fh_error = null;
			fh_error = new FileHandler("D:\\file_error.txt");
			logger_error.addHandler(fh_error);
			logger_error.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();
			fh_error.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 645, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		PanelAirplane panel = new PanelAirplane();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (plane != null) {
					panel.setAirplane(plane);
					panel.repaint();
				}
				if (weapons != null) {
					if (panel.getTransportplane() != null) {
						((Fighter) panel.getTransportplane()).setWeapons(weapons);
						panel.repaint();
					}
				}
			}
		});
		panel.setBackground(Color.WHITE);
		panel.setBounds(195, 42, 115, 94);
		frame.getContentPane().add(panel);

		JLabel lblAirplane = new JLabel("Airplane");
		lblAirplane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				plane = new Airplane((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000,
						Color.green, true, true, (int) (Math.random() * 3));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				plane = null;
			}
		});
		lblAirplane.setBounds(15, 16, 69, 20);
		frame.getContentPane().add(lblAirplane);

		JLabel lblFighter = new JLabel("Fighter");
		lblFighter.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				plane = new Fighter((int) (Math.random() * 200) + 100, (int) (Math.random() * 1000) + 1000,
						Color.orange, Color.darkGray, true, true, true, true, (int) (Math.random() * 3));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				plane = null;
			}
		});
		lblFighter.setBounds(15, 45, 132, 20);
		frame.getContentPane().add(lblFighter);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(15, 251, 115, 29);
		frame.getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddAirplane.induce(panel.getTransportplane());
					frame.dispose();
				} catch (ParkingOverflowException ex) {
					logger_error.warning("Парковка переполнена");
					JOptionPane.showMessageDialog(frame, "Парковка переполнена", "Exception",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOk.setBounds(15, 206, 115, 29);
		frame.getContentPane().add(btnOk);

		JLabel lblWeapons = new JLabel("Weapons");
		lblWeapons.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				weapons = new Weapons((int) (Math.random() * 3) + 4);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				weapons = null;
			}
		});
		lblWeapons.setBounds(15, 90, 132, 20);
		frame.getContentPane().add(lblWeapons);

		JLabel lblWeaponsRectangle = new JLabel("Weapons Rectangle");
		lblWeaponsRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				weapons = new WeaponsRectangle((int) (Math.random() * 3) + 4);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				weapons = null;
			}
		});
		lblWeaponsRectangle.setBounds(15, 116, 165, 20);
		frame.getContentPane().add(lblWeaponsRectangle);

		JLabel lblWeaponsSquare = new JLabel("Weapons Square");
		lblWeaponsSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				weapons = new WeaponsSquare((int) (Math.random() * 3) + 4);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				weapons = null;
			}
		});
		lblWeaponsSquare.setBounds(15, 141, 165, 20);
		frame.getContentPane().add(lblWeaponsSquare);

		JLabel lblMainColor = new JLabel("Main Color");
		lblMainColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (color != null && panel.getTransportplane() != null) {
					((Airplane) panel.getTransportplane()).setColor(color);
					color = null;
					panel.repaint();
				}
			}
		});
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBounds(195, 170, 115, 20);
		frame.getContentPane().add(lblMainColor);

		JLabel lblDopColor = new JLabel("Dop Color");
		lblDopColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (color != null && panel.getTransportplane() != null
						&& panel.getTransportplane() instanceof Fighter) {
					((Fighter) panel.getTransportplane()).setDopColor(color);
					color = null;
					panel.repaint();
				}
			}
		});
		lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDopColor.setBounds(195, 210, 115, 20);
		frame.getContentPane().add(lblDopColor);

		JPanel panelWhite = new JPanel();
		panelWhite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.white;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(429, 42, 40, 40);
		frame.getContentPane().add(panelWhite);

		JPanel panelBlack = new JPanel();
		panelBlack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.black;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(379, 42, 40, 40);
		frame.getContentPane().add(panelBlack);

		JPanel panelRed = new JPanel();
		panelRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.red;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(379, 141, 40, 40);
		frame.getContentPane().add(panelRed);

		JPanel panelGray = new JPanel();
		panelGray.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.gray;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(379, 190, 40, 40);
		frame.getContentPane().add(panelGray);

		JPanel panelBlue = new JPanel();
		panelBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.blue;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(429, 90, 40, 40);
		frame.getContentPane().add(panelBlue);

		JPanel panelYellow = new JPanel();
		panelYellow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.yellow;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(429, 141, 40, 40);
		frame.getContentPane().add(panelYellow);

		JPanel panelOrange = new JPanel();
		panelOrange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.orange;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(429, 190, 40, 40);
		frame.getContentPane().add(panelOrange);

		JPanel panelGreen = new JPanel();
		panelGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = Color.green;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				color = null;
			}
		});
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(379, 90, 40, 40);
		frame.getContentPane().add(panelGreen);
	}
}