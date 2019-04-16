package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SearchEmployee {

	private JFrame frame;
	private int deplasament = 29;
	private int startHeight = 66;
	private JTextField Nume;
	private JTextField Prenume;
	private JComboBox<String> Functie;
	private String StringNume;
	private String StringPrenume;
	private String StringFunctie;
	private int ok = 0;
	private JLabel[] array;
	private JTextField[] arrayText;
	private static final int NR = 14;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEmployee window = new SearchEmployee();
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
	public SearchEmployee() {
		initialize();
		frame.setVisible(true);
	}
	
	public void GetInfoFromDatabase(String Type) throws SQLException
	{
		String result;
		System.out.println(StringNume);
		System.out.println(StringPrenume);
		System.out.println(Type);
		String sql = "select * from " + Type + " where nume = '" + StringNume +"' and prenume = '" + StringPrenume + "';";
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next())
		{
		System.out.println(Conexiune.myRs.getString("nume"));
		result = Conexiune.myRs.getString("nume");
		arrayText[0].setText(result);
		result = Conexiune.myRs.getString("prenume");
		arrayText[1].setText(result);
		result = Conexiune.myRs.getString("CNP");
		arrayText[2].setText(result);
		result = Conexiune.myRs.getString("adresa");
		arrayText[3].setText(result);
		result = Conexiune.myRs.getString("email");
		arrayText[4].setText(result);
		result = Conexiune.myRs.getString("numar_telefon");
		arrayText[5].setText(result);
		result = Conexiune.myRs.getString("data_angajarii");
		arrayText[6].setText(result);
		int numar = Conexiune.myRs.getInt("numar_contract");
		arrayText[7].setText(String.valueOf(numar));
		result = Conexiune.myRs.getString("iban");
		arrayText[8].setText(result);
		int salariu = Conexiune.myRs.getInt("salariu");
		arrayText[9].setText(String.valueOf(salariu));
		int ore = Conexiune.myRs.getInt("numar_ore");
		arrayText[10].setText(String.valueOf(ore));
		for (int i = 0; i < 11; i++)
		{
			arrayText[i].setEditable(false);
		}
		if (Type.equals("asistent_medical"))
		{
			arrayText[11].setText(Conexiune.myRs.getString("tipul"));
		}
		if (Type.equals("medic"))
		{
			arrayText[11].setText(Conexiune.myRs.getString("grad"));
			arrayText[12].setText(Conexiune.myRs.getString("cod_parafa"));
			int procent = Conexiune.myRs.getInt("procent");
			arrayText[13].setText(String.valueOf(procent));
			for (int i = 11; i < 14; i++)
			{
				arrayText[i].setEditable(false);
			}
		}
		}
		
	}
	
	public void DoctorInfo()
	{
		array[11].setText("Grad");
		for (JLabel item : array)
		{
			item.setVisible(true);
		}
		for (JTextField item : arrayText)
		{
			item.setVisible(true);
		}
		try
		{
			GetInfoFromDatabase("medic");
			
		}
		catch(SQLException catchThis)
		{
			System.err.println("Eroare preluare informatii pentru medic");
		}
		
	}
	
	public void NurseInfo()
	{
		for (JLabel item : array)
		{
			item.setVisible(true);
		}
		for (JTextField item: arrayText)
		{
			item.setVisible(true);
		}
		array[11].setText("Tipul");
		for (int i = 12; i < NR; i++)
		{
			array[i].setVisible(false);
			arrayText[i].setVisible(false);
		}
		try
		{
			GetInfoFromDatabase("asistent_medical");
			
		}
		catch(SQLException catchThis)
		{
			System.err.println("Eroare preluare informatii pentru asistent");
		}
	}
	
	public void ReceptionistInfo()
	{
		for (int i = 0; i < 11; i++)
		{
			array[i].setVisible(true);
			arrayText[i].setVisible(true);
		}
		for (int i = 11; i < NR; i++)
		{
			array[i].setVisible(false);
			arrayText[i].setVisible(false);
		}
		try
		{
			GetInfoFromDatabase("receptioner");
			
		}
		catch(SQLException catchThis)
		{
			System.err.println("Eroare preluare informatii pentru receptioner");
		}
		
	}
	
	public void AccountantInfo()
	{
		for (int i = 0; i < 11; i++)
		{
			array[i].setVisible(true);
			arrayText[i].setVisible(true);
		}
		for (int i = 11; i < NR; i++)
		{
			array[i].setVisible(false);
			arrayText[i].setVisible(false);
		}
		try
		{
			GetInfoFromDatabase("contabil");
			
		}
		catch(SQLException catchThis)
		{
			System.err.println("Eroare preluare informatii pentru contabil");
		}
		
	}
	
	public void HRManagerInfo()
	{
		for (int i = 0; i < 11; i++)
		{
			array[i].setVisible(true);
			arrayText[i].setVisible(true);
		}
		for (int i = 11; i < NR; i++)
		{
			array[i].setVisible(false);
			arrayText[i].setVisible(false);
		}
		try
		{
			GetInfoFromDatabase("inspector");
			
		}
		catch(SQLException catchThis)
		{
			System.err.println("Eroare preluare informatii pentru HRManager");
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
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(84, 221, 80, 16);
		frame.getContentPane().add(lblNume);
		lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(84, 265, 112, 16);
		frame.getContentPane().add(lblPrenume);
		lblPrenume.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		JLabel lblFunctie = new JLabel("Functie");
		lblFunctie.setBounds(84, 307, 99, 16);
		frame.getContentPane().add(lblFunctie);
		lblFunctie.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		
		Nume = new JTextField();
		Nume.setBounds(194, 221, 116, 22);
		frame.getContentPane().add(Nume);
		Nume.setColumns(10);
		
		Prenume = new JTextField();
		Prenume.setBounds(194, 265, 116, 22);
		frame.getContentPane().add(Prenume);
		Prenume.setColumns(10);
		
		Functie = new JComboBox<String>();
		Functie.setBounds(194, 307, 116, 22);
		frame.getContentPane().add(Functie);
		Functie.addItem("Doctor");
		Functie.addItem("Nurse");
		Functie.addItem("Accountant");
		Functie.addItem("Receptionist");
		Functie.addItem("HR Manager");
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringNume = Nume.getText();
				StringPrenume = Prenume.getText();
				StringFunctie = (String) Functie.getSelectedItem();
				if (StringFunctie.equals("Doctor"))
				{
					DoctorInfo();
				}
				if (StringFunctie.equals("Nurse"))
				{
					NurseInfo();
				}
				if (StringFunctie.equals("Accountant"))
				{
					AccountantInfo();
				}
				if (StringFunctie.equals("Receptionist"))
				{
					ReceptionistInfo();
				}
				if (StringFunctie.equals("HR Manager"))
				{
					HRManagerInfo();
				}
			}
		});
		btnSearch.setBounds(194, 342, 97, 25);
		frame.getContentPane().add(btnSearch);
		
		array = new JLabel[NR];
		arrayText = new JTextField[NR];
		int startHeight = 66;
		
		array[0] = new JLabel("Nume");
		array[1] = new JLabel("Prenume");
		array[2] = new JLabel("CNP");
		array[3] = new JLabel("Adresa");
		array[4] = new JLabel("Email");
		array[5] = new JLabel("Telefon");
		array[6]= new JLabel("Data Angajare");
		array[7] = new JLabel("Numar Contract");
		array[8] = new JLabel("IBAN");
		array[9] = new JLabel("Salariu");
		array[10]= new JLabel("Numar ore");
		array[11]= new JLabel("Grad");
		array[12] = new JLabel("Cod Parafa");
		array[13] = new JLabel("Procent");
		
		for (int i = 0; i < NR; i++)
		{
			array[i].setFont(new Font ("Times New Roman", Font.BOLD, 15));
			array[i].setBounds(357, startHeight + deplasament, 150, 16);
			array[i].setVisible(false);
			startHeight += deplasament;
			frame.getContentPane().add(array[i]);
		}
		startHeight = 66;
		for (int i = 0; i < NR; i++)
		{
			arrayText[i] = new JTextField();
			arrayText[i].setBounds(500, startHeight + deplasament, 150, 20);
			arrayText[i].setVisible(false);
			frame.getContentPane().add(arrayText[i]);
			startHeight += deplasament;
		}
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
		
		
		
		
		
	}
}
