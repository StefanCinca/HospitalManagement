package receipt;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;



import java.io.IOException;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class MakeFile {
	private static int nrBon = 1;
	private String numePoliclinica = null;
	private String numeDoctor = null;
	private String numeReceptioner = null;
	private String[] servicii;
	private int[] preturi;
	private int numar;
	private String data;
	private String ora;

	
	private static final PDFont NORMAL_FONT = PDType1Font.COURIER;
	private static final PDFont BOLD = PDType1Font.COURIER_BOLD;
	private static final int NORMAL_FONT_SIZE = 10;
	
	
	public MakeFile(String numePoliclinica, String numeDoctor, String numeReceptioner, String servicii[],
			int preturi[], int numar, String data,String  ora)
	{
		super();
		this.numePoliclinica = numePoliclinica;
		this.numeDoctor = numeDoctor;
		this.numeReceptioner = numeReceptioner;
		this.servicii = new String[10];
		this.preturi = new int[10];
		this.servicii = servicii;
		this.preturi = preturi;
		this.numar = numar;
		this.data = data;
		this.ora = ora;
		nrBon++;
	}
	
	public  void GetBillAsPdf()
	{
		try
		{
			String ora1 = ora.substring(0, 2);
			String ora2 = ora.substring(3, 5);
			String filename = "C:\\Users\\Stefan\\eclipse-workspace\\MedicalServicesProject\\Bonuri Fiscale//"
					+ "BonFiscal" + numeReceptioner + ora1 + ora2 + ".pdf";
			PDDocument doc = new PDDocument();
			PDPage page = new PDPage();
			doc.addPage(page);
			PDPageContentStream content = new PDPageContentStream(doc, page);
			
			generateBillHeader(page, content);
			generateBillInfoText(page, content);
			
			content.close();
			
			doc.save(filename);
			doc.close();
			System.out.println("your file created in : "+ System.getProperty("user.dir"));
			nrBon++;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	 private void generateBillHeader(PDPage firstPage, PDPageContentStream contentStream)
	            throws IOException {
	        
	        PDFont currentFont;
	        int currentFontSize;
	        String headerLine1 = "Policlinica " + numePoliclinica;
	        String headerLine2 = "Bon Fiscal";
	        contentStream.setLeading(10);
	        currentFont = BOLD;
	        currentFontSize = 14;
	        contentStream.setFont(currentFont, currentFontSize);
	        contentStream.beginText();
	        float offsetX = getCenteredTextXPos(firstPage, headerLine1, currentFont, currentFontSize);
	        contentStream.newLineAtOffset(offsetX, 750f);
	        contentStream.showText(headerLine1);
	        currentFont = PDType1Font.COURIER_BOLD;
	        currentFontSize = 12;
	        contentStream.setFont(currentFont, currentFontSize);
	        float offsetX2 = getCenteredTextXPos(firstPage, headerLine2, currentFont, currentFontSize);
	        contentStream.newLineAtOffset(-offsetX + offsetX2, -5f);
	        contentStream.newLine();
	        contentStream.showText(headerLine2);
	        contentStream.endText();
	    }
	 private void generateBillInfoText(PDPage firstPage, PDPageContentStream contentStream)
	            throws IOException {
	       

	        contentStream.beginText();

	      
	        contentStream.newLineAtOffset(80f, 710f);
	        contentStream.setLeading(10);
	        contentStream.setFont(BOLD, NORMAL_FONT_SIZE);
	        contentStream.showText("Numele Doctorului/Asistentului: ");
	        contentStream.setFont(NORMAL_FONT, NORMAL_FONT_SIZE);
	        contentStream.showText(numeDoctor);

	      
	        contentStream.newLine();
	        contentStream.setFont(BOLD, NORMAL_FONT_SIZE);
	        contentStream.showText("Numele Receptionerului: ");
	        contentStream.setFont(NORMAL_FONT, NORMAL_FONT_SIZE);
	        contentStream.showText(numeReceptioner);
	        
	        contentStream.newLine();
	        contentStream.showText("---------------------------------------------------------");
	        
	        contentStream.newLine();
	        contentStream.setFont(BOLD, NORMAL_FONT_SIZE);
	        contentStream.showText("Servicii");
	        contentStream.newLineAtOffset(+400f, 0);
	        contentStream.showText("Pret");
	        contentStream.newLineAtOffset(-400f, 0);
	        int total = 0;
	        for (int i = 0; i < numar; i++)
	        {
	        	contentStream.newLine();
	        	contentStream.setFont(BOLD,  NORMAL_FONT_SIZE);
	        	contentStream.showText(servicii[i]);
	        	contentStream.newLineAtOffset(+400f, 0);
	        	contentStream.showText(String.valueOf(preturi[i]));
	        	contentStream.newLineAtOffset(-400f, 0);
	        	total += preturi[i];
	        }
	        contentStream.newLine();
	        contentStream.showText("---------------------------------------------------------");
	        contentStream.newLine();
	        contentStream.setFont(BOLD, NORMAL_FONT_SIZE);
	        contentStream.showText("Total: " + total);
	        
	        contentStream.newLine();
	        contentStream.showText(data +"         " +  ora);
	  
	        contentStream.endText();

	    }
	 private float getCenteredTextXPos(PDPage page, String text, PDFont font, int fontSize)
	            throws IOException {
	        float textWidth = getStringWidth(text, font, fontSize);
	        PDRectangle pageSize = page.getMediaBox();
	        float pageCenterX = pageSize.getWidth() / 2F;
	        float textX = pageCenterX - textWidth/2F;
	        return textX;
	    }
	 private float getStringWidth(String text, PDFont font, int fontSize) throws IOException {
	        return font.getStringWidth(text) * fontSize / 1000F;
	    }
	
}
