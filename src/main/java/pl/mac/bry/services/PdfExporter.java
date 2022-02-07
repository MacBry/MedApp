package pl.mac.bry.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

public interface PdfExporter {
	void export (HttpServletResponse response) throws DocumentException, IOException;
}
