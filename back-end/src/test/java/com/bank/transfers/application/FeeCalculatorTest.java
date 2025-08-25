package com.bank.transfers.application;

import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.exceptions.InvalidTransferException;
import com.bank.transfers.domain.ports.FeeCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FeeCalculatorTest {

    private final FeeCalculator feeCalculator = new FeeCalculator() {
        @Override
        public BigDecimal calculateFee(Transfer transfer) {
            return null;
        }
    };

    @Test
    void testFeeForSameDayTransfer() {
        Transfer transfer = Transfer.builder()
                .amount(BigDecimal.valueOf(1000))
                .scheduledDate(LocalDate.of(2025, 9, 1))
                .transferDate(LocalDate.of(2025, 9, 1))
                .build();

        BigDecimal fee = feeCalculator.calculateFee(transfer);
        assertEquals(BigDecimal.valueOf(28), fee); // 1000*0.025 + 3
    }

    @Test
    void testFeeForDays11to20() {
        Transfer transfer = Transfer.builder()
                .amount(BigDecimal.valueOf(1000))
                .scheduledDate(LocalDate.of(2025, 9, 1))
                .transferDate(LocalDate.of(2025, 9, 15))
                .build();

        BigDecimal fee = feeCalculator.calculateFee(transfer);
        assertEquals(BigDecimal.valueOf(82), fee); // 1000*0.082
    }

    @Test
    void testInvalidTransferDays() {
        Transfer transfer = Transfer.builder()
                .amount(BigDecimal.valueOf(1000))
                .scheduledDate(LocalDate.of(2025, 9, 1))
                .transferDate(LocalDate.of(2025, 11, 1)) // 61 dias
                .build();

        assertThrows(InvalidTransferException.class,
                () -> feeCalculator.calculateFee(transfer));
    }
}
