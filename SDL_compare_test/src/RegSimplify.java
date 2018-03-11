import java.util.regex.*;

public class RegSimplify
{
  public static void RegSimplify(String path)
  {
    String txt="17B Block_RS140RS240_SDL_1.19_20171106.xls";

    String re1="(\\d+)";	// Integer Number 1
    String re2="(.)";	// Any Single Character 1
    String re3="(\\s+)";	// White Space 1
    String re4="((?:[a-z][a-z]+))";	// Word 1
    String re5="(_)";	// Any Single Character 2
    String re6="((?:[a-z][a-z]+))";	// Word 2
    String re7="(.)";	// Any Single Character 3
    String re8="(.)";	// Any Single Character 4
    String re9="(\\d+)";	// Integer Number 2
    String re10="((?:[a-z][a-z]+))";	// Word 3
    String re11="(\\d+)";	// Integer Number 3
    String re12="(_)";	// Any Single Character 5
    String re13="((?:[a-z][a-z]+))";	// Word 4
    String re14="(_)";	// Any Single Character 6
    String re15="(\\d+)";	// Integer Number 4
    String re16="(.)";	// Any Single Character 7
    String re17="(\\d+)";	// Integer Number 5
    String re18="(.)";	// Any Single Character 8
    String re19="((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))(?:[0]?[1-9]|[1][012])(?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";	// YYYYMMDD 1
    String re20="(\\.)";	// Any Single Character 9
    String re21="((?:[a-z][a-z]+))";	// Word 5

    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13+re14+re15+re16+re17+re18+re19+re20+re21,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Matcher m = p.matcher(txt);
    if (m.find())
    {
        String int1=m.group(1);
        String c1=m.group(2);
        String ws1=m.group(3);
        String word1=m.group(4);
        String c2=m.group(5);
        String word2=m.group(6);
        String c3=m.group(7);
        String c4=m.group(8);
        String int2=m.group(9);
        String word3=m.group(10);
        String int3=m.group(11);
        String c5=m.group(12);
        String word4=m.group(13);
        String c6=m.group(14);
        String int4=m.group(15);
        String c7=m.group(16);
        String int5=m.group(17);
        String c8=m.group(18);
        String yyyymmdd1=m.group(19);
        String c9=m.group(20);
        String word5=m.group(21);
        System.out.print("("+int1.toString()+")"+"("+c1.toString()+")"+"("+ws1.toString()+")"+"("+word1.toString()+")"+"("+c2.toString()+")"+"("+word2.toString()+")"+"("+c3.toString()+")"+"("+c4.toString()+")"+"("+int2.toString()+")"+"("+word3.toString()+")"+"("+int3.toString()+")"+"("+c5.toString()+")"+"("+word4.toString()+")"+"("+c6.toString()+")"+"("+int4.toString()+")"+"("+c7.toString()+")"+"("+int5.toString()+")"+"("+c8.toString()+")"+"("+yyyymmdd1.toString()+")"+"("+c9.toString()+")"+"("+word5.toString()+")"+"\n");
    }
  }
}