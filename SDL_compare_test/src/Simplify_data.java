import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

public class Simplify_data {
	private String newfilepath = null;
	
	public String getNewfilepath() {
		System.out.println(newfilepath);
		return newfilepath;
	}

	public void setNewfilepath(String newfilepath) {
		this.newfilepath = newfilepath;
	}
	
	public Simplify_data(String path,String outpath) throws IOException, EncryptedDocumentException, InvalidFormatException {
		String newpath = null;
		File f = new File(path);
		String filename = f.getName();//get file name from string path
		System.out.println(filename);
		Workbook wb = WorkbookFactory.create(f);
		System.out.println(wb.getSpreadsheetVersion());

		DataFormatter formatter = new DataFormatter();
		int i = 1;
		int numberOfSheets = wb.getNumberOfSheets();
		System.out.println("numberOfSheets"+numberOfSheets);

		for (Sheet sheet : wb) {
			//System.out.println(sheet.getSheetName().equals("DDList Data"));
			//System.out.println("DDList Data" == sheet.getSheetName());

			if (sheet.getSheetName().equals("DDList Data") || sheet.getSheetName().equals("DDList")) {
				//++
				//鏁版嵁澶勭悊姝ｅ垯鍖归厤driver鐗堟湰鍙�
				System.out.println("Sheet " + i + " of " + numberOfSheets + ": " + sheet.getSheetName());
				for (Row row : sheet) {
					System.out.println("\tRow " + row.getRowNum());
					for (Cell cell : row) {
						String Temp = cell.getStringCellValue();
						// Testing code
						// System.out.println(Temp);
						Pattern p = Pattern.compile("Driver.*?ver:(.*?)Build.*?ID",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
						Matcher m = p.matcher(Temp);
						// Testing code
						// System.out.println(m.find());
						while (m.find()) {
							// Testing code
							// System.out.println("******************************");
							// System.out.println(m.group(1));
							cell.setCellValue(m.group(1).replaceAll("\\s*", ""));
						}
						/*System.out.println(
								"\t\t" + cell.getAddress().formatAsString() + ": " + formatter.formatCellValue(cell));*/
					}
				}
				//--
				newpath = outpath.concat(filename);
				setNewfilepath(newpath);
				System.out.println(newpath);
				OutputStream fos = new FileOutputStream(newpath);
				wb.write(fos);
				fos.close();
			}
			else {
				System.out.println(f.getName() + " in path:" + f.getParentFile() + " is not a DDList file.");
			}
		}
		System.out.println("dump");
	}
	
	public static String [] get_dbhead_for_sqlite(String path) throws EncryptedDocumentException, InvalidFormatException, IOException {
		System.out.println("Test for 'get_dbhead_for_sqlite' start");
		File f = new File(path);
		String [] dbhead = null;
		Workbook wb = WorkbookFactory.create(f);
		System.out.println(wb.getSpreadsheetVersion());
		//int numberOfSheets = wb.getNumberOfSheets();
		int i = 0;
		for (Sheet sheet : wb) {
			int cellnumber = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println(cellnumber);
			dbhead = new String[cellnumber];
			Row rowdata = sheet.getRow(0);
			for (Cell cell : rowdata) {
				dbhead[i] = cell.getStringCellValue();
				System.out.println(dbhead[i] + "\t");
				i+=1;
			}
		}
		System.out.println("Test for 'get_dbhead_for_sqlite' end");
		return dbhead;
		
	}

	public static String[][] get_dbdata_for_sqlite(String path) throws EncryptedDocumentException, InvalidFormatException, IOException {
		System.out.println("Test for 'get_dbdata_for_sqlite' start");
		File f = new File(path);
		System.out.println(path);
		String [][] dbdata = null;
		Workbook wb = WorkbookFactory.create(f);
		System.out.println( wb.getSpreadsheetVersion());
		//int numberOfSheets = wb.getNumberOfSheets();
		for (Sheet sheet : wb) {
			int cellnumber = sheet.getRow(0).getPhysicalNumberOfCells();
			int rownumber =sheet.getPhysicalNumberOfRows() ;
			dbdata = new String [rownumber][cellnumber];
			System.out.println(cellnumber + "\t" + rownumber);
			for (Row row : sheet) {
				for (Cell cell : row) {
					System.out.println(cell.getRowIndex());
					 int t = cell.getRowIndex();
					if (t > 0) {
						System.out.println("row_number is :"+cell.getRowIndex());
						System.out.println("cell_number is :"+cell.getColumnIndex());
						System.out.println(cell.getAddress().formatAsString());
						dbdata[cell.getRowIndex()][cell.getColumnIndex()] = cell.getStringCellValue();
						System.out.println(dbdata[cell.getRowIndex()][cell.getColumnIndex()] + "\t");
					}
				}
			}
		}
		System.out.println("Test for 'get_dbdata_for_sqlite' end");
		return dbdata;
	}


	
}