import java.sql.*;
import Admin.ApproveCandidate;
import Admin.ApproveVoter;
import Candidate.CandidateClass;
import Candidate.CandidateRegister;
import ConnectionToDbms.ConnectionClass;
import Election.ImportElectionData;
import Party.importPartydata;
import User.Register;
import Voter.Voter;
import Voter.VoterDetails;
import constituency.ImportConstituencyData;


public class VSMain {
    
    public static Connection con = null;
    
    public static void main(String[] args) throws Exception
    {
        Voter VoterDetails=new Voter();
        String[] x1=new String[10];
        String[] x2=new String[10];
        
        x1[0]="swert";
        x1[1]="126";
        x1[2]="25";
        x1[3]="tpg";
        x1[4]="sewr";
        
        x2[0]="ghhj";
        x2[1]="100";
        x2[2]="45";
        x2[3]="kkd";
        x2[4]="p123";
        
        String[] x=new String[10];
        x[0]="siva";
        x[1]="123";
        x[2]="80";
        x[3]="tdp";
        x[4]="cycle";
        x[5]="tpg";
        x[6]="sai";
        //System.out.printf("Enter Your Details to Register\n YourName\t YourAdhaarNo\t YourAge\t YourCity\t SetApassword for future login\n");
        con = ConnectionClass.connecttodbms();
        //register voter
//        UserField u1=new UserField(x1[0],x1[1],Integer.parseInt(x1[2]),x1[3],x1[4]);
//        Register.insertRegisteredVoter(con, u1);
        
        //VoterDetails.getVoterDetails(con,100);
        
        //approve voter
//        ApproveVoter.getRegisterMemberintolist(con);
//        ApproveVoter.getListDetailstoVoterTable(con);
       
        //register candidate
//        CandidateField c1=new CandidateField(x[0],x[1],Integer.parseInt(x[2]),x[3],x[4],x[5],x[6]);
//        CandidateRegister.insertRegisterCandidate(con,c1);
        
        //approve candidate
//       ApproveCandidate.getRegisterMemberintolist(con);
//        ApproveCandidate.getListDetailstoCandidateTable(con);
        
        // Voter module
//        String id="2022123";
//        String ps="sai";
//        Voter.login(con,id,ps);
//        Voter.vote(con,"2022123");
        
        //Candidate module
//        String cid="2022123";
//        String cps="sai";
//        CandidateClass.loginCandidate(con,cid,cps);
        
        //voted people
//        System.out.println("Voted people");
//        OngoingElectionDetailsClass.Votedpeople(con);
//        
//        System.out.println("Not-Voted people");
//        OngoingElectionDetailsClass.NotVotedpeople(con);
        
        //no of votes for a candidate
        //String idc2="2022123";
        //VoterDetails.NoofVotesofeachCandidate(con, idc2);
        //VoterDetails.Votercount(con);
        //VoterDetails.Votedpeoplecount(con);
        
        //ImportElectionData.loadData(con);
        //importPartydata.loadData(con);
        ImportConstituencyData.loadData(con);
        
        ConnectionClass.disconnecttodbms(con);
       
        
    }
}
