package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Parameterization
{
	public static String getExcelData(String name,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream file=new FileInputStream("");
		String value=WorkbookFactory.create(file).getSheet("name").getRow(row).getCell(cell).toString();
		return value;
	}

	
}
