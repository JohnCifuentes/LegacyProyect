package com.legacybridge.controller;

import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.service.LegacyBridgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/legacy/bridge")
@CrossOrigin(origins = "http://localhost:4200")
public class LegacyBridgeController {
    private final LegacyBridgeService legacyBridgeService;

    @PostMapping
    public void readAndSaveLegacyApi(){
        legacyBridgeService.readAndSaveLegacyApi();
    }

    @GetMapping("/get/transactions")
    public List<ReadTransactionDTO> getAllTransactions(){
        return legacyBridgeService.getAllTransactions();
    }

    @GetMapping("/get/transactions/filters")
    public List<ReadTransactionDTO> getTransactionsByMerchantIdCategoryId(@RequestParam(required = false) Long merchantId, @RequestParam(required = false) Long categoryId){
        return legacyBridgeService.getTransactionsByMerchantIdCategoryId(merchantId, categoryId);
    }

}
