import java.sql.*;
import org.sqlite.*;

public class Sqlite {
	public static void SqliteConn(String path,String [] filelist,String [][] dbheadlist,String [][][] dbdatelist) throws ClassNotFoundException, SQLException {
		//连接SQLite的JDBC
		Class.forName("org.sqlite.JDBC");
		String dbpath = path.concat("automation.db");
		//建立一个数据库名automation.db的连接，如果不存在就在当前目录下创建之
		Connection conn = DriverManager.getConnection("jdbc:sqlite:".concat(dbpath));
		Statement stat = conn.createStatement();
		System.out.println(stat);
		System.out.println("Table_name length is:"+filelist.length);
		for (String name : filelist) {
			System.out.println("Table_name is:"+name);
		}
		
		
		conn.close(); //结束数据库的连接
	}

}
