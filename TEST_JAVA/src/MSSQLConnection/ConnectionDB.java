package MSSQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDB {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	// MS-SQL JDBC드라이버 로드
	static final String msjdbc_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	static final String msjdbc_url = "jdbc:microsoft:sqlserver://192.168.0.88:1433;databasename=mysite1";// DB이름

	// MSSQL ID, PASSWORD
	private static String msid = "yjyoon"; // mssql ID
	private static String mspassword = "bsnc791979"; // mssql password

//	String url = msjdbc_url; // MSSQLJDBC URL

	// -------------------------------------------------------
	// Name : ConnectionDB
	// Desc : 생성자
	// -------------------------------------------------------
	public ConnectionDB() {

	}	
	
	public String getMsSQLJDBCUrl() {
 
		StringBuilder url = new StringBuilder();
		url.append("jdbc:sqlserver://");
		url.append("192.168.0.88"); // 접속호스트
		url.append(":");
		url.append("1433"); // 포트번호
		url.append(";");
		url.append("databaseName=");
		url.append("mysite1"); // DB명
		url.append(";");
		url.append("selectMethod=");
		url.append("cursor");
		url.append(";");
 
		return url.toString();
	}


	// -------------------------------------------------------
	// Name : getPublicKey
	// Desc : 데이타베이스와 연결해서 공개키를 가져온다.
	// -------------------------------------------------------
	// 통합사이트에서만 키를 가져오면 된다.
	public void getDB() throws Exception {

		String sql = "select raw from dbo.test1";

		// MS-SQL에 연결
		Class.forName(msjdbc_driver).newInstance();
		conn = DriverManager.getConnection(getMsSQLJDBCUrl(), msid, mspassword);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
		disconnectDB(); // DB와 연결을 끊는다.

	}

	// -------------------------------------------------------
	// Name : disconnectDB
	// Desc : 데이타베이스와 연결을 끊는다.
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