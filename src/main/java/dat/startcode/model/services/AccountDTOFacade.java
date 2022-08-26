package dat.startcode.model.services;

import dat.startcode.model.DTO.AccountDTO;
import dat.startcode.model.persistence.DTOMappers.AccountDTOMapper;
import dat.startcode.model.persistence.ConnectionPool;

public class AccountDTOFacade {

    public static AccountDTO getAccountAndCustomerInfo(int customerId, ConnectionPool connectionPool) {
        AccountDTOMapper accountDTOMapper = new AccountDTOMapper(connectionPool);

        return accountDTOMapper.getAccountAndCustomerDTO(customerId);
    }
}
