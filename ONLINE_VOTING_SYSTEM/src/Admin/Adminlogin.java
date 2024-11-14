package Admin;

import java.sql.Connection;
import java.sql.ResultSet;

interface admin{
    public int loginAdmin(Connection con,String adminId,String password);
}


public class Adminlogin implements admin{
    public static int flag=0;
    public int loginAdmin(Connection con,String adminId,String password)
    {
        try
        {
            String query="select * from AdminTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(adminId.equals(rs.getString("Admin_Id")) && password.equals(rs.getString("Admin_Password")))
                {
                    flag=1;
                    System.out.println("You logged in succesfully");
                }
//                else
//                {
//                    System.out.println("Login Failed by Admin! Please try again");
//                }
            }
            if(flag==0)
            {
                System.out.println("Login Failed,Please Enter Valid Credentials");
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable connect to Admin login");
        }
        return flag;
    }
}