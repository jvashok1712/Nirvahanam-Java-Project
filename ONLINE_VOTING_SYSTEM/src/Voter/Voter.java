package Voter;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

public class Voter {
    
    public static int vflag1;
    
    public static boolean isLogged(Connection con,String VoterId) {
        try
        {
            String status="yes";
            String query="select * from VoterTable";
             //st=con.createStatement();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(status.equals(rs.getString("Loginstatus")) && VoterId.equals(rs.getString("VoterId")))
                {
                    return true;
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable connect to login check");
        }
        return false;
       
    }


        //    public void setLogged(boolean logged) {
        //        this.logged = logged;
        //    }
        //
        //    
   public static boolean isnotVoted(Connection con,String VoterId) {
       try
       {
           String status="no";
           String query="select * from VoterTable";
           java.sql.Statement st=con.createStatement();
           ResultSet rs=st.executeQuery(query);
           while(rs.next())
           {
               if(status.equals(rs.getString("Votingstatus")) && VoterId.equals(rs.getString("VoterId")))
               {
                   return true;
               }
           }
           st.close();
       }
       catch(Exception e)
       {
           System.out.println("Unable connect to vote check");
       }
       return false;
    }

       //    public void setVoted(boolean voted) {
       //        this.voted = voted;
       //    }


    public static void login(Connection con,String VoterId,String password)
    {
        try
        {
            String query="select * from VoterTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(VoterId.equals(rs.getString("VoterId")) && password.equals(rs.getString("Password")))
                {
                    System.out.println("You logged in succesfully");
                    UpdateLoginDetails(con,VoterId);
                    vflag1=1;
                    
                }
//                else
//                {
//                    System.out.println("Login Failed!! Please try again");
//                }
            }
            if(vflag1==0)
            {
                System.out.println("Login Failed!! Please try again");
            }
            
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable connect to login");
        }
    }
    
    public static void vote(Connection con,String VoterId)
    {
        if(isLogged(con,VoterId)==true)
        {
            if(isnotVoted(con,VoterId)==true)
            {
            
                Scanner sc=new Scanner(System.in);
                System.out.println("Your Voting Candidates are...");
                try
                {
                    String query="select * from candidatetable";
                    java.sql.Statement st=con.createStatement();
                    ResultSet rs=st.executeQuery(query);
                    System.out.println("CandidateId\t"+" : "+"  Name "+" : "+"PartySymbol");
                    while(rs.next())
                    {
                        String CandidateDetails=rs.getString("CandidateId")+" : "+rs.getString("Name")+" : "+rs.getString("PartySymbol");
                        System.out.println(CandidateDetails);
                    }
                    System.out.println();
                    st.close();
                }
                catch(Exception e)
                {
                    System.out.println("Unable to connect DisplayCandidateDEtails");
                }
                
                System.out.println("Enter candidateId to vote for them");
                String CandidateId=sc.nextLine();
                //setVoted(true);
                
                if(checkcandidateId(con,CandidateId)==1)
                {
                    UpdateCandidateVotes(con,CandidateId);
                    updateVoterDetails(con,VoterId);
                }
                else {
                    logout(con,VoterId);
                    System.out.println("Invalid candidateid entered,Please Enter Valid Id");
                }
                logout(con,VoterId);
                sc.close();
            }
            else
            {
                System.out.println("You are Already Voted,You Cannot Vote for second Time");
            }
        }
        else
        {
            System.out.println("You are not logged in,Please Login first");
        }
        
    }
        

    public static void UpdateLoginDetails(Connection con, String VoterId) 
    {
        try {
            String status="yes";
            String query = "update VoterTable set LoginStatus = ? where VoterId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            //java.sql.Statement st = con.createStatement();
            preparedStmt.setString(1, status);
            preparedStmt.setString(2, VoterId);
            int num = preparedStmt.executeUpdate();
//            if (num == 0) {
//                System.out.println("LoginStatus Update failed!!!");
//            } else {
//                System.out.println("Login");
//            }
            //st.close();
        } catch (Exception e) {
            System.out.println("Unable to connect update Voting status");
        }

    }
    
