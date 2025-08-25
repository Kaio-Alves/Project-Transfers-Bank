package com.bank.transfers.infrastructure.repository;

import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.ports.TransferRepositoryPort;
import com.bank.transfers.infrastructure.repository.jpa.JpaTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransferRepositoryAdapter implements TransferRepositoryPort {

    private final JpaTransferRepository jpa;

    @Override
    public Transfer save(Transfer t) {
        return jpa.save(t);
    }

    @Override
    public List<Transfer> findAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return jpa.findById(id);
    }
}
