package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ListVacations {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private String nume;
	private String prenume;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListVacations window = new ListVacations();
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
	public ListVacations() {
		initialize();
		frame.setVisible(true);
	}
	public void GetInfo(String type, String CNP)
	{
		String sql = "select * from " + type + " where CNP like '%" + CNP + "%';";
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
				nume = Conexiune.myRs2.getString(2);
				prenume = Conexiune.myRs2.getString(3);
				
			}
		}
			catch(SQLException catchy)
			{
				System.out.println("Nu s-a putut lua informatiile");
			}
		
	}
	public void Vacations()
	{
		String sql = "select * from concediu;";
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
				String dataInceput = Conexiune.myRs.getString(3);
				String dataSfarsit = Conexiune.myRs.getString(4);
				String resultConcediu = dataInceput + "   :   " + dataSfarsit;
				System.out.println(resultConcediu);
				String receptionerCNP = Conexiune.myRs.getString(5);
				String medicCNP = Conexiune.myRs.getString(6);
				String inspectorCNP = Conexiune.myRs.getString(7);
				String contabilCNP = Conexiune.myRs.getString(8);
				String asistentCNP = Conexiune.myRs.getString(9);
				String[] row = null;
				if (receptionerCNP != null)
				{
					GetInfo("receptioner", receptionerCNP);
					row = new String[]{receptionerCNP, nume, prenume, resultConcediu};
				}
				if (inspectorCNP != null)
				{
					GetInfo("inspector", inspectorCNP);
					row = new String[]{inspectorCNP, nume, prenume, resultConcediu};
				}
				if (contabilCNP != null)
				{
					GetInfo("contabil", contabilCNP);
					row = new String[]{contabilCNP, nume, prenume, resultConcediu};
				}
				if (asistentCNP != null)
				{
					GetInfo("asistent_medical", asistentCNP);
					row = new String[]{asistentCNP, nume, prenume, resultConcediu};
				}
				if (medicCNP != null)
				{
					GetInfo("medic", medicCNP);
					row = new String[]{medicCNP, nume, prenume, resultConcediu};
				}
				model.addRow(row);
				
			}
		}
		catch(SQLException ea)
		{
			System.err.println("Nu s-a putut lista tabelul");
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
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel();
		table = new JTable(model);
	
		model.addColumn("CNP");
		model.addColumn("Nume");
		model.addColumn("Prenume");
		model.addColumn("Concediu");
		
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		Vacations();
		
		
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
		frame.getContentPane().add(Back, BorderLayout.WEST);
		
		
		
	}

}
