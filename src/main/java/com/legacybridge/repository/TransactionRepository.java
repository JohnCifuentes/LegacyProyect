package com.legacybridge.repository;

import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<ReadTransactionDTO> findByOrderByTransactionIdAsc();

    @Query("""
        SELECT t
        FROM Transaction t
        WHERE (:categoryId IS NULL OR t.category.categoryId = :categoryId)
          AND (:merchantId IS NULL OR t.merchant.merchantId = :merchantId)
        ORDER BY t.transactionId ASC
    """)
    List<Transaction> findByCategoryAndMerchant(
            @Param("merchantId") Long merchantId,
            @Param("categoryId") Long categoryId
    );

}
