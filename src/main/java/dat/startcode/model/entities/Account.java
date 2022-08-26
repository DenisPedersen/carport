package dat.startcode.model.entities;

public class Account {

    private String email;
    private String password;
    private int role;
    private int accountId;

    public Account(String email, String password, int role, int accountId) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.accountId = accountId;
    }

    public Account(int accountId) {
        this.accountId = accountId;
    }

    public Account(String email, String password, int role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", accountId=" + accountId +
                '}';
    }
}
