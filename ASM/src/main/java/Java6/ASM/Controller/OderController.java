package Java6.ASM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import Java6.ASM.service.OderSercvice;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OderController {
    @Autowired
    OderSercvice oderSercvice;


    @RequestMapping("/oder/checkout")
    public String checkOut(){
        return "oder/checkout";
    }

    @RequestMapping("/oder/list")
    public String list(Model model,HttpServletRequest request){
        String userName = request.getRemoteUser();
        
        model.addAttribute("orders", oderSercvice.findByUsername(userName));
        return "oder/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail(@PathVariable("id") Long id,Model model){
        model.addAttribute("order", oderSercvice.findByid(id));
        return "oder/detail";
    }
}
