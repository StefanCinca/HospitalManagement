package MedicalServices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class AddReportMedic {

	private JFrame frame;
	private JTextField numePacientTf;
	private JTextField numeMedicTf;
	private JTextField numeAsistentTf;
	private JTextField numeRecomandareTf;
	private JTextField simptomeTf;
	private JTextField recomandariTf;
	private JTextField diagnosticTf;
	private JTextField dataTf;
	private String StringNumePacient;
	private String StringNumeMedic;
	private String StringNumeAsistent;
	private String StringNumeMedicRecomandare;
	private String StringRecomandare;
	private String StringDiagnostic;
	private String StringSimptome;
	private String StringInvestigatii;
	private String StringData;
	private String StringParafa;
	private String CodParafa;
	private String StringIstoric;
	private JTextField istoricTf;
	private JTextField tfCautaNume;
	private JComboBox rapoarteBox;
	private JTextField cautaPrenumeTf;
	private String cautaNume;
	private String cautaPrenume;
	private int idRaportMedical;
	private JTextArea investigatieTa;
	private JButton btnAddRaport;
	private JComboBox comboBox;
	private String numeMedicFct;
	private String prenumeMedicFct;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddReportMedic window = new AddReportMedic();
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
	public AddReportMedic() {
		initialize();
		frame.setVisible(true);
	}
	
	public void getCodParafa()
	{
		
			
			String username = Login.usernameLogin;
			try
			{
				String sql = "select cod_parafa from medic where username = '" + username + "';";
				Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
				Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
				if (!Conexiune.myRs.next()) {
					System.out.println("NU EXISTA specializarea");
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next()) {
					CodParafa = Conexiune.myRs.getString(1);
				}
			}
			catch(SQLException e)
			{	
				System.out.println("Nu s-a putut obtine Codul Parafa al medicului");
			}
		
	}
	
	public void getInfo()
	{
		String StringCNP = null;
		String username = Login.usernameLogin;
		String result;
		String sql = "select nume, prenume from medic where username = '" + username + "';";
		try {
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next())
		{
			numeMedicFct = Conexiune.myRs.getString(1);
			prenumeMedicFct = Conexiune.myRs.getString(2);
		}
		}
		catch(SQLException exceptie)
		{
			System.out.println("Nu s-au putut lua numele si prenumele medicului");
		}
	}
	
	public void ArataRapoarte()
	{
		String cautaNumeFull = cautaNume + " " + cautaPrenume;
		String sql = "select idRapoarte_medicale from rapoarte_medicale where numePacient ='" + cautaNumeFull + "';";
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
				rapoarteBox.addItem(Conexiune.myRs.getString(1));
			}
		}
		catch(SQLException exceptiee)
		{
			System.out.println("Nu s-au putut identifica Rapoartele_medicale");
		}
	}

	public void ArataRaportul()
	{
		String numeMedicFullFct = numeMedicFct + " " + prenumeMedicFct;
		String sql = "select * from rapoarte_medicale where idRapoarte_medicale =" + idRaportMedical +" and numeMedic ='" + numeMedicFullFct +"';";
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
				numePacientTf.setText(Conexiune.myRs.getString("numePacient"));
				numeMedicTf.setText(Conexiune.myRs.getString("numeMedic"));
				String numeAsistent = Conexiune.myRs.getString("numeAsistent");
				if (numeAsistent != null)
				numeAsistentTf.setText(Conexiune.myRs.getString("numeAsistent"));
				String numeMedicRecomandat2 = Conexiune.myRs.getString("numeMedicRecomandat");
				if (numeMedicRecomandat2 != null)
				numeRecomandareTf.setText(Conexiune.myRs.getString("numeMedicRecomandat"));
				diagnosticTf.setText(Conexiune.myRs.getString("diagnostic"));
				dataTf.setText(Conexiune.myRs.getString("ziua"));
				simptomeTf.setText(Conexiune.myRs.getString("simptome"));
				istoricTf.setText(Conexiune.myRs.getString("istoric"));
				investigatieTa.setText(Conexiune.myRs.getString("Investigatii"));
				recomandariTf.setText(Conexiune.myRs.getString("recomandari"));
				String parafa = Conexiune.myRs.getString("parafa");
				if (!parafa.equals("nu este parafat"))
				{
					numePacientTf.setEditable(false);
					numeAsistentTf.setEditable(false);
					numeRecomandareTf.setEditable(false);
					diagnosticTf.setEditable(false);
					dataTf.setEditable(false);
					simptomeTf.setEditable(false);
					istoricTf.setEditable(false);
					investigatieTa.setEditable(false);
					recomandariTf.setEditable(false);
					btnAddRaport.setVisible(false);
					comboBox.setSelectedItem(parafa);
					comboBox.setEditable(false);
				}
				else
				{
					numePacientTf.setEditable(true);
					numeAsistentTf.setEditable(true);
					numeRecomandareTf.setEditable(true);
					diagnosticTf.setEditable(true);
					dataTf.setEditable(true);
					simptomeTf.setEditable(true);
					istoricTf.setEditable(true);
					investigatieTa.setEditable(true);
					recomandariTf.setEditable(true);
					btnAddRaport.setVisible(true);
					comboBox.setSelectedItem("nu este parafat");
					comboBox.setEditable(true);
				}
		}
		}
		catch(SQLException exceptiee)
		{
			System.out.println("Nu s-au putut identifica Rapoartele_medicale");
		}
		}
	
	public void AddReport()
	{
		try
		{
			String sql = "{call adaugare_raport_medical_medic(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)};";
			Conexiune.cstmt = Conexiune.con.prepareCall(sql);
			Conexiune.cstmt.setString(1, StringNumePacient);
			Conexiune.cstmt.setString(2, StringNumeMedic);
			Conexiune.cstmt.setString(3, StringNumeAsistent);
			Conexiune.cstmt.setString(4, StringNumeMedicRecomandare);
			Conexiune.cstmt.setString(5, StringSimptome);
			Conexiune.cstmt.setString(6, StringIstoric);
			Conexiune.cstmt.setString(7, StringDiagnostic);
			Conexiune.cstmt.setString(8, StringInvestigatii);
			Conexiune.cstmt.setString(9, StringRecomandare);
			Conexiune.cstmt.setString(10, StringData);
			Conexiune.cstmt.setString(11, StringParafa);
			Conexiune.cstmt.execute();
		}
		catch (SQLException exceptie)
		{
			System.out.println("Nu a mers introducerea raportului medical");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		getInfo();
		getCodParafa();
		
		JLabel lblPacient = new JLabel("Nume Pacient");
		lblPacient.setBounds(442, 116, 153, 16);
		frame.getContentPane().add(lblPacient);
		
		JLabel lblMedic = new JLabel("Nume Medic");
		lblMedic.setBounds(442, 146, 153, 16);
		frame.getContentPane().add(lblMedic);
		
		JLabel lblAsistent = new JLabel("Nume Asistent");
		lblAsistent.setBounds(442, 175, 160, 16);
		frame.getContentPane().add(lblAsistent);
		
		JLabel lblNewLabel = new JLabel("Nume Recomandare Medic");
		lblNewLabel.setBounds(442, 204, 160, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSimptome = new JLabel("Simptome");
		lblSimptome.setBounds(441, 233, 154, 16);
		frame.getContentPane().add(lblSimptome);
		
		JLabel lblRecomandare = new JLabel("Recomandari");
		lblRecomandare.setBounds(441, 262, 100, 16);
		frame.getContentPane().add(lblRecomandare);
		
		JLabel lblDiagnostic = new JLabel("Diagnostic");
		lblDiagnostic.setBounds(442, 294, 160, 16);
		frame.getContentPane().add(lblDiagnostic);
		
		JLabel lblInvestigatii = new JLabel("Investigatii");
		lblInvestigatii.setBounds(442, 321, 153, 16);
		frame.getContentPane().add(lblInvestigatii);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(442, 544, 56, 16);
		frame.getContentPane().add(lblData);
		
		JLabel lblParafa = new JLabel("Parafa");
		lblParafa.setBounds(442, 573, 56, 16);
		frame.getContentPane().add(lblParafa);
		
		numePacientTf = new JTextField();
		numePacientTf.setBounds(612, 113, 300, 22);
		frame.getContentPane().add(numePacientTf);
		numePacientTf.setColumns(10);
		
		numeMedicTf = new JTextField();
		numeMedicTf.setBounds(612, 143, 300, 22);
		numeMedicTf.setEditable(false);
		numeMedicTf.setText(numeMedicFct + " " + prenumeMedicFct);
		frame.getContentPane().add(numeMedicTf);
		numeMedicTf.setColumns(10);
		
		numeAsistentTf = new JTextField();
		numeAsistentTf.setBounds(612, 172, 300, 22);
		frame.getContentPane().add(numeAsistentTf);
		numeAsistentTf.setColumns(10);
		
		numeRecomandareTf = new JTextField();
		numeRecomandareTf.setBounds(612, 201, 300, 22);
		frame.getContentPane().add(numeRecomandareTf);
		numeRecomandareTf.setColumns(10);
		
		simptomeTf = new JTextField();
		simptomeTf.setBounds(612, 230, 300, 22);
		frame.getContentPane().add(simptomeTf);
		simptomeTf.setColumns(10);
		
		recomandariTf = new JTextField();
		recomandariTf.setBounds(612, 259, 300, 22);
		frame.getContentPane().add(recomandariTf);
		recomandariTf.setColumns(10);
		
		diagnosticTf = new JTextField();
		diagnosticTf.setBounds(612, 291, 300, 22);
		frame.getContentPane().add(diagnosticTf);
		diagnosticTf.setColumns(10);
		
		investigatieTa = new JTextArea();
		investigatieTa.setBounds(612, 330, 300, 147);
		frame.getContentPane().add(investigatieTa);
		
		dataTf = new JTextField();
		dataTf.setBounds(612, 541, 116, 22);
		frame.getContentPane().add(dataTf);
		dataTf.setColumns(10);
		
	
		comboBox = new JComboBox();
		comboBox.setBounds(612, 570, 116, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("nu este parafat");
		comboBox.addItem(CodParafa);
		
		btnAddRaport = new JButton("Modifica");
		btnAddRaport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringNumePacient = numePacientTf.getText();
				StringNumeMedic = numeMedicTf.getText();
				StringNumeAsistent = numeAsistentTf.getText();
				StringNumeMedicRecomandare = numeRecomandareTf.getText();
				StringSimptome = simptomeTf.getText();
				StringDiagnostic = diagnosticTf.getText();
				StringData = dataTf.getText();
				StringInvestigatii = investigatieTa.getText();
				StringRecomandare = recomandariTf.getText();
				StringIstoric = istoricTf.getText();
				StringParafa = (String)comboBox.getSelectedItem();
				if (StringParafa.equals("nu este parafata"))
				{
					StringParafa = null;
				}
				AddReport();
				
			}
		});
		btnAddRaport.setBounds(631, 616, 97, 25);
		frame.getContentPane().add(btnAddRaport);
		
		JLabel lblIstoric = new JLabel("Istoric");
		lblIstoric.setBounds(442, 507, 56, 16);
		frame.getContentPane().add(lblIstoric);
		
		istoricTf = new JTextField();
		istoricTf.setBounds(612, 504, 116, 22);
		frame.getContentPane().add(istoricTf);
		istoricTf.setColumns(10);
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Medic window = new Medic();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(12, 13, 95, 48);
		frame.getContentPane().add(Back);
		
		JLabel lblNumePacient = new JLabel("Nume Pacient");
		lblNumePacient.setBounds(12, 116, 97, 16);
		frame.getContentPane().add(lblNumePacient);
		
		tfCautaNume = new JTextField();
		tfCautaNume.setBounds(131, 113, 116, 22);
		frame.getContentPane().add(tfCautaNume);
		tfCautaNume.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rapoarteBox.removeAllItems();
				cautaNume = tfCautaNume.getText();
				cautaPrenume = cautaPrenumeTf.getText();
				ArataRapoarte();
			}
		});
		btnSearch.setBounds(141, 171, 97, 25);
		frame.getContentPane().add(btnSearch);
		
		rapoarteBox = new JComboBox();
		rapoarteBox.setBounds(131, 201, 116, 22);
		frame.getContentPane().add(rapoarteBox);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				idRaportMedical = Integer.valueOf((String)rapoarteBox.getSelectedItem());
				ArataRaportul();
			}
		});
		btnSelect.setBounds(141, 246, 97, 25);
		frame.getContentPane().add(btnSelect);
		
		JLabel lblCautaPrenume = new JLabel("Prenume Pacient");
		lblCautaPrenume.setBounds(12, 146, 110, 16);
		frame.getContentPane().add(lblCautaPrenume);
		
		cautaPrenumeTf = new JTextField();
		cautaPrenumeTf.setBounds(131, 143, 116, 22);
		frame.getContentPane().add(cautaPrenumeTf);
		cautaPrenumeTf.setColumns(10);
		
	}
}
