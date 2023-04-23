package Java6.ASM.Rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Java6.ASM.Entities.Product;
import Java6.ASM.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService pService;

    @GetMapping()
    public List<Product> getAll() {
        return pService.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id) {
        return pService.findByid(id);
    }
 
	@PostMapping
	public Product create(@RequestBody Product product) {
		return pService.create(product);
	}
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id,@RequestBody Product product) {
		Product prd = pService.update(product);
		return prd;
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		pService.delete(id); 
	}
}
