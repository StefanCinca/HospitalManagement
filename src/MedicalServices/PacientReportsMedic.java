package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class PacientReportsMedic {

	private JFrame frame;
	private DefaultTableModel model2;
	private JTable table;
	private JTable table2;
	private JTextField numeTf;
	private JTextField prenumeTf;
	private String numePacient;
	private String prenumePacient;
	private JTextField textField;
	private JTextField textField_1;
	private String numePacient1;
	private String prenumePacient1;
	private DefaultTableModel model1;
	private String StringPacientCNP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacientReportsMedic window = new PacientReportsMedic();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ShowAllReports()
	{
		int idRaport = -1;
		int idAnalize = -1;
		int valoareNumerica = -1;
		boolean valoareBinara = false;
		String denumireAnaliza = null;
		try
		{
			
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Arata_rapoarte_medicale_dupa_pacient(?, ?)};");
			Conexiune.cstmt.setString(1, numePacient);
			Conexiune.cstmt.setString(2, prenumePacient);
			
			boolean hadResult = Conexiune.cstmt.execute();
			while (hadResult)
			{
				Conexiune.myRs = Conexiune.cstmt.getResultSet();
				if (!Conexiune.myRs.next())
				{
					System.out.println("Nu Exista");
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next())
				{
					idRaport = Conexiune.myRs.getInt(1);
					idAnalize = Conexiune.myRs.getInt(2);
					valoareNumerica = Conexiune.myRs.getInt(3);
					valoareBinara = Conexiune.myRs.getBoolean(4);
					String sql2 = "select nume from servicii_medicale where idServicii_medicale ='" + idAnalize +"';";
					Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql2);
					Conexiune.myRs3 = Conexiune.preparedStatement.executeQuery();
					if (!Conexiune.myRs3.next()) {
						System.out.println("NU EXISTA specializarea");
					}
					Conexiune.myRs3.previous();
					while (Conexiune.myRs3.next()) {
						denumireAnaliza = Conexiune.myRs3.getString(1);
					}
					String[] vector = new String[4];
					vector[0] =String.valueOf(idRaport);
					vector[2] =String.valueOf(valoareNumerica);
					vector[3] =String.valueOf(valoareBinara);
					vector[1] = denumireAnaliza;
					model2.addRow(vector);
				}
				hadResult = Conexiune.cstmt.getMoreResults();
			}
		}
		catch(SQLException e2)
		{
			System.out.println("Nu s-au putut afisa rapoartele");
		}
	}
	public void getPacientCNP()
	{
		try
		{
			String sql = "select CNP from pacient where nume = '" + numePacient1 +"' and prenume ='"
					+ prenumePacient1 + "';";
			
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next()) {
			StringPacientCNP = Conexiune.myRs2.getString(1);
		}
		}
		catch(SQLException exceptie2)
		{
			System.out.println("Nu a mers preluarea CNP-ului pacientului");
		}
	}
	public void ShowMedicalReports()
	{
		getPacientCNP();
		String f_NumePacient = null;
		String f_NumeMedic = null;
		String f_investigatii = null;
		String f_simptome = null;
		String f_diagnostic = null;
		String f_istoric = null;
		String f_recomandari = null;
		String f_ziua = null;
		try
		{
			
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Arata_rapoarte_medicale_dupa_pacient_medic(?, ?)};");
			Conexiune.cstmt.setString(1, numePacient1);
			Conexiune.cstmt.setString(2, prenumePacient1);
			boolean hadResult = Conexiune.cstmt.execute();
			while (hadResult)
			{
				Conexiune.myRs = Conexiune.cstmt.getResultSet();
				if (!Conexiune.myRs.next())
				{
					System.out.println("Nu Exista");
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next())
				{
					f_NumePacient = Conexiune.myRs.getString(1);
					f_NumeMedic = Conexiune.myRs.getString(2);
					f_investigatii = Conexiune.myRs.getString(3);
					f_simptome = Conexiune.myRs.getString(4);
					f_diagnostic = Conexiune.myRs.getString(5);
					f_istoric = Conexiune.myRs.getString(6);
					f_recomandari = Conexiune.myRs.getString(7);
					f_ziua = Conexiune.myRs.getString(8);
					
					String[] vector = new String[8];
					vector[0] = f_NumePacient;
					vector[1] = f_NumeMedic;
					vector[2] = f_investigatii;
					vector[3] = f_simptome;
					vector[4] = f_diagnostic;
					vector[5] = f_istoric;
					vector[6] = f_recomandari;
					vector[7] = f_ziua;
					model1.addRow(vector);
				}
				hadResult = Conexiune.cstmt.getMoreResults();
			}
		}
		catch(SQLException e2)
		{
			System.out.println("Nu s-au putut afisa rapoartele");
		}
	}

	/**
	 * Create the application.
	 */
	public PacientReportsMedic() {
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
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(28, 120, 56, 16);
		frame.getContentPane().add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(28, 151, 56, 16);
		frame.getContentPane().add(lblPrenume);
		
		numeTf = new JTextField();
		numeTf.setBounds(114, 117, 116, 22);
		frame.getContentPane().add(numeTf);
		numeTf.setColumns(10);
		
		prenumeTf = new JTextField();
		prenumeTf.setBounds(114, 148, 116, 22);
		frame.getContentPane().add(prenumeTf);
		prenumeTf.setColumns(10);
		
		JButton btnHistory = new JButton("Vezi Rapoartele");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numePacient = numeTf.getText();
				prenumePacient = prenumeTf.getText();
				model2.setRowCount(0);
				ShowAllReports();
				
			}
		});
		
		btnHistory.setBounds(108, 183, 135, 25);
		frame.getContentPane().add(btnHistory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(255, 99, 615, 126);
		frame.getContentPane().add(scrollPane);
		model2 = new DefaultTableModel();
		table = new JTable();
		scrollPane.setViewportView(table);
		model2.addColumn("Id Raport");
		model2.addColumn("Analize");
		model2.addColumn("Valoare Numerica");
		model2.addColumn("Valoare binara");
		table.setModel(model2);
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Medic window = new Medic();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 882, 64);
		frame.getContentPane().add(Back);
		
		JLabel lblNewLabel = new JLabel("Rapoarte Medicale Analize");
		lblNewLabel.setBounds(46, 91, 184, 16);
		lblNewLabel.setFont(new Font("TimesRoman", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNume1 = new JLabel("Nume");
		lblNume1.setBounds(28, 307, 56, 16);
		frame.getContentPane().add(lblNume1);
		
		JLabel lblPrenume1 = new JLabel("Prenume");
		lblPrenume1.setBounds(28, 336, 56, 16);
		frame.getContentPane().add(lblPrenume1);
		
		textField = new JTextField();
		textField.setBounds(114, 304, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(114, 333, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRapoarteMedicale = new JButton("Vezi Rapoartele Medicale");
		btnRapoarteMedicale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model1.setRowCount(0);
				numePacient1 = textField.getText();
				prenumePacient1 = textField_1.getText();
				ShowMedicalReports();
				
			}
		});
		btnRapoarteMedicale.setBounds(28, 368, 203, 25);
		frame.getContentPane().add(btnRapoarteMedicale);
		table2 = new JTable();
		model1 = new DefaultTableModel();
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(251, 261, 619, 254);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(table2);
		model1.addColumn("Nume Pacient");
		model1.addColumn("Nume Medic");
		model1.addColumn("Investigatii");
		model1.addColumn("Simptome");
		model1.addColumn("Diagnostic");
		model1.addColumn("Istoric");
		model1.addColumn("Recomandari");
		model1.addColumn("Data");
		table2.setModel(model1);
	}
}
