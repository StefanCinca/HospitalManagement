package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;

public class Asistent {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Asistent window = new Asistent();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	public JFrame getFrame()
	{
		return frame;
	}

	/**
	 * Create the application.
	 */
	public Asistent() {
		initialize();
		frame.setVisible(true);
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
		JLabel ReceptionistLabel = new JLabel("Asistent");
		ReceptionistLabel.setIcon(null);
		ReceptionistLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ReceptionistLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		ReceptionistPanel.add(ReceptionistLabel, gbc_Welcome);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel logout = new JLabel("");
		panel.add(logout);
		LogOut(logout);
		logout.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/logout.png")));
		logout.setBounds(0, 0, 99, 64);
		
		JLabel Report = new JLabel("Medical report");
		panel.add(Report);
		Report.setOpaque(true);
		Report.setBackground(Color.LIGHT_GRAY);
		Report.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MedicalReport window = new MedicalReport();
				frame.dispose();
			}
		});
		Report.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/medical-history.png")));
		Report.setBounds(326, 263, 162, 91);
		
		JLabel lblSchedule = new JLabel("");
		lblSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AsistentSchedule window = new AsistentSchedule();
				frame.dispose();
			}
		});
		lblSchedule.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/schedule.png")));
		lblSchedule.setBounds(205, 0, 70, 64);
		panel.add(lblSchedule);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InfoAsistent window = new InfoAsistent();
				frame.dispose();
			}
		});
		lblInfo.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/info-admin.png")));
		lblInfo.setBounds(102, 13, 56, 51);
		panel.add(lblInfo);
		
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
