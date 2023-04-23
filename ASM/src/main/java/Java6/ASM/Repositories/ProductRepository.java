package Java6.ASM.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Java6.ASM.Entities.Product;
public interface ProductRepository extends JpaRepository<Product,Integer>{

    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(String cid);
    
}
