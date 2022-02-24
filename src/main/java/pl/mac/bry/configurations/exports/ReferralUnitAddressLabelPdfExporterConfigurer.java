package pl.mac.bry.configurations.exports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mac.bry.entities.PdfReferralUnitAddressTable;

@Configuration
public class ReferralUnitAddressLabelPdfExporterConfigurer {

	@Bean
	public PdfReferralUnitAddressTable pdfRefUnitAddressLableTable() {
		PdfReferralUnitAddressTable table = new PdfReferralUnitAddressTable();
		table.setFirstColumnWidth(140);
		table.setSecondColumnWidth(0);
		table.setNumberOfColumns(2);
		table.setWidthLocked(true);
		return table;
	}
}
