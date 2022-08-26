package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.CarportRequest;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.CarportRequestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class RequestDeny extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));

        if (CarportRequestFacade.deleteRequest(requestId, ApplicationStart.getConnectionPool())) {
            String requestDeleted = "Forespørgsel med id nummer " + requestId + " blev slettet";
            request.setAttribute("requestDeleted", requestDeleted);
        }

        ArrayList<CarportRequest> carportRequestArraylist = CarportRequestFacade.getAllOpenCarportRequests(ApplicationStart.getConnectionPool());

        request.setAttribute("carportRequestArraylist", carportRequestArraylist);
        return "requestoverview";
    }
}
