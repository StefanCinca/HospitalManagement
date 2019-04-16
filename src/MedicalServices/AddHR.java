package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ValidateData.Valid;

public class AddHR {

	private JFrame frame;
	private static final int NRTEXTFIELD = 11;
	private JTextField[] hrTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddHR window = new AddHR();
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
	public AddHR() {
		initialize();
		frame.setVisible(true);
	}
	public boolean ValidateDate(JTextField[] hrTextField)
	{
		if (Valid.isFieldNull(hrTextField) == true)
		{
			return false;
		}
		if (Valid.isValidNume(hrTextField[0].getText()) == false || Valid.isValidNume(hrTextField[1].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidCNP(hrTextField[2].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidEmail(hrTextField[4].getText()) == false)
		{
			System.out.println("EMAIL INVALID");
			return false;
		}
		if (Valid.isValidNumarTelefon(hrTextField[5].getText()) == false)
		{
			return false;
		}
		if (Valid.isTime(hrTextField[6].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidIban(hrTextField[8].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidSalariu(hrTextField[9].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidOre(hrTextField[10].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidContract(hrTextField[7].getText()) == false)
		{
			return false;
		}
		return true;
	}
	
	public void AddHrAction(JTextField[] hrTextField)
	{
		if (ValidateDate(hrTextField) == true)
		{
			try
			{Conexiune.cstmt = Conexiune.con.prepareCall("{call adaugare_inspector_hr(?,?,?,?,?,?,?,?,?,?,?)}");
			
			Conexiune.cstmt.setString(1, hrTextField[2].getText());
			Conexiune.cstmt.setString(2, hrTextField[0].getText());
			Conexiune.cstmt.setString(3, hrTextField[1].getText());
			Conexiune.cstmt.setString(4, hrTextField[3].getText());
			Conexiune.cstmt.setString(5, hrTextField[5].getText());
			Conexiune.cstmt.setString(6, hrTextField[4].getText());
			Conexiune.cstmt.setString(7, hrTextField[8].getText());
			Conexiune.cstmt.setInt(8, Integer.parseInt(hrTextField[7].getText()));
			Conexiune.cstmt.setString(9, hrTextField[6].getText());
			Conexiune.cstmt.setInt(10, Integer.parseInt(hrTextField[9].getText()));
			Conexiune.cstmt.setInt(11, Integer.parseInt(hrTextField[10].getText()));
			Conexiune.cstmt.execute();
			
			}
			catch(SQLException exc)
			{
				System.err.println("Nu a mers introducerea contabilului");
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
		
		hrTextField = new JTextField[NRTEXTFIELD];
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
		
		
		JLabel lblSalariu = new JLabel("Salariu");
		lblSalariu.setBounds(326, 327, 56, 16);
		frame.getContentPane().add(lblSalariu);
		
		JLabel lblNumarOre = new JLabel("Numar ore");
		lblNumarOre.setBounds(326, 356, 97, 16);
		frame.getContentPane().add(lblNumarOre);
		
		
		hrTextField[0]  = new JTextField();
		hrTextField[0].setBounds(505, 63, 116, 22);
		frame.getContentPane().add(hrTextField[0]);
		hrTextField[0].setColumns(10);
		
		hrTextField[1] = new JTextField();
		hrTextField[1].setBounds(505, 92, 116, 22);
		frame.getContentPane().add(hrTextField[1]);
		hrTextField[1].setColumns(10);
		
		hrTextField[2] = new JTextField();
		hrTextField[2].setBounds(505, 121, 116, 22);
		frame.getContentPane().add(hrTextField[2]);
		hrTextField[2].setColumns(10);
		
		hrTextField[3] = new JTextField();
		hrTextField[3].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(hrTextField[3]);
		hrTextField[3].setColumns(10);
		
		hrTextField[4] = new JTextField();
		hrTextField[4].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(hrTextField[4]);
		hrTextField[4].setColumns(10);
		
		hrTextField[5] = new JTextField();
		hrTextField[5].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(hrTextField[5]);
		hrTextField[5].setColumns(10);
		
		hrTextField[6] = new JTextField();
		hrTextField[6].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(hrTextField[6]);
		hrTextField[6].setColumns(10);
		
		hrTextField[7] = new JTextField();
		hrTextField[7].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(hrTextField[7]);
		hrTextField[7].setColumns(10);
		
		hrTextField[8] = new JTextField();
		hrTextField[8].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(hrTextField[8]);
		hrTextField[8].setColumns(10);
		
		hrTextField[9] = new JTextField();
		hrTextField[9].setBounds(505, 353, 116, 22);
		frame.getContentPane().add(hrTextField[9]);
		hrTextField[9].setColumns(10);
		
		hrTextField[10] = new JTextField();
		hrTextField[10].setBounds(505, 324, 116, 22);
		frame.getContentPane().add(hrTextField[10]);
		hrTextField[10].setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddHrAction(hrTextField);
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
