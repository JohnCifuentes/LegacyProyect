package com.legacybridge.service.implement;

import com.legacybridge.config.Settings;
import com.legacybridge.dto.TransactionResponseXmlDTO;
import com.legacybridge.dto.TransactionXmlDTO;
import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.dto.merchant.ReadMerchantDTO;
import com.legacybridge.dto.transaction.CreateTransactionDTO;
import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.service.LegacyBridgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LegacyBridgeServiceImplement implements LegacyBridgeService {
    private final LegacyApiServiceImplement legacyApiServiceImplement;
    private final MerchantServiceImplement merchantService;
    private final CategoryDetailServiceImplement categoryDetailService;
    private final TransactionServiceImplement transactionService;

    private final Settings settings = new Settings();

    @Override
    public void readAndSaveLegacyApi() {
        List<CreateTransactionDTO> transactions = new ArrayList<>();
        TransactionResponseXmlDTO transactionResponseXml = legacyApiServiceImplement.getTransactions();
        List<TransactionXmlDTO> transactionsXml = transactionResponseXml.transactions();

        transactionsXml.forEach(transactionXml -> {
            Long merchantId = getMerchantId(transactionXml.description());
            Long categoryId = getCategoryId(transactionXml.description());
            transactions.add(
                   new CreateTransactionDTO(
                           transactionXml.txn_id(),
                           merchantId,
                           categoryId,
                           transactionXml.description(),
                           getAmountClean(transactionXml.amount()),
                           transactionXml.currency(),
                           getTransactionDateClean(transactionXml.date())
                   )
           );
        });

        for(CreateTransactionDTO transactionDTO : transactions) {
            transactionService.createTransaction(transactionDTO);
        }

    }

    private static int countOrderedMatches(String keyword, String descriptionWord) {
        int matchCount = 0;
        int keywordIndex = 0;

        for (int i = 0; i < descriptionWord.length() && keywordIndex < keyword.length(); i++) {
            if (descriptionWord.charAt(i) == keyword.charAt(keywordIndex)) {
                matchCount++;
                keywordIndex++;
            }
        }

        return matchCount;
    }

    public int calculateSimilarity(String keyword, String description) {
        if (keyword == null || description == null) {
            return 0;
        }

        keyword = keyword.toUpperCase();
        description = description.toUpperCase();

        String[] words = description.split("\\s+");

        int maxMatches = 0;

        for (String word : words) {
            int matches = countOrderedMatches(keyword, word);
            maxMatches = Math.max(maxMatches, matches);
        }

        return (maxMatches / keyword.length()) * 100;
    }

    public Long getMerchantId(String description) {
        List<ReadMerchantDTO> merchantsDTO = merchantService.getMerchants();
        for(ReadMerchantDTO merchant: merchantsDTO) {
            if(calculateSimilarity(merchant.name(), description) > 90)
                return merchant.merchantId();
        }
        return (long) 0;
    }

    public Long getCategoryId(String description) {
        List<ReadCategoryDetailDTO> categoryDetailsDTO = categoryDetailService.getAllCategoryDetails();
        for(ReadCategoryDetailDTO category: categoryDetailsDTO) {
            if(calculateSimilarity(category.value(), description) == 100)
                return category.category().getCategoryId();
        }
        return (long) 0;
    }

    public Double getAmountClean(String amount) {
        String cleanAmount = amount.replaceAll("[^\\d.]", "");
        int lastDot = cleanAmount.lastIndexOf('.');
        if (lastDot != -1) {
            cleanAmount = cleanAmount.substring(0, lastDot).replaceAll("\\.", "") + cleanAmount.substring(lastDot);
        }
        return amount.trim().isEmpty() ? 0 : Double.parseDouble(cleanAmount);
    }

    public LocalDateTime getTransactionDateClean(String transactionDate){
        if (transactionDate == null || transactionDate.isBlank()) {
            return LocalDateTime.of(1970, 1, 1, 0, 0);
        }

        transactionDate = transactionDate.trim();

        for (DateTimeFormatter formatter : settings.getFormatters()) {
            try {
                return LocalDate
                        .parse(transactionDate, formatter)
                        .atStartOfDay(); // 00:00:00
            } catch (DateTimeParseException ignored) {
            }
        }

        return LocalDateTime.of(1970, 1, 1, 0, 0);
    }

    @Override
    public List<ReadTransactionDTO> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @Override
    public List<ReadTransactionDTO> getTransactionsByMerchantIdCategoryId(Long merchantId, Long categoryId) {
        System.out.println("merchantId: " + merchantId);
        System.out.println("categoryId: " + categoryId);
        return transactionService.getTransactionsByMerchantIdCategoryId(merchantId, categoryId);
    }

}
