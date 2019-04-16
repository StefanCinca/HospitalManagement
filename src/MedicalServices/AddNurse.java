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
import javax.swing.JComboBox;

public class AddNurse {

	private JFrame frame;
	private JTextField[] nurseTextField;
	private static final int NRTEXTFIELD = 11;
	private JComboBox<String> grad;
	private JComboBox<String> tip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNurse window = new AddNurse();
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
	public AddNurse() {
		initialize();
		frame.setVisible(true);
	}
	public boolean ValidareDate(JTextField[] nurseTextField)
	{
		if (Valid.isFieldNull(nurseTextField) == true)
		{
			return false;
		}
		if (Valid.isValidNume(nurseTextField[0].getText()) == false || Valid.isValidNume(nurseTextField[1].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidCNP(nurseTextField[2].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidEmail(nurseTextField[4].getText()) == false)
		{
			System.out.println("EMAIL INVALID");
			return false;
		}
		if (Valid.isValidNumarTelefon(nurseTextField[5].getText()) == false)
		{
			return false;
		}
		if (Valid.isTime(nurseTextField[6].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidIban(nurseTextField[8].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidSalariu(nurseTextField[9].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidOre(nurseTextField[10].getText()) == false)
		{
			return false;
		}
		if (Valid.isValidContract(nurseTextField[7].getText()) == false)
		{
			return false;
		}
		return true;
		
		
	}
	public void AddNurseAction(JTextField[] nurseTextField)
	{
		if (ValidareDate(nurseTextField) == true)
		{
			try
			{
			Conexiune.cstmt = Conexiune.con.prepareCall("{call adaugare_asistent_medical_hr(?,?,?,?,?,?,?,?,?,?,?, ?, ?)}");
			Conexiune.cstmt.setString(1, nurseTextField[2].getText());
			Conexiune.cstmt.setString(2, nurseTextField[0].getText());
			Conexiune.cstmt.setString(3, nurseTextField[1].getText());
			Conexiune.cstmt.setString(4, nurseTextField[3].getText());
			Conexiune.cstmt.setString(5, nurseTextField[5].getText());
			Conexiune.cstmt.setString(6, nurseTextField[4].getText());
			Conexiune.cstmt.setString(7, nurseTextField[8].getText());
			Conexiune.cstmt.setInt(8, Integer.parseInt(nurseTextField[7].getText()));
			Conexiune.cstmt.setString(9, nurseTextField[6].getText());
			Conexiune.cstmt.setInt(10, Integer.parseInt(nurseTextField[9].getText()));
			Conexiune.cstmt.setInt(11, Integer.parseInt(nurseTextField[10].getText()));
			Conexiune.cstmt.setString(12, (String)grad.getSelectedItem());
			Conexiune.cstmt.setString(13, (String)tip.getSelectedItem());
			Conexiune.cstmt.execute();
			
			}
			catch(SQLException exc)
			{
				System.err.println("Nu a mers introducerea asistentului");
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
		
		
		nurseTextField = new JTextField[NRTEXTFIELD];
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
		
		
		nurseTextField[0]  = new JTextField();
		nurseTextField[0].setBounds(505, 63, 116, 22);
		frame.getContentPane().add(nurseTextField[0]);
		nurseTextField[0].setColumns(10);
		
		nurseTextField[1] = new JTextField();
		nurseTextField[1].setBounds(505, 92, 116, 22);
		frame.getContentPane().add(nurseTextField[1]);
		nurseTextField[1].setColumns(10);
		
		nurseTextField[2] = new JTextField();
		nurseTextField[2].setBounds(505, 121, 116, 22);
		frame.getContentPane().add(nurseTextField[2]);
		nurseTextField[2].setColumns(10);
		
		nurseTextField[3] = new JTextField();
		nurseTextField[3].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(nurseTextField[3]);
		nurseTextField[3].setColumns(10);
		
		nurseTextField[4] = new JTextField();
		nurseTextField[4].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(nurseTextField[4]);
		nurseTextField[4].setColumns(10);
		
		nurseTextField[5] = new JTextField();
		nurseTextField[5].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(nurseTextField[5]);
		nurseTextField[5].setColumns(10);
		
		nurseTextField[6] = new JTextField();
		nurseTextField[6].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(nurseTextField[6]);
		nurseTextField[6].setColumns(10);
		
		nurseTextField[7] = new JTextField();
		nurseTextField[7].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(nurseTextField[7]);
		nurseTextField[7].setColumns(10);
		
		nurseTextField[8] = new JTextField();
		nurseTextField[8].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(nurseTextField[8]);
		nurseTextField[8].setColumns(10);
		
		nurseTextField[9] = new JTextField();
		nurseTextField[9].setBounds(505, 353, 116, 22);
		frame.getContentPane().add(nurseTextField[9]);
		nurseTextField[9].setColumns(10);
		
		nurseTextField[10] = new JTextField();
		nurseTextField[10].setBounds(505, 324, 116, 22);
		frame.getContentPane().add(nurseTextField[10]);
		nurseTextField[10].setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddNurseAction(nurseTextField);
			}
		});
		btnNewButton.setBounds(324, 453, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_9 = new JLabel("Grad");
		lblNewLabel_9.setBounds(326, 385, 56, 16);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Tip");
		lblNewLabel_10.setBounds(326, 414, 56, 16);
		frame.getContentPane().add(lblNewLabel_10);
		
		grad = new JComboBox<String>();
		grad.setBounds(505, 411, 116, 22);
		grad.addItem("secundar");
		grad.addItem("principal");
		frame.getContentPane().add(grad);
		
		tip = new JComboBox<String>();
		tip.setBounds(505, 382, 116, 22);
		tip.addItem("generalist");
		tip.addItem("laborator");
		tip.addItem("radiologie");
		frame.getContentPane().add(tip);
	
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
