package com.legacybridge.controller;

import com.legacybridge.dto.merchant.CreateMerchantDTO;
import com.legacybridge.dto.merchant.DeleteMerchantDTO;
import com.legacybridge.dto.merchant.ReadMerchantDTO;
import com.legacybridge.dto.merchant.UpdateMerchantDTO;
import com.legacybridge.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/merchant")
@CrossOrigin(origins = "http://localhost:4200")
public class MerchantController {
    private final MerchantService merchantService;

    @PostMapping
    public void createMerchant(@RequestBody CreateMerchantDTO merchantDTO){
        merchantService.createMerchant(merchantDTO);
    }

    @PutMapping
    public void updateMerchant(@RequestBody UpdateMerchantDTO merchantDTO){
        merchantService.updateMerchant(merchantDTO);
    }

    @DeleteMapping
    public void deleteMerchant(@RequestBody DeleteMerchantDTO merchantDTO){
        merchantService.deleteMerchant(merchantDTO);
    }

    @GetMapping("/get/merchant")
    public ReadMerchantDTO getMerchant(@RequestParam  Long merchantId){
        return merchantService.getMerchant(merchantId);
    }

    @GetMapping("/get/merchants")
    public List<ReadMerchantDTO> getMerchants(){
        return merchantService.getMerchants();
    }

}
