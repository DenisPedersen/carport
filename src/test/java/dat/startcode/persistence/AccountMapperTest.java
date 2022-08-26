package dat.startcode.persistence;

import dat.startcode.model.entities.Account;

import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.AccountFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest
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

                // Indsæt et par brugere
                stmt.execute("insert into account (email, password, role) " +
                        "values ('user@fog.dk','1234',2),('admin@fog.dk','1234',1), ('ben@ben.dk','1234',2)");
                stmt.execute("ALTER TABLE account AUTO_INCREMENT = 0");



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
    void login() throws DatabaseException
    {
        //Account expectedAccount = new Account("a@a.dk","1234",2);
        Account actualAccount = AccountFacade.login("admin@fog.dk","1234", connectionPool);
        assertEquals("admin@fog.dk", actualAccount.getEmail());
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> AccountFacade.login("a@a.dk","123", connectionPool));
    }

    @Test
    void getAccountIdTest() throws DatabaseException
    {
        int accountId = AccountFacade.getAccountId("admin@fog.dk", "1234", connectionPool);
        assertEquals(2, accountId);

        accountId = AccountFacade.getAccountId("ben@ben.dk", "1234", connectionPool);
        assertEquals(3, accountId);

    }

    @Test
    void invalidAccountNameLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> AccountFacade.login("bob","1234", connectionPool));
    }

    @Test
    void createAccount() throws DatabaseException
    {
        int newAccount = AccountFacade.createAccount("jill@jill.dk", "1234", 2, connectionPool);
        assertEquals(4, newAccount);

        Account account = AccountFacade.login("jill@jill.dk", "1234", connectionPool);
        assertEquals("jill@jill.dk", account.getEmail());

    }

    @Test
    void updateAccountTest() throws  DatabaseException
    {
        Account account = new Account("ben@ben.dk", "12345", 2, 3);
       boolean testBoolean = AccountFacade.updateAccount(account, connectionPool);
        assertEquals(true, testBoolean);

       account = AccountFacade.login("ben@ben.dk", "12345", connectionPool);
        assertEquals("12345", account.getPassword());
    }




}