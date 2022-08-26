package dat.startcode.model.services;

import dat.startcode.model.entities.Bomline;
import dat.startcode.model.persistence.entityMappers.BomMapper;
import dat.startcode.model.persistence.ConnectionPool;

import java.util.ArrayList;

public class BomFacade {

    public static boolean createCompleteBillOfMaterials(ArrayList<Bomline> bomlineArrayList, int orderId, ConnectionPool connectionPool) {
        BomMapper bomMapper = new BomMapper(connectionPool);
        return bomMapper.createCompleteBillOfMaterials(bomlineArrayList, orderId);
    }

    public static ArrayList<Bomline> getAllBomlinesWithSpecificBOMId(int bomId, ConnectionPool connectionPool) {
        BomMapper bomMapper = new BomMapper(connectionPool);
        return bomMapper.getAllBomlinesWithSpecificBOMId(bomId);
    }

    public static int getBomIdFromOrderId(int orderId, ConnectionPool connectionPool) {
        BomMapper bomMapper = new BomMapper(connectionPool);
        return bomMapper.getBomIdFromOrderId(orderId);
    }
}
