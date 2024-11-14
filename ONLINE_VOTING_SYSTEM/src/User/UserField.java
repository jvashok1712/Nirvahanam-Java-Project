package User;

import personField.PersonField;

public class UserField extends PersonField{
    
    private String City;
    private String newpassword;
    
    public UserField(String name, String adharno, int age, String city, String newpassword) {
        super(name, adharno, age);
        City = city;
        this.newpassword = newpassword;
    }
    
    public String getCity() {
        return City;
    }
    public void setCity(String city) {
        City = city;
    }
    public String getNewpassword() {
        return newpassword;
    }
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
    
    
}
