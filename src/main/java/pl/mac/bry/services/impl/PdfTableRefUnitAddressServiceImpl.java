package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import pl.mac.bry.entities.PdfReferralUnitAddressTable;
import pl.mac.bry.services.PdfTableService;

@Service
@Qualifier("REF-ADDRESS-LABEL")
public class PdfTableRefUnitAddressServiceImpl implements PdfTableService {
	
	private PdfReferralUnitAddressTable referralUnitAddresLabelTable;
	
	@Autowired
	public PdfTableRefUnitAddressServiceImpl(PdfReferralUnitAddressTable referralUnitAddresLabelTable) {
		super();
		this.referralUnitAddresLabelTable = referralUnitAddresLabelTable;
	}



	@Override
	public PdfPTable createPdfPTable() throws DocumentException {
		PdfPTable table = new PdfPTable(this.referralUnitAddresLabelTable.getNumberOfColumns());
		table.setTotalWidth(new float [] {this.referralUnitAddresLabelTable.getFirstColumnWidth(), 
				this.referralUnitAddresLabelTable.getSecondColumnWidth()});
		table.setLockedWidth(this.referralUnitAddresLabelTable.isWidthLocked());
		return table;
	}

}
