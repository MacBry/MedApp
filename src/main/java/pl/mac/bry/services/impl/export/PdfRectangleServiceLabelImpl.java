package pl.mac.bry.services.impl.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Rectangle;

import pl.mac.bry.entities.export.PdfLabelRectangle;
import pl.mac.bry.services.export.PdfRectangleService;



@Service
@Qualifier("LABEL")
public class PdfRectangleServiceLabelImpl implements PdfRectangleService {

	private PdfLabelRectangle pdfLabelRectangle;
	
	@Autowired
	private PdfRectangleServiceLabelImpl(PdfLabelRectangle pdfLabelRectangle) {
		super();
		this.pdfLabelRectangle = pdfLabelRectangle;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(pdfLabelRectangle.getPdfWidth(), pdfLabelRectangle.getPdfHeight());
	}

}
