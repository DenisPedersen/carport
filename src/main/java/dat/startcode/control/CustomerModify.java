package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Account;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.AccountFacade;
import dat.startcode.model.services.CustomerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerModify extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();

        Customer customer = (Customer) session.getAttribute("customer");
        System.out.println(customer);

        int accountId = customer.getAccountId();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int role = customer.getRole();
        int customerId = customer.getCustomerId();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        int zip = Integer.parseInt(request.getParameter("zip"));
        int mobile = Integer.parseInt(request.getParameter("mobile"));

        if (!customer.getEmail().equals(email) || !customer.getPassword().equals(password)) {
            AccountFacade.updateAccount(new Account(email, password, role, accountId), ApplicationStart.getConnectionPool());
        }

        customer = new Customer(email, password, role, customerId, name, address, city, zip, mobile, accountId);
        CustomerFacade.updateCustomerProfile(customer, ApplicationStart.getConnectionPool());

        boolean modificationDone = true;
        String customerModified = "Dine ændringer er gemt!";

        session.setAttribute("customerModified", customerModified);
        session.setAttribute("modificationDone", modificationDone);
        session.setAttribute("customer", customer);

        return "customerprofile";
    }

}
