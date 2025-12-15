package com.legacybridge.dto.transaction;

import java.time.LocalDateTime;

public record UpdateTransactionDTO(
        Long transactionId,
        String transactionReference,
        Long merchantId,
        Long categoryId,
        String description,
        Double amount,
        String currency,
        LocalDateTime transactionDate
) {
}
