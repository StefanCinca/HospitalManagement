package ValidateData;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import javax.swing.JTextField;

import java.text.SimpleDateFormat;
public class Valid {

	 public static final int KEY = 0x23;
	 public static String hashString(String input)
	 {
		 char[] inputCharArray = input.toCharArray();
		 for (int i = 0; i < inputCharArray.length; i++)
		 {
			 int numericValue = (char) inputCharArray[i];
			 inputCharArray[i] = (char) (numericValue ^ KEY);
			 
		 }
		 String output = String.valueOf(inputCharArray);
		 return output;
	 }
	 public static boolean isValidEmail(String email) 
	    { 
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
	                            "[a-zA-Z0-9_+&*-]+)*@" + 
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + s
	                            "A-Z]{2,7}$"; 
	                              
	        Pattern pat = Pattern.compile(emailRegex); 
	        if (email == null) 
	            {
	        		return false; 
	            }
	        return pat.matcher(email).matches(); 
	    } 
	 public static boolean isAlpha(String name) {
		    return name.matches("[a-zA-Z]+");
		}
		public static boolean isNotAlpha(String name) {
		    return name.matches("[0-9]+");
		}
		
		public static boolean isValidCNP(String CNP)
		{
			if (Valid.isNotAlpha(CNP) == false)
			{
				System.out.println("CNP-ul trebuie sa contina doar cifre");
				return false;}
			
				if (CNP.length() != 13)
				{
					System.out.println("CNP-ul trebuie sa contina 13 cifre");
					return false;
				}
				return true;
		}
		
		public static boolean isValidNumarTelefon(String numar)
		{
			if (Valid.isNotAlpha(numar) == false || numar.length() != 10)
			{
				System.out.println("Numarul de telefon este incorect");
				return false;
			}
			return true;
		}
		
		public static boolean isValidIban(String Iban)
		{
			if (Iban.length() < 2)
			{
				return false;
			}
			String indicativ = Iban.substring(0,  1);
			if (Valid.isAlpha(indicativ) == false)
			{
				System.out.println("Indicativul tarii trebuie sa aiba 2 litere");
				return false;
			}
			
			if (Iban.length() != 24)
			{
				System.out.println("IBANUL trebuie sa aiba 24 caractere");
				return false;
			}
			return true;
		}
		
		public static boolean isValidSalariu(String salariu)
		{
			if (Valid.isNotAlpha(salariu) == false)
			{
				System.out.println("Salariul trebuie sa fie int");
				return false;
			}
			int numarSalariu = Integer.parseInt(salariu);
			if (numarSalariu < 0)
			{
				System.out.println("salariul trebuie sa fie pozitiv");
				return false;
			}
			return true;
		}
		
		public static boolean isValidOre(String ore)
		{
			if (Valid.isNotAlpha(ore) == false)
			{
				System.out.println("Numarul de ore trebuie sa fie int");
				return false;
			}
			int numarOre = Integer.parseInt(ore);
			if (numarOre < 0)
			{
				System.out.println("Numarul de ore este intreg pozitiv");
				return false;
			}
			return true;
		}
		
		
		public static boolean isValidNume(String nume)
		{
			if (Valid.isAlpha(nume) == false)
			{
				System.out.println("Numele trebuie sa contina doar litere");
				return false;
			}
			return true;
		}
		
		public static boolean isFieldNull(JTextField[] fields)
		{
			for (JTextField field : fields)
			{
				if (field.getText().equals(""))
				{
					System.out.println("Unul din campuri este null");
					return true;
				}
			}
			return false;
			
		}
		
		public static boolean isValidContract(String contract)
		{
			if (Valid.isNotAlpha(contract) == false)
			{
				System.out.println("Contractul trebuie sa fie un numar intreg");
				return false;
			}
			int contractValue = Integer.parseInt(contract);
			if (contractValue < 0)
			{
				System.out.println("Contractul trebuie sa fie pozitiv");
				return false;
			}
			return true;
		}
		

	
	public static boolean isTime(String time)
	{
				if (time.length() != 10)
					return false;
				char[] charArray = new char[20];
				charArray = time.toCharArray();
				if (charArray[4] != '-' || charArray[7] != '-')
				{
					System.out.println("Bad Format1");
					return false;
				}
				if (time.length() != 10)
				{
					System.out.println("Bad Format2");
					return false;
				}
				int month = Integer.parseInt(time.substring(5, 7));
				if (month < 0 || month > 12)
				{
					System.out.println("BAD FORMAT3");
					return false;
				}
				int day = Integer.parseInt(time.substring(8,10));
				if (day < 0 || day > 31)
				{
					System.out.println("BAD FORMAT4");
					return false;
				}
				int year = Integer.parseInt(time.substring(0, 4));
				if (year < 2000 || year > 2018)
				{
					System.out.println(year);
					System.out.println("BAD FORMAT5");
					return false;
				}
				return true;
	}
	
	public static boolean isHour(String hour)
	{
		if (hour.length() != 5)
		{
			return false;
		}
		char[] array = hour.toCharArray();
		if (array[0] == '0')
		{
			if (array[1] < '0' || array[1] > '9')
			{
				return false;
			}
		}
		else
		{
		int ora = Integer.parseInt(hour.substring(0,2));
		if (ora >= 24)
		{
			return false;
		}
	}
		if (array[3] == '0')
		{
			if (array[4] < '0' || array[4] > '9')
			{
				return false;
			}
		}
		else
		{
			int minute = Integer.parseInt(hour.substring(3, 5));
			if (minute > 60)
			{
				return false;
			}
		}
		return true;
	}
		
	
}
