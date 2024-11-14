package Party;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class importPartydata {
    public static void loadData(Connection con) {
        String line = "";
        String splitBy = ",";
        try {
            Statement stmt2 = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PartyTable(Partyid varchar(40) NOT NULL PRIMARY KEY,Name varchar(40) NOT NULL,Symbol varchar(40) NOT NULL,Contact varchar(40) NOT NULL);";
            stmt2.executeUpdate(sql);
            BufferedReader br = new BufferedReader(new FileReader("D:\\programming\\JAVA\\PROJECT\\ONLINE_VOTING_SYSTEM\\party.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                String query = " insert into PartyTable(Partyid,Name,Symbol,Contact) values (?,?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, employee[0]);
                preparedStmt.setString(2, employee[1]);
                preparedStmt.setString(3, employee[2]);
                preparedStmt.setString(4, employee[3]);
                preparedStmt.executeUpdate();
            }
            System.out.println("Party Details Imported");
            br.close();
        }
            catch (Exception e) {
                System.out.println(e);
                }
    }
}
