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

public class SDL_Compare_Test {
	public static void main(String args[]) throws Exception {
		File src_xls = new File("D:\\test\\SDL\\RS160RS260 SDL_1.8_20171220.xls");
		File tgt_xls = new File("D:\\test\\SDL\\RS160RS260 SDL_1.4_20171122.xls");
		Workbook src_data = WorkbookFactory.create(src_xls);
		Workbook tgt_data = WorkbookFactory.create(tgt_xls);
		int src_sheet_count = src_data.getNumberOfSheets();
		int tgt_sheet_count = tgt_data.getNumberOfSheets();
		int i = 1;
		for (Sheet sheet : src_data) {
			System.out.println("Sheet " + i + " of " + src_sheet_count + ": " + sheet.getSheetName());
		}
	}
} 