package pl.mac.bry.services.impl.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
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
import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.export.PdfDocumentService;
import pl.mac.bry.services.export.PdfExporter;
import pl.mac.bry.services.export.PdfTableService;

@Service
@Qualifier("PDF-A4-SAMPLE-RAPORT-EXPORTER")
public class PdfA4SampleRaportExporter implements PdfExporter<List<Sample>> {

	private PdfDocumentService documentService;
	private PdfTableService tableService;
	private List<Sample> allReffUnitsSamples;

	public PdfA4SampleRaportExporter(@Qualifier("A4-DOCUMENT") PdfDocumentService documentService,
			@Qualifier("PF-A4-SAMPLE-RAPORT") PdfTableService tableService) {
		super();
		this.documentService = documentService;
		this.tableService = tableService;
	}

	@Override
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = documentService.createDocument();
		PdfPTable table = tableService.createPdfPTable();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

		Font font = new Font(FontFamily.HELVETICA, 5);
		Font bold = new Font(FontFamily.HELVETICA, 5, Font.BOLD);
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss:ms");

		PdfPCell cell = new PdfPCell();
		int i = 0;
		
		firstRowColumnNames(table, bold, cell);
		table.deleteBodyRows();
		
		if(!allReffUnitsSamples.isEmpty()) {
			for (Iterator<Sample> iterator = allReffUnitsSamples.iterator(); iterator.hasNext();) {
				i=+1;
				Sample sample = (Sample) iterator.next();
				sampleColumnData(table, font, sample, dateFormatter, i);
				
			}
		} else {
			document.add(new Phrase("", bold));
		}
		
		document.add(table);
		document.close();
		pdfContentByte.reset();
		pdfWriter.close();
		table.deleteBodyRows();

	}

	

	private void sampleColumnData(PdfPTable table, Font font, Sample sample, DateFormat dateFormatter, int i) {
		table.resetColumnCount(8);
		table.addCell(new Phrase(String.valueOf(i), font));
		table.addCell(new Phrase("88-" + String.valueOf(sample.getReferralUnit().getId()), font));
		table.addCell(new Phrase(sample.getReferralUnit().getAddress().getCity(), font));
		table.addCell(new Phrase(sample.getPatient().getLastName() + " " + sample.getPatient().getFirstName(), font));
		table.addCell(new Phrase(sample.getPatient().getPesel(), font));
		table.addCell(new Phrase(sample.getPatient().getAboGroup().getDescription() + " "
				+ sample.getPatient().getRhdFactor().getDescription(), font));
		table.addCell(new Phrase(sample.getDonationDateTime().toString(), font));
		table.addCell(new Phrase(sample.getRejestrationDateTime().toString(), font));
	}

	private void firstRowColumnNames(PdfPTable table, Font font, PdfPCell cell) {
		cell = new PdfPCell(new Phrase("Lp", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Referral Unit Number", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("City", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Name", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("PESEL", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Blood group", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Donation date", font));
		cellFormatForFirstRow(table, cell);
		cell = new PdfPCell(new Phrase("Rejestratiron date", font));
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

	@Override
	public void setModel(List<Sample> t) {
		this.allReffUnitsSamples = t;

	}

}
