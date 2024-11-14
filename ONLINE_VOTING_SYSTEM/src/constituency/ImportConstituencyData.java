package constituency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class ImportConstituencyData {
    public static void loadData(Connection con) {
        String line = "";
        String splitBy = ",";
        try {
          Statement stmt2 = con.createStatement();
          String sql = "CREATE TABLE IF NOT EXISTS Constituencydetails(Constituencyid varchar(40) NOT NULL PRIMARY KEY,Name varchar(40) NOT NULL,State varchar(40) NOT NULL);";
          stmt2.executeUpdate(sql);
          BufferedReader br = new BufferedReader(new FileReader(
              "D:\\programming\\JAVA\\PROJECT\\ONLINE_VOTING_SYSTEM\\Constituency.csv"));
          line = br.readLine();
          while ((line = br.readLine()) != null) {
            String[] lines = line.split(splitBy);
            String query = " insert into  Constituencydetails  values (?,?,?);";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, lines[0]);
            preparedStmt.setString(2, lines[1]);
            preparedStmt.setString(3, lines[2]);
            preparedStmt.executeUpdate();
          }
          System.out.println("Constitudency Details Imported");
          br.close();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
}
