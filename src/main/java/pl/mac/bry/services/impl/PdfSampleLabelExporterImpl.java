package pl.mac.bry.services.impl;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.PdfDocumentService;
import pl.mac.bry.services.PdfExporter;
import pl.mac.bry.services.PdfTableService;

@Service
public class PdfSampleLabelExporterImpl implements PdfExporter {

	private PdfDocumentService documentService;
	private PdfTableService tableService;
	private Sample sample;

	@Autowired
	public PdfSampleLabelExporterImpl(@Qualifier("LABEL") PdfDocumentService documentService,
			@Qualifier("LABEL") PdfTableService tableService) {
		super();
		this.documentService = documentService;
		this.tableService = tableService;
	}

	@Override
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = documentService.createDocument();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		PdfPTable table = tableService.createPdfPTable();
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

		// first row
		PdfPCell cell = new PdfPCell(new Phrase("ORS SAMPLE", new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);

		// second row
		String secondRowString = sample.getPatient().getFirstName() + " " + sample.getPatient().getLastName() + " "
				+ sample.getPatient().getPesel();
		cell = new PdfPCell(new Phrase(secondRowString, new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.BOX);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("TO DO", new Font(FontFamily.HELVETICA, 8)));
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
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Donation date " + LocalDate.now(), new Font(FontFamily.HELVETICA, 6)));
		cell.setBorder(Rectangle.BOX);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		document.add(table);
		document.close();
	}

}
