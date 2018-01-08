import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Read_List {
	private String dir_name = null;
	private String list_name = null;
	private BufferedWriter out = null;
	Vector<String> ver = null;

	public Read_List(String dir_name, String list_name) throws IOException {
		this.dir_name = dir_name; // �ļ��е�ַ
		this.list_name = list_name; // �����ļ��б����ļ���ַ
		ver = new Vector<String>(); // ������ջ
	}
	public String Get_dir_name () {
		return dir_name;
	}
	public String Get_list_name () {
		return list_name;
	}

	public void getList() throws Exception {
		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Get_list_name(), true))); // ��׷�ӵķ�ʽд�뵽ָ�����ļ�
		ver.add(Get_dir_name());
		while (ver.size() > 0) {
			File[] files = new File(ver.get(0).toString()).listFiles(); // ��ȡ���ļ��������е��ļ�(��)��
			ver.remove(0);
			int len = files.length;
			for (int i = 0; i < len; i++) {
				String tmp = files[i].getAbsolutePath();
				if (files[i].isDirectory()) // �����Ŀ¼���������С��Ա���к�������
					ver.add(tmp);
				else
					out.write(tmp + "\r\n"); // ������ļ�����ֱ������ļ�����ָ�����ļ���
			}
		}
		out.close();
	}
}