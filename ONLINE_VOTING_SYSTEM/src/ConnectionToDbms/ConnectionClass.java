package ConnectionToDbms;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
//	private static Connection conn = null;
	
	public static Connection connecttodbms()
	{
		try
		{
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String url="jdbc:mysql://localhost:3306/projectjava";
//			String root="root";
//			String Password="sivasaikakarla@123";
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectjava", "root","sivasaikakarla@123" );
			return conn;
		} catch (Exception ex){
			System.out.println("Unable to connect ,PLease try again");
		}
		return null;
	}
	
	public static void disconnecttodbms(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Unable to disconnect");
        }
	}
}
