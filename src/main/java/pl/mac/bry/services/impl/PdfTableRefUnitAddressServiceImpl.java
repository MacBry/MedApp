package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import pl.mac.bry.services.PdfTableService;

@Service
@Qualifier("REF-ADDRESS-LABEL")
public class PdfTableRefUnitAddressServiceImpl implements PdfTableService {

	@Override
	public PdfPTable createPdfPTable() throws DocumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
