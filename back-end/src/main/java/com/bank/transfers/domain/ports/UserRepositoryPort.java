package com.bank.transfers.domain.ports;

import com.bank.transfers.domain.AppUser;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<AppUser> findByUsername(String username);
    AppUser save(AppUser user);
}
