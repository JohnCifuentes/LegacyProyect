package com.legacybridge.service.implement;

import com.legacybridge.dto.transaction.CreateTransactionDTO;
import com.legacybridge.dto.transaction.DeleteTransactionDTO;
import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.dto.transaction.UpdateTransactionDTO;
import com.legacybridge.mapper.TransactionMapper;
import com.legacybridge.model.Category;
import com.legacybridge.model.Merchant;
import com.legacybridge.model.Transaction;
import com.legacybridge.repository.CategoryRepository;
import com.legacybridge.repository.MerchantRepository;
import com.legacybridge.repository.TransactionRepository;
import com.legacybridge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImplement implements TransactionService {
    private final MerchantRepository merchantRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(CreateTransactionDTO transactionDTO) {
        Merchant merchant = merchantRepository.findById(transactionDTO.merchantId()).orElseThrow(
                () -> new RuntimeException("Merchant not found")
        );
        Category category = categoryRepository.findById(transactionDTO.categoryId()).orElseThrow(
                () -> new RuntimeException("Category not found")
        );
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setMerchant(merchant);
        transaction.setCategory(category);
        transaction.setCreateDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransaction(UpdateTransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(transactionDTO.transactionId()).orElseThrow(
                () -> new RuntimeException("Transaction not found")
        );
        transactionMapper.updateEntityFromDTO(transactionDTO, transaction);
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(DeleteTransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(transactionDTO.transactionId()).orElseThrow(
                () -> new RuntimeException("Transaction not found")
        );
        transactionMapper.deleteEntityFromDTO(transactionDTO, transaction);
        transactionRepository.save(transaction);
    }

    @Override
    public ReadTransactionDTO getTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(
                () -> new RuntimeException("Transaction not found")
        );
        return transactionMapper.toDTO(transaction);
    }

    @Override
    public List<ReadTransactionDTO> getTransactions() {
        return transactionRepository.findByOrderByTransactionIdAsc();
    }

    @Override
    public List<ReadTransactionDTO> getTransactionsByMerchantIdCategoryId(Long merchantId, Long categoryId) {
        System.out.println("merchantId: " + merchantId);
        System.out.println("categoryId: " + categoryId);
        return transactionRepository.findByCategoryAndMerchant(merchantId, categoryId)
                .stream().map(transactionMapper::toDTO).collect(Collectors.toList());
    }

}
