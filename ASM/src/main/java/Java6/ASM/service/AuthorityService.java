package Java6.ASM.service;

import java.util.List;

import Java6.ASM.Entities.Authority;

public interface AuthorityService {

    List<Authority> findAuthoritiesOfAdministrators();

    List<Authority> findAll();

    public void delete(Integer id);

    public Authority create(Authority auth);

    
}
