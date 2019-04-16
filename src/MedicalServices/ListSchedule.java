package MedicalServices;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import ValidateData.Valid;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListSchedule {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String orarPoliclinica;
	private String programStart;
	private String programEnd;
	private String programStartWeekend;
	private String programEndWeekend;
	private JTextField textField;
	private String nume;
	private String prenume;
	private String StringCNP;
	private String sugestie;
	private static final int LUNI = 2;
	private static int ok = 1;
	private static int esteConcediu = 0;
	private JLabel IncearcaLuni;
	private String[] OrarZile;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static int numarLinii = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListSchedule window = new ListSchedule();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ListScheduleTable(String dataStart)
	{
		String copie = dataStart;
		String user = null;
		for (int j = 0; j < 5; j++)
		{
			switch (j)
			{
			case 0:user = "medic";break;
			case 1:user = "asistent_medical";break;
			case 2:user = "receptioner";break;
			case 3:user = "contabil"; break;
			case 4:user = "inspector"; break;
			}
		String sql = "select CNP, nume, prenume from " + user + ";";
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
				numarLinii = 0;
				dataStart = copie;
				OrarZile = new String[10];
				StringCNP = Conexiune.myRs.getString(1);
				OrarZile[0] = StringCNP;
				nume = Conexiune.myRs.getString(2);
				OrarZile[1] = nume;
				prenume = Conexiune.myRs.getString(3);
				OrarZile[2] = prenume;
				for (int i = 3; i < 10; i++)
				{
					esteConcediu = VerificaConcediu(dataStart, user);
					if (esteConcediu == 1)
					{
						OrarZile[i] = "Concediu";
						continue;
					}
					else
					{
						if (j == 0) //daca este medic
						{OrarZile[i] = VerificaSpecific(dataStart);}
						if (OrarZile[i] == null || j != 0)
						{
							if (i < 8)
							{
								OrarZile[i] = programStart + "-" + programEnd;
							}
							else
							{
								OrarZile[i] = programStartWeekend + "-" + programEndWeekend;
							}
						}
					}
					dataStart = AdaugaZi(dataStart);
				}
				
				model.addRow(OrarZile);
			}
		}
			catch(SQLException catchy)
			{
				System.out.println("Nu s-a putut lua informatiile din tabel");
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
	
	public String VerificaSpecific(String zi)
	{
		String rezultat = "";
		System.out.println(zi);
		String sql = "select ora_inceput, ora_sfarsit, idPoliclinica from orar where ziua = '" + zi +"'" + " and CNP = '" + StringCNP +"';";
		try
		{
			Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
			if (!Conexiune.myRs3.next())
			{
				System.out.println("NU EXISTA");
				return null;
			}
			Conexiune.myRs3.previous();
			while (Conexiune.myRs3.next())
			{
				numarLinii++;
				String ora_inceput = Conexiune.myRs3.getString(1);
				String ora_sfarsit = Conexiune.myRs3.getString(2);
				String idPoliclinica = Conexiune.myRs3.getString(3);
				rezultat += ora_inceput + "-" + ora_sfarsit + " la Policlinica " + idPoliclinica + System.lineSeparator() +"; ";
			}
			return rezultat;
		}
		catch(SQLException esteExceptie)
		{
			System.out.println("Exceptie orar specific");
		}
		return null;
	}
	
	public String AdaugaZi(String data)
	{
		String Maine;
		Calendar myCalendar = Calendar.getInstance();
		try
		{
			Date myDate = sdf.parse(data);
			myCalendar.setTime(myDate);
			myCalendar.add(Calendar.DAY_OF_MONTH, 1);
			Maine = sdf.format(myCalendar.getTime());
			return Maine;
		}
		catch(Exception e5)
		{
			System.out.println("NUU");
		}
		return null;
	}

	/**
	 * Create the application.
	 */
	public ListSchedule() {
		initialize();
		frame.setVisible(true);
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
		frame.setBounds(100, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 118, 1170, 788);
		frame.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		table = new JTable(model);
		/*table.getColumnModel().getColumn(7).setCellRenderer(renderer);*/
	
		model.addColumn("CNP");
		model.addColumn("Nume");
		model.addColumn("Prenume");
		model.addColumn("Luni");
		model.addColumn("Marti");
		model.addColumn("Miercuri");
		model.addColumn("Joi");
		model.addColumn("Vineri");
		model.addColumn("Sambata");
		model.addColumn("Duminica");
		
		
		
		
		
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(473, 58, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel IncearcaLuni = new JLabel("");
		IncearcaLuni.setBounds(333, 93, 460, 16);
		IncearcaLuni.setFont(new Font("Times New Roman", Font.BOLD, 18));
		IncearcaLuni.setForeground(new Color(0, 100, 0));
		IncearcaLuni.setVisible(true);
		frame.getContentPane().add(IncearcaLuni);
		
		JButton btnNewButton = new JButton("See");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Calendar myCalendar = Calendar.getInstance();
				String data = textField.getText();
				if (Valid.isTime(data) == true)
				{try 
				{
					ok = 1;
					Date myDate = sdf.parse(data);
					myCalendar.setTime(myDate);
					int dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);
					sugestie = sdf.format(myCalendar.getTime());
					if (dayOfWeek != LUNI)
					{	ok = 0;
						if (dayOfWeek == 1)
						{
							myCalendar.add(Calendar.DAY_OF_MONTH, 1);
						}
						else
						{
							myCalendar.add(Calendar.DAY_OF_MONTH, -(dayOfWeek - LUNI));
						}
						sugestie = sdf.format(myCalendar.getTime());
					}
					
					if (ok == 0)
					{
						IncearcaLuni.setText("Nu este o zi de luni, incercati: " + sugestie);
					}
					if (ok == 1)
					{
						IncearcaLuni.setText("");
						model.setRowCount(0);
						ListScheduleTable(sugestie);
					}
				}
				catch (Exception ea)
				{
					System.out.println("eroare parsare data");
				}
			}
				else
				{
					final JPanel panel = new JPanel();
					 JOptionPane.showMessageDialog(panel, "Data nu este valida. Formatul trebuie sa fie"
					 		+ "YYYY/MM/DD", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnNewButton.setBounds(617, 57, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(405, 61, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
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
				if (Accountant.AccountantType == false)
				{
					Hr_Manager window = new Hr_Manager();
					window.getFrame().setVisible(true);
				}
				else
				{
					Accountant window = new Accountant();
					window.getFrame().setVisible(true);
				}
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(30, 13, 95, 48);
		frame.getContentPane().add(Back);
		
		
		
	}
}
