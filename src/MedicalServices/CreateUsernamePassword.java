package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;

import ValidateData.Valid;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class CreateUsernamePassword {

	private JFrame frame;
	private JComboBox<String> CNP;
	private JTextField Username;
	private JTextField Password;
	String StringCNP;
	String StringUsername;
	String StringPassword;
	private JLabel Eroare;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUsernamePassword window = new CreateUsernamePassword();
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
	public CreateUsernamePassword() {
		initialize();
		frame.setVisible(true);
	}
	
	public boolean ValidareDate()
	{
		if (StringCNP.equals("") || StringUsername.equals("") || StringPassword.equals(""))
		{
			Eroare.setVisible(true);
			Eroare.setFont(new Font("TimesRoman", Font.BOLD, 14));
			Eroare.setForeground(Color.RED);
			Eroare.setText("Eroare! Cel putin un camp este Gol");
			return false;
		}
		if (Valid.isValidCNP(StringCNP) == false)
		{
			Eroare.setVisible(true);
			Eroare.setFont(new Font("TimesRoman", Font.BOLD, 14));
			Eroare.setForeground(Color.RED);
			Eroare.setText("CNP-ul nu este valid");
			return false;
		}
		if (StringUsername.length() < 5)
		{
			Eroare.setVisible(true);
			Eroare.setFont(new Font("TimesRoman", Font.BOLD, 14));
			Eroare.setForeground(Color.RED);
			Eroare.setText("Username-ul este prea mic. Minim 5 caractere!");
			return false;
		}
		if (StringPassword.length() < 6)
		{
			Eroare.setVisible(true);
			Eroare.setFont(new Font("TimesRoman", Font.BOLD, 14));
			Eroare.setForeground(Color.RED);
			Eroare.setText("Parola este prea mica. Minim 6 caractere!");
			return false;
		}
		return true;
	}
	
	public void CreateUsernamePasswordAction() 
	{
		int ok;
		if (ValidareDate() == false)
		{
			return;
		}
		try {
			int type = -1;
			if (Login.isAdmin == true)
			{
				type = AdminCreateUser.Type;
			}
			else if (Login.isSuperAdmin == true)
			{
				
				type = SuperAdminCreateUser.TypeSuperAdmin;
			}
			switch(type)
			{
			case 1:Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_medic_admin(?, ?, ?, ?)}");
			break;
			case 2:Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_asistent_medical_admin(?, ?, ?, ?)}");
			break;
			case 3:Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_contabil_admin(?, ?, ?, ?)}");
			break;
			case 4:Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_receptioner_admin(?, ?, ?, ?)}");
			break;
			case 5:Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_inspector_admin(?, ?, ?, ?)}");
			break;
			case 6:Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_administrator(?, ?, ?, ?)}");
			break;
			default:System.out.println("NO ACTION");
			break;
			}
			Conexiune.cstmt.setString(1, StringCNP);
			Conexiune.cstmt.setString(2, StringUsername);
			/*StringPassword = Valid.hashString(StringPassword);*/
			StringPassword = Valid.hashString(StringPassword);
			Conexiune.cstmt.setString(3, StringPassword);
			Conexiune.cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
			boolean hadResult = Conexiune.cstmt.execute();
			ok = Conexiune.cstmt.getInt(4);
			if (hadResult == true && ok != 1)
			{
				Eroare.setVisible(true);
				Eroare.setFont(new Font("TimesRoman", Font.BOLD, 14));
				Eroare.setForeground(Color.green);
				Eroare.setText("Contul a fost creat cu succes!");
			}
			if (ok == 1)
			{
				Eroare.setVisible(true);
				Eroare.setFont(new Font("TimesRoman", Font.BOLD, 14));
				Eroare.setForeground(Color.red);
				Eroare.setText("Username-ul mai exista!");
			}
		}
		catch(SQLException e)
		{
			System.err.println("Nu s-a putut crea cont");
		}
	}
	
	void PopulateJComboBox()
	{
		String user = null;
		int type = -1;
		if (Login.isAdmin == true)
		{
			type = AdminCreateUser.Type;
		}
		else if (Login.isSuperAdmin == true)
		{
			
			type = SuperAdminCreateUser.TypeSuperAdmin;
		}
		switch(type)
		{
		case 1:user = "medic";
		break;
		case 2:user = "asistent_medical";
		break;
		case 3:user = "contabil";
		break;
		case 4:user = "receptioner";
		break;
		case 5:user = "inspector";
		break;
		case 6:user = "administrator";
		break;
		default:System.out.println("NO ACTION");
		break;
		}
		String sql = "select CNP from " + user + " where username is null;";
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
				CNP.addItem(Conexiune.myRs.getString(1));
			}
		}
		catch(SQLException exceptieWow)
		{
			System.out.println("EXCEPTIEE");
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
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(256, 210, 125, 50);
		lblCnp.setFont(new Font("Times New Roman",  Font.PLAIN, 24));
		frame.getContentPane().add(lblCnp);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(256, 273, 154, 16);
		lblUsername.setFont(new Font("Times New Roman",  Font.PLAIN, 24));
		frame.getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("PASSWORD");
		lblNewLabel.setBounds(256, 320, 154, 16);
		lblNewLabel.setFont(new Font("Times New Roman",  Font.PLAIN, 24));
		frame.getContentPane().add(lblNewLabel);
		
		CNP = new JComboBox<String>();
		CNP.setBounds(445, 227, 116, 22);
		frame.getContentPane().add(CNP);
		PopulateJComboBox();
		
		
		Username = new JTextField();
		Username.setBounds(445, 273, 116, 22);
		frame.getContentPane().add(Username);
		Username.setColumns(10);
		
		Password = new JTextField();
		Password.setBounds(445, 314, 116, 22);
		frame.getContentPane().add(Password);
		Password.setColumns(10);
		System.out.println(AdminCreateUser.Type);
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				if (Login.isAdmin == true)
				{
					Admin windowAdmin = new Admin();
					windowAdmin.getFrame().setVisible(true);
				}
				if (Login.isSuperAdmin == true)
				{
					SuperAdmin windowSuper = new SuperAdmin();
					windowSuper.getFrame().setVisible(true);
				}
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Eroare.setVisible(false);
				StringCNP = (String)CNP.getSelectedItem();
				StringUsername = Username.getText();
				StringPassword = Password.getText();
				CreateUsernamePasswordAction();
			}
		});
		btnNewButton.setBounds(448, 378, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		Eroare = new JLabel("");
		Eroare.setBounds(333, 349, 317, 16);
		frame.getContentPane().add(Eroare);
		Eroare.setVisible(false);
		
	}
}
