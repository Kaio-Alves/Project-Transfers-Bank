package com.bank.transfers.infrastructure.repository;

import com.bank.transfers.domain.AppUser;
import com.bank.transfers.domain.ports.UserRepositoryPort;
import com.bank.transfers.infrastructure.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpa;

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return jpa.findByUsername(username);
    }

    @Override
    public AppUser save(AppUser user) {
        return jpa.save(user);
    }
}
