package pl.mac.bry.configurations.exports;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itextpdf.text.pdf.PdfPTable;

import pl.mac.bry.entities.export.PdfA4Document;
import pl.mac.bry.entities.export.PdfReferralUnitAddressTable;

@Configuration
public class ReferralUnitPdfExporterConfigurer {

	@Bean
	public PdfReferralUnitAddressTable pdfRefUnitAddressLableTable() {
		PdfReferralUnitAddressTable table = new PdfReferralUnitAddressTable();

		table.setFirstColumnWidth(130);
		table.setSecondColumnWidth(10);
		table.setNumberOfColumns(2);
		table.setWidthLocked(true);
		return table;
	}
	
	@Bean
	public PdfA4Document PdfA4Document( ) {
		PdfA4Document document = new PdfA4Document();
		document.setBottomMargin(25);
		document.setTopMargin(25);
		document.setLeftMargin(2);
		document.setRightMargin(2);
		return document;
	}
	
	@Bean
	@Qualifier("REF-UNIT-A4-TABLE")
	public PdfPTable pdfPTable() {
		PdfPTable table = new PdfPTable(10);
		return table;
	}
}
