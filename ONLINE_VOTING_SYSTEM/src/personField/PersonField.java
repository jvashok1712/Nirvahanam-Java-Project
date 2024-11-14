package personField;

public class PersonField {
    private String Name;
    private String Adharno;
    private int Age;
    public PersonField(String name, String adharno, int age) {
        super();
        Name = name;
        Adharno = adharno;
        Age = age;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAdharno() {
        return Adharno;
    }
    public void setAdharno(String adharno) {
        Adharno = adharno;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    
    
}
