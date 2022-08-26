package dat.startcode.model.persistence.entityMappers;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.interfaceMappers.IBomMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BomMapper implements IBomMapper {

    ConnectionPool connectionPool;

    public BomMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public ArrayList<Bomline> getAllBomlines() {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();

        String sql = "SELECT * FROM bomline";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bomlineId = rs.getInt("bomline_id");
                    int bomId = rs.getInt("bom_id");
                    int quantity = rs.getInt("quantity");
                    int descriptionId = rs.getInt("description_id");
                    int materialId = rs.getInt("material_id");
                    Bomline newBomline = new Bomline(bomlineId, bomId, quantity, descriptionId, materialId);
                    bomlineArrayList.add(newBomline);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bomlineArrayList;
    }

    @Override
    public ArrayList<Bomline> getAllBomlinesWithSpecificBOMId(int bomId) {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Bomline> bomlineArrayList = new ArrayList<>();

        String sql = "SELECT * FROM bomline WHERE bom_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bomId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bomlineId = rs.getInt("bomline_id");
                    int quantity = rs.getInt("quantity");
                    int descriptionId = rs.getInt("description_id");
                    int materialId = rs.getInt("material_id");
                    Bomline newBomline = new Bomline(bomlineId, bomId, quantity, descriptionId, materialId);
                    bomlineArrayList.add(newBomline);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bomlineArrayList;
    }

    @Override
    public Bomline getSpecificBomline(int bomlineId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM bomline WHERE bomline_id = ?";
        Bomline bomline = null;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bomlineId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int bomId = rs.getInt("bom_id");
                    int quantity = rs.getInt("quantity");
                    int descriptionId = rs.getInt("description_id");
                    int materialId = rs.getInt("material_id");

                    bomline = new Bomline(bomlineId, bomId, quantity, descriptionId, materialId);
                    return bomline;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bomline;
    }

    public int getBomIdFromOrderId(int orderId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT bom_id FROM bill_of_materials WHERE order_id = ?";

        int bomId = 0;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bomId = rs.getInt("bom_id");

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bomId;

    }


    @Override
    public boolean createBomline(int bomId, int quantity, int descriptionId, int materialId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "INSERT INTO bomline (bom_id, quantity, description_id, material_id) VALUES (?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, bomId);
                ps.setInt(2, quantity);
                ps.setInt(3, descriptionId);
                ps.setInt(4, materialId);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 1) {
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public int createBom(int orderId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "INSERT INTO bill_of_materials (order_id) VALUES (?)";

        int returnedBomId = -1;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, orderId);

                int rowsAffected = ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();

                if (rowsAffected == 1) {
                    rs.next();
                    returnedBomId = rs.getInt(1);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnedBomId;
    }

    @Override
    public boolean createCompleteBillOfMaterials(ArrayList<Bomline> bomlineArrayList, int orderId) {

        Logger.getLogger("web").log(Level.INFO, "");

        int bomId = createBom(orderId);
        boolean returnedBoolean = false;


        for (Bomline bomline : bomlineArrayList) {
            returnedBoolean = createBomline(bomId, bomline.getQuantity(), bomline.getDescriptionId(), bomline.getMaterial().getMaterialId());
            if (!returnedBoolean) {
                break;
            }
        }

        return returnedBoolean;
    }


}
