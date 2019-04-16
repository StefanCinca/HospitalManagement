package MedicalServices;

import java.awt.EventQueue;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class UpdateDoctor {

	private JFrame frame;
	private JTextField[] doctorTextField;
	private static final int NRTEXTFIELD = 14;
	private JTextField CNP;
	private String StringCNP;
	private JTextField GradDoctor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDoctor window = new UpdateDoctor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void GetInfo()
	{
		String result;
		try 
		{
			String sql = "select * from medic where CNP like '%" + StringCNP + "%';";
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
			doctorTextField[0].setText(result);
			result = Conexiune.myRs.getString("prenume");
			doctorTextField[1].setText(result);
			result = Conexiune.myRs.getString("numar_telefon");
			doctorTextField[4].setText(result);
			result = Conexiune.myRs.getString("iban");
			doctorTextField[7].setText(result);
			result = Conexiune.myRs.getString("adresa");
			doctorTextField[2].setText(result);
			result = Conexiune.myRs.getString("email");
			doctorTextField[3].setText(result);
			result = Conexiune.myRs.getString("data_angajarii");
			doctorTextField[5].setText(result);
			int numar = Conexiune.myRs.getInt("numar_contract");
			doctorTextField[6].setText(String.valueOf(numar));
			int ore = Conexiune.myRs.getInt("numar_ore");
			doctorTextField[10].setText(String.valueOf(ore));
			int salariu = Conexiune.myRs.getInt("salariu");
			doctorTextField[9].setText(String.valueOf(salariu));
			String codParafa = Conexiune.myRs.getString("cod_parafa");
			doctorTextField[8].setText(codParafa);
			String grad = Conexiune.myRs.getString("grad");
			GradDoctor.setText(grad);
			int procent = Conexiune.myRs.getInt("procent");
			doctorTextField[11].setText(String.valueOf(procent));
			String username = Conexiune.myRs.getString("username");
			doctorTextField[12].setText(username);
			String parola = Conexiune.myRs.getString("parola");
			doctorTextField[13].setText(parola);
		}
		}
		catch(SQLException ea)
		{
			System.err.println("Nu a mers luarea informatiilor");
		}
	}
	/**
	 * Create the application.
	 */
	public UpdateDoctor() {
		initialize();
		frame.setVisible(true);
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
		
		
		
		
		doctorTextField = new JTextField[NRTEXTFIELD];
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
		
		JLabel lblNewLabel_9 = new JLabel("Grad");
		lblNewLabel_9.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setBounds(305, 327, 77, 16);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Cod de Parafa");
		lblNewLabel_10.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_10.setBounds(305, 356, 116, 16);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblSalariu = new JLabel("Salariu");
		lblSalariu.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblSalariu.setBounds(305, 385, 116, 16);
		frame.getContentPane().add(lblSalariu);
		
		JLabel lblNewLabel_11 = new JLabel("Numar ore");
		lblNewLabel_11.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_11.setBounds(305, 414, 116, 16);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Procent");
		lblNewLabel_12.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_12.setBounds(305, 443, 77, 16);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Username");
		lblNewLabel_13.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_13.setBounds(305, 472, 77, 16);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Parola");
		lblNewLabel_14.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		lblNewLabel_14.setBounds(305, 501, 77, 16);
		frame.getContentPane().add(lblNewLabel_14);

		doctorTextField[0] = new JTextField();
		doctorTextField[0].setBounds(505, 95, 116, 22);
		frame.getContentPane().add(doctorTextField[0]);
		doctorTextField[0].setColumns(10);
		
		doctorTextField[1] = new JTextField();
		doctorTextField[1].setBounds(505, 124, 116, 22);
		frame.getContentPane().add(doctorTextField[1]);
		doctorTextField[1].setColumns(10);
		
		doctorTextField[2] = new JTextField();
		doctorTextField[2].setBounds(505, 150, 116, 22);
		frame.getContentPane().add(doctorTextField[2]);
		doctorTextField[2].setColumns(10);
		
		doctorTextField[3] = new JTextField();
		doctorTextField[3].setBounds(505, 179, 116, 22);
		frame.getContentPane().add(doctorTextField[3]);
		doctorTextField[3].setColumns(10);
		
		doctorTextField[4] = new JTextField();
		doctorTextField[4].setBounds(505, 208, 116, 22);
		frame.getContentPane().add(doctorTextField[4]);
		doctorTextField[4].setColumns(10);
		
		doctorTextField[5] = new JTextField();
		doctorTextField[5].setBounds(505, 237, 116, 22);
		frame.getContentPane().add(doctorTextField[5]);
		doctorTextField[5].setColumns(10);
		
		doctorTextField[6] = new JTextField();
		doctorTextField[6].setBounds(505, 266, 116, 22);
		frame.getContentPane().add(doctorTextField[6]);
		doctorTextField[6].setColumns(10);
		
		doctorTextField[7] = new JTextField();
		doctorTextField[7].setBounds(505, 295, 116, 22);
		frame.getContentPane().add(doctorTextField[7]);
		doctorTextField[7].setColumns(10);
		
		
		GradDoctor = new JTextField();
		GradDoctor.setBounds(505, 324, 127, 22);
		frame.getContentPane().add(GradDoctor);
		
		doctorTextField[8] = new JTextField();
		doctorTextField[8].setBounds(505, 353, 116, 22);
		frame.getContentPane().add(doctorTextField[8]);
		doctorTextField[8].setColumns(10);
		
		
		doctorTextField[9] = new JTextField();
		doctorTextField[9].setBounds(505, 382, 116, 22);
		frame.getContentPane().add(doctorTextField[9]);
		doctorTextField[9].setColumns(10);
		
		doctorTextField[10] = new JTextField();
		doctorTextField[10].setBounds(505, 411, 116, 22);
		frame.getContentPane().add(doctorTextField[10]);
		doctorTextField[10].setColumns(10);
		
		doctorTextField[11] = new JTextField();
		doctorTextField[11].setBounds(505, 440, 116, 22);
		frame.getContentPane().add(doctorTextField[11]);
		
		doctorTextField[12] = new JTextField();
		doctorTextField[12].setBounds(505, 469, 116, 22);
		frame.getContentPane().add(doctorTextField[12]);
		
		doctorTextField[13] = new JTextField();
		doctorTextField[13].setBounds(505, 498, 116, 22);
		frame.getContentPane().add(doctorTextField[13]);
		
		JButton UpdateButton = new JButton("Update");
		UpdateButton.setBounds(721, 497, 97, 25);
		frame.getContentPane().add(UpdateButton);
		doctorTextField[11].setColumns(10);
		
		UpdateButton.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						try
						{
							String Nume = doctorTextField[0].getText();
							String Prenume = doctorTextField[1].getText();
							String Adresa = doctorTextField[2].getText();
							String Email = doctorTextField[3].getText();
							String Telefon = doctorTextField[4].getText();
							String DataAngajare = doctorTextField[5].getText();
							int NumarContract = Integer.parseInt(doctorTextField[6].getText());
							String Iban = doctorTextField[7].getText();
							String Grad = GradDoctor.getText();
							String Parafa = doctorTextField[8].getText();
							int Salariu = Integer.parseInt(doctorTextField[9].getText());
							int NumarOre = Integer.parseInt(doctorTextField[10].getText());
							int Procent = Integer.parseInt(doctorTextField[11].getText());
							String Username = doctorTextField[12].getText();
							String Parola = doctorTextField[13].getText();
							
							Conexiune.cstmt = Conexiune.con.prepareCall("{call Update_medic(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
							Conexiune.cstmt.setString(1, StringCNP);
							Conexiune.cstmt.setString(2, Nume);
							Conexiune.cstmt.setString(3, Prenume);
							Conexiune.cstmt.setString(4, Adresa);
							Conexiune.cstmt.setString(5, Telefon);
							Conexiune.cstmt.setString(6, Email);
							Conexiune.cstmt.setString(7, Iban);
							Conexiune.cstmt.setInt(8, NumarContract);
							Conexiune.cstmt.setString(9, DataAngajare);
							Conexiune.cstmt.setString(10, Grad);
							Conexiune.cstmt.setString(11, Parafa);
							Conexiune.cstmt.setInt(12, Salariu);
							Conexiune.cstmt.setInt(13, NumarOre);
							Conexiune.cstmt.setInt(14, Procent);
							Conexiune.cstmt.setString(15, Username);
							Conexiune.cstmt.setString(16, Parola);
							Conexiune.cstmt.execute();
						}
						catch(SQLException ea)
						{
							System.err.println("Nu s-a putut updata medicul");
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
