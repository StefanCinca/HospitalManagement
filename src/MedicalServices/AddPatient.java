package MedicalServices;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ValidateData.Valid;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class AddPatient {

	private JFrame frame;
	private JTextField CNP;
	private JTextField Nume;
	private JTextField Prenume;
	private JTextField Adresa;
	private JTextField Telefon;
	private JTextField Email;
	private String StringCNP;
	private String StringNume;
	private String StringPrenume;
	private String StringAdresa;
	private String StringTelefon;
	private String StringEmail;
	private JLabel lblMesaj;
	private int ok = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPatient window = new AddPatient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public boolean ValideazaDatele(String CNP, String nume, String prenume, String adresa, String telefon, String email)
	{
		
		if (CNP.equals("") || nume.equals("") || prenume.equals("") || adresa.equals("") || telefon.equals("") || email.equals(""))
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Unul din campuri este null", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if (Valid.isValidCNP(CNP) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "CNP-ul este invalid", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if (Valid.isAlpha(nume) == false || Valid.isAlpha(prenume) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Numele sau prenumele sunt invalide", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if (Valid.isValidNumarTelefon(telefon) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Numarul de telefon este invalid", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if (Valid.isValidEmail(email) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Email-ul este invalid", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		return true;
	}
	public void RegisterUser()
	{
		StringCNP = CNP.getText();
		StringNume = Nume.getText();
		StringPrenume = Prenume.getText();
		StringAdresa = Adresa.getText();
		StringTelefon = Telefon.getText();
		StringEmail = Email.getText();
		
		boolean dateValide = ValideazaDatele(StringCNP, StringNume, StringPrenume, StringAdresa, StringTelefon, StringEmail);
		if (dateValide == true)
		{try
		{
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_pacient(?, ?, ?, ?, ?, ?, ?)}");
			Conexiune.cstmt.setString(1, StringCNP);
			Conexiune.cstmt.setString(2, StringNume);
			Conexiune.cstmt.setString(3, StringPrenume);
			Conexiune.cstmt.setString(4, StringAdresa);
			Conexiune.cstmt.setString(5, StringTelefon);
			Conexiune.cstmt.setString(6, StringEmail);
			Conexiune.cstmt.registerOutParameter(7, java.sql.Types.INTEGER);
			Conexiune.cstmt.execute();
			int ok = Conexiune.cstmt.getInt(7);
			if (ok == 1)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Pacientul a fost inregistrat", "Succes", JOptionPane.INFORMATION_MESSAGE);
			}
			if (ok == 2)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Datele pacientului au fost actualizate", "Succes", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException e)
		{
			System.err.println("Eroare inregistrare pacient");
		}
		}
	}

	/**
	 * Create the application.
	 */
	public AddPatient() {
		initialize();
		frame.setVisible(true);
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
		
		JLabel lblCNP = new JLabel("CNP");
		lblCNP.setBounds(353, 211, 56, 16);
		lblCNP.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblCNP);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(353, 251, 102, 16);
		lblNume.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(353, 294, 80, 16);
		lblPrenume.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblPrenume);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(353, 342, 56, 16);
		lblAdresa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblAdresa);
		
		JLabel lblTelefon = new JLabel("Numar Telefon");
		lblTelefon.setBounds(353, 389, 102, 16);
		lblTelefon.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblTelefon);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(353, 430, 56, 16);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(lblEmail);
		
		CNP = new JTextField();
		CNP.setBounds(497, 208, 116, 22);
		frame.getContentPane().add(CNP);
		CNP.setColumns(10);
		
		Nume = new JTextField();
		Nume.setBounds(497, 248, 116, 22);
		frame.getContentPane().add(Nume);
		Nume.setColumns(10);
		
		Prenume = new JTextField();
		Prenume.setBounds(497, 291, 116, 22);
		frame.getContentPane().add(Prenume);
		Prenume.setColumns(10);
		
		Adresa = new JTextField();
		Adresa.setBounds(497, 339, 116, 22);
		frame.getContentPane().add(Adresa);
		Adresa.setColumns(10);
		
		Telefon = new JTextField();
		Telefon.setBounds(497, 386, 116, 22);
		frame.getContentPane().add(Telefon);
		Telefon.setColumns(10);
		
		Email = new JTextField();
		Email.setBounds(497, 427, 116, 22);
		frame.getContentPane().add(Email);
		Email.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterUser();
			}
		});
		btnNewButton.setBounds(497, 504, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		lblMesaj = new JLabel("");
		lblMesaj.setBounds(353, 475, 397, 16);
		frame.getContentPane().add(lblMesaj);
		lblMesaj.setForeground(new Color(0, 100, 0));
		lblMesaj.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Receptionist window = new Receptionist();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(177, 13, 95, 48);
		frame.getContentPane().add(Back);
		
	}
}
