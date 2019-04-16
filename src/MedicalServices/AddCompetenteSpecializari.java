package MedicalServices;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.JLabel;

public class AddCompetenteSpecializari {

	private JFrame frame;
	private JRadioButton BtnCardiologie;
	private JRadioButton BtnBoli;
	private JRadioButton BtnOftalmologie;
	private JRadioButton BtnPsihiatrie;
	private JRadioButton BtnNeurologie;
	private JRadioButton BtnEndocrinologie;
	private JRadioButton BtnAnestezie;
	private int okCompetente = 0;
	private int okSpecializari = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCompetenteSpecializari window = new AddCompetenteSpecializari();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int isUnique(JTextArea area, String competenta)
	{
		try
		{Document doc = area.getDocument();
		String text = doc.getText(0, doc.getLength());
		if (text.contains(competenta))
		{
			return 1;
		}
		}
		catch (Exception e)
		{
			System.err.println("NU s-a putut forma fisierul");
		}
		return 0;
	}
	
	
	public Vector<String> GetItems() 
	{
		Vector<String> VectorList = new Vector<String>(100);
		String sql = "select * from competente;";
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
				VectorList.add(Conexiune.myRs.getString("Competente"));
			}
		}
		catch(SQLException e)
		{
			System.err.println("Eroare accesare competente");
		}
		return VectorList;
	}
	public void AddSpecializareString(String specializare) throws SQLException
	{
		int[] arrayId = new int[10];
		int dim = 0;
		String sql = "select * from specializare idSpecializare where Specializare = '"+ specializare + "';";
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA SPECIALIZARE");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next())
		{
			
			int id = Conexiune.myRs.getInt("idSpecializare");
			arrayId[dim++] = id;
		}
		
		
		for (int i = 0; i < dim; i++)
		{
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_specializare_medic(?, ?)}");
			Conexiune.cstmt.setString(1, AddDoctor.CNP);
			Conexiune.cstmt.setInt(2, arrayId[i]);
			Conexiune.cstmt.execute();	
		}
		
	}
	
	public void AddSpecializari() throws SQLException
	{
		if (BtnCardiologie.isSelected())
		{
			AddSpecializareString(BtnCardiologie.getText());
		}
		if (BtnBoli.isSelected())
		{
			AddSpecializareString(BtnBoli.getText());
		}
		if (BtnOftalmologie.isSelected()) 
		{
			AddSpecializareString(BtnOftalmologie.getText());
		}
		if (BtnPsihiatrie.isSelected()) 
		{
			AddSpecializareString(BtnPsihiatrie.getText());
		}
		if (BtnNeurologie.isSelected())
		{
			AddSpecializareString(BtnNeurologie.getText());
		}
		if (BtnEndocrinologie.isSelected())
		{
			AddSpecializareString(BtnEndocrinologie.getText());
		}
		if (BtnAnestezie.isSelected())
		{
			AddSpecializareString(BtnAnestezie.getText());
		}
		
	}
	
	public void AddCompetenta(String competenta) throws SQLException
	{
		String sql = "select * from competente idCompetente where Competente = '"+ competenta + "';";
		Conexiune.preparedStatement = Conexiune.con.prepareStatement(sql);
		Conexiune.myRs = Conexiune.preparedStatement.executeQuery();
		if (!Conexiune.myRs.next())
		{
			System.out.println("NU EXISTA COMPETENTA");
		}
		Conexiune.myRs.previous();
		while (Conexiune.myRs.next())
		{
			int id = Conexiune.myRs.getInt("idCompetente");
			Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_competente_medic(?, ?)}");
			Conexiune.cstmt.setString(1, AddDoctor.CNP);
			Conexiune.cstmt.setInt(2, id);
			Conexiune.cstmt.execute();
		}
	}
	
	/**
	 * Create the application.
	 */
	public AddCompetenteSpecializari() {
		initialize();
		System.out.println(AddDoctor.CNP);
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
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(693, 334, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		BtnCardiologie = new JRadioButton("Cardiologie");
		BtnCardiologie.setBounds(108, 334, 127, 25);
		BtnCardiologie.setSelected(false);
		frame.getContentPane().add(BtnCardiologie);
		
		BtnBoli = new JRadioButton("Boli Infectioase");
		BtnBoli.setBounds(239, 334, 127, 25);
		frame.getContentPane().add(BtnBoli);
		
		BtnOftalmologie = new JRadioButton("Oftalmologie");
		BtnOftalmologie.setBounds(397, 334, 127, 25);
		frame.getContentPane().add(BtnOftalmologie);
		
		BtnPsihiatrie = new JRadioButton("Psihiatrie");
		BtnPsihiatrie.setBounds(276, 440, 127, 25);
		frame.getContentPane().add(BtnPsihiatrie);
		
		BtnNeurologie = new JRadioButton("Neurologie");
		BtnNeurologie.setBounds(424, 440, 127, 25);
		frame.getContentPane().add(BtnNeurologie);
		
		BtnEndocrinologie = new JRadioButton("Endocrinologie");
		BtnEndocrinologie.setBounds(239, 387, 127, 25);
		frame.getContentPane().add(BtnEndocrinologie);
		
		BtnAnestezie = new JRadioButton("Anestezie si terapie intensiva");
		BtnAnestezie.setBounds(380, 387, 233, 25);
		frame.getContentPane().add(BtnAnestezie);
		
		
		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea.setEditable(false);
			}
		});
		textArea.setBounds(442, 154, 171, 70);
		frame.getContentPane().add(textArea);
		
		Vector<String> ListItems =new Vector<String>(100);
		ListItems = GetItems();
		Vector<String> CompetenteDoctor = new Vector<String>(10);
		JComboBox<String> comboBox = new JComboBox<String>(ListItems);
		comboBox.setBounds(290, 154, 120, 22);
		comboBox.setEditable(true);
		frame.getContentPane().add(comboBox);
		
		JLabel lblCompetente = new JLabel("Competente");
		lblCompetente.setBounds(192, 157, 148, 16);
		frame.getContentPane().add(lblCompetente);
		
		JLabel lblSpecializari = new JLabel("Specializari");
		lblSpecializari.setBounds(192, 275, 120, 16);
		frame.getContentPane().add(lblSpecializari);
		JTextField editor = (JTextField) comboBox.getEditor().getEditorComponent();
		editor.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String competenta = editor.getText();
						int index = comboBox.getSelectedIndex();
						if (index < 0)
						{
							comboBox.addItem(competenta);
							try {
								Conexiune.cstmt = Conexiune.con.prepareCall("{call Adaugare_Competente(?)}");
								Conexiune.cstmt.setString(1, competenta);
								Conexiune.cstmt.execute();
							}
							catch(SQLException e)
							{
								System.err.println("Nu a mers introducerea item-ului");
							}
						}
						if (isUnique(textArea, competenta) == 0)
						{
							CompetenteDoctor.addElement(competenta);
							textArea.append(competenta + '\n');
						}
						}
				});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Text = textArea.getText();
				 String[] lines = Text.split("\\r?\\n");
				 for (String competenta : lines)
				 {
					 try
					 {
						AddCompetenta(competenta);
					 }
					 catch(SQLException exceptie)
					 {
						 System.err.println("Nu a mers introducerea competentei");
					 }
				 }
				 try 
				 {
					 AddSpecializari();
				 }
				 catch(SQLException exceptie2)
				 {
					 System.err.println("Nu a mers introducerea specializarilor");
				 }
				
			}
		});
		
		JLabel Back = new JLabel("");
		Back.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				AddDoctor window = new AddDoctor();
				window.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		Back.setIcon(new ImageIcon(AdminInfo.class.getResource("/MedicalServices/back.png")));
		Back.setBounds(177, 13, 95, 48);
		frame.getContentPane().add(Back);
		
	}
	

}
