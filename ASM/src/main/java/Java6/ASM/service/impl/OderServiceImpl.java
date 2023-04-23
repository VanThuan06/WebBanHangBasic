package Java6.ASM.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Java6.ASM.Entities.Order;
import Java6.ASM.Entities.OrderDetail;
import Java6.ASM.Repositories.OderDetailRepository;
import Java6.ASM.Repositories.OderRepository;
import Java6.ASM.service.OderSercvice;

@Service
public class OderServiceImpl implements OderSercvice{
    @Autowired
    OderRepository oderRepository;

    @Autowired
    OderDetailRepository oderDetailRepository;
    
    public OderServiceImpl (OderRepository oderRepository){
        super();
        this.oderRepository = oderRepository;
    }

    @Override
    public Order create(JsonNode oderData) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("000000000000000000000000000000000000000000000000000");
        System.out.println("account :" + oderData.get("address").asText() +"---------");
        Order order = mapper.convertValue(oderData, Order.class);
        System.out.println("000000000000000000000000000000000000000000000000000");
        oderRepository.save(order);
        System.out.println(order.getAccount());
        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(oderData.get("orderDetails"), type).
        stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        oderDetailRepository.saveAll(details);
        return order;
    }

    @Override
    public Order findByid(Long id) {
        return oderRepository.findById(id).get();
    }

    @Override
    public List<Order> findByUsername(String username) {
        return oderRepository.findByUsername(username);
    }
    
}
