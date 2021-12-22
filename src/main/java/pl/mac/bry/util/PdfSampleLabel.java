package pl.mac.bry.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.management.loading.PrivateClassLoader;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.Builder;
import lombok.Data;
import pl.mac.bry.entities.Sample;

@Builder
@Data
public class PdfSampleLabel {
	
	
	private String dest = "/MedApp/src/main/resources/labels" + createNameString();
	
	private float leftMargin;
	
	private float rightMargin;
	
	private float topMargin;
	
	private float bottomMargin;
	
	private float pageSizeX;
	
	private float pageSizeY;
	
	private FontFamily fontFamily;
	
	private float fontSize;
	
	private Sample sample;
	
	public Rectangle createRectangle() {
		return new Rectangle(pageSizeX, pageSizeY);
	}
	
	public Font createFont() {
		return new Font(fontFamily, fontSize);
	}
	
	public Document createDocument() {
		return new Document(createRectangle()
				,leftMargin
				,rightMargin
				,topMargin,
				bottomMargin);
	}
	
	private String createNameString() {
		return "/"+ sample.getPatient().getLastName() + LocalDateTime.now().toString() + ".pdf";
	}
	
	public void createPdf(String dest) throws IOException, DocumentException {
        Rectangle small = createRectangle();
        Font smallfont = createFont();
        Document document = createDocument();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(new float[]{ 1603, 120 });
        table.setLockedWidth(true);
        PdfContentByte cb = writer.getDirectContent();
        // first row
        PdfPCell cell = new PdfPCell(new Phrase("Some text here"));
        cell.setFixedHeight(30);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setColspan(2);
        table.addCell(cell);
        // second row
        cell = new PdfPCell(new Phrase("Some more text", smallfont));
        cell.setFixedHeight(30);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
        Barcode128 code128 = new Barcode128();
        code128.setCode("14785236987541");
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        cell = new PdfPCell(code128Image, true);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setFixedHeight(30);
        table.addCell(cell);
        // third row
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("and something else here", smallfont));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        document.add(table);
        document.close();
    }
}
