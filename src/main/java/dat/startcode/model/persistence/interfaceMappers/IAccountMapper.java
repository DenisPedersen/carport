package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.entities.Account;
import dat.startcode.model.exceptions.DatabaseException;

public interface IAccountMapper {

    Account login(String email, String password) throws DatabaseException;

    int createAccount(String email, String password, int role) throws DatabaseException;

    int getAccountId(String email, String password) throws DatabaseException;

    boolean updateAccount(Account account);
}
