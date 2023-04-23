package Java6.ASM.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Java6.ASM.Entities.Role;

public interface RoleRelository extends JpaRepository<Role,String>{
    
}
