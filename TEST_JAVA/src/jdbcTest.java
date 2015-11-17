import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class jdbcTest {
	public static void main(String[] args) {

		  try {
		         // Construct data
		         String data = URLEncoder.encode("phone", "01045761476") + "=" + URLEncoder.encode("uuid", "ddddd");
		     
		         // Send data
		         URL url = new URL("http://192.168.0.181:8080/TestGCM/myGCM");
		         URLConnection conn = url.openConnection();
		         // If you invoke the method setDoOutput(true) on the URLConnection, it will always use the POST method.
		         conn.setDoOutput(true);
		         OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		         wr.write(data);
		         wr.flush();
		     
		         // Get the response
//		         BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//		         String line;
//		         while ((line = rd.readLine()) != null) {
//		            System.out.println(line);
//		         }
		         wr.close();
//		         rd.close();
		         
		     }
		     catch (Exception e) {
		     }
	}

}