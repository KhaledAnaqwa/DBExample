package ps.jawwal.dbexample.db;

public class User {
    private int id ;
    private String FName ;
    private String LName ;
    private String Address ;

    public User(int id, String FName, String LName, String address) {
        this.id = id;
        this.FName = FName;
        this.LName = LName;
        Address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
