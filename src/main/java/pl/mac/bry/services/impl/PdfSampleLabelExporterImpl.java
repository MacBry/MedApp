package pl.mac.bry.services.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.services.PdfDocumentService;
import pl.mac.bry.services.PdfExporter;
import pl.mac.bry.services.PdfRectangleService;
import pl.mac.bry.services.PdfTableService;

@Service
public class PdfSampleLabelExporterImpl implements PdfExporter {
	
	PdfDocumentService documentService;
	PdfRectangleService rectangleService;
	PdfTableService tableService;
	
	@Autowired
	public PdfSampleLabelExporterImpl(PdfDocumentService documentService,
			PdfRectangleService rectangleService,
			PdfTableService tableService) {
		super();
		this.documentService = documentService;
		this.rectangleService = rectangleService;
		this.tableService = tableService;
	}


	@Override
	public void export(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
