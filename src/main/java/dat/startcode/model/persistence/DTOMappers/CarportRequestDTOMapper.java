package dat.startcode.model.persistence.DTOMappers;

import dat.startcode.model.DTO.CarportRequestDTO;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.interfaceMappers.ICarportRequestDTOMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarportRequestDTOMapper implements ICarportRequestDTOMapper {

    ConnectionPool connectionPool;

    public CarportRequestDTOMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public CarportRequestDTO getSpecificCarportRequestDTO(int carportRequestId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM carport.carport_request_with_customer_info WHERE carport_request_id = ?";

        CarportRequestDTO carportRequestDTO = null;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportRequestId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    String roof = rs.getString("roof");
                    int roofIncline = rs.getInt("roof_incline");
                    boolean isApproved = rs.getBoolean("is_approved");
                    int shedLength = rs.getInt("shed_length");
                    int shedWidth = rs.getInt("shed_width");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    int zip = rs.getInt("zip");
                    int mobile = rs.getInt("mobile");
                    int accountId = rs.getInt("account_id");

                    carportRequestDTO = new CarportRequestDTO(customerId, carportRequestId, width, length, roof, roofIncline, isApproved, shedLength, shedWidth, name, address, city, zip, mobile, accountId);
                    return carportRequestDTO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carportRequestDTO;
    }
}
