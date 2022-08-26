package dat.startcode.persistence;

import dat.startcode.model.entities.Account;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.CustomerFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest
{
    private final static String USER = "test";
    private final static String PASSWORD = "nemt";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {

        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("delete from account");
                stmt.execute("ALTER TABLE account AUTO_INCREMENT = 0");
                stmt.execute("delete from customer");
                stmt.execute("ALTER TABLE customer AUTO_INCREMENT = 0");



                // Indsæt et par brugere
                stmt.execute("insert into account (email, password, role) " +
                        "values ('user@fog.dk','1234',2),('admin@fog.dk','1234',1), ('ben@ben.dk','1234',2)");
                stmt.execute("ALTER TABLE account AUTO_INCREMENT = 0");
                stmt.execute("INSERT INTO `carport_test`.`customer` (`name`,`address`,`city`,`zip`,`mobile`,`account_id`)VALUES('Test Testington', 'Adresse', 'By', 1234, 12345678, 1), ('Ben Benson', 'Benvej', 'Benby', 2080, 87654321, 3)");
                stmt.execute("ALTER TABLE customer AUTO_INCREMENT = 0");


            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
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
    void getSpecificCustomerTest() throws DatabaseException {
        Customer customer = CustomerFacade.getSpecificCustomer(2, connectionPool);

        assertEquals("Ben Benson", customer.getName());

        customer = CustomerFacade.getSpecificCustomer(33, connectionPool);
        assertEquals(null, customer);

    }

    @Test
    void getCustomerIdTest() throws DatabaseException
    {
        int customerId = CustomerFacade.getCustomerId(12345678, connectionPool);
        assertEquals(1, customerId);

        customerId = CustomerFacade.getCustomerId(87654321, connectionPool);
        assertEquals(2, customerId);

    }

    @Test
    void updateCustomerProfileTest() throws DatabaseException {
        Customer customer = new Customer(1,1, "Testi", "Ny adresse", "ny by", 4000, 98456723);

        boolean testBoolean = CustomerFacade.updateCustomerProfile(customer,connectionPool);

        assertEquals(true, testBoolean);

        customer = CustomerFacade.getSpecificCustomer(1, connectionPool);
        assertEquals("Testi", customer.getName());
    }

    @Test
    void customerAccountTest() throws DatabaseException {
        Account account = new Account("user@fog.dk","1234",2, 1);
        Customer customer = CustomerFacade.customerAccount(account, connectionPool);
        assertEquals("Test Testington", customer.getName());

        account = new Account("forkert@bruger.dk", "4567", 2, 6);
        customer = CustomerFacade.customerAccount(account, connectionPool);
        assertEquals(null, customer);
    }

    @Test
    void createCustomerTest() throws DatabaseException {
        Customer customer = CustomerFacade.createCustomer("Nyt navn", "Gammel adresse", "ingen by", 3000, 80796857, "bo@bo.dk", "3412", connectionPool);

        assertEquals("Nyt navn", customer.getName());
        assertEquals(3000, customer.getZip());
        assertEquals(4, customer.getAccountId());
        assertEquals(3, customer.getCustomerId());
    }



}