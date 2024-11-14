package Voter;

import personField.PersonField;

public class VoterField extends PersonField{
    
    private String pasword;
    private String VoterId;
    public String getPasword() {
        return pasword;
    }
    public void setPasword(String pasword) {
        this.pasword = pasword;
    }
    public String getVoterId() {
        return VoterId;
    }
    public void setVoterId(String voterId) {
        VoterId = voterId;
    }
    public VoterField(String name, String adharno, int age, String pasword, String voterId) {
        super(name, adharno, age);
        this.pasword = pasword;
        VoterId = voterId;
    }
    
    
    
    
    
    
}
