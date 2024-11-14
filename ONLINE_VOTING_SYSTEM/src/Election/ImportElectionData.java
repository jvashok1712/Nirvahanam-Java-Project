package Election;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ImportElectionData {
    public static void loadData(Connection con) {
        String line = "";
        String splitBy = ",";
        try {
          Statement stmt2 = con.createStatement();
          String sql = "CREATE TABLE IF NOT EXISTS electiondetails(electionid varchar(40) NOT NULL PRIMARY KEY,electiondate date NOT NULL,electiontype varchar(40) NOT NULL,details varchar(40) NOT NULL,Resultdate date NOT NULL);";
          stmt2.executeUpdate(sql);
          BufferedReader br = new BufferedReader(
              new FileReader("D:\\programming\\JAVA\\PROJECT\\ONLINE_VOTING_SYSTEM\\election.csv"));
          line = br.readLine();
          while ((line = br.readLine()) != null) {
            String[] lines = line.split(splitBy);
            String query = " insert into electiondetails(electionid,electiondate,electiontype,details,Resultdate) values (?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, lines[0]);
            preparedStmt.setDate(2, Date.valueOf(lines[1]));
            preparedStmt.setString(3, lines[2]);
            preparedStmt.setString(4, lines[3]);
            preparedStmt.setDate(5, Date.valueOf(lines[4]));
            preparedStmt.executeUpdate();
          }
          System.out.println("Election Details Imported");
          br.close();
        } catch (Exception e) {
          System.out.println(e);
        }
      }
}
