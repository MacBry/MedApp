package pl.mac.bry.services.impl.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

import pl.mac.bry.entities.export.PdfA4Document;
import pl.mac.bry.services.export.PdfDocumentService;

@Service
@Qualifier("A4-DOCUMENT")
public class RaportPdfDocumentServiceImpl implements PdfDocumentService {
	
	private PdfA4Document a4document;
	
	
	@Autowired
	public RaportPdfDocumentServiceImpl(PdfA4Document a4document) {
		super();
		this.a4document = a4document;
	}

	@Override
	public Document createDocument() {
		Document document = new Document(PageSize.A4.rotate()); 
		document.setMargins(a4document.getLeftMargin(),
				a4document.getRightMargin(),
				a4document.getTopMargin(),
				a4document.getBottomMargin());
		return document;
	}

}
