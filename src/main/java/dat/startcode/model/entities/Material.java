package dat.startcode.model.entities;

public class Material {
    private int materialId;
    private String name;
    private int price;
    private String unit;
    private int length;
    private int width;
    private int height;
    private int typeId;
    private String typeName;
    private int quantity;

    public Material(int materialId, String name, int price, String unit, int typeId) {
        this.materialId = materialId;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.typeId = typeId;
    }

    public Material(int materialId, String name, int price, String unit, int length, int width, int height, int quantity, int typeId, String typeName) {
        this.materialId = materialId;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.length = length;
        this.width = width;
        this.height = height;
        this.typeId = typeId;
        this.typeName = typeName;
        this.quantity = quantity;
    }

    public Material(int materialId, String name, int price, String unit, int length, int width, int height, int quantity, int typeId) {
        this.materialId = materialId;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.length = length;
        this.width = width;
        this.height = height;
        this.typeId = typeId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Material(int materialId, String name, int price, String unit, int typeId, int quantity) {
        this.materialId = materialId;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.typeId = typeId;
        this.quantity = quantity;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public String toString() {
        return "Material{" +
                "materialId=" + materialId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
