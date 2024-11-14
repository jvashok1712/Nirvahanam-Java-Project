package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import ConnectionToDbms.ConnectionClass;
import java.sql.*;
import java.util.*;
import java.util.Scanner;

abstract class user{
    protected abstract void registeruser(Connection con) throws SQLException, ClassNotFoundException;
}

public class Register extends user{
    public void registeruser(Connection con) throws SQLException, ClassNotFoundException {
      Boolean tryAgain = true;
      while (tryAgain) {
        try {
          Statement s = con.createStatement();
          String sql0 = " CREATE TABLE IF NOT EXISTS Users(Name VARCHAR(30),Aadhar VARCHAR(30) NOT NULL PRIMARY KEY ,age int NOT NULL,address VARCHAR(50),password VARCHAR(30) NOT NULL);";
          s.executeUpdate(sql0);
          String Query = "insert into Users values(?,?,?,?,?) ;";
          PreparedStatement Stm = con.prepareStatement(Query);
          Scanner sc = new Scanner(System.in);

          // For Name
          Boolean trryAgain = true;
          while (trryAgain) {
            System.out.println("Enter Your name as user:");
            String FN = sc.nextLine();
            boolean isVaalid = true;
            for (int i = 0; i < FN.length(); i++) {
              char ch = FN.charAt(i);
              if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'z') {
                continue;
              }
              isVaalid = false;
            }
            if (isVaalid) {
              String FirstName = FN;
              Stm.setString(1, FirstName);
              trryAgain = false;
            } else {
              tryAgain = true;
              System.out.println("invalid Field entered! Please Enter your First name again");
            }
          }

          // For Aadhar
          Boolean trrrryAgain = true;
          while (trrrryAgain) {
            System.out.println("Enter Your AdharNumber:");
            String adhr = sc.nextLine();
            boolean isVaaalid = true;
            for (int i = 0; i < adhr.length(); i++) {
              if ((adhr.charAt(i) >= '0' || adhr.charAt(i) <= '9') && adhr.length() == 12) {
                continue;
              }
              isVaaalid = false;
            }
            if (isVaaalid) {
              String Aadhar = adhr;
              Stm.setString(2, Aadhar);
              trrrryAgain = false;
            } else {
              trrrryAgain = true;
              System.out.println("invalid Field entered! Please Enter your Aadhar again");
            }
          }

          // For Age
          Boolean trrrrryAgain = true;
          while (trrrrryAgain) {
            System.out.println("Enter Your Age:");
            String ag = sc.nextLine();
            boolean isVaalid = true;
            int aage = Integer.parseInt(ag);
            for (int i = 0; i < ag.length(); i++) {
              // char ch=ag.charAt(i);
              if ((ag.charAt(i) >= '0' || ag.charAt(i) <= '9') && (aage >= 18 && aage <= 100)) {
                continue;
              }
              isVaalid = false;
            }
            if (isVaalid) {
              String Age = ag;
              Stm.setString(3, Age);
              trrrrryAgain = false;
            } else {
              trrrrryAgain = true;
              System.out.println("invalid Field entered! Please Enter your Age again");
            }
          }

          System.out.println("Enter Your City:");
          String Address = sc.nextLine();
          Stm.setString(4, Address);

          // For password
          System.out.println("Set new password of user:");
          String Password = sc.nextLine();
          Stm.setString(5, Password);

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
  }
