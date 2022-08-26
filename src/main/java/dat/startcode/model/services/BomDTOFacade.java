package dat.startcode.model.services;

import dat.startcode.model.DTO.BomDTO;
import dat.startcode.model.persistence.DTOMappers.BomDTOMapper;
import dat.startcode.model.persistence.ConnectionPool;

import java.util.ArrayList;

public class BomDTOFacade {

    public static ArrayList<BomDTO> getBomlineWithInfo(int bomId, ConnectionPool connectionPool) {

        BomDTOMapper bomDTOMapper = new BomDTOMapper(connectionPool);

        return bomDTOMapper.getBomlineWithInfo(bomId);

    }
}
