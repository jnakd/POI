import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mkdir_storage {
	private String outputfilestoragepath;
	
	public void set_path(String path) {
		this.outputfilestoragepath = path;
	}
	public String get_path() {
		System.out.println(outputfilestoragepath);
		return outputfilestoragepath;
	}
	
	public Mkdir_storage(String outputfilestoragepath_pre) throws IOException {
		File dir = new File(outputfilestoragepath_pre);
		if (!dir.exists()) {
			if(dir.mkdirs()) {
				System.out.println("create dir:" + outputfilestoragepath_pre + " is sucess."); 
			}else {
				System.out.println("create dir:" + outputfilestoragepath_pre + " is fail.");
			}
		}else {
			System.out.println("create dir:" + outputfilestoragepath_pre + " is exist.");
		}
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd_hhmm");
		System.out.println(dateFormat.format(date));
		String outputfilestoragepath_end = dateFormat.format(date).concat("\\");
		String outputfilestoragepath = outputfilestoragepath_pre.concat(outputfilestoragepath_end);
		set_path(outputfilestoragepath);
		File dir1 = new File(outputfilestoragepath);
		if (!dir1.exists()) {
			if(dir1.mkdirs()) {
				System.out.println("create dir:" + outputfilestoragepath + " is sucess."); 
			}else {
				System.out.println("create dir:" + outputfilestoragepath + " is fail.");
			}
		}else {
			System.out.println("create dir:" + outputfilestoragepath + " is exist.");
		}
	}
	
	
}