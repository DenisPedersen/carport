package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.entities.Material;

import java.util.ArrayList;
import java.util.HashMap;

public interface IMaterialMapper {

    ArrayList<Material> getAllMaterials();

    Material getSpecificMaterial(int materialId);

    boolean createNewMaterial(String name, int price, String unit, int length, int typeId, int width, int height, int quantity);

    boolean updateMaterial(Material material);

    boolean deleteMaterial(int materialId);

    HashMap<String, ArrayList<Material>> getMaterialHashmaps();

}
