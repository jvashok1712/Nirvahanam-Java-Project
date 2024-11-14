import java.sql.*;
import java.util.Scanner;

import Admin.AdminManagement;
import Admin.Adminlogin;
import Admin.ApproveCandidate;
import Admin.ApproveVoter;
import Candidate.CandidateClass;
import Candidate.CandidateRegister;
import ConnectionToDbms.ConnectionClass;
import Election.ImportElectionData;
import Party.importPartydata;
import ResultModule.ResultClass;
import User.Register;
import User.UserDetailsbylogin;
import Voter.Voter;
import Voter.VoterDetails;
import constituency.ImportConstituencyData;
import updatecsv.updateviacsv;

public class Main {
    public static Connection con = null;
    
    public static void main(String[] args) throws Exception
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n-------------Welcome to OOPS Voting Management Project-------------------\n");
        
        try {
            switch(args[0])
            {
                ///////////////////////////////////////
                case "-help":
                {
                    System.out.println("\t  <Commands> \t\t\t: \t\t<Uses>");
                    System.out.println("-upload -election \t\t\t: command to import csv-election file to the database");
                    System.out.println("-upload -party \t\t\t\t: command to import csv-party file to the database");
                    System.out.println("-upload -constituency \t\t\t: command to import csv-constituency file to the database");
                    System.out.println("-upload -Admindetails \t\t\t: command to import csv-Admin file to the database");
                    System.out.println("-updateviacsv -party \t\t\t: command to update the data in database by csv file");
                    System.out.println("-updateviacsv -constituency \t\t: command to update the data in database by csv file");
                    System.out.println("-register -user \t\t\t: command to Register to become a voter");
                    System.out.println("-register -candidate \t\t\t: command to Register to become a candidate");
                    System.out.println("-requestvid \t\t\t\t: command to get VoterId of user");
                    System.out.println("-AdminLogin -Approveuser \t\t: command to approve the user requests to voter");
                    System.out.println("-AdminLogin -Approvecandidate \t\t: command to approve the candidate requests ");
                    System.out.println("-Voter-login -getdetails \t\t: command to login as a voter");
                    System.out.println("-Voter-login -vote \t\t\t: command to vote for the candidate");
                    System.out.println("-candidate-detail -login \t\t: command to login as candidate");
                    System.out.println("-searchbyid -voter \t\t\t: command to search voter by id");
                    System.out.println("-searchbyid -candidate \t\t\t: command to search candidate by id");
                    System.out.println("-searchbyid -Admin \t\t\t: command to search Admin by id");
                    System.out.println("-searchbyname -voter \t\t\t: command to search(partial String) voter by name");
                    System.out.println("-searchbyname -candidate \t\t: command to search(partial String) candidate by name");
                    System.out.println("-searchbyname -Admin \t\t\t: command to search(partial String) Admin by name");
                    System.out.println("-searchbyage -voter \t\t\t: command to search voters by age");
                    System.out.println("-searchbyage -candidate \t\t: command to search candidates by age");
                    System.out.println("-update -voter \t\t\t\t: command to update name of voter by VoterId");
                    System.out.println("-update -candidate \t\t\t: command to update name of candidate by CandidateId");
                    System.out.println("-delete -voter \t\t\t\t: command to delete a voter by voterId");
                    System.out.println("-delete -candidate \t\t\t: command to delete a candidate by candidateId");
                    System.out.println("-statistics -votedpeople \t\t: command to find people who are voted");
                    System.out.println("-statistics -notvotedpeople \t\t: command to find people who are not voted");
                    System.out.println("-statistics -votedpeoplecount \t\t: command to find count of people who voted");
                    System.out.println("-statistics -notvotedpeoplecount \t: command to find count of people who not voted");
                    System.out.println("-statistics -votebyagegroup 18-30 \t: command to find count of people voted in age group 18-30");
                    System.out.println("-statistics -votebyagegroup 30-40 \t: command to find count of people voted in age group 30-40");
                    System.out.println("-statistics -votebyagegroup 40-60 \t: command to find count of people voted in age group 40-60");
                    System.out.println("-statistics -votebyagegroup 60-100 \t: command to find count of people voted in age group 60-100");
                    System.out.println("-result -votingrate \t\t\t: command gives voting rate of the election");
                    System.out.println("-result -votebyage \t\t\t: comamnd gives voting rate by age group");
                    System.out.println("-result -winner \t\t\t: command gievs the winner of election ");
                    System.out.println("-result -partyvotes \t\t\t: command to find parties with their votes");

                    break;
                }
                
                ///////////////////////////////////////
                case "-upload":
                {
                    switch(args[1])
                    {
                        case "-election":
                        {
                            con = ConnectionClass.connecttodbms();
                            ImportElectionData.loadData(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-party":
                        {
                            con = ConnectionClass.connecttodbms();
                            importPartydata.loadData(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-constituency":
                        {
                            con = ConnectionClass.connecttodbms();
                            ImportConstituencyData.loadData(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-Admindetails":
                        {
                            con = ConnectionClass.connecttodbms();
                            AdminManagement.loadData(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                
                case "-updateviacsv":
                {
                    switch(args[1])
                    {
                        case "-party":
                        {
                            con = ConnectionClass.connecttodbms();
                            updateviacsv.updateTableparty(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-constituency":
                        {
                            con = ConnectionClass.connecttodbms();
                            updateviacsv.updateTableconstituency(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        
                    }
                    break;
                }
                
                
                
                ///////////////////////////////////////
                case "-create" :
                case "-register" :
                {
                    switch(args[1])
                    {
                        case "-user":
                        {
                            con = ConnectionClass.connecttodbms();
                            Register ru=new Register();
                            System.out.println("Enter Details to register as a Voter....");
                            ru.registeruser(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-candidate":
                        {
                            con = ConnectionClass.connecttodbms();
                            CandidateRegister rc=new CandidateRegister();
                            System.out.println("Enter Details to register as a Candidate....");
                            rc.registerCandidate(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                case "-requestvid":
                {
                    con = ConnectionClass.connecttodbms();
                    System.out.println("Enter Your Adharno: ");
                    String Ano=input.nextLine();
                    System.out.println("Enter Your Password: ");
                    String ps=input.nextLine();
                    UserDetailsbylogin.userlogin(con,Ano,ps);
                    ConnectionClass.disconnecttodbms(con);
                    break;
                }
                
                ////////////////////////////////////////
                case "-AdminLogin":
                {
                    con = ConnectionClass.connecttodbms();
                    Adminlogin a=new Adminlogin();
                    System.out.println("Enter Your AdminId: ");
                    String Admid=input.nextLine();
                    System.out.println("Enter Your Password: ");
                    String pass=input.nextLine();
                    int k=a.loginAdmin(con,Admid,pass);
                    ConnectionClass.disconnecttodbms(con);
                        switch(args[1])
                        {
                            case "-Approveuser":
                            {
                                con = ConnectionClass.connecttodbms();
                                if(k==1)
                                {
                                    ApproveVoter.getRegisterMemberintolist(con);
                                    ApproveVoter.getListDetailstoVoterTable(con);
                                }
                                ConnectionClass.disconnecttodbms(con);
                                break;
                            }
                            case "-Approvecandidate":
                            {
                                con = ConnectionClass.connecttodbms();
                                if(k==1)
                                {
                                    ApproveCandidate.getRegisterMemberintolist(con);
                                    ApproveCandidate.getListDetailstoCandidateTable(con);
                                }
                                ConnectionClass.disconnecttodbms(con);
                                break;
                            }
                        }
                    break;
                }
                
                ////////////////////////////////////////
                case "-Voter-login":
                {
                    con = ConnectionClass.connecttodbms();
                    System.out.println("Enter Your VoterId: ");
                    String voterIdl=input.nextLine();
                    System.out.println("Enter Your Password: ");
                    String ps=input.nextLine();
                    Voter.login(con,voterIdl,ps);
                    ConnectionClass.disconnecttodbms(con);
                    switch(args[1])
                    {
                        case "-getdetails":
                        {
                            con = ConnectionClass.connecttodbms();
                            Voter.getvoterdetails(con, voterIdl);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-vote":
                        {
                            con = ConnectionClass.connecttodbms();
                            Voter.vote(con,voterIdl);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                /////////////////////////////////////////
                case "-candidate-detail":
                {
                    switch(args[1])
                    {
                        case "-login":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Your CandidateId: ");
                            String candidateIdi=input.nextLine();
                            System.out.println("Enter Your Password: ");
                            String ps=input.nextLine();
                            CandidateClass.loginCandidate(con,candidateIdi,ps);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                ///////////////////////////////////////////
                case "-searchbyid":
                {
                    switch(args[1])
                    {
                        case "-voter":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter VoterId: ");
                            String voterIds=input.nextLine();
                            VoterDetails v=new VoterDetails();
                            v.SearchVoter(con,voterIds);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-candidate":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Your CandidateId: ");
                            String candidateIds=input.nextLine();
                            CandidateClass.SearchCandidate(con,candidateIds);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-Admin":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Your AdminId: ");
                            String AdminIds=input.nextLine();
                            AdminManagement.SearchAdmin(con,AdminIds);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                ///////////////////////////////////////////
                case "-searchbyname":
                {
                    switch(args[1])
                    {
                        case "-voter":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Voter name: ");
                            String name=input.nextLine();
                            VoterDetails v=new VoterDetails();
                            v.SearchVoterbyname(con,name);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-candidate":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Candidate name: ");
                            String name=input.nextLine();
                            CandidateClass.SearchCandidatebyname(con,name);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-Admin":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Your AdminName: ");
                            String Adminnam=input.nextLine();
                            AdminManagement.SearchAdminbyname(con,Adminnam);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                ////////////////////////////////////////////
                case "-searchbyage":
                {
                    switch(args[1])
                    {
                        case "-voter":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Voter age: ");
                            int name=input.nextInt();
                            input.nextLine();
                            VoterDetails v=new VoterDetails();
                            v.SearchVoterbyage(con,name);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-candidate":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter Candidate name: ");
                            int name=input.nextInt();
                            input.nextLine();
                            CandidateClass.SearchCandidatebyage(con,name);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                
                ///////////////////////////////////////////
                case "-update":
                {
                    switch(args[1])
                    {
                        case "-voter":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter VoterId:");
                            String vid=input.nextLine();
                            System.out.println("Enter New Name of Voter");
                            String newnamev=input.nextLine();
                            Voter.UpdateVoter(con, vid, newnamev);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-candidate":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter CandidateId:");
                            String cid=input.nextLine();
                            System.out.println("Enter New Name of Candidate");
                            String newnamec=input.nextLine();
                            CandidateClass.UpdateCandidate(con, cid, newnamec);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                
                ///////////////////////////////////////////
                case "-delete":
                {
                    switch(args[1])
                    {
                        case "-voter":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter VoterId:");
                            String vid=input.nextLine();
                            Voter.deleteVoter(con, vid);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-candidate":
                        {
                            con = ConnectionClass.connecttodbms();
                            System.out.println("Enter CandidateId:");
                            String cid=input.nextLine();
                            CandidateClass.deleteCandidate(con, cid);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                /////////////////////////////////////////////
                case "-statistics":
                {
                    switch(args[1])
                    {
                        case "-votedpeople":
                        {
                            con = ConnectionClass.connecttodbms();
                            VoterDetails v1=new VoterDetails();
                            v1.Votedpeople(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-notvotedpeople":
                        {
                            con = ConnectionClass.connecttodbms();
                            VoterDetails v1=new VoterDetails();
                            v1.NotVotedpeople(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-votedpeoplecount":
                        {
                            con = ConnectionClass.connecttodbms();
                            ResultClass v1=new ResultClass();
                            int count=v1.Votedpeoplecount(con);
                            System.out.println("No of people Voted: "+count);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-notvotedpeoplecount":
                        {
                            con = ConnectionClass.connecttodbms();
                            ResultClass v1=new ResultClass();
                            int count=v1.NotVotedpeoplecount(con);
                            System.out.println("No of people Registered But Not Voted: "+count);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-votebyagegroup":
                        {
                            switch(args[2])
                            {
                                case "18-30":
                                {
                                    ResultClass v1=new ResultClass();
                                    int count=v1.countbyagegroup1(con);
                                    System.out.println("No of people Voted At age (18-30)"+count);
                                    break;
                                }
                                case "30-40":
                                {
                                    ResultClass v1=new ResultClass();
                                    int count=v1.countbyagegroup2(con);
                                    System.out.println("No of people Voted At age (30-40)"+count);
                                    break;
                                }
                                case "40-60":
                                {
                                    ResultClass v1=new ResultClass();
                                    int count=v1.countbyagegroup3(con);
                                    System.out.println("No of people Voted At age (40-60)"+count);
                                    break;
                                }
                                case "60-100":
                                {
                                    ResultClass v1=new ResultClass();
                                    int count=v1.countbyagegroup4(con);
                                    System.out.println("No of people Voted At age (60-100)"+count);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                
                ////////////////////////////////////////////
                case "-result":
                {
                    switch(args[1])
                    {
                        case "-votingrate":
                        {
                            con = ConnectionClass.connecttodbms();
                            ResultClass r1=new ResultClass();
                            r1.Votingrate(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-votebyage":
                        {
                            con = ConnectionClass.connecttodbms();
                            ResultClass r1=new ResultClass();
                            r1.VotingRatebyAgeGroup(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-winner":
                        {
                            con = ConnectionClass.connecttodbms();
                            ResultClass r1=new ResultClass();
                            r1.winner(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                        case "-partyvotes":
                        {
                            con = ConnectionClass.connecttodbms();
                            ResultClass r1=new ResultClass();
                            r1.VotesandPercentageperparty(con);
                            ConnectionClass.disconnecttodbms(con);
                            break;
                        }
                    }
                    break;
                }
                
                //////////////////////////////////////////////
                default :
                {
                    System.out.println("Wrong command!,Enter Valid command");
                }
            }
             
            
        }catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Unknown error occured! (Could be Wrong number of inputs)\n");
        }
    }
}
