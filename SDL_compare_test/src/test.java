import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xslf.usermodel.XSLFSlideShow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class test {
	public static void main(String[] args) throws Exception {
		//使用Files模式而非InputStreams模式
		//--
		File f = new File("E:\\compare\\20170915\\RS160RS260_20170914.xls");
		Workbook wb = WorkbookFactory.create(f);
		//--
		
		//DataFormatter formatter = new DataFormatter();
		int i = 1;
		int numberOfSheets = wb.getNumberOfSheets();
		//++
		//判断是否是DDLIST
		for (Sheet sheet : wb) {
			
			System.out.println("Sheet " + i + " of " + numberOfSheets + ": " + sheet.getSheetName());

		}
		//--
		//test open ole
		//POIFSFileSystem fs = new POIFSFileSystem(new File("E:\\18a\\ES\\20171222\\SDL_src\\18A Block_TS460TS560_SDL_1.8_20171220.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook("E:\\compare\\18A Block_RS160RS260 SDL_1.8_20171220.xlsx");
		System.out.println(workbook.getSpreadsheetVersion());
		  for (PackagePart pPart : workbook.getAllEmbedds()) {
		      String contentType = pPart.getContentType();
		      // Excel Workbook - either binary or OpenXML
		      if (contentType.equals("application/vnd.ms-excel")) {
		    	  System.out.println(contentType);
		    	  HSSFWorkbook embeddedWorkbook = new HSSFWorkbook(pPart.getInputStream());
		    	  //Workbook embeddedWorkbook = WorkbookFactory.create(pPart.getInputStream());
		    	  int numberOfSheets_1 = embeddedWorkbook.getNumberOfSheets();
		          for (Sheet sheet : embeddedWorkbook) {
		        	  System.out.println("Sheet " + i + " of " + numberOfSheets_1 + ": " + sheet.getSheetName());
		        	  if (sheet.getSheetName().equals("DDList Data") || sheet.getSheetName().equals("DDList")) {
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
		  							cell.setCellValue(m.group(1).replaceAll("\\s*", ""));
		  						}
		  						System.out.println(
										"\t\t" + cell.getAddress().formatAsString() + ": " + cell);
		  					}
		  				}
		  				OutputStream fileOut = new FileOutputStream("E:\\compare\\RS160RS260_SDL.xls");
		  				System.out.println(embeddedWorkbook.getSpreadsheetVersion());
		  				embeddedWorkbook.write(fileOut);
		  				fileOut.close();
		        	  };
		          }
		          
		          /*FileOutputStream fileOut = new FileOutputStream("output.xls");
		          embeddedWorkbook.write(fileOut);
		          fileOut.close();*/
		      }
		      // Excel Workbook - OpenXML file format
		      else if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
		    	  System.out.println(contentType);
		          OPCPackage docPackage = OPCPackage.open(pPart.getInputStream());
		          XSSFWorkbook embeddedWorkbook = new XSSFWorkbook(docPackage);
		      }
		      // Word Document - binary (OLE2CDF) file format
		      /*else if (contentType.equals("application/msword")) {
		          HWPFDocument document = new HWPFDocument(pPart.getInputStream());
		      }*/
		      // Word Document - OpenXML file format
		      else if (contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
		    	  System.out.println(contentType);
		          OPCPackage docPackage = OPCPackage.open(pPart.getInputStream());
		          XWPFDocument document = new XWPFDocument(docPackage);
		      }
		      // PowerPoint Document - binary file format
		      /*else if (contentType.equals("application/vnd.ms-powerpoint")) {
		          HSLFSlideShow slideShow = new HSLFSlideShow(pPart.getInputStream());
		      }*/
		      // PowerPoint Document - OpenXML file format
		      else if (contentType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
		    	  System.out.println(contentType);
		          OPCPackage docPackage = OPCPackage.open(pPart.getInputStream());
		          XSLFSlideShow slideShow = new XSLFSlideShow(docPackage);
		      }
		      // Any other type of embedded object.
		      else {
		    	  System.out.println(contentType);
		          System.out.println("Unknown Embedded Document: " + contentType);
		          InputStream inputStream = pPart.getInputStream();
		      }
		  }
		//
	}
}