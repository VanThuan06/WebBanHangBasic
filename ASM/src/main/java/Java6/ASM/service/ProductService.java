package Java6.ASM.service;

import java.util.List;

import Java6.ASM.Entities.Product;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByCategoryId(String cid);

    Product findByid(Integer id);
    Product create(Product product);
    Product update(Product product);
    void delete(Integer id);

}
