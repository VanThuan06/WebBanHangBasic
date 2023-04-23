package Java6.ASM.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Java6.ASM.Entities.OrderDetail;

public interface OderDetailRepository extends JpaRepository<OrderDetail,Long>{
    
}
