package com.bank.transfers.web.controller;

import com.bank.transfers.application.ScheduleTransferService;
import com.bank.transfers.domain.Transfer;
import com.bank.transfers.web.dto.TransferRequest;
import com.bank.transfers.web.dto.TransferResponse;
import com.bank.transfers.web.mapper.TransferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final ScheduleTransferService scheduleService;

    @PostMapping
    public ResponseEntity<TransferResponse> create(@Validated @RequestBody TransferRequest req) {
        Transfer entity = TransferMapper.toEntity(req);
        Transfer saved = scheduleService.schedule(entity);
        return ResponseEntity.ok(TransferMapper.toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<TransferResponse>> listAll() {
        List<TransferResponse> body = scheduleService.listAll()
                .stream().map(TransferMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(body);
    }
}
