package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Material;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.services.MaterialFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MaterialGetById extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        int materialId = Integer.parseInt(request.getParameter("modify"));

        Material material = MaterialFacade.getSpecificMaterial(materialId, ApplicationStart.getConnectionPool());

        request.setAttribute("material", material);

        ArrayList<String> stringArrayList = new ArrayList<>();

        stringArrayList.add("Træ");
        stringArrayList.add("Tagplader");
        stringArrayList.add("Beslag & Hulbånd & Diverse");
        stringArrayList.add("Skruer & Skiver & Bolte");

        request.setAttribute("stringArrayList", stringArrayList);

        return "materialmodify";
    }
}
