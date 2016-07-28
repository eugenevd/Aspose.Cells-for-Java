package com.aspose.cells.examples.worksheets.management;

import com.aspose.cells.*;
import com.aspose.cells.examples.Utils;

import java.io.FileInputStream;

public class AccessingWorksheetsusingSheetName {

	public static void main(String[] args) throws Exception {
		// ExStart:1
		// The path to the documents directory.
		String dataDir = Utils.getDataDir(AccessingWorksheetsusingSheetName.class);
		String filePath = dataDir + "book1.xlsx";

		// Creating a file stream containing the Excel file to be opened
		FileInputStream fstream = new FileInputStream(filePath);

		// Instantiating a Workbook object with the stream
		Workbook workbook = new Workbook(fstream);

		// Accessing a worksheet using its sheet name
		Worksheet worksheet = workbook.getWorksheets().get("Sheet1");
		Cell cell = worksheet.getCells().get(0, 0);

		// Print Message
		System.out.println(cell.getValue());
		// ExEnd:1
	}
}
