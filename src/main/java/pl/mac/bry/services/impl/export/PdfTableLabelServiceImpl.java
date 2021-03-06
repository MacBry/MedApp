package pl.mac.bry.services.impl.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import pl.mac.bry.entities.export.PdfLabelTable;
import pl.mac.bry.services.export.PdfTableService;

@Service
@Qualifier("LABEL")
public class PdfTableLabelServiceImpl implements PdfTableService {

	private PdfLabelTable pdfLabelTable;
	
	@Autowired
	public PdfTableLabelServiceImpl(PdfLabelTable pdfLabelTable) {
		super();
		this.pdfLabelTable = pdfLabelTable;
	}



	@Override
	public PdfPTable createPdfPTable() throws DocumentException {
		PdfPTable table = new PdfPTable(this.pdfLabelTable.getNumberOfColumns());
		table.setTotalWidth(new float [] {this.pdfLabelTable.getFirstColumnWidth(),
				this.pdfLabelTable.getSecondColumnWidth() });
		table.setLockedWidth(this.pdfLabelTable.isWidthLocked());
		return table;
	}

}
