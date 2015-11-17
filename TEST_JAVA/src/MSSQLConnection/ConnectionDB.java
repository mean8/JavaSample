package MSSQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDB {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	// MS-SQL JDBC����̹� �ε�
	static final String msjdbc_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	static final String msjdbc_url = "jdbc:microsoft:sqlserver://192.168.0.88:1433;databasename=mysite1";// DB�̸�

	// MSSQL ID, PASSWORD
	private static String msid = "yjyoon"; // mssql ID
	private static String mspassword = "bsnc791979"; // mssql password

//	String url = msjdbc_url; // MSSQLJDBC URL

	// -------------------------------------------------------
	// Name : ConnectionDB
	// Desc : ������
	// -------------------------------------------------------
	public ConnectionDB() {

	}	
	
	public String getMsSQLJDBCUrl() {
 
		StringBuilder url = new StringBuilder();
		url.append("jdbc:sqlserver://");
		url.append("192.168.0.88"); // ����ȣ��Ʈ
		url.append(":");
		url.append("1433"); // ��Ʈ��ȣ
		url.append(";");
		url.append("databaseName=");
		url.append("mysite1"); // DB��
		url.append(";");
		url.append("selectMethod=");
		url.append("cursor");
		url.append(";");
 
		return url.toString();
	}


	// -------------------------------------------------------
	// Name : getPublicKey
	// Desc : ����Ÿ���̽��� �����ؼ� ����Ű�� �����´�.
	// -------------------------------------------------------
	// ���ջ���Ʈ������ Ű�� �������� �ȴ�.
	public void getDB() throws Exception {

		String sql = "select raw from dbo.test1";

		// MS-SQL�� ����
		Class.forName(msjdbc_driver).newInstance();
		conn = DriverManager.getConnection(getMsSQLJDBCUrl(), msid, mspassword);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		disconnectDB(); // DB�� ������ ���´�.

	}

	// -------------------------------------------------------
	// Name : disconnectDB
	// Desc : ����Ÿ���̽��� ������ ���´�.
	// -------------------------------------------------------
	public void disconnectDB() throws Exception {
		conn.close();
		stmt.close();
		rs.close();
	}

	// main
	public static void main(String[] args) throws Exception {
		ConnectionDB db = new ConnectionDB();
		db.getDB();

	}
}