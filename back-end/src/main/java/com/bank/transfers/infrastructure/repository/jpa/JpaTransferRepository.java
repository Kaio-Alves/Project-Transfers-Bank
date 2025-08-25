package com.bank.transfers.infrastructure.repository.jpa;

import com.bank.transfers.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransferRepository extends JpaRepository<Transfer, Long> { }
