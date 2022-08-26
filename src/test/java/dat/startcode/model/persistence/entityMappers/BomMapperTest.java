package dat.startcode.model.persistence.entityMappers;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.Material;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.BomFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BomMapperTest {

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
        try(Connection testConnection = connectionPool.getConnection()){
            try(Statement stmt = testConnection.createStatement()){
                // Remove all rows from all tables
                stmt.execute("delete from `bomline`");
                stmt.execute("ALTER TABLE `bomline` AUTO_INCREMENT = 0");
                stmt.execute("delete from `bill_of_materials`");
                stmt.execute("ALTER TABLE `bill_of_materials` AUTO_INCREMENT = 0");
                stmt.execute("delete from `order`");
                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 0");
                stmt.execute("delete from `carport_request`");
                stmt.execute("ALTER TABLE `carport_request` AUTO_INCREMENT = 0");
                stmt.execute("delete from `customer`");
                stmt.execute("ALTER TABLE `customer` AUTO_INCREMENT = 0");
                stmt.execute("delete from `account`");
                stmt.execute("ALTER TABLE `account` AUTO_INCREMENT = 0");
                stmt.execute("delete from `material`");
                stmt.execute("ALTER TABLE `material` AUTO_INCREMENT = 0");
                stmt.execute("delete from `material_type`");
                stmt.execute("ALTER TABLE `material_type` AUTO_INCREMENT = 0");
                stmt.execute("delete from `description`");
                stmt.execute("ALTER TABLE `description` AUTO_INCREMENT = 0");



                stmt.execute("INSERT INTO `account` (email, password, role) VALUES ('a@a.dk','1234',2)");
                stmt.execute("ALTER TABLE `account` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `customer` (name, address, city, zip, mobile, account_id) VALUES ('Allan Allanson','Allansvej 1', 'Allanrød', 1111, 12121212, 1)");
                stmt.execute("ALTER TABLE `customer` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `carport_request` (width, length, roof, roof_incline, shed_length, shed_width, customer_id) VALUES (10, 10, 'ja', 2, 5, 5, 1), (20, 20, 'nej', 0, 10, 10, 1)");
                stmt.execute("ALTER TABLE `carport_request` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `order` (customer_id, date, carport_type, price, carport_request_id) VALUES (1, '2022.10.02', 1, 100, 1), (1, '2022.10.03', 1, 200, 2)");
                stmt.execute("ALTER TABLE `order` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `bill_of_materials` (`order_id`) VALUES (1), (2) ");
                stmt.execute("ALTER TABLE `bill_of_materials` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `material_type` (`type_id`,`name`) VALUES (1,'Træ & Tagplader'), (2,'Beslag & Skruer')");
                stmt.execute("ALTER TABLE `material_type` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `material` (`name`,`price`,`unit`,`length`,`width`,`height`,`type_id`, `quantity`)" +
                        "VALUES ('25x200 mm. trykimp. Brædt', 50, 'Stk', 720,25, 200, 1, 1),('45x95 mm. Reglar ub.', 25, 'Stk', 720,45, 95, 1, 1)");
                stmt.execute("ALTER TABLE `material` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `description` (description) VALUES ('Something')");
                stmt.execute("ALTER TABLE `description` AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `bomline` (`bom_id`,`quantity`,`description_id`,`material_id`) VALUES (1, 1, 1,1), (1, 3, 1, 1)");
                stmt.execute("ALTER TABLE `bomline` AUTO_INCREMENT = 0");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void getAllBomlinesWithSpecificBOMId() {

        ArrayList<Bomline> bomlineArrayList = BomFacade.getAllBomlinesWithSpecificBOMId(1, connectionPool);

        assertEquals(2, bomlineArrayList.size());

    }

    @Test
    void getBomIdFromOrderId() {

        int bomId = BomFacade.getBomIdFromOrderId(2, connectionPool);

        assertEquals(2, bomId);


    }

    @Test
    void createCompleteBillOfMaterials() {
        Material material = new Material(1, "Træ", 20, "stk", 200, 1, 20, 50, 1);
        ArrayList<Bomline> bomlineList = new ArrayList<>();
        bomlineList.add(new Bomline(2, material, 1));
        boolean newBom = BomFacade.createCompleteBillOfMaterials(bomlineList, 1, connectionPool);

        assertEquals(true, newBom);
    }
}