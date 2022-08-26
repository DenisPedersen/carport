package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Material;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DeleteMaterial extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        int materialId = Integer.parseInt(request.getParameter("delete"));

        if (MaterialFacade.deleteMaterial(materialId, ApplicationStart.getConnectionPool())) {
            boolean deletionSuccess = true;
            String deletionMessage = "Materialet med id: " + materialId + " er blevet slettet";
            request.setAttribute("deletionSuccess", deletionSuccess);
            request.setAttribute("deletionMessage", deletionMessage);
        }

        ArrayList<Material> materialArrayList = new ArrayList<>();

        materialArrayList = MaterialFacade.getAllMaterials(ApplicationStart.getConnectionPool());
        request.setAttribute("materialArrayList", materialArrayList);

        return "materialoverview";
    }
}
