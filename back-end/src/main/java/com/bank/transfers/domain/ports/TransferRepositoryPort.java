package com.bank.transfers.domain.ports;

import com.bank.transfers.domain.Transfer;
import java.util.List;
import java.util.Optional;

public interface TransferRepositoryPort {
    Transfer save(Transfer t);
    List<Transfer> findAll();
    Optional<Transfer> findById(Long id);
}
