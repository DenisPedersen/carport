package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.entities.Account;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;

public interface ICustomerMapper {
    Customer createCustomer(String name, String address, String city, int zip, int mobile, String email, String password) throws DatabaseException;

    int getCustomerId(int mobile) throws DatabaseException;

    Customer getSpecificCustomer(int customerId) throws DatabaseException;

    boolean updateCustomerProfile(Customer customer);


    Customer customerAccount(Account account) throws DatabaseException;
}
