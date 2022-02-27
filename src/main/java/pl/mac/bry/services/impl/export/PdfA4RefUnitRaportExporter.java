package pl.mac.bry.services.impl.export;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Font.FontStyle;
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
	private List<ReferralUnit> referralUnits;

	public PdfA4RefUnitRaportExporter(@Qualifier("A4-DOCUMENT") PdfDocumentService documentService,
			@Qualifier("PDF-A4-REF-RAPORT") PdfTableService pdfTableService) {
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

		Font font = new Font(FontFamily.HELVETICA, 5);
		Font boldFont = new Font(FontFamily.HELVETICA, 5, Font.BOLD);
		PdfPCell cell = new PdfPCell();
		
		firstRowcolumnNames(table, font, cell);
		table.deleteBodyRows();
		
		for (Iterator<ReferralUnit> iterator = referralUnits.iterator(); iterator.hasNext();) {
			ReferralUnit referralUnit = (ReferralUnit) iterator.next();
			referralColumnData(table, font, referralUnit);
		}
		
		//for (ReferralUnit referralUnit : referralUnits) {
		//		referralColumnData(table, font, referralUnit);	
		//}
		document.add(table);
		document.close();
		pdfContentByte.reset();
		pdfWriter.close();
		table.deleteBodyRows();
		
	}
	
	private void firstRowcolumnNames(PdfPTable table, Font boldFont, PdfPCell cell) {
		System.out.println("0");
		cell = new PdfPCell(new Phrase("Lp", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Full Name", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Short Name", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Country", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Zip Code", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("City", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Street Prefix", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Street", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Building Number", boldFont));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Apartment Number", boldFont));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);
		table.setHeaderRows(1);
	}

	protected void cellFormatForFirstRow(PdfPTable table, PdfPCell cell) {
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);
	}
	

	private void referralColumnData(PdfPTable table, Font font, ReferralUnit referralUnit) {

		table.addCell(new Phrase(String.valueOf(referralUnit.getId()), font));
		table.addCell(new Phrase(referralUnit.getFullName(), font));
		table.addCell(new Phrase(referralUnit.getShortName(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getCountry(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getZipCode(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getCity(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getStreetPrefix().getDescription(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getStreet(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getBuildingNumber(), font));
		table.addCell(new Phrase(referralUnit.getAddress().getApartmentNumber(), font));

	}


	@Override
	public void setModel(List<ReferralUnit> t) {
		this.referralUnits = t;

	}

}
