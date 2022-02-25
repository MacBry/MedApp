package pl.mac.bry.services.impl.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.services.export.PdfDocumentService;
import pl.mac.bry.services.export.PdfExporter;
import pl.mac.bry.services.export.PdfTableService;

@Service
@Qualifier("REFF-ADDRESS-MULTI-EXPORT")
public class PdfMultiAddressLabelExporter implements PdfExporter<List<ReferralUnit>> {
	
	private PdfDocumentService documentService;
	private PdfTableService pdfTableService;
	private List <ReferralUnit> referralUnits;
	
	

	public PdfMultiAddressLabelExporter(@Qualifier("LABEL")PdfDocumentService documentService, 
			@Qualifier("REF-ADDRESS-LABEL")PdfTableService pdfTableService) {
		super();
		this.documentService = documentService;
		this.pdfTableService = pdfTableService;
	}

	@Override
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = documentService.createDocument();
		PdfPTable table = pdfTableService.createPdfPTable();
		
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
		
		for (ReferralUnit referralUnit : referralUnits) {
			//1 row
			PdfPCell cell = new PdfPCell(new Phrase(referralUnit.getShortName(), new Font(FontFamily.HELVETICA, 8)));
			cell.setFixedHeight(15);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);
			
			//2 row
			cell = new PdfPCell(new Phrase(referralUnit.getAddress().getCountry(), new Font(FontFamily.HELVETICA, 8)));
			cell.setFixedHeight(15);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);
			
			//3 row
			cell = new PdfPCell(new Phrase(referralUnit.getAddress().getZipCode() + " " + referralUnit.getAddress().getCity() , new Font(FontFamily.HELVETICA, 8)));
			cell.setFixedHeight(15);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);
			
			
			//4 row
			cell = new PdfPCell(new Phrase(referralUnit.getAddress().getStreetPrefix().getDescription() + " " + referralUnit.getAddress().getStreet() + " " + referralUnit.getAddress().getBuildingNumber() + "/" + referralUnit.getAddress().getApartmentNumber() , new Font(FontFamily.HELVETICA, 8)));
			cell.setFixedHeight(15);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);
			//document.add(table);
			//document.newPage();	
		}
		document.add(table);
		document.close();

		
		
	}

	@Override
	public void setModel(List<ReferralUnit> t) {
		this.referralUnits = t;
		
	}

}
