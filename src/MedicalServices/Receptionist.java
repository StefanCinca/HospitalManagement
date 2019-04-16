package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class Receptionist {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist window = new Receptionist();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	/**
	 * Create the application.
	 */
	public Receptionist() {
		initialize();
		frame.setVisible(true);
	}
	
	public void LogOut(JLabel logout)
	{
		logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MainFrame window = new MainFrame();
				window.getFrmLantDePoliclinici().setVisible(true);
				
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		/*frame.getContentPane().setLayout(null);*/
		
		JPanel ReceptionistPanel = new JPanel();
		frame.getContentPane().add(ReceptionistPanel, BorderLayout.WEST);
		ReceptionistPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ReceptionistPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		GridBagLayout gbl_Receptionist = new GridBagLayout();
		gbl_Receptionist.columnWeights = new double[]{1.0};
		ReceptionistPanel.setLayout(gbl_Receptionist);
		JLabel ReceptionistLabel = new JLabel("Receptionist");
		ReceptionistLabel.setIcon(null);
		ReceptionistLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ReceptionistLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		ReceptionistPanel.add(ReceptionistLabel, gbc_Welcome);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel logout = new JLabel("");
		LogOut(logout);
		logout.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/logout.png")));
		logout.setBounds(12, 0, 109, 64);
		panel.add(logout);
		
		JLabel lblPatient = new JLabel("Add patient");
		lblPatient.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				AddPatient window = new AddPatient();
				frame.dispose();
			}
		});
		lblPatient.setBackground(Color.LIGHT_GRAY);
		lblPatient.setIcon(new ImageIcon(Receptionist.class.getResource("/MedicalServices/patient.png")));
		lblPatient.setBounds(39, 234, 173, 77);
		lblPatient.setOpaque(true);
		panel.add(lblPatient);
		
		JLabel lblAppointment = new JLabel("Appointment");
		lblAppointment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Appointment window = new Appointment();
				frame.dispose();
			}
		});
		lblAppointment.setBackground(Color.LIGHT_GRAY);
		lblAppointment.setIcon(new ImageIcon(Receptionist.class.getResource("/MedicalServices/appointment.png")));
		lblAppointment.setBounds(290, 234, 173, 77);
		lblAppointment.setOpaque(true);
		panel.add(lblAppointment);
		
		JLabel lblReceipt = new JLabel("Receipt");
		lblReceipt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Receipt window = new Receipt();
				frame.dispose();
			}
		});
		lblReceipt.setBackground(Color.LIGHT_GRAY);
		lblReceipt.setIcon(new ImageIcon(Receptionist.class.getResource("/MedicalServices/payment.png")));
		lblReceipt.setBounds(541, 234, 173, 77);
		lblReceipt.setOpaque(true);
		panel.add(lblReceipt);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InfoReceptionist window = new InfoReceptionist();
				frame.dispose();
			}
		});
		lblInfo.setIcon(new ImageIcon(Receptionist.class.getResource("/MedicalServices/info-admin.png")));
		lblInfo.setBounds(105, 0, 64, 64);
		panel.add(lblInfo);
		
		JLabel lblSchedule = new JLabel("");
		lblSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReceptionistSchedule window = new ReceptionistSchedule();
				frame.dispose();
			}
		});
		lblSchedule.setIcon(new ImageIcon(Receptionist.class.getResource("/MedicalServices/schedule.png")));
		lblSchedule.setBounds(212, 0, 70, 64);
		panel.add(lblSchedule);
		
		
		JLabel lblSalary = new JLabel("");
		lblSalary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalculateSalary window = new CalculateSalary();
				frame.dispose();
			}
		});
		lblSalary.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/money.png")));
		lblSalary.setBounds(304, 13, 64, 51);
		panel.add(lblSalary);
		
		
		
		
	}
}
