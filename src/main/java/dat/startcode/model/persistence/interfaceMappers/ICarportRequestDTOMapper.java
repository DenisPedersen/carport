package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.DTO.CarportRequestDTO;

public interface ICarportRequestDTOMapper {

    CarportRequestDTO getSpecificCarportRequestDTO(int carportRequestId);

}
