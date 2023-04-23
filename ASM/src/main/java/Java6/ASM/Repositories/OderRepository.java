package Java6.ASM.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Java6.ASM.Entities.Order;

public interface OderRepository extends JpaRepository<Order,Long>{

    @Query("SELECT o FROM Order o WHERE o.account.username=?1")
    List<Order> findByUsername(String username);
 
}
