package dat.startcode.control;

import dat.startcode.model.DTO.OrderDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Bomline;
import dat.startcode.model.entities.calculator.CarportCalculator;
import dat.startcode.model.entities.CarportRequest;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RequestApprove extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException
    {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        CarportRequest carportRequest;

        if (CarportRequestFacade.approveSpecificCarportRequest(requestId, ApplicationStart.getConnectionPool())) {

            carportRequest = CarportRequestFacade.getSpecificCarportRequest(requestId, ApplicationStart.getConnectionPool());

            LocalDateTime date = LocalDateTime.now();

            int carportType;
            if (carportRequest.getShedLength() == 0) {
                carportType = 1; //type 1 er NO SHED! IKKE NOGET SKUR!
            } else {
                carportType = 2; //type 2 er FULLSHED! SKUR I FULD LÆNGDE!
            }

            int orderId = OrderFacade.createOrder(carportRequest.getCustomerId(), date, carportType, requestId, ApplicationStart.getConnectionPool());

            CarportCalculator carportCalculator = new CarportCalculator(MaterialFacade.getAllMaterials(ApplicationStart.getConnectionPool()));

            ArrayList<Bomline> bomlineArrayList;

            if (carportType == 1) {
                bomlineArrayList = carportCalculator.createCarportNoShed(carportRequest.getLength(), carportRequest.getWidth());
            } else {
                bomlineArrayList = carportCalculator.createCarportWithFullShed(carportRequest.getLength(), carportRequest.getWidth(), carportRequest.getShedLength(), carportRequest.getWidth());
            }

            if (BomFacade.createCompleteBillOfMaterials(bomlineArrayList, orderId, ApplicationStart.getConnectionPool())) {

                OrderDTO orderDTO = OrderDTOFacade.getOrderWithAllInfo(orderId, ApplicationStart.getConnectionPool());

                request.setAttribute("orderDTO", orderDTO);
                return "orderview";

            }
        }
        return "error";
    }
}
