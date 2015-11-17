import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class test {
	 public static void main(String[] args) {
		  try {
		   Class.forName("com.mysql.jdbc.Driver");
		  } catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }

		  String url = "jdbc:mysql://localhost:3306";
		  String username = "root";
		  String password = "apmsetup";
		  try {
		   Connection conn = DriverManager.getConnection(url, username, password);
		   Statement stmt = conn.createStatement();
		   
		   String sql = "INSERT INTO `user`.`gcm_info` (`uuid`) VALUES ('325');";
		   stmt.executeUpdate(sql);
		  } catch (SQLException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		 }
}
