package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Material;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MaterialOverview extends Command {

    private ConnectionPool connectionPool;

    public MaterialOverview() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        ArrayList<Material> materialArrayList;

        materialArrayList = MaterialFacade.getAllMaterials(connectionPool);

        request.setAttribute("materialArrayList", materialArrayList);

        return "materialoverview";
    }
}
