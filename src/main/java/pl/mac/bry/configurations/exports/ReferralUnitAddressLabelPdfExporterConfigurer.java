package pl.mac.bry.configurations.exports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mac.bry.entities.export.PdfReferralUnitAddressTable;

@Configuration
public class ReferralUnitAddressLabelPdfExporterConfigurer {

	@Bean
	public PdfReferralUnitAddressTable pdfRefUnitAddressLableTable() {
		PdfReferralUnitAddressTable table = new PdfReferralUnitAddressTable();
		table.setFirstColumnWidth(130);
		table.setSecondColumnWidth(10);
		table.setNumberOfColumns(2);
		table.setWidthLocked(true);
		return table;
	}
}
