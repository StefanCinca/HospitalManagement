package MedicalServices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class InfoHR {

	private JFrame frame;
	
	private JTextField Nume;
	private JTextField Prenume;
	private JTextField CNP;
	private JTextField Adresa;
	private JTextField Email;
	private JTextField IBAN;
	private JTextField DataAngajare;
	private JTextField NumarContract;
	private JTextField Telefon;
	private JTextField salariuTf;
	private JTextField numarOreTf;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoHR window = new InfoHR();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ShowInfo()
	{
		String username = Login.usernameLogin;
		String result;
		String sql = "select * from inspector where username = '" + username + "';";
		try {
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next())
		{System.out.println(Conexiune.myRs.getString("nume"));
		result = Conexiune.myRs.getString("nume");
		getNume().setText(result);
		result = Conexiune.myRs.getString("prenume");
		getPrenume().setText(result);
		result = Conexiune.myRs.getString("numar_telefon");
		getTelefon().setText(result);
		result = Conexiune.myRs.getString("iban");
		getIBAN().setText(result);
		result = Conexiune.myRs.getString("adresa");
		getAdresa().setText(result);
		result = Conexiune.myRs.getString("email");
		getEmail().setText(result);
		result = Conexiune.myRs.getString("data_angajarii");
		getDataAngajare().setText(result);
		result = Conexiune.myRs.getString("CNP");
		getCNP().setText(result);
		int numar = Conexiune.myRs.getInt("numar_contract");
		getNumarContract().setText(String.valueOf(numar));
		int salariu = Conexiune.myRs.getInt("salariu");
		salariuTf.setText(String.valueOf(salariu));
		salariuTf.setEditable(false);
		int numarOre = Conexiune.myRs.getInt("numar_ore");
		numarOreTf.setText(String.valueOf(numarOre));
		numarOreTf.setEditable(false);
		
		
		}
		}
		catch (SQLException ea)
		{
			System.err.println("Eroare");
		}
	}

	/**
	 * Create the application.
	 */
	public InfoHR() {
		initialize();
		ShowInfo();
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
		
		JPanel HRPanel = new JPanel();
		frame.getContentPane().add(HRPanel, BorderLayout.WEST);
		HRPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		HRPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		GridBagLayout gbl_HRPanel = new GridBagLayout();
		gbl_HRPanel.columnWeights = new double[]{1.0};
		HRPanel.setLayout(gbl_HRPanel);
		JLabel HRLabel = new JLabel("HR Manager");
		HRLabel.setIcon(null);
		HRLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HRLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		HRPanel.add(HRLabel, gbc_Welcome);
		
		JPanel panelDreapta = new JPanel();
		panelDreapta.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel InformationPanel = new JLabel("Profile Information");
		InformationPanel.setBackground(Color.DARK_GRAY);
		InformationPanel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panelDreapta.add(InformationPanel);
		frame.getContentPane().add(panelDreapta, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNume.setBounds(93, 85, 56, 16);
		panel_1.add(lblNume);
		
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(93, 116, 56, 16);
		panel_1.add(lblPrenume);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(93, 145, 56, 16);
		panel_1.add(lblCnp);
		
		JLabel lblNewLabel = new JLabel("Adresa");
		lblNewLabel.setBounds(93, 174, 56, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-Mail");
		lblNewLabel_1.setBounds(93, 203, 56, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblIban = new JLabel("IBAN");
		lblIban.setBounds(93, 232, 56, 16);
		panel_1.add(lblIban);
		
		JLabel lblNewLabel_2 = new JLabel("Data Angajare");
		lblNewLabel_2.setBounds(93, 265, 82, 16);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Numar Contract");
		lblNewLabel_3.setBounds(93, 299, 90, 16);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(93, 334, 56, 16);
		panel_1.add(lblTelefon);
		
		Nume = new JTextField();
		Nume.setEditable(false);
		Nume.setBounds(269, 83, 196, 22);
		panel_1.add(Nume);
		Nume.setColumns(10);
		
		Prenume = new JTextField();
		Prenume.setEditable(false);
		Prenume.setBounds(269, 113, 196, 22);
		panel_1.add(Prenume);
		Prenume.setColumns(10);
		
		CNP = new JTextField();
		CNP.setEditable(false);
		CNP.setBounds(269, 142, 196, 22);
		panel_1.add(CNP);
		CNP.setColumns(10);
		
		Adresa = new JTextField();
		Adresa.setEditable(false);
		Adresa.setBounds(269, 171, 196, 22);
		panel_1.add(Adresa);
		Adresa.setColumns(10);
		
		Email = new JTextField();
		Email.setEditable(false);
		Email.setBounds(269, 200, 196, 22);
		panel_1.add(Email);
		Email.setColumns(10);
		
		IBAN = new JTextField();
		IBAN.setEditable(false);
		IBAN.setBounds(269, 229, 196, 22);
		panel_1.add(IBAN);
		IBAN.setColumns(10);
		
		DataAngajare = new JTextField();
		DataAngajare.setEditable(false);
		DataAngajare.setBounds(269, 262, 196, 22);
		panel_1.add(DataAngajare);
		DataAngajare.setColumns(10);
		
		NumarContract = new JTextField();
		NumarContract.setEditable(false);
		NumarContract.setBounds(269, 296, 196, 22);
		panel_1.add(NumarContract);
		NumarContract.setColumns(10);
		
		Telefon = new JTextField();
		Telefon.setEditable(false);
		Telefon.setBounds(269, 331, 196, 22);
		panel_1.add(Telefon);
		Telefon.setColumns(10);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Hr_Manager window = new Hr_Manager();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(12, 438, 95, 48);
		panel_1.add(Back);
		
		salariuTf = new JTextField();
		salariuTf.setBounds(269, 366, 196, 22);
		panel_1.add(salariuTf);
		salariuTf.setColumns(10);
		
		JLabel lblSalariu = new JLabel("Salariu");
		lblSalariu.setBounds(93, 369, 56, 16);
		panel_1.add(lblSalariu);
		
		JLabel lblNumarOre = new JLabel("Numar ore");
		lblNumarOre.setBounds(93, 404, 71, 16);
		panel_1.add(lblNumarOre);
		
		numarOreTf = new JTextField();
		numarOreTf.setBounds(269, 401, 196, 22);
		panel_1.add(numarOreTf);
		numarOreTf.setColumns(10);
	}

	public JFrame getFrame() {
		return frame;
	}


	public JTextField getNume() {
		return Nume;
	}


	public JTextField getPrenume() {
		return Prenume;
	}


	public JTextField getCNP() {
		return CNP;
	}


	public JTextField getAdresa() {
		return Adresa;
	}


	public JTextField getEmail() {
		return Email;
	}


	public JTextField getIBAN() {
		return IBAN;
	}


	public JTextField getDataAngajare() {
		return DataAngajare;
	}


	public JTextField getNumarContract() {
		return NumarContract;
	}


	public JTextField getTelefon() {
		return Telefon;
	}
}
