package com.bank.transfers.domain.ports;

import com.bank.transfers.domain.Transfer;
import java.math.BigDecimal;

public interface FeeCalculator {
    BigDecimal calculateFee(Transfer transfer);
}
