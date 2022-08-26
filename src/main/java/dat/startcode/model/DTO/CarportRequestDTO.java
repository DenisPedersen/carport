package dat.startcode.model.DTO;

public class CarportRequestDTO {
    private int customerId;
    private int carportRequestId;
    private int width;
    private int length;
    private String roof;
    private int roofIncline;
    private boolean isApproved;
    private int shedLength;
    private int shedWidth;
    private String name;
    private String address;
    private String city;
    private int zip;
    private int mobile;
    private int accountId;

    public CarportRequestDTO(int customerId, int carportRequestId, int width, int length, String roof, int roofIncline, boolean isApproved, int shedLength, int shedWidth, String name, String address, String city, int zip, int mobile, int accountId) {
        this.customerId = customerId;
        this.carportRequestId = carportRequestId;
        this.width = width;
        this.length = length;
        this.roof = roof;
        this.roofIncline = roofIncline;
        this.isApproved = isApproved;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.mobile = mobile;
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCarportRequestId() {
        return carportRequestId;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public String getRoof() {
        return roof;
    }

    public int getRoofIncline() {
        return roofIncline;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public int getShedLength() {
        return shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
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

    public int getMobile() {
        return mobile;
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "CarportRequestDTO{" +
                "customerId=" + customerId +
                ", carportRequestID=" + carportRequestId +
                ", width=" + width +
                ", length=" + length +
                ", roof='" + roof + '\'' +
                ", roofIncline=" + roofIncline +
                ", isApproved=" + isApproved +
                ", shedLength=" + shedLength +
                ", shedWidth=" + shedWidth +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip=" + zip +
                ", mobile=" + mobile +
                ", accountId=" + accountId +
                '}';
    }

}
