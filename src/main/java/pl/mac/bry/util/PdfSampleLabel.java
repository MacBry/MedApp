package pl.mac.bry.util;

import java.time.LocalDateTime;

import javax.management.loading.PrivateClassLoader;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import lombok.Builder;
import lombok.Data;
import pl.mac.bry.entities.Sample;

@Builder
@Data
public class PdfSampleLabel {
	
	
	private String dest = "/MedApp/src/main/resources/labels" + createNameString();
	
	private float leftMargin;
	
	private float rightMargin;
	
	private float topMargin;
	
	private float bottomMargin;
	
	private float pageSizeX;
	
	private float pageSizeY;
	
	private FontFamily fontFamily;
	
	private float fontSize;
	
	private Sample sample;
	
	public Rectangle createRectangle() {
		return new Rectangle(pageSizeX, pageSizeY);
	}
	
	public Font createFont(FontFamily fontFamily, float fontSize) {
		return new Font(fontFamily, fontSize);
	}
	
	public Document createDocument() {
		return new Document(createRectangle()
				,leftMargin
				,rightMargin
				,topMargin,
				bottomMargin);
	}
	
	private String createNameString() {
		return "/"+ sample.getPatient().getLastName() + LocalDateTime.now().toString() + ".pdf";
	}
}
