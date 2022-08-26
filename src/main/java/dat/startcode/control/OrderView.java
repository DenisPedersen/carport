package dat.startcode.control;

import dat.startcode.model.DTO.OrderDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.OrderDTOFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderView extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDTO orderDTO = OrderDTOFacade.getOrderWithAllInfo(orderId, ApplicationStart.getConnectionPool());

        request.setAttribute("orderDTO", orderDTO);
        return "orderview";
    }
}
