package com.legacybridge.dto.transaction;

import com.legacybridge.model.Category;
import com.legacybridge.model.Merchant;

import java.time.LocalDateTime;

public record ReadTransactionDTO(
        Long transactionId,
        String transactionReference,
        Merchant merchant,
        Category category,
        String description,
        Double amount,
        String currency,
        LocalDateTime transactionDate,
        LocalDateTime createDate
) {
}
