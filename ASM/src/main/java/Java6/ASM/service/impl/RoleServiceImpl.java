package Java6.ASM.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.ASM.Entities.Role;
import Java6.ASM.Repositories.RoleRelository;
import Java6.ASM.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRelository roleRelository;
    
    @Override
    public List<Role> findAll() {
        return roleRelository.findAll();
    }
    
}
