package User;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDetailsbylogin {
    public static int var1=0;
    public static void userlogin(Connection con,String Aadhar,String password){
        try{
              String query="select * from votertable";
              java.sql.Statement st=con.createStatement();
              ResultSet rs=st.executeQuery(query);
              while(rs.next()){
                  if(Aadhar.equals(rs.getString("Ano")) && password.equals(rs.getString("Password"))){
                      System.out.println("You logged in succesfully");
                      System.out.println("Your Details and VID are");
                      String userDetails1="Name: "+rs.getString("Name");
                      System.out.println(userDetails1);
//                      String userDetails2=rs.getString("Aadhar");
//                      System.out.println(userDetails2);
                      String userDetails3="VoterId: "+rs.getString("VoterId");
                      System.out.println(userDetails3);
                      var1=1;
                  }
              }
              if(var1==0)
              {
                  System.out.println("Login Failed,Please Enter Valid Credencials");
              }
              st.close();
          }
          catch(Exception e)
          {
              System.out.println("Unable connect to userlogin");
          }
      }
}
