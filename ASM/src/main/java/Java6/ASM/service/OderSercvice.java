package Java6.ASM.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import Java6.ASM.Entities.Order;

public interface OderSercvice {

    Order create(JsonNode oderData);

    Order findByid(Long id);
    List<Order> findByUsername(String username);
     
}
