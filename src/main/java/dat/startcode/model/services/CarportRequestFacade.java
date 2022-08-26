package dat.startcode.model.services;

import dat.startcode.model.entities.CarportRequest;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.entityMappers.CarportRequestMapper;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarportRequestFacade {

    public static CarportRequest createCarportRequest(int width, int length, String roofType, int roofIncline, int shedWidth, int shedLength, int customerId, ConnectionPool connectionPool) throws DatabaseException, SQLException {

        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);
        return carportRequestMapper.createCarportRequest(width, length, roofType, roofIncline, shedWidth, shedLength, customerId);

    }

    public static ArrayList<CarportRequest> getAllCarportRequests(ConnectionPool connectionPool) {
        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);
        return carportRequestMapper.getAllRequests();
    }

    public static ArrayList<CarportRequest> getAllCarportRequestsFromCustomer(int customerId, ConnectionPool connectionPool) {
        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);
        return carportRequestMapper.getAllRequestFromCustomer(customerId);
    }

    public static ArrayList<CarportRequest> getAllOpenCarportRequests(ConnectionPool connectionPool) {
        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);
        return carportRequestMapper.getAllOpenRequests();
    }

    public static CarportRequest getSpecificCarportRequest(int requestId, ConnectionPool connectionPool) {
        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);

        return carportRequestMapper.getSpecificRequest(requestId);
    }

    public static boolean approveSpecificCarportRequest(int requestId, ConnectionPool connectionPool) {
        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);

        return carportRequestMapper.approveSpecificRequest(requestId);
    }

    public static boolean deleteRequest(int carportRequestId, ConnectionPool connectionPool) {
        CarportRequestMapper carportRequestMapper = new CarportRequestMapper(connectionPool);
        return carportRequestMapper.deleteCarportRequest(carportRequestId);
    }
}
