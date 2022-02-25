package pl.mac.bry.services.impl.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import pl.mac.bry.services.export.PdfTableService;

@Service
@Qualifier("PDF-A4-REF-RAPORT")
public class PdfTableRefUnitA4RaportServiceImpl implements PdfTableService {
	
	private PdfPTable table; 
	
	@Autowired
	public PdfTableRefUnitA4RaportServiceImpl(@Qualifier("REF-UNIT-A4-TABLE") PdfPTable table) {
		super();
		this.table = table;
	}



	@Override
	public PdfPTable createPdfPTable() throws DocumentException {
		return this.table;
	}

}
