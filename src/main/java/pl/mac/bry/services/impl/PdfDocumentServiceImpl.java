package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import pl.mac.bry.entities.PdfLabelDocument;
import pl.mac.bry.services.PdfDocumentService;
import pl.mac.bry.services.PdfRectangleService;

@Service
public class PdfDocumentServiceImpl implements PdfDocumentService {
	
	private PdfLabelDocument pdfLabelDocument;
	private PdfRectangleService pdfRectangleService;
	
	@Autowired
	public PdfDocumentServiceImpl(pl.mac.bry.entities.PdfLabelDocument pdfLabelDocument,
			PdfRectangleService pdfRectangleService) {
		super();
		this.pdfLabelDocument = pdfLabelDocument;
		this.pdfRectangleService = pdfRectangleService;
	}



	@Override
	public Document createDocument() {
		return new Document(pdfRectangleService.getRectangle(),
				pdfLabelDocument.getBottomMargin(),
				pdfLabelDocument.getTopMargin(),
				pdfLabelDocument.getLeftMargin(),
				pdfLabelDocument.getRightMargin());
	}

}
