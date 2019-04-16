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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Medic {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Medic window = new Medic();
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
	public Medic() {
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
		
		
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ScheduleMedic window = new ScheduleMedic();
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		lblSchedule.setBackground(Color.LIGHT_GRAY);
		lblSchedule.setIcon(new ImageIcon(Medic.class.getResource("/MedicalServices/schedule.png")));
		lblSchedule.setBounds(198, 283, 141, 82);
		lblSchedule.setOpaque(true);
		
		frame.getContentPane().add(lblSchedule);
		JLabel logout = new JLabel("");
		LogOut(logout);
		logout.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/logout.png")));
		logout.setBounds(129, 21, 73, 64);
		frame.getContentPane().add(logout);
		
		JLabel lblReports = new JLabel("See Reports");
		lblReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PacientReportsMedic window = new PacientReportsMedic();
				frame.dispose();
			}
		});
		lblReports.setBackground(Color.LIGHT_GRAY);
		lblReports.setIcon(new ImageIcon(Medic.class.getResource("/MedicalServices/folder (1).png")));
		lblReports.setBounds(409, 283, 138, 82);
		lblReports.setOpaque(true);
		frame.getContentPane().add(lblReports);
		
		JLabel lblAddReport = new JLabel("Add Report");
		lblAddReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddReportMedic window = new AddReportMedic();
				frame.dispose();
			}
		});
		lblAddReport.setBackground(Color.LIGHT_GRAY);
		lblAddReport.setIcon(new ImageIcon(Medic.class.getResource("/MedicalServices/clinic-history.png")));
		lblAddReport.setBounds(612, 283, 141, 82);
		lblAddReport.setOpaque(true);
		frame.getContentPane().add(lblAddReport);
		
		
		JPanel AdminPanel = new JPanel();
		AdminPanel.setBounds(0, 0, 106, 553);
		frame.getContentPane().add(AdminPanel);
		AdminPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AdminPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		GridBagLayout gbl_AdminPanel = new GridBagLayout();
		gbl_AdminPanel.columnWeights = new double[]{1.0};
		AdminPanel.setLayout(gbl_AdminPanel);
		JLabel AdminLabel = new JLabel("Doctor");
		AdminLabel.setIcon(null);
		AdminLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AdminLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		AdminPanel.add(AdminLabel, gbc_Welcome);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MedicSchedule window = new MedicSchedule();
				frame.dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Medic.class.getResource("/MedicalServices/schedule.png")));
		lblNewLabel.setBounds(214, 21, 64, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InfoMedic window = new InfoMedic();
				frame.dispose();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(Medic.class.getResource("/MedicalServices/info-admin.png")));
		lblNewLabel_1.setBounds(290, 21, 64, 56);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblSalary = new JLabel("");
		lblSalary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalculateSalary window = new CalculateSalary();
				frame.dispose();
			}
		});
		lblSalary.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/money.png")));
		lblSalary.setBounds(376, 26, 64, 51);
		frame.getContentPane().add(lblSalary);
		
	}
}
