package pl.mac.bry.configurations.exports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.mac.bry.entities.export.PdfLabelDocument;
import pl.mac.bry.entities.export.PdfLabelRectangle;
import pl.mac.bry.entities.export.PdfLabelTable;
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
		exporter.setLabelHeight(72);
		exporter.setLabelWidth(144);
		exporter.setFirstColumnWidth(100);
		exporter.setSecondColumnWidth(40);
		return exporter;
	}
	
	@Bean
	public PdfLabelDocument pdfLabelDocument() {
		PdfLabelDocument document = new PdfLabelDocument();
		document.setBottomMargin(2);
		document.setTopMargin(2);
		document.setLeftMargin(2);
		document.setRightMargin(2);
		return document;
	}
	
	@Bean
	public PdfLabelRectangle pdfLabelRectangle() {
		PdfLabelRectangle rectangle = new PdfLabelRectangle();
		rectangle.setPdfHeight(72);
		rectangle.setPdfWidth(144);
		return rectangle;
	}
	
	@Bean
	public PdfLabelTable pdfLabelTable() {
		PdfLabelTable table = new PdfLabelTable();
		table.setFirstColumnWidth(100);
		table.setSecondColumnWidth(40);
		table.setNumberOfColumns(2);
		table.setWidthLocked(true);
		return table;
	}
}
