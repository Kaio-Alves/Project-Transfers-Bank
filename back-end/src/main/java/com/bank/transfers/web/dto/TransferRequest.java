package com.bank.transfers.web.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferRequest {

    @NotBlank
    private String sourceAccount;

    @NotBlank
    private String destinationAccount;

    @NotNull @DecimalMin("0.01")
    private BigDecimal amount;

    @NotNull
    private LocalDate transferDate; // scheduledDate será "hoje" no service
}
