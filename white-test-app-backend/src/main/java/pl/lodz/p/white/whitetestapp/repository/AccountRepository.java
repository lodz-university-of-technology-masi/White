package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
}
