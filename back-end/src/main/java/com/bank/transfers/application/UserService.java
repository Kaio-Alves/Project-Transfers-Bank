package com.bank.transfers.application;

import com.bank.transfers.domain.AppUser;
import com.bank.transfers.domain.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public AppUser register(String username, String rawPassword) {
        AppUser user = AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .role("ROLE_USER")
                .build();
        return userRepository.save(user);
    }
}
