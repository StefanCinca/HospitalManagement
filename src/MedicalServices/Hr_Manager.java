package MedicalServices;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class Hr_Manager {

	private JFrame frame;

	
	public JFrame getFrame()
	{
		return frame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hr_Manager window = new Hr_Manager();
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
	
	public void Angajeaza(JLabel addUser)
	{
		addUser.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				NewUser window = new NewUser();
				frame.dispose();
				
			}
		});
	}
	
	public void InfoHR(JPanel panel)
	{
	}

	/**
	 * Create the application.
	 */
	public Hr_Manager() {
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
		
		JPanel Panel_HR_Icons = new JPanel();
		frame.getContentPane().add(Panel_HR_Icons, BorderLayout.CENTER);
		JLabel AddUser = new JLabel("Employ");
		Angajeaza(AddUser);
		AddUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		Panel_HR_Icons.setLayout(null);
		AddUser.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/add-user.png")));
		AddUser.setOpaque(true);
		AddUser.setBackground(Color.lightGray);
		AddUser.setBounds(46, 211, 155, 100);
		Panel_HR_Icons.add(AddUser);
		
		JPanel HRPanel = new JPanel();
		frame.getContentPane().add(HRPanel, BorderLayout.WEST);
		HRPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		HRPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		GridBagLayout gbl_HRPanel = new GridBagLayout();
		gbl_HRPanel.columnWeights = new double[]{1.0};
		HRPanel.setLayout(gbl_HRPanel);
		JLabel HRLabel = new JLabel("HR Manager");
		HRLabel.setIcon(null);
		HRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HRLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		HRPanel.add(HRLabel, gbc_Welcome);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 77, 64);
		Panel_HR_Icons.add(panel);
		InfoHR(panel);
		
		
		JLabel logout = new JLabel("");
		panel.add(logout);
		LogOut(logout);
		logout.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/logout.png")));
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchEmployee window = new SearchEmployee();
				frame.dispose();
			}
		});
		lblSearch.setBackground(Color.LIGHT_GRAY);
		lblSearch.setIcon(new ImageIcon(Hr_Manager.class.getResource("/MedicalServices/search.png")));
		lblSearch.setBounds(304, 211, 155, 100);
		Panel_HR_Icons.add(lblSearch);
		lblSearch.setOpaque(true);
		
		JLabel Schedule = new JLabel("Add");
		Schedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddSchedule window = new AddSchedule();
				frame.dispose();
			}
		});
		Schedule.setHorizontalAlignment(SwingConstants.CENTER);
		Schedule.setBackground(Color.LIGHT_GRAY);
		Schedule.setIcon(new ImageIcon(Hr_Manager.class.getResource("/MedicalServices/calendar.png")));
		Schedule.setBounds(532, 212, 147, 100);
		Schedule.setOpaque(true);
		Panel_HR_Icons.add(Schedule);
		
		JLabel lblGiveVacation = new JLabel("Give Vacation");
		lblGiveVacation.setBackground(Color.LIGHT_GRAY);
		lblGiveVacation.setIcon(new ImageIcon(Hr_Manager.class.getResource("/MedicalServices/trekking.png")));
		lblGiveVacation.setBounds(46, 372, 155, 87);
		lblGiveVacation.setOpaque(true);
		lblGiveVacation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Vacation window = new Vacation();
				frame.dispose();
			}
		});
		Panel_HR_Icons.add(lblGiveVacation);
		
		JLabel lblScheduleAll = new JLabel("View Schedule");
		lblScheduleAll.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				ListSchedule window = new ListSchedule();
				frame.dispose();
			}
		});
		lblScheduleAll.setBackground(Color.LIGHT_GRAY);
		lblScheduleAll.setIcon(new ImageIcon(Hr_Manager.class.getResource("/MedicalServices/schedule.png")));
		lblScheduleAll.setBounds(304, 372, 155, 87);
		lblScheduleAll.setOpaque(true);
		Panel_HR_Icons.add(lblScheduleAll);
		
		JLabel lblVacations = new JLabel("View Vacations");
		lblVacations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ListVacations window = new ListVacations();
				frame.dispose();
			}
		});
		lblVacations.setBackground(Color.LIGHT_GRAY);
		lblVacations.setIcon(new ImageIcon(Hr_Manager.class.getResource("/MedicalServices/vacation.png")));
		lblVacations.setBounds(532, 372, 162, 87);
		lblVacations.setOpaque(true);
		Panel_HR_Icons.add(lblVacations);
		
		JLabel lblSalary = new JLabel("");
		Panel_HR_Icons.add(lblSalary);
		lblSalary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CalculateSalary window = new CalculateSalary();
				frame.dispose();
			}
		});
		lblSalary.setIcon(new ImageIcon(Asistent.class.getResource("/MedicalServices/money.png")));
		lblSalary.setBounds(177, 13, 64, 64);
		JLabel Info = new JLabel("");
		Info.setBounds(101, 13, 64, 64);
		Panel_HR_Icons.add(Info);
		Info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				InfoHR window = new InfoHR();
				frame.dispose();
			}
		});
		Info.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/info-admin.png")));
		
		
		
		
		
	}
}
