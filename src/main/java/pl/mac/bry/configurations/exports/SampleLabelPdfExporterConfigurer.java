package pl.mac.bry.configurations.exports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mac.bry.util.SampleLabelPdfExporter;

@Configuration
public class SampleLabelPdfExporterConfigurer {

	@Bean
	public SampleLabelPdfExporter sampleLabelPdfExporter() {
		SampleLabelPdfExporter exporter = new SampleLabelPdfExporter();
		exporter.setBottomMargin(2);
		exporter.setTopMargin(2);
		exporter.setLeftMargin(2);
		exporter.setRightMargin(2);
		exporter.setLabelHeight(144);
		exporter.setLabelWidth(72);
		exporter.setFirstColumnWidth(100);
		exporter.setSecondColumnWidth(40);
		return exporter;
	}
}
