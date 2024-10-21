public class Person {
    protected String socialSecurityNumber;
    protected String name;
    protected String date;

    public Person(String socialSecurityNumber, String name, String date) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.name = name;
        this.date = date;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "Personnummer: " + socialSecurityNumber +
                        ", namn: " + name +
                        ", datum: " + date;
    }
}
