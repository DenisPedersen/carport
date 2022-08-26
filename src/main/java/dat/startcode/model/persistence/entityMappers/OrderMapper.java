package dat.startcode.model.persistence.entityMappers;

import dat.startcode.model.entities.Order;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.interfaceMappers.IOrderMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper implements IOrderMapper {

    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public ArrayList<Order> getAllOrders() {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM `order`";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    int customerId = rs.getInt("customer_id");
                    Timestamp timeStamp = rs.getTimestamp("date");
                    LocalDateTime date = timeStamp.toLocalDateTime();
                    int carportType = rs.getInt("carport_type");
                    int price = rs.getInt("price");
                    int carportRequestId = rs.getInt("carport_request_id");
                    Order newOrder = new Order(orderId, customerId, date, carportType, price, carportRequestId);
                    orderList.add(newOrder);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orderList;
    }

    @Override
    public ArrayList<Order> getAllOrdersFromSpecificCustomer(int customerId) {

        Logger.getLogger("web").log(Level.INFO, "");

        ArrayList<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM `order` WHERE customer_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, customerId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Timestamp timeStamp = rs.getTimestamp("date");
                    LocalDateTime date = timeStamp.toLocalDateTime();
                    int carportType = rs.getInt("carport_type");
                    int price = rs.getInt("price");
                    int carportRequestId = rs.getInt("carport_request_id");
                    Order newOrder = new Order(orderId, customerId, date, carportType, price, carportRequestId);
                    orderList.add(newOrder);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orderList;
    }

    @Override
    public Order getSpecificOrder(int orderId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM `order` WHERE order_id = ?";
        Order order = null;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    Timestamp timeStamp = rs.getTimestamp("date");
                    LocalDateTime date = timeStamp.toLocalDateTime();
                    int carportType = rs.getInt("carport_type");
                    int price = rs.getInt("price");
                    int carportRequestId = rs.getInt("carport_request_id");

                    order = new Order(orderId, customerId, date, carportType, price, carportRequestId);

                    return order;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    @Override
    public int createOrder(int customerId, LocalDateTime dateTime, int carportType, int carportRequestId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "INSERT INTO `order` (customer_id, date, carport_type, carport_request_id) VALUES (?,?,?,?)";

        int orderId = 0;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, customerId);
                ps.setTimestamp(2, Timestamp.valueOf(dateTime));
                ps.setInt(3, carportType);
                ps.setInt(4, carportRequestId);

                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rowsAffected == 1) {
                    rs.next();
                    orderId = rs.getInt(1);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderId;
    }
}
