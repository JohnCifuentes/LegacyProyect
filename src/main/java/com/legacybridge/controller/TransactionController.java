package com.legacybridge.controller;

import com.legacybridge.dto.transaction.CreateTransactionDTO;
import com.legacybridge.dto.transaction.DeleteTransactionDTO;
import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.dto.transaction.UpdateTransactionDTO;
import com.legacybridge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public void createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO) {
        transactionService.createTransaction(createTransactionDTO);
    }

    @PutMapping
    public void updateTransaction(@RequestBody UpdateTransactionDTO updateTransactionDTO) {
        transactionService.updateTransaction(updateTransactionDTO);
    }

    @DeleteMapping
    public void deleteTransaction(@RequestBody DeleteTransactionDTO deleteTransactionDTO) {
        transactionService.deleteTransaction(deleteTransactionDTO);
    }

    @GetMapping("/get/transaction")
    public ReadTransactionDTO getTransaction(@RequestParam Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @GetMapping("/get/transactions")
    public List<ReadTransactionDTO> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/get/transactions/category/merchant")
    public List<ReadTransactionDTO> getTransactionsByMerchantIdCategoryId(@RequestParam(required = false) Long merchantId, @RequestParam(required = false) Long categoryId) {
        return transactionService.getTransactionsByMerchantIdCategoryId(merchantId, categoryId);
    }

}
