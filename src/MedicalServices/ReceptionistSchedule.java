package MedicalServices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ValidateData.Valid;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ReceptionistSchedule {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblNewLabel;
	private JTextField anTf;
	private JComboBox<String> comboBox;
	private String an;
	private String luna;
	private int nrLuna;
	private String StringCNP;
	private int idUnitate;
	private String StringNumarLuna;
	private String StringNumarZi;
	private String program;
	private String programStart;
	private String programEnd;
	private String programStartWeekend;
	private String programEndWeekend;
	private String orarPoliclinica;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String[] luni = {null, "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
	private int[] nrZile = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionistSchedule window = new ReceptionistSchedule();
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
	public ReceptionistSchedule() {
		initialize();
		frame.setVisible(true);
	}
	
	public void GetInfo()
	{
		String username = Login.usernameLogin;
		String result;
		String sql = "select CNP, unitate_medicala_idUnitate_Medicala from receptioner where username = '" + username + "';";
		try {
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next()) {
			StringCNP = Conexiune.myRs.getString(1);
			idUnitate = Conexiune.myRs.getInt(2);
		}
		}
		catch(SQLException eaea)
		{
			System.out.println("EXCEPTIEEEAEAEaEA");
		}
	}
	public boolean ValideazaAn()
	{
		if (Valid.isNotAlpha(an) == false)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Anul nu este valid!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		int numarAn = Integer.parseInt(an);
		if (numarAn < 2000 || numarAn > 2019)
		{
			final JPanel panel = new JPanel();
			 JOptionPane.showMessageDialog(panel, "Anul nu este valid!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	public void ShowSchedule()
	{
		String zi = null;
		if (ValideazaAn() == true)
		{
			if (nrLuna < 10)
			{
				StringNumarLuna = "0" + nrLuna;
			}
			else
			{
				StringNumarLuna = String.valueOf(nrLuna);
			}
			for (int i = 1; i <= nrZile[nrLuna]; i++)
			{
				if (i < 10)
				{
					StringNumarZi = "0" + i;
				}
				else
				{
					StringNumarZi = String.valueOf(i);
				}
				zi = an + "-" + StringNumarLuna + "-" + StringNumarZi;
				int esteConcediu = VerificaConcediu(zi, "receptioner");
				if (esteConcediu == 1)
				{
					program = "concediu";
				}
				else
				{
					int esteWeekend = 0;
					Calendar c = Calendar.getInstance();
					Date d;
					try {
						d = sdf.parse(zi);
						c.setTime(d);
						if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
						{
							esteWeekend = 1;
							program = programStartWeekend + "-" + programEndWeekend;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (esteWeekend == 0)
					program = programStart + "-" + programEnd;
				}
				String[] Vector = new String[2];
				Vector[0] = zi;
				Vector[1] = program;
				model.addRow(Vector);
			}
			
		}
		
	}
	public int VerificaConcediu(String zi, String angajat)
	{
		String sql = "select data_inceput, data_final from concediu where " + angajat+"_CNP = '" + StringCNP + "';";
		try
		{
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next())
			{
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next())
			{
				String data_inceput = Conexiune.myRs2.getString(1);
				String data_sfarsit = Conexiune.myRs2.getString(2);
				try
				{
					Date data_inceput_d = sdf.parse(data_inceput);
					Date data_sfarsit_d = sdf.parse(data_sfarsit);
					Date d_zi = sdf.parse(zi);
					if (d_zi.after(data_inceput_d) && d_zi.before(data_sfarsit_d) || d_zi.equals(data_inceput_d) || d_zi.equals(data_sfarsit_d))
					{
						return 1;
					}
					else
					{
						return 0;
					}
				}
				catch(Exception e123)
				{
					System.out.println("Nu a mers conversia datelor");
				}
				
			}
		}
			catch(SQLException catchy)
			{
				System.out.println("Nu s-a putut lua informatiile");
			}
		return 0;
	}
	
	public void GetSchedulePoliclinica()
	{
		String sql = "select program_functionare from unitate_medicala;";
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 84, 827, 456);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		model.addColumn("Data");
		model.addColumn("Program");
		table.setModel(model);
		
		lblNewLabel = new JLabel("Data");
		lblNewLabel.setBounds(299, 45, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		anTf = new JTextField();
		anTf.setBounds(367, 42, 116, 22);
		frame.getContentPane().add(anTf);
		anTf.setColumns(10);
		
		comboBox = new JComboBox<String>();
		for (int i = 1; i < 13; i++)
		{
			comboBox.addItem(luni[i]);
		}
		comboBox.setBounds(513, 42, 114, 22);
		frame.getContentPane().add(comboBox);
		GetInfo();
		System.out.println(StringCNP);
		System.out.println(idUnitate);
		JButton btnSearch = new JButton("Vezi programul");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				an = anTf.getText();
				luna = (String)comboBox.getSelectedItem();
				for (int i = 1; i <= 12; i++)
				{
					if (luna.equals(luni[i]))
					{
						nrLuna = i;
					}
				}
				ShowSchedule();
			}
		});
		btnSearch.setBounds(650, 41, 124, 25);
		frame.getContentPane().add(btnSearch);
		
		GetSchedulePoliclinica();
		
		programStart = orarPoliclinica.substring(5, 7);
		programStart = programStart.concat(":00");
		
		programEnd = orarPoliclinica.substring(8, 10);
		programEnd = programEnd.concat(":00");
		
		programStartWeekend = orarPoliclinica.substring(17, 19);
		programEndWeekend = orarPoliclinica.substring(20, 22);
		
		programStartWeekend = programStartWeekend.concat(":00");
		programEndWeekend = programEndWeekend.concat(":00");
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Receptionist window = new Receptionist();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(30, 13, 95, 48);
		frame.getContentPane().add(Back);
		
	}
}
