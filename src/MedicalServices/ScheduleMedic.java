package MedicalServices;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScheduleMedic {

	private JFrame frame;
	private JTextField DataTf;
	private DefaultTableModel model;
	private JTable tableSchedule;
	private String StringMedicCNP;
	private String StringZi;
	private String[] VectorProgramari;
	private JTextField numeTf;
	private JTextField prenumeTf;
	private JTable table;
	private DefaultTableModel model2;
	private String StringPacientCNP;
	private String numePacient;
	private String prenumePacient;
	private String[] vector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleMedic window = new ScheduleMedic();
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
	public ScheduleMedic() {
		initialize();
		frame.setVisible(true);
	}
	
	public void GetStringCNP()
	{
		
		String username = Login.usernameLogin;
		try
		{
			String sql = "select CNP from medic where username = '" + username + "';";
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next()) {
				System.out.println("NU EXISTA specializarea");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next()) {
				StringMedicCNP = Conexiune.myRs.getString(1);
			}
		}
		catch(SQLException e)
		{	
			System.out.println("Nu s-a putut obtine CNP-ul medicului");
		}
	}
	
	public void ShowSchedule()
	{
		
			String sql = "select ora_programarii, servicii_medicale_idServicii_medicale, pacient_CNP, idPoliclinica from programari where "
					+"medic_CNP='" + StringMedicCNP + "' and data_programarii ='" + StringZi + "';";
			try {
				Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
				Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
				if (!Conexiune.myRs.next()) {
					System.out.println("NU EXISTA");
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next()) {
					String ora_sfarsit = null;
					int durata = 0;
					String ora_inceput = Conexiune.myRs.getString(1);
					ora_inceput = ora_inceput.substring(0, ora_inceput.length() - 3);
					int id_serviciu = Conexiune.myRs.getInt(2);
					String pacient_CNP = Conexiune.myRs.getString(3);
					int idPoliclinica= Conexiune.myRs.getInt(4);
					String sqlPacient = "select nume, prenume from pacient where CNP = '" + pacient_CNP + "'";
					Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sqlPacient);
					Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
					if (!Conexiune.myRs2.next()) {
						System.out.println("NU EXISTA");
					}
					Conexiune.myRs2.previous();
					String numePacient = null;
					String prenumePacient = null;
					while (Conexiune.myRs2.next()) {
						numePacient = Conexiune.myRs2.getString(1);
						prenumePacient = Conexiune.myRs2.getString(2);
					}
					
					String sql2 = "select durata from servicii_medicale where idServicii_medicale =" + id_serviciu + ";";
					Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql2);
					Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
					if (!Conexiune.myRs2.next()) {
						System.out.println("NU EXISTA");
					}
					Conexiune.myRs2.previous();
					while (Conexiune.myRs2.next()) {
						durata = Conexiune.myRs2.getInt(1);
						System.out.println(durata);
					}
						SimpleDateFormat df = new SimpleDateFormat("HH:mm");
						try {
							Date d = df.parse(ora_inceput);
							Calendar cal = Calendar.getInstance();
							cal.setTime(d);
							cal.add(Calendar.MINUTE, durata);
							ora_sfarsit = df.format(cal.getTime());
						} catch (ParseException e13) {
							System.out.println("Nu a mers ora");
						}
						VectorProgramari = new String[4];
						String oraProgramare = ora_inceput + " - " + ora_sfarsit;
						VectorProgramari[1] = numePacient + " " + prenumePacient;
						VectorProgramari[0] = pacient_CNP;
						VectorProgramari[2] = oraProgramare;
						VectorProgramari[3] = String.valueOf(idPoliclinica);
						model.addRow(VectorProgramari);
					

				} 
		}
		catch(SQLException e2)
		{
			System.out.println("Nu s-au putut afisa pacientii programati");
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
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(279, 50, 95, 16);
		frame.getContentPane().add(lblData);
		
		DataTf = new JTextField();
		DataTf.setBounds(331, 47, 116, 22);
		frame.getContentPane().add(DataTf);
		DataTf.setColumns(10);
		
		GetStringCNP();
		JButton btnSchedule = new JButton("See");
		btnSchedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringZi = DataTf.getText();
				model.setRowCount(0);
				ShowSchedule();
			}
		});
		btnSchedule.setBounds(473, 46, 97, 25);
		frame.getContentPane().add(btnSchedule);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 79, 608, 297);
		frame.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		tableSchedule = new JTable(model);
		model.addColumn("CNP");
		model.addColumn("Nume");
		model.addColumn("Programare");
		model.addColumn("Policlinica");
		tableSchedule.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableSchedule);
		
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Medic window = new Medic();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(177, 13, 95, 48);
		frame.getContentPane().add(Back);
		
		
	}
}
