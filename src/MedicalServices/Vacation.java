package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Vacation {

	private JFrame frame;
	private JTextField CNP;
	private JTextField durata;
	private JTextField data_inceput;
	private JTextField data_sfarsit;
	private String StringCNP;
	private int durataZile;
	private String StringInceput;
	private String StringSfarsit;
	private JComboBox<String> Functie;
	private String StringFunctie;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vacation window = new Vacation();
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
	public Vacation() {
		initialize();
		frame.setVisible(true);
	}
	
	public void AddVacation()
	{
		try
		{
			if (StringFunctie.equals("Doctor"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_concediu_medic(?, ?, ?, ?, ?)}");
			}
			if (StringFunctie.equals("Nurse"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_concediu_asistent(?, ?, ?, ?, ?)}");
			}
			if (StringFunctie.equals("Accountant"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_concediu_contabil(?, ?, ?, ?, ?)}");
			}
			if (StringFunctie.equals("Receptionist"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_concediu_receptioner(?, ?, ?, ?, ?)}");
			}
			if (StringFunctie.equals("HR Manager"))
			{
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_concediu_inspector(?, ?, ?, ?, ?)}");
			}
			
			Conexiune.cstmt.setString(4, StringCNP);
			Conexiune.cstmt.setInt(1, durataZile);
			Conexiune.cstmt.setString(2, StringInceput);
			Conexiune.cstmt.setString(3, StringSfarsit);
			Conexiune.cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
			
			boolean result = Conexiune.cstmt.execute();
			int ok = Conexiune.cstmt.getInt(5);
			if (ok == 3)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Angajatul nu exista!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (ok == 1)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Nu este un interval valid! Intercalare!!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (ok == 0)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Concediul a fost introdus!", "Succes", JOptionPane.INFORMATION_MESSAGE);
			}
			if (result == true && ok == 0)
			{
				data_sfarsit.setText(StringSfarsit);
			}
			
		}
		catch(SQLException exceptie)
		{
			System.err.println("Nu s-a putut adauga concediu");
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
		
		JLabel LblCNP = new JLabel("CNP");
		LblCNP.setBounds(273, 258, 92, 16);
		LblCNP.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblCNP);
		
		JLabel LblDate = new JLabel("Durata");
		LblDate.setBounds(273, 287, 92, 16);
		LblDate.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblDate);
		
		JLabel LblStartWork = new JLabel("Data Inceput");
		LblStartWork.setBounds(273, 321, 148, 16);
		LblStartWork.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblStartWork);
		
		JLabel LblEndWork = new JLabel("Data Sfarsit");
		LblEndWork.setBounds(273, 354, 137, 16);
		LblEndWork.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblEndWork);
		
		
		CNP = new JTextField();
		CNP.setBounds(460, 258, 116, 22);
		frame.getContentPane().add(CNP);
		CNP.setColumns(10);
		
		durata = new JTextField();
		durata.setBounds(460, 287, 116, 22);
		frame.getContentPane().add(durata);
		durata.setColumns(10);
		
		JTextField data_inceput = new JTextField();
		data_inceput.setBounds(460, 321, 116, 22);
		frame.getContentPane().add(data_inceput);
		data_inceput.setColumns(10);
		
		data_sfarsit = new JTextField();
		data_sfarsit.setBounds(460, 351, 116, 22);
		frame.getContentPane().add(data_sfarsit);
		data_sfarsit.setColumns(10);
		data_sfarsit.setEditable(false);
		
		Functie = new JComboBox<String>();
		Functie.setBounds(460, 223, 116, 22);
		frame.getContentPane().add(Functie);
		Functie.addItem("Doctor");
		Functie.addItem("Nurse");
		Functie.addItem("Accountant");
		Functie.addItem("Receptionist");
		Functie.addItem("HR Manager");
		
		JLabel LblFunctie = new JLabel("Functie");
		LblFunctie.setBounds(273, 223, 116, 22);
		LblFunctie.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblFunctie);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringCNP = CNP.getText();
				durataZile = Integer.parseInt(durata.getText());
				StringInceput = data_inceput.getText();
				Calendar c = Calendar.getInstance();
				Date data_i = null;
				try {
					data_i = sdf.parse(StringInceput);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.setTime(data_i);
				c.add(Calendar.DATE, durataZile);
				StringSfarsit = sdf.format(c.getTime());
				
				StringFunctie = (String) Functie.getSelectedItem();
				AddVacation();
			}
		});
		btnNewButton.setBounds(460, 398, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Hr_Manager window = new Hr_Manager();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(30, 13, 95, 48);
		frame.getContentPane().add(Back);
	}
}
