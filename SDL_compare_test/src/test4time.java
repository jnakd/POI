import java.util.regex.*;

public class test4time {
	public static void main(String[] args) throws Exception {
		String str = "E:\\\\compare\\\\20170915\\\\RS160RS260_20170914.xls";

	    String regex="\\d{8}";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(str);

	    String result = null;
	    if(matcher.find()){
	        result = matcher.group();
	    }

	    System.out.println(result);
	}
}