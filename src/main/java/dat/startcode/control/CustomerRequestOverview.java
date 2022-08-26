package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.CarportRequest;
import dat.startcode.model.entities.Customer;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.CarportRequestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class CustomerRequestOverview extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        ArrayList<CarportRequest> carportRequestArraylist = CarportRequestFacade.getAllCarportRequestsFromCustomer(customer.getCustomerId(), ApplicationStart.getConnectionPool());

        request.setAttribute("carportRequestArraylist", carportRequestArraylist);
        return "customerrequestoverview";
    }
}
