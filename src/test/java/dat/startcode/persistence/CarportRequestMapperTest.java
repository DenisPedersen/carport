package dat.startcode.persistence;

import dat.startcode.model.entities.CarportRequest;
import dat.startcode.model.entities.Material;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.CarportRequestFacade;
import dat.startcode.model.services.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarportRequestMapperTest {

    private final static String USER = "test";
    private final static String PASSWORD = "nemt";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test";
    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement statement = testConnection.createStatement()) {
                statement.execute("delete from `carport_request`");
                statement.execute("alter table carport_request auto_increment =0");
                statement.execute("delete from `account`");
                statement.execute("alter table account auto_increment=0");
                statement.execute("delete from `customer`");
                statement.execute("alter table customer auto_increment=0");

                statement.execute("INSERT INTO `account`(`account_id`,`email`,`password`,`role`)VALUES(1,'bla@bla.dk',1234,2)");
                statement.execute("INSERT INTO `customer`(`customer_id`,`name`,`address`,`city`,`zip`,`mobile`,`account_id`) VALUES(1,'Poul','Poulvej 2','Aalborg',2100,12345678,2)");
                statement.execute("INSERT INTO `carport_request`(`carport_request_id`,`width`,`length`,`roof`,`roof_incline`,`is_approved`,`shed_length`,`shed_width`,`customer_id`)VALUES(1,600,780,'Plasttrapez',null,0,280,600,2);");

            }

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void createCarportRequest() throws SQLException, DatabaseException {
        CarportRequest carportRequest = CarportRequestFacade.createCarportRequest(570, 750, "Plasttrapez", 0, 570, 280, 2, connectionPool);

        assertEquals(2,carportRequest.getCustomerId());

    }

    @Test
    void getSpecificRequest() {

        CarportRequest carportRequest = CarportRequestFacade.getSpecificCarportRequest(1, connectionPool);
        assertEquals(1, carportRequest.getRequestId());

    }

    @Test
    void approveSpecificRequest() {

        boolean test = CarportRequestFacade.approveSpecificCarportRequest(1, connectionPool);

        assertEquals(true, test);

    }

    @Test
    void getAllRequests() {

        ArrayList<CarportRequest> carportRequestArrayList = CarportRequestFacade.getAllCarportRequests(connectionPool);
        assertEquals(1, carportRequestArrayList.size());
    }

    @Test
    void getAllRequestFromCustomer() {
        ArrayList<CarportRequest> carportRequestArrayList = CarportRequestFacade.getAllCarportRequestsFromCustomer(2, connectionPool);

        assertEquals(1, carportRequestArrayList.size());

    }

    @Test
    void getAllOpenRequests() {
        ArrayList<CarportRequest> carportRequestArrayList = CarportRequestFacade.getAllOpenCarportRequests(connectionPool);

        assertEquals(1, carportRequestArrayList.size());

    }

    @Test
    void deleteCarportRequest() {

        boolean carportRequest = CarportRequestFacade.deleteRequest(1, connectionPool);

        assertEquals(true, carportRequest);
    }
}