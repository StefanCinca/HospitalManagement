package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ValidateData.Valid;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.util.Vector;
public class Appointment {

	private JFrame frame;
	private static int idPoliclinica = -1;
	private JTextField Serviciu;
	private String StringServiciu;
	private String StringZi;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	private JTable tableAngajati;
	private JTable tableProgramari;
	private JTextField Ziua;
	private String StringCNP;
	private String StringNume;
	private String StringPrenume;
	private String[] Vector;
	private String[] VectorProgramari;
	private String orarPoliclinica;
	private String programStart;
	private String programEnd;
	private String programStartWeekend;
	private String programEndWeekend;
	private String Orar;
	private int esteConcediu = 0;
	private int numarLinii = 1;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
	private JComboBox<String> CNP2;
	private JTextField ZiProgramare;
	private String StringCNP2;
	private String StringZi2;
	private JTextField pacientCNP;
	private JTextField medicCNP;
	private JTextField oraProgramarii;
	private JTextField dataProgramarii;
	private JLabel lblPacientCnp;
	private JLabel lblMedicCNP;
	private JLabel lblOraProgramare;
	private JLabel lblDataProgramare;
	private JLabel lblServiciuMedical;
	private JTextField serviciuMedicalTf;
	JButton btnAddProgramare;
	private String[] ProgramariSfarsit = new String[30];
	private String[] ProgramariInceput = new String[30];
	private int nrProgramari = 0;
	private String StringPacientCNP;
	private String StringMedicCNP;
	private String StringOraProgramarii;
	private String StringDataProgramarii;
	private boolean esteMedic = false;
	JComboCheckBox analizeMedicale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appointment window = new Appointment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void ShowInfo() {
		String username = Login.usernameLogin;
		String result;
		String sql = "select * from receptioner where username like '%" + username + "%';";
		try {
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next()) {
				idPoliclinica = Conexiune.myRs.getInt("unitate_medicala_idUnitate_Medicala");
			}
		} catch (SQLException ea) {
			System.err.println("Eroare, nu are unitate medicala");
		}
	}

	public void ShowSchedule() {
		esteMedic = false;
		try {
			String sql = "select specializare_id from servicii_medicale where nume = '" + StringServiciu + "';";
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next()) {
				System.out.println("NU EXISTA specializarea");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next()) {
				if (Conexiune.myRs.getString(1) != null) {
					esteMedic = true;
				}
			}
		} catch (SQLException exceptieSQL) {
			System.out.println("Nu a mers identificarea tipului de serviciu");
		}
		try {
			if (esteMedic == true) {
				Conexiune.cstmt = Conexiune.con.prepareCall("{call Arata_medici_capabili(?)};");
				Conexiune.cstmt.setString(1, StringServiciu);
				boolean hadResult = Conexiune.cstmt.execute();
				while (hadResult) {
					Conexiune.myRs = Conexiune.cstmt.getResultSet();
					if (!Conexiune.myRs.next()) {
						System.out.println("NU EXISTA medici");
					}
					Conexiune.myRs.previous();
					while (Conexiune.myRs.next()) {
						StringCNP = Conexiune.myRs.getString(1);
						StringNume = Conexiune.myRs.getString(2);
						StringPrenume = Conexiune.myRs.getString(3);
						CNP2.addItem(StringCNP);
						VerificaConcediu(StringZi);
						if (esteConcediu == 1) {
							Orar = "Concediu";
						} else {
							Orar = VerificaSpecific(StringZi);
							if (Orar == null) {
								int dayOfWeek = -1;
								try {
									Calendar myCalendar = Calendar.getInstance();
									Date myDate = sdf.parse(StringZi);
									myCalendar.setTime(myDate);
									dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);
									System.out.println("*" + dayOfWeek + "*");
									if (dayOfWeek != 1 && dayOfWeek != 6) {
										Orar = programStart + "-" + programEnd;
									} else {
										Orar = programStartWeekend + "-" + programEndWeekend;
									}
								} catch (Exception ea) {
									System.out.println("Nu am putut identifica ce zi este");
								}
							}
						}
						Vector = new String[4];
						Vector[0] = StringCNP;
						Vector[1] = StringNume;
						Vector[2] = StringPrenume;
						Vector[3] = Orar;
						model1.addRow(Vector);
					}

					hadResult = Conexiune.cstmt.getMoreResults();
				}
			} else if (StringServiciu.equals("Analize")) {

				String sql = "select CNP, nume, prenume from asistent_medical where unitate_medicala_idUnitate_Medicala"
						+ "=" + idPoliclinica + ";";
				Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
				Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
				if (!Conexiune.myRs.next()) {
					System.out.println("NU EXISTA asistenti");
				}
				Conexiune.myRs.previous();
				while (Conexiune.myRs.next()) {
					StringCNP = Conexiune.myRs.getString(1);
					StringNume = Conexiune.myRs.getString(2);
					StringPrenume = Conexiune.myRs.getString(3);
					CNP2.addItem(StringCNP);
					VerificaConcediu(StringZi);
					if (esteConcediu == 1) {
						Orar = "Concediu";
					} else {
						int dayOfWeek = -1;
						try {
							Calendar myCalendar = Calendar.getInstance();
							Date myDate = sdf.parse(StringZi);
							myCalendar.setTime(myDate);
							dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK);
							System.out.println("*" + dayOfWeek + "*");
							if (dayOfWeek != 1 && dayOfWeek != 6) {
								Orar = programStart + "-" + programEnd;
							} else {
								Orar = programStartWeekend + "-" + programEndWeekend;
							}
						} catch (Exception ea) {
							System.out.println("Nu am putut identifica ce zi este");
						}

					}
					Vector = new String[4];
					Vector[0] = StringCNP;
					Vector[1] = StringNume;
					Vector[2] = StringPrenume;
					Vector[3] = Orar;
					model1.addRow(Vector);

				}
			}
		} catch (SQLException ea) {
			System.out.println("Nu s-a putut gasi medicii care pot face asta");
		}
	}

	public void VerificaConcediu(String zi) {
		String sql = "select data_inceput, data_final from concediu where medic_CNP = '" + StringCNP + "';";
		try {
			Conexiune.preparedStatement2 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs2 = Conexiune.preparedStatement2.executeQuery();
			if (!Conexiune.myRs2.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs2.previous();
			while (Conexiune.myRs2.next()) {
				String data_inceput = Conexiune.myRs2.getString(1);
				String data_sfarsit = Conexiune.myRs2.getString(2);
				try {
					Date data_inceput_d = sdf.parse(data_inceput);
					Date data_sfarsit_d = sdf.parse(data_sfarsit);
					Date d_zi = sdf.parse(zi);
					if (d_zi.after(data_inceput_d) && d_zi.before(data_sfarsit_d) || d_zi.equals(data_inceput_d)
							|| d_zi.equals(data_sfarsit_d)) {
						esteConcediu = 1;
						return;
					} else {
						esteConcediu = 0;
					}
				} catch (Exception e123) {
					System.out.println("Nu a mers conversia datelor");
				}

			}
		} catch (SQLException catchy) {
			System.out.println("Nu s-a putut lua informatiile");
		}
	}

	public String VerificaSpecific(String zi) {
		String rezultat = "";
		System.out.println(zi);
		String sql = "select ora_inceput, ora_sfarsit, idPoliclinica from orar where ziua = '" + zi + "'"
				+ " and CNP = '" + StringCNP + "';";
		try {
			Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
			if (!Conexiune.myRs3.next()) {
				System.out.println("NU EXISTA");
				return null;
			}
			Conexiune.myRs3.previous();
			while (Conexiune.myRs3.next()) {
				numarLinii++;
				String ora_inceput = Conexiune.myRs3.getString(1);
				String ora_sfarsit = Conexiune.myRs3.getString(2);
				String id = Conexiune.myRs3.getString(3);
				if (Integer.parseInt(id) == idPoliclinica) {
					rezultat += ora_inceput + "-" + ora_sfarsit + " la Policlinica " + id + System.lineSeparator();

				}
			}
			return rezultat;
		} catch (SQLException esteExceptie) {
			System.out.println("Exceptie orar specific");
		}
		return null;
	}

	public void GetSchedulePoliclinica() {
		String sql = "select program_functionare from unitate_medicala;";
		try {
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next()) {
				System.out.println("NU EXISTA");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next()) {
				orarPoliclinica = Conexiune.myRs.getString(1);
			}
		} catch (SQLException e) {
			System.err.println("Nu s-a putut lua orarul policlinicii");
		}
	}

	/**
	 * Create the application.
	 */
	public Appointment() {
		initialize();
		frame.setVisible(true);
	}

	public void ArataProgramare() {
		String UserCNP;
		nrProgramari = 0;
		if (esteMedic == true) {
			UserCNP = "medic_CNP";
		} else {
			UserCNP = "asistent_CNP";
		}
		String sql = "select ora_programarii, servicii_medicale_idServicii_medicale, pacient_CNP from programari where "
				+ UserCNP + "= '" + StringCNP2 + "' and data_programarii ='" + StringZi2 + "';";
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
				if (esteMedic == true)
				{String sql2 = "select durata from servicii_medicale where idServicii_medicale =" + id_serviciu + ";";
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
				}
				else
				{
					durata = 5;
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
					VectorProgramari[0] = StringCNP2;
					VectorProgramari[1] = numePacient + " " + prenumePacient;
					VectorProgramari[2] = pacient_CNP;
					VectorProgramari[3] = oraProgramare;
					model2.addRow(VectorProgramari);
					ProgramariInceput[nrProgramari] = ora_inceput;
					ProgramariSfarsit[nrProgramari++] = ora_sfarsit;
				

			}
		} catch (SQLException e_programare) {
			System.err.println("Nu s-au putut lista programarile");
		}
	}

	public boolean VerificaProgramare() {
		boolean nuExista = true;
		String StringOraProgramariiSfarsit = null;
		int durata = 0;
		try {
			String sql = "select durata from servicii_medicale where nume = '" + (String) serviciuMedicalTf.getText()
					+ "';";
			Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
			if (!Conexiune.myRs3.next()) {
				System.out.println("NU EXISTA");
				nuExista = false;
			}
			Conexiune.myRs3.previous();
			while (Conexiune.myRs3.next()) {
				durata = Conexiune.myRs3.getInt(1);
			}
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			try {
				if (nuExista == false)
				{
					durata = 5;
				}
				Date d = df.parse(StringOraProgramarii);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				cal.add(Calendar.MINUTE, durata);
				StringOraProgramariiSfarsit = df.format(cal.getTime());
			} catch (ParseException e13) {
				System.out.println("Nu a mers ora");
			}

			try {
				Date xs = df.parse(StringOraProgramarii);
				Date xf = df.parse(StringOraProgramariiSfarsit);

				for (int i = 0; i < nrProgramari; i++) {
					Date time1Sfarsit = df.parse(ProgramariSfarsit[i]);
					Date time1Inceput = df.parse(ProgramariInceput[i]);
					if (time1Inceput.equals(xs)) {
						return false;
					}
					if (xs.before(time1Inceput) && xf.after(time1Inceput) && xf.before(time1Sfarsit)) {

						return false;
					}
					if (xs.after(time1Inceput) && xf.before(time1Sfarsit)) {
						return false;
					}
					if (xs.after(time1Inceput) && xs.before(time1Sfarsit)) {
						return false;
					}
					if (xs.before(time1Inceput) && xf.after(time1Sfarsit)) {
						return false;
					}

				}
				return true;
			} catch (Exception Why) {
				System.out.println("Nu a mers verificarea programarii");
			}
			return false;
		} catch (SQLException exception2) {
			System.out.println("Nu a mers  programare");
		}
		return false;
	}

	public void AdaugaProgramare() {
		if (VerificaProgramare() == true) {	
			try {
				
					Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_programare(?, ?, ?, ?, ?, ?, ?, ?)};");
					Conexiune.cstmt.setString(1, StringPacientCNP);
					Conexiune.cstmt.setString(2, StringDataProgramarii);
					Conexiune.cstmt.setString(3, StringOraProgramarii);
					Conexiune.cstmt.setString(4, StringMedicCNP);
					if (esteMedic == true)
					{Conexiune.cstmt.setString(5, serviciuMedicalTf.getText());}
					else
					{
						Conexiune.cstmt.setString(5, null);
					}
					Conexiune.cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
					System.out.println(esteMedic);
					Conexiune.cstmt.setBoolean(7, esteMedic);
					Conexiune.cstmt.setInt(8, idPoliclinica);
					Conexiune.cstmt.execute();
					int ok = Conexiune.cstmt.getInt(6);
					if (ok == 1) {
						System.out.println("Programarea a fost inregistrata");
					}
					if (ok == 2) {
						System.out.println("Programarea a fost modificata");
					}
					
				
					if (esteMedic == false)
					{
						
						int idProgramare = 0;
						String sql5 = "select idProgramare from programari where pacient_CNP ='" + StringPacientCNP +"' and "
								
							+ "asistent_CNP = '" + StringMedicCNP +"' and data_programarii = '" + StringDataProgramarii +
							"' and ora_programarii ='" + StringOraProgramarii + "';";
						Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql5);
						Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
						if (!Conexiune.myRs3.next()) {
							System.out.println("NU EXISTA");
						}
						Conexiune.myRs3.previous();
						while (Conexiune.myRs3.next()) {
							idProgramare = Conexiune.myRs3.getInt(1);
						}
						int idRaportMedical = 0;
						Conexiune.cstmt = Conexiune.con.prepareCall("{call Adauga_raport_medical(?, ?)}");
						Conexiune.cstmt.setString(1, StringPacientCNP);
						Conexiune.cstmt.setString(2, StringMedicCNP);
						Conexiune.cstmt.execute();
						String sql7 = "select id from rapoarte_medicale_asistent where pacientCNP = '" + 
						StringPacientCNP +"' and asistentCNP = '" + StringMedicCNP +"';";
						Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql7);
						Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
						if (!Conexiune.myRs3.next()) {
							System.out.println("NU EXISTA");
						}
						Conexiune.myRs3.previous();
						while (Conexiune.myRs3.next()) {
							idRaportMedical = Conexiune.myRs3.getInt(1);
						}
						
						int size = analizeMedicale.getItemCount();
						for (int i = 0; i < size; i++)
						{
							JCheckBox item = (JCheckBox) analizeMedicale.getItemAt(i);
							if (item.isSelected() == true)
							{
								String sql = "select idServicii_medicale from servicii_medicale where nume ='" + item.getText() + "';";
								Conexiune.preparedStatement3 = Conexiune.con.prepareStatement(sql);
								Conexiune.myRs3 = Conexiune.preparedStatement3.executeQuery();
								if (!Conexiune.myRs3.next()) {
									System.out.println("NU EXISTA");
								}
								Conexiune.myRs3.previous();
								while (Conexiune.myRs3.next()) {
									int id = Conexiune.myRs3.getInt(1);
									System.out.println(id);
									Conexiune.cstmt = Conexiune.con.prepareCall("{call Adauga_analiza_programare(?, ?)};");
									Conexiune.cstmt.setInt(1, idProgramare);
									Conexiune.cstmt.setInt(2, id);
									Conexiune.cstmt.execute();
									
								Conexiune.cstmt2 = Conexiune.con.prepareCall("{call Adauga_analize_raport_medical(?,?)};");
								Conexiune.cstmt2.setInt(1, idRaportMedical);
								Conexiune.cstmt2.setInt(2, id);	
								Conexiune.cstmt2.execute();
								}
							}
						}
					}
			} catch (SQLException eroareInserareProgramare) {
				System.out.println("Nu s-a putut insera programarea");
			}
		}

	}

	public java.util.Vector PopulateJCheckBox(java.util.Vector v) {
		String sql = "select nume from servicii_medicale where tip = 'Analize de laborator'";
		try {
			Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
			Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
			if (!Conexiune.myRs.next()) {
				System.out.println("NU EXISTA specializarea");
			}
			Conexiune.myRs.previous();
			while (Conexiune.myRs.next()) {
				JCheckBox check = new JCheckBox(Conexiune.myRs.getString(1));
				v.add(check);
			}
		} catch (SQLException exceptiema) {
			System.out.println("Nu s-a putut popula ComboCheckBox-ul");
		}
		return v;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		ShowInfo();

		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				Receptionist window = new Receptionist();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(25, 13, 95, 48);
		frame.getContentPane().add(Back, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Serviciul Medical");
		lblNewLabel.setBounds(423, 42, 105, 16);
		frame.getContentPane().add(lblNewLabel);

		Serviciu = new JTextField();
		Serviciu.setBounds(541, 39, 116, 22);
		frame.getContentPane().add(Serviciu);
		Serviciu.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 136, 1470, 297);
		frame.getContentPane().add(scrollPane);

		model1 = new DefaultTableModel();
		tableAngajati = new JTable(model1);
		model1.addColumn("CNP");
		model1.addColumn("Nume");
		model1.addColumn("Prenume");
		model1.addColumn("Orar");
		tableAngajati.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tableAngajati);

		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringServiciu = Serviciu.getText();
				StringZi = Ziua.getText();
				if (Valid.isTime(StringZi) == true)
				{ZiProgramare.setText(StringZi);
				ZiProgramare.setEditable(false);
				model1.setRowCount(0);
				CNP2.removeAllItems();
				ShowSchedule();
				}
				else
				{
						final JPanel panel = new JPanel();
						 JOptionPane.showMessageDialog(panel, "Data nu este valida. Formatul trebuie sa fie"
						 		+ "YYYY/MM/DD", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSearch.setBounds(541, 102, 97, 25);
		frame.getContentPane().add(btnSearch);

		JLabel lblZiua = new JLabel("Ziua");
		lblZiua.setBounds(445, 71, 56, 16);
		frame.getContentPane().add(lblZiua);

		Ziua = new JTextField();
		Ziua.setBounds(541, 67, 116, 22);
		frame.getContentPane().add(Ziua);
		Ziua.setColumns(10);

		JLabel lblCnp = new JLabel("CNP");
		lblCnp.setBounds(83, 446, 56, 16);
		frame.getContentPane().add(lblCnp);

		CNP2 = new JComboBox<String>();
		CNP2.setBounds(134, 446, 116, 22);
		frame.getContentPane().add(CNP2);

		ZiProgramare = new JTextField();
		ZiProgramare.setBounds(134, 481, 116, 22);
		frame.getContentPane().add(ZiProgramare);
		ZiProgramare.setColumns(10);

		JLabel lblZiua_1 = new JLabel("Ziua");
		lblZiua_1.setBounds(83, 484, 56, 16);
		frame.getContentPane().add(lblZiua_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 556, 450, 297);
		frame.getContentPane().add(scrollPane_1);

		model2 = new DefaultTableModel();
		tableProgramari = new JTable();
		tableProgramari.setModel(model2);

		model2.addColumn("CNP Doctor");
		model2.addColumn("Numele Pacientului");
		model2.addColumn("CNP Pacient");
		model2.addColumn("Programari");
		tableProgramari.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(tableProgramari);

		Vector<JCheckBox> v = new Vector<JCheckBox>();
		v = PopulateJCheckBox(v);
		analizeMedicale = new JComboCheckBox(v);
		analizeMedicale.setBounds(978, 675, 251, 22);
		analizeMedicale.setVisible(false);
		frame.getContentPane().add(analizeMedicale);
		

		JButton btnSearchProgramare = new JButton("Search");
		btnSearchProgramare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringCNP2 = (String) (CNP2.getSelectedItem());
				StringZi2 = StringZi;
				if (Valid.isTime(StringZi2) == false || StringCNP2 == null)
				{
						final JPanel panel = new JPanel();
						 JOptionPane.showMessageDialog(panel, "Ziua sau CNP-ul nu sunt valide. Formatul zilei trebuie sa fie"
						 		+ "YYYY/MM/DD", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{model2.setRowCount(0);
				lblPacientCnp.setVisible(true);
				lblDataProgramare.setVisible(true);
				lblOraProgramare.setVisible(true);
				lblMedicCNP.setVisible(true);
				lblServiciuMedical.setVisible(true);
				if (esteMedic == true) {
					serviciuMedicalTf.setVisible(true);
					serviciuMedicalTf.setEditable(false);
				} else {
					analizeMedicale.setVisible(true);
				}
				serviciuMedicalTf.setText(StringServiciu);
				pacientCNP.setVisible(true);
				medicCNP.setVisible(true);
				medicCNP.setText(StringCNP2);
				medicCNP.setEditable(false);
				oraProgramarii.setVisible(true);
				dataProgramarii.setVisible(true);
				dataProgramarii.setText(StringZi);
				dataProgramarii.setEditable(false);
				btnAddProgramare.setVisible(true);
				ArataProgramare();
			}
			}
		});
		btnSearchProgramare.setBounds(144, 518, 97, 25);
		frame.getContentPane().add(btnSearchProgramare);

		lblPacientCnp = new JLabel("Pacient CNP");
		lblPacientCnp.setBounds(825, 556, 89, 16);
		lblPacientCnp.setVisible(false);
		frame.getContentPane().add(lblPacientCnp);

		lblDataProgramare = new JLabel("Data Programarii");
		lblDataProgramare.setBounds(825, 643, 116, 16);
		lblDataProgramare.setVisible(false);
		frame.getContentPane().add(lblDataProgramare);

		lblOraProgramare = new JLabel("Ora Programarii");
		lblOraProgramare.setBounds(825, 614, 95, 16);
		lblOraProgramare.setVisible(false);
		frame.getContentPane().add(lblOraProgramare);

		lblMedicCNP = new JLabel("Medic CNP");
		lblMedicCNP.setBounds(825, 585, 89, 16);
		lblMedicCNP.setVisible(false);
		frame.getContentPane().add(lblMedicCNP);

		pacientCNP = new JTextField();
		pacientCNP.setBounds(978, 553, 251, 22);
		frame.getContentPane().add(pacientCNP);
		pacientCNP.setVisible(false);
		pacientCNP.setColumns(10);

		medicCNP = new JTextField();
		medicCNP.setBounds(978, 582, 251, 22);
		frame.getContentPane().add(medicCNP);
		medicCNP.setVisible(false);
		medicCNP.setColumns(10);

		oraProgramarii = new JTextField();
		oraProgramarii.setBounds(978, 614, 251, 22);
		frame.getContentPane().add(oraProgramarii);
		oraProgramarii.setVisible(false);
		oraProgramarii.setColumns(10);

		dataProgramarii = new JTextField();
		dataProgramarii.setBounds(978, 640, 251, 22);
		frame.getContentPane().add(dataProgramarii);
		dataProgramarii.setVisible(false);
		dataProgramarii.setColumns(10);

		lblServiciuMedical = new JLabel("Serviciu Medical");
		lblServiciuMedical.setBounds(825, 678, 116, 16);
		frame.getContentPane().add(lblServiciuMedical);
		lblServiciuMedical.setVisible(false);
		lblServiciuMedical.setVisible(false);

		serviciuMedicalTf = new JTextField();
		serviciuMedicalTf.setBounds(978, 675, 251, 22);
		serviciuMedicalTf.setVisible(false);
		frame.getContentPane().add(serviciuMedicalTf);

		btnAddProgramare = new JButton("Add");
		btnAddProgramare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringPacientCNP = pacientCNP.getText();
				StringMedicCNP = medicCNP.getText();
				StringOraProgramarii = oraProgramarii.getText();
				StringDataProgramarii = dataProgramarii.getText();
				AdaugaProgramare();
			}
		});
		btnAddProgramare.setBounds(1040, 722, 141, 25);
		btnAddProgramare.setVisible(false);
		frame.getContentPane().add(btnAddProgramare);

		GetSchedulePoliclinica();

		programStart = orarPoliclinica.substring(5, 7);
		programStart = programStart.concat(":00");

		programEnd = orarPoliclinica.substring(8, 10);
		programEnd = programEnd.concat(":00");

		programStartWeekend = orarPoliclinica.substring(17, 19);
		programEndWeekend = orarPoliclinica.substring(20, 22);

		programStartWeekend = programStartWeekend.concat(":00");
		programEndWeekend = programEndWeekend.concat(":00");
	}
}
