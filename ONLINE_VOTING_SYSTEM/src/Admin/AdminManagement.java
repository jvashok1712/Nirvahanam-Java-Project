package Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminManagement{
    public static void loadData(Connection con) {
        String line = "";
        String splitBy = ",";
        try {
            Statement stmt2 = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS AdminTable(Admin_Id varchar(40) NOT NULL PRIMARY KEY,Admin_Password varchar(40) NOT NULL,Admin_Name varchar(40) NOT NULL);";
            stmt2.executeUpdate(sql);
            BufferedReader br = new BufferedReader(new FileReader("D:\\programming\\JAVA\\PROJECT\\ONLINE_VOTING_SYSTEM\\Admindetails.csv"));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                String query = " insert into AdminTable(Admin_Id,Admin_Password,Admin_Name) values (?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, employee[0]);
                preparedStmt.setString(2, employee[1]);
                preparedStmt.setString(3, employee[2]);
                preparedStmt.executeUpdate();
            }
            System.out.println("Admintable formed in database");
            br.close();
        }
            catch (Exception e) {
                System.out.println(e);
                }
    }
    
    
    
    public static void SearchAdmin(Connection con,String adminId)
    {
        try
        {
            String query="select * from AdminTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(adminId.equals(rs.getString("Admin_Id")))
                {
                    String adminIdDetails=rs.getString("Admin_Id")+" : "+rs.getString("Admin_Name");
                    System.out.println("Admin Details is : ");
                    System.out.println(adminIdDetails);
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchAdminDetails");
        }
    }
    public static void SearchAdminbyname(Connection con,String name)
    {
        try
        {
            String query="select * from AdminTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.println("Admin Details are: ");
            System.out.println("AdminId\t\t  :\tName");
            while(rs.next())
            {
                String x=rs.getString("Admin_Name");
                for(  int i = 0;i<x.length();i++)
                {
                  for (int j = i + 1; j <= x.length(); j++)
                  {
                    if (x.substring(i, j).toUpperCase().equals(name.toUpperCase())) 
                    {
                        String adminiDetails=rs.getString("Admin_Id")+" : "+rs.getString("Admin_Name");
                        System.out.println(adminiDetails);
                    }
                  }
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchAdmin   Details");
        }
    }
}

