import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.regex.*;

import org.apache.poi.ss.usermodel.*;

public class SDL_COMPARE {
	public static void main(String[] args) throws Exception {
		//浣跨敤Files妯″紡鑰岄潪InputStreams妯″紡
		//--
		File f = new File("E:\\onboard_ES\\17B Block_RS140RS240_SDL_1.19_20171106.xls");
		Workbook wb = WorkbookFactory.create(f);
		System.out.println(wb.getSpreadsheetVersion());
		//--
		
		DataFormatter formatter = new DataFormatter();
		int i = 1;
		int numberOfSheets = wb.getNumberOfSheets();
		System.out.println(numberOfSheets);
		//++
		//鍒ゆ柇鏄惁鏄疍DLIST
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
						System.out.println(
								"\t\t" + cell.getAddress().formatAsString() + ": " + formatter.formatCellValue(cell));
					}
				}
				//--
				OutputStream fos = new FileOutputStream("SampleSS-updated.xls");
				wb.write(fos);
				fos.close();
			}
			else {
				System.out.println(f.getName() + " in path:" + f.getParentFile() + " is not a DDList file.");
			}
		}
		//--
		
		/*//++
		//鏁版嵁澶勭悊姝ｅ垯鍖归厤driver鐗堟湰鍙�
		
		for (Sheet sheet : wb) {
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
					System.out.println(
							"\t\t" + cell.getAddress().formatAsString() + ": " + formatter.formatCellValue(cell));
				}
			}
		}
		//--
*/		
		
		
		// Modify the workbook
		//Sheet sh = wb.createSheet("new sheet");
		//Row row = sh.createRow(7);
		//Cell cell = row.createCell(42);
		// cell.setActiveCell(true);
		// Testing code
		// cell.setCellValue("The answer to life, the universe, and everything");
		// Save and close the workbook
		/*OutputStream fos = new FileOutputStream("SampleSS-updated.xls");
		wb.write(fos);
		fos.close();*/
	}
}