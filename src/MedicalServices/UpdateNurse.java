package MedicalServices;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpdateNurse {

	private JFrame frame;
	private JTextField[] nurseTextField;
	private static final int NRTEXTFIELD = 13;
	private JTextField CNP;
	private String StringCNP;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateNurse window = new UpdateNurse();
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
	public UpdateNurse() {
		initialize();
		frame.setVisible(true);
	}
	
	public void GetInfo()
	{
		String result;
		try 
		{
			String sql = "select * from asistent_medical where CNP like '%" + StringCNP + "%';";
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
			nurseTextField[0].setText(result);
			result = Conexiune.myRs.getString("prenume");
			nurseTextField[1].setText(result);
			result = Conexiune.myRs.getString("numar_telefon");
			nurseTextField[4].setText(result);
			result = Conexiune.myRs.getString("iban");
			nurseTextField[7].setText(result);
			result = Conexiune.myRs.getString("adresa");
			nurseTextField[2].setText(result);
			result = Conexiune.myRs.getString("email");
			nurseTextField[3].setText(result);
			result = Conexiune.myRs.getString("data_angajarii");
			nurseTextField[5].setText(result);
			int numar = Conexiune.myRs.getInt("numar_contract");
			nurseTextField[6].setText(String.valueOf(numar));
			int ore = Conexiune.myRs.getInt("numar_ore");
			nurseTextField[10].setText(String.valueOf(ore));
			int salariu = Conexiune.myRs.getInt("salariu");
			nurseTextField[9].setText(String.valueOf(salariu));
			String username = Conexiune.myRs.getString("username");
			nurseTextField[11].setText(username);
			String parola = Conexiune.myRs.getString("parola");
			nurseTextField[12].setText(parola);
			String Tip = Conexiune.myRs.getString("tipul");
			nurseTextField[8].setText(Tip);
		}
		}
		catch(SQLException ea)
		{
			System.err.println("Nu a mers luarea informatiilor");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(305, 33, 125, 50);
		lblCnp.setFont(new Font("Times New Roman",  Font.PLAIN, 24));
		frame.getContentPane().add(lblCnp);
		
		CNP = new JTextField();
		CNP.setBounds(505, 50, 161, 22);
		frame.getContentPane().add(CNP);
		CNP.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringCNP = CNP.getText();
				GetInfo();
				
			}
		});
		btnNewButton.setBounds(704, 49, 100, 25);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		nurseTextField = new JTextField[NRTEXTFIELD];
		JLabel lblNewLabel = new JLabel("Nume");
		lblNewLabel.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(305, 95, 77, 16);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Prenume");
		lblNewLabel_1.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(305, 124, 77, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Adresa");
		lblNewLabel_3.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(305, 153, 77, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setBounds(305, 182, 77, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Telefon");
		lblNewLabel_5.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(305, 211, 77, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Data Angajare");
		lblNewLabel_6.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_6.setBounds(305, 240, 116, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Numar Contract");
		lblNewLabel_7.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(305, 269, 116, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("IBAN");
		lblNewLabel_8.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(305, 298, 77, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Tipul");
		lblNewLabel_9.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setBounds(305, 327, 116, 16);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblSalariu = new JLabel("Salariu");
		lblSalariu.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblSalariu.setBounds(305, 356, 116, 16);
		frame.getContentPane().add(lblSalariu);
		
		JLabel lblNewLabel_11 = new JLabel("Numar ore");
		lblNewLabel_11.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_11.setBounds(305, 385, 116, 16);
		frame.getContentPane().add(lblNewLabel_11);
		
		
		JLabel lblNewLabel_13 = new JLabel("Username");
		lblNewLabel_13.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_13.setBounds(305, 414, 77, 16);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Parola");
		lblNewLabel_14.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_14.setBounds(305, 433, 77, 16);
		frame.getContentPane().add(lblNewLabel_14);
		
		nurseTextField[0] = new JTextField();
		nurseTextField[0].setBounds(505, 95, 116, 22);
		frame.getContentPane().add(nurseTextField[0]);
		nurseTextField[0].setColumns(10);
		
		nurseTextField[1] = new JTextField();
		nurseTextField[1].setBounds(505, 124, 116, 22);
		frame.getContentPane().add(nurseTextField[1]);
		nurseTextField[1].setColumns(10);
		
		nurseTextField[2] = new JTextField();
		nurseTextField[2].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(nurseTextField[2]);
		nurseTextField[2].setColumns(10);
		
		nurseTextField[3] = new JTextField();
		nurseTextField[3].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(nurseTextField[3]);
		nurseTextField[3].setColumns(10);
		
		nurseTextField[4] = new JTextField();
		nurseTextField[4].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(nurseTextField[4]);
		nurseTextField[4].setColumns(10);
		
		nurseTextField[5] = new JTextField();
		nurseTextField[5].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(nurseTextField[5]);
		nurseTextField[5].setColumns(10);
		
		nurseTextField[6] = new JTextField();
		nurseTextField[6].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(nurseTextField[6]);
		nurseTextField[6].setColumns(10);
		
		nurseTextField[7] = new JTextField();
		nurseTextField[7].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(nurseTextField[7]);
		nurseTextField[7].setColumns(10);
		
		
		nurseTextField[8] = new JTextField();
		nurseTextField[8].setBounds(505, 324, 116, 22);
		frame.getContentPane().add(nurseTextField[8]);
		nurseTextField[8].setColumns(10);
		
		
		nurseTextField[9] = new JTextField();
		nurseTextField[9].setBounds(505, 353, 116, 22);
		frame.getContentPane().add(nurseTextField[9]);
		nurseTextField[9].setColumns(10);
		
		nurseTextField[10] = new JTextField();
		nurseTextField[10].setBounds(505, 384, 116, 22);
		frame.getContentPane().add(nurseTextField[10]);
		nurseTextField[10].setColumns(10);
		
		nurseTextField[11] = new JTextField();
		nurseTextField[11].setBounds(505, 411, 116, 22);
		frame.getContentPane().add(nurseTextField[11]);
		
		nurseTextField[12] = new JTextField();
		nurseTextField[12].setBounds(505, 440, 116, 22);
		frame.getContentPane().add(nurseTextField[12]);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.setBounds(450, 494, 97, 25);
		frame.getContentPane().add(UpdateButton);
		nurseTextField[11].setColumns(10);
		
		UpdateButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					String nume = nurseTextField[0].getText();
					String prenume = nurseTextField[1].getText();
					String adresa = nurseTextField[2].getText();
					String email = nurseTextField[3].getText();
					String telefon = nurseTextField[4].getText();
					String data = nurseTextField[5].getText();
					int numar = Integer.parseInt(nurseTextField[6].getText());
					String iban = nurseTextField[7].getText();
					String Tip = nurseTextField[8].getText();
					int salariu = Integer.parseInt(nurseTextField[9].getText());
					int ore = Integer.parseInt(nurseTextField[10].getText());
					String username = nurseTextField[11].getText();
					String parola = nurseTextField[12].getText();
					
					
					Conexiune.cstmt = Conexiune.con.prepareCall("{call Update_asistent_medical(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					Conexiune.cstmt.setString(1, StringCNP);
					Conexiune.cstmt.setString(2, nume);
					Conexiune.cstmt.setString(3, prenume);
					Conexiune.cstmt.setString(4, adresa);
					Conexiune.cstmt.setString(5, telefon);
					Conexiune.cstmt.setString(6, email);
					Conexiune.cstmt.setString(7, iban);
					Conexiune.cstmt.setInt(8, numar);
					Conexiune.cstmt.setString(9, data);
					Conexiune.cstmt.setInt(10, salariu);
					Conexiune.cstmt.setString(11, Tip);
					Conexiune.cstmt.setInt(12, ore);
					Conexiune.cstmt.setString(13, username);
					Conexiune.cstmt.setString(14, parola);
					Conexiune.cstmt.execute();
				}
				catch(SQLException ea)
				{
					System.err.println("Nu s-a putut updata asistentul");
				}
			}
		});
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				UpdateUserAdmin window = new UpdateUserAdmin();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back);

	
		
	}

}
