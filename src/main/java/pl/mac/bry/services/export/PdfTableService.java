package pl.mac.bry.services.export;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

public interface PdfTableService {
	PdfPTable createPdfPTable () throws DocumentException;
}
