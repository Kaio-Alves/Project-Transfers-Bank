package com.bank.transfers.application;

import com.bank.transfers.domain.AppUser;
import com.bank.transfers.infrastructure.repository.UserRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepositoryAdapter userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByUsername() {
        AppUser user = AppUser.builder().username("kaio").password("123").build();
        when(userRepository.findByUsername("kaio")).thenReturn(Optional.of(user));

        AppUser found = userService.findByUsername("kaio").orElse(null);

        assertNotNull(found);
        assertEquals("kaio", found.getUsername());
        verify(userRepository).findByUsername("kaio");
    }
}
