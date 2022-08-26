package dat.startcode.model.services;

import dat.startcode.model.entities.Account;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.entityMappers.CustomerMapper;

public class CustomerFacade {

    public static Customer createCustomer(String name, String address, String city, int zip, int mobile, String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.createCustomer(name, address, city, zip, mobile, email, password);
    }

    public static int getCustomerId(int mobile, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.getCustomerId(mobile);
    }

    public static Customer customerAccount(Account account, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.customerAccount(account);
    }

    public static Customer getSpecificCustomer(int customerId, ConnectionPool connectionPool) throws DatabaseException {
        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.getSpecificCustomer(customerId);
    }

    public static boolean updateCustomerProfile(Customer customer, ConnectionPool connectionPool) {

        CustomerMapper customerMapper = new CustomerMapper(connectionPool);
        return customerMapper.updateCustomerProfile(customer);
    }

}
