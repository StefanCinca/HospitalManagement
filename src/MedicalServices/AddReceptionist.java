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

public class AddReceptionist {

	private JFrame frame;
	private JTextField[] receptionistTextField;
	private static final int NRTEXTFIELD = 11;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddReceptionist window = new AddReceptionist();
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
	public AddReceptionist() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public boolean ValidareDate(JTextField[] receptionistTextField)
	{
		if (Valid.isFieldNull(receptionistTextField) == true)
		{
			return false;
		}
		if (Valid.isValidNume(receptionistTextField[0].getText()) == false || Valid.isValidNume(receptionistTextField[1].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidCNP(receptionistTextField[2].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidEmail(receptionistTextField[4].getText()) == false)
		{
			System.out.println("EMAIL INVALID");
			return false;
		}
		if (Valid.isValidNumarTelefon(receptionistTextField[5].getText()) == false)
		{
			return false;
		}
		if (Valid.isTime(receptionistTextField[6].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidIban(receptionistTextField[8].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidSalariu(receptionistTextField[9].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidOre(receptionistTextField[10].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidContract(receptionistTextField[7].getText()) == false)
		{
			return false;
		}
		return true;
		
		
	}
	public void AddReceptionistAction(JTextField[] receptionistTextField)
	{
		if (ValidareDate(receptionistTextField) == true)
		{
			try
			{Conexiune.cstmt = Conexiune.con.prepareCall("{call adaugare_receptioner_hr(?,?,?,?,?,?,?,?,?,?,?)}");
			
			Conexiune.cstmt.setString(1, receptionistTextField[2].getText());
			Conexiune.cstmt.setString(2, receptionistTextField[0].getText());
			Conexiune.cstmt.setString(3, receptionistTextField[1].getText());
			Conexiune.cstmt.setString(4, receptionistTextField[3].getText());
			Conexiune.cstmt.setString(5, receptionistTextField[5].getText());
			Conexiune.cstmt.setString(6, receptionistTextField[4].getText());
			Conexiune.cstmt.setString(7, receptionistTextField[8].getText());
			Conexiune.cstmt.setInt(8, Integer.parseInt(receptionistTextField[7].getText()));
			Conexiune.cstmt.setString(9, receptionistTextField[6].getText());
			Conexiune.cstmt.setInt(10, Integer.parseInt(receptionistTextField[9].getText()));
			Conexiune.cstmt.setInt(11, Integer.parseInt(receptionistTextField[10].getText()));
			Conexiune.cstmt.execute();
			
			}
			catch(SQLException exc)
			{
				System.err.println("Nu a mers introducerea receptionerului");
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
		
		
		receptionistTextField = new JTextField[NRTEXTFIELD];
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
		
		
		receptionistTextField[0]  = new JTextField();
		receptionistTextField[0].setBounds(505, 63, 116, 22);
		frame.getContentPane().add(receptionistTextField[0]);
		receptionistTextField[0].setColumns(10);
		
		receptionistTextField[1] = new JTextField();
		receptionistTextField[1].setBounds(505, 92, 116, 22);
		frame.getContentPane().add(receptionistTextField[1]);
		receptionistTextField[1].setColumns(10);
		
		receptionistTextField[2] = new JTextField();
		receptionistTextField[2].setBounds(505, 121, 116, 22);
		frame.getContentPane().add(receptionistTextField[2]);
		receptionistTextField[2].setColumns(10);
		
		receptionistTextField[3] = new JTextField();
		receptionistTextField[3].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(receptionistTextField[3]);
		receptionistTextField[3].setColumns(10);
		
		receptionistTextField[4] = new JTextField();
		receptionistTextField[4].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(receptionistTextField[4]);
		receptionistTextField[4].setColumns(10);
		
		receptionistTextField[5] = new JTextField();
		receptionistTextField[5].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(receptionistTextField[5]);
		receptionistTextField[5].setColumns(10);
		
		receptionistTextField[6] = new JTextField();
		receptionistTextField[6].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(receptionistTextField[6]);
		receptionistTextField[6].setColumns(10);
		
		receptionistTextField[7] = new JTextField();
		receptionistTextField[7].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(receptionistTextField[7]);
		receptionistTextField[7].setColumns(10);
		
		receptionistTextField[8] = new JTextField();
		receptionistTextField[8].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(receptionistTextField[8]);
		receptionistTextField[8].setColumns(10);
		
		receptionistTextField[9] = new JTextField();
		receptionistTextField[9].setBounds(505, 353, 116, 22);
		frame.getContentPane().add(receptionistTextField[9]);
		receptionistTextField[9].setColumns(10);
		
		receptionistTextField[10] = new JTextField();
		receptionistTextField[10].setBounds(505, 324, 116, 22);
		frame.getContentPane().add(receptionistTextField[10]);
		receptionistTextField[10].setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddReceptionistAction(receptionistTextField);
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
