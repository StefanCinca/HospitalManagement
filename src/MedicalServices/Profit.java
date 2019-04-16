package MedicalServices;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Profit {

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profit window = new Profit();
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
	public Profit() {
		initialize();
		frame.setVisible(true);
	}
	
	public int CalculeazaCheltuieli() throws ParseException
	{
		int nrZile = 0;
		int cheltuieli = 0;
		int salariuMedic = 0;
		double profitMedic = 0;
		double venitMedic = 0;
		String StringCNP = null;
		for (String angajat : Angajati)
		{
			try
			{
				String sql = "select CNP from " + angajat +";";
				Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
				Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
				if (!Conexiune.myRs2.next()) {
					System.out.println("NU EXISTA1");
				}
				Conexiune.myRs2.previous();
				while (Conexiune.myRs2.next()) {
					nrZile = 0;
					StringCNP = Conexiune.myRs2.getString(1);
					String sql2 = "select durata, data_inceput, data_final from concediu where "
							+ "receptioner_CNP = '" +StringCNP + "' or medic_CNP ='" + StringCNP +
							"' or inspector_CNP = '" +StringCNP + "' or contabil_CNP ='" + StringCNP +
							"' or asistent_medical_CNP ='" + StringCNP + "';";
					Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql2);
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
							if (CalculeazaZile(durata, data_inceput, data_final) != -1)
							{
								nrZile += CalculeazaZile(durata, data_inceput, data_final);
								if (CalculeazaZile(durata, data_inceput, data_final) == durata)
								{
									nrZile++;
								}
							}
						}
					}
					String sql3 = "select salariu, nume, prenume from " + angajat + " where CNP = '" + StringCNP +"';";
					Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql3);
					Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
					if (!Conexiune.myRs3.next()) {
						System.out.println("NU EXISTA3");
					}
					Conexiune.myRs3.previous();
					while (Conexiune.myRs3.next()) {
						{
							if (angajat.equals("medic"))
							{
								String[] Vector = new String[5];
								String StringNumeMedic = Conexiune.myRs3.getString(2);
								String StringPrenumeMedic = Conexiune.myRs3.getString(3);
								String StringNumePrenumeMedic = StringNumeMedic + " " + StringPrenumeMedic;
								Vector[0] = StringNumePrenumeMedic;
								salariuMedic = Conexiune.myRs3.getInt(1);
								Vector[1] = String.valueOf(salariuMedic);
								venitMedic = CalculeazaVenit(true, StringCNP);
								Vector[2] = String.valueOf(venitMedic);
								profitMedic = venitMedic - salariuMedic;
								Vector[3] = String.valueOf(profitMedic);
								model.addRow(Vector);
							}
							int salariu = Conexiune.myRs3.getInt(1);
							cheltuieli += salariu * (daysInMonth - nrZile) / daysInMonth;
						}
				}
			}
			}
			catch(SQLException exceptie)
			{
				System.out.println("Nu s-a putut prelua CNP-ul medicului");
			}
		}
		return cheltuieli;
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

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		
		JComboBox<String> comboLuna = new JComboBox<String>();
		comboLuna.setBounds(449, 48, 95, 22);
		frame.getContentPane().add(comboLuna);
		for (int i = 1; i <= 12; i++)
		{
			comboLuna.addItem(Luni[i]);
		}
		
		
		JButton btnProfit = new JButton("Calculeaza Profit");
		btnProfit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.setRowCount(0);
				String an = String.valueOf(comboAn.getSelectedItem());
				String luna = (String) comboLuna.getSelectedItem();
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
				lunaSfarsit = String.valueOf(numarAn) + "-" + x + "-" + "01";
				try {
					lblProfitTotal.setVisible(true);
					profitTotalTf.setVisible(true);
					double ch = CalculeazaVenit(false, null);
					ch -= CalculeazaCheltuieli();
					profitTotalTf.setText(String.valueOf(ch));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnProfit.setBounds(329, 87, 215, 25);
		frame.getContentPane().add(btnProfit);
		
		lblProfitTotal = new JLabel("Profitul total");
		lblProfitTotal.setBounds(233, 133, 69, 16);
		lblProfitTotal.setVisible(false);
		frame.getContentPane().add(lblProfitTotal);
		
		profitTotalTf = new JTextField();
		profitTotalTf.setBounds(376, 130, 116, 22);
		frame.getContentPane().add(profitTotalTf);
		profitTotalTf.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(117, 162, 568, 79);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		profitTotalTf.setVisible(false);
		model = new DefaultTableModel();
		model.addColumn("Nume");
		model.addColumn("Salariu");
		model.addColumn("Castig");
		model.addColumn("Profit");
		table.setModel(model);
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
					Accountant window = new Accountant();
					window.getFrame().setVisible(true);
				
				
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(30, 13, 95, 48);
		frame.getContentPane().add(Back, BorderLayout.WEST);
		
		
	}
}
