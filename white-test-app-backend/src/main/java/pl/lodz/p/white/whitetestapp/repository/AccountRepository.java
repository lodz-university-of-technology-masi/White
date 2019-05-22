package pl.lodz.p.white.whitetestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Role;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);

    List<Account> findAllByRole(Role role);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
