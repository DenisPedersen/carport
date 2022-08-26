package dat.startcode.model.services;

import dat.startcode.model.DTO.CarportRequestDTO;
import dat.startcode.model.persistence.DTOMappers.CarportRequestDTOMapper;
import dat.startcode.model.persistence.ConnectionPool;

public class CarportRequestDTOFacade {

    public static CarportRequestDTO getSpecificCarportRequestDTO(int carportRequestId, ConnectionPool connectionPool) {

        CarportRequestDTOMapper carportRequestDTOMapper = new CarportRequestDTOMapper(connectionPool);

        return carportRequestDTOMapper.getSpecificCarportRequestDTO(carportRequestId);

    }

}
