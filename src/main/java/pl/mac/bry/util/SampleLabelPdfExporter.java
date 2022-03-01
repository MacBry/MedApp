package pl.mac.bry.util;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.audit4j.core.annotation.Audit;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.Data;
import pl.mac.bry.entities.Sample;

@Data
public class SampleLabelPdfExporter {
	
	private Sample sample;
	private float labelHeight;
	private float labelWidth;
	private float topMargin;
	private float bottomMargin;
	private float leftMargin;
	private float rightMargin;
	private float firstColumnWidth;
	private float secondColumnWidth;
	
	private Rectangle createLabelRectangle() {
		return new Rectangle(this.labelWidth, this.labelHeight); 
	}
	
	private Document createDocument() {
		return new Document(createLabelRectangle(), this.leftMargin, this.rightMargin, this.topMargin, this.bottomMargin);
	}
		
	private  PdfPTable createPdfPTable () throws DocumentException {
		PdfPTable table = new PdfPTable(2); // Table with 2 columns
		table.setTotalWidth(new float[] {this.firstColumnWidth, this.secondColumnWidth});
		table.setLockedWidth(true);
		return table;
	}
	
	@Audit(action = "SampleLabelPdfExporter.export()")
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = createDocument();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		PdfPTable table = createPdfPTable();
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
		
		//first row
		String name = sample.getPatient().getFirstName() + " " + sample.getPatient().getLastName();
		String pesel = sample.getPatient().getPesel();
		String refeNumber =  "88-" + sample.getReferralUnit().getId();
		PdfPCell cell = new PdfPCell(new Phrase(name, new Font(FontFamily.HELVETICA, 8)));
		
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(sample.getPatient().getAboGroup().getDescription(),new Font( FontFamily.HELVETICA, 8)));
		table.addCell(cell);
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		
		//second row
		cell = new PdfPCell(new Phrase(pesel, new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.BOX);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(refeNumber ,new Font(FontFamily.HELVETICA, 8)));
        table.addCell(cell);
        
        Barcode128 code128 = new Barcode128();
        code128.setCode("Z 5160-" + sample.getId());
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(pdfContentByte, null, null);
        cell = new PdfPCell(code128Image, true);
        cell.setBorder(Rectangle.BOX);
        cell.setFixedHeight(25);
        table.addCell(cell);
        
        // third row
        cell = new PdfPCell(new Phrase("Donation date " + LocalDate.now(), new Font(FontFamily.HELVETICA, 6)));
        cell.setBorder(Rectangle.BOX);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        
        document.add(table);
        document.close();
       
	}

}
