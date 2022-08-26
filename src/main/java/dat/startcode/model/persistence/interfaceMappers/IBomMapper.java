package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.entities.Bomline;

import java.util.ArrayList;

public interface IBomMapper {

    ArrayList<Bomline> getAllBomlines();

    Bomline getSpecificBomline(int bomlineId);

    ArrayList<Bomline> getAllBomlinesWithSpecificBOMId(int bomId);

    boolean createBomline(int bomId, int quantity, int descriptionId, int materialId);

    int createBom(int orderId);

    boolean createCompleteBillOfMaterials(ArrayList<Bomline> bomlineArrayList, int orderId);

}
