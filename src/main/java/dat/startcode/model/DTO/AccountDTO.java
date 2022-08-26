package dat.startcode.model.DTO;

public class AccountDTO {

    private int accountId;
    private String email;
    private String password;
    private int role;
    private int customerId;
    private String name;
    private String address;
    private String city;
    private int zip;

    public AccountDTO(int accountId, String email, String password, int role, int customerId, String name, String address, String city, int zip) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId=" + accountId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                '}';
    }
}
