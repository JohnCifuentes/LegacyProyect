package com.legacybridge.service.implement;

import com.legacybridge.dto.merchant.CreateMerchantDTO;
import com.legacybridge.dto.merchant.DeleteMerchantDTO;
import com.legacybridge.dto.merchant.ReadMerchantDTO;
import com.legacybridge.dto.merchant.UpdateMerchantDTO;
import com.legacybridge.mapper.MerchantMapper;
import com.legacybridge.model.Merchant;
import com.legacybridge.repository.MerchantRepository;
import com.legacybridge.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImplement implements MerchantService {
    private final MerchantMapper merchantMapper;
    private final MerchantRepository merchantRepository;

    @Override
    public void createMerchant(CreateMerchantDTO merchantDTO) {
        Merchant merchant = merchantMapper.toEntity(merchantDTO);
        merchantRepository.save(merchant);
    }

    @Override
    public void updateMerchant(UpdateMerchantDTO merchantDTO) {
        Merchant merchant = merchantRepository.findById(merchantDTO.merchantId()).orElseThrow(()
            -> new RuntimeException("Merchant not found")
        );
        merchantMapper.updateEntityFromDTO(merchantDTO, merchant);
        merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(DeleteMerchantDTO merchantDTO) {
        try{
            merchantRepository.deleteById(merchantDTO.merchantId());
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar el comerciante, existen registros asociados.");
        }
    }

    @Override
    public ReadMerchantDTO getMerchant(Long merchantId) {
        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(()
                -> new RuntimeException("Merchant not found")
        );
        return merchantMapper.toDTO(merchant);
    }

    @Override
    public List<ReadMerchantDTO> getMerchants() {
        return merchantRepository.findByOrderByMerchantIdAsc();
    }

}
