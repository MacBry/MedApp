package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Rectangle;

import pl.mac.bry.entities.PdfLabelRectangle;
import pl.mac.bry.services.PdfRectangleService;



@Service
public class PdfRectangleServiceImpl implements PdfRectangleService {

	private PdfLabelRectangle pdfLabelRectangle;
	
	@Autowired
	private PdfRectangleServiceImpl(PdfLabelRectangle pdfLabelRectangle) {
		super();
		this.pdfLabelRectangle = pdfLabelRectangle;
	}

	@Override
	public Rectangle getRectangle() {
		return new Rectangle(pdfLabelRectangle.getPdfWidth(), pdfLabelRectangle.getPdfHeight());
	}

}
