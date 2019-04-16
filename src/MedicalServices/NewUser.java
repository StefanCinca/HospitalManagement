package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class NewUser {

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
					NewUser window = new NewUser();
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
	public NewUser() {
		initialize();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel AddDoctor = new JLabel("Doctor");
		AddDoctor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddDoctor window = new AddDoctor();
			}
			
		});
		AddDoctor.setBackground(Color.LIGHT_GRAY);
		AddDoctor.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		AddDoctor.setBounds(10, 70, 136, 52);
		frame.getContentPane().add(AddDoctor);
		AddDoctor.setOpaque(true);
		
		JLabel AddNurse = new JLabel("Nurse");
		AddNurse.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				AddNurse window = new AddNurse();
			}
		});
		AddNurse.setBackground(Color.LIGHT_GRAY);
		AddNurse.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/doctor.png")));
		AddNurse.setBounds(158, 70, 128, 52);
		frame.getContentPane().add(AddNurse);
		AddNurse.setOpaque(true);
		
		JLabel AddAccountant = new JLabel("Accountant");
		AddAccountant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddAccountant window = new AddAccountant();
			}
		});
		AddAccountant.setBackground(Color.LIGHT_GRAY);
		AddAccountant.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/calculator.png")));
		AddAccountant.setBounds(298, 70, 114, 52);
		frame.getContentPane().add(AddAccountant);
		AddAccountant.setOpaque(true);
		
		JLabel AddReceptionist = new JLabel("Receptionist");
		AddReceptionist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddReceptionist window = new AddReceptionist();
			}
		});
		AddReceptionist.setBackground(Color.LIGHT_GRAY);
		AddReceptionist.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/reception.png")));
		AddReceptionist.setBounds(158, 148, 128, 47);
		frame.getContentPane().add(AddReceptionist);
		AddReceptionist.setOpaque(true);
		
		JLabel AddHRManager = new JLabel("HRManager");
		AddHRManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddHR window = new AddHR();
			}
		});
		AddHRManager.setBackground(Color.LIGHT_GRAY);
		AddHRManager.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/recruitment.png")));
		AddHRManager.setBounds(298, 148, 114, 47);
		frame.getContentPane().add(AddHRManager);
		AddHRManager.setOpaque(true);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Hr_Manager window = new Hr_Manager();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				AddAdmin window = new AddAdmin();
				
			}
		});
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setIcon(new ImageIcon(NewUser.class.getResource("/MedicalServices/admin.png")));
		lblNewLabel.setBounds(12, 148, 134, 47);
		lblNewLabel.setOpaque(true);
		frame.getContentPane().add(lblNewLabel);
		
	}
}
