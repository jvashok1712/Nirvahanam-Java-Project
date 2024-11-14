package Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CandidateClass {
    /*
    private int serialNum;
    private String Name;
    private String Address;
    private String Party;
    private int NoOfVotes;
    */
    //private boolean logged;
    

    public static void loginCandidate(Connection con,String CandidateId,String password)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(CandidateId.equals(rs.getString("CandidateId")) && password.equals(rs.getString("password")))
                {
                    
                    System.out.println("You logged in succesfully");
                    
                    System.out.println("Your Details as Candidate are");
                    try
                    {
                        String query2="select * from CandidateTable";
                        Statement st2=con.createStatement();
                        ResultSet rs2=st2.executeQuery(query2);
                        while(rs2.next())
                        {
                            String CandidateDetails1=rs2.getString("CandidateId");
                            System.out.println("CandidateId\t:\t"+CandidateDetails1);
                            String CandidateDetails2=rs2.getString("Name");
                            System.out.println("Name\t\t:\t"+CandidateDetails2);
                            String CandidateDetails3=rs2.getString("PartyName");
                            System.out.println("Party Name\t:\t"+CandidateDetails3);
                            String CandidateDetails4=rs2.getString("PartySymbol");
                            System.out.println("Party Symbol\t:\t"+CandidateDetails4);
                            String CandidateDetails5=rs2.getString("ElectionName");
                            System.out.println("ElectionName\t:\t"+CandidateDetails5);
                            
                        }
                        st2.close();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Unable to connect DisplayCandidateDEtails");
                    }
                }
                else
                {
                    System.out.println("Login Failed by candidate!! Please try again");
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable connect to Candidatelogin");
        }
    }
    
    public static void SearchCandidate(Connection con,String candidateId)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(candidateId.equals(rs.getString("Candidate")))
                {
                    String VoterIdDetails=rs.getString("CandidateId")+" : "+rs.getString("Name");
                    System.out.println("Candidate Details is : ");
                    System.out.println(VoterIdDetails);
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchCandidateDetails");
        }
    }
    
    public static void SearchCandidatebyname(Connection con,String name)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.println("Candidate Details are: ");
            System.out.println("CandidateId\t\t  :\tName");
            while(rs.next())
            {
                String x=rs.getString("Name");
                for(  int i = 0;i<x.length();i++)
                {
                  for (int j = i + 1; j <= x.length(); j++) 
                  {
                    if (x.substring(i, j).toUpperCase().equals(name.toUpperCase())) 
                    {
                        String candidateDetails=rs.getString("CandidateId")+" : "+rs.getString("Name");
                        System.out.println(candidateDetails);
                    }
                  }
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchCandidateDetails");
        }
    }
    
    public static void SearchCandidatebyage(Connection con,int age)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(age==(rs.getInt("Age")))
                {
                    String candidateDetails=rs.getString("CandidateId")+" : "+rs.getString("Name");
                    System.out.println("Candidate Details is : ");
                    System.out.println(candidateDetails);
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchCandidateDetails");
        }
    }
    
    public static void UpdateCandidate(Connection con,String candidateId,String newname)
    {
        try {
            String query = "update CandidateTable set Name = ? where CandidateId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, newname);
            preparedStmt.setString(2, candidateId);
            int rs = preparedStmt.executeUpdate();
            if (rs == 0) {
                System.out.println("newname not updated");
            } else {

                System.out.println("newname votes Updated");
            }
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println("Unable to connect in update newname of candidate");

        }
    }
    
    public static void deleteCandidate(Connection con,String candidateId)
    {
        try {
            String query = "delete from CandidateTable where CandidateId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, candidateId);
            int rs = preparedStmt.executeUpdate();
            if (rs == 0) {
                System.out.println("delete failed!!!");
            } else {

                System.out.println("candidate removed");
            }
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println("Unable to connect in delete candidate");

        }
    }
    
    
}
