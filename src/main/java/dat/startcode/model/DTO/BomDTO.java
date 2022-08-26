package dat.startcode.model.DTO;

public class BomDTO {

    int bomId;
    int bomlineId;
    String name;
    int length;
    int quantity;
    String unit;
    String description;
    int materialId;
    int price;
    int typeId;
    int width;
    int height;
    int mQuantity;

    public BomDTO(int bomId, int bomlineId, String name, int length, int quantity, String unit, String description, int materialId, int price, int typeId, int width, int height, int mQuantity) {
        this.bomId = bomId;
        this.bomlineId = bomlineId;
        this.name = name;
        this.length = length;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
        this.materialId = materialId;
        this.price = price;
        this.typeId = typeId;
        this.width = width;
        this.height = height;
        this.mQuantity = mQuantity;
    }

    public int getBomId() {
        return bomId;
    }

    public String getName() {
        return name;
    }

    public int getBomlineId() {
        return bomlineId;
    }

    public int getLength() {
        return length;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getDescription() {
        return description;
    }

    public int getMaterialId() {
        return materialId;
    }

    public int getPrice() {
        return price;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    @Override
    public String toString() {
        return "BomDTO{" +
                "bomId=" + bomId +
                ", bomlineId=" + bomlineId +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", description='" + description + '\'' +
                ", material_id=" + materialId +
                ", price=" + price +
                ", typeId=" + typeId +
                ", width=" + width +
                ", height=" + height +
                ", mQuantity=" + mQuantity +
                '}';
    }
}
