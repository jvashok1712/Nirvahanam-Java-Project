package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ApproveCandidate {
    static ArrayList<String> Namelist=new ArrayList<String>();
    static ArrayList<String> Anolist=new ArrayList<String>();
    static ArrayList<String> PartyNamelist=new ArrayList<String>();
    static ArrayList<String> Symbollist=new ArrayList<String>();
    static ArrayList<String> Electionnamelist=new ArrayList<String>();
    static ArrayList<String> CandidateIdlist=new ArrayList<String>();
    static ArrayList<String> passwordlist=new ArrayList<String>();
    //ArrayList<Integer> NoOfVotes=new ArrayList<Integer>();
    static Integer count=0;
    
    public static void getRegisterMemberintolist(Connection con)
    {
        try
        {
            String query="select * from registeredcandidates";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            while(rs.next())
            {
                String Name=rs.getString("Name");
                Namelist.add(Name);
                //System.out.println(Name);
                
                String Adharno=rs.getString("Aadhar");
                Anolist.add(Adharno);
                //System.out.println(Adharno);
                
                
                String PartyId=rs.getString("PartyID");
                
                String partyname=getPartyName(con,PartyId);
                PartyNamelist.add(partyname);
                
                String symbol=getSymbol(con,PartyId);
                Symbollist.add(symbol);
                //System.out.println(PartyId);
                
                String ElectionId=rs.getString("ElectiontypID");
                
                String electionname=getElectionName(con,ElectionId);
                Electionnamelist.add(electionname);
                
                
                String CandidateId="2022"+Adharno;
                CandidateIdlist.add(CandidateId);
                //System.out.println(CandidateId);
                
                String setnewpassword=rs.getString("password");
                passwordlist.add(setnewpassword);
                
                //System.out.println(setnewpassword);
//                NoOfVotes.add(0);
            }
            st.close();
            truncateTable(con);
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect add to Candidatelist");
        }
    }
    
    public static void getListDetailstoCandidateTable(Connection con)
    {
        try {
            String query = " insert into CandidateTable(Name,Ano,PartyName,PartySymbol,ElectionName,CandidateId,Password) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
       
            for(int i = Namelist.size() - 1; i >= 0; i--) 
            {
                String Name=Namelist.get(i);
                //System.out.println(Namelist.get(i));
                //Namelist.remove(Namelist.size() - 1);
                preparedStmt.setString(1, Name);
                
                String AdharNO=Anolist.get(i);
                //Anolist.remove(Anolist.size() - 1);
                preparedStmt.setString(2, AdharNO);
                
                String party=PartyNamelist.get(i);
                //Partylist.remove(Partylist.size() - 1);
                preparedStmt.setString(3, party);
                
                String symbol=Symbollist.get(i);
                //Symbollist.remove(Symbollist.size() - 1);
                preparedStmt.setString(4, symbol);
                
                String electionname=Electionnamelist.get(i);
                preparedStmt.setString(5, electionname);
                
                String Candidateid=CandidateIdlist.get(i);
                //CandidateIdlist.remove(CandidateIdlist.size() - 1);
                preparedStmt.setString(6, Candidateid);
                
                String Passwords=passwordlist.get(i);
                //passwordlist.remove(passwordlist.size() - 1);
                preparedStmt.setString(7, Passwords);
                
                //String loginstatus=LoginStatuslist.get(LoginStatuslist.size() - 1);
                //LoginStatuslist.remove(LoginStatuslist.size() - 1);
                //preparedStmt.setString(5, loginstatus);
                
//                int NoofVotes=NoOfVotes.get(NoOfVotes.size() - 1);
//                NoOfVotes.remove(NoOfVotes.size() - 1);
//                preparedStmt.setInt(6, NoofVotes);
                
                int rs = preparedStmt.executeUpdate();
                if (rs == 0) {
                    System.out.println("Candidate Insert failed!!!");
                } else {
    
                    System.out.printf("Candidate with ID:%s,Name:%s isapproved",Candidateid,Name);
                    System.out.println();
                }
                
            }
        } catch (Exception e) {
            System.out.println("Unable to connect inserting data to CandidateTable");
        }
        Namelist.clear();
        Anolist.clear();
        CandidateIdlist.clear();
        PartyNamelist.clear();
        Symbollist.clear();
        Electionnamelist.clear();
        passwordlist.clear();
        
    }
    
    public static void truncateTable(Connection con)
    {
        try
        {
            String query = "truncate registeredcandidates";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            int rs = preparedStmt.executeUpdate();
            
        }
        catch (Exception e) {
            System.out.println("Unable to connect truncate data from userregisterTable");
        }
    }
    
    public String CandidateIDGenerator(String firstName,String Aadhar) {
        String res1 = firstName.substring(0, 2);
        String res2 =  Aadhar.substring(Aadhar.length() - 4);
        
        String res4 = res1  + res2;
        
        String res5 = count.toString().length() == 1 ? ("0000" + count)
                : count.toString().length() == 2 ? ("0" + count) : count.toString();
        count = count + 1;
        String finalResult = res4 + res5;
        return finalResult;
    }

    public void DeleteCandidate(Connection con,String candidateId)
    {
        try
        {
            String query = "delete from CandidateTable " + "where CandidateId= ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, candidateId);

            int row = preparedStatement.executeUpdate();
            if(row==0)
            {
                System.out.println("candidate not Deleted");
            }
            else
            {
                System.out.println("candidate Deleted");
            }
                preparedStatement.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect deleteCandidate");
        }
    }
    
    public static String getSymbol(Connection con,String partyId)
    {
        try
        {
            String query="select * from PartyTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(partyId.equals(rs.getString("Partyid")))
                {
                    String Symbol=rs.getString("Symbol");
                    return Symbol;
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect get symbol");
        }
        return null;
    }
    
    public static String getPartyName(Connection con,String partyId)
    {
        try
        {
            String query="select * from PartyTable";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(partyId.equals(rs.getString("Partyid")))
                {
                    String name=rs.getString("Name");
                    //System.out.println(name);
                    return name;
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect get partyname");
        }
        return null;
    }
    
    public static String getElectionName(Connection con,String ElectionId)
    {
        try
        {
            String query="select * from electiondetails";
            java.sql.Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next())
            {
                if(ElectionId.equals(rs.getString("electionid")))
                {
                    String name=rs.getString("electiontype");
                    return name;
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("Unable to connect get Electionname");
        }
        return null;
    }
    
    
}
