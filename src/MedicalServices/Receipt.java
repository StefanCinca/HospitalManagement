package MedicalServices;
import receipt.*;
import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;

import ValidateData.Valid;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Receipt {

	private JFrame frame;
	private JTextField dataTf;
	private JTextField NumeTf;
	private JTextField PrenumeTf;
	private JTable tableProgramari;
	private DefaultTableModel model;
	private String StringData;
	private String StringNume;
	private String StringPrenume;
	private String StringPacientCNP = null;
	private String numeServiciu;
	private JTextField oraTf;
	private JTextField numeDoctorTf;
	private static int dim = 0;
	private JCheckBox[] vector;
	private JTextField prenumeDoctorTf;
	private JComboBox<String> functieTf;
	private String numeDoctor = null;
	private String prenumeDoctor = null;
	private JButton EmiteBon;
	JComboCheckBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receipt window = new Receipt();
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
	public Receipt() {
		initialize();
		frame.setVisible(true);
	}
	
	public void ArataProgramari()
	{
		int idProgramare = 0;
		numeServiciu = "";
		String sql = "select CNP from pacient where nume = '" + StringNume + "'" + " and prenume = '" + StringPrenume + "';";
		try
		{
			boolean estePlatit = false;
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next())
			{
				System.out.println("NU EXISTA");
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Pacientul cu numele respectiv nu exista", "Error", JOptionPane.ERROR_MESSAGE);
				 oraTf.setVisible(false);
					numeDoctorTf.setVisible(false);
					functieTf.setVisible(false);
					comboBox.setVisible(false);
					prenumeDoctorTf.setVisible(false);
					EmiteBon.setVisible(false);
				 return;
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next())
			{
				StringPacientCNP = Conexiune.myRs.getString(1);
			}
			if (StringPacientCNP != null)
			{
				oraTf.setVisible(true);
				numeDoctorTf.setVisible(true);
				functieTf.setVisible(true);
				comboBox.setVisible(true);
				prenumeDoctorTf.setVisible(true);
				EmiteBon.setVisible(true);
			}
			sql = "select medic_CNP,asistent_CNP,ora_programarii, servicii_medicale_idServicii_medicale, idProgramare from programari where " +
			"pacient_CNP = '" + StringPacientCNP + "'" + " and data_programarii = '" + StringData +"';";
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next())
			{
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next())
			{	
				numeServiciu = "";
				String ora_sfarsit = null;
				int durata = 0;
				String StringMedicCNP = Conexiune.myRs.getString(1);
				String StringAsistentCNP = Conexiune.myRs.getString(2);
				String ora_inceput = Conexiune.myRs.getString(3);
				idProgramare = Conexiune.myRs.getInt(5);
				ora_inceput = ora_inceput.substring(0, ora_inceput.length() - 3);
				int id_serviciu = Conexiune.myRs.getInt(4);
				String sql2 = "select durata, nume from servicii_medicale where idServicii_medicale =" + id_serviciu +";";
				Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql2);
				Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
				if (!Conexiune.myRs2.next())
				{
					System.out.println("NU EXISTA");
				}
				Conexiune.myRs2.previous();
				while (Conexiune.myRs2.next() || id_serviciu == 0)
				{
					if (id_serviciu != 0)
					{
						durata = Conexiune.myRs2.getInt(1);
						numeServiciu = Conexiune.myRs2.getString(2);
					}
					else
					{
						durata = 5;
						String sql5 = "select id_analize from programari_has_analize where id_programare = " + idProgramare + ";";
						Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql5);
						Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
						if (!Conexiune.myRs3.next())
						{
							System.out.println("NU EXISTA LOL?");
						}
						Conexiune.myRs3.previous();
						while (Conexiune.myRs3.next())
						{
							int id_analize = Conexiune.myRs3.getInt(1);
							String sql6 = "select nume from servicii_medicale where idServicii_medicale =" + id_analize + ";";
							Conexiune.preparedStatement4 = Conexiune.con.prepareStatement(sql6);
							Conexiune.myRs4 = Conexiune.preparedStatement4.executeQuery();
							if (!Conexiune.myRs4.next())
							{
								System.out.println("NU EXISTA LOL4?");
							}
							Conexiune.myRs4.previous();
							while (Conexiune.myRs4.next())
							{
								numeServiciu += Conexiune.myRs4.getString(1) + ";";
							}
							
						}
					}
				System.out.println(durata);
				 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
				 try
				 {Date d = df.parse(ora_inceput); 
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(d);
				 cal.add(Calendar.MINUTE, durata);
				 ora_sfarsit = df.format(cal.getTime());
				 }
				 catch(ParseException e13)
				 {
					 System.out.println("Nu a mers ora");
				 }
				 if (id_serviciu == 0)
				 {
					 break;
				 }
				}
				String oraProgramare = ora_inceput + "-" + ora_sfarsit;
				String Functie = null;
				String CNP = null;
				if (StringMedicCNP == null && StringAsistentCNP != null)
				{
					Functie = "Asistent";
					CNP = StringAsistentCNP;
				}
				else if (StringMedicCNP != null && StringAsistentCNP == null)
				{
					Functie = "Doctor";
					CNP = StringMedicCNP;
				}
				
				String sql3;
				if (StringMedicCNP != null)
				{
					sql3 = "select nume, prenume from medic where CNP = '" + CNP + "'";
				}
				else
				{
					sql3 = "select nume, prenume from asistent_medical where CNP = '" + CNP + "'";
				}
				Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql3);
				Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
				if (!Conexiune.myRs2.next())
				{
					System.out.println("NU EXISTA");
				}
				Conexiune.myRs2.previous();
				while (Conexiune.myRs2.next())
				{
					numeDoctor = Conexiune.myRs2.getString(1);
					prenumeDoctor = Conexiune.myRs2.getString(2);
				}
				
				String[] Vector = new String[7];
				Vector[0] = Functie;
				Vector[1] = CNP;
				Vector[2] = numeDoctor + " " + prenumeDoctor;
				Vector[3] = StringPacientCNP;
				Vector[4] = oraProgramare;
				Vector[5] = numeServiciu;
				model.addRow(Vector);
				 
			}
		}
		catch(SQLException e_programare)
		{
			System.err.println("Nu s-au putut lista programarile");
		}
	}
	
	public void EmiteBon(String functie, String nume, String prenume, String ora, String[] servicii, int dimServicii)
	{
		int total = 0;
		int[] pret = new int[dimServicii];
		boolean esteMedic = true;
		String MedicCNP = null;
		try
		{
			for (int i = 0; i < dimServicii; i++)
			{
				String item = servicii[i];
				String sql = "select pret from servicii_medicale where nume ='" + item + "'";
				Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
				Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
				if (!Conexiune.myRs2.next())
				{
					System.out.println("NU EXISTA");
				}
				Conexiune.myRs2.previous();
				while (Conexiune.myRs2.next())
				{
					pret[i] = Conexiune.myRs2.getInt(1);
					total += Conexiune.myRs2.getInt(1);
				}
			}
			String sql = null;
			if (functie.equals("Doctor"))
			{
				sql = "select CNP from medic where nume = '" + nume +"'" + " and prenume ='" + prenume + "';";
			}
			else if (functie.equals("Asistent"))
			{
				esteMedic = false;
				sql = "select CNP from asistent_medical where nume = '" + nume +"'" + " and prenume ='" + prenume + "';";
			}
			
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next())
			{
				System.out.println("NU EXISTA");
				final JPanel panel = new JPanel();
				 JOptionPane.showMessageDialog(panel, "Nu exista doctor/asistent cu numele acesta", "Error", JOptionPane.ERROR_MESSAGE);
				 return;
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next())
			{
				MedicCNP = Conexiune.myRs2.getString(1);
			}
			
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Adauga_bon_fiscal(?, ?, ?, ?, ?, ?, ?)}");
			Conexiune.cstmt.setString(1, StringPacientCNP);
			Conexiune.cstmt.setString(2, MedicCNP);
			Conexiune.cstmt.setString(3, MedicCNP);
			Conexiune.cstmt.setInt(4, total);
			Conexiune.cstmt.setString(5, StringData);
			Conexiune.cstmt.setString(6, ora);
			Conexiune.cstmt.setBoolean(7, esteMedic);
			Conexiune.cstmt.execute();
			
			System.out.println("ADAUGA BON_FISCAL");
			
			String numeReceptionist = null;
			String prenumeReceptionist = null;
			String denumirePoliclinica = null;
			int idPoliclinica = -1;
			String username = Login.usernameLogin;
			String sql3 = "select nume, prenume, unitate_medicala_idUnitate_Medicala from receptioner where username ='" + username + "'";
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql3);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next())
			{
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next())
			{
				numeReceptionist = Conexiune.myRs2.getString(1);
				prenumeReceptionist = Conexiune.myRs2.getString(2);
				idPoliclinica = Conexiune.myRs2.getInt(3);
			}
			
			String sql4 = "select denumire from unitate_medicala where idUnitate_Medicala = " + idPoliclinica + ";";
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql4);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next())
			{
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next())
			{
				denumirePoliclinica = Conexiune.myRs2.getString(1);
			}
			
			MakeFile bill = new MakeFile(denumirePoliclinica, nume + " " + prenume, numeReceptionist + " " +
					prenumeReceptionist, servicii, pret, dimServicii, StringData, ora);
			bill.GetBillAsPdf();
			
		}
		catch(SQLException exceptiePret)
		{
			System.out.println("Nu s-a putut calcula pretul total");
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(0, 65, 56, 16);
		frame.getContentPane().add(lblData);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setBounds(0, 94, 56, 16);
		frame.getContentPane().add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setBounds(0, 134, 56, 16);
		frame.getContentPane().add(lblPrenume);
		
		dataTf = new JTextField();
		dataTf.setBounds(57, 62, 116, 22);
		frame.getContentPane().add(dataTf);
		dataTf.setColumns(10);
		
		NumeTf = new JTextField();
		NumeTf.setBounds(57, 91, 116, 22);
		frame.getContentPane().add(NumeTf);
		NumeTf.setColumns(10);
		
		PrenumeTf = new JTextField();
		PrenumeTf.setBounds(57, 131, 116, 22);
		frame.getContentPane().add(PrenumeTf);
		PrenumeTf.setColumns(10);
		
		JButton btnNewButton = new JButton("See");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StringNume  = NumeTf.getText();
				StringPrenume = PrenumeTf.getText();
				StringData = dataTf.getText();
				if (Valid.isTime(StringData) == true)
				{model.setRowCount(0);
				ArataProgramari();}
				else
				{
						final JPanel panel = new JPanel();
						 JOptionPane.showMessageDialog(panel, "Data nu este valida. Formatul trebuie sa fie"
						 		+ "YYYY/MM/DD", "Error", JOptionPane.ERROR_MESSAGE);	
						 oraTf.setVisible(false);
							numeDoctorTf.setVisible(false);
							functieTf.setVisible(false);
							comboBox.setVisible(false);
							prenumeDoctorTf.setVisible(false);
							EmiteBon.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(67, 166, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(185, 28, 985, 253);
		frame.getContentPane().add(scrollPane);
		
		model = new DefaultTableModel();
		tableProgramari = new JTable();
		tableProgramari.setModel(model);
		
		model.addColumn("Functie");
		model.addColumn("CNP");
		model.addColumn("Nume");
		model.addColumn("CNP Pacient");
		model.addColumn("Ora");
		model.addColumn("Serviciu");
		model.addColumn("Platit");
		tableProgramari.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableProgramari);
		
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				Receptionist window = new Receptionist();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(0, 0, 95, 48);
		frame.getContentPane().add(Back, BorderLayout.NORTH);
		
		JLabel lblOra = new JLabel("Ora");
		lblOra.setBounds(10, 337, 56, 16);
		frame.getContentPane().add(lblOra);
		vector = new JCheckBox[100];
		
		JLabel lblNewLabel = new JLabel("Servicii");
		lblNewLabel.setBounds(10, 366, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		oraTf = new JTextField();
		oraTf.setBounds(113, 334, 116, 22);
		frame.getContentPane().add(oraTf);
		oraTf.setVisible(false);
		oraTf.setColumns(10);
		
		JLabel lblNumeDoctor = new JLabel("Nume ");
		lblNumeDoctor.setBounds(10, 422, 85, 16);
		frame.getContentPane().add(lblNumeDoctor);
		
		numeDoctorTf = new JTextField();
		numeDoctorTf.setBounds(113, 419, 116, 22);
		frame.getContentPane().add(numeDoctorTf);
		numeDoctorTf.setColumns(10);
		numeDoctorTf.setVisible(false);
		Vector<JCheckBox> v = new Vector<JCheckBox>();
		try
		{
			String sql = "select nume from servicii_medicale";
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next())
			{
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next())
			{
				v.add(new JCheckBox(Conexiune.myRs2.getString(1)));
			}
			
		}
		catch(SQLException exceptie)
		{
			System.out.println("Nu s-au putut lista serviciile medicale");
		}
		
		comboBox = new JComboCheckBox(v);
		comboBox.setBounds(78, 366, 200, 22);	
		comboBox.setVisible(false);
		frame.getContentPane().add(comboBox);
		
		functieTf = new JComboBox<String>();
		functieTf.setBounds(113, 390, 116, 22);
		functieTf.addItem("Doctor");
		functieTf.addItem("Asistent");
		functieTf.setVisible(false);
		frame.getContentPane().add(functieTf);
		
		
		EmiteBon = new JButton("Emite bon");
		EmiteBon.setVisible(false);
		EmiteBon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String nume = numeDoctorTf.getText();
				String prenume = prenumeDoctorTf.getText();
				String functie = (String) functieTf.getSelectedItem();
				String ora = oraTf.getText();
				if (Valid.isHour(ora) == false)
				{
					final JPanel panel = new JPanel();
					 JOptionPane.showMessageDialog(panel, "Ora nu este valida.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{int dimArray = 0;
				String[] arrayServicii = new String[10];
				
				for (int i = 0; i < v.size(); i++)
				{
					if (v.get(i).isSelected() == true)
					{
						arrayServicii[dimArray++] = v.get(i).getText();
					}
				}
				if (dimArray == 0)
				{
					final JPanel panel = new JPanel();
					 JOptionPane.showMessageDialog(panel, "Nu a fost selectat niciun serviciu.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{EmiteBon(functie, nume, prenume, ora, arrayServicii, dimArray);}
				
			}
			}
		});
		EmiteBon.setBounds(123, 498, 97, 25);
		frame.getContentPane().add(EmiteBon);
		
		JLabel lblNewLabel_1 = new JLabel("Prenume");
		lblNewLabel_1.setBounds(10, 454, 97, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		prenumeDoctorTf = new JTextField();
		prenumeDoctorTf.setBounds(113, 451, 116, 22);
		frame.getContentPane().add(prenumeDoctorTf);
		prenumeDoctorTf.setVisible(false);
		prenumeDoctorTf.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Functie");
		lblNewLabel_2.setBounds(10, 393, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		
	}
}

