package pl.mac.bry.services.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.services.PdfDocumentService;
import pl.mac.bry.services.PdfExporter;
import pl.mac.bry.services.PdfTableService;

@Service
public class PdfRefUnitAddressLabelExporterImpl implements PdfExporter {
	
	private PdfDocumentService documentService;
	private PdfTableService pdfTableService;
	private ReferralUnit referralUnit;
	
	@Autowired
	public PdfRefUnitAddressLabelExporterImpl(@Qualifier("LABEL")PdfDocumentService documentService, PdfTableService pdfTableService,
			ReferralUnit referralUnit) {
		super();
		this.documentService = documentService;
		this.pdfTableService = pdfTableService;
		this.referralUnit = referralUnit;
	}



	@Override
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		// TODO Auto-generated method stub

	}

}
