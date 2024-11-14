package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ApproveVoter {
    static ArrayList<String> Namelist=new ArrayList<String>();
    static ArrayList<String> Anolist=new ArrayList<String>();
    static ArrayList<Integer> Agelist=new ArrayList<Integer>();
    static ArrayList<String> passwordlist=new ArrayList<String>();
    static ArrayList<String> VoterIdlist=new ArrayList<String>();
    static Integer count=0;
    
    public static void getRegisterMemberintolist(Connection con)
    {
        try
        {
            String query="select * from users";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next())
            {
                String Name=rs.getString("Name");
                //System.out.println(Name);
                Namelist.add(Name);
                
                String Adharno=rs.getString("Aadhar");
                Anolist.add(Adharno);
                
                int age = rs.getInt("age");
                Agelist.add(age);
                
                String setnewpassword=rs.getString("password");
                passwordlist.add(setnewpassword);
                
                String VoterId="2022"+Adharno;
                VoterIdlist.add(VoterId);
                
//                VotingStatuslist.add("no");
//                LoginStatuslist.add("no");
            }
            st.close();
            truncateTable(con);
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect add details to list");
        }
    }
    
    public static void getListDetailstoVoterTable(Connection con)
    {
        try {
            String query = " insert into VoterTable(Name,Ano,Age,VoterId,Password) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            for(int i = Namelist.size() - 1; i >= 0; i--) 
            {
                String Name = Namelist.get(i);
                //System.out.println(Namelist.get(i));
                //Namelist.remove(Namelist.size() - 1);
                preparedStmt.setString(1, Name);

                String AdharNO = Anolist.get(i);
                //System.out.println(Anolist.get(i));
                //Anolist.remove(Anolist.size() - 1);
                preparedStmt.setString(2, AdharNO);
                
                int age = Agelist.get(i);
                //System.out.println(Agelist.get(i));
                //VoterIdlist.remove(VoterIdlist.size() - 1);
                preparedStmt.setInt(3, age);
                
                String Voterid = VoterIdlist.get(i);
                //System.out.println(VoterIdlist.get(i));
                //Agelist.remove(Agelist.size() - 1);
                preparedStmt.setString(4, Voterid);
                

                String Passwords = passwordlist.get(i);
                //System.out.println(passwordlist.get(i));
                //passwordlist.remove(passwordlist.size() - 1);
                preparedStmt.setString(5, Passwords);

                int rs = preparedStmt.executeUpdate();
//                if (rs == 0) {
//                    System.out.println("Insert failed!!!");
//                } else {
//
//                    System.out.println("Inserted successfully");
//
//                }
                System.out.printf("Voter with name:%s,AadharNo:%s Succesfully approved",Name,AdharNO);
                System.out.println();
            }
            System.out.println("Succesfully approved Voter Requests");
        } catch (Exception e) {
            System.out.println("Unable to connect inserting data to VoterTable");

        }
//        System.out.println(Namelist);
        Namelist.clear();
        Anolist.clear();
        Agelist.clear();
        passwordlist.clear();
        VoterIdlist.clear();
    }
    
    public static void truncateTable(Connection con)
    {
        try
        {
            String query = "truncate users";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            int rs = preparedStmt.executeUpdate();
            
        }
        catch (Exception e) {
            System.out.println("Unable to connect truncate data from userregisterTable");
        }
    }
    
    public static String VoterIDGenerator(String firstName,String Aadhar) {
        String res1 = firstName.substring(0, 2);
        String res2 =  Aadhar.substring(Aadhar.length() - 4);
        
        String res4 = res1  + res2;
        
        String res5 = count.toString().length() == 1 ? ("0000" + count)
                : count.toString().length() == 2 ? ("0" + count) : count.toString();
        count = count + 1;
        String finalResult = res4 + res5;
        return finalResult;
    }
    
    public void DeleteVoter(Connection con,String VoterId)
    {
        try
        {
            String query = "delete from VoterTable " + "where VoterId= ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, VoterId);

            int row = preparedStatement.executeUpdate();
            if(row==0)
            {
                System.out.println("Voter not Deleted");
            }
            else
            {
                System.out.println("Voter Deleted");
            }
                preparedStatement.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect deleteVoter");
        }
    }
}
