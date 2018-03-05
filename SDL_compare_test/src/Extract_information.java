import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract_information {
	public Extract_information(String path) throws IOException {
	    String regex="\\d{8}";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(path);

	    String result = null;
	    if(matcher.find()){
	        result = matcher.group();
	    }

	    System.out.println(result);
	}
}