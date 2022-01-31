package pl.mac.bry.util;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

import pl.mac.bry.entities.Patient;
import pl.mac.bry.entities.Sample;

public class test {

	public static void main(String[] args) throws IOException, DocumentException {
		Sample sample = new Sample();
		Patient patient = new Patient();
		patient.setLastName("TestoweNazwisko");
		sample.setPatient(patient);
		
		PdfSampleLabel label = new PdfSampleLabel();
		
		label.setBottomMargin(1);
		label.setTopMargin(1);
		label.setFontFamily(FontFamily.TIMES_ROMAN);
		label.setFontSize(10);
		label.setPageSizeX(500);
		label.setPageSizeY(250);
		label.setLeftMargin(1);
		label.setRightMargin(1);
		label.setSample(sample);
		
		label.createPdf();
	}

}
