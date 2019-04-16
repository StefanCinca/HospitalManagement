package MedicalServices;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpdateUserAdmin {

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
					UpdateUserAdmin window = new UpdateUserAdmin();
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
	public UpdateUserAdmin() {
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
		
		JLabel UpdateDoctor = new JLabel("Doctor");
		UpdateDoctor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		UpdateDoctor.addMouseListener(new MouseAdapter() {
			@Override
					public void mouseClicked(MouseEvent arg0) {
						UpdateDoctor window = new UpdateDoctor();	
						frame.dispose();
					}
			
		});
		UpdateDoctor.setBackground(Color.LIGHT_GRAY);
		UpdateDoctor.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		UpdateDoctor.setBounds(122, 154, 152, 84);
		frame.getContentPane().add(UpdateDoctor);
		UpdateDoctor.setOpaque(true);
		
		JLabel UpdateNurse = new JLabel("Nurse");
		UpdateNurse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		UpdateNurse.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				UpdateNurse window = new UpdateNurse();
				frame.dispose();
			}
		});
		UpdateNurse.setBackground(Color.LIGHT_GRAY);
		UpdateNurse.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		UpdateNurse.setBounds(351, 160, 152, 78);
		frame.getContentPane().add(UpdateNurse);
		UpdateNurse.setOpaque(true);
		
		JLabel UpdateAccountant = new JLabel("Accountant");
		UpdateAccountant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateAccountant window = new UpdateAccountant();
				frame.dispose();
			}
		});
		UpdateAccountant.setBackground(Color.LIGHT_GRAY);
		UpdateAccountant.setFont(new Font("Times New Roman", Font.BOLD, 15));
		UpdateAccountant.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/calculator.png")));
		UpdateAccountant.setBounds(593, 157, 152, 84);
		frame.getContentPane().add(UpdateAccountant);
		UpdateAccountant.setOpaque(true);
		
		JLabel UpdateReceptionist = new JLabel("Receptionist");
		UpdateReceptionist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateReceptionist window = new UpdateReceptionist();
				frame.dispose();
			}
		});
		UpdateReceptionist.setBackground(Color.LIGHT_GRAY);
		UpdateReceptionist.setFont(new Font("Times New Roman", Font.BOLD, 15));
		UpdateReceptionist.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/reception.png")));
		UpdateReceptionist.setBounds(239, 309, 152, 84);
		frame.getContentPane().add(UpdateReceptionist);
		UpdateReceptionist.setOpaque(true);
		
		JLabel UpdateHRManager = new JLabel("HRManager");
		UpdateHRManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateHR window = new UpdateHR();
				frame.dispose();
			}
		});
		UpdateHRManager.setBackground(Color.LIGHT_GRAY);
		UpdateHRManager.setFont(new Font("Times New Roman", Font.BOLD, 15));
		UpdateHRManager.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/recruitment.png")));
		UpdateHRManager.setBounds(476, 312, 152, 78);
		frame.getContentPane().add(UpdateHRManager);
		UpdateHRManager.setOpaque(true);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Admin window = new Admin();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);
		
	}

	}
