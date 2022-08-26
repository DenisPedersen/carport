package dat.startcode.model.entities;

public class Customer extends Account {

    private int customerId;
    private String name;
    private String address;
    private String city;
    private int zip;
    private int mobile;


    public Customer(String email, String password, int role, int customerId, String name, String address, String city, int zip, int mobile, int accountId) {
        super(email, password, role, accountId);
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.mobile = mobile;

    }

    public Customer(int accountId, int customerId, String name, String address, String city, int zip, int mobile) {
        super(accountId);
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.mobile = mobile;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", mobile=" + mobile +
                '}';
    }
}
