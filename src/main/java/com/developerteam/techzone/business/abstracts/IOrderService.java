package com.developerteam.techzone.business.abstracts;

import com.developerteam.techzone.entities.dto.DtoOrder;
import com.developerteam.techzone.entities.dto.DtoOrderIU;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

     List<DtoOrder> getAllOrders();
     DtoOrder getOrderById(int id);
     DtoOrder createOrder(DtoOrderIU dtoOrderIU);

     List<DtoOrder> getAllCustomersOrder();
     DtoOrder getByIdCustomersOrder(int id);

}