    public static void updateVoterDetails(Connection con,String VoterId)
    {
        //String x = null;
        if(isnotVoted(con,VoterId)==true)
        {
            try
            {
                String status="yes";
                String query = "update VoterTable set VotingStatus = ?  where VoterId = ? ";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                java.sql.Statement st=con.createStatement();
                preparedStmt.setString(1, status);
                preparedStmt.setString(2, VoterId);
                int num = preparedStmt.executeUpdate();
                if (num == 0) {
                    System.out.println("Update failed!!!");
                } else {

                    System.out.println("Your Vote has been taken.");
                }
                st.close();
            }
            catch (Exception e) {
                System.out.println("Unable to connect update Voting status");
            }
        }
    }
    
    public static void UpdateCandidateVotes(Connection con,String candidateId)
    {
        int x=1;
        try {
            String query = "update CandidateTable set NoOfVotes = NoOfVotes + ? where CandidateId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(2, candidateId);
            preparedStmt.setInt(1, x);
            int rs = preparedStmt.executeUpdate();
            if (rs == 0) {
                System.out.println("no of votes not updated");
            } else {

                System.out.println("Voting Completed");
            }
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println("Unable to connect in no of votes");

        }
    }
    
    public static void UpdateVoter(Connection con,String VoterId,String newname)
    {
        try
        {
            String query = "update VoterTable set Name = ?  where VoterId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            java.sql.Statement st=con.createStatement();
            preparedStmt.setString(1, newname);
            preparedStmt.setString(2, VoterId);
            int num = preparedStmt.executeUpdate();
            if (num == 0) {
                System.out.println("Update failed!!!");
            } else {

                System.out.println("Voting status Updated");
            }
            st.close();
        }
        catch (Exception e) {
            System.out.println("Unable to connect update Voter name");
        }
    }
    
    public static void deleteVoter(Connection con,String VoterId)
    {
        try
        {
            String query = "delete from VoterTable where VoterId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            java.sql.Statement st=con.createStatement();
            preparedStmt.setString(1, VoterId);
            int num = preparedStmt.executeUpdate();
            if (num == 0) {
                System.out.println("delete failed!!!");
            } else {

                System.out.println("Voter removed!!!");
            }
            st.close();
        }
        catch (Exception e) {
            System.out.println("Unable to connect delete Voter");
        }
    }
    
    public static int checkcandidateId(Connection con,String candidateId)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(candidateId.equals(rs.getString("CandidateId")))
                {
                    return 1;
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect checkCandidate");
        }
        return 0;
    }
    
    public static void logout(Connection con,String VoterId)
    {
        try
        {
            String status="no";
            String query = "update VoterTable set LoginStatus = ?  where VoterId = ? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            java.sql.Statement st=con.createStatement();
            preparedStmt.setString(1, status);
            preparedStmt.setString(2, VoterId);
            int num = preparedStmt.executeUpdate();
//            if (num == 0) {
//                System.out.println("Update failed!!!");
//            } else {
//
//                System.out.println("Voting status Updated");
//            }
            st.close();
        }
        catch (Exception e) {
            System.out.println("Unable to connect update logout");
        }
    }
    
    public static void getvoterdetails(Connection con,String VoterId)
    {
        if(isLogged(con,VoterId)==true)
        {
            try
            {
                String query="select * from VoterTable";
                java.sql.Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(query);
                System.out.println("Your Details are: ");
                System.out.println("VoterName\t\t  :\tVotingStatus");
                while(rs.next())
                {
                    if(VoterId.equals(rs.getString("VoterId")))
                    {
                        String VoterIdDetails=rs.getString("Name")+"          :          "+rs.getString("VotingStatus");
                        System.out.println(VoterIdDetails);
                    }
                }
                st.close();
                logout(con,VoterId);
            }
            catch(Exception e)
            {
                System.out.println("Unable to connect SearchVoterDetails");
            }
        }
        else {
            System.out.println("Login And Try Again.");
        }
    }
}
