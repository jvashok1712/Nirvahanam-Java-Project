package ResultModule;

import java.sql.Connection;
import java.sql.ResultSet;
import Voter.VoterDetails;

public class ResultClass {
    
    VoterDetails voterDetails=new VoterDetails();
    public void Votingrate(Connection con)
    {
        int registeredpeoplecount=Votercount(con);
        int votedpeoplecount=Votedpeoplecount(con);
        int notvotedpeoplecount=NotVotedpeoplecount(con);
        System.out.println("No of people registered : "+registeredpeoplecount);
        System.out.println("No of people voted : "+votedpeoplecount);
        System.out.println("No of people not voted : "+notvotedpeoplecount);
        System.out.println();
        float votingrate=(float)((votedpeoplecount*100)/registeredpeoplecount);
        System.out.println("Voting Rate for this election :"+votingrate);
        
    }
    
    public  void VotingRatebyAgeGroup(Connection con)
    {
        int agegroup1=countbyagegroup1(con);
        int agegroup2=countbyagegroup2(con);
        int agegroup3=countbyagegroup3(con);
        int agegroup4=countbyagegroup4(con);
        
        System.out.println("No of people voted in age group 18-29 : "+agegroup1);
        System.out.println("No of people voted in age group 30-39 : "+agegroup2);
        System.out.println("No of people voted in age group 40-59 : "+agegroup3);
        System.out.println("No of people voted in age group 60-99 : "+agegroup4);
    }
    
    public void winner(Connection con)
    {
        try
        {
            String query="SELECT * FROM CandidateTable WHERE NoOfVotes=(SELECT MAX(NoOfVotes) FROM CandidateTable);";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                System.out.println("------------------------------------------------------------- Election Result ---------------------------------------------------------------------");
                System.out.println("\nWinner   ->    Candidate "+rs.getString("Name")+" from "+rs.getString("PartyName")+"Party"+"("+rs.getString("PartySymbol")+")"+" has won the Election");
                
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect winner resultclass");
        }
    }
    
    public void VotesandPercentageperparty(Connection con)
    {
        voterDetails.CandidateandNoofvotes(con);
        int totalvotes=Votedpeoplecount(con);
        try
        {
            String query="select * from CandidateTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                int candidatevotes=voterDetails.NoofVotesofeachCandidate(con, rs.getString("CandidateId"));
//                System.out.println(candidatevotes);
//                System.out.println(totalvotes);
                float percentage=(float)(((float)candidatevotes*100)/((float)totalvotes));
                System.out.println("Percentage of Votes for Candidate:"+rs.getString("Name")+" : "+percentage);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayCandidateDEtailsin resultclass");
        }
        System.out.println();
    }
    
    public int NotVotedpeoplecount(Connection con)
    {
        int notvotedcount=0;
        try
        {
            String query="select count(*) as c from VoterTable where VotingStatus = 'no' ";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                notvotedcount=rs.getInt("c");
                //System.out.println();
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplaynotVotedpeople resultclass");
        }
        return notvotedcount;
    }
    
    public int Votedpeoplecount(Connection con)
    {
        int votedcount=0;
        try
        {
            String query="select count(*) as c from VoterTable where VotingStatus = 'yes' ";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                votedcount = rs.getInt("c");
                //System.out.println(votedcount);
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotedpeople resultclass");
        }
        return votedcount;
    }
    
    public int Votercount(Connection con)
    {
        int votercount=0;
        try
        {
            String query="select count(*) as c from VoterTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                votercount=rs.getInt("c");
                //System.out.println(rs.getInt("c"));
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotercount resultclass");
        }
        return votercount;
    }
    public int countbyagegroup1(Connection con)
    {
        int votercountbyage=0;
        try
        {
            String query="select count(*) as c from VoterTable where Age >= 18 and Age <30 and VotingStatus='yes'";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query); 
            while(rs.next())
            {
                votercountbyage=rs.getInt("c");
                //System.out.println(rs.getInt("c"));
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotercount(1) resultclass");
        }
        return votercountbyage;   
    }
    
    public int countbyagegroup2(Connection con)
    {
        int votercountbyage=0;
        try
        {
            String query="select count(*) as c from VoterTable where Age >= 30 and Age < 40 and VotingStatus='yes'";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query); 
            while(rs.next())
            {
                votercountbyage=rs.getInt("c");
                //System.out.println(rs.getInt("c"));
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotercount(2) resultclass");
        }
        return votercountbyage;
    }
    
    public int countbyagegroup3(Connection con)
    {
        int votercountbyage=0;
        try
        {
            String query="select count(*) as c from VoterTable where Age >= 40 and Age < 60 and VotingStatus='yes'";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query); 
            while(rs.next())
            {
                votercountbyage=rs.getInt("c");
                //System.out.println(rs.getInt("c"));
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotercount(3) resultclass");
        }
        return votercountbyage;
    }
    
    public int countbyagegroup4(Connection con)
    {
        int votercountbyage=0;
        try
        {
            String query="select count(*) as c from VoterTable where Age >= 60 and Age < 100 and VotingStatus='yes'";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query); 
            while(rs.next())
            {
                votercountbyage=rs.getInt("c");
                //System.out.println(rs.getInt("c"));
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect DisplayVotercount(4) resultclass");
        }
        return votercountbyage;
    }
    
}
