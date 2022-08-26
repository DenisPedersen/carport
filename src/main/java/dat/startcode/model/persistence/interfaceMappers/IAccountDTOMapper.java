package dat.startcode.model.persistence.interfaceMappers;

import dat.startcode.model.DTO.AccountDTO;

public interface IAccountDTOMapper {

    AccountDTO getAccountAndCustomerDTO(int customerId);

}
