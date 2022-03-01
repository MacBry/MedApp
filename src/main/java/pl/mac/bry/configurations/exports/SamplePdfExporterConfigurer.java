package pl.mac.bry.configurations.exports;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itextpdf.text.pdf.PdfPTable;

@Configuration
public class SamplePdfExporterConfigurer {
	
	@Bean
	@Qualifier("PF-A4-SAMPLE-RAPORT")
	public PdfPTable pdfTable ( ) {
		return new PdfPTable(7);
	}
}
