package pl.mac.bry.services;

import javax.servlet.http.HttpServletResponse;

public interface PdfExporter {
	void export (HttpServletResponse response);
}
