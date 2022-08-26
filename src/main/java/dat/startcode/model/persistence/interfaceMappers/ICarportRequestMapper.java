package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.entities.CarportRequest;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICarportRequestMapper {
    ArrayList<CarportRequest> getAll = new ArrayList<>();

    CarportRequest createCarportRequest(int width, int length, String roofType, int roofIncline, int shedWidth, int shedLength, int customerId) throws DatabaseException, SQLException;

    CarportRequest getSpecificRequest(int carportRequestId);

    boolean approveSpecificRequest(int carportRequestId);

    ArrayList<CarportRequest> getAllRequests();

    ArrayList<CarportRequest> getAllOpenRequests();

    boolean deleteCarportRequest(int carportRequestId);

    ArrayList<CarportRequest> getAllRequestFromCustomer(int customerId);


}
