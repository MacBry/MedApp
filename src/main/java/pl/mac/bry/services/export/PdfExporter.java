package pl.mac.bry.services.export;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

public interface PdfExporter <T>  {
	void export (HttpServletResponse response) throws DocumentException, IOException;
	void setModel(T t);
}
