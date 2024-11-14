package Candidate;

import personField.PersonField;

public class CandidateField extends PersonField {
    private String Party;
    private String Symbol;
    private String Address;
    private String newpassword;
    
    public CandidateField(String name, String adharno, int age, String party, String symbol, String address,
            String newpassword) {
        super(name, adharno, age);
        Party = party;
        Symbol = symbol;
        Address = address;
        this.newpassword = newpassword;
    }

    public String getParty() {
        return Party;
    }

    public void setParty(String party) {
        Party = party;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
    
    
    
}



