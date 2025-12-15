package com.legacybridge.service;

import com.legacybridge.dto.transaction.CreateTransactionDTO;
import com.legacybridge.dto.transaction.DeleteTransactionDTO;
import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.dto.transaction.UpdateTransactionDTO;

import java.util.List;

public interface TransactionService {

    void createTransaction(CreateTransactionDTO createTransactionDTO);

    void updateTransaction(UpdateTransactionDTO updateTransactionDTO);

    void deleteTransaction(DeleteTransactionDTO deleteTransactionDTO);

    ReadTransactionDTO getTransaction(Long transactionId);

    List<ReadTransactionDTO> getTransactions();

    List<ReadTransactionDTO> getTransactionsByMerchantIdCategoryId(Long merchantId, Long categoryId);

}
