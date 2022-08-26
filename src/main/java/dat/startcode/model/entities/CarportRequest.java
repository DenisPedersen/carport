package dat.startcode.model.entities;

public class CarportRequest {

    @Override
    public String toString() {
        return "CarportRequest{" +
                "width=" + width +
                ", length=" + length +
                ", roofType='" + roofType + '\'' +
                ", shedWidth=" + shedWidth +
                ", shedLength=" + shedLength +
                ", roofIncline=" + roofIncline +
                '}';
    }

    int requestId;
    int width;
    int length;
    String roofType;
    int shedWidth;
    int shedLength;
    int roofIncline;
    int customerId;
    boolean isApproved;

    public CarportRequest(int requestId, int width, int length, String roofType, int roofIncline, boolean isApproved, int shedLength, int shedWidth, int customerId) {
        this.width = width;
        this.length = length;
        this.roofType = roofType;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.roofIncline = roofIncline;
        this.requestId = requestId;
        this.customerId = customerId;
        this.isApproved = isApproved;
    }


    public CarportRequest(int width, int length, String roofType, int shedLength, int shedWidth, int customerId) {
        this.width = width;
        this.length = length;
        this.roofType = roofType;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.customerId = customerId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }


    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getRoofIncline() {
        return roofIncline;
    }

    public void setRoofIncline(int roofIncline) {
        this.roofIncline = roofIncline;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRequestId() {
        return requestId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public CarportRequest(int width, int length, String roofType, int customerId) {
        this.width = width;
        this.length = length;
        this.roofType = roofType;
        this.customerId = customerId;
    }

    public CarportRequest(int width, int length, String roofType, int shedWidth, int shedLength, int roofIncline, int customerId) {
        this.width = width;
        this.length = length;
        this.roofType = roofType;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.roofIncline = roofIncline;
        this.customerId = customerId;
    }
}
