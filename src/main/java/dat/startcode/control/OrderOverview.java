package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class OrderOverview extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        ArrayList<Order> orderArraylist = OrderFacade.getAllOrders(ApplicationStart.getConnectionPool());

        request.setAttribute("orderArraylist", orderArraylist);
        return "orderoverview";
    }
}
