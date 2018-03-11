

public class main {
	public static void main(String args[]) throws Exception {
		int args_length = args.length;
		//存新生成文件列表
		String [] file_list = new String[2];
		//存数据库表头
		String [][] dbhead_list = new String[2][];
		//存数据文件
		String [][][] dbdata_list = new String[2][][];

		if (args_length == 2) {
			String outpathparent = "E:\\outputpath\\";
			//根据系统时间生成输出目录
			Mkdir_storage path = new Mkdir_storage(outpathparent);
			String outpath = path.get_path();
			
			//分离driver数据存入 outpath目录
        	for (int i=0;i<2;i++) {
				//new Extract_information(args[i]);
        		Simplify_data sd = new Simplify_data(args[i],outpath);
        		file_list[i] = sd.getNewfilepath();
			}
        	int db_head_count = 0;
        	//生成数据库表头
        	for(String name:file_list)
            {
        		dbhead_list[db_head_count] = Simplify_data.get_dbhead_for_sqlite(name);
        		dbdata_list[db_head_count] = Simplify_data.get_dbdata_for_sqlite(name);
                System.out.println(name);
                //System.out.println(dbhead_list[i]);
                db_head_count++;
            }
        	/*System.out.print(dbhead_list.length + "\r\n");
            System.out.print(dbhead_list[0].length);
        	System.out.print(dbhead_list[1].length);*/
        	/*for(String[] dblist :dbhead_list) {
        		for(String db_head :dblist) {
        			System.out.println(db_head);
        		}
        	}*/
        	
        	//创建数据库连接,数据导入sqlite数据库
        	Sqlite.SqliteConn(outpathparent,file_list,dbhead_list,dbdata_list);
        	
        	//数据比对,生成诧异数据表
        	Sqlite.SqliteCompare(outpathparent,file_list);
        	
		} else
			System.out.println("please input correct args!");
//		Read_List RL = new Read_List("C:\\Users\\Administrator\\Desktop\\compare\\20170915\\","test.txt");
//		RL.getList();
	}
}