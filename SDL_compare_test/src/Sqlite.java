import java.io.File;
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
		System.out.println("Table_name list length is:"+filelist.length);
		int count = 0;
		for (String name : filelist) {
			
			String sql_command_saving_creating_table_head = null;//Use to saving sql command for creating the table head.
			System.out.println("Table_name is:"+name+".Test 4 creating table head for sqlite");
			File f = new File(name);//transfer filepath to File object.
			String filename = f.getName();//get file name from string path
			String sql = "CREATE TABLE ";
			sql_command_saving_creating_table_head = sql.concat(filename+" (");
			for (int i = 0 ; i < dbheadlist[count].length; i+=1){
				System.out.println(dbheadlist[count][i]);
				if (i != dbheadlist[count].length-1) {
					if (i != 2) {
						sql_command_saving_creating_table_head = sql_command_saving_creating_table_head.concat("["+dbheadlist[count][i]+"] VARCHAR(50)" + ",");
					} else {
						sql_command_saving_creating_table_head = sql_command_saving_creating_table_head.concat("["+dbheadlist[count][i]+"] VARCHAR(50) PRIMARY KEY" + ",");
					}
				} else {
					sql_command_saving_creating_table_head = sql_command_saving_creating_table_head.concat("["+dbheadlist[count][i]+"] VARCHAR(50)" + ");");
				}
			}
			System.out.println(sql_command_saving_creating_table_head);
			//生成表头
			stat.executeUpdate(sql_command_saving_creating_table_head);
			count+=1;
		}
		
		stat.close();
		conn.close(); //结束数据库的连接
	}

}
