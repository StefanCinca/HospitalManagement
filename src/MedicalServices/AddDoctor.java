package MedicalServices;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.text.Document;

import ValidateData.Valid;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;

public class AddDoctor {

	private JFrame frame;
	private JTextField[] doctorTextField;
	private JTextField textNume;  // 0
	private JTextField textPrenume; // 1
	private JTextField textCNP; //2
	private JTextField textAdresa; // 3
	private JTextField textEmail; // 4
	private JTextField textTelefon;// 5
	private JTextField textData;// 6
	private JTextField textContract;// 7
	private JTextField textIBAN;//8 
	private JTextField textParafa;// 9
	public static String CNP;
	public final static int NRTEXTFIELD = 13;
	private JComboBox<String>  GradDoctor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDoctor window = new AddDoctor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getCNP()
	{
		return CNP;
	}
	/**
	 * Create the application.
	 */
	public AddDoctor() {
		initialize();
		frame.setVisible(true);
	}
	
	public AddDoctor(int x)
	{
		;
	}

	
	public boolean ValidareDate(JTextField[] doctorTextField)
	{	
		for (JTextField item : doctorTextField) {
			if (item.getText().equals(""))
			{
				System.out.println("Unul din campuri este NULL");
				return false;
			}
		}
		if (Valid.isAlpha(doctorTextField[0].getText()) == false)
		{
			System.out.println("Numele trebuie sa contina doar litere");
			return false;
		}	
		if (Valid.isAlpha(doctorTextField[1].getText()) == false)
		{
			System.out.println("Prenumele trebuie sa contina doar litere");
			return false;
		}
		if (Valid.isNotAlpha(doctorTextField[2].getText()) == false)
		{
			System.out.println("CNP-ul trebuie sa contina doar cifre");
			return false;}
		
			String Cnp = doctorTextField[2].getText();
			if (Cnp.length() != 13)
			{
				System.out.println("CNP-ul trebuie sa contina 13 cifre");
			}
			
			String email = doctorTextField[4].getText();
			if (Valid.isValidEmail(email) == false)
			{
				System.out.println("Email Invalid");
				return false;
			}
			
			String numarTelefon = doctorTextField[5].getText();
			if (Valid.isNotAlpha(numarTelefon) == false || numarTelefon.length() != 10)
			{
				System.out.println("Numarul de telefon este incorect");
				return false;
			}
			
			String Iban = doctorTextField[8].getText();
			if (Iban.length() < 2)
			{
				return false;
			}
			String indicativ = Iban.substring(0,  1);
			if (Valid.isAlpha(indicativ) == false)
			{
				System.out.println("Indicativul tarii trebuie sa aiba 2 litere");
				return false;
			}
			
			if (Iban.length() != 24)
			{
				System.out.println("IBANUL trebuie sa aiba 24 caractere");
				return false;
			}
			String time = doctorTextField[6].getText();
			Valid.isTime(time);
			
			if (Valid.isNotAlpha(doctorTextField[10].getText()) == false)
			{
				System.out.println("Salariul trebuie sa fie int");
				return false;
			}
			int salariu = Integer.parseInt(doctorTextField[10].getText());
			if (salariu < 0)
			{
				System.out.println("salariul trebuie sa fie pozitiv");
				return false;
			}
			
			if (Valid.isNotAlpha(doctorTextField[11].getText()) == false)
			{
				System.out.println("Numarul de ore trebuie sa fie int");
				return false;
			}
			int numarOre = Integer.parseInt(doctorTextField[11].getText());
			if (numarOre < 0)
			{
				System.out.println("Numarul de ore este intreg pozitiv");
				return false;
			}
			if (Valid.isNotAlpha(doctorTextField[12].getText()) == false)
			{
				System.out.println("Procentul este numar intreg");
				return false;
			}
			int procent = Integer.parseInt(doctorTextField[12].getText());
			if (procent < 0 || procent > 99)
			{
				System.out.println("Procentul trebuie sa fie numar mai mic decat 100 si pozitiv");
				return false;
			}
			
			
			return true;
		}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public void AddDoctorAction(JTextField[] doctorTextField)
	{
		if (ValidareDate(doctorTextField) == true)
		{
			try 
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_medic_hr(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				CNP = doctorTextField[2].getText();
				System.out.println(CNP);
				String Nume = doctorTextField[0].getText();
				System.out.println(Nume);
				String Prenume = doctorTextField[1].getText();
				System.out.println(Prenume);
				String Adresa = doctorTextField[3].getText();
				System.out.println(Adresa);
				String Telefon = doctorTextField[5].getText();
				System.out.println(Telefon);
				String Email = doctorTextField[4].getText();
				System.out.println(Email);
				String IBAN = doctorTextField[8].getText();
				System.out.println(IBAN);
				int NumarContract = Integer.parseInt(doctorTextField[7].getText());
				System.out.println(NumarContract);
				String Data = doctorTextField[6].getText();
				System.out.println(Data);
				String Grad = (String)GradDoctor.getSelectedItem();
				System.out.println(Grad);
				String Parafa = doctorTextField[9].getText();
				System.out.println(Parafa);
				int Salariu = Integer.parseInt(doctorTextField[10].getText());
				System.out.println(Salariu);
				int NumarOre = Integer.parseInt(doctorTextField[11].getText());
				System.out.println(NumarOre);
				
				
				
				Conexiune.cstmt.setString(1, doctorTextField[2].getText());
				Conexiune.cstmt.setString(2, doctorTextField[0].getText());
				Conexiune.cstmt.setString(3, doctorTextField[1].getText());
				Conexiune.cstmt.setString(4, doctorTextField[3].getText());
				Conexiune.cstmt.setString(5, doctorTextField[5].getText());
				Conexiune.cstmt.setString(6, doctorTextField[4].getText());
				Conexiune.cstmt.setString(7, doctorTextField[8].getText());
				Conexiune.cstmt.setInt(8, Integer.parseInt(doctorTextField[7].getText()));
				Conexiune.cstmt.setString(9, doctorTextField[6].getText());
				Conexiune.cstmt.setString(10, (String)GradDoctor.getSelectedItem());
				Conexiune.cstmt.setString(11, doctorTextField[9].getText());
				Conexiune.cstmt.setInt(12, Integer.parseInt(doctorTextField[10].getText()));
				Conexiune.cstmt.setInt(13, Integer.parseInt(doctorTextField[11].getText()));
				Conexiune.cstmt.setInt(14, Integer.parseInt(doctorTextField[12].getText()));
				Conexiune.cstmt.execute();
				
			}
			
			catch (SQLException ea)
			{
				;
			}
			AddCompetenteSpecializari window = new AddCompetenteSpecializari();
			frame.dispose();
		}
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
		
		doctorTextField = new JTextField[NRTEXTFIELD];
		
		JLabel lblNewLabel = new JLabel("Nume");
		lblNewLabel.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(305, 66, 77, 16);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Prenume");
		lblNewLabel_1.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(305, 95, 77, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CNP");
		lblNewLabel_2.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(305, 124, 77, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Adresa");
		lblNewLabel_3.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(305, 153, 77, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setBounds(305, 182, 77, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Telefon");
		lblNewLabel_5.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(305, 211, 77, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Data Angajare");
		lblNewLabel_6.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_6.setBounds(305, 240, 116, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Numar Contract");
		lblNewLabel_7.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(305, 269, 116, 16);
		frame.getContentPane().add(lblNewLabel_7);
	
		JLabel lblNewLabel_8 = new JLabel("IBAN");
		lblNewLabel_8.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(305, 298, 77, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Grad");
		lblNewLabel_9.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setBounds(305, 327, 77, 16);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Cod de Parafa");
		lblNewLabel_10.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_10.setBounds(305, 356, 116, 16);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblSalariu = new JLabel("Salariu");
		lblSalariu.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblSalariu.setBounds(305, 385, 116, 16);
		frame.getContentPane().add(lblSalariu);
		
		JLabel lblNewLabel_11 = new JLabel("Numar ore");
		lblNewLabel_11.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_11.setBounds(305, 414, 116, 16);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Procent");
		lblNewLabel_12.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_12.setBounds(305, 443, 77, 16);
		frame.getContentPane().add(lblNewLabel_12);

		doctorTextField[0] = new JTextField();
		doctorTextField[0].setBounds(505, 63, 116, 22);
		frame.getContentPane().add(doctorTextField[0]);
		doctorTextField[0].setColumns(10);
		
		doctorTextField[1] = new JTextField();
		doctorTextField[1].setBounds(505, 92, 116, 22);
		frame.getContentPane().add(doctorTextField[1]);
		doctorTextField[1].setColumns(10);
		
		doctorTextField[2] = new JTextField();
		doctorTextField[2].setBounds(505, 121, 116, 22);
		frame.getContentPane().add(doctorTextField[2]);
		doctorTextField[2].setColumns(10);
		
		doctorTextField[3] = new JTextField();
		doctorTextField[3].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(doctorTextField[3]);
		doctorTextField[3].setColumns(10);
		
		doctorTextField[4] = new JTextField();
		doctorTextField[4].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(doctorTextField[4]);
		doctorTextField[4].setColumns(10);
		
		doctorTextField[5] = new JTextField();
		doctorTextField[5].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(doctorTextField[5]);
		doctorTextField[5].setColumns(10);
		
		doctorTextField[6] = new JTextField();
		doctorTextField[6].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(doctorTextField[6]);
		doctorTextField[6].setColumns(10);
		
		doctorTextField[7] = new JTextField();
		doctorTextField[7].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(doctorTextField[7]);
		doctorTextField[7].setColumns(10);
		
		doctorTextField[8] = new JTextField();
		doctorTextField[8].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(doctorTextField[8]);
		doctorTextField[8].setColumns(10);
		
		
		GradDoctor = new JComboBox<String>();
		GradDoctor.setBounds(505, 324, 127, 22);
		GradDoctor.addItem("specialist");
		GradDoctor.addItem("primar");
		frame.getContentPane().add(GradDoctor);
		
		doctorTextField[9] = new JTextField();
		doctorTextField[9].setBounds(505, 353, 116, 22);
		frame.getContentPane().add(doctorTextField[9]);
		doctorTextField[9].setColumns(10);
		
		
		doctorTextField[10] = new JTextField();
		doctorTextField[10].setBounds(505, 382, 116, 22);
		frame.getContentPane().add(doctorTextField[10]);
		doctorTextField[10].setColumns(10);
		
		doctorTextField[11] = new JTextField();
		doctorTextField[11].setBounds(505, 411, 116, 22);
		frame.getContentPane().add(doctorTextField[11]);
		doctorTextField[11].setColumns(10);
		
		doctorTextField[12] = new JTextField();
		doctorTextField[12].setBounds(505, 440, 116, 22);
		frame.getContentPane().add(doctorTextField[12]);
		doctorTextField[12].setColumns(10);
		
		
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddDoctorAction(doctorTextField);
			}
		});
		btnNewButton.setBounds(419, 494, 97, 25);
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
