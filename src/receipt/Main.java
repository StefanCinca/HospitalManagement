package receipt;

public class Main {

	public static void main(String[] args) {
		
		String[] servicii = new String[3];
		int[] preturi = new int[3];
		servicii[0] = "Injectie";
		preturi[0] = 50;
		servicii[1] = "Antibiotic";
		preturi[1] = 20;
		servicii[2] = "Consultatie";
		preturi[2] = 70;
		int numar = 3;
		MakeFile bill = new MakeFile("Alexandru Ioan Cuza", "Cinca Stefan", "Porumbel Oana",
				servicii, preturi, numar, "2010-09-14", "14:23");
		MakeFile bill2 = new MakeFile("Alexandru Ioan Cuza", "Cinca Stefan", "Porumbel Oana",
				servicii, preturi, numar, "2010-09-14", "14:25");
		bill.GetBillAsPdf();
		bill2.GetBillAsPdf();

	}

}
