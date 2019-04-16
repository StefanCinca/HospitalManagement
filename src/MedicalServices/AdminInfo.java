package MedicalServices;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminInfo {

	//textbox-urile in care afisam informatia 
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
	
	//panel din mijloc
	private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInfo window = new AdminInfo();
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
		String sql = "initialised";
		if (Login.isAdmin == true && Login.isSuperAdmin == false)
		{sql = "select * from administrator where username like '%" + username + "%';";}
		else if (Login.isSuperAdmin == true && Login.isAdmin == false)
		{
			sql = "select * from super_administrator where username like '%" + username + "%';";
		}
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
	public AdminInfo() {
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
		
		JPanel AdminPanel = new JPanel();
		frame.getContentPane().add(AdminPanel, BorderLayout.WEST);
		AdminPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AdminPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		GridBagLayout gbl_AdminPanel = new GridBagLayout();
		gbl_AdminPanel.columnWeights = new double[]{1.0};
		AdminPanel.setLayout(gbl_AdminPanel);
		JLabel AdminLabel = new JLabel("Admin");
		AdminLabel.setIcon(null);
		AdminLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AdminLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		GridBagConstraints gbc_Welcome = new GridBagConstraints();
		gbc_Welcome.insets = new Insets(0, 0, 5, 0);
		gbc_Welcome.gridx = 0;
		gbc_Welcome.gridy = 0;
		AdminPanel.add(AdminLabel, gbc_Welcome);
		
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
		Nume.setBounds(269, 83, 201, 22);
		panel_1.add(Nume);
		Nume.setColumns(10);
		
		Prenume = new JTextField();
		Prenume.setEditable(false);
		Prenume.setBounds(269, 113, 201, 22);
		panel_1.add(Prenume);
		Prenume.setColumns(10);
		
		CNP = new JTextField();
		CNP.setEditable(false);
		CNP.setBounds(269, 142, 201, 22);
		panel_1.add(CNP);
		CNP.setColumns(10);
		
		Adresa = new JTextField();
		Adresa.setEditable(false);
		Adresa.setBounds(269, 171, 201, 22);
		panel_1.add(Adresa);
		Adresa.setColumns(10);
		
		Email = new JTextField();
		Email.setEditable(false);
		Email.setBounds(269, 200, 201, 22);
		panel_1.add(Email);
		Email.setColumns(10);
		
		IBAN = new JTextField();
		IBAN.setEditable(false);
		IBAN.setBounds(269, 229, 201, 22);
		panel_1.add(IBAN);
		IBAN.setColumns(10);
		
		DataAngajare = new JTextField();
		DataAngajare.setEditable(false);
		DataAngajare.setBounds(269, 262, 201, 22);
		panel_1.add(DataAngajare);
		DataAngajare.setColumns(10);
		
		NumarContract = new JTextField();
		NumarContract.setEditable(false);
		NumarContract.setBounds(269, 296, 201, 22);
		panel_1.add(NumarContract);
		NumarContract.setColumns(10);
		
		Telefon = new JTextField();
		Telefon.setEditable(false);
		Telefon.setBounds(269, 331, 201, 22);
		panel_1.add(Telefon);
		Telefon.setColumns(10);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Admin window = new Admin();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(12, 438, 95, 48);
		panel_1.add(Back);
		
		
		
		
		
		
		
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
