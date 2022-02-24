package pl.mac.bry.services.impl.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;

import pl.mac.bry.entities.export.PdfLabelDocument;
import pl.mac.bry.services.export.PdfDocumentService;
import pl.mac.bry.services.export.PdfRectangleService;

@Service
@Qualifier("LABEL")
public class PdfDocumentServiceLabelImpl implements PdfDocumentService {
	
	private PdfLabelDocument pdfLabelDocument;
	private PdfRectangleService pdfRectangleService;
	
	@Autowired
	public PdfDocumentServiceLabelImpl(PdfLabelDocument pdfLabelDocument,
			@Qualifier("LABEL") PdfRectangleService pdfRectangleService) {
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
