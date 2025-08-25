package com.bank.transfers.application;

import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.exceptions.InvalidTransferException;
import com.bank.transfers.domain.ports.FeeCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Component
public class FeeCalculatorImpl implements FeeCalculator {

    @Override
    public BigDecimal calculateFee(Transfer transfer) {
        long days = ChronoUnit.DAYS.between(transfer.getScheduledDate(), transfer.getTransferDate());

        if (days == 0) {
            BigDecimal fixed = BigDecimal.valueOf(3);
            BigDecimal percent = transfer.getAmount().multiply(BigDecimal.valueOf(0.025));
            return fixed.add(percent);
        }
        if (days >= 1 && days <= 10) return BigDecimal.valueOf(12).setScale(2, RoundingMode.HALF_UP);
        if (days >= 11 && days <= 20) return transfer.getAmount().multiply(BigDecimal.valueOf(0.082)).setScale(2, RoundingMode.HALF_UP);
        if (days >= 21 && days <= 30) return transfer.getAmount().multiply(BigDecimal.valueOf(0.069)).setScale(2, RoundingMode.HALF_UP);
        if (days >= 31 && days <= 40) return transfer.getAmount().multiply(BigDecimal.valueOf(0.047)).setScale(2, RoundingMode.HALF_UP);
        if (days >= 41 && days <= 50) return transfer.getAmount().multiply(BigDecimal.valueOf(0.017)).setScale(2, RoundingMode.HALF_UP);

        throw new InvalidTransferException("Taxa não aplicável para esse agendamento");
    }
}
