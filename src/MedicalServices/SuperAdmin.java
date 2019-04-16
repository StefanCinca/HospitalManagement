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

public class SuperAdmin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuperAdmin window = new SuperAdmin();
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
	public SuperAdmin() {
		initialize();
		frame.setVisible(true);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void AdaugaUser(JLabel addUser)
	{
		addUser.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				SuperAdminCreateUser window = new SuperAdminCreateUser();
				frame.dispose();
				
			}
		});
	}
	
	public void StergeUser(JLabel removeUser)
	{
		removeUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				RemoveUser window = new RemoveUser();
				frame.dispose();
			}
		});
	}
	
	public void UpdateUserAction(JLabel updateUser)
	{
		updateUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				UpdateUserSuperAdmin window = new UpdateUserSuperAdmin();
				frame.dispose();
			}
		});
	}
	
	public void InfoAdmin(JPanel panel)
	{
		JLabel Info = new JLabel("");
		Info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AdminInfo window = new AdminInfo();
				frame.dispose();
			}
		});
		Info.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/info-admin.png")));
		panel.add(Info);
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
		frame.setBounds(0, 0, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel AdminPanel = new JPanel();
		frame.getContentPane().add(AdminPanel, BorderLayout.WEST);
		AdminPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AdminPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		GridBagLayout gbl_AdminPanel = new GridBagLayout();
		gbl_AdminPanel.columnWeights = new double[]{1.0};
		AdminPanel.setLayout(gbl_AdminPanel);
		JLabel AdminLabel = new JLabel("SuperAdmin");
		AdminLabel.setIcon(null);
		AdminLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AdminLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		AdminPanel.add(AdminLabel, gbc_Welcome);
		
		JPanel Panel_A_Icons = new JPanel();
		frame.getContentPane().add(Panel_A_Icons, BorderLayout.CENTER);
		JLabel AddUser = new JLabel("AddUser");
		AdaugaUser(AddUser);
		AddUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		Panel_A_Icons.setLayout(null);
		AddUser.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/add-user.png")));
		AddUser.setOpaque(true);
		AddUser.setBackground(Color.lightGray);
		AddUser.setBounds(93, 202, 155, 100);
		Panel_A_Icons.add(AddUser);
		
		JLabel RemoveUser = new JLabel("RemoveUser");
		RemoveUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
		RemoveUser.setOpaque(true);
		RemoveUser.setBackground(Color.lightGray);
		RemoveUser.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/remove-user.png")));
		Panel_A_Icons.add(RemoveUser);
		StergeUser(RemoveUser);
		RemoveUser.setBounds(338, 202, 155, 100);
		
		JLabel UpdateUser = new JLabel("UpdateUser");
		UpdateUser.setFont(new Font("Times New Roman", Font.BOLD, 15));
		UpdateUser.setOpaque(true);
		UpdateUser.setBackground(Color.lightGray);
		UpdateUser.setIcon(new ImageIcon(MainFrame.class.getResource("/MedicalServices/update-user.png")));
		Panel_A_Icons.add(UpdateUser);
		UpdateUserAction(UpdateUser);
		UpdateUser.setBounds(583, 202, 155, 100);
		
		JPanel panel = new JPanel();
		panel.setBounds(-33, 13, 155, 74);
		Panel_A_Icons.add(panel);
		
		InfoAdmin(panel);
		
		JLabel logout = new JLabel("");
		LogOut(logout);
		logout.setIcon(new ImageIcon(Admin.class.getResource("/MedicalServices/logout.png")));
		logout.setBounds(150, 13, 80, 74);
		Panel_A_Icons.add(logout);
	}

}
