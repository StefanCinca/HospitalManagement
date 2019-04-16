package MedicalServices;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainFrame {

	private JFrame frmLantDePoliclinici;
	private JTextField username2 = new JTextField();
	private JPasswordField password2 = new JPasswordField();
	public static final int DOCTORTYPE = 0;
	public static final int NURSETYPE = 1;
	public static final int RECEPTIONISTTYPE = 2;
	public static final int ACCOUNTANTTYPE = 3;
	public static final int HRMANAGERTYPE = 4;
	public static final int ADMINTYPE = 5;
	public static final int SUPERADMINTYPE = 6;
	public static int TYPE = -1;
	
	public MainFrame() {
		initialize();
		getFrmLantDePoliclinici().setVisible(true);
	}

	public JFrame getFrmLantDePoliclinici() {
		return frmLantDePoliclinici;
	}
	
	public void goToLogin(JLabel X, int type)
	{
			X.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				TYPE = type;
				Login window = new Login(type);
				window.getFrame().setVisible(true);
				username2 = window.getUsername();
				 username2.addFocusListener(new FocusListener()
						 {
					 		public void focusGained(FocusEvent e)
					 		{
					 			username2.setForeground(Color.black);
					 			username2.setBorder(BorderFactory.createLineBorder(Color.black));
					 			if (username2.getText().equals("Username"))
					 			{
					 				
					 				username2.setText("");
					 			}
					 		}
					 		public void focusLost(FocusEvent e)
					 		{
					 			username2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					 			if (username2.getText().equals(""))
					 			{
					 				username2.setForeground(Color.LIGHT_GRAY);
					 				username2.setText("Username");
					 			}
					 		}
						 });
				 password2 = window.getPassword();
				 password2.addFocusListener(new FocusListener()
						 {
					 		public void focusGained(FocusEvent e)
					 		{
					 			password2.setForeground(Color.black);
					 			password2.setBorder(BorderFactory.createLineBorder(Color.black));
					 			password2.setEchoChar('*');
					 			if (Arrays.equals(password2.getPassword(), new char[]{'P','a','s','s','w','o','r','d'}))
					 			{
					 				password2.setText("");
					 			}
					 		}
					 		public void focusLost(FocusEvent e)
					 		{
					 			password2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					 			if (password2.getPassword().length == 0)
					 			{
					 				password2.setEchoChar((char)0);
					 				password2.setForeground(Color.LIGHT_GRAY);
					 				password2.setText("Password");
					 			}
					 		}
						 });
				frmLantDePoliclinici.setVisible(false);
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLantDePoliclinici = new JFrame();
		frmLantDePoliclinici.setTitle("Hospitals");
		frmLantDePoliclinici.setBounds(1000,1000, 1400, 600);
		frmLantDePoliclinici.setLocationRelativeTo(null);
		frmLantDePoliclinici.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSystem = new JLabel("System Management");
		 lblSystem .setForeground(UIManager.getColor("EditorPane.caretForeground"));
		 lblSystem .setBackground(UIManager.getColor("EditorPane.background"));
		 lblSystem .setHorizontalAlignment(SwingConstants.CENTER);
		 lblSystem .setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/Management System.png")));
		frmLantDePoliclinici.getContentPane().add(lblSystem, BorderLayout.NORTH);
		
		JPanel PanelWelcome = new JPanel();
		PanelWelcome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		PanelWelcome.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		frmLantDePoliclinici.getContentPane().add(PanelWelcome, BorderLayout.WEST);
		GridBagLayout gbl_PanelWelcome = new GridBagLayout();
		gbl_PanelWelcome.columnWeights = new double[]{1.0};
		PanelWelcome.setLayout(gbl_PanelWelcome);
		
		JLabel Welcome = new JLabel("Welcome to our Hospitals");
		Welcome.setIcon(null);
		Welcome.setHorizontalAlignment(SwingConstants.CENTER);
		Welcome.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		PanelWelcome.add(Welcome, gbc_Welcome);
		
		JLabel Hospital = new JLabel("");
		Hospital.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/hospital.png")));
		GridBagConstraints gbc_Hospital = new GridBagConstraints();
		gbc_Hospital.anchor = GridBagConstraints.SOUTH;
		gbc_Hospital.gridx = 0;
		gbc_Hospital.gridy = 1;
		PanelWelcome.add(Hospital, gbc_Hospital);
		
		JPanel Panel_Icons = new JPanel();
		frmLantDePoliclinici.getContentPane().add(Panel_Icons, BorderLayout.CENTER);
		Panel_Icons.setLayout(null);
		JLabel Admin = new JLabel("Admin");
		Admin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Admin.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/admin.png")));
		Admin.setOpaque(true);
		Admin.setBackground(Color.lightGray);
		Admin.setBounds(653, 72, 143, 100);
		Panel_Icons.add(Admin);
		goToLogin(Admin, ADMINTYPE);
		
		JLabel Doctor = new JLabel("Doctor");
		Doctor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Doctor.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/doctor.png")));
		Doctor.setOpaque(true);
		Doctor.setBackground(Color.lightGray);
		Doctor.setBounds(63, 202, 143, 100);
		Panel_Icons.add(Doctor);
		goToLogin(Doctor, DOCTORTYPE);
		
		JLabel Receptioner = new JLabel("Receptionist");
		Receptioner.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Receptioner.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/reception.png")));
		Receptioner.setOpaque(true);
		Receptioner.setBackground(Color.lightGray);
		Receptioner.setBounds(531, 202, 143, 100);
		Panel_Icons.add(Receptioner);
		goToLogin(Receptioner, RECEPTIONISTTYPE);
		
		JLabel Accountant = new JLabel("Accountant");
		Accountant.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Accountant.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/calculator.png")));
		Accountant.setOpaque(true);
		Accountant.setBackground(Color.lightGray);
		Accountant.setBounds(759, 202, 143, 100);
		goToLogin(Accountant, ACCOUNTANTTYPE);
		Panel_Icons.add(Accountant);
		
		JLabel HR = new JLabel("HR Manager");
		HR.setFont(new Font("Times New Roman", Font.BOLD, 16));
		HR.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/recruitment.png")));
		
		HR.setOpaque(true);
		HR.setBackground(Color.lightGray);
		HR.setBounds(973, 202, 138, 100);
		Panel_Icons.add(HR);
		goToLogin(HR, HRMANAGERTYPE);
		
		JLabel Asistent = new JLabel("Nurse");
		Asistent.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Asistent.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/doctor.png")));
		Asistent.setBounds(296, 202, 143, 100);
		Panel_Icons.add(Asistent);
		Asistent.setOpaque(true);
		Asistent.setBackground(Color.lightGray);
		goToLogin(Asistent, NURSETYPE);
		
		JLabel SuperAdmin = new JLabel("SuperAdmin");
		SuperAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		SuperAdmin.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/admin.png")));
		SuperAdmin.setBounds(414, 72, 143, 100);
		Panel_Icons.add(SuperAdmin);
		SuperAdmin.setOpaque(true);
		SuperAdmin.setBackground(Color.lightGray);
		goToLogin(SuperAdmin, SUPERADMINTYPE);
		
	}
}
