package dat.startcode.model.persistence.entityMappers;

import dat.startcode.model.entities.Account;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.interfaceMappers.ICustomerMapper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerMapper implements ICustomerMapper {
    ConnectionPool connectionPool;

    public CustomerMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Customer createCustomer(String name, String address, String city, int zip, int mobile, String email, String password) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        AccountMapper accountMapper = new AccountMapper(connectionPool);

        int accountId = accountMapper.createAccount(email, password, 2);

        int customerId = 0;

        Logger.getLogger("web").log(Level.INFO, "");
        Customer customer;
        String sql = "insert into customer (name, address, city, zip, mobile, account_id) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, city);
                ps.setInt(4, zip);
                ps.setInt(5, mobile);
                ps.setInt(6, accountId);
                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rowsAffected == 1) {
                    rs.next();
                    customerId = rs.getInt(1);
                    customer = new Customer(email, password, 2, customerId, name, address, city, zip, mobile, accountId);
                } else {
                    throw new DatabaseException("The customer with name = " + name + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return customer;
    }

    @Override
    public int getCustomerId(int mobile) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM customer WHERE mobile = ?";
        int customerId = 0;

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, mobile);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    customerId = rs.getInt("customer_id");
                }
                if (customerId == 0) {
                    throw new DatabaseException("Kunne ikke finde customer_id");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke finde customer:id");
        }

        return customerId;
    }


    @Override
    public Customer getSpecificCustomer(int customerId) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "Select * FROM `customer_and_account_overview` WHERE customer_id = ?";
        Customer customer = null;


        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, customerId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    int zip = rs.getInt("zip");
                    int mobile = rs.getInt("mobile");
                    int account_id = rs.getInt("account_id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int role = rs.getInt("role");

                    customer = new Customer(email, password, role, customerId, name, address, city, zip, mobile, account_id);
                    return customer;

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean updateCustomerProfile(Customer customer) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "UPDATE `customer` SET `name` = ?, `address` = ?, `city` = ?, `zip` = ?, `mobile` = ?  WHERE `customer_id` = ?";


        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getAddress());
                ps.setString(3, customer.getCity());
                ps.setInt(4, customer.getZip());
                ps.setInt(5, customer.getMobile());
                ps.setInt(6, customer.getCustomerId());

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
    public Customer customerAccount(Account account) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM customer WHERE account_id = ?";

        Customer customer = null;

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, account.getAccountId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    int zip = rs.getInt("zip");
                    int mobile = rs.getInt("mobile");

                    customer = new Customer(account.getEmail(), account.getPassword(), account.getRole(), customerId, name, address, city, zip, mobile, account.getAccountId());
                    return customer;
                }

            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke finde customer:id");
        }

        return customer;
    }


}
