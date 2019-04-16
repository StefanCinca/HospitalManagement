package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

public class Accountant {

	private JFrame frame;
	public static boolean AccountantType = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accountant window = new Accountant();
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
	public Accountant() {
		initialize();
		AccountantType = false;
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		JLabel logout = new JLabel("");
		LogOut(logout);
		logout.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/logout.png")));
		logout.setBounds(150, 13, 80, 58);
		frame.getContentPane().add(logout);
		
		JLabel lblProfit = new JLabel("Profit");
		lblProfit.setBackground(Color.LIGHT_GRAY);
		lblProfit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Profit window = new Profit();
				AccountantType = true;
				frame.dispose();
			}
		});
		lblProfit.setIcon(new ImageIcon(Accountant.class.getResource("/MedicalServices/change.png")));
		lblProfit.setBounds(164, 282, 158, 87);
		lblProfit.setOpaque(true);
		frame.getContentPane().add(lblProfit);
		
		JLabel lblSchedule = new JLabel("View Schedule");
		lblSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListSchedule windowSchedule = new ListSchedule();
				AccountantType = true;
				frame.dispose();
			}
		});
		lblSchedule.setBackground(Color.LIGHT_GRAY);
		lblSchedule.setIcon(new ImageIcon(Accountant.class.getResource("/MedicalServices/schedule.png")));
		lblSchedule.setBounds(407, 282, 158, 87);
		lblSchedule.setOpaque(true);
		frame.getContentPane().add(lblSchedule);
		
		JLabel lblVacations = new JLabel("View Vacations");
		lblVacations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListVacations windowVacations = new ListVacations();
				AccountantType = true;
				frame.dispose();
			}
		});
		lblVacations.setBackground(Color.LIGHT_GRAY);
		lblVacations.setIcon(new ImageIcon(Accountant.class.getResource("/MedicalServices/vacation.png")));
		lblVacations.setBounds(650, 281, 158, 88);
		lblVacations.setOpaque(true);
		frame.getContentPane().add(lblVacations);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InfoAccountant window = new InfoAccountant();
				frame.dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Accountant.class.getResource("/MedicalServices/info-admin.png")));
		lblNewLabel.setBounds(243, 13, 64, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSalary = new JLabel("");
		lblSalary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalculateSalary window = new CalculateSalary();
				frame.dispose();
			}
		});
		lblSalary.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/money.png")));
		lblSalary.setBounds(311, 13, 64, 58);
		frame.getContentPane().add(lblSalary);
	}
}
