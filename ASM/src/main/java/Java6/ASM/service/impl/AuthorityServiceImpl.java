package Java6.ASM.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.ASM.Entities.Account;
import Java6.ASM.Entities.Authority;
import Java6.ASM.Repositories.AccountRepository;
import Java6.ASM.Repositories.AuthorityRepository;
import Java6.ASM.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Authority> findAuthoritiesOfAdministrators() {
        List<Account> ac = accountRepository.getAdministrators();
        return authorityRepository.authorities(ac);
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
         authorityRepository.deleteById(id);;
    }

    @Override
    public Authority create(Authority auth) {
        return authorityRepository.save(auth);
    }

}
