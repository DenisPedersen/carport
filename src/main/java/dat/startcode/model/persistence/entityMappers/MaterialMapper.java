package dat.startcode.model.persistence.entityMappers;

import dat.startcode.model.entities.Material;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.interfaceMappers.IMaterialMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialMapper implements IMaterialMapper {
    ConnectionPool connectionPool;

    public MaterialMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override

    public ArrayList<Material> getAllMaterials() {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Material> materialList = new ArrayList<>();

        String sql = "SELECT * FROM material_view ORDER BY material_id";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int materialId = rs.getInt("material_id");
                    int typeId = rs.getInt("type_id");
                    String materialName = rs.getString("material_name");
                    int price = rs.getInt("price");
                    String unit = rs.getString("unit");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int quantity = rs.getInt("quantity");
                    String typeName = rs.getString("mt_name");
                    Material newMaterial = new Material(materialId, materialName, price, unit, length, width, height, quantity, typeId, typeName);
                    materialList.add(newMaterial);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materialList;
    }


    @Override
    public Material getSpecificMaterial(int materialID) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "Select * FROM `material_view` WHERE material_id = ?";
        Material material = null;


        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialID);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("material_name");
                    int price = rs.getInt("price");
                    String unit = rs.getString("unit");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int quantity = rs.getInt("quantity");

                    String typeName = rs.getString("mt_name");
                    int typeID = rs.getInt("type_id");

                    material = new Material(materialID, name, price, unit, length, width, height, quantity, typeID, typeName);
                    return material;

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    @Override

    public boolean createNewMaterial(String name, int price, String unit, int length, int typeId, int width, int height, int quantity) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "INSERT INTO `material` (`name`, `price`, `unit`, `length`, `type_id`, `width`, `height`, `quantity`) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, name);
                ps.setInt(2, price);
                ps.setString(3, unit);
                ps.setInt(4, length);
                ps.setInt(5, typeId);
                ps.setInt(6, width);
                ps.setInt(7, height);
                ps.setInt(8, quantity);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateMaterial(Material material) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "UPDATE `material` SET `name` = ?, `price` = ?, `unit` = ?, `length` = ?, `type_id` = ?, `width` = ?, `height` = ?, `quantity` = ? WHERE `material_id` = ?";


        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, material.getName());
                ps.setInt(2, material.getPrice());
                ps.setString(3, material.getUnit());
                ps.setInt(4, material.getLength());
                ps.setInt(5, material.getTypeId());
                ps.setInt(6, material.getWidth());
                ps.setInt(7, material.getHeight());
                ps.setInt(8, material.getQuantity());
                ps.setInt(9, material.getMaterialId());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteMaterial(int materialId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "DELETE FROM `material` WHERE `material_id` = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialId);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public HashMap<String, ArrayList<Material>> getMaterialHashmaps() {

        Logger.getLogger("web").log(Level.INFO, "");

        HashMap<String, ArrayList<Material>> materialHashmap = new HashMap<>();

        ArrayList<Material> materialArrayList = new ArrayList<>();

        //Stolper
        materialArrayList.add(getSpecificMaterial(10));
        materialHashmap.put("Stolpe", materialArrayList);

        //Overstern
        materialArrayList.clear();
        materialArrayList.add(getSpecificMaterial(3));
        materialArrayList.add(getSpecificMaterial(4));
        materialHashmap.put("Overstern", materialArrayList);

        //Understern
        materialArrayList.clear();
        materialArrayList.add(getSpecificMaterial(1));
        materialArrayList.add(getSpecificMaterial(2));
        materialHashmap.put("Understern", materialArrayList);

        return null;
    }


}
