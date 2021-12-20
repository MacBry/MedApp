package pl.mac.bry.entities;

import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PdfFrame {
	
	private float leftMargin;
	
	private float rightMargin;
	
	private float topMargin;
	
	private float bottomMargin;
	
	private PageSize pageSize;
	
	private Image image;
}
