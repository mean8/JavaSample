import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMServerSide {

	public void sendMessage() throws IOException {

		Sender sender = new Sender("AIzaSyC5d7zn3Gn_k2yDKu8T9Jzo63g_xvP4Ke8");
		List<String> list = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306",
					"root", "apmsetup");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `user`.`gcm_info`");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			while (rs.next()) {
				String regId = rs.getString("uuid");
				list.add(regId);
			}
		} catch (Exception ex) {
			// handle the error
		}

		String str = URLEncoder.encode("한글은요 ?", "euc-kr");
		Message message = new Message.Builder().addData("msg", str).build();

		MulticastResult multiResult = sender.send(message, list, 5);

		if (multiResult != null) {

			List<Result> resultList = multiResult.getResults();

			for (Result result : resultList) {

				System.out.println(result.getMessageId());
			}

		}

	}

	public static void main(String[] args) throws Exception {

		GCMServerSide s = new GCMServerSide();

		s.sendMessage();

	}

}