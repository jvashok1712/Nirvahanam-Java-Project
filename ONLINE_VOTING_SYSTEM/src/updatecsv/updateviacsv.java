package updatecsv;

import java.util.Scanner;
import java.io.File;
import java.sql.*;

public class updateviacsv {

        public static void updateTableparty(Connection con) throws Exception
        {
            String query = "update partytable set Name=?, Symbol=?,Contact=? where Partyid=?";
            PreparedStatement ps=con.prepareStatement(query);
            Scanner sc = new Scanner(new File("D:\\programming\\JAVA\\PROJECT\\ONLINE_VOTING_SYSTEM\\party.csv"));
            while(sc.hasNextLine()){
              String[] str = sc.nextLine().split(",");
              ps.setString(1,str[1]);
              ps.setString(2,str[2]);
              ps.setString(3,str[3]);
              ps.setString(4,str[0]);
              int x=ps.executeUpdate();
            }
            System.out.println("Data Updated Succesfully In Party Table");
         }
        
        public static void updateTableconstituency(Connection con) throws Exception
        {
            String query = "update constituencydetails set Name=?,State=? where Constituencyid=?";
            PreparedStatement ps=con.prepareStatement(query);
            Scanner sc = new Scanner(new File("D:\\programming\\JAVA\\PROJECT\\ONLINE_VOTING_SYSTEM\\Constituency.csv"));
            while(sc.hasNextLine()){
              String[] str = sc.nextLine().split(",");
              ps.setString(1, str[1]);
              ps.setString(2, str[2]);
              ps.setString(3, str[0]);
              int x=ps.executeUpdate();
              
            }
            System.out.println("Data Updated Succesfully In ConstituencyTable");
        }
    
}

