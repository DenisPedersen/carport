package dat.startcode.model.persistence.entityMappers;

import dat.startcode.model.entities.Account;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.interfaceMappers.IAccountMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountMapper implements IAccountMapper {

    ConnectionPool connectionPool;

    public AccountMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Account login(String email, String password) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        Account account;

        String sql = "SELECT * FROM account WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int role = rs.getInt("role");
                    int accountId = rs.getInt("account_id");
                    account = new Account(email, password, role, accountId);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return account;
    }

    public Set<String> getAllEmails() throws SQLException {
        Logger.getLogger("web").log(Level.INFO, "");

        Set<String> emailSet = new HashSet<>();

        String sql = "SELECT * FROM carport.account";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("email");

                    emailSet.add(email);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return emailSet;
        }
    }

    @Override
    public int createAccount(String email, String password, int role) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        int accountId = 0;

        String sql = "insert into account (email, password, role) values (?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setInt(3, 2);
                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rowsAffected == 1) {
                    rs.next();
                    accountId = rs.getInt(1);

                } else {
                    throw new DatabaseException("Kunne ikke oprette account med følgende email: " + email);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            //throw new DatabaseException(ex, "Could not insert account into database");
        }
        return accountId;
    }

    @Override
    public int getAccountId(String email, String password) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "SELECT * FROM account WHERE email = ? AND password = ?";

        int accountId = 0;

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    accountId = rs.getInt("account_id");
                }
                if (accountId == 0) {
                    throw new DatabaseException("Kunne ikke finde account_id");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Kunne ikke finde account:id");
        }

        return accountId;

    }

    @Override
    public boolean updateAccount(Account account) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "UPDATE account SET email = ?, password = ? WHERE account_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassword());
                ps.setInt(3, account.getAccountId());

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
}
