package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ValidateData.Valid;

public class AddAdmin {

	private JFrame frame;
	private JTextField[] adminTextField;
	public final static int NRTEXTFIELD = 11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAdmin window = new AddAdmin();
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
	public AddAdmin() {
		initialize();
		frame.setVisible(true);
	}
	
	public boolean ValidareDate(JTextField[] adminTextField)
	{
		if (Valid.isFieldNull(adminTextField) == true)
		{
			return false;
		}
		if (Valid.isValidNume(adminTextField[0].getText()) == false || Valid.isValidNume(adminTextField[1].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidCNP(adminTextField[2].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidEmail(adminTextField[4].getText()) == false)
		{
			System.out.println("EMAIL INVALID");
			return false;
		}
		if (Valid.isValidNumarTelefon(adminTextField[5].getText()) == false)
		{
			return false;
		}
		if (Valid.isTime(adminTextField[6].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidIban(adminTextField[8].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidContract(adminTextField[7].getText()) == false)
		{
			return false;
		}
		return true;
	}
	
	public void AddAccountantAction(JTextField[] adminTextField)
	{
		int ok = 0;
		if (ValidareDate(adminTextField) == true)
		{
			try
			{Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_administrator_hr(?,?,?,?,?,?,?,?,?, ?)}");
			
			Conexiune.cstmt.setString(1, adminTextField[2].getText());
			Conexiune.cstmt.setString(2, adminTextField[0].getText());
			Conexiune.cstmt.setString(3, adminTextField[1].getText());
			Conexiune.cstmt.setString(4, adminTextField[3].getText());
			Conexiune.cstmt.setString(5, adminTextField[5].getText());
			Conexiune.cstmt.setString(6, adminTextField[4].getText());
			Conexiune.cstmt.setString(7, adminTextField[8].getText());
			Conexiune.cstmt.setInt(8, Integer.parseInt(adminTextField[7].getText()));
			Conexiune.cstmt.setString(9, adminTextField[6].getText());
			Conexiune.cstmt.registerOutParameter(10, java.sql.Types.INTEGER);
			Conexiune.cstmt.execute();
			ok = Conexiune.cstmt.getInt(10);
			if (ok == 1)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Exista deja un administrator cu acest CNP", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
			catch(SQLException exc)
			{
				System.err.println("Nu a mers introducerea administratorului");
			}
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
		
		
		adminTextField = new JTextField[NRTEXTFIELD];
		JLabel lblNewLabel = new JLabel("Nume");
		lblNewLabel.setBounds(326, 66, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prenume");
		lblNewLabel_1.setBounds(326, 95, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CNP");
		lblNewLabel_2.setBounds(326, 124, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresa");
		lblNewLabel_3.setBounds(326, 153, 56, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(326, 182, 56, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Telefon");
		lblNewLabel_5.setBounds(326, 211, 56, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Data Angajare");
		lblNewLabel_6.setBounds(326, 240, 95, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Numar Contract");
		lblNewLabel_7.setBounds(326, 269, 95, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IBAN");
		lblNewLabel_8.setBounds(326, 298, 56, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		adminTextField[0]  = new JTextField();
		adminTextField[0].setBounds(505, 63, 116, 22);
		frame.getContentPane().add(adminTextField[0]);
		adminTextField[0].setColumns(10);
		
		adminTextField[1] = new JTextField();
		adminTextField[1].setBounds(505, 92, 116, 22);
		frame.getContentPane().add(adminTextField[1]);
		adminTextField[1].setColumns(10);
		
		adminTextField[2] = new JTextField();
		adminTextField[2].setBounds(505, 121, 116, 22);
		frame.getContentPane().add(adminTextField[2]);
		adminTextField[2].setColumns(10);
		
		adminTextField[3] = new JTextField();
		adminTextField[3].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(adminTextField[3]);
		adminTextField[3].setColumns(10);
		
		adminTextField[4] = new JTextField();
		adminTextField[4].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(adminTextField[4]);
		adminTextField[4].setColumns(10);
		
		adminTextField[5] = new JTextField();
		adminTextField[5].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(adminTextField[5]);
		adminTextField[5].setColumns(10);
		
		adminTextField[6] = new JTextField();
		adminTextField[6].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(adminTextField[6]);
		adminTextField[6].setColumns(10);
		
		adminTextField[7] = new JTextField();
		adminTextField[7].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(adminTextField[7]);
		adminTextField[7].setColumns(10);
		
		adminTextField[8] = new JTextField();
		adminTextField[8].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(adminTextField[8]);
		adminTextField[8].setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddAccountantAction(adminTextField);
			}
		});
		btnNewButton.setBounds(326, 407, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				NewUser window = new NewUser();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(177, 13, 95, 48);
		frame.getContentPane().add(Back);
	}

}
