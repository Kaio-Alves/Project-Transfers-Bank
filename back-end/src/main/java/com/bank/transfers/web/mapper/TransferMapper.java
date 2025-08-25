package com.bank.transfers.web.mapper;

import com.bank.transfers.domain.Transfer;
import com.bank.transfers.web.dto.TransferRequest;
import com.bank.transfers.web.dto.TransferResponse;

public class TransferMapper {

    public static Transfer toEntity(TransferRequest dto) {
        return Transfer.builder()
                .sourceAccount(dto.getSourceAccount())
                .destinationAccount(dto.getDestinationAccount())
                .amount(dto.getAmount())
                .transferDate(dto.getTransferDate())
                .build();
    }

    public static TransferResponse toResponse(Transfer t) {
        return new TransferResponse(
                t.getId(),
                t.getSourceAccount(),
                t.getDestinationAccount(),
                t.getAmount(),
                t.getFee(),
                t.getScheduledDate(),
                t.getTransferDate()
        );
    }
}
