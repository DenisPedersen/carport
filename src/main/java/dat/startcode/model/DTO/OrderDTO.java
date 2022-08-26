package dat.startcode.model.DTO;

import dat.startcode.model.entities.Order;

import java.util.ArrayList;


public class OrderDTO {
    Order order;
    CarportRequestDTO carportRequestDTO;
    ArrayList<BomDTO> bomDTOArrayList;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "order=" + order +
                ", carportRequestDTO=" + carportRequestDTO +
                ", bomlineArrayList=" + bomDTOArrayList +
                '}';
    }

    public OrderDTO(Order order, CarportRequestDTO carportRequestDTO, ArrayList<BomDTO> bomDTOArrayList) {
        this.order = order;
        this.carportRequestDTO = carportRequestDTO;
        this.bomDTOArrayList = bomDTOArrayList;
    }

    public Order getOrder() {
        return order;
    }

    public CarportRequestDTO getCarportRequestDTO() {
        return carportRequestDTO;
    }

    public ArrayList<BomDTO> getBomDTOArrayList() {
        return bomDTOArrayList;
    }
}
