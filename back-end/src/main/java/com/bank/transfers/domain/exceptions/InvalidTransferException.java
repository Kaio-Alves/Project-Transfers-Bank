package com.bank.transfers.domain.exceptions;

public class InvalidTransferException extends BusinessException {
    public InvalidTransferException(String message) { super(message); }
}
