package pl.mac.bry.services.impl.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
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
public class PdfA4SampleRaportExporter implements PdfExporter<List<List<Sample>>> {

	private PdfDocumentService documentService;
	private PdfTableService tableService;
	private List<List<Sample>> allReffUnitsSamples;

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
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		PdfPCell cell = new PdfPCell();

		for (List<Sample> singleList : allReffUnitsSamples) {
			for (Sample sample : singleList) {
				singleRefUnitPatientsSamples(table, font, sample, dateFormatter);
				summaryForSinleRef(table, bold, singleList);
			}
			document.add(table);
			table.deleteBodyRows();
		}
		document.add(table);
		table.deleteBodyRows();
		document.close();
		pdfContentByte.closeMCBlock(cell);
	}


	private void singleRefUnitPatientsSamples(PdfPTable table, Font font, Sample sample, DateFormat dateFormatter) {
		table.resetColumnCount(7);
		table.addCell(new Phrase("88-" + String.valueOf(sample.getReferralUnit().getAddress().getId()), font));
		table.addCell(new Phrase(sample.getReferralUnit().getAddress().getCity(), font));
		table.addCell(new Phrase(sample.getPatient().getLastName() + " " + sample.getPatient().getFirstName(), font));
		table.addCell(new Phrase(sample.getPatient().getPesel(), font));
		table.addCell(new Phrase(sample.getPatient().getAboGroup().getDescription() + " "
				+ sample.getPatient().getRhdFactor().getDescription(), font));
		table.addCell(new Phrase(dateFormatter.format(sample.getDonationDateTime()), font));
		table.addCell(new Phrase(dateFormatter.format(sample.getRejestrationDateTime()), font));
	}

	private void summaryForSinleRef(PdfPTable table, Font font, List<Sample> singleReffUnitSamples) {
		table.resetColumnCount(1);
		table.addCell(new Phrase("Razem: " + singleReffUnitSamples.size()));
	}

	@Override
	public void setModel(List<List<Sample>> t) {
		this.allReffUnitsSamples = t;

	}

}
