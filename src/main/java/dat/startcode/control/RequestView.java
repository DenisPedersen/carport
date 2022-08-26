package dat.startcode.control;

import dat.startcode.model.DTO.CarportRequestDTO;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.CarportRequestDTOFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestView extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));

        CarportRequestDTO carportRequestDTO = CarportRequestDTOFacade.getSpecificCarportRequestDTO(requestId, ApplicationStart.getConnectionPool());

        request.setAttribute("carportRequestDTO", carportRequestDTO);

        return "requestview";
    }

}
