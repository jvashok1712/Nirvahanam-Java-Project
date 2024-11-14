package Candidate;

import java.sql.*;
import java.util.*;
import java.util.Scanner;
import java.sql.*;
import java.util.*;
import java.util.Scanner;

abstract class candidate{
    protected abstract void registerCandidate(Connection con) throws SQLException, ClassNotFoundException;
}

public class CandidateRegister extends candidate{
    public void registerCandidate(Connection con) throws SQLException, ClassNotFoundException {
      Boolean tryAgain = true;
      while (tryAgain) {
        try {
          Statement s = con.createStatement();
          String sql0 = " CREATE TABLE IF NOT EXISTS RegisteredCandidates(Name VARCHAR(30) NOT NULL,Aadhar VARCHAR(30) NOT NULL PRIMARY KEY ,age int NOT NULL,address VARCHAR(50) NOT NULL,password VARCHAR(30) NOT NULL,partyID VARCHAR(30) NOT NULL,ElectiontypID VARCHAR(30) NOT NULL,ConstituencyID VARCHAR(30) NOT NULL);";
          s.executeUpdate(sql0);
          String Query = "insert into RegisteredCandidates values(?,?,?,?,?,?,?,?) ;";
          PreparedStatement Stm = con.prepareStatement(Query);
          Scanner sc = new Scanner(System.in);
          // For Name
          Boolean try1Again = true;
          while (try1Again) {
            System.out.println("enter Your name as Candidate:");
            String FN = sc.nextLine();
            boolean isVaalid1 = true;
            for (int i = 0; i < FN.length(); i++) {
              char ch = FN.charAt(i);
              if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'z') {
                continue;
              }
              isVaalid1 = false;
            }
            if (isVaalid1) {
              String Name = FN;
              Stm.setString(1, Name);
              try1Again = false;
            } else {
              try1Again = true;
              System.out.println("invalid Field entered! Please Enter your First name again");
            }
          }

          // For Aadhar
          Boolean try3Again = true;
          while (try3Again) {
            System.out.println("Enter Your AdharNumber:");
            String adhr = sc.nextLine();
            boolean isVaaalid3 = true;
            for (int i = 0; i < adhr.length(); i++) {
              if ((adhr.charAt(i) >= '0' || adhr.charAt(i) <= '9') && adhr.length() == 12) {
                continue;
              }
              isVaaalid3 = false;
            }
            if (isVaaalid3) {
              String Aadhar = adhr;
              Stm.setString(2, Aadhar);
              try3Again = false;
            } else {
              try3Again = true;
              System.out.println("invalid Field entered! Please Enter your Aadhar again");
            }
          }

          // For Age
          Boolean try4Again = true;
          while (try4Again) {
            System.out.println("Enter Your Age:");
            String ag = sc.nextLine();
            boolean isVaalid4 = true;
            int aage = Integer.parseInt(ag);
            for (int i = 0; i < ag.length(); i++) {
              // char ch=ag.charAt(i);
              if ((ag.charAt(i) >= '0' || ag.charAt(i) <= '9') && (aage >= 18 && aage <= 100)) {
                continue;
              }
              isVaalid4 = false;
            }
            if (isVaalid4) {
              String Age = ag;
              Stm.setString(3, Age);
              try4Again = false;
            } else {
              try4Again = true;
              System.out.println("invalid Field entered! Please Enter your Age again");
            }
          }

          System.out.println("Enter Your City:");
          String Address = sc.nextLine();
          Stm.setString(4, Address);

          // For password
          System.out.println("Set new password of Candidate:");
          String PWD = sc.nextLine();
          Stm.setString(5, PWD);

          // partyID
          Boolean try7Again = true;
          while (try7Again) {
            //System.out.println("Valid PartyId's are :");
            System.out.println("Enter Your PartyId:");
            String PID = sc.nextLine();
            boolean isVaalid7 = true;
            for (int i = 0; i < PID.length(); i++) {
              char ch = PID.charAt(i);
              if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'z' || (ch >= '0' || ch <= '9')) {
                continue;
              }
              isVaalid7 = false;
            }
            if (isVaalid7 && checkpartyid(con,PID))
            {
              String partyID = PID;
              Stm.setString(6, partyID);
              try7Again = false;
            } else {
              try7Again = true;
              System.out.println("invalid Field entered! Please Enter your partyID again");
            }
          }

          // ElectiontypeID
          Boolean try8Again = true;
          while (try8Again) {
            System.out.println("enter ElectionID as Candidate: ");
            String EID = sc.nextLine();
            boolean isVaalid8 = true;
            for (int i = 0; i < EID.length(); i++) {
              char ch = EID.charAt(i);
              if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'z' || (ch >= '0' || ch <= '9')) {
                continue;
              }
              isVaalid8 = false;
            }
            if (isVaalid8 && checkelectionid(con,EID)) {
              String ElectiontypeID = EID;
              Stm.setString(7, ElectiontypeID);
              try8Again = false;
            } else {
              try8Again = true;
              System.out.println("invalid Field entered! Please Enter your ElectiontypeID again");
            }
          }

          // ConstituencyID
          Boolean try9Again = true;
          while (try9Again) {
            System.out.println("Enter Your ConstituencyID as Candidate");
            String CID = sc.nextLine();
            boolean isVaalid9 = true;
            for (int i = 0; i < CID.length(); i++) {
              char ch = CID.charAt(i);
              if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'z' || (ch >= '0' || ch <= '9')) {
                continue;
              }
              isVaalid9 = false;
            }
            if (isVaalid9) {
              String ConstituencyID = CID;
              Stm.setString(8, ConstituencyID);
              try9Again = false;
            } else {
              try9Again = true;
              System.out.println("invalid Field entered! Please Enter your ConstituencyID again");
            }
          }

          
          Stm.execute();
          tryAgain = false;
          sc.close();
          con.close();
        }

        catch (SQLIntegrityConstraintViolationException e) {
          // Print line number if exception occurred
          tryAgain = true;
          System.out.println("Aadhar entered is already taken by someone else! Please Enter your details again");
        } catch (NumberFormatException e) {
          System.out.println("You didn't enter a valid integer for age");
        } catch (InputMismatchException e) {
          tryAgain = true;
          System.out.println("Please Enter your details again ! Entered Fields are invalid .");
        }

      }
      System.out.println("Hureeee!! You Succesfully Registered");
    }
    
    public static boolean checkpartyid(Connection con,String id)
    {
            try
            {
                String query="select * from PartyTable";
                java.sql.Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(query);
                while(rs.next())
                {
                     if(id.equals(rs.getString("PartyId")))
                     {
                         return true;
                     }
                }
                st.close();
            }
            catch(Exception e)
            {
                System.out.println("Unable to connect checkpartyid");
            }
        return false;
    }
    
    public static boolean checkelectionid(Connection con,String id)
    {
            try
            {
                String query="select * from Electiondetails";
                java.sql.Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(query);
                while(rs.next())
                {
                    if(id.equals(rs.getString("electionid")))
                    {
                        return true;
                    }
                    
                }
                st.close();
            }
            catch(Exception e)
            {
                System.out.println("Unable to connect checkelectionid");
            }
        return false;
    }
  }