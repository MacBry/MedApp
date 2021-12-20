package pl.mac.bry.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

public interface PdfFrameService {
	
	Document createDocument (PageSize pageSize, 
			float leftMargin,
			float rightMargin,
			float topMargin,
			float bottomMargin);
	
}
