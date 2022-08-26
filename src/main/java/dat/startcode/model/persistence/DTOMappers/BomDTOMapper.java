package dat.startcode.model.persistence.DTOMappers;

import dat.startcode.model.DTO.BomDTO;
import dat.startcode.model.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BomDTOMapper {

    ConnectionPool connectionPool;

    public BomDTOMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ArrayList<BomDTO> getBomlineWithInfo(int bomId) {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<BomDTO> bomDTOArrayList = new ArrayList<>();

        BomDTO bomDTO;

        String sql = "SELECT * FROM carport.bomlines_with_materialname_and_description WHERE bom_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bomId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int bomlineId = rs.getInt("bomline_id");
                    String name = rs.getString("name");
                    int length = rs.getInt("length");
                    int quantity = rs.getInt("quantity");
                    String unit = rs.getString("unit");
                    String description = rs.getString("description");
                    int materialId = rs.getInt("material_id");
                    int price = rs.getInt("price");
                    int typeId = rs.getInt("type_id");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int mQuantity = rs.getInt("m_quantity");

                    bomDTO = new BomDTO(bomId, bomlineId, name, length, quantity, unit, description, materialId, price, typeId, width, height, mQuantity);
                    bomDTOArrayList.add(bomDTO);
                }

            }

        } catch (SQLException exception) {

        }
        return bomDTOArrayList;
    }
}
