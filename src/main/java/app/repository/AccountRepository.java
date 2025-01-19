package app.repository;

import app.dto.Account;

public interface AccountRepository {
    void save(Account account);
    Account findByUsername(String username);
}
