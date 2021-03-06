package pojos;

public class Spartan {
      // this has to be match with documentation ---> gender, name, phone all should to be lower case
       private String gender;
       private String name;
       private int phone;

    public Spartan(String gender, String name, int phone) {
        this.gender = gender;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
