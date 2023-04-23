package Java6.ASM.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Java6.ASM.Entities.Account;
import Java6.ASM.Entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority,Integer>{
    @Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
    List<Authority> authorities(List<Account> ac);
}
