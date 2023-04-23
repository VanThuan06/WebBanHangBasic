package Java6.ASM.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Java6.ASM.Entities.Product;
import Java6.ASM.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService prdService;

    @RequestMapping("/product/list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid) {
        if (cid.isPresent()) {
            List<Product> products = prdService.findByCategoryId(cid.get());
            model.addAttribute("items", products);
        } else {
            List<Product> products = prdService.findAll();
            model.addAttribute("items", products);
        }
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product itemn = prdService.findByid(id);
        model.addAttribute("item", itemn);
        return "product/detail";
    }

}
