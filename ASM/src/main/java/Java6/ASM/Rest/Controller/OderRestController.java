package Java6.ASM.Rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import Java6.ASM.Entities.Order;
import Java6.ASM.service.OderSercvice;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OderRestController {
    @Autowired
    OderSercvice oderSercvice;

    @PostMapping()
    public Order create(@RequestBody JsonNode oder){
        System.out.println("-------------------------------");
        System.out.println(oder);
        return oderSercvice.create(oder);
    }

}
