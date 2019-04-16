package MedicalServices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class MedicalReport {

	private JFrame frame;
	DefaultTableModel model;
	JTable table;
	private JLabel lblNume;
	private JLabel lblPrenume;
	private JTextField nume;
	private JTextField prenume;
	private String numePacient;
	private String prenumePacient;
	private String StringPacientCNP;
	private String StringAsistentCNP;
	private JTextField ValoareNumericaTf;
	private JTextField NumeAnalizaTf;
	private JTextField idRaportTf;
	private JTextField idRaportTf2;
	private JTextField ZiuaTf;
	private JTextField OraTf;
	private int idRaport;
	private int ValoareNumerica;
	private boolean ValoareBinara;
	private String NumeAnaliza;
	private String data;
	private String ora;
	private int idRaport2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicalReport window = new MedicalReport();
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
	public MedicalReport() {
		initialize();
		frame.setVisible(true);
	}
	
	public void GetAsistentCNP()
	{
		String loginUsername = Login.usernameLogin;
		try
		{
			String sql = "select CNP from asistent_medical where username = '" + loginUsername + "';";
			Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
			if (!Conexiune.myRs3.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs3.previous();
			while (Conexiune.myRs3.next()) {
				StringAsistentCNP = Conexiune.myRs3.getString(1);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Nu s-a putut lua CNP-ul asistentului");
		}
	}
	
	public void ShowReports()
	{
		int idRaport = -1;
		int idAnalize = -1;
		int ValoareNumerica = -1;
		boolean ValoareBinara = false;
		String denumireAnalize = null;
		try
		{
			String sql = "select CNP from pacient where nume = '" + numePacient + "' and prenume = '" + prenumePacient+"';";
			Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
			if (!Conexiune.myRs3.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs3.previous();
			while (Conexiune.myRs3.next()) {
				StringPacientCNP = Conexiune.myRs3.getString(1);
			}
			
		}
		catch(SQLException e2)
		{
			System.out.println("Nu a mers Afisarea rapoartelor");
		}
		
		try
		{
			String ziuaValidarii = null;
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Arata_rapoarte_medicale(?, ?,?)};");
			Conexiune.cstmt.setString(1, numePacient);
			Conexiune.cstmt.setString(2, prenumePacient);
			Conexiune.cstmt.setString(3, StringAsistentCNP);
			
			boolean hadResult = Conexiune.cstmt.execute();
			while (hadResult) {
				Conexiune.myRs = Conexiune.cstmt.getResultSet();
				if (!Conexiune.myRs.next()) {
					System.out.println("NU EXISTA medici");
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next()) {
					idRaport = Conexiune.myRs.getInt(1);
					idAnalize = Conexiune.myRs.getInt(2);
					ValoareNumerica = Conexiune.myRs.getInt(3);
					ValoareBinara = Conexiune.myRs.getBoolean(4);
					String sql5 = "Select nume from servicii_medicale where idServicii_medicale =" + idAnalize;
					Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql5);
					Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
					if (!Conexiune.myRs3.next()) {
						System.out.println("NU EXISTA");
					}
					Conexiune.myRs3.previous();
					while (Conexiune.myRs3.next())
					{
						denumireAnalize = Conexiune.myRs3.getString(1);
					}
					String sql6 = "Select ziua from rapoarte_medicale_asistent where id = " + idRaport +";";
					Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql6);
					Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
					if (!Conexiune.myRs3.next()) {
						System.out.println("NU EXISTA");
					}
					Conexiune.myRs3.previous();
					while (Conexiune.myRs3.next())
					{
						ziuaValidarii = Conexiune.myRs3.getString(1);
					}
					String[] Vector = new String[5];
					Vector[0] = String.valueOf(idRaport);
					Vector[1] = denumireAnalize;
					Vector[2] = String.valueOf(ValoareNumerica);
					Vector[3] = String.valueOf(ValoareBinara);
					if (ziuaValidarii == null)
					{
						Vector[4] = "Nu este Validat";
					}
					else
					{
						Vector[4] = ziuaValidarii;
					}
					model.addRow(Vector);
				}
				hadResult = Conexiune.cstmt.getMoreResults();
			}
		}
		catch(SQLException e3)
		{
			System.out.println("Nu a mers afisarea id-urilor rapoartelor");
		}	
	}
	
	public void ModifyReport()
	{
		try
		{
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Update_raport_medical_asistent(?, ?, ?, ?)};");
			Conexiune.cstmt.setInt(1, idRaport);
			Conexiune.cstmt.setInt(2, ValoareNumerica);
			Conexiune.cstmt.setBoolean(3, ValoareBinara);
			Conexiune.cstmt.setString(4, NumeAnaliza);
			Conexiune.cstmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Nu a mers modificarea raportului");
		}
	}
	
	public void Validate()
	{
		try
		{
			/*int validareOk = 0;*/
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Valideaza_Raport(?, ?, ?)};");
			Conexiune.cstmt.setInt(1, idRaport2);
			Conexiune.cstmt.setString(2, data);
			Conexiune.cstmt.setString(3,  ora);
			/*Conexiune.cstmt.registerOutParameter(4, java.sql.Types.INTEGER);*/
			Conexiune.cstmt.execute();
			/*validareOk = Conexiune.cstmt.getInt(4);*/
			/*if (validareOk == 0)
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Raportul medical nu a fost completat in intregime", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Raportul medical a fost validat", "Succes", JOptionPane.INFORMATION_MESSAGE);
				return;
			}*/
			
		}
		catch(SQLException e3)
		{
			
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
		
		model = new DefaultTableModel();
		frame.getContentPane().setLayout(null);
		table = new JTable();
		table.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 169, 484, 156);
		frame.getContentPane().add(scrollPane);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		lblNume = new JLabel("Nume");
		lblNume.setBounds(315, 34, 56, 16);
		frame.getContentPane().add(lblNume);
		
		lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(315, 71, 56, 16);
		frame.getContentPane().add(lblPrenume);
		
		nume = new JTextField();
		nume.setBounds(415, 31, 116, 22);
		frame.getContentPane().add(nume);
		nume.setColumns(10);
		
		prenume = new JTextField();
		prenume.setBounds(415, 68, 116, 22);
		frame.getContentPane().add(prenume);
		prenume.setColumns(10);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				model.setRowCount(0);
				numePacient = nume.getText();
				prenumePacient = prenume.getText();
				ShowReports();
				GetAsistentCNP();
				System.out.println(StringAsistentCNP);
				System.out.println(StringPacientCNP);
			}
		});
		btnNewButton.setBounds(425, 103, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("idRaport");
		lblNewLabel.setBounds(34, 399, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume Analiza");
		lblNewLabel_1.setBounds(34, 428, 89, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Valoare Numerica");
		lblNewLabel_2.setBounds(34, 457, 105, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblValoareBinara = new JLabel("Valoare Binara");
		lblValoareBinara.setBounds(34, 486, 105, 16);
		frame.getContentPane().add(lblValoareBinara);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(164, 483, 89, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("True");
		comboBox.addItem("False");
		
		ValoareNumericaTf = new JTextField();
		ValoareNumericaTf.setBounds(164, 454, 89, 22);
		frame.getContentPane().add(ValoareNumericaTf);
		ValoareNumericaTf.setColumns(10);
		
		NumeAnalizaTf = new JTextField();
		NumeAnalizaTf.setBounds(164, 425, 89, 22);
		frame.getContentPane().add(NumeAnalizaTf);
		NumeAnalizaTf.setColumns(10);
		
		idRaportTf = new JTextField();
		idRaportTf.setBounds(164, 396, 89, 22);
		frame.getContentPane().add(idRaportTf);
		idRaportTf.setColumns(10);
		
		JButton btnModify = new JButton("Modify");
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idRaport = Integer.valueOf(idRaportTf.getText());
				ValoareNumerica = Integer.valueOf(ValoareNumericaTf.getText());
				ValoareBinara = Boolean.valueOf((String)comboBox.getSelectedItem());
				NumeAnaliza = NumeAnalizaTf.getText();
				ModifyReport();
			}
		});
		btnModify.setBounds(164, 515, 97, 25);
		frame.getContentPane().add(btnModify);
		
		idRaportTf2 = new JTextField();
		idRaportTf2.setBounds(598, 396, 116, 22);
		frame.getContentPane().add(idRaportTf2);
		idRaportTf2.setColumns(10);
		
		JLabel lblRaport2 = new JLabel("Id Raport");
		lblRaport2.setBounds(496, 399, 56, 16);
		frame.getContentPane().add(lblRaport2);
		
		ZiuaTf = new JTextField();
		ZiuaTf.setBounds(598, 425, 116, 22);
		frame.getContentPane().add(ZiuaTf);
		ZiuaTf.setColumns(10);
		
		JLabel lblZiua = new JLabel("Ziua");
		lblZiua.setBounds(496, 428, 56, 16);
		frame.getContentPane().add(lblZiua);
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(496, 457, 56, 16);
		frame.getContentPane().add(lblOra);
		
		OraTf = new JTextField();
		OraTf.setBounds(598, 454, 116, 22);
		frame.getContentPane().add(OraTf);
		OraTf.setColumns(10);
		
		JButton btnValidate = new JButton("Validate");
		btnValidate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				data = ZiuaTf.getText();
				ora = OraTf.getText();
				idRaport2 = Integer.parseInt(idRaportTf2.getText());
				Validate();
			}
		});
		btnValidate.setBounds(608, 482, 97, 25);
		frame.getContentPane().add(btnValidate);
		model.addColumn("RaportId");
		model.addColumn("Analize");
		model.addColumn("Valoare numerica");
		model.addColumn("Valoare binara");
		model.addColumn("Data validarii");
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Asistent window = new Asistent();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(177, 13, 95, 48);
		frame.getContentPane().add(Back);
	}
}
