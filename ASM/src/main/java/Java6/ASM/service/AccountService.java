package Java6.ASM.service;

import java.util.List;

import Java6.ASM.Entities.Account;

public interface AccountService {
    Account findById(String useName);

    List<Account> getAdministrators();

    List<Account> findAll();
}
