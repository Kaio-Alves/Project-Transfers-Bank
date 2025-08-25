package com.bank.transfers.application;

import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.exceptions.InvalidTransferException;
import com.bank.transfers.domain.ports.FeeCalculator;
import com.bank.transfers.domain.ports.TransferRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleTransferService {

    private final TransferRepositoryPort transferRepository;
    private final FeeCalculator feeCalculator;

    public Transfer schedule(Transfer t) {
        if (t.getScheduledDate() == null) {
            t.setScheduledDate(LocalDate.now());
        }
        if (t.getTransferDate() == null || t.getTransferDate().isBefore(t.getScheduledDate())) {
            throw new InvalidTransferException("Data de transferência inválida");
        }
        BigDecimal fee = feeCalculator.calculateFee(t);
        t.setFee(fee);
        return transferRepository.save(t);
    }

    public List<Transfer> listAll() {
        return transferRepository.findAll();
    }
}
