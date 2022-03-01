package pl.mac.bry.services.impl.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

		PdfPCell cell = new PdfPCell();
		
		for (Sample sample : allReffUnitsSamples) {
			for(Long refIndex : preparationOfList(allReffUnitsSamples)) {
				int i = 0;
				if(Long.valueOf(sample.getReferralUnit().getId()).equals(refIndex)) {
					i = i +1;
					singleRefUnitPatientsSamples(table, font, sample, dateFormatter);
					document.add(table);
				}
				//summaryForSinleRef(table, bold, i);
			}
			table.deleteBodyRows();
		}
		
		
		document.add(table);
		table.deleteBodyRows();
		document.close();
		pdfContentByte.closeMCBlock(cell);
	}

	private ArrayList<Long> preparationOfList(List<Sample>allReffUnitsSamples) {
		ArrayList<Long> indexList = new ArrayList<> ();

		for (Sample sample : allReffUnitsSamples) {
			indexList.add(sample.getReferralUnit().getId());
		}
		
		LinkedHashSet<Long> hashSet = new LinkedHashSet<>(indexList);
		return  new ArrayList<>(hashSet);
	}

	private void singleRefUnitPatientsSamples(PdfPTable table, Font font, Sample sample, DateFormat dateFormatter) {
		table.resetColumnCount(5);
		table.addCell(new Phrase("88-" + String.valueOf(sample.getReferralUnit().getId()), font));
		table.addCell(new Phrase(sample.getReferralUnit().getAddress().getCity(), font));
		table.addCell(new Phrase(sample.getPatient().getLastName() + " " + sample.getPatient().getFirstName(), font));
		table.addCell(new Phrase(sample.getPatient().getPesel(), font));
		table.addCell(new Phrase(sample.getPatient().getAboGroup().getDescription() + " "
				+ sample.getPatient().getRhdFactor().getDescription(), font));
		//table.addCell(new Phrase(dateFormatter.format(sample.getDonationDateTime()).toString(), font));
		//table.addCell(new Phrase(dateFormatter.format(sample.getRejestrationDateTime().toString()), font));
	}

	private void summaryForSinleRef(PdfPTable table, Font font, int count) {
		table.resetColumnCount(1);
		table.addCell(new Phrase("Summary:" + count, font));
	}

	@Override
	public void setModel(List<Sample> t) {
		this.allReffUnitsSamples = t;

	}

}
