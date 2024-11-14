package Voter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VoterDetails implements VoterDetailsinterface {
    
    public void Voterlist(Connection con)
    {
        try
        {
            String query="select * from VoterTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                String CandidateDetails=rs.getString("VoterId")+" : "+rs.getString("Name")+" : "+rs.getString("VotingStatus");
                System.out.println(CandidateDetails);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVoterDEtailsin resultclass");
        }
    }
    
    public void Canidatelist(Connection con)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                String CandidateDetails=rs.getString("CandidateId")+" : "+rs.getString("Name")+" : "+rs.getString("Symbol");
                System.out.println(CandidateDetails);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayCandidateDEtailsin resultclass");
        }
    }
    
    public void Votedpeople(Connection con)
    {
        try
        {
            String query="select * from VoterTable where VotingStatus = 'yes' ";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                
                String CandidateDetails=rs.getString("Name")+" : "+rs.getString("Ano")+" : "+rs.getString("VoterId");
                System.out.println(CandidateDetails);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotedpeople resultclass");
        }
    }
    
    public void NotVotedpeople(Connection con)
    {
        try
        {
            String query="select * from VoterTable where VotingStatus = 'no' ";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                String CandidateDetails=rs.getString("Name")+" : "+rs.getString("Ano")+" : "+rs.getString("VoterId");
                System.out.println(CandidateDetails);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotedpeople resultclass");
        }
        
    }
    
    public int NoofVotesofeachCandidate(Connection con,String CandidateId)
    {
        int noofvotes = 0;
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(CandidateId.equals(rs.getString("CandidateId")))
                {
                    noofvotes=rs.getInt("NoOfVotes");
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayCandidateDEtailsin resultclass");
        }
        //System.out.println(noofvotes);
        return noofvotes;
    }
    
    public void CandidateandNoofvotes(Connection con)
    {
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                String CandidateDetails=rs.getString("CandidateId")+" : "+rs.getString("Name")+" : "+rs.getString("NoOfVotes");
                System.out.println(CandidateDetails);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayCandidateDEtailsin resultclass");
        }
    }
    
    
    
    public void SearchVoter(Connection con,String VoterId)
    {
        try
        {
            String query="select * from VoterTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.println("Voter Details are: ");
            System.out.println("VoterName\t:\tVotingStatus");
            while(rs.next())
            {
                if(VoterId.equals(rs.getString("VoterId")))
                {
                    String VoterIdDetails=rs.getString("Name")+"          :          "+rs.getString("VotingStatus");
                    System.out.println(VoterIdDetails);
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchVoterDetails");
        }
    }
    
    public void SearchVoterbyname(Connection con,String name)
    {
        try
        {
            String query="select * from VoterTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.println("Voter Details are: ");
            System.out.println("VoterName\t\t  :\tVotingStatus");
            while(rs.next())
            {
                String x=rs.getString("Name");
                for(  int i = 0;i<x.length();i++)
                {
                  for (int j = i + 1; j <= x.length(); j++) 
                  {
                    if (x.substring(i, j).toUpperCase().equals(name.toUpperCase())) 
                    {
                        String VoterIdDetails=rs.getString("Name")+"          :          "+rs.getString("VotingStatus");
                        System.out.println(VoterIdDetails);
                    }
                  }
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchVoterDetails");
        }
    }
    
    public void SearchVoterbyage(Connection con,int age)
    {
        try
        {
            String query="select * from VoterTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.println("Voter Details are: ");
            System.out.println("VoterName\t\t  :\tVotingStatus");
            while(rs.next())
            {
                if(age==rs.getInt("Age"))
                {
                    String VoterIdDetails=rs.getString("VoterId")+"          :          "+rs.getString("VotingStatus");
                    System.out.println(VoterIdDetails);
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect SearchVoterDetails");
        }
    }
    
    
}
