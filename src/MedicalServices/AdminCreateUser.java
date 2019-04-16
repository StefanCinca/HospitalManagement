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

public class AdminCreateUser {

	private JFrame frame;
	public static int Type = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminCreateUser window = new AdminCreateUser();
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
	public AdminCreateUser() {
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
		AddDoctor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddDoctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Type = 1;
				CreateUsernamePassword window = new CreateUsernamePassword();
				frame.dispose();
			}
			
		});
		AddDoctor.setBackground(Color.LIGHT_GRAY);
		AddDoctor.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		AddDoctor.setBounds(110, 185, 146, 85);
		frame.getContentPane().add(AddDoctor);
		AddDoctor.setOpaque(true);
		
		JLabel AddNurse = new JLabel("Nurse");
		AddNurse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddNurse.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				Type = 2;
				CreateUsernamePassword window = new CreateUsernamePassword();
				frame.dispose();
			}
		});
		AddNurse.setBackground(Color.LIGHT_GRAY);
		AddNurse.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		AddNurse.setBounds(372, 185, 146, 85);
		frame.getContentPane().add(AddNurse);
		AddNurse.setOpaque(true);
		
		JLabel AddAccountant = new JLabel("Accountant");
		AddAccountant.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddAccountant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Type = 3;
				CreateUsernamePassword window = new CreateUsernamePassword();
				frame.dispose();
			}
		});
		AddAccountant.setBackground(Color.LIGHT_GRAY);
		AddAccountant.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/calculator.png")));
		AddAccountant.setBounds(632, 185, 146, 89);
		frame.getContentPane().add(AddAccountant);
		AddAccountant.setOpaque(true);
		
		JLabel AddReceptionist = new JLabel("Receptionist");
		AddReceptionist.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddReceptionist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Type = 4;
				CreateUsernamePassword window = new CreateUsernamePassword();
				frame.dispose();
			}
		});
		AddReceptionist.setBackground(Color.LIGHT_GRAY);
		AddReceptionist.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/reception.png")));
		AddReceptionist.setBounds(247, 323, 146, 85);
		frame.getContentPane().add(AddReceptionist);
		AddReceptionist.setOpaque(true);
		
		JLabel AddHRManager = new JLabel("HRManager");
		AddHRManager.setFont(new Font("Times New Roman", Font.BOLD, 15));
		AddHRManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Type = 5;
				CreateUsernamePassword window = new CreateUsernamePassword();
				frame.dispose();
			}
		});
		
		AddHRManager.setBackground(Color.LIGHT_GRAY);
		AddHRManager.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/recruitment.png")));
		AddHRManager.setBounds(521, 323, 146, 85);
		frame.getContentPane().add(AddHRManager);
		AddHRManager.setOpaque(true);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				Admin window = new Admin();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(40, 13, 95, 48);
		frame.getContentPane().add(Back);
		
		
		
	}
	}


