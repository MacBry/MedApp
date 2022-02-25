package pl.mac.bry.services.impl.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.services.export.PdfDocumentService;
import pl.mac.bry.services.export.PdfExporter;
import pl.mac.bry.services.export.PdfTableService;

@Service
@Qualifier("PDF-A4-REF-UNIT-RAPORT-EXPORTER")
public class PdfA4RefUnitRaportExporter implements PdfExporter<List<ReferralUnit>> {

	private PdfDocumentService documentService;
	private PdfTableService pdfTableService;
	private List <ReferralUnit> referralUnits;
	
	
	
	public PdfA4RefUnitRaportExporter(@Qualifier("A4-DOCUMENT")PdfDocumentService documentService, 
			@Qualifier("PDF-A4-REF-RAPORT")PdfTableService pdfTableService) {
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
		
		Font font =  new Font(FontFamily.HELVETICA, 5);
		
		for (ReferralUnit referralUnit : referralUnits) {
							
			PdfPCell cell =  new PdfPCell(new Phrase(String.valueOf(referralUnit.getId()), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getFullName(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getShortName(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getCountry(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getZipCode(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getCity(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getStreetPrefix().getDescription(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getStreet(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getBuildingNumber(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getAddress().getApartmentNumber(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getReferralUnitDetails().getEmail(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getReferralUnitDetails().getPhoneNumber(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getReferralUnitDetails().getResortBookNumber(), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(String.valueOf(referralUnit.getReferralUnitDetails().getNipNumber()), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(String.valueOf(referralUnit.getReferralUnitDetails().getRegonNumber()), font));
			table.addCell(cell);
			cell =  new PdfPCell(new Phrase(referralUnit.getReferralUnitDetails().getDeadline().getDescription(), font));
			
			cell.setFixedHeight(15);
			cell.setBorder(Rectangle.BOX);
			cell.setColspan(2);
			table.addCell(cell);

		}
		document.add(table);
		document.close();
	}

	@Override
	public void setModel(List<ReferralUnit> t) {
		this.referralUnits = t;
		
	}

}
