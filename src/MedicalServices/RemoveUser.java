package MedicalServices;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ValidateData.Valid;

public class RemoveUser {

	private JFrame frame;
	private String StringCNP;
	private JTextField CNP;
	private JComboBox<String> TypeRemoved;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveUser window = new RemoveUser();
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
	public RemoveUser() {
		initialize();
		frame.setVisible(true);
	}

	public void RemoveUserAction()
	{	
		if (Valid.isValidCNP(StringCNP) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Cnp-ul nu este valid", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			String type = (String) TypeRemoved.getSelectedItem();
			if (type.equals("Doctor"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Delete_medic(?, ?)}");
			}
			if (type.equals("Asistent"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Delete_asistent_medical(?, ?)}");
			}
			if (type.equals("Contabil"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Delete_contabil(?, ?)}");
			}
			if (type.equals("Receptioner"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Delete_receptioner(?, ?)}");
			}
			if (type.equals("Inspector"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Delete_inspector(?, ?)}");
			}
			if (type.equals("Admin"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Delete_administrator(?, ?)}");
			}
		Conexiune.cstmt.setString(1, StringCNP);
		Conexiune.cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
		Conexiune.cstmt.execute();
		int ok = 0;
		ok = Conexiune.cstmt.getInt(2);
		if (ok == 1)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Nu exista user-ul cu CNP-ul introdus", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (ok == 0)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "User-ul a fost sters", "Succes", JOptionPane.INFORMATION_MESSAGE);
		}
		}
		catch(SQLException ea)
		{
			System.err.println("Nu s-a putut sterge user-ul");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(335, 309, 125, 50);
		lblCnp.setFont(new Font("Times New Roman",  Font.PLAIN, 24));
		frame.getContentPane().add(lblCnp);
		
		TypeRemoved = new JComboBox<String>();
		TypeRemoved.addItem("Doctor");
		TypeRemoved.addItem("Asistent");
		TypeRemoved.addItem("Contabil");
		TypeRemoved.addItem("Receptioner");
		TypeRemoved.addItem("Inspector");
		if (Login.isSuperAdmin == true)
		{
			TypeRemoved.addItem("Admin");
		}
		TypeRemoved.setBounds(445, 265, 161, 31);
		frame.getContentPane().add(TypeRemoved);
		
		CNP = new JTextField();
		CNP.setBounds(445, 326, 161, 22);
		frame.getContentPane().add(CNP);
		CNP.setColumns(10);
		StringCNP = CNP.getText();
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				if (Login.isSuperAdmin == true)
				{SuperAdmin window = new SuperAdmin();
				window.getFrame().setVisible(true);
				}
				else
				{
					Admin window = new Admin();
					window.getFrame().setVisible(true);
				}
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				StringCNP = CNP.getText();
				RemoveUserAction();
			}
		});
		btnNewButton.setBounds(448, 378, 158, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("TYPE");
		lblNewLabel.setBounds(337, 272, 96, 16);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman",  Font.PLAIN, 24));
	}
}
