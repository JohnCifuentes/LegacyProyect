package com.legacybridge.service;

import com.legacybridge.dto.transaction.ReadTransactionDTO;

import java.util.List;

public interface LegacyBridgeService {

    void readAndSaveLegacyApi();

    List<ReadTransactionDTO> getAllTransactions();

    List<ReadTransactionDTO> getTransactionsByMerchantIdCategoryId(Long merchantId, Long categoryId);

}
