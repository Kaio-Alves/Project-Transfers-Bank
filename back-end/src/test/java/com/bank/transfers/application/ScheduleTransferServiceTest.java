package com.bank.transfers.application;

import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.ports.FeeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class ScheduleTransferServiceTest {

    @Mock
    private FeeCalculator feeCalculator;

    @InjectMocks
    private ScheduleTransferService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleTransfer() {
        Transfer transfer = Transfer.builder()
                .amount(BigDecimal.valueOf(1000))
                .build();

        when(feeCalculator.calculateFee(transfer)).thenReturn(BigDecimal.valueOf(12));

        service.schedule(transfer);

        verify(feeCalculator).calculateFee(transfer);
        // aqui você poderia verificar se o transfer foi salvo ou auditado
    }
}
