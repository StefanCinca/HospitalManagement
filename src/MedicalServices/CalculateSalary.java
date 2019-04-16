package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CalculateSalary {

	private JFrame frame;
	private String[] Luni = {null,"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August",
			"Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
	private JTextField profitTotalTf;
	private JLabel lblProfitTotal;
	private String lunaInceput;
	private String lunaSfarsit;
	private String[] Angajati = {"medic", "asistent_medical", "contabil", "inspector", "receptioner"};
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int daysInMonth = 0;
	private JTable table;
	private DefaultTableModel model;
	private String user;
	private String StringCNP;
	private int idUnitate;
	private int nrZile;
	private int salariu;
	private String an;
	private String luna;
	private int procent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculateSalary window = new CalculateSalary();
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
	public CalculateSalary() {
		initialize();
		frame.setVisible(true);
	}
	
	public void IdentifyUser()
	{
		switch(MainFrame.TYPE)
		{case 0:user = "medic";
		break;
		case 1:user = "asistent_medical";
		break;
		case 3:user = "contabil";
		break;
		case 2:user = "receptioner";
		break;
		case 4: user = "inspector";
		break;
		default:System.out.println("NO ACTION");
		break;
		}
	}
	
	public void GetInfo()
	{
		String sql = null;
		String username = Login.usernameLogin;
		String result;
		if (!(user.equals("medic")))
		 sql = "select CNP, unitate_medicala_idUnitate_Medicala, salariu from " + user + " where username = '" + username + "';";
		else
		{
			sql = "select CNP, unitate_medicala_idUnitate_Medicala, salariu, procent from medic where username ='" + username + "';";
		}
		try {
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA1");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next()) {
			StringCNP = Conexiune.myRs.getString(1);
			idUnitate = Conexiune.myRs.getInt(2);
			salariu = Conexiune.myRs.getInt(3);
			if (user.equals("medic"))
			{
				procent = Conexiune.myRs.getInt(4);
			}
		}
		}
		catch(SQLException eaea)
		{
			System.out.println("EXCEPTIEEEAEAEaEA");
		}
	}
	
	public void CalculeazaSalariu()
	{
		model.setColumnCount(2);
		int nrZile = 0;
		String sql2 = "select durata, data_inceput, data_final from concediu where "
				+ "receptioner_CNP = '" +StringCNP + "' or medic_CNP ='" + StringCNP +
				"' or inspector_CNP = '" +StringCNP + "' or contabil_CNP ='" + StringCNP +
				"' or asistent_medical_CNP ='" + StringCNP + "';";
		try
		{Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql2);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next()) {
			System.out.println("NU EXISTA2");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next()) {
			{
				int durata= Conexiune.myRs.getInt(1);
				String data_inceput = Conexiune.myRs.getString(2);
				String data_final = Conexiune.myRs.getString(3);
				try {
				if (CalculeazaZile(durata, data_inceput, data_final) != -1)
				{
					nrZile += CalculeazaZile(durata, data_inceput, data_final);
					if (CalculeazaZile(durata, data_inceput, data_final) == durata)
					{
						nrZile++;
					}
				}
			}
			catch(ParseException parse)
			{
				System.out.println("Nu a mers prelucrarea zilelor");
			}
		}
		}
		}
		catch(SQLException ea)
		{
			System.out.println("EXCEPTIEEE");
		}
		if (!(user.equals("medic")))
		{
			salariu = salariu * (daysInMonth - nrZile) / daysInMonth;
			String[] v = new String[2];
			v[0] = luna + an;
			v[1] = String.valueOf(salariu);
			model.addRow(v);
			
		}
		else
		{
			double venituri = CalculeazaVenit(true, StringCNP);
			double venit = venituri / (1 - procent / 100);
			double comisioane = venituri * procent / 100;
			int salariuMedic = (int)(salariu + comisioane);
			String[] v = new String[3];
			v[0] = luna + an;
			v[1] = String.valueOf(salariuMedic);
			v[2] = String.valueOf(venituri);
			model.addColumn("Profit adus lantului de policlinici");
			model.addRow(v);
			
			
		}
		
	}
	
	public int CalculeazaZile(int durata, String data_inceput, String data_sfarsit) throws ParseException
	{
			Date data_inceput_d = sdf.parse(data_inceput);
			Date data_sfarsit_d = sdf.parse(data_sfarsit);
			Date luna_inceput_d = sdf.parse(lunaInceput);
			Date luna_sfarsit_d = sdf.parse(lunaSfarsit);
			if (data_inceput_d.after(luna_inceput_d) && data_inceput_d.before(luna_sfarsit_d))
			{
				if (data_sfarsit_d.after(luna_sfarsit_d))
				{
					long diff = data_sfarsit_d.getTime() - luna_sfarsit_d.getTime();
					long nrDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					return durata - (int)nrDays;
				}
				return durata;	
			}
			return -1;
	}
	public double CalculeazaVenit(boolean esteMedic, String StringCNP)
	{	double venit = 0;
		try
		{
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Calculeaza_profit(?, ?, ?, ?)};");
			Conexiune.cstmt.setString(1, lunaInceput);
			Conexiune.cstmt.registerOutParameter(2, java.sql.Types.DOUBLE);
			Conexiune.cstmt.setBoolean(3, esteMedic);
			Conexiune.cstmt.setString(4,  StringCNP);
			Conexiune.cstmt.execute();
			venit = Conexiune.cstmt.getDouble(2);
		}
		catch(SQLException ea)
		{
			System.out.println("Nu s-a putut calcula venitul");
		}
		return venit;
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(233, 51, 56, 16);
		frame.getContentPane().add(lblData);
		
		JComboBox<Integer> comboAn = new JComboBox<Integer>();
		comboAn.setBounds(319, 48, 95, 22);
		frame.getContentPane().add(comboAn);
		int anInceput = 2009;
		for (int i = 0; i < 10; i++)
		{
			comboAn.addItem(anInceput);
			anInceput++;
		}
		
		IdentifyUser();
		GetInfo();
		
		
		JComboBox<String> comboLuna = new JComboBox<String>();
		comboLuna.setBounds(449, 48, 95, 22);
		frame.getContentPane().add(comboLuna);
		for (int i = 1; i <= 12; i++)
		{
			comboLuna.addItem(Luni[i]);
		}
		
		
		JButton btnProfit = new JButton("Calculeaza Salariu");
		btnProfit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.setRowCount(0);
				an = String.valueOf(comboAn.getSelectedItem());
				luna = (String) comboLuna.getSelectedItem();
				String lunaCifre = null;
				int numarLuna = 0;
				int numarAn = Integer.valueOf(an);
				for (int i = 1; i <= 12; i++)
				{
					if (luna.equals(Luni[i]))
					{
						numarLuna = i;
					}
				}
				if (numarLuna < 10)
				{
					lunaCifre = "0" + numarLuna;
				}
				else
				{
					lunaCifre = String.valueOf(numarLuna);
				}
				lunaInceput = an + "-" + lunaCifre + "-" + "01";
				YearMonth yearMonthObject = YearMonth.of(numarAn, numarLuna);
				daysInMonth = yearMonthObject.lengthOfMonth();
				int lunaCifreViitor = (numarLuna + 1) % 12;
				if (lunaCifreViitor == 1)
				{
					numarAn++;
				}
				if (lunaCifreViitor == 0)
				{
					lunaCifreViitor = 12;
				}
				String x = null;
				if (lunaCifreViitor < 10)
				{
					x = "0" + String.valueOf(lunaCifreViitor);
				}
				else
				{
					x = String.valueOf(lunaCifreViitor);
				}
				model.setRowCount(0);
				lunaSfarsit = String.valueOf(numarAn) + "-" + x + "-" + "01";
				CalculeazaSalariu();
				
			
			}
		});
		btnProfit.setBounds(329, 87, 215, 25);
		frame.getContentPane().add(btnProfit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 142, 731, 64);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("data");
		model.addColumn("salariu");
		table.setModel(model);

		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				switch(user)
				{
				case "medic": Medic window = new Medic();frame.dispose();
				break;
				case "asistent_medical": Asistent windowAs = new Asistent(); frame.dispose();
				break;
				case "contabil": Accountant windowAcc = new Accountant(); frame.dispose();
				break;
				case "inspector": Hr_Manager windowManager = new Hr_Manager(); frame.dispose();
				break;
				case "receptioner": Receptionist windowReceptionist = new Receptionist(); frame.dispose();
				break;
				default:
					System.out.println("No Action");
				}
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(30, 13, 95, 48);
		frame.getContentPane().add(Back);
		
		
		
		
	}
}
