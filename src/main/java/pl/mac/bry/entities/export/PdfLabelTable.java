package pl.mac.bry.entities.export;

import lombok.Data;

@Data
public class PdfLabelTable {
	private float firstColumnWidth;
	private float secondColumnWidth;
	private int numberOfColumns;
	private boolean isWidthLocked;
}
