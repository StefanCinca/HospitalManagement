package MedicalServices;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ValidateData.Valid;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AddSchedule {

	private JFrame frame;
	private JComboBox<String> CNP;
	private JTextField Date;
	private JComboBox<String> PoliclinicaBox;
	private String StringCNP;
	private String StringDate;
	private String StringStart;
	private String StringEnd;
	private int idPoliclinica;
	private JLabel EroareOrar;
	private JLabel NotExistCNP;
	private JLabel SuccesOrar;
	private JLabel OrarActualizat;
	private String orarPoliclinica;
	private String programStart;
	private String programEnd;
	private String programStartWeekend;
	private String programEndWeekend;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSchedule window = new AddSchedule();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean ValideazaDate()
	{
		if (StringCNP.equals("") || StringDate.equals(""))
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Cel putin unul din campuri este gol", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if (Valid.isValidCNP(StringCNP) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "CNP-ul este invalid", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		if (Valid.isTime(StringDate) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Data este invalida", "Error", JOptionPane.ERROR_MESSAGE);
			 return false;
		}
		return true;
	}
	
	public void GetSchedulePoliclinica()
	{
		String sql = "select program_functionare from unitate_medicala where idUnitate_Medicala =" + idPoliclinica +";";
		try
		{
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next())
			{
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next())
			{
				orarPoliclinica = Conexiune.myRs.getString(1);
			}
		}
		catch(SQLException e)
		{
			System.err.println("Nu s-a putut lua orarul policlinicii");
		}
	}
	
	public void PopulateJComboBox()
	{
		String cnpToPopulate = null;
		String sql = "select CNP from medic;";
		try
		{
			Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
			if (!Conexiune.myRs3.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs3.previous();
			while (Conexiune.myRs3.next())
			{
				cnpToPopulate = Conexiune.myRs3.getString(1);
				CNP.addItem(cnpToPopulate);
			}
		}
		catch(SQLException exceptie)
		{
			System.out.println("Nu a mers preluarea CNP-ului de la medici");
		}
	}

	/**
	 * Create the application.
	 */
	public AddSchedule() {
		initialize();
		frame.setVisible(true);
	}
	
	public void AddSchedule()
	{
		if (ValideazaDate() == true)
		{try
		{
			int ok = -1;
			int numarStart = 0;
			int numarEnd = 0;
			char[] array = StringStart.toCharArray();
			char[] array2 = StringEnd.toCharArray();
			for (int i = 0; i < 2; i++)
			{
				numarStart = numarStart * 10 + (array[i] - '0');
				numarEnd = numarEnd * 10 + (array2[i] - '0');
			}
			System.out.println(numarStart);
			System.out.println(numarEnd);
			if (numarStart >= numarEnd)
			{
				EroareOrar.setVisible(true);
				return;
			}
			Calendar myCalendar = Calendar.getInstance();
			int dayOfWeek = 0;
			Date myDate = null;
			try
			{
				myDate = sdf.parse(StringDate);
			myCalendar.setTime(myDate);
			dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);
			}
			catch(Exception e)
			{
				System.out.println("Nu s-a putut verifica ce zi este");
			}
			String oraInceputPoliclinica = null;
			String oraSfarsitPoliclinica = null;
			if (dayOfWeek == 1 || dayOfWeek == 7)
			{
				oraInceputPoliclinica = programStartWeekend;
				oraSfarsitPoliclinica = programEndWeekend;
			}
			else
			{
				oraInceputPoliclinica = programStart;
				oraSfarsitPoliclinica = programEnd;
			}
			int numarStartPoliclinica = 0, numarEndPoliclinica = 0;
			char[] arrayStartPol = oraInceputPoliclinica.toCharArray();
			char[] arrayEndPol = oraSfarsitPoliclinica.toCharArray();
			for (int i = 0; i < 2; i++)
			{
				numarStartPoliclinica = numarStartPoliclinica * 10 + arrayStartPol[i] - '0';
				numarEndPoliclinica = numarEndPoliclinica * 10 + arrayEndPol[i] - '0';
			}
			if (numarStart < numarStartPoliclinica)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Orarul nu corespunde cu orarul policlinicii", "Error", JOptionPane.ERROR_MESSAGE);
				 return;
				
			}
			if (numarEnd > numarEndPoliclinica)
				{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Orarul nu corespunde cu orarul policlinicii", "Error", JOptionPane.ERROR_MESSAGE);
				 return;
				
				}
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_orar(?, ?, ?, ?, ?, ?)}");
			Conexiune.cstmt.setString(1, StringCNP);
			Conexiune.cstmt.setString(2, StringDate);
			Conexiune.cstmt.setString(3, StringStart);
			Conexiune.cstmt.setString(4, StringEnd);
			Conexiune.cstmt.setInt(5, idPoliclinica);
			Conexiune.cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
			Conexiune.cstmt.execute();
			ok = Conexiune.cstmt.getInt(6);
			if (ok == 0)
			{
				NotExistCNP.setVisible(true);
			}
			if (ok == 1)
			{
				SuccesOrar.setVisible(true);
			}
			if (ok == 2)
			{
				OrarActualizat.setVisible(true);
			}
			
		}
		catch(SQLException exception)
		{
			System.err.println("Eroare introducere orar");
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
		
		JLabel LblCNP = new JLabel("CNP");
		LblCNP.setBounds(309, 258, 56, 16);
		LblCNP.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblCNP);
		
		JLabel LblDate = new JLabel("Date");
		LblDate.setBounds(309, 287, 56, 16);
		LblDate.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblDate);
		
		JLabel LblStartWork = new JLabel("Start Work");
		LblStartWork.setBounds(309, 321, 112, 16);
		LblStartWork.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblStartWork);
		
		JLabel LblEndWork = new JLabel("End Work");
		LblEndWork.setBounds(309, 354, 101, 16);
		LblEndWork.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblEndWork);
		
		JLabel LblPoliclinica = new JLabel("Policlinica");
		LblPoliclinica.setBounds(309, 387, 101, 16);
		LblPoliclinica.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(LblPoliclinica);
		
		PoliclinicaBox = new JComboBox<String>();
		PoliclinicaBox.setBounds(460, 387, 116, 22);
		frame.getContentPane().add(PoliclinicaBox);
		PoliclinicaBox.addItem("1");
		PoliclinicaBox.addItem("2");
		PoliclinicaBox.addItem("3");
		
		CNP = new JComboBox<String>();
		CNP.setBounds(460, 258, 116, 22);
		frame.getContentPane().add(CNP);
		PopulateJComboBox();
		
		
		Date = new JTextField();
		Date.setBounds(460, 287, 116, 22);
		frame.getContentPane().add(Date);
		Date.setColumns(10);
		
		JComboBox startWork = new JComboBox();
		startWork.setBounds(460, 321, 116, 22);
		frame.getContentPane().add(startWork);
		
		JComboBox endWork = new JComboBox();
		endWork.setBounds(460, 351, 116, 22);
		frame.getContentPane().add(endWork);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringCNP = (String)CNP.getSelectedItem();
				StringDate = Date.getText();
				StringStart = (String) startWork.getSelectedItem();
				StringEnd = (String) endWork.getSelectedItem();
				idPoliclinica = Integer.parseInt( (String) PoliclinicaBox.getSelectedItem());
				OrarActualizat.setVisible(false);
				SuccesOrar.setVisible(false);
				EroareOrar.setVisible(false);
				NotExistCNP.setVisible(false);
				GetSchedulePoliclinica();
				programStart = orarPoliclinica.substring(5, 7);
				programStart = programStart.concat(":00");
				
				programEnd = orarPoliclinica.substring(8, 10);
				programEnd = programEnd.concat(":00");
				
				programStartWeekend = orarPoliclinica.substring(17, 19);
				programEndWeekend = orarPoliclinica.substring(20, 22);
				
				programStartWeekend = programStartWeekend.concat(":00");
				programEndWeekend = programEndWeekend.concat(":00");
				AddSchedule();
			}
		});
		btnAdd.setBounds(460, 436, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		for (int i = 0; i < 10; i++)
		{
			String hour = "0" + i + ":00";
			startWork.addItem(hour);
			endWork.addItem(hour);
		}
		for (int i = 10; i <= 23; i++)
		{
			String hour = i + ":00";
			startWork.addItem(hour);
			endWork.addItem(hour);
			
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
		Back.setBounds(30, 13, 95, 48);
		frame.getContentPane().add(Back);
		
		EroareOrar = new JLabel("Programul de lucru nu este valid!!");
		EroareOrar.setBounds(600, 357, 270, 16);
		EroareOrar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		EroareOrar.setForeground(Color.RED);
		EroareOrar.setVisible(false);
		frame.getContentPane().add(EroareOrar);
		
		NotExistCNP = new JLabel("CNP-ul nu exista!!");
		NotExistCNP.setBounds(588, 261, 282, 16);
		NotExistCNP.setFont(new Font("Times New Roman", Font.BOLD, 18));
		NotExistCNP.setForeground(Color.RED);
		NotExistCNP.setVisible(false);
		frame.getContentPane().add(NotExistCNP);
		
		SuccesOrar = new JLabel("Orarul a fost adaugat!");
		SuccesOrar.setBounds(411, 474, 293, 16);
		SuccesOrar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		SuccesOrar.setForeground(new Color(0, 100, 0));
		SuccesOrar.setVisible(false);
		frame.getContentPane().add(SuccesOrar);
		
		OrarActualizat = new JLabel("Orarul a fost actualizat!");
		OrarActualizat.setBounds(412, 474, 264, 16);
		OrarActualizat.setFont(new Font("Times New Roman", Font.BOLD, 18));
		OrarActualizat.setForeground(new Color(0, 100, 0));
		OrarActualizat.setVisible(false);
		frame.getContentPane().add(OrarActualizat);
		
		
	
		
	}
}
