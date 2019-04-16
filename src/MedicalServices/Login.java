package MedicalServices;
import java.awt.Color;



import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import ValidateData.Valid;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class Login {

	private JFrame frame;
	private JTextField username;
	private JPanel panel;
	public static boolean isAdmin = false;
	public static boolean isSuperAdmin = false;
	public static String usernameLogin;
	public JPanel getPanel() {
		return panel;
	}

	public JTextField getUsername() {
		return username;
	}

	public JPasswordField getPassword() {
		return password;
	}

	private JPasswordField password;
	private JTextField txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login(3);
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
	public Login(int type) {
		initialize(type);
	}


	public void LoginButton(int type) throws SQLException
	{
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				String usernameString = username.getText();
				char[] passwordChar = new char[20];
				/*System.out.println(usernameString);*/
			    passwordChar = password.getPassword();
			    String word = username.getText();
			    usernameLogin = word;
			    String result;
			    String typeOfUser = null;
			    switch(type)
			    {
			    case MainFrame.DOCTORTYPE:typeOfUser = "medic";
				break;
				case MainFrame.ACCOUNTANTTYPE:typeOfUser = "contabil";
				break;
				case MainFrame.ADMINTYPE:typeOfUser = "administrator";
				break;
				case MainFrame.SUPERADMINTYPE:typeOfUser = "super_administrator";
				break;
				case MainFrame.NURSETYPE: typeOfUser = "asistent_medical";
				break;
				case MainFrame.HRMANAGERTYPE: typeOfUser = "inspector";
				break;
				default:
					typeOfUser = "Receptioner";
			    }
				String sql = "select * from " + typeOfUser + " where username like '%" + word + "%';";
				try {
				Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
				Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
				if (!Conexiune.myRs.next())
				{
						final JPanel panel = new JPanel();
						 JOptionPane.showMessageDialog(panel, "Nu exista username sau parola!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next())
				{System.out.println(Conexiune.myRs.getString("parola"));
				result = Conexiune.myRs.getString("parola");
				String passwordString = new String(passwordChar);
				passwordString = Valid.hashString(passwordString);
				if (!(passwordString.equals(result)))
				{
					final JPanel panel = new JPanel();
					 JOptionPane.showMessageDialog(panel, "Nu exista username sau parola!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (passwordString.equals(result))
				{
					switch(type)
				    {
				    case MainFrame.DOCTORTYPE:  Medic windowM = new Medic();isAdmin = false;isSuperAdmin = false;
					break;
					case MainFrame.ACCOUNTANTTYPE:Accountant windowAccountant = new Accountant(); isAdmin = false;isSuperAdmin = false;
					break;
					case MainFrame.ADMINTYPE:Admin windowAdmin = new Admin(); isAdmin = true; isSuperAdmin = false;
					break;
					case MainFrame.SUPERADMINTYPE:SuperAdmin windowSuper = new SuperAdmin(); isSuperAdmin = true; isAdmin = false;
					break;
					case MainFrame.NURSETYPE: Asistent window = new Asistent(); isAdmin = false;isSuperAdmin = false;
					break;
					case MainFrame.HRMANAGERTYPE: Hr_Manager windowHR = new Hr_Manager(); isAdmin = false;isSuperAdmin = false;
					break;
					default:
						Receptionist windowReceptionist = new Receptionist();isAdmin = false;isSuperAdmin = false;
				    }
					frame.setVisible(false);
				}
				}
				}
				catch (SQLException ea)
				{
					System.err.println("Eroare");
				}
			}
		});
		btnLogin.setBounds(103, 331, 97, 25);
		panel.add(btnLogin);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int Type) {
		frame = new JFrame();
		frame.setBounds(0, 0, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getBounds().getWidth();
		 panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, UIManager.getColor("inactiveCaptionText"), null));
		panel.setBounds(300,50, 300, 450);
		panel.setBackground(UIManager.getColor("info"));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		username = new JTextField();
		username.setBounds(84, 227, 136, 22);
		username.setForeground(Color.LIGHT_GRAY);
		username.setText("Username");
		
		panel.add(username);
		
		username.setColumns(10);
		
		
		password = new JPasswordField();
		password.setBounds(84, 276, 136, 22);
		password.setForeground(Color.LIGHT_GRAY);
		password.setEchoChar((char)0);
		password.setText("Password");
		/*password.BackGround()*/
		panel.add(password);
		
		txt = new JTextField();
		txt.setEditable(false);
		txt.setBackground(UIManager.getColor("info"));
		txt.setHorizontalAlignment(SwingConstants.CENTER);
		switch (Type) {
		case MainFrame.DOCTORTYPE:txt.setText("Doctor Login");
		break;
		case MainFrame.ACCOUNTANTTYPE:txt.setText("Accountant Login");
		break;
		case MainFrame.ADMINTYPE:txt.setText("Admin Login");
		break;
		case MainFrame.SUPERADMINTYPE:txt.setText("SuperAdmin Login");
		break;
		case MainFrame.NURSETYPE:txt.setText("Nurse Login");
		break;
		case MainFrame.HRMANAGERTYPE:txt.setText("HR Manager Login");
		break;
		default:
			txt.setText("Receptionist Login");
		}
		txt.setFont(new Font("Times New Roman", Font.BOLD, 22));
		txt.setBounds(37, 108, 228, 67);
		txt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txt);
		txt.setColumns(10);
		
		try {
			LoginButton(Type);
		}
		catch(SQLException e)
		{
			;
		}
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				MainFrame window = new MainFrame();
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);
		
	}

	public JFrame getFrame() {
		return frame;
	}
}
