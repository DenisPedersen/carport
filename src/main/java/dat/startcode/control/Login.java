package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Account;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.AccountFacade;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.CustomerFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends Command {
    private ConnectionPool connectionPool;

    public Login() {

        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();
        session.setAttribute("account", null); // adding empty user object to session scope

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Account account = AccountFacade.login(email, password, connectionPool);

        System.out.println(account);

        Customer customer = CustomerFacade.customerAccount(account, connectionPool);

        System.out.println(customer);


        if (customer != null) {
            session.setAttribute("customer", customer);
            return "index";
        } else {
            session.setAttribute("account", account); // adding user object to session scope
            return "index";
        }
    }
}