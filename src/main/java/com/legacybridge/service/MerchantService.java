package com.legacybridge.service;

import com.legacybridge.dto.merchant.CreateMerchantDTO;
import com.legacybridge.dto.merchant.DeleteMerchantDTO;
import com.legacybridge.dto.merchant.ReadMerchantDTO;
import com.legacybridge.dto.merchant.UpdateMerchantDTO;

import java.util.List;

public interface MerchantService {

    void createMerchant(CreateMerchantDTO merchantDTO);

    void updateMerchant(UpdateMerchantDTO merchantDTO);

    void deleteMerchant(DeleteMerchantDTO merchantDTO);

    ReadMerchantDTO getMerchant(Long merchantId);

    List<ReadMerchantDTO> getMerchants();

}
