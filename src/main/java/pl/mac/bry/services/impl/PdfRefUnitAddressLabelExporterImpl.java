package pl.mac.bry.services.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Table;
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
import pl.mac.bry.services.PdfDocumentService;
import pl.mac.bry.services.PdfExporter;
import pl.mac.bry.services.PdfTableService;

@Service
@Qualifier("REF-ADDRESS-LABEL")
public class PdfRefUnitAddressLabelExporterImpl implements PdfExporter {
	
	private PdfDocumentService documentService;
	private PdfTableService pdfTableService;
	private ReferralUnit referralUnit;
	
	@Autowired
	public PdfRefUnitAddressLabelExporterImpl(@Qualifier("LABEL")PdfDocumentService documentService, 
			@Qualifier("REF-ADDRESS-LABEL")PdfTableService pdfTableService) {
		super();
		this.documentService = documentService;
		this.pdfTableService = pdfTableService;
	}
	

	public void setReferralUnit(ReferralUnit referralUnit) {
		this.referralUnit = referralUnit;
	}



	@Override
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = documentService.createDocument();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		PdfPTable table = pdfTableService.createPdfPTable();
		PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
		
		//1 row
		PdfPCell cell = new PdfPCell(new Phrase(referralUnit.getShortName(), new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);
		
		//2 row
		cell = new PdfPCell(new Phrase(referralUnit.getAddress().getCountry(), new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);
		
		//3 row
		cell = new PdfPCell(new Phrase(referralUnit.getAddress().getZipCode() + " " + referralUnit.getAddress().getCity() , new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);
		
		
		//4 row
		cell = new PdfPCell(new Phrase(referralUnit.getAddress().getStreetPrefix() + " " + referralUnit.getAddress().getStreet() + " " + referralUnit.getAddress().getBuildingNumber() + "/" + referralUnit.getAddress().getApartmentNumber() , new Font(FontFamily.HELVETICA, 8)));
		cell.setFixedHeight(15);
		cell.setBorder(Rectangle.BOX);
		cell.setColspan(2);
		table.addCell(cell);
		
		document.add(table);
		document.close();
	}

}
