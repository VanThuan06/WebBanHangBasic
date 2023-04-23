package Java6.ASM.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.ASM.Entities.Account;
import Java6.ASM.Repositories.AccountRepository;
import Java6.ASM.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository aRepository;

    @Override
    public Account findById(String useName) {
        return aRepository.findById(useName).get();
    }

    @Override
    public List<Account> getAdministrators() {
        return aRepository.getAdministrators();
    }

    @Override
    public List<Account> findAll() {
        return aRepository.findAll();
    }
    
}
