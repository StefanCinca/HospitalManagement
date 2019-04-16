package MedicalServices;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpdateUserSuperAdmin {

	private JFrame frame;
	private JTextField CNP;
	private String StringCNP;
	private JLabel Nume;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserSuperAdmin window = new UpdateUserSuperAdmin();
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
	public UpdateUserSuperAdmin() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel AddDoctor = new JLabel("Doctor");
		AddDoctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					UpdateDoctor window = new UpdateDoctor();
			}
			
		});
		AddDoctor.setBackground(Color.LIGHT_GRAY);
		AddDoctor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddDoctor.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		AddDoctor.setBounds(138, 170, 155, 82);
		frame.getContentPane().add(AddDoctor);
		AddDoctor.setOpaque(true);
		
		JLabel AddNurse = new JLabel("Nurse");
		AddNurse.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				UpdateNurse window = new UpdateNurse();
			}
		});
		AddNurse.setBackground(Color.LIGHT_GRAY);
		AddNurse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddNurse.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		AddNurse.setBounds(385, 173, 155, 77);
		frame.getContentPane().add(AddNurse);
		AddNurse.setOpaque(true);
		
		JLabel AddAccountant = new JLabel("Accountant");
		AddAccountant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateAccountant window = new UpdateAccountant();
			}
		});
		AddAccountant.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddAccountant.setBackground(Color.LIGHT_GRAY);
		AddAccountant.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/calculator.png")));
		AddAccountant.setBounds(638, 173, 153, 77);
		frame.getContentPane().add(AddAccountant);
		AddAccountant.setOpaque(true);
		
		JLabel AddReceptionist = new JLabel("Receptionist");
		AddReceptionist.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddReceptionist.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				UpdateReceptionist window = new UpdateReceptionist();
			}
		});
		AddReceptionist.setBackground(Color.LIGHT_GRAY);
		AddReceptionist.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/reception.png")));
		AddReceptionist.setBounds(385, 321, 155, 77);
		frame.getContentPane().add(AddReceptionist);
		AddReceptionist.setOpaque(true);
		
		JLabel AddHRManager = new JLabel("HRManager");
		AddHRManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateHR window = new UpdateHR();
			}
		});
		AddHRManager.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddHRManager.setBackground(Color.LIGHT_GRAY);
		AddHRManager.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/recruitment.png")));
		AddHRManager.setBounds(638, 321, 155, 77);
		frame.getContentPane().add(AddHRManager);
		AddHRManager.setOpaque(true);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				if (Login.isAdmin == true)
				{
					Admin windowAdmin = new Admin();
					windowAdmin.getFrame().setVisible(true);
				}
				if (Login.isSuperAdmin == true)
				{
					SuperAdmin windowSuper = new SuperAdmin();
					windowSuper.getFrame().setVisible(true);
				}
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);
		
		JLabel Admin = new JLabel("Admin");
		Admin.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				UpdateAdmin window = new UpdateAdmin();
			}
		});
		Admin.setBackground(Color.LIGHT_GRAY);
	    Admin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Admin.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/admin.png")));
		Admin.setBounds(138, 321, 155, 77);
		Admin.setOpaque(true);
		frame.getContentPane().add(Admin);
	}

}
